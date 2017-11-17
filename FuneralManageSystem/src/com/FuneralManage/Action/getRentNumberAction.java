package com.FuneralManage.Action;

import com.FuneralManage.Service.RentCoffinService;
import com.opensymphony.xwork2.ActionSupport;

public class getRentNumberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String returnString;


	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}



	public String getRentNumber()
	{
		RentCoffinService rentCoffinDao = new RentCoffinService();
		returnString = rentCoffinDao.getRentCoffinInfoByRentNumber();		
		return SUCCESS;
	}

}
