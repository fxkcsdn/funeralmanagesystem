package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONArray;

import com.FuneralManage.Service.ToExcelService;
import com.opensymphony.xwork2.ActionSupport;

public class ToExcelAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String returnString;
	private String result ;
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	ToExcelService ToExcelDao=new ToExcelService();
	
	public String toExcel() throws SQLException{
                 				
		 JSONArray returnString1 = ToExcelDao.toExcelDao(startTime, endTime);
		 	  
 		 returnString = returnString1.toString();
 		 
 				 
		 return "ToExcelInfo";
	}
}
