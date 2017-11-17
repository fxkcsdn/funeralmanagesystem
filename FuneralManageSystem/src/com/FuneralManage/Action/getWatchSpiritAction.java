package com.FuneralManage.Action;

import com.FuneralManage.Service.WatchSpiritService;
import com.opensymphony.xwork2.ActionSupport;

public class getWatchSpiritAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String returnString;
	private String watchNumber;
	
	
	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getWatchNumber() {
		return watchNumber;
	}


	public void setWatchNumber(String watchNumber) {
		this.watchNumber = watchNumber;
	}


	public String getWatchSpirit()
	{
		WatchSpiritService watchSpiritDao = new WatchSpiritService();
		returnString = watchSpiritDao.getWatchSpiritByWatchNumber(watchNumber);		
		return SUCCESS;
	}

}
