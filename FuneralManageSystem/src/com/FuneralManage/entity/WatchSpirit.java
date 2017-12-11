package com.FuneralManage.entity;

import java.util.Date;

public class WatchSpirit      //守灵信息
{
    private String watchNumber;           //守灵编号
    private String carryNumber;           //守灵接运编号
    private String deadID;                //逝者身份证号
    private String memberMobile;          //丧属手机号码
    private String memberName;            //丧属姓名
    private String villaName;             //守灵别墅号
    private String coffinNumber;          //水晶棺编号
    private Date startTime;             //开始时间
    private Date endTime;               //结束时间
    private Float villaBeCost;           //守灵使用别墅应收费用金额
    private Float villaRealCost;         //守灵使用别墅实收费用金额
    private Float coffinBeCost;          //守灵使用水晶棺应收费用金额
    private Float coffinRealCost;        //守灵使用水晶棺实收费用金额
    private Float carryRealCost;
    private Float serviceRealCost;
    private Float allBeCost;
    private Float allRealCost;
    
    
	public String getWatchNumber() {
		return watchNumber;
	}
	public void setWatchNumber(String watchNumber) {
		this.watchNumber = watchNumber;
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
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getVillaName() {
		return villaName;
	}
	public void setVillaName(String villaName) {
		this.villaName = villaName;
	}
	public String getCoffinNumber() {
		return coffinNumber;
	}
	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Float getVillaBeCost() {
		return villaBeCost;
	}
	public void setVillaBeCost(Float villaBeCost) {
		this.villaBeCost = villaBeCost;
	}
	public Float getVillaRealCost() {
		return villaRealCost;
	}
	public void setVillaRealCost(Float villaRealCost) {
		this.villaRealCost = villaRealCost;
	}
	public Float getCoffinBeCost() {
		return coffinBeCost;
	}
	public void setCoffinBeCost(Float coffinBeCost) {
		this.coffinBeCost = coffinBeCost;
	}
	public Float getCoffinRealCost() {
		return coffinRealCost;
	}
	public void setCoffinRealCost(Float coffinRealCost) {
		this.coffinRealCost = coffinRealCost;
	}
	public Float getCarryRealCost() {
		return carryRealCost;
	}
	public void setCarryRealCost(Float carryRealCost) {
		this.carryRealCost = carryRealCost;
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
