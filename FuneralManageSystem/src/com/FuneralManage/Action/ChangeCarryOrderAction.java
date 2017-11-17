package com.FuneralManage.Action;

import com.FuneralManage.Service.ChangeCarryOrderService;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeCarryOrderAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String returnString = null;
	private String carryNum;
	private String carNum;
	ChangeCarryOrderService changeCarryOrderDao = new ChangeCarryOrderService();
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getCarryNum() {
		return carryNum;
	}
	public void setCarryNum(String carryNum) {
		this.carryNum = carryNum;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String execute()
	{
		
		returnString=changeCarryOrderDao.updateInfo(carryNum,carNum);
		return "linkedit";
	}
	/**
	 * 删除接运单
	 * @return
	 */
	public String deleteCarryOrder(){
		returnString=changeCarryOrderDao.deleteCarryOrder(carryNum);
		return "linkedit";
	}
}
