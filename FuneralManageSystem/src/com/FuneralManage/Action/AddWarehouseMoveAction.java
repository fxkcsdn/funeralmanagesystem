package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.WarehouseBalanceService;
import com.FuneralManage.Service.WarehouseMoveService;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddWarehouseMoveAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String goodsType;// 商品种类
	private String goodsName;// 品名
	private String warehouseName;// 调出仓库
	private String warehouseMoves;// 仓库调拨信息
	
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

	public String getWarehouseMoves() {
		return warehouseMoves;
	}

	public void setWarehouseMoves(String warehouseMoves) {
		this.warehouseMoves = warehouseMoves;
	}

	/**
	 * 获取商品种类信息
	 * @return 商品种类信息
	 */
	public String getGoodsTypesInWarehouse()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 获取商品种类信息
		returnString = warehouseBalanceService.getGoodsTypesInWarehouseForJson(warehouseName);
		return "getGoodsTypes";
	}
	
	/**
	 * 获取品名信息
	 * @return 品名信息
	 */
	public String getGoodsNamesInWarehouse()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 获取品名信息
		returnString = warehouseBalanceService.getGoodsNamesInWarehouseForJson(warehouseName, goodsType);
		return "getGoodsNames";
	}
	
	/**
	 * 获取单位和库存数量
	 * @return 单位和库存数量
	 */
	public String getUnitAndNum()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 获取单位和库存数量
		returnString = warehouseBalanceService.getUnitAndNum(warehouseName, goodsType, goodsName);
		return "getUnitAndNum";
	}
	
	/**
	 * 保存调拨信息
	 * @return 保存结果
	 */
	public String addWarehouseMove()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 创建最新的调拨单号
		String warehouseMoveNumber = NumberUtil.createWarehouseMoveNumber(goodsList.get(0).get("moveDate"));
		// 新增调拨信息
		boolean result = warehouseMoveService.addWarehouseMove(warehouseMoveNumber, goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getAddResult";
	}
	
	/**
	 * 远程修改仓库库存信息
	 * @return
	 */
	public String remoteUpdateWarehouseBalance()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 远程修改仓库信息
		boolean result = warehouseBalanceService.remoteUpdateWarehouseBalance(goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getRemoteUpdateResult";
	}
	
	/**
	 * 重置本地库存量
	 * @return 重置结果，true为重置成功，false为失败
	 */
	public String resetLocalWarehouseBalance()
	{
		WarehouseMoveService WarehouseMoveService = new WarehouseMoveService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 重置库存信息
		boolean result = WarehouseMoveService.resetLocalWarehouseBalance(goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getResetLocalResult";
	}
}
