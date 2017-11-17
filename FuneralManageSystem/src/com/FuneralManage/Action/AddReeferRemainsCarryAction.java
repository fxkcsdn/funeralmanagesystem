package com.FuneralManage.Action;

import com.FuneralManage.Service.CarService;
import com.FuneralManage.Service.ReeferRemainsCarryService;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddReeferRemainsCarryAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String carryNumber;// 接运编号
	private String carryTime;// 接运时间
	private String contactName;// 联系人姓名
	private String contactMobile;// 联系人电话
	private String address;// 接运地址
	private String carNumber;// 车牌号
	private String bInternalCar;// 是否蹦车辆
	private String carBeCost;// 预收费用
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getCarryNumber() {
		return carryNumber;
	}

	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
	}

	public String getCarryTime() {
		return carryTime;
	}

	public void setCarryTime(String carryTime) {
		this.carryTime = carryTime;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getBInternalCar() {
		return bInternalCar;
	}

	public void setBInternalCar(String bInternalCar) {
		this.bInternalCar = bInternalCar;
	}

	public String getCarBeCost() {
		return carBeCost;
	}

	public void setCarBeCost(String carBeCost) {
		this.carBeCost = carBeCost;
	}

	/**
	 * 获取车牌号
	 * @return 车牌号JSON字符串
	 */
	public String getCarNumbers()
	{
		CarService carService = new CarService();
		// 获取车牌号、司机姓名和司机电话数据
		returnString = carService.getCarInfo();
		return "getCarNumbers";
	}
	
	/**
	 * 获取最新接运编号
	 * @return 接运编号，JSON格式字符串
	 */
	public String getReeferRemainsCarryNumber()
	{
		// 获取接运编号
		returnString = "{carryNumber:\"" + NumberUtil.createReeferCarryNumber(carryTime) + "\"}";
		return "getCarryNumber";
	}
	
	/**
	 * 保存接运信息
	 * @return 保存结果，true为保存成功，false为保存失败
	 */
	public String addReeferRemainsCarry()
	{
		ReeferRemainsCarryService reeferRemainsCarryService = new ReeferRemainsCarryService();
		// 添加接运信息
		boolean result = reeferRemainsCarryService.addReeferRemainsCarry(carryNumber, contactName, contactMobile, carryTime, address
				, carNumber, bInternalCar, Integer.parseInt(carBeCost));
		if (result == true) returnString = "true";
		else returnString = "false";
		return "getSaveResult";
	}
}
