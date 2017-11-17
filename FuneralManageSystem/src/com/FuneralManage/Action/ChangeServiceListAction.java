package com.FuneralManage.Action;

import com.FuneralManage.Service.ChangeServiceListDao;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeServiceListAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String typeName;
	private String returnString;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	ChangeServiceListDao changeServiceListDao=new ChangeServiceListDao();
	
	public String changeListInfo(){
		returnString=changeServiceListDao.changeListInfoDao(typeName);
		return "changeListInfo";
	}
}
