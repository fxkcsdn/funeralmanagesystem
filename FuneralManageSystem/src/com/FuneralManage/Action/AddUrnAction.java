package com.FuneralManage.Action;

import com.FuneralManage.Service.AddUrnService;
import com.opensymphony.xwork2.ActionSupport;

public class AddUrnAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	
	private String urnName;
	
	private String urnBeCost;
	
	private String urnRealCost;
	
	private String returnString;
	
	public String getDeadId() {
		return deadId;
	}
	
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	public String getUrnName() {
		return urnName;
	}
	
	public void setUrnName(String urnName) {
		this.urnName = urnName;
	}
	
	public String getUrnBeCost() {
		return urnBeCost;
	}
	
	public void setUrnBeCost(String urnBeCost) {
		this.urnBeCost = urnBeCost;
	}
	
	public String getUrnRealCost() {
		return urnRealCost;
	}
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public void setUrnRealCost(String urnRealCost) {
		this.urnRealCost = urnRealCost;
	}
	
	AddUrnService addUrnDao=new AddUrnService();
	
	public String addUrn(){
		returnString=addUrnDao.insertDeadChosenUrn(deadId, urnName, urnBeCost, urnRealCost);
		return "addUrn";
	}
}
