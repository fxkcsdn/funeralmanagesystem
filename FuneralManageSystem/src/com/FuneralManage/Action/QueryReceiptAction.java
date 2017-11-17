package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.FuneralManage.Service.PurchaseMasterService;
import com.FuneralManage.Service.ReceiptService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryReceiptAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String purchaseNumber;// 采购编号
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
	
	public String getPurchaseNumber() {
		return purchaseNumber;
	}
	
	public void setPurchaseNumber(String purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
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
	 * 获取收货单信息
	 * @return 收货单信息
	 */
	public String getReceipts()
	{
		ReceiptService receiptService = new ReceiptService();
		// 根据查询条件获取收货单信息
		List<Map<String, String>> receipts = receiptService.getReceipts(purchaseNumber, startTime, endTime, state, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		// 将收货单信息转换为JSON格式数据
		returnString = JSONArray.fromObject(receipts).toString();
		return "getReceipts";
	}
	
	/**
	 * 查询分页数
	 * @return 分页数
	 */
	public String getPageCount()
	{
		ReceiptService receiptService = new ReceiptService();
		// 查询分页数
		returnString = receiptService.getPageCount(purchaseNumber, startTime, endTime, state, Integer.parseInt(pageSize));
		return "getPageCount";
	}
}
