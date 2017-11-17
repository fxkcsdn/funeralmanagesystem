package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;



public class GetTaxInfoService extends BaseService{
	
	private String returnString;
	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String getTaxInfoDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select deadName,address,deadSex,deadAge from remainsin where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					String deadName=rs.getString("deadName");
					String deadSex=rs.getString("deadSex");
					String deadAge=rs.getInt("deadAge")+"";
					String address=rs.getString("address");
					this.returnString="{\"deadName\":\"" + deadName + "\", \"deadSex\":\"" + deadSex + "\", \"deadAge\":\"" + deadAge + "\", \"address\":\"" + address+"\"}" ;
				}
				else
				{
					this.returnString="获取税票信息失败！";
				}
				rs.close();
				ps.close();
				conn.close();
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
