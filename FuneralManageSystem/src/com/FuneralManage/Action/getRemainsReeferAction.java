package com.FuneralManage.Action;

import com.FuneralManage.Service.RemainsReeferService;
import com.opensymphony.xwork2.ActionSupport;

public class getRemainsReeferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String returnString;
	private String reeferNumber;
	private String deadId;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getReeferNumber() {
		return reeferNumber;
	}
	public void setReeferNumber(String reeferNumber) {
		this.reeferNumber = reeferNumber;
	}
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	public String getRemainsReefer()
	{
		RemainsReeferService remainsReeferDao = new RemainsReeferService();
		returnString = remainsReeferDao.getRemainsReefer(reeferNumber, deadId);
		return SUCCESS;
	}

}
