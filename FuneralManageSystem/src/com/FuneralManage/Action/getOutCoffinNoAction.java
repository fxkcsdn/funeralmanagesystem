package com.FuneralManage.Action;

import com.FuneralManage.Service.RentCoffinService;
import com.opensymphony.xwork2.ActionSupport;

public class getOutCoffinNoAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String coffinNumber;
	private String rentNumber;
	
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getCoffinNumber() {
		return coffinNumber;
	}


	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
	}


	public String getRentNumber() {
		return rentNumber;
	}


	public void setRentNumber(String rentNumber) {
		this.rentNumber = rentNumber;
	}


	public String getOutCoffinNo()
	{
		RentCoffinService rentCoffinDao = new RentCoffinService();
		returnString = rentCoffinDao.getRentCoffinInfoByCoffinNumberOrRentNumber(coffinNumber, rentNumber);	
		return SUCCESS;
	}	

}
