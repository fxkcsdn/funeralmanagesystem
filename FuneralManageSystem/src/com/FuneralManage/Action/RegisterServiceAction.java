package com.FuneralManage.Action;

import com.FuneralManage.Service.RegisterServiceService;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterServiceAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String setName;
	private String returnString;
	
	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	RegisterServiceService registerServiceDao=new RegisterServiceService();
	
	public String showDeadInfo(){
		returnString=registerServiceDao.showDeadInfoDao(deadId);
		return "showDeadInfo";
	}
	
	public String showOrderDeadInfo(){
		returnString=registerServiceDao.showOrderDeadInfoDao(deadId);
		return "showOrderDeadInfo";
	}
	
	public String showAllFuneralGoods(){
		returnString=registerServiceDao.showAllFuneralGoodsDao();
		
		return "showAllFuneralGoods";
	}
	
	public String showUrn(){
		returnString=registerServiceDao.showUrnDao();
		return "showUrn";
	}
	
	public String getLeaveRoom(){
		returnString=registerServiceDao.getLeaveRoomDao();
		return "getLeaveRoom";
	}
	
	public String getSetService(){
		returnString=registerServiceDao.getSetServiceDao();
		return "getSetService";
	}
	
	public String showSetServiceDetail(){
		returnString=registerServiceDao.showSetServiceDetailDao(setName);
		return "showSetServiceDetail";
	}
	
	public String showSetGoodsDetail(){
		returnString=registerServiceDao.showSetGoodsDetailDao(setName);
		return "showSetGoodsDetail";
	}
}
