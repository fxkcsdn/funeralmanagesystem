package com.FuneralManage.entity;

public class DeadReason       //死亡原因
{
	private String reasonName;         //原因名称
	private String memo;               //备注
	
	public String getReasonName() {
		return reasonName;
	}
	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}   
}
