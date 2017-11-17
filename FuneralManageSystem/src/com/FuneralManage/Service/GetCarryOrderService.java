package com.FuneralManage.Service;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
public class GetCarryOrderService extends BaseService
{
	private String returnString;
	
	public String findOrder(String timeString) throws SQLException, JSONException
	{
		String result = "";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn !=null)
		{
			String sql = "select * from remainscarry where startTime like ? and (deadID IS NULL OR deadID = '') ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=null;
			try 
			{
				ps.setString(1, timeString+"%");
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while(rs.next())
				{
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("carryNumber", rs.getString("carryNumber"));
					jsonObject.put("carryTime", rs.getString("startTime"));
					jsonObject.put("carryAddress", rs.getString("address"));
					jsonObject.put("contactName", rs.getString("contactName"));
					jsonObject.put("carNumber", rs.getString("carNumber"));
					jsonArray.add(jsonObject);
				}
				
				returnString=jsonArray.toString();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
			rs.close();
			ps.close();
			conn.close();
		}	
		//System.out.println(returnString);
		//System.out.println(jsonArray);
		return returnString;
	}
}
