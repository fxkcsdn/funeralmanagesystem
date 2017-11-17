package com.FuneralManage.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.ExchangeDataService;

public class ExchangeSingleDataAction {
	public String deadid;
	public String returnString;
	public boolean flag;
	public String getDeadid() {
		return deadid;
	}

	public void setDeadid(String deadid) {
		this.deadid = deadid;
	}

	public String exchangeSingleData(){
		ExchangeDataService exchangeDataDao =new ExchangeDataService();
		JSONObject jsonObject = new JSONObject();
		jsonObject = exchangeDataDao.queryData(deadid);
		JSONObject innerObject = new JSONObject();
		JSONArray resultArray = new JSONArray();
		flag = exchangeDataDao.linkAndUpload(jsonObject);
		if(flag){
			innerObject.put("deadID", deadid);
			innerObject.put("status", "success");
		}
		else{
			innerObject.put("deadID", deadid);
			innerObject.put("status", "failure");
		}
		resultArray.add(innerObject);
		returnString = resultArray.toString();
		return "uploadSingleRecord";
	}
}
