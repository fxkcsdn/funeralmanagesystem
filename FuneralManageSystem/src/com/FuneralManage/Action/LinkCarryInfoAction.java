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

import net.sf.json.JSONException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.inject.Context;

import org.apache.commons.lang.math.IntRange;

import com.FuneralManage.Service.LinkCarryInfoService;

public class LinkCarryInfoAction 
{
	private static final long serialVersionUID = 1L;
	public String returnString;
	HttpServletRequest request;
	HttpServletResponse response;
	LinkCarryInfoService linkCarryInfoDao =new LinkCarryInfoService();
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
	}
	public String connectCarryInfo() throws IOException, JSONException, SQLException
	{
		HttpServletRequest request=ServletActionContext.getRequest(); //获取request对象
		String deadId = request.getParameter("deadId");
		String carryNumber = request.getParameter("carryNumber");
		String a = linkCarryInfoDao.updateCarryInfo(deadId,carryNumber);
		if(a.equals("success"))
		{
			returnString = "成功绑定接运信息！";
		}
		else 
			{
				returnString = "操作失败！";
			}
		
		return "connectDeadId";
	}
}
