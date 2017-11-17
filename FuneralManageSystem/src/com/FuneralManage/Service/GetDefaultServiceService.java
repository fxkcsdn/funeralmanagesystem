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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetDefaultServiceService extends BaseService
{
	private String returnString;
	private String orderNoresult; //用于存储根据deadId取出来的orderNumber
	public String getOrderNoresult()
	{
		return orderNoresult;
	}
	public void setOrderNoresult(String orderNoresult)
	{
		this.orderNoresult = orderNoresult;
	}
	public String findOrderNo(String deadId)   //用于主方法中调用，来获取身份证对应的预约编号
	{
		String resultString="";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql="select deadId,orderNumber from deadorderbasic where deadID=?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				System.out.println(rs.toString());
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				if(rs.next()){
				jsonObject.put("deadId", rs.getString("deadId"));
				jsonObject.put("orderNumber", rs.getString("orderNumber"));
				jsonArray.add(jsonObject);
				orderNoresult=rs.getString("orderNumber");
				returnString=jsonArray.toString();
			}
				else
				{
					System.out.println("数据库中无此记录！");
				}
		}
			catch(SQLException e){
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
	public String findDefaultService(String deadId) //action中调用的主方法
	{
		//orderNoresult="";
		this.findOrderNo(deadId);
		System.out.println("orderNoresult:   "+orderNoresult);
		Connection conn=DBDao.openDateBase("dongtai");
		System.out.println(returnString);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
	}
}
