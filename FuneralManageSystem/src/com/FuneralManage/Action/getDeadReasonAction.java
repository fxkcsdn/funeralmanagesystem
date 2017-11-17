package com.FuneralManage.Action;

import com.FuneralManage.Service.DeadReasonService;
import com.opensymphony.xwork2.ActionSupport;

public class getDeadReasonAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getDeadReason()
	{		
		DeadReasonService deadReasonDao = new DeadReasonService();
		returnString = deadReasonDao.getDeadReason();		
		return SUCCESS;
	}
}
