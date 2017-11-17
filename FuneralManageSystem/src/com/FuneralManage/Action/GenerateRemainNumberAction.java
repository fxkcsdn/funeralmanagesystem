package com.FuneralManage.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.GenerateRemainNumberService;

public class GenerateRemainNumberAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String returnString;
	
	
	public String getReturnString()
	{
		return returnString;
	}
	public void setReturnString(String returnString)
	{
		this.returnString = returnString;
	}
	public int generateNo;
	GenerateRemainNumberService generateRemainNumberDao=new GenerateRemainNumberService();
	public String makeRemainNumber()
	{
		returnString=generateRemainNumberDao.decideRemainNumber()+"";
		return "generateNo";
	}
}
