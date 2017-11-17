package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FuneralManage.Service.QueryCremationService;
import com.FuneralManage.Service.QueryPageService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryCremationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String returnString;
	private String pageNum;
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
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
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	QueryCremationService QueryCremationDao=new QueryCremationService();
	QueryPageService qpd =new 	QueryPageService();
	public String queryCremation() throws SQLException{
			                				
		 JSONArray returnString1 = QueryCremationDao.queryCremationDao(startTime,endTime, pageNum);
		 
		 String result = qpd.queryPageDao(startTime, endTime)+"";
		
 		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 		 returnString1.put(jsonObj);   
 		 returnString = returnString1.toString();
 		 
 		 
 				 
		 return "queryCremation";
	}
}
