package com.FuneralManage.Action;

import java.sql.SQLException;

import com.FuneralManage.Service.GetAgentService;
import com.FuneralManage.Service.GetCarBeCostService;
import com.opensymphony.xwork2.ActionSupport;

public class GetAgentAction  extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String returnString;
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String searchAgent() 
	{
		GetAgentService getAgentDao = new GetAgentService();
		returnString=getAgentDao.findAllAgent();
		//System.out.println(returnString);
		return "getAgent";
	}
}
