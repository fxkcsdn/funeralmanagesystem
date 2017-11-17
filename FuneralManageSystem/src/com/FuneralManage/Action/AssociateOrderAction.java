package com.FuneralManage.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.AssociateOrderService;

public class AssociateOrderAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
//	private DeadInfo deadInfo=new DeadInfo();
	public String deadId;
	public String returnString;
//	public DeadInfo getOrderInfo()
//	{
//		return deadInfo;
//	}
//	public void setDeadInfo(DeadInfo orderInfo)
//	{
//		this.deadInfo=deadInfo;
//	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	AssociateOrderService associateOrderDao=new AssociateOrderService();
	
	public String searchOrderInfo()
	{
		returnString=associateOrderDao.findOrderedInfo(deadId);
		System.out.println(returnString);
		return "associateOrderedInfo";
	}
}
