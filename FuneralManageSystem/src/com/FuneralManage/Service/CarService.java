package com.FuneralManage.Service;

import java.sql.*;

public class CarService extends BaseService{
	private String returnString;
	
	public String getCarInfo()
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select * from car";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);		
				String result = "";
				rs=ps.executeQuery();				
				while(rs.next())
				{					 
					 result = result + "{carNumber:\""+rs.getString("carNumber")+"\",driverName:\""+rs.getString("driverName")+"\",driverMobile:\""+rs.getString("driverMobile")+"\"},";					 
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
