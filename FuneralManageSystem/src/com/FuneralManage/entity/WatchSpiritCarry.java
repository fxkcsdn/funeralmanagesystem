package com.FuneralManage.entity;

import java.util.Date;


public class WatchSpiritCarry {
	private String carryNumber;
	private String contactName;
	private String contactMobile;
	private Date carryTime;
	private String address;
	private String carNumber;
	private Boolean bInternalCar;
	private Float carBeCost;
	
	public String getCarryNumber() {
		return carryNumber;
	}
	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
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
	public Date getCarryTime() {
		return carryTime;
	}
	public void setCarryTime(Date carryTime) {
		this.carryTime = carryTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public boolean getBInternalCar() {
		return bInternalCar;
	}
	public void setBInternalCar(boolean bInternalCar) {
		this.bInternalCar = bInternalCar;
	}
	public Float getCarBeCost() {
		return carBeCost;
	}
	public void setCarBeCost(Float carBeCost) {
		this.carBeCost = carBeCost;
	}

}
