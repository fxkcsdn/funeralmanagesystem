package com.FuneralManage.Action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.FuneralManage.Service.SellService;
import com.opensymphony.xwork2.ActionSupport;

public class QuerySellAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String staffName;// 业务人员
	private String startTime;// 起始时间
	private String endTime;// 终止时间
	private String warehouseName;// 销售仓库
	private String pageNum;// 当前页数
	private String pageSize;// 每页记录数
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getStaffName() {
		return staffName;
	}
	
	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
	
	public String getWarehouseName() {
		return warehouseName;
	}
	
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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
	 * 获取销售单信息
	 * @return 销售单信息
	 */
	public String getSells()
	{
		SellService sellService = new SellService();
		// 获取销售单信息
		List<Map<String, String>> sells = sellService.getSells(staffName, startTime, endTime, warehouseName, Integer.parseInt(pageNum)
				, Integer.parseInt(pageSize));
		returnString = JSONArray.fromObject(sells).toString();
		return "getSells";
	}
	
	/**
	 * 获取分页数
	 * @return 分页数
	 */
	public String getPageCount()
	{
		SellService sellService = new SellService();
		// 获取分页数
		returnString = sellService.getPageCount(staffName, startTime, endTime, warehouseName, Integer.parseInt(pageSize));
		return "getPageCount";
	}
}
