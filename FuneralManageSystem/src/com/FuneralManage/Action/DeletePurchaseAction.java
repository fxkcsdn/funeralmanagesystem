package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;

import com.FuneralManage.Service.PurchaseDetailService;
import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.TransactionManager;
import com.opensymphony.xwork2.ActionSupport;

public class DeletePurchaseAction extends ActionSupport {
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
	 * 删除采购单信息
	 * @return 删除结果
	 */
	public String saveDeleteInfo()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		TransactionManager transactionManager = new TransactionManager();
		try {
			// 事务开始
			transactionManager.start();
			// 获取Connnection对象
			Connection conn = transactionManager.getConnection();
			// 删除采购单主信息和明细信息
			purchaseMasterService.deletePurchase(conn, purchaseNumber);
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
