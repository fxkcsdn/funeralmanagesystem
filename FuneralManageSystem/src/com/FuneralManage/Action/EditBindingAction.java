package com.FuneralManage.Action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.FuneralManage.Service.EditBindingService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class EditBindingAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString = null;
	private String timeString;
	EditBindingService editBindingDao = new EditBindingService();
	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getBill()  {
		HttpServletRequest request=ServletActionContext.getRequest();
		timeString = request.getParameter("time");
		JSONArray jsonArray=new JSONArray();
		jsonArray = editBindingDao.getResult(timeString);
		returnString= jsonArray.toString();
		return "searchBill";
	}
}
