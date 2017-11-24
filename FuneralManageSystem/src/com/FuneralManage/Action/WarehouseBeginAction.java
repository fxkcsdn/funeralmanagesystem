package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.GoodsService;
import com.FuneralManage.Service.WarehouseBalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class WarehouseBeginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String goodsType;// 商品种类
	private String goodsName;// 品名
	private String warehouseName;// 仓库名称
	private String goods;// 商品信息
	
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

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 获取所有商品种类信息
	 * @return 商品种类
	 */
	public String getGoodsTypes()
	{
		GoodsService goodsService = new GoodsService();
		// 获取所有商品种类信息
		returnString = goodsService.getGoodsTypes();
		return "getGoodsTypes";
	}

	/**
	 * 获取品名
	 * @return 品名
	 */
	public String getGoodsNames()
	{
		GoodsService goodsService = new GoodsService();
		// 根据商品种类获取相应的品名
		returnString = goodsService.getGoodsNames(goodsType);
		return "getGoodsNames";
	}
	
	/**
	 * 获取单位和销售价
	 * @return 单位和销售价
	 */
	public String getUnitAndPrice()
	{
		GoodsService goodsService = new GoodsService();
		// 根据品名、商品种类获取相应的单位
		returnString = goodsService.getUnitAndPrice(goodsType, goodsName);
		return "getUnitAndPrice";
	}
	
	/**
	 * 保存期初入库信息
	 * @return
	 */
	public String saveWarehouseBeginInfo()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject jsonObject=JSONObject.fromObject(goods);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 添加商品信息
		boolean result = warehouseBalanceService.addGoods(warehouseName, goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getSaveResult";
	}
}
