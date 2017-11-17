package com.FuneralManage.Action;

import com.FuneralManage.Service.VillaService;
import com.opensymphony.xwork2.ActionSupport;

public class getVillaNumberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getVillaNumber()
	{
		VillaService villaDao = new VillaService();
		returnString = villaDao.getVillaNumber();
		return SUCCESS;
	}
}
