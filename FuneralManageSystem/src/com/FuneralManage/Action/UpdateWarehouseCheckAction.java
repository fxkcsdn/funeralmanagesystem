package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.WarehouseCheckService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateWarehouseCheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String warehouseCheckNumber;// 盘点单号
	private String goods;// 商品信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getWarehouseCheckNumber() {
		return warehouseCheckNumber;
	}
	
	public void setWarehouseCheckNumber(String warehouseCheckNumber) {
		this.warehouseCheckNumber = warehouseCheckNumber;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 获取盘点单主信息
	 * @return 盘点单主信息
	 */
	public String getWarehouseCheck()
	{
		WarehouseCheckService warehouseCheckService = new WarehouseCheckService();
		// 获取盘点单信息
		returnString = warehouseCheckService.getWarehouseCheck(warehouseCheckNumber);
		return "getWarehouseCheck";
	}
	
	/**
	 * 获取盘点单明细信息
	 * @return 盘点单明细信息
	 */
	public String getWarehouseCheckDetails()
	{
		WarehouseCheckService warehouseCheckService = new WarehouseCheckService();
		// 获取盘点单明细信息
		returnString = warehouseCheckService.getWarehouseCheckDetails(warehouseCheckNumber);
		return "getWarehouseCheckDetails";
	}
	
	/**
	 * 修改盘点单信息
	 * @return 修改结果
	 */
	public String updateWarehouseCheck()
	{
		WarehouseCheckService warehouseCheckService = new WarehouseCheckService();
		JSONObject jsonObject=JSONObject.fromObject(goods);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 修改盘点单信息
		boolean result = warehouseCheckService.updateWarehouseCheck(warehouseCheckNumber, goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getUpdateResult";
	}
}
