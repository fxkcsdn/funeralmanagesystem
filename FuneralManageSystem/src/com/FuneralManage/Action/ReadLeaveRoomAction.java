package com.FuneralManage.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.Service.ReadLeaveRoomService;

public class ReadLeaveRoomAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	public String returnString;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	ReadLeaveRoomService readLeavdRoomDao = new ReadLeaveRoomService();
	public String readLeaveRoom()
	{
		returnString=readLeavdRoomDao.readLeaveRoomInfo();
		System.out.println(returnString);
		return "readLeaveRoomInfo";
	}
}
