package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.ReceiptService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Service.WarehouseBalanceService;
import com.opensymphony.xwork2.ActionSupport;

public class CancelAuditReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String receiptNumber;// 收货单号
	private String purchaseNumber;// 采购编号
	private String warehouseName;// 收货仓库
	private String details;// 收货明细信息
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getReceiptNumber() {
		return receiptNumber;
	}
	
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * 获取收货单主信息
	 * @return 收货单主信息
	 */
	public String getReceipt()
	{
		ReceiptService receiptService = new ReceiptService();
		// 获取收货单主信息
		List<Map<String, String>> receipt = receiptService.getReceipt(receiptNumber);
		returnString = JSONArray.fromObject(receipt).toString();
		return "getReceipt";
	}
	
	/**
	 * 获取收货单明细信息
	 * @return 收货单明细信息
	 */
	public String getReceiptDetails()
	{
		ReceiptService receiptService = new ReceiptService();
		// 获取收货单明细信息
		List<Map<String, String>> details = receiptService.getReceiptDetails(receiptNumber);
		returnString = JSONArray.fromObject(details).toString();
		return "getReceiptDetails";
	}
	
	/**
	 * 保存取消审核信息
	 * @return 保存取消审核信息的结果
	 */
	public String saveCancelAuditInfo()
	{
		ReceiptService receiptService = new ReceiptService();
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		TransactionManager transactionManager = new TransactionManager();
		WarehouseBalanceService warehouseBalanceService = new WarehouseBalanceService();
		JSONObject object = JSONObject.fromObject(details);
		// 收货明细信息
		List<Map<String, String>> list = object.getJSONArray("data");
		try {
			// 开启事务
			transactionManager.start();
			// 获取Connnection对象
			Connection conn = transactionManager.getConnection();
			// 取消收货单审核
			receiptService.cancelAuditReceipt(conn, receiptNumber);
			// 遍历收货单明细信息
			for (Map<String, String> map : list)
			{
				// 品名
				String goodsName = map.get("goodsName");
				// 本次收货数量
				String amountIn = map.get("amountIn");
				// 减少库存量
				warehouseBalanceService.reduceBalanceNumber(conn, warehouseName, goodsName, Integer.parseInt(amountIn));
			}
			// 取消对应的采购单结案标志
			purchaseMasterService.cancelClosePurchaseTran(conn, purchaseNumber);
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (Exception e) {
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
