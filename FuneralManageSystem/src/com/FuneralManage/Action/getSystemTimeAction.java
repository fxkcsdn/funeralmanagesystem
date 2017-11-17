package com.FuneralManage.Action;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.opensymphony.xwork2.ActionSupport;

public class getSystemTimeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;	
	

	public String getReturnString() {
		return returnString;
	}


	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	public String getSystemTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());		
		this.returnString = "{startTime:\""+date+"\"}";
		return SUCCESS;
	}

}
