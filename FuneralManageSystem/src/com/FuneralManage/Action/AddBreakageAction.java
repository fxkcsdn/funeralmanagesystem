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
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddBreakageAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String goodsType;// 商品种类
	private String goodsName;// 品名
	private String warehouseName;// 报损仓库
	private String breakages;// 报损信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getGoodsType() {
		return goodsType;
	}
	
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getWarehouseName() {
		return warehouseName;
	}
	
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	public String getBreakages() {
		return breakages;
	}

	public void setBreakages(String breakages) {
		this.breakages = breakages;
	}

	/**
	 * 获取所有商品种类信息
	 * @return 商品种类
	 */
	public String getGoodsTypesInWarehouse()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 获取仓库里所有商品种类信息
		List<Map<String, String>> goodsTypes = warehouseBalanceService.getGoodsTypesInWarehouse(warehouseName);
		returnString = JSONArray.fromObject(goodsTypes).toString();
		return "getGoodsTypes";
	}
	
	/**
	 * 获取品名
	 * @return 品名
	 */
	public String getGoodsNamesInWarehouse()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 根据商品种类获取相应的品名
		List<Map<String, String>> goodsNames = warehouseBalanceService.getGoodsNamesInWarehouse(warehouseName, goodsType);
		returnString = JSONArray.fromObject(goodsNames).toString();
		return "getGoodsNames";
	}
	
	/**
	 * 获取单位和库存数量
	 * @return 单位和库存数量
	 */
	public String getUnitAndNum()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 根据品名、商品种类获取相应的单位和库存数量
		List<Map<String, String>> unitAndNum = warehouseBalanceService.getUnitAndNum(warehouseName, goodsType, goodsName);
		returnString = JSONArray.fromObject(unitAndNum).toString();
		return "getUnitAndNum";
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
			// 获取最新报损单号
			String breakageNumber = NumberUtil.createBreakageNumber(breakagesList.get(0).get("breakageDate"));
			// 遍历报损信息
			for (Map<String, String> map : breakagesList)
			{
				// 品名
				String goodsName = map.get("goodsName");
				// 报损数量
				int amount = Integer.parseInt(map.get("amount"));
				// 报损仓库
				String warehouseName = map.get("warehouseName");
				// 新增报损信息
				breakageService.addBreakage(conn, map, breakageNumber);
				// 减少库存量
				warehouseBalanceService.reduceBalanceNumber(conn, warehouseName, goodsName, amount);
			}
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (Exception e) {
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
