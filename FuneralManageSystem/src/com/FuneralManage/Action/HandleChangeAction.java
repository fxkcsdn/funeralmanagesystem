package com.FuneralManage.Action;

import org.omg.CORBA.Request;

import com.FuneralManage.Service.HandleChangeService;
import com.opensymphony.xwork2.ActionSupport;

public class HandleChangeAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString = null;
	private String cn1;
	private String id1;
	private String cn2;
	private String id2;
	public String getCn1() {
		return cn1;
	}
	public void setCn1(String cn1) {
		this.cn1 = cn1;
	}
	public String getId1() {
		return id1;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public String getCn2() {
		return cn2;
	}
	public void setCn2(String cn2) {
		this.cn2 = cn2;
	}
	public String getId2() {
		return id2;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	HandleChangeService handleChangeDao = new HandleChangeService();
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getReturnString() {
		return returnString;
	}
	public String handleData()
	{
		int result1=0;
		int result2=0;
		if(cn2.length()<1)   //说明此时只上传了一组数据
		{
			result1=handleChangeDao.updateInfo(cn1,id1);
			returnString = result1+"";
		} 
		else {        //说明此时需要修改两条数据
			result2=handleChangeDao.updateInfo(cn1,id1)+handleChangeDao.updateInfo(cn2,id2);
			returnString = result2+"";
		}
		return "doTheChange";
	}
	
}
