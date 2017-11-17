package com.FuneralManage.entity;

public class DeadLeaveRoom {

	private String leaveRoomName;
	private int leaveRoomBeCost;
	private int leaveRoomRealCost;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeaveRoomName() {
		return leaveRoomName;
	}
	public void setLeaveRoomName(String leaveRoomName) {
		this.leaveRoomName = leaveRoomName;
	}
	public int getLeaveRoomBeCost() {
		return leaveRoomBeCost;
	}
	public void setLeaveRoomBeCost(int leaveRoomBeCost) {
		this.leaveRoomBeCost = leaveRoomBeCost;
	}
	public int getLeaveRoomRealCost() {
		return leaveRoomRealCost;
	}
	public void setLeaveRoomRealCost(int leaveRoomRealCost) {
		this.leaveRoomRealCost = leaveRoomRealCost;
	}
}
