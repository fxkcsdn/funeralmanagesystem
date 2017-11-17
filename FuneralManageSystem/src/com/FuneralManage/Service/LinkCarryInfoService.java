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


public class LinkCarryInfoService extends BaseService
{
	public String updateCarryInfo(String deadId,String carryNumber) throws SQLException
	{
		String result="";
		Connection conn=DBDao.openDateBase("dongtai");
		int row = 0;
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		String sql="update remainscarry set deadID=?,returnTime=? where carryNumber=?";
		if(conn!=null)
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			try 
			{
				ps.setString(1,deadId);
				ps.setString(2,time);
				ps.setString(3,carryNumber);
				row=ps.executeUpdate();
				if(row>0)
				{
					System.out.println("成功更新数据！");
					result="success";
				}
				else
				{
					System.out.println("数据库操作失败!");
					result="failure";
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			ps.close();
			conn.close();
		}
		return result;
	}
}
