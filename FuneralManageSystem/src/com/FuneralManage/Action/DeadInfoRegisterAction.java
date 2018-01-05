package com.FuneralManage.Action;

import java.sql.SQLException;

import com.FuneralManage.Service.DeadInfoRegisterService;
import com.FuneralManage.entity.DeadInfo;
import com.opensymphony.xwork2.ActionSupport;

public class DeadInfoRegisterAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String invoiceNo;
	private String subsidyNo;
	private String benefitTime;
	private String wrongDeadId;
	public String getWrongDeadId() {
		return wrongDeadId;
	}
	public void setWrongDeadId(String wrongDeadId) {
		this.wrongDeadId = wrongDeadId;
	}
	public String getLatestDeadId() {
		return latestDeadId;
	}
	public void setLatestDeadId(String latestDeadId) {
		this.latestDeadId = latestDeadId;
	}

	private String latestDeadId;
	public String getBenefitTime() {
		return benefitTime;
	}
	public void setBenefitTime(String benefitTime) {
		this.benefitTime = benefitTime;
	}

	private String subsidyMoney;
	
	private DeadInfo deadInfo=new DeadInfo();

	public DeadInfo getDeadInfo() {
		return deadInfo;
	}
	public void setDeadInfo(DeadInfo deadInfo) {
		this.deadInfo = deadInfo;
	}
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getSubsidyNo() {
		return subsidyNo;
	}
	public void setSubsidyNo(String subsidyNo) {
		this.subsidyNo = subsidyNo;
	}
	public String getSubsidyMoney() {
		return subsidyMoney;
	}
	public void setSubsidyMoney(String subsidyMoney) {
		this.subsidyMoney = subsidyMoney;
	}

	public String returnString;
	DeadInfoRegisterService deadInfoRegisterDao=new DeadInfoRegisterService();
	
	public String registDeadInfo(){
		deadInfo.deadNumber=deadInfoRegisterDao.getMaxRemainsNumber(deadInfo);
		returnString=deadInfoRegisterDao.deadInfoRegister(deadInfo);
		return "registDeadInfo";
	}
	
	public String registOrderDeadInfo(){
		deadInfo.deadNumber=deadInfoRegisterDao.getMaxOrderRemainsNumber(deadInfo);
		returnString=deadInfoRegisterDao.orderDeadInfoRegister(deadInfo);
		return "registOrderDeadInfo";
	}
	
	public String decideInvoiceAndSubsidy(){
		returnString=deadInfoRegisterDao.alterDeadInvoiceAndSubsidyInfo(deadInfo,invoiceNo,subsidyNo,subsidyMoney,benefitTime);
		return "decideInvoiceAndSubsidy";
	}
	public String updateDeadId() {
		try {
			returnString = deadInfoRegisterDao.updateDeadId(wrongDeadId, latestDeadId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "updateDeadId";
		
	}
}
