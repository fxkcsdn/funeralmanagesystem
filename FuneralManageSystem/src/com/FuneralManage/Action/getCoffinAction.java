package com.FuneralManage.Action;

import com.FuneralManage.Service.CoffinService;
import com.opensymphony.xwork2.ActionSupport;

public class getCoffinAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
    private String returnString;
    
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getCoffin()
	{
		CoffinService coffinDao = new CoffinService();
		returnString = coffinDao.getCoffinInfo();
		return SUCCESS;
	}
}
