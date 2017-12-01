package com.FuneralManage.entity;

import java.util.Date;

public class ReeferRemainsCarry {
	 private String  carryNumber;           //接运编号

	    private String  contactName;           //联系人姓名
	    private String  contactMobile;         //联系人手机
	    private Date    carryTime;             //接运时间

	    private String  address;			   //接运地址
	    private String  carNumber;  		   //接运车牌号
	    private boolean bInternalCar;		   //是否是本馆车辆
	    private int     carBeCost;             //接运用车应收费用金额
		public String getCarryNumber() {
			return carryNumber;
		}
		public void setCarryNumber(String carryNumber) {
			this.carryNumber = carryNumber;
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
		public Date getCarryTime() {
			return carryTime;
		}
		public void setCarryTime(Date carryTime) {
			this.carryTime = carryTime;
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
		public boolean isbInternalCar() {
			return bInternalCar;
		}
		public void setbInternalCar(boolean bInternalCar) {
			this.bInternalCar = bInternalCar;
		}
		public int getCarBeCost() {
			return carBeCost;
		}
		public void setCarBeCost(int carBeCost) {
			this.carBeCost = carBeCost;
		}


}
