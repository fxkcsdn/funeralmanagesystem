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

public class UploadTableService extends BaseService 
{
	public String getCarryNumber(String estimatedTime) throws SQLException //生成接运编号
	{
		String carryNumber=null;  //接运编号
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn !=null)
		{
			String sql = "select * from remainscarry where carryNumber like ?";
			ResultSet rs=null;
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, date+"%");
			rs=ps.executeQuery();
			int count = 0;
			while(rs.next())
			{					 
				 result = rs.getString("carryNumber");
				 ++count;
			}
			if (result != "")
			{
				//result = result.substring(6, result.length());
				result = date + (count + 1);
			}
			else
			{
				result = date + "1";
			}
			rs.close();
			ps.close();
			conn.close();
		}
		carryNumber=result;
		return carryNumber; 
	}
	public int insertData(String carNumber,String contactName,String address,String estimatedTime,String carBeCost,String carryNumber,String beInternal) throws SQLException   //将数据插入数据库表
	{
		Connection conn=DBDao.openDateBase("dongtai");
		String sql = "insert into remainscarry(carNumber,contactName,address,startTime,carBeCost,carRealCost,carryNumber,bInternalCar)values(?,?,?,?,?,?,?,?)";
		int row=0;
		int status =0;
		if(conn!=null)
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			if(beInternal.equals("1"))
			{
				status=1;
			}
			else
			{
				status=2;
			}
			try
			{
				ps.setString(1,carNumber);
				ps.setString(2,contactName);
				ps.setString(3,address);
				ps.setString(4,estimatedTime);
				ps.setString(5,carBeCost);
				ps.setString(6,carBeCost);
				ps.setString(7,carryNumber);
				ps.setInt(8,status);
				row=ps.executeUpdate();
				if(row>0)
				{
					System.out.println("成功插入了"+row+"行数据！");
				}
				else
				{
					System.out.println("提交数据失败!");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			ps.close();
			conn.close();
		}
		return row;
	}
}
