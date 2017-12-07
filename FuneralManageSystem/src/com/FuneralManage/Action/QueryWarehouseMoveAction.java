package com.FuneralManage.Action;

import com.FuneralManage.Service.WarehouseMoveService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryWarehouseMoveAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String staffName;// 业务人员
	private String startTime;// 起始时间
	private String endTime;// 终止时间
	private String outWarehouse;// 调出仓库
	private String inWarehouse;// 调入仓库
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
	
	public String getOutWarehouse() {
		return outWarehouse;
	}
	
	public void setOutWarehouse(String outWarehouse) {
		this.outWarehouse = outWarehouse;
	}
	
	public String getInWarehouse() {
		return inWarehouse;
	}
	
	public void setInWarehouse(String inWarehouse) {
		this.inWarehouse = inWarehouse;
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
	 * 获取调拨单信息
	 * @return 调拨单信息
	 */
	public String getWarehouseMoves()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		// 获取调拨单信息
		returnString = warehouseMoveService.getWarehouseMoves(staffName, startTime, endTime,
				outWarehouse, inWarehouse, Integer.parseInt(pageNum), Integer.parseInt(pageSize));
		return "getWarehouseMoves";
	}
	
	/**
	 * 获取分页数
	 * @return 分页数
	 */
	public String getPageCount()
	{
		WarehouseMoveService warehouseMoveService = new WarehouseMoveService();
		// 获取分页数
		returnString = warehouseMoveService.getPageCount(staffName, startTime, endTime, outWarehouse, inWarehouse, Integer.parseInt(pageSize));
		return "getPageCount";
	}
}
