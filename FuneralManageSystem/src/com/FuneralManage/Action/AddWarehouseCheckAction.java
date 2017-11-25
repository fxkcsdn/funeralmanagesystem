package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.WarehouseBalanceService;
import com.FuneralManage.Service.WarehouseCheckService;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddWarehouseCheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String warehouseName;// 盘点仓库
	private String goods;// 商品信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
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
	 * 获取该仓库里的商品信息
	 * @return 商品信息
	 */
	public String getGoodsInWarehouse()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		// 获取该仓库里的商品信息
		returnString = warehouseBalanceService.getGoodsInWarehouse(warehouseName);
		return "getGoodsInWarehouse";
	}
	
	/**
	 * 保存盘点信息
	 * @return 保存结果
	 */
	public String addWarehouseCheck()
	{
		WarehouseCheckService warehouseCheckService = new WarehouseCheckService();
		JSONObject jsonObject=JSONObject.fromObject(goods);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		String warehouseCheckNumber = NumberUtil.createWarehouseCheckNumber(goodsList.get(0).get("checkDate"));
		// 添加盘点信息
		boolean result = warehouseCheckService.addWarehouseCheck(warehouseCheckNumber, goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getAddResult";
	}
}
