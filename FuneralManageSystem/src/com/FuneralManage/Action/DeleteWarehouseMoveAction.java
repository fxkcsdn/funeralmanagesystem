package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.FuneralManage.Service.WarehouseBalanceService;
import com.FuneralManage.Service.WarehouseMoveService;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteWarehouseMoveAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String warehouseMoveNumber;// 调拨单号
	private String warehouseMoves;// 调拨信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getWarehouseMoveNumber() {
		return warehouseMoveNumber;
	}
	
	public void setWarehouseMoveNumber(String warehouseMoveNumber) {
		this.warehouseMoveNumber = warehouseMoveNumber;
	}

	public String getWarehouseMoves() {
		return warehouseMoves;
	}

	public void setWarehouseMoves(String warehouseMoves) {
		this.warehouseMoves = warehouseMoves;
	}

	/**
	 * 获取调拨单主信息
	 * @return 调拨单主信息
	 */
	public String getWarehouseMove()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		// 获取调拨单主信息
		returnString = warehouseMoveService.getWarehouseMove(warehouseMoveNumber);
		return "getWarehouseMove";
	}
	
	/**
	 * 获取调拨单明细信息
	 * @return 调拨单明细信息
	 */
	public String getWarehouseMoveDetails()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		// 获取调拨单明细信息
		returnString = warehouseMoveService.getWarehouseMoveDetails(warehouseMoveNumber);
		return "getWarehouseMoveDetails";
	}

	/**
	 * 还原本地仓库
	 * @return 还原结果
	 */
	public String resetLocalNumber()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 还原库存信息
		boolean result = warehouseBalanceService.resetLocalNumber(goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getResetLocalResult";
	}
	
	/**
	 * 还原远程仓库
	 * @return 还原结果
	 */
	public String resetRemoteNumber()
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		// 允许跨域访问
		response.addHeader("Access-Control-Allow-Origin", "*");
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 还原远程仓库
		boolean result = warehouseBalanceService.resetRemoteNumber(goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getResetRemoteResult";
	}
	
	/**
	 * 删除调拨单
	 * @return 删除结果
	 */
	public String deleteWarehouseMove()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		// 删除调拨单
		boolean result = warehouseMoveService.deleteWarehouseMove(warehouseMoveNumber);
		if (result) returnString = "true";
		else returnString = "false";
		return "getDeleteResult";
	}
	
	/**
	 * 还原本地仓库
	 * @return 还原结果
	 */
	public String resetLocalNumberSec()
	{
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject jsonObject=JSONObject.fromObject(warehouseMoves);
		// 获取商品信息
		List<Map<String, String>> goodsList = jsonObject.getJSONArray("data");
		// 远程还原失败后，再次还原本地
		boolean result = warehouseBalanceService.resetLocalNumberSec(goodsList);
		if (result) returnString = "true";
		else returnString = "false";
		return "getResetLocalSecResult";
	}
}
