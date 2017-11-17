package com.FuneralManage.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.GetDefaultServiceService;

public class GetDefaultServiceAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	public String deadId;
	public String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	GetDefaultServiceService getDefaultServiceDao = new GetDefaultServiceService();
	public String getDefaultInfo() //根据身份证获取orderedNo，然后再去取出预约的服务信息
	{
		returnString= getDefaultServiceDao.findDefaultService(deadId);
		System.out.println(returnString);
		return "defaultServiceInfo";
	}
}
