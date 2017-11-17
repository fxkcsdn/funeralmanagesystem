package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAgentService extends BaseService
{
	public String findAllAgent() //查找所有一条龙信息
	{
		String returnString="";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql="select * from cooperateagent";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while(rs.next())
				{
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("agentName", rs.getString("agentName"));
					jsonObject.put("agentMobile", rs.getString("agentMobile"));
					jsonArray.add(jsonObject);
				}
				returnString=jsonArray.toString();
				rs.close();
				ps.close();
				conn.close();
				return returnString;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
}
