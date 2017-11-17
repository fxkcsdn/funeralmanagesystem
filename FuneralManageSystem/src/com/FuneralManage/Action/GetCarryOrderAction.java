package com.FuneralManage.Action;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Context;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.commons.lang.math.IntRange;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.FuneralManage.Service.GetCarryOrderService;

public class GetCarryOrderAction extends ActionSupport 
{
	private static final long serialVersionUID = 1L;
	public String returnString;
	public String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	HttpServletRequest request;
	HttpServletResponse response;
	GetCarryOrderService getCarryOrderDao = new GetCarryOrderService();
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	public String findCarryOrder() throws SQLException, JSONException 
	{	
		returnString = getCarryOrderDao.findOrder(time);		
		return "searchCarryOrder";
	}
}

