package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;

import com.FuneralManage.Service.RemainsReeferService;
import com.opensymphony.xwork2.ActionSupport;

public class saveRemainsReeferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	private String reeferNumber;
	private String carryNumber;
	private String deadId;
	private String contactName;
	private String contactMobile;
	private String reeferNo;
	private String startTime;
	private String deadSource;
	private String staffName;
	private String belongings;
	private String memo;
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
	public String getCarryNumber() {
		return carryNumber;
	}
	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getReeferNo() {
		return reeferNo;
	}
	public void setReeferNo(String reeferNo) {
		this.reeferNo = reeferNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDeadSource() {
		return deadSource;
	}
	public void setDeadSource(String deadSource) {
		this.deadSource = deadSource;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
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
	
	public String saveRemainsReefer() throws UnsupportedEncodingException
	{
		RemainsReeferService remainsReeferDao = new RemainsReeferService();
		returnString = remainsReeferDao.saveRemainsReefer(reeferNumber, carryNumber, deadId, contactMobile, contactName, reeferNo, startTime, deadSource, staffName, belongings, memo);
		return SUCCESS;
	}

}
