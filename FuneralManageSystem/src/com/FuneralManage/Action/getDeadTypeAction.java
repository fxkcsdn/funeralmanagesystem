package com.FuneralManage.Action;

import com.FuneralManage.Service.DeadTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class getDeadTypeAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getDeadType()
	{
		DeadTypeService deadTypeDao = new DeadTypeService();
		returnString = deadTypeDao.getDeadType();		
		return SUCCESS;
	}

}
