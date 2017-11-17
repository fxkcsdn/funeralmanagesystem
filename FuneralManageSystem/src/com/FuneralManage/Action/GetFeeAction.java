package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONException;

import com.FuneralManage.Service.GetFeeService;
import com.opensymphony.xwork2.ActionSupport;

public class GetFeeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deadId;
	private String returnString;

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
	GetFeeService GetFeeDao = new GetFeeService();
	public String Money() throws SQLException, JSONException{
		
		String fee = GetFeeDao.getRemainsCarryFee(deadId);
		String fee1 = GetFeeDao.getRentcoffinFee(deadId);
		returnString =GetFeeDao.getWatchspiritFee(deadId);
		fee=fee.substring(0, fee.length()-2);
		fee1=fee1.substring(1, fee1.length()-1);
		returnString=returnString.substring(1, returnString.length());
						
		System.out.println(fee);
		System.out.println(fee1);		
		System.out.println(returnString);
		
		returnString=fee+","+fee1+","+returnString+"]";
		System.out.println(returnString);
		
	

	    	   	
		return "getFee1";
	}
	
}
