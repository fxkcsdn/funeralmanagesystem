package com.FuneralManage.Action;

import com.FuneralManage.Service.GetServiceBeCostService;
import com.opensymphony.xwork2.ActionSupport;

public class GetServiceBeCostAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String itemName;
	private String returnString;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	GetServiceBeCostService getServiceBeCost=new GetServiceBeCostService();
	
	public String getServiceBeCost(){
		this.returnString=getServiceBeCost.getItemPrice(itemName);
		return "getServiceBeCost";
	}
	
}
