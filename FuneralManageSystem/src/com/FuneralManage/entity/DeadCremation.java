package com.FuneralManage.entity;

public class DeadCremation {

	private String cremationName;
	private int cremationBeCost;
	private int cremationRealCost;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCremationName() {
		return cremationName;
	}
	public void setCremationName(String cremationName) {
		this.cremationName = cremationName;
	}
	public int getCremationBeCost() {
		return cremationBeCost;
	}
	public void setCremationBeCost(int cremationBeCost) {
		this.cremationBeCost = cremationBeCost;
	}
	public int getCremationRealCost() {
		return cremationRealCost;
	}
	public void setCremationRealCost(int cremationRealCost) {
		this.cremationRealCost = cremationRealCost;
	}
	
}
