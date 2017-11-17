package com.FuneralManage.Action;

import com.FuneralManage.Service.PurchaseMasterService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryPurchaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String supplierName;// 厂家
	private String startTime;// 起始时间
	private String endTime;// 终止时间
	private String state;// 状态
	private String pageNum;// 当前页数
	private String pageSize;// 每页记录数
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取采购单中的供货商信息
	 * @return 供货商信息
	 */
	public String getSuppliersInPurchases()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 获取采购单中的厂家信息
		returnString = purchaseMasterService.getSuppliersInPurchases();
		return "getSuppliers";
	}
	
	/**
	 * 根据查询条件获取采购单主信息
	 * @return 采购单主信息
	 */
	public String getPurchases()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 根据查询条件获取采购单主信息
		returnString = purchaseMasterService.getPurchases(supplierName, startTime, endTime, state, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return "getPurchases";
	}
	
	/**
	 * 查询分页数
	 * @return 分页数
	 */
	public String getPageCount()
	{
		PurchaseMasterService purchaseMasterService = new PurchaseMasterService();
		// 查询分页数
		returnString = purchaseMasterService.getPageCount(supplierName, startTime, endTime, state, Integer.parseInt(pageSize));
		return "getPageCount";
	}
}
