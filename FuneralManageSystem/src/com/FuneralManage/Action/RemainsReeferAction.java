package com.FuneralManage.Action;


import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class RemainsReeferAction extends ActionSupport{
	private Date queryDate;
	private String returnString;// 返回的字符串
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	
	public String queryRemainsCarry(){
		returnString="{1:2}";
		return "queryRemainsCarry";
	}
}
