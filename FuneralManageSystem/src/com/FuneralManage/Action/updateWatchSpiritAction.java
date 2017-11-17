package com.FuneralManage.Action;

import com.FuneralManage.Service.WatchSpiritService;
import com.opensymphony.xwork2.ActionSupport;

public class updateWatchSpiritAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String watchNumber;
	private String endTime;
	private String coffinRealCost;
	private String villaRealCost;
	private String villaNumber;
	private String coffinNumber;
	private String coffinBeCost;
	private String villaBeCost;
	
	public String getCoffinBeCost() {
		return coffinBeCost;
	}
	public void setCoffinBeCost(String coffinBeCost) {
		this.coffinBeCost = coffinBeCost;
	}
	public String getVillaBeCost() {
		return villaBeCost;
	}
	public void setVillaBeCost(String villaBeCost) {
		this.villaBeCost = villaBeCost;
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCoffinRealCost() {
		return coffinRealCost;
	}
	public void setCoffinRealCost(String coffinRealCost) {
		this.coffinRealCost = coffinRealCost;
	}
	public String getVillaRealCost() {
		return villaRealCost;
	}
	public void setVillaRealCost(String villaRealCost) {
		this.villaRealCost = villaRealCost;
	}
	
	public String updateWatchSpirit()
	{
		WatchSpiritService watchSpiritDao = new WatchSpiritService();
		returnString = watchSpiritDao.updateWatchSpiritByWatchNumber(watchNumber, endTime, coffinRealCost, villaRealCost, villaNumber, coffinNumber, coffinBeCost, villaBeCost);
		return SUCCESS;
	}

}
