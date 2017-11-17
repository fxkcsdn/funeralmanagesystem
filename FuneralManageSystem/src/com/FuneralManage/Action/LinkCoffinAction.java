package com.FuneralManage.Action;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.LinkCoffinService;

public class LinkCoffinAction extends ActionSupport implements ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	private LinkCoffinService linkCoffinDao = new LinkCoffinService();
	public String returnString;
	public void setServletResponse(HttpServletResponse arg0) 
	{
		this.response=arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) 
	{
		this.request=arg0;
	}
	public String connectCoffin() throws IOException, ParseException, SQLException
	{
		this.response.setContentType("text/json;charset=utf-8");
		this.response.setHeader("Access-Control-Allow-Origin","*");
		String deadID = request.getParameter("deadID");
		String returnTime = request.getParameter("returnTime");
		String beRentCost = request.getParameter("beRentCost");
		String rentNumber = request.getParameter("rentNumber");
		String coffinNumber = request.getParameter("coffinNumber");
		String a = linkCoffinDao.connectCoffinInfo(deadID,returnTime,beRentCost,rentNumber);
		String b = linkCoffinDao.changeCoffinState(coffinNumber);
		if((a.equals("success"))&&(b.equals("success")))
		{
			returnString = "还棺成功！";
		}
		else
		{
			returnString = "操作失败！";
		}
		return "linkRentCoffinInfo";
	}
	public String deleteCoffin() throws IOException, ParseException, SQLException{
		this.response.setContentType("text/json;charset=utf-8");
		this.response.setHeader("Access-Control-Allow-Origin","*");
		String rentNumber = request.getParameter("rentNumber");
		String coffinNumber = request.getParameter("coffinNumber");
		String a = linkCoffinDao.deleteCoffinInfo(coffinNumber,rentNumber);
		if((a.equals("success"))){
			returnString = "删除租棺信息成功！";
		}else if(a.equals("hasLinked")){
			returnString = "该租棺信息已经绑定逝者信息，不能删除！";
		}else{
			returnString = "删除租棺信息失败！";
		}
		return "linkRentCoffinInfo";
	}
}
