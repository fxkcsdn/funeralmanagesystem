package com.FuneralManage.entity;

import java.util.Date;

public class RentCoffin     //租棺信息
{			
	private String rentNumber;           //租棺编号
	private String deadID;               //逝者身份证号
	private String contactMobile;        //联系人手机
	private String contactName;          //联系人姓名
	private Date   startTime;            //送棺时间
	private Date   returnTime;           //还棺时间
	private int    beRentCost;           //租棺应收费用金额
	private int    realRentCost;         //租棺实际收费金额
	private String coffinNumber;         //水晶棺编号
	private String address;              //送棺材地址
	private String carNumber;            //送棺车牌号
	private int    carBeCost;            //送棺用车应收费用金额
	private int    carRealCost;          //送棺用车实收费用金额
	
	public String getRentNumber() {
		return rentNumber;
	}
	public void setRentNumber(String rentNumber) {
		this.rentNumber = rentNumber;
	}
	public String getDeadID() {
		return deadID;
	}
	public void setDeadID(String deadID) {
		this.deadID = deadID;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public int getBeRentCost() {
		return beRentCost;
	}
	public void setBeRentCost(int beRentCost) {
		this.beRentCost = beRentCost;
	}
	public int getRealRentCost() {
		return realRentCost;
	}
	public void setRealRentCost(int realRentCost) {
		this.realRentCost = realRentCost;
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
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getCarBeCost() {
		return carBeCost;
	}
	public void setCarBeCost(int carBeCost) {
		this.carBeCost = carBeCost;
	}
	public int getCarRealCost() {
		return carRealCost;
	}
	public void setCarRealCost(int carRealCost) {
		this.carRealCost = carRealCost;
	}

}