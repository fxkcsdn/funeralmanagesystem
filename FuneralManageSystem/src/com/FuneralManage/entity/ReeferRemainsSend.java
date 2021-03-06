package com.FuneralManage.entity;

import java.util.Date;


public class ReeferRemainsSend {
	private String reeferNumber;
	private String carryNumber;
	private String contactName;
	private String contactMobile;
	private Date sendTime;
	private String address;
	private String carNumber;
	private boolean bInternalCar;
	private Float carBeCost;
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
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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
