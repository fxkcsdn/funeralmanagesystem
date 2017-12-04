package com.FuneralManage.entity;


public class ReeferServiceConsumeInfo {
	private String reeferNumber;
	private String type;
	private String name;
	private Integer number;
	private Float beCost;
	private String consumeTime;
	
	public String getConsumeTime() {
		return consumeTime;
	}
	public String getReeferNumber() {
		return reeferNumber;
	}
	public void setReeferNumber(String reeferNumber) {
		this.reeferNumber = reeferNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Float getBeCost() {
		return beCost;
	}
	public void setBeCost(Float beCost) {
		this.beCost = beCost;
	}


}
