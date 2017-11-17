package com.FuneralManage.Action;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.inject.Context;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.commons.lang.math.IntRange;

import com.FuneralManage.Service.UploadTableService;

public class UploadTableAction 
{
	private static final long serialVersionUID = 1L;
	public String returnString;
	HttpServletRequest request;
	HttpServletResponse response;
	UploadTableService uploadTableDao = new UploadTableService();
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	public JSONArray stringToJsonArray(String jsonString)
	  {
	    JSONArray jsonArray = new JSONArray();
	    try
	    {
	      jsonArray = new JSONArray(jsonString);
	    }
	    catch (JSONException e)
	    {
	      e.printStackTrace();
	    }
	    return jsonArray;
	  }
      
	public String uploadData() throws IOException, JSONException, SQLException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("json");
		System.out.println(str);
		JSONArray jsonArray=stringToJsonArray(str);
		//System.out.println("jsonArray"+jsonArray);
		int  length=jsonArray.length();
		for(int i=0;i<length;i++){
			fillValue(jsonArray.getJSONObject(i));
		}
		returnString=length+"";
		return "uploadTab";
	}
	
	public String fillValue(JSONObject jsonObject) throws JSONException, SQLException
	{
		String driverPhone=jsonObject.getString("司机手机");
		String driverName=jsonObject.getString("司机姓名");
		String carNumber=jsonObject.getString("车牌号");
		String contactName=jsonObject.getString("联系人");
		String address=jsonObject.getString("派车地点");
		String estimatedTime=jsonObject.getString("预计时间");
		String carBeCost=jsonObject.getString("应收费");
		String beInternal=jsonObject.getString("本馆车");
		String carryNumber=uploadTableDao.getCarryNumber(estimatedTime);//根据时间获取接运编号
		int  insertResult = uploadTableDao.insertData(carNumber,contactName,address,estimatedTime,carBeCost,carryNumber,beInternal);
		return returnString;
		
	}
	
}
