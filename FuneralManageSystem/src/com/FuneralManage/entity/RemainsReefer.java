package com.FuneralManage.entity;

public class RemainsReefer           //遗体冷藏信息
{
	private String reeferNumber;      //冷藏编号
	private String carryNumber;       //冷藏接运编号
	private String deadId;            //逝者身份证号
	private String contactMobile;     //经办人手机号码
	private String contactName;       //经办人姓名
	private String deadSource;        //尸体来源
	private String startTime;         //冷藏开始时间
	private String endTime;           //冷藏结束时间
	private String reeferNo;          //冰柜号
	private String reeferBeCost;      //冷藏应收费用金额
	private String reeferRealCost;    //冷藏实收费用金额
	private String staffName;         //馆内业务人员姓名
	private String belongings;        //死者物品
	private String memo;              //备注
	
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
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
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
	public String getDeadSource() {
		return deadSource;
	}
	public void setDeadSource(String deadSource) {
		this.deadSource = deadSource;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getReeferNo() {
		return reeferNo;
	}
	public void setReeferNo(String reeferNo) {
		this.reeferNo = reeferNo;
	}
	public String getReeferBeCost() {
		return reeferBeCost;
	}
	public void setReeferBeCost(String reeferBeCost) {
		this.reeferBeCost = reeferBeCost;
	}
	public String getReeferRealCost() {
		return reeferRealCost;
	}
	public void setReeferRealCost(String reeferRealCost) {
		this.reeferRealCost = reeferRealCost;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getBelongings() {
		return belongings;
	}
	public void setBelongings(String belongings) {
		this.belongings = belongings;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
