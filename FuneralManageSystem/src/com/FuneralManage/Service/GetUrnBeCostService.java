package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUrnBeCostService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String searchUrnBeCost(String urnName){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select urnBeCost from urn where urnName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, urnName);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					int cost=rs.getInt("urnBeCost");
					returnString="{\"urnBeCost\":\"" + cost + "\"}";
				}
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
}
