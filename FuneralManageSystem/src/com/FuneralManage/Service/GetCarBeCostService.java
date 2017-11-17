package com.FuneralManage.Service;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
public class GetCarBeCostService extends BaseService
{
	public String returnString;
	public String searchBeCost(String a)
	{
		int resultA = 0;
		String result = "";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn !=null)
		{
			String sql = "select price from hearsePrice where kind = ?";
			ResultSet rs = null;
			try 
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, a);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					resultA=rs.getInt("price"); 					 
				}
				returnString = "[{\"beCost\":\""+Integer.toString(resultA)+"\"}]";

				//System.out.println("returnString:" + returnString);
				
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
