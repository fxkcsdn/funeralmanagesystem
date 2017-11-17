package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.FuneralManage.Service.RentCoffinService;
import com.opensymphony.xwork2.ActionSupport;

public class saveRentCoffinAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String contactName;
	private String contactMobile;
	private String startTime;
	private String coffinNumber;
	private String address;
	private String rentNumber;
	private String carCost;
	private String carNumber;
	private String bInternalCar;
	private String carRealCost;	
	
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getContactName() {
		return contactName;
	}


	public void setContactName(String contactName) {
		System.out.println("contactName11"+contactName);
		this.contactName = contactName;
	}


	public String getContactMobile() {
		return contactMobile;
	}


	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getCoffinNumber() {
		return coffinNumber;
	}


	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRentNumber() {
		return rentNumber;
	}


	public void setRentNumber(String rentNumber) {
		this.rentNumber = rentNumber;
	}


	public String getCarCost() {
		return carCost;
	}


	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}


	public String getCarNumber() {
		return carNumber;
	}


	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}


	public String getBInternalCar() {
		return bInternalCar;
	}


	public void setBInternalCar(String bInternalCar) {
		this.bInternalCar = bInternalCar;
	}


	public String getCarRealCost() {
		return carRealCost;
	}


	public void setCarRealCost(String carRealCost) {
		this.carRealCost = carRealCost;
	}


	public String saveRentCoffin() throws UnsupportedEncodingException 
	{					
		RentCoffinService rentCoffinDao = new RentCoffinService();
		System.out.println("contactName22"+contactName+"rentNumber="+rentNumber);
		returnString = rentCoffinDao.saveRentCoffin(contactName, contactMobile, startTime, coffinNumber, address, rentNumber, carCost, carNumber, bInternalCar, carRealCost);
		return SUCCESS;
	}

}
