package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.SellService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Service.WarehouseBalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteSellAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String sellNumber;// 销售单号
	private String sells;// 销售信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getSellNumber() {
		return sellNumber;
	}
	
	public void setSellNumber(String sellNumber) {
		this.sellNumber = sellNumber;
	}
	
	public String getSells() {
		return sells;
	}
	
	public void setSells(String sells) {
		this.sells = sells;
	}
	
	/**
	 * 获取销售主信息
	 * @return 销售主信息
	 */
	public String getSell()
	{
		SellService sellService = new SellService();
		// 获取销售主信息
		List<Map<String, String>> sell = sellService.getSell(sellNumber);
		returnString = JSONArray.fromObject(sell).toString();
		return "getSell";
	}
	
	/**
	 * 获取销售明细信息
	 * @return 销售明细信息
	 */
	public String getSellDetails()
	{
		SellService sellService = new SellService();
		// 获取销售明细信息
		List<Map<String, String>> sellDetails = sellService.getSellDetails(sellNumber);
		returnString = JSONArray.fromObject(sellDetails).toString();
		return "getSellDetails";
	}
	
	/**
	 * 删除销售信息
	 * @return 返回删除结果
	 */
	public String saveDeleteInfo()
	{
		SellService sellService = new SellService();
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		TransactionManager transactionManager = new TransactionManager();
		JSONObject jsonObj = JSONObject.fromObject(sells);
		List<Map<String, String>> sellsList = jsonObj.getJSONArray("data");
		try {
			transactionManager.start();
			Connection conn = transactionManager.getConnection();
			// 遍历销售明细信息
			for (Map<String, String> map : sellsList)
			{
				// 品名
				String goodsName = map.get("goodsName");
				// 销售数量
				int amount = Integer.parseInt(map.get("amount"));
				// 销售仓库
				String warehouseName = map.get("warehouseName");
				// 增加商品库存量
				warehouseBalanceService.increaseBalanceNumber(conn, warehouseName, "", goodsName, "", amount);
			}
			// 删除销售单
			sellService.deleteSell(conn, sellNumber);
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			returnString = "false";
		} finally {
			transactionManager.close();
		}
		return "getSaveResult";
	}
}
