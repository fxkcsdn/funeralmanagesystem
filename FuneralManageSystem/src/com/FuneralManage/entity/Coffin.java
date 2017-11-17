package com.FuneralManage.entity;

public class Coffin     //水晶棺信息
{			
	private String coffinNumber;           //水晶棺编号
	private boolean bAvailable;            //可用状态
	private String memo;                   //备注
	
	public String getCoffinNumber() {
		return coffinNumber;
	}
	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
	}
	public boolean isbAvailable() {
		return bAvailable;
	}
	public void setbAvailable(boolean bAvailable) {
		this.bAvailable = bAvailable;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}