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

public class LinkCoffinService extends BaseService
{
	public String connectCoffinInfo(String deadID,String returnTime,String beRentCost,String rentNumber) throws SQLException 
	{
		String result="";
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		String sql = "update rentcoffin set deadID=?,returnTime=?,beRentCost=?,realRentCost=? where rentNumber=?";
		if(conn !=null)
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			try
			{
				ps.setString(1,deadID);
				ps.setString(2,returnTime);
				ps.setString(3,beRentCost);
				ps.setString(4,beRentCost);
				ps.setString(5,rentNumber);
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
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			ps.close();
			conn.close();
		}
		return result;
	}
	public String changeCoffinState(String coffinNumber) throws SQLException
	{
		String result="";
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		String sql = "update coffin set bAvailable=? where coffinNumber=?";
		if(conn!=null)
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			try
			{
				ps.setString(1,"1");
				ps.setString(2,coffinNumber);
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
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			ps.close();
			conn.close();
		}
		  return result;
	}
	
	/**
	 * 删除租棺信息
	 * @param coffinNumber
	 * @param rentNumber
	 * @return
	 * @throws SQLException
	 */
	public String deleteCoffinInfo(String coffinNumber,String rentNumber) throws SQLException
	{
		String result="";
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0,row2=0;
		String sql1 = "",sql2="";
		/*先查询该租棺信息是否已经绑定逝者信息*/
		boolean ifLink=queryRentState(coffinNumber,rentNumber);
		if(ifLink)
			return "hasLinked";
		//如果没有关联逝者信息，则删除租棺信息，并且更新水晶棺状态
		if (!(coffinNumber.equals("") || rentNumber.equals(""))){
			sql1 = "delete from rentcoffin where coffinNumber = ? and rentNumber = ?  ORDER BY startTime ASC  ";
		}else{
			sql1 = "delete from rentcoffin where coffinNumber = ? or rentNumber = ?  ORDER BY startTime ASC  ";
		}
		sql2="update coffin set bAvailable='1' where  coffinNumber=?";
		if(conn!=null){
			conn.setAutoCommit(false);
			PreparedStatement ps=conn.prepareStatement(sql1);
			PreparedStatement ps2=conn.prepareStatement(sql2);
			try{
				ps.setString(1,coffinNumber);
				ps.setString(2,rentNumber);
				row=ps.executeUpdate();
				
				ps2.setString(1,coffinNumber);
				row2=ps2.executeUpdate();
				
				if(row>0&&row2>0){
					System.out.println("成功删除租棺信息！");
					result="success";
				}else{
					System.out.println("数据库操作失败!");
					result="failure";
				}
				conn.commit();
			}catch(SQLException e){
				e.printStackTrace();
				conn.rollback();
			}
			ps.close();
			conn.close();
		}
		  return result;
	}
	public boolean queryRentState(String coffinNumber,String rentNumber) throws SQLException
	{
		String result="";
		Connection conn=DBDao.openDateBase("dongtai");
		ResultSet rs=null;
		int row=0;
		String sql = "";
		if (!(coffinNumber.equals("") || rentNumber.equals(""))){
			sql = "select deadId from rentcoffin where coffinNumber = ? and rentNumber = ?  ORDER BY startTime ASC  ";
		}else{
			sql = "select deadId from rentcoffin where coffinNumber = ? or rentNumber = ?  ORDER BY startTime ASC  ";
		}
		if(conn!=null){
			PreparedStatement ps=conn.prepareStatement(sql);
			try{
				ps.setString(1,coffinNumber);
				ps.setString(2,rentNumber);
				rs=ps.executeQuery();
				if(rs.next()){
					String deadId=rs.getString("deadId");
					if(deadId!=null&&deadId.length()>0)
						return true;
					else
						return false;
				}else{
					return false;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			ps.close();
			conn.close();
		}
		  return false;
	}
	
}
	
