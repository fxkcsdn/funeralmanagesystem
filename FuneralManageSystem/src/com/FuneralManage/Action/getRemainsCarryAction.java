package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;

import com.FuneralManage.Service.RemainsCarryService;
import com.opensymphony.xwork2.ActionSupport;

public class getRemainsCarryAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String carryNumber;
	private String coffinNumber;
	private String carryType;
	

	public String getCarryType() {
		return carryType;
	}


	public void setCarryType(String carryType) {
		this.carryType = carryType;
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


	public String getCarryNumber() {
		return carryNumber;
	}


	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}


	public String getRemainsCarry() throws UnsupportedEncodingException
	{			
		RemainsCarryService remainsCarryDao = new RemainsCarryService();
		returnString = remainsCarryDao.getRemainsCarryByCarryNumber(coffinNumber, carryNumber, carryType);
		return SUCCESS;
	}
}
