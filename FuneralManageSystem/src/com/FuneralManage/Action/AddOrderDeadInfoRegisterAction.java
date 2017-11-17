package com.FuneralManage.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.AddOrderDeadInfoRegisterService;
import com.FuneralManage.entity.DeadInfo;
import com.opensymphony.xwork2.ActionSupport;

public class AddOrderDeadInfoRegisterAction extends ActionSupport
{
private static final long serialVersionUID = 1L;
	
	private DeadInfo deadInfo=new DeadInfo();

	public DeadInfo getDeadInfo() {
		return deadInfo;
	}

	public void setDeadInfo(DeadInfo deadInfo) {
		this.deadInfo = deadInfo;
	}
	public String returnString;
	AddOrderDeadInfoRegisterService addOrderDeadInfoRegisterDao = new AddOrderDeadInfoRegisterService();
	public String registDeadInfo(){
		returnString=addOrderDeadInfoRegisterDao.orderDeadInfoRegister(deadInfo);
		return "addOrderRegistDeadInfo";
	}
}