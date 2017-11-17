package com.FuneralManage.entity;

public class Reefer        //冰柜信息
{
	private String reeferNumber;          //冰柜编号
	private String bAvailable;            //可用状态
	private String memo;                         //备注
	public String getReeferNumber() {
		return reeferNumber;
	}
	public void setReeferNumber(String reeferNumber) {
		this.reeferNumber = reeferNumber;
	}
	public String getbAvailable() {
		return bAvailable;
	}
	public void setbAvailable(String bAvailable) {
		this.bAvailable = bAvailable;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
