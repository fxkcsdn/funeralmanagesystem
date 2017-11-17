package com.FuneralManage.entity;

public class DeadType     //死亡类型
{       
	private String typeName;       //类型名称
	private String memo;           //备注
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
