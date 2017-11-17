package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUrnService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String insertDeadChosenUrn(String deadId, String urnName, String urnBeCost, String urnRealCost){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql = "insert into deadChosenUrn(deadId,urnName,urnBeCost,urnRealCost)values(?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, urnName);
				ps.setString(3, urnBeCost);
				ps.setString(4, urnRealCost);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功选购了" + row + "个骨灰盒！";
				}
				else
				{
					returnString="选择骨灰盒失败，请查看是否重复选择!";
				}
				ps.close();
				conn.close();
				
			}catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
}
