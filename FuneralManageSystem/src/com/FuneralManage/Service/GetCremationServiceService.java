package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCremationServiceService extends BaseService{
	
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
	
	public String getService(String deadId)throws SQLException, JSONException{
//		System.out.println("查询服务");
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select deadName,itemBeCost,itemRealCost,CremationTypeNo ,CremationItemNo,status,remainsin.`inTime` from remainsin,deadserviceitem,deadchosenurn WHERE remainsIn.`deadID`=? AND deadserviceitem.`deadID`=remainsIn.`deadID`  ";
			ResultSet rs=null;
			PreparedStatement ps=null;
			try{
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
//				ps.setString(2, deadId);
//				ps.setString(3, deadId);
				rs=ps.executeQuery();
				String itemName=null;
				JSONArray array = new JSONArray();
	            while(rs.next())
				{
	            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					
					String CremationTypeNo=rs.getString("CremationTypeNo");
					String CremationItemNo=rs.getString("CremationItemNo");
					
					String itemBeCost=rs.getString("itemBeCost");
					String itemRealCost=rs.getString("itemRealCost");
//					String urnBeCost=rs.getString("urnBeCost");
//					String urnRealCost=rs.getString("urnRealCost");
					String deadName=rs.getString("deadName");
					
//					System.out.println("itemBeCost"+itemBeCost);
//					System.out.println("itemRealCost"+itemRealCost);

					Date date=new Date();
					date =rs.getDate("inTime");
					String inTime=formatter.format(date);				
					
					
					String status =rs.getString("status");
											
					itemName =getServiceName(CremationTypeNo,CremationItemNo);
//					System.out.println(itemName);
						
				 						    			            	
		            JSONObject jsonObj = new JSONObject();
		            jsonObj.put("CremationTypeNo", CremationTypeNo);
		            jsonObj.put("deadId", deadId);
		            jsonObj.put("deadName", deadName);
					jsonObj.put("status", status);
					jsonObj.put("itemName", itemName);
//					jsonObj.put("urnName", urnName);
					jsonObj.put("inTime", inTime);
					jsonObj.put("itemBeCost", itemBeCost);
					jsonObj.put("itemRealCost", itemRealCost);
//					jsonObj.put("urnBeCost", urnBeCost);
//					jsonObj.put("urnRealCost", urnRealCost);
					
					array.put(jsonObj);  
		                  		            		                   
		             }  
																	
	            this.returnString=array.toString();
	            
				}
			
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				
			
		}finally{
			rs.close();
			ps.close();
			conn.close();
						
		}
			
	}
		return returnString;
}
	

	
	public String getServiceName(String CremationTypeNo,String CremationItemNo)throws SQLException{
		Connection conn = DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select ItemName from cremationserviceitem where TypeNo=? and ItemNo=?";
			ResultSet rs=null;
			PreparedStatement ps =null;
			try{
				 ps = conn.prepareStatement(sql);
				ps.setString(1, CremationTypeNo);
				ps.setString(2, CremationItemNo);
				rs =ps.executeQuery();
				
				while(rs.next()){
										
					 String ItemName=rs.getString("ItemName");
					
					this.returnString1= ItemName ;
					
				}
								
			}catch(SQLException e){
				e.printStackTrace();
				returnString1="数据库操作失败！";
				
				
			}finally{
				rs.close();
				ps.close();
				conn.close();
				
			}
		}
		
		return returnString1;
	}




public String getFee(String deadId) throws SQLException{
	Connection conn=DBDao.openDateBase("dongtai");
	if(conn!=null){
		String sql = "select beRentCost,realRentCost,carBeCost,carRealCost,villaBeCost,villaRealCost,coffinBeCost,coffinRealCost from watchspirit,rentcoffin where watchspirit.`deadID`=rentcoffin.`deadID` AND watchspirit.`deadID`=?";
		ResultSet rs=null;
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(sql);
			ps.setString(1, deadId);
			rs=ps.executeQuery();
			rs.last();
			if(rs.getRow()==1)
			{
				int coffinRentBeCost=rs.getInt("beRentCost");
				int coffinRentRealcost=rs.getInt("realRentCost");
				int coffinCarBeCost=rs.getInt("carBeCost");
				int coffinCarRealCost=rs.getInt("carRealCost");
				int villaBeCost=rs.getInt("villaBeCost");
				int villaRealCost=rs.getInt("villaRealCost");
				int villaCoffinBeCost=rs.getInt("coffinBeCost");
				int villaCoffinrealCost=rs.getInt("coffinRealCost");
				returnString2=this.returnString="{\"coffinRentBeCost\":\"" + coffinRentBeCost + "\", \"coffinRentRealcost\":\"" + coffinRentRealcost + "\", \"coffinCarBeCost\":\"" + coffinCarBeCost + "\", \"coffinCarRealCost\":\"" + coffinCarRealCost+"\",\"villaBeCost\":\"" + villaBeCost + "\",\"villaRealCost\":\"" + villaRealCost + "\",\"villaCoffinBeCost\":\"" + villaCoffinBeCost + "\",\"villaCoffinrealCost\":\"" + villaCoffinrealCost + "\"}" ;
			}
			else{
				returnString2="没有相关费用";
			}
			rs.close();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			returnString2="数据库操作失败！";
			
		}finally{
			rs.close();
			ps.close();
			conn.close();
			
		}
	}
	return returnString2;
}
public String getRemainsCarryFee(String deadId){
	Connection conn=DBDao.openDateBase("dongtai");
	if(conn!=null){
		String sql = "select carBeCost,carRealCost from remainscarry where deadId=?";
		ResultSet rs=null;
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, deadId);
			rs=ps.executeQuery();
			rs.last();
			if(rs.getRow()==1)
			{
				
				int remainsCarBeCost=rs.getInt("carBeCost");
				int remainsCarRealCost=rs.getInt("carRealCost");
				
			
				returnString3=this.returnString="{\"remainsCarBeCost\":\"" + remainsCarBeCost + "\", \"remainsCarRealCost\":\"" + remainsCarRealCost + "\"}" ;
			}
			else{
				returnString3="没有相关费用";
			}
			rs.close();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			e.printStackTrace();
			returnString3="数据库操作失败！";
			return returnString;
		}
	}
	return returnString3;
}
public String getUrn(String deadId)throws SQLException, JSONException{
//	System.out.println("查询服务");
	Connection conn=DBDao.openDateBase("dongtai");
	if(conn!=null){
		String sql="select urnName,urnBeCost,urnRealCost from deadchosenurn where deadId=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			
			ps=conn.prepareStatement(sql);
			ps.setString(1, deadId);

			rs=ps.executeQuery();
			
			JSONArray array = new JSONArray();
            while(rs.next())
			{
//            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String urnName=rs.getString("urnName");
				String urnBeCost=rs.getString("urnBeCost");
				String urnRealCost=rs.getString("urnRealCost");

			 						    			            	
	            JSONObject jsonObj = new JSONObject();
	      
				jsonObj.put("urnName", urnName);
				jsonObj.put("urnBeCost", urnBeCost);
				jsonObj.put("urnRealCost", urnRealCost);
				
				array.put(jsonObj);  
	                  		            		                   
			}  
																
            this.returnString=array.toString();
            
			}
		
		catch(SQLException e){
			e.printStackTrace();
			returnString="数据库操作失败！";
			
		
	}finally{
		rs.close();
		ps.close();
		conn.close();
					
	}
		
}
	return returnString;
}
}