package com.FuneralManage.entity;

import java.util.Date;


public class RemainsReefer           //遗体冷藏信息
{
	private String reeferNumber;      //冷藏编号
	private String carryNumber;       //冷藏接运编号
	private String deadID;            //逝者身份证号
	private String contactMobile;     //经办人手机号码
	private String contactName;       //经办人姓名
	private String sendRemainsUnit;   //送尸单位
	private Date arrivalTime;       //到馆时间
	private Date endTime;           //结束时间
	private String familyName;        //家属姓名
	private String reeferNo;          //冰柜号
	private String accidentAddress;   //事故地址
	private Float deposit;           //押金
	private String staffName;         //馆内业务人员姓名
	private String familyMobile;      //家属手机号
	private String memo;              //备注
	private Float carryRealCost;    //接运实收
	private Float sendRealCost;     //送运实收
	private Float reeferBeCost;     //冰棺应收
	private Float reeferRealCost;   //冰棺实收
	private Float serviceRealCost;  //冷藏服务实收
	private Float allBeCost;        //总应收
	private Float allRealCost;      //总实收
	
	
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
	public String getSendRemainsUnit() {
		return sendRemainsUnit;
	}
	public void setSendRemainsUnit(String sendRemainsUnit) {
		this.sendRemainsUnit = sendRemainsUnit;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getReeferNo() {
		return reeferNo;
	}
	public void setReeferNo(String reeferNo) {
		this.reeferNo = reeferNo;
	}
	public String getAccidentAddress() {
		return accidentAddress;
	}
	public void setAccidentAddress(String accidentAddress) {
		this.accidentAddress = accidentAddress;
	}
	public Float getDeposit() {
		return deposit;
	}
	public void setDeposit(Float deposit) {
		this.deposit = deposit;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getFamilyMobile() {
		return familyMobile;
	}
	public void setFamilyMobile(String familyMobile) {
		this.familyMobile = familyMobile;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Float getCarryRealCost() {
		return carryRealCost;
	}
	public void setCarryRealCost(Float carryRealCost) {
		this.carryRealCost = carryRealCost;
	}
	public Float getSendRealCost() {
		return sendRealCost;
	}
	public void setSendRealCost(Float sendRealCost) {
		this.sendRealCost = sendRealCost;
	}
	public Float getReeferBeCost() {
		return reeferBeCost;
	}
	public void setReeferBeCost(Float reeferBeCost) {
		this.reeferBeCost = reeferBeCost;
	}
	public Float getReeferRealCost() {
		return reeferRealCost;
	}
	public void setReeferRealCost(Float reeferRealCost) {
		this.reeferRealCost = reeferRealCost;
	}
	public Float getServiceRealCost() {
		return serviceRealCost;
	}
	public void setServiceRealCost(Float serviceRealCost) {
		this.serviceRealCost = serviceRealCost;
	}
	public Float getAllBeCost() {
		return allBeCost;
	}
	public void setAllBeCost(Float allBeCost) {
		this.allBeCost = allBeCost;
	}
	public Float getAllRealCost() {
		return allRealCost;
	}
	public void setAllRealCost(Float allRealCost) {
		this.allRealCost = allRealCost;
	}
	
}
