package com.FuneralManage.Action;

import com.FuneralManage.Service.WatchSpiritService;
import com.opensymphony.xwork2.ActionSupport;

public class getWatchNumberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getWatchNumber()
	{
		WatchSpiritService watchSpiritDao = new WatchSpiritService();
		returnString = watchSpiritDao.createWatchNumber();
		return SUCCESS;
	}

}
