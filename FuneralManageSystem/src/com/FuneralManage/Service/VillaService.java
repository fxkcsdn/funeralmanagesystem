package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VillaService extends BaseService
{
	private String returnString;
	
	public String getVillaNumber()
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select * from villa where bAvailable=?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);		
				String result = "";
				ps.setString(1, "1");
				rs=ps.executeQuery();				
				while(rs.next())
				{					 
					 result = result + "{villaNumber:\""+rs.getString("villaNumber")+"\"},";					 
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
