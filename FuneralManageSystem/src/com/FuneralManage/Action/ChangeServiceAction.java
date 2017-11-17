package com.FuneralManage.Action;

import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.ChangeServiceService;
import com.FuneralManage.entity.DeadCremation;
import com.FuneralManage.entity.DeadFuneralGoods;
import com.FuneralManage.entity.DeadLeaveRoom;
import com.FuneralManage.entity.DeadMakeBeauty;
import com.FuneralManage.entity.DeadUrn;
import com.opensymphony.xwork2.ActionSupport;

public class ChangeServiceAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deadId;
	private String urn;
	private String makeBeauty;
	private String leaveRoom;
	private String cremation;
	private String funeralGoods;
	private String returnString;
	
	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getMakeBeauty() {
		return makeBeauty;
	}

	public void setMakeBeauty(String makeBeauty) {
		this.makeBeauty = makeBeauty;
	}

	public String getLeaveRoom() {
		return leaveRoom;
	}

	public void setLeaveRoom(String leaveRoom) {
		this.leaveRoom = leaveRoom;
	}

	public String getCremation() {
		return cremation;
	}

	public void setCremation(String cremation) {
		this.cremation = cremation;
	}

	public String getFuneralGoods() {
		return funeralGoods;
	}

	public void setFuneralGoods(String funeralGoods) {
		this.funeralGoods = funeralGoods;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}


	ChangeServiceService ChangeServiceDao=new ChangeServiceService();
	
	public String changeService(){
		
		
		try {
			returnString=ChangeServiceDao.insertIntoService(deadId,makeBeauty)+"02";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			returnString=ChangeServiceDao.insertIntoService1(deadId,leaveRoom)+"03";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			returnString=ChangeServiceDao.insertIntoService2(deadId,cremation)+"04";
	
		} catch (Exception e) {
	// TODO: handle exception
		}
		
		try {
			returnString=ChangeServiceDao.insertIntoUrn(deadId,urn)+"01";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
		returnString=ChangeServiceDao.insertIntoGoods(deadId, funeralGoods);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		

		

		
		return "changeService";
		
	}
	

}
