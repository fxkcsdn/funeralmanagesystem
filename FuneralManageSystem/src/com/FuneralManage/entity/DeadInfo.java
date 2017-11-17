package com.FuneralManage.entity;

import java.util.Date;

public class DeadInfo {			//遗体信息，封装遗体登记时的信息
	
	public String deadId;		//逝者身份证号码
	public String deadName;		//逝者姓名
	public String deadSex;		//逝者性别
	public int deadNumber;	//遗体编号，对应数据库表中remainsNumber
	public int deadAge;		//逝者年龄
	public String deadProveUnit;//死亡证明出具单位，对应数据库表中proofUnit
	public String memberMobile;//家属联系方式
	public String deadAddress;	//死者家庭住址，对应数据库表中address
	public String inTime;		//遗体进馆时间
	public String deadKind;		//死亡类型，对应数据库表中deadType
	public String deadReason;	//死亡原因
	public String deadTime;		//死亡时间
	public String area;			//死者所属区域
	public String operatorRelation;	//死者与经办人关系
	public String deadResidence;	//户口所在地
	public String pathogeny;		//具体病因
	public String ashesDisposition;	//骨灰去向
	public String dealerId;
	public String dealerName;
	public String dealerAddress;
	public String directorName;
	public String deadExtraInfo;//逝者补充信息，对应数据库表中memo
	public String getOperatorRelation() {
		return operatorRelation;
	}
	public void setOperatorRelation(String operatorRelation) {
		this.operatorRelation = operatorRelation;
	}
	public String getDeadResidence() {
		return deadResidence;
	}
	public void setDeadResidence(String deadResidence) {
		this.deadResidence = deadResidence;
	}
	public String getPathogeny() {
		return pathogeny;
	}
	public void setPathogeny(String pathogeny) {
		this.pathogeny = pathogeny;
	}
	public String getAshesDisposition() {
		return ashesDisposition;
	}
	public void setAshesDisposition(String ashesDisposition) {
		this.ashesDisposition = ashesDisposition;
	}
	public String getDeadId() {
		return deadId;
	}
	public String getDeadName() {
		return deadName;
	}
	public void setDeadName(String deadName) {
		this.deadName = deadName;
	}
	public String getDeadSex() {
		return deadSex;
	}
	public void setDeadSex(String deadSex) {
		this.deadSex = deadSex;
	}
	
	public int getDeadNumber() {
		return deadNumber;
	}
	public void setDeadNumber(int deadNumber) {
		this.deadNumber = deadNumber;
	}
	public int getDeadAge() {
		return deadAge;
	}
	public void setDeadAge(int deadAge) {
		this.deadAge = deadAge;
	}
	public String getDeadProveUnit() {
		return deadProveUnit;
	}
	public void setDeadProveUnit(String deadProveUnit) {
		this.deadProveUnit = deadProveUnit;
	}
	public String getMemberMobile() {
		return memberMobile;
	}
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	public String getDeadAddress() {
		return deadAddress;
	}
	public void setDeadAddress(String deadAddress) {
		this.deadAddress = deadAddress;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getDeadKind() {
		return deadKind;
	}
	public void setDeadKind(String deadKind) {
		this.deadKind = deadKind;
	}
	public String getDeadReason() {
		return deadReason;
	}
	public void setDeadReason(String deadReason) {
		this.deadReason = deadReason;
	}
	public String getDeadExtraInfo() {
		return deadExtraInfo;
	}
	public String getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setDeadExtraInfo(String deadExtraInfo) {
		this.deadExtraInfo = deadExtraInfo;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDealerAddress() {
		return dealerAddress;
	}
	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
}
