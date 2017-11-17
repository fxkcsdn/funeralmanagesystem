package com.FuneralManage.Action;

import com.FuneralManage.Service.CarService;
import com.opensymphony.xwork2.ActionSupport;

public class getCarNumberAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getCarNumber()
	{
		CarService carDao = new CarService();
		returnString = carDao.getCarInfo();
		return SUCCESS;
	}

}
