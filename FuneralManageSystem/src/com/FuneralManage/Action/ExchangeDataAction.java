package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.FuneralManage.Service.ExchangeDataService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ExchangeDataAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	public String returnString=null;
	public String queryDate=null;
	public String deadID=null;
	public int i;                          //成功上传数
	public int j;                          //待上传数
	public boolean flag;                   //用于接收数据上传的返回值
	private ExchangeDataService exchangeDataDao = new ExchangeDataService();
	public String getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String exchangeData() throws SQLException
	{
		ArrayList<String>  deadIdList = exchangeDataDao.getDeadId(queryDate);
		JSONObject jsonObject = new JSONObject();	
		JSONObject innerObject = new JSONObject();
		JSONArray resultArray = new JSONArray();
		Iterator<String> iterator = deadIdList.iterator();
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time=df.format(new java.util.Date());
		Timestamp t = new Timestamp(new java.util.Date().getTime());
//		t = Timestamp.valueOf(time);
		/*遍历获取到的当天的所有的身份证号，去数据库查询所需要上传的所有数据*/
		while(iterator.hasNext()){
			deadID = iterator.next();
			System.out.println("正在上传:"+deadID);
			j++;
			jsonObject = exchangeDataDao.queryData(deadID);
//			jsonObject.put("INSERT_DATE", t);	
			flag=exchangeDataDao.linkAndUpload(jsonObject, t);
			if(flag){
				if(exchangeDataDao.changeUploadStatus(deadID))
				{
					innerObject.put("deadID", deadID);
					innerObject.put("status", "success");
					i++;
				}
			}
			else{
				innerObject.put("deadID", deadID);
				innerObject.put("status", "failure");
			}
			resultArray.add(innerObject);                     //将反馈封装到json数组中，返回给前台
		}		
		if(i==j){
			System.out.println("成功上传了："+i+"条记录");
		}
		System.out.println(queryDate+"成功i:"+i);
		System.out.println(queryDate+"总数j:"+j);
		returnString = resultArray.toString();
		return "dataExchange";
	}
}
