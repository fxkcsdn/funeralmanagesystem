package com.FuneralManage.Action;

import java.util.Date;

import com.FuneralManage.Service.RentCoffinService;
import com.opensymphony.xwork2.ActionSupport;

public class updateRentCoffinAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String deadId;
	private String returnTime;
	private String rentNumber;
	private String coffinNumber;
	private String beRentCost;
	private String realRentCost;
	
	
	public String getBeRentCost() {
		return beRentCost;
	}


	public void setBeRentCost(String beRentCost) {
		this.beRentCost = beRentCost;
	}


	public String getRealRentCost() {
		return realRentCost;
	}


	public void setRealRentCost(String realRentCost) {
		this.realRentCost = realRentCost;
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


	public String getDeadId() {
		return deadId;
	}


	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}


	public String getReturnTime() {
		return returnTime;
	}


	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	public String getRentNumber() {
		return rentNumber;
	}


	public void setRentNumber(String rentNumber) {
		this.rentNumber = rentNumber;
	}


	public String updateRentCoffin()
	{
		RentCoffinService rentCoffinDao = new RentCoffinService();
		returnString = rentCoffinDao.updateRentCoffin(deadId, returnTime, rentNumber, coffinNumber, beRentCost, realRentCost);		
		return SUCCESS;
	}

}
