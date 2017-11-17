package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class QueryChosenGoodsService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString1) {
		this.returnString = returnString1;
	}


	public String QueryChosenGoods(String deadId) throws JSONException, SQLException{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn!=null){
				
				String sql="select goodsName,goodsBeCost,goodsRealCost from deadfuneralgoods where deadID=?";
				ResultSet rs=null;
				PreparedStatement ps=null;
				try{
					
					ps=conn.prepareStatement(sql);
					ps.setString(1, deadId);
					rs=ps.executeQuery();
					
					JSONArray array = new JSONArray();
		             
		          
					
//					System.out.println(rs.getString("goodsName"));
					while(rs.next()){
						JSONObject jsonObj = new JSONObject();
						String goodsName=rs.getString("goodsName");
				        String goodsBeCost=rs.getString("goodsBeCost");
				        String goodsRealCost=rs.getString("goodsRealCost");
				        jsonObj.put("goodsName", goodsName);
			            jsonObj.put("goodsBeCost", goodsBeCost);
			            jsonObj.put("goodsRealCost", goodsRealCost);
						
						   
		                 array.put(jsonObj); 
					}
					this.returnString=array.toString();
					
				}catch(SQLException e){
					e.printStackTrace();
//					returnString="数据库操作失败！";					
					
				}finally{
					rs.close();
					ps.close();
					conn.close();
				}
								
			}
				
		return returnString;
	}
}
	
	
//	public String QueryChosenGoods(String deadId){
//		Connection conn=DBDao.openDateBase("dongtai");
//		if(conn!=null){
//			String sql="select goodsName,goodsBeCost,goodsRealCost from deadfuneralgoods where deadId=?";
//			PreparedStatement ps=null;
//			ResultSet rs=null;
//			try{
//				ps=conn.prepareStatement(sql);
//				ps.setString(1, deadId);
//				rs=ps.executeQuery();
//				while(rs.next()){
//					String goodsName=rs.getString("goodsName");
//					String goodsBeCost=rs.getString("goodsBeCost");
//					String goodsRealCost=rs.getString("goodsRealCost");
//					this.returnString1="{\"goodsName\":\"" + goodsName + "\", \"goodsBeCost\":\"" + goodsBeCost + "\", \"goodsRealCost\":\"" + goodsRealCost + "\"}" ;
//
//					
//				}
//				
//			}catch(SQLException e){
//				e.printStackTrace();
//				returnString1="数据库操作失败！";	
//			}
//		}
//		return returnString1;
//	}


