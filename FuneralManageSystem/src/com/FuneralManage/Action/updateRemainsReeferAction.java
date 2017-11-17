package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;

import com.FuneralManage.Service.RemainsReeferService;
import com.opensymphony.xwork2.ActionSupport;

public class updateRemainsReeferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String returnString;
	private String reeferNumber;
	private String endTime;
	private String reeferBeCost;
	private String reeferRealCost;
	private String deadId;
	private String belongings;
	private String memo;
	private String reeferNo;
	private String carryNumber;
	
	public String getCarryNumber() {
		return carryNumber;
	}
	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getReeferNumber() {
		return reeferNumber;
	}
	public void setReeferNumber(String reeferNumber) {
		this.reeferNumber = reeferNumber;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getReeferBeCost() {
		return reeferBeCost;
	}
	public void setReeferBeCost(String reeferBeCost) {
		this.reeferBeCost = reeferBeCost;
	}
	public String getReeferRealCost() {
		return reeferRealCost;
	}
	public void setReeferRealCost(String reeferRealCost) {
		this.reeferRealCost = reeferRealCost;
	}
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	public String getBelongings() {
		return belongings;
	}
	public void setBelongings(String belongings) {
		this.belongings = belongings;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getReeferNo() {
		return reeferNo;
	}
	public void setReeferNo(String reeferNo) {
		this.reeferNo = reeferNo;
	}
	
	
	public String updateRemainsReefer() throws UnsupportedEncodingException
	{
		RemainsReeferService remainsReeferDao = new RemainsReeferService();
		returnString = remainsReeferDao.updateRemainsReefer(reeferNumber, endTime, reeferBeCost, reeferRealCost, deadId, belongings, memo, reeferNo, carryNumber);
		return SUCCESS;
	}

}
