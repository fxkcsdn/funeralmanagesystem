package com.FuneralManage.Action;

import com.FuneralManage.Service.ReeferService;
import com.opensymphony.xwork2.ActionSupport;

public class getReeferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getReefer()
	{
		ReeferService reeferDao = new ReeferService();
		returnString = reeferDao.getReefer();
		return SUCCESS;
	}
}
