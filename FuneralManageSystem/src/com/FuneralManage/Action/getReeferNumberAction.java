package com.FuneralManage.Action;

import com.FuneralManage.Service.RemainsReeferService;
import com.opensymphony.xwork2.ActionSupport;

public class getReeferNumberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getReeferNumber()	
	{
		RemainsReeferService remainsReeferDao = new RemainsReeferService();
		returnString = remainsReeferDao.getReeferNumber();
		return SUCCESS;
	}
}
