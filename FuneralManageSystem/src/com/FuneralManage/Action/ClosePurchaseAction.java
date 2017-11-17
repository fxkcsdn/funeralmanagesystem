package com.FuneralManage.Action;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.opensymphony.xwork2.ActionSupport;

public class ClosePurchaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String purchaseNumber;// 采购编号
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}
	
	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}

	/**
	 * 获取采购单主信息
	 * @return 采购单主信息
	 */
	public String getPurchaseMaster()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 获取采购单主信息
		returnString = purchaseMasterService.getPurchaseMaster(purchaseNumber);
		return "getPurchaseMaster";
	}
	
	/**
	 * 获取采购单明细信息
	 * @return 采购单明细信息
	 */
	public String getPurchaseDetails()
	{
		PurchaseDetailService purchaseDetailService = new PurchaseDetailService();
		// 获取采购单明细信息
		returnString = purchaseDetailService.getPurchaseDetails(purchaseNumber);
		return "getPurchaseDetails";
	}
	
	/**
	 * 保存结案信息
	 * @return
	 */
	public String saveCloseInfo()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 保存结案信息
		boolean result = purchaseMasterService.closePurchase(purchaseNumber);
		if (result == true) returnString = "true";
		else returnString = "false";
		return "getSaveResult";
	}
}
