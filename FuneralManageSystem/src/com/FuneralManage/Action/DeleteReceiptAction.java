package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.ReceiptService;
import com.FuneralManage.Service.TransactionManager;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String receiptNumber;// 收货单号
	
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
	 * 删除收货单信息
	 * @return 删除结果
	 */
	public String saveDeleteInfo()
	{
		ReceiptService receiptService = new ReceiptService();
		// 删除收货单
		boolean result = receiptService.deleteReceipt(receiptNumber);
		if (result) returnString = "true";
		else returnString = "false";
		return "getSaveResult";
	}
}
