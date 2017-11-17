package com.FuneralManage.Service;


import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class ReadLeaveRoomService extends BaseService
{
	private String returnString;
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String readLeaveRoomInfo()
	{
		String resultString="";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql="select typeNo,itemNo,price from cremationServiceItem where typeNo='02'";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				System.out.println(rs.toString());
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				if(rs.next())
				{
					jsonObject.put("typeNo", rs.getString("typeNo"));
					jsonObject.put("itemNo", rs.getString("itemNo"));
					jsonObject.put("price", rs.getString("price"));
					jsonArray.add(jsonObject);
					returnString=jsonArray.toString();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
			
		}
		System.out.println(returnString);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
	}
}
