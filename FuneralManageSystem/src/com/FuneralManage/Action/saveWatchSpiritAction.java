package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;

import com.FuneralManage.Service.WatchSpiritService;
import com.opensymphony.xwork2.ActionSupport;

public class saveWatchSpiritAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	private String watchNumber;
	private String carryNumber;
	private String deadId;
	private String memberName;
	private String memberMobile;
	private String villaNumber;
	private String coffinNumber;
	private String deadType;
	private String deadReason;
	private String deadTime;
	private String startTime;
	
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getWatchNumber() {
		return watchNumber;
	}


	public void setWatchNumber(String watchNumber) {
		this.watchNumber = watchNumber;
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


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberMobile() {
		return memberMobile;
	}


	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}


	public String getVillaNumber() {
		return villaNumber;
	}


	public void setVillaNumber(String villaNumber) {
		this.villaNumber = villaNumber;
	}


	public String getCoffinNumber() {
		return coffinNumber;
	}


	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
	}


	public String getDeadType() {
		return deadType;
	}


	public void setDeadType(String deadType) {
		this.deadType = deadType;
	}


	public String getDeadReason() {
		return deadReason;
	}


	public void setDeadReason(String deadReason) {
		this.deadReason = deadReason;
	}


	public String getDeadTime() {
		return deadTime;
	}


	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String saveWatchSpirit() throws UnsupportedEncodingException
	{
		WatchSpiritService watchSpiritDao = new WatchSpiritService();
		returnString = watchSpiritDao.saveWatchSpirit(watchNumber, carryNumber, deadId, memberName, memberMobile, villaNumber, coffinNumber, startTime);
		return SUCCESS;
	}

}
