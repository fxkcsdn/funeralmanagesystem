package com.FuneralManage.Action;

import com.FuneralManage.Service.RemainsCarryService;
import com.opensymphony.xwork2.ActionSupport;

public class getCarryNumberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getCarryNumber()
	{
		RemainsCarryService remainsCarryDao = new RemainsCarryService();
		returnString = remainsCarryDao.getCarryNumber();
		return SUCCESS;
	}

}
