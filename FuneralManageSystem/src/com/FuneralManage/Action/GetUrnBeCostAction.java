package com.FuneralManage.Action;

import com.FuneralManage.Service.GetUrnBeCostService;
import com.opensymphony.xwork2.ActionSupport;

public class GetUrnBeCostAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String urnName;
	private String returnString;

	public String getUrnName() {
		return urnName;
	}

	public void setUrnName(String urnName) {
		this.urnName = urnName;
	}
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	GetUrnBeCostService getUrnBeCostDao=new GetUrnBeCostService();
	
	public String getUrnBeCost(){
		returnString=getUrnBeCostDao.searchUrnBeCost(urnName);
		return "getUrnBeCost";
	}
}
