package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.inject.New;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FuneralManage.Dao.UpdateServiceDao;

public class UpdateServiceService extends BaseService{
	
	private String returnString;
    private String returnString1;
	private String returnString2;
	private String returnString3;
	public String getReturnString3() {
		return returnString3;
	}

	public void setReturnString3(String returnString3) {
		this.returnString3 = returnString3;
	}

	public String getReturnString2() {
		return returnString2;
	}

	public void setReturnString2(String returnString2) {
		this.returnString2 = returnString2;
	}

	public String getReturnString1() {
		return returnString1;
	}

	public void setReturnString1(String returnString1) {
		this.returnString1 = returnString1;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String updateService(String deadId)throws SQLException, JSONException{
		
		UpdateServiceDao updateServiceDao = new UpdateServiceDao(dataSource);
		
		String service = updateServiceDao.getService(deadId);
		
		String goods = updateServiceDao.getGoods(deadId);
		
		String remainsCarryFee = updateServiceDao.getRemainsCarryFee(deadId);
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("service", new JSONArray(service));
		object.put("goods", new JSONArray(goods));
		object.put("remainsCarryFee", new JSONArray(remainsCarryFee));		
		array.put(object);	
		
		return array.toString();
//		return service;
}
	


}