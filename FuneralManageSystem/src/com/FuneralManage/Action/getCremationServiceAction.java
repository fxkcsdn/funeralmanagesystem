package com.FuneralManage.Action;

import java.sql.SQLException;

import org.json.JSONException;

import com.FuneralManage.Service.GetCremationInfoService;
import com.FuneralManage.Service.GetCremationServiceService;
import com.opensymphony.xwork2.ActionSupport;

public class getCremationServiceAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deadId;
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	GetCremationServiceService GetCremationServiceDao = new GetCremationServiceService();
	public String getCremationService() throws SQLException, JSONException{
		
		
        String urn=GetCremationServiceDao.getUrn(deadId);
        urn=urn.substring(0, urn.length()-2);

        
	    returnString =GetCremationServiceDao.getService(deadId);
	    if(urn.length()>2){
	    	returnString=returnString.substring(2, returnString.length());
		    returnString=urn+","+returnString;
	    	
	    }
	    

	    

		
	
		return "getCremationService";
	}

}
