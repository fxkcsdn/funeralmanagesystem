package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.json.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetFeeService extends BaseService{
	private String returnString;
    private String returnString1;
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String getReturnString1() {
		return returnString1;
	}
	public void setReturnString1(String returnString1) {
		this.returnString1 = returnString1;
	}
	
    public String getRentcoffinFee(String deadId){
    	Connection conn=DBDao.openDateBase("dongtai");
    	if(conn!=null){
    		String sql = "select beRentCost,realRentCost,carBeCost,carRealCost from rentcoffin where deadID=?";
    		ResultSet rs=null;
    		try{
    			PreparedStatement ps=conn.prepareStatement(sql);
    			ps.setString(1, deadId);
    			rs=ps.executeQuery();
    			rs.last();
    			if(rs.getRow()==1)
    			{
    				String coffinRentBeCost=rs.getString("beRentCost");
    				if(coffinRentBeCost==""){
    					coffinRentBeCost="0";
    				}
    				
    				String coffinRentRealcost=rs.getString("realRentCost");
    				
    				if(coffinRentRealcost==""){
    					coffinRentRealcost="0";
    				}
    				String coffinCarBeCost=rs.getString("carBeCost");
    				if(coffinCarBeCost==""){
    					coffinCarBeCost="0";
    				}
    				String coffinCarRealCost=rs.getString("carRealCost");
    				if(coffinCarRealCost==""){
    					coffinCarRealCost="0";
    				}

    				returnString="{\"coffinRentBeCost\":\"" + coffinRentBeCost + "\", \"coffinRentRealcost\":\"" + coffinRentRealcost + "\", \"coffinCarBeCost\":\"" + coffinCarBeCost + "\", \"coffinCarRealCost\":\"" + coffinCarRealCost+"\"}" ;
    			}
    			else{
    				returnString="{\"coffinRentBeCost\":\"" + "0" + "\", \"coffinRentRealcost\":\"" + "0" + "\", \"coffinCarBeCost\":\"" + "0" + "\", \"coffinCarRealCost\":\"" + "0"+"\"}";
    			}
    			rs.close();
    			ps.close();
    			conn.close();
    		}
    		catch(SQLException e){
    			e.printStackTrace();
    			returnString="数据库操作失败！";
    			
    		}
    	}
    	return returnString;
    }
    public String getRemainsCarryFee(String deadId) throws JSONException{
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
    				
    				String remainsCarBeCost=rs.getString("carBeCost");
    				if(remainsCarBeCost==""){
    					remainsCarBeCost="0";
    				}
    				String remainsCarRealCost=rs.getString("carRealCost");
    				if(remainsCarRealCost==""){
    					remainsCarRealCost="0";
    					
    				}

    				JSONObject object  = new JSONObject();
    				JSONArray array = new JSONArray();
    				object.put("remainsCarBeCost", remainsCarBeCost);
    				object.put("remainsCarRealCost", remainsCarRealCost);
    				array.add(object);
    				returnString1=array.toString();
    			
//    				returnString1="{\"remainsCarBeCost\":\"" + remainsCarBeCost + "\",\"remainsCarRealCost\":\"" + remainsCarRealCost + "\"}" ;
    			}
    			else{
    				JSONObject object  = new JSONObject();
    				JSONArray array = new JSONArray();
    				object.put("remainsCarBeCost", "0");
    				object.put("remainsCarRealCost", "0");
    				array.add(object);
    				returnString1=array.toString();
    			}
    			rs.close();
    			ps.close();
    			conn.close();
    		}
    		catch(SQLException e){
    			e.printStackTrace();
    			returnString1="数据库操作失败！";
    			
    		}
    	}
    	return returnString1;
    }
    public String getWatchspiritFee(String deadId){
    	Connection conn=DBDao.openDateBase("dongtai");
    	if(conn!=null){
    		String sql = "select villaBeCost,villaRealCost,coffinBeCost,coffinRealCost from watchspirit where deadID=?";
    		ResultSet rs=null;
    		try{
    			PreparedStatement ps=conn.prepareStatement(sql);
    			ps.setString(1, deadId);
    			rs=ps.executeQuery();
    			rs.last();
    			if(rs.getRow()==1)
    			{

    				String villaBeCost=rs.getString("villaBeCost");
    				if(villaBeCost==""){
    					villaBeCost="0";
    				}
    				String villaRealCost=rs.getString("villaRealCost");
    				if(villaRealCost==""){
    					villaRealCost="0";
    				}
    				String villaCoffinBeCost=rs.getString("coffinBeCost");
    				if(villaCoffinBeCost==""){
    					villaCoffinBeCost="0";
    				}
    				String villaCoffinrealCost=rs.getString("coffinRealCost");
    				if(villaCoffinrealCost==""){
    					villaCoffinrealCost="0";
    				}
    				this.returnString="{\"villaBeCost\":\"" + villaBeCost + "\",\"villaRealCost\":\"" + villaRealCost + "\",\"villaCoffinBeCost\":\"" + villaCoffinBeCost + "\",\"villaCoffinrealCost\":\"" + villaCoffinrealCost + "\"}" ;
    			}
    			else{
    				returnString="{\"villaBeCost\":\"" + "0" + "\",\"villaRealCost\":\"" + "0" + "\",\"villaCoffinBeCost\":\"" + "0" + "\",\"villaCoffinrealCost\":\"" + "0" + "\"}";
    			}
    			rs.close();
    			ps.close();
    			conn.close();
    		}
    		catch(SQLException e){
    			e.printStackTrace();
    			returnString="数据库操作失败！";
    			
    		}
    	}
    	return returnString;
    }
    
}
