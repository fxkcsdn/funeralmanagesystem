package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONException;

import com.FuneralManage.Service.UpdateServiceService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateServiceAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deadId;
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	private String returnString;
	UpdateServiceService updateService= new UpdateServiceService();
	public String UpdateService() {
		try {
			returnString=updateService.updateService(deadId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "updateService";
		
	}

}
