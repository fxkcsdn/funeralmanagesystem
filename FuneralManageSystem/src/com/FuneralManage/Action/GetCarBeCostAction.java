package com.FuneralManage.Action;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.GetCarBeCostService;
public class GetCarBeCostAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	public String returnString;
//	public String getReturnString() {
//		return returnString;
//	}
//	public void setReturnString(String returnString) {
//		this.returnString = returnString;
//	}
	public String findCarBeCost() throws SQLException
	{
		GetCarBeCostService getCarBeCostDao = new GetCarBeCostService();
		returnString=getCarBeCostDao.searchBeCost("一般");
		return "getCarBeCost";
	}
}
