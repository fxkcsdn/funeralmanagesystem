package com.FuneralManage.Action;

import java.util.Date;

import com.FuneralManage.Service.RemainsCarryService;
import com.opensymphony.xwork2.ActionSupport;

public class updateRemainsCarryAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString;
	private String deadId;
	private String returnTime;
	private String carryNumber;
	
		



	public String getReturnString() {
		return returnString;
	}





	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}





	public String getDeadId() {
		return deadId;
	}





	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}





	public String getReturnTime() {
		return returnTime;
	}





	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}





	public String getCarryNumber() {
		return carryNumber;
	}





	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}





	public String updateRemainsCarry()
	{
		RemainsCarryService remainsCarryDao = new RemainsCarryService();
		returnString = remainsCarryDao.updateRemainsCarry(deadId, returnTime, carryNumber);		
		return SUCCESS;
	}

}
