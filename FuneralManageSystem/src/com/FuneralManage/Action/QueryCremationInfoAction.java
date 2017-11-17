package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FuneralManage.Service.QueryCremationInfoService;
import com.FuneralManage.Service.QueryPageService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryCremationInfoAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String startTime;
	private String endTime;
	private String returnString;
	private String pageNum;
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
	
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageNum() {
		return pageNum;
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

	QueryCremationInfoService QueryCremationInfoDao=new QueryCremationInfoService();
	QueryPageService qpd =new 	QueryPageService();
	public String queryCremationInfo() throws SQLException{
                 				
		 JSONArray returnString1 = QueryCremationInfoDao.queryCremationInfoDao(startTime,endTime, pageNum);
		 
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
 				 
		 return "queryCremationInfo";
	}
}
