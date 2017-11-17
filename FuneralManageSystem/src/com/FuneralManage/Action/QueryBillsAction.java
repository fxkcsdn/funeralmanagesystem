package com.FuneralManage.Action;

import com.FuneralManage.Service.QueryBillsService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class QueryBillsAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String queryDate; 
	private String returnString;
	QueryBillsService queryBillsDao = new QueryBillsService();
	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String execute()
	{
		JSONArray jsonArray = new JSONArray();
		jsonArray = queryBillsDao.execute(queryDate);
		returnString = jsonArray.toString();
		return "queryBills";
	}
}
