package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.BreakageService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Service.WarehouseBalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateBreakageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String breakageNumber;// 报损单号
	private String breakages;// 报损信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getBreakageNumber() {
		return breakageNumber;
	}
	
	public void setBreakageNumber(String breakageNumber) {
		this.breakageNumber = breakageNumber;
	}

	public String getBreakages() {
		return breakages;
	}

	public void setBreakages(String breakages) {
		this.breakages = breakages;
	}

	/**
	 * 获取报损主信息
	 * @return 报损主信息
	 */
	public String getBreakage()
	{
		BreakageService breakageService = new BreakageService();
		// 获取报损主信息
		List<Map<String, String>> breakage = breakageService.getBreakage(breakageNumber);
		returnString = JSONArray.fromObject(breakage).toString();
		return "getBreakage";
	}
	
	/**
	 * 获取报损明细信息
	 * @return 报损明细信息
	 */
	public String getBreakageDetails()
	{
		BreakageService breakageService = new BreakageService();
		// 获取报损明细信息
		List<Map<String, String>> breakageDetails = breakageService.getBreakageDetails(breakageNumber);
		returnString = JSONArray.fromObject(breakageDetails).toString();
		return "getBreakageDetails";
	}
	
	/**
	 * 保存报损信息
	 * @return
	 */
	public String saveBreakageInfo()
	{
		BreakageService breakageService = new BreakageService();
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		TransactionManager transactionManager = new TransactionManager();
		JSONObject jsonObj = JSONObject.fromObject(breakages);
		List<Map<String, String>> breakagesList = jsonObj.getJSONArray("data");
		try {
			transactionManager.start();
			Connection conn = transactionManager.getConnection();
			// 遍历报损明细信息
			for (Map<String, String> map : breakagesList)
			{
				// 品名
				String goodsName = map.get("goodsName");
				// 库存数量
				int balanceNumber = Integer.parseInt(map.get("balanceNumber"));
				// 销售仓库
				String warehouseName = map.get("warehouseName");
				// 还原商品库存量
				warehouseBalanceService.resetBalanceNumber(conn, warehouseName, goodsName, balanceNumber);
			}
			// 删除报损单
			breakageService.deleteBreakage(conn, breakageNumber);
			// 遍历明细信息
			for (Map<String, String> map : breakagesList)
			{
				// 品名
				String goodsName = map.get("goodsName");
				// 销售数量
				int amount = Integer.parseInt(map.get("amount"));
				// 销售仓库
				String warehouseName = map.get("warehouseName");
				// 新增报损信息
				breakageService.addBreakage(conn, map, breakageNumber);
				// 减少库存量
				warehouseBalanceService.reduceBalanceNumber(conn, warehouseName, goodsName, amount);
			}
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 回滚事务
			transactionManager.rollback();
			returnString = "false";
		} finally {
			// 关闭事务
			transactionManager.close();	
		}
		return "getSaveResult";
	}
}
