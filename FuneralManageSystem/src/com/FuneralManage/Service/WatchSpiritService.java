package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatchSpiritService extends BaseService
{
	private String returnString;
	
	public String getWatchNumber()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "select * from watchSpirit where watchNumber like ?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, date+"%");
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 result = rs.getString("watchNumber");
					 
				}				
				if (result != "")
				{
					result = result.substring(6, result.length());
					result = date + (Integer.parseInt(result) + 1);
				}
				else
				{
					result = date + "1";
				}
				returnString = "{watchNumber:\"" + result + "\"}";
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
	
	public String saveWatchSpirit(String watchNumber, String carryNumber, String deadId, String memberName, String memberMobile, String villaNumber, String coffinNumber, String startTime) throws UnsupportedEncodingException
	{		
		Connection conn=DBDao.openDateBase("dongtai");
		memberName = new String(memberName.getBytes("ISO-8859-1"), "GB2312");
		int row=0;
		if(conn!=null)
		{
			String sql = "update coffin set bAvailable=? where coffinNumber=?";
			String sql1 = "update remainsCarry set deadID=? , returnTime = ? where carryNumber=?";
			String sql2 = "insert into watchSpirit(watchNumber, carryNumber, deadID, memberName, memberMobile, villaName, coffinNumber, startTime) values(?,?,?,?,?,?,?,?)";
			String sql3 = "update villa set bAvailable=? where villaNumber=?";
			try
			{
				conn.setAutoCommit(false);
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "0");
				ps.setString(2, coffinNumber);				
				row = ps.executeUpdate();
				
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
				ps1.setString(2, startTime);	
				ps1.setString(3, carryNumber);
				row = ps1.executeUpdate();	
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, watchNumber);
				ps2.setString(2, carryNumber);	
				ps2.setString(3, deadId);
				ps2.setString(4, memberName);
				ps2.setString(5, memberMobile);
				ps2.setString(6, villaNumber);
				ps2.setString(7, coffinNumber);				
				ps2.setString(8, startTime);
				row = ps2.executeUpdate();		
				
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, "0");
				ps3.setString(2, villaNumber);				
				row = ps3.executeUpdate();
				
				conn.commit();
				returnString="{result:\"yes\"}";
				
				ps.close();		
				conn.close();			
			}
			catch(SQLException e)
			{
				try {
					conn.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
				returnString="{result:\"no\"}";				
			}
		}
		else
		{
			returnString="{result:\"no\"}";
		}
		return returnString;
	}
	
	public String getWatchSpiritByWatchNumber(String watchNumber)
	{			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());
		
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{			
			String sql = "select * from watchSpirit where watchNumber=?";
			ResultSet rs=null;	
			
			try
			{							
				PreparedStatement ps=conn.prepareStatement(sql);							
				ps.setString(1, watchNumber);					
				
				String result = "";
				rs=ps.executeQuery();	
				if (rs.next())
				{
					rs.last();												
				
					if (rs.getString("endTime") != null)
					{						
						this.returnString = null;
						return returnString;
					}	
									
					
					result = "{deadId:\""+rs.getString("deadID")
								+"\",memberName:\""+rs.getString("memberName")
								+"\",memberMobile:\""+ rs.getString("memberMobile")								
								+"\",startTime:\"" + rs.getString("startTime").toString().substring(0,16) 
								+"\",endTime:\"" + date
								+"\",villaNumber:\""+rs.getString("villaName")
								+"\",coffinNumber:\""+ rs.getString("coffinNumber") + "\"}";																	 
						this.returnString = result;						
					rs.close();
					ps.close();
					conn.close();
				}				
			}
			catch(SQLException e)
			{				
				e.printStackTrace();
				this.returnString = null;
			}
		}		
		return returnString;
	}
	
	public String updateWatchSpiritByWatchNumber(String watchNumber, String endTime, String coffinRealCost, String villaRealCost, String villaNumber, String coffinNumber, String coffinBeCost, String villaBeCost)
	{
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		
		if(conn!=null)
		{
			String sql = "update watchSpirit set endTime=?, coffinRealCost=?, villaRealCost=?, coffinBeCost=?, villaBeCost=? where watchNumber=?";
			String sql2 = "update coffin set bAvailable=? where coffinNumber=?";
			String sql3 = "update villa set bAvailable=? where villaNumber=?";
			try
			{
				conn.setAutoCommit(false);
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, endTime);
				ps.setString(2, coffinRealCost);
				ps.setString(3, villaRealCost);
				ps.setString(4, coffinBeCost);
				ps.setString(5, villaBeCost);
				ps.setString(6, watchNumber);
				
				row = ps.executeUpdate();
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, "1");
				ps2.setString(2, coffinNumber);			
				row = ps2.executeUpdate();	
				
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, "1");
				ps3.setString(2, villaNumber);			
				row = ps3.executeUpdate();					
				
				conn.commit();
				returnString="{result:\"yes\"}";				
				
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				returnString="{result:\"no\"}";
				e.printStackTrace();				
			}
		}
		else
		{
			returnString="{result:\"no\"}";
		}		
		return returnString;
	}

}
