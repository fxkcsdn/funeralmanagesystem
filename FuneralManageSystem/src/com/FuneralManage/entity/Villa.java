package com.FuneralManage.entity;

public class Villa        //守灵别墅
{
    private String villaNumber;       //别墅号
    private String villaName;         //别墅名
    private String coffinNumber;      //别墅内固定存放的水晶棺编号
    private String bAvailable;        //可用状态
    private String memo;              //备注
    
    
	public String getVillaNumber() {
		return villaNumber;
	}
	public void setVillaNumber(String villaNumber) {
		this.villaNumber = villaNumber;
	}
	public String getVillaName() {
		return villaName;
	}
	public void setVillaName(String villaName) {
		this.villaName = villaName;
	}
	public String getCoffinNumber() {
		return coffinNumber;
	}
	public void setCoffinNumber(String coffinNumber) {
		this.coffinNumber = coffinNumber;
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
