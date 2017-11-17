package com.FuneralManage.Action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FuneralManage.Service.UploadToRemoteServerService;
import com.opensymphony.xwork2.ActionSupport;

import org.json.JSONArray;
import org.json.JSONException;
import org.apache.struts2.ServletActionContext;

public class UploadToRemoteServerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String returnString;
	HttpServletRequest request;
	HttpServletResponse response;
	UploadToRemoteServerService uploadToRemoteServerDao = new UploadToRemoteServerService();
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public JSONArray stringToJsonArray(String jsonString)
	  {
	    JSONArray jsonArray = new JSONArray();
	    try
	    {
	      jsonArray = new JSONArray(jsonString);
	    }
	    catch (JSONException e)
	    {
	      e.printStackTrace();
	    }
	    return jsonArray;
	  }
	public String doExchange() throws JSONException, SQLException   //接收前台上传的待交互数据
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String str=request.getParameter("json");
		JSONArray jsonArray=stringToJsonArray(str);
		int  length=jsonArray.length();
		for(int i=0;i<length;i++){
//			fillValue(jsonArray.getJSONObject(i));
		}
		returnString = "拿到了！";
		return "uploadTab";
	}
//	public String fillValue(JSONObject jsonObject) throws JSONException, SQLException   //向远程服务器上传数据
//	{
//		String driverPhone=jsonObject.getString("司机手机");
//		String driverName=jsonObject.getString("司机姓名");
//		String carNumber=jsonObject.getString("车牌号");
//		String contactName=jsonObject.getString("联系人");
//		String address=jsonObject.getString("派车地点");
//		String estimatedTime=jsonObject.getString("预计时间");
//		String carBeCost=jsonObject.getString("应收费");
//		String beInternal=jsonObject.getString("本馆车");
//		return "aaa";
//		
//	}
}
