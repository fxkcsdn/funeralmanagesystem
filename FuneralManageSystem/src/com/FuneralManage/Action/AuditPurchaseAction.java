package com.FuneralManage.Action;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.opensymphony.xwork2.ActionSupport;

public class AuditPurchaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String purchaseNumber;// 采购编号
	private String audit;// 财务审批人
	private String type;// 类型，判断审批人
	private String auditResult;// 审核结果
	
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
	
	public String getAudit() {
		return audit;
	}

	public void setAudit(String audit) {
		this.audit = audit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
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
	 * 保存审核信息
	 * @return 返回审核结果，“true”代表审核成功，“false”代表审核失败
	 */
	public String saveAuditInfo()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 保存审核信息
		boolean result = purchaseMasterService.auditPurchase(purchaseNumber, audit, type, auditResult);
		if (result == true) returnString = "true";
		else returnString = "false";
		return "getSaveResult";
	}
}
