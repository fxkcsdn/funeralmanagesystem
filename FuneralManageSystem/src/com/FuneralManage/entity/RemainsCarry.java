package com.FuneralManage.entity;

import java.util.Date;

public class RemainsCarry     //遗体接运信息
{			
    private String  carryNumber;           //接运编号
    private String  deadID;                //逝者身份证号
    private String  carryType;             //接运类型
    private String  contactName;           //联系人姓名
    private String  contactMobile;         //联系人手机
    private Date    startTime;             //接运时间
    private Date    returnTime;            //到馆时间
    private String  address;			   //接运地址
    private String  carNumber;  		   //接运车牌号
    private boolean bInternalCar;		   //是否是本馆车辆
    private int     carBeCost;             //接运用车应收费用金额
    private int     carRealCost;           //接运用车实收费用金额
    
	public String getCarryNumber() {
		return carryNumber;
	}
	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}
	public String getDeadID() {
		return deadID;
	}
	public void setDeadID(String deadID) {
		this.deadID = deadID;
	}
	public String getCarryType() {
		return carryType;
	}
	public void setCarryType(String carryType) {
		this.carryType = carryType;
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
	public boolean isbInternalCar() {
		return bInternalCar;
	}
	public void setbInternalCar(boolean bInternalCar) {
		this.bInternalCar = bInternalCar;
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