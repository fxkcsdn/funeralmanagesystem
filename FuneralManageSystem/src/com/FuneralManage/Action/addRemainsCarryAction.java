package com.FuneralManage.Action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.FuneralManage.Service.RemainsCarryService;
import com.opensymphony.xwork2.ActionSupport;

public class addRemainsCarryAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String contactName;
	private String contactMobile;
	private String startTime;
	private String address;
	private String carryNumber;
	private String deadId;
	private String carryType;
	private String carNumber;
	private String carCost;
	private String carRealCost;
	private String bInternalCar;
	private String rentNumber;
	
	private String test;
		
	


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




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
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




	public String getCarryType() {
		return carryType;
	}




	public void setCarryType(String carryType) {
		this.carryType = carryType;
	}




	public String getCarNumber() {
		return carNumber;
	}




	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}




	public String getCarCost() {
		return carCost;
	}




	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}




	public String getCarRealCost() {
		return carRealCost;
	}


	public void setCarRealCost(String carRealCost) {
		this.carRealCost = carRealCost;
	}


	public String getBInternalCar() {
		return bInternalCar;
	}


	public void setBInternalCar(String bInternalCar) {
		this.bInternalCar = bInternalCar;
	}


	public String getRentNumber() {
		return rentNumber;
	}


	public void setRentNumber(String rentNumber) {
		this.rentNumber = rentNumber;
	}


	public String addRemainsCarry() throws UnsupportedEncodingException
	{	
		RemainsCarryService remainsCarryDao = new RemainsCarryService();
		returnString = remainsCarryDao.addRemainsCarry(contactName, contactMobile, startTime, address, carryNumber, deadId, carryType, carNumber, carCost, bInternalCar, rentNumber, carRealCost);		
		return SUCCESS;
	}
	
}
