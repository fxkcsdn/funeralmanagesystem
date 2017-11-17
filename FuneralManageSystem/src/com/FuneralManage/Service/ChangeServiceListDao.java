package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChangeServiceListDao extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String changeListInfoDao(String typeName){
		Connection conn=DBDao.openDateBase("funeralinterment");
		if(conn!=null){
			String sql="select cremationserviceitem.itemName from cremationserviceitem,cremationservicetype where cremationserviceitem.typeNo=cremationservicetype.typeNo and cremationservicetype.typeName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, typeName);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("itemName", rs.getString("itemName"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
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
