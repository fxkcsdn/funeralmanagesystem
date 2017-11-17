package com.FuneralManage.Service;

import java.sql.*;

public class CoffinService extends BaseService{
	private String returnString;
	
	public String getCoffinInfo()
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select * from coffin";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);		
				String result = "";
				rs=ps.executeQuery();				
				while(rs.next())
				{					 
					 result = result + "{coffinNumber:\""+rs.getString("coffinNumber")+"\",bAvailable:\""+rs.getString("bAvailable")+"\"},";					 
				}							
				result = result.substring(0, result.length()-1);							
				returnString = "[" + result + "]";				
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}		
		return returnString;
	}
}
