package com.FuneralManage.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDateService extends BaseService{
	public JSONArray queryData(String date,String status){
		JSONObject singleJsonObject = new JSONObject();
		JSONArray resultArray =new JSONArray();
		Connection conn = DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			if(date.length()<2)
			{
				String sql = "SELECT * FROM remainsin WHERE uploadStatus=? ORDER BY inTime";
				ResultSet rs = null;
				try {
					PreparedStatement pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, status);
					rs=pStatement.executeQuery();
					while(rs.next())
					{
						singleJsonObject.put("time", rs.getString("inTime"));
						singleJsonObject.put("id", rs.getString("deadID"));
						singleJsonObject.put("name", rs.getString("deadName"));
						resultArray.add(singleJsonObject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null)
					{
						try {
							rs.close();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						rs=null;  
					}
					if(conn!=null)
					{
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						conn=null;
					}
				}
			}
			else{
				String sql = "SELECT * FROM remainsin WHERE inTime like ? ORDER BY uploadStatus DESC";
				ResultSet rs = null;
				try {
					PreparedStatement pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, date+"%");
					rs=pStatement.executeQuery();
					while(rs.next())
					{
						singleJsonObject.put("time", rs.getString("inTime"));
						singleJsonObject.put("id", rs.getString("deadID"));
						singleJsonObject.put("name", rs.getString("deadName"));
						singleJsonObject.put("status", rs.getString("uploadStatus"));
						resultArray.add(singleJsonObject);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null)
					{
						try {
							rs.close();
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
						rs=null;  
					}
					if(conn!=null)
					{
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						conn=null;
					}
				}
			}
		}
		return resultArray;
	}
}
