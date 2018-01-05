package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QueryCremationInfoService extends BaseService{
		
	private JSONArray returnString;

	public JSONArray getReturnString() {
		return returnString;
	}

	public void setReturnString(JSONArray returnString) {
		this.returnString = returnString;
	}
	public JSONArray queryCremationInfoDao(String startTime,String endTime,String pageNum) throws SQLException{
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
						
			String startTime1=startTime+" 00:00:00";
			String endTime1=endTime+" 23:59:59";
						
			String sql="select deadName,deadId,address,deadSex,deadAge,deadTime,deadReason,inTime,invoiceNo,memberMobile,remainsNumber,remainsOrderNumber,subsidyMoney from remainsin where inTime between ? and ? ORDER BY DATE_FORMAT (inTime,'%Y-%m-%d') ASC,remainsNumber ASC,remainsOrderNumber ASC LIMIT ?,10";
			ResultSet rs=null;
			PreparedStatement ps=null;
			try{
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, startTime1);
				ps.setString(2, endTime1);
				ps.setInt(3, (Integer.parseInt(pageNum)-1)*10);
			
				rs=ps.executeQuery();
				JSONArray array = new JSONArray();
	             ResultSetMetaData metaData = rs.getMetaData();  
	             int columnCount = metaData.getColumnCount();
	             while (rs.next()) {  
	                 JSONObject jsonObj = new JSONObject();  
	                  
	                 // 遍历每一列  
	                 for (int i = 1; i <= columnCount; i++) {  
	                     String columnName =metaData.getColumnLabel(i);  
	                     String value = rs.getString(columnName);  
	                     try {
							jsonObj.put(columnName, value);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
	                 }   
	                 array.put(jsonObj);   
	             }  
	            returnString= array;
	            	            	            
			}
			 catch(SQLException e){
				e.printStackTrace();
			//	returnString="数据库操作失败！";
							
		}finally{
			rs.close();
			ps.close();
			conn.close();
		}
			}
		return returnString;
	}
}

			
	
		


