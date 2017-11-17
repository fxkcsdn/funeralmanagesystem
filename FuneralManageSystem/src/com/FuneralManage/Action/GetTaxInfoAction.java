package com.FuneralManage.Action;

import com.FuneralManage.Service.GetTaxInfoService;
import com.opensymphony.xwork2.ActionSupport;

public class GetTaxInfoAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String returnString;
	
	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	GetTaxInfoService getTaxInfoDao=new GetTaxInfoService();
	
	public String getTaxInfo(){
		returnString=getTaxInfoDao.getTaxInfoDao(deadId);
		return "getTaxInfo";
	}
}
