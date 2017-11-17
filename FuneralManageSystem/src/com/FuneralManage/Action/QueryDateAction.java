package com.FuneralManage.Action;

import net.sf.json.JSONArray;

import com.FuneralManage.Service.QueryDateService;

public class QueryDateAction {
	private String returnString=null;
	private String status = null;
	private String date = null;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	QueryDateService queryDateDao = new QueryDateService();
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String queryDate(){
		JSONArray resultArray= new JSONArray();
		resultArray = queryDateDao.queryData(date,status);
		returnString = resultArray.toString();
		if(date.length()<2)
		{
			return "queryDate";
		}
		else{
			return "querySuccessData";
		}
	}
}
