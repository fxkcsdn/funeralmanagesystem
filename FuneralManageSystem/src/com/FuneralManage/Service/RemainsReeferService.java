package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemainsReeferService extends BaseService{
	private String returnString;
	
	public String getReeferNumber()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "select * from remainsReefer where reeferNumber like ?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, date+"%");
				rs=ps.executeQuery();
				int count = 0;
				while(rs.next())
				{					 
					 result = rs.getString("reeferNumber");
					 ++count;					 
				}				
				if (result != "")
				{
					//result = result.substring(6, result.length());
					//result = date + (Integer.parseInt(result) + 1);
					result = date + (count + 1);
				}
				else
				{
					result = date + "1";
				}
				returnString = "{reeferNumber:\"" + result + "\"}";
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

	public String saveRemainsReefer(String reeferNumber, String carryNumber, String deadId, String contactMobile, String contactName, String reeferNo, String startTime, String deadSource, String staffName, String belongings, String memo) throws UnsupportedEncodingException
	{
		Connection conn=DBDao.openDateBase("dongtai");
		contactName = new String(contactName.getBytes("ISO-8859-1"), "GB2312");
		deadSource = new String(deadSource.getBytes("ISO-8859-1"), "GB2312");
		staffName = new String(staffName.getBytes("ISO-8859-1"), "GB2312");
		belongings = new String(belongings.getBytes("ISO-8859-1"), "GB2312");
		memo = new String(memo.getBytes("ISO-8859-1"), "GB2312");
		
		int row=0;
		if(conn!=null)
		{
			String sql = "update reefer set bAvailable=? where reeferNumber=?";
			String sql1 = "update remainsCarry set deadID=? , returnTime = ? where carryNumber=?";
			String sql2 = "insert into remainsReefer(reeferNumber, carryNumber, deadID, contactName, contactMobile, reeferNo, startTime, deadSource, staffName, belongings, memo) values(?,?,?,?,?,?,?,?,?,?,?)";
	
			try
			{
				conn.setAutoCommit(false);
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, "0");
				ps.setString(2, reeferNo);				
				row = ps.executeUpdate();
				
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
				ps1.setString(2, startTime);	
				ps1.setString(3, carryNumber);
				row = ps1.executeUpdate();	
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, reeferNumber);
				ps2.setString(2, carryNumber);	
				ps2.setString(3, deadId);
				ps2.setString(4, contactName);
				ps2.setString(5, contactMobile);
				ps2.setString(6, reeferNo);
				ps2.setString(7, startTime);				
				ps2.setString(8, deadSource);
				ps2.setString(9, staffName);
				ps2.setString(10, belongings);
				ps2.setString(11, memo);
				row = ps2.executeUpdate();						
				
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
	
	public String getRemainsReefer(String reeferNumber, String deadId)
	{		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());
		
		Connection conn=DBDao.openDateBase("dongtai");	
	
		int row=0;
		if(conn!=null)
		{
			String sql ="";
			String carryNumber = null;
			String endTime = null;
			ResultSet rs=null;
			ResultSet rs2=null;
			if (!reeferNumber.equals("") && !deadId.equals(""))
			{
				sql = "select * from remainsReefer where reeferNumber=? and deadID = ?";
			}
			else
			{
				sql = "select * from remainsReefer where reeferNumber=? or deadID = ?";
			}
			
			String sql2 = "select * from remainsCarry where carryNumber=?";	
			
			try
			{								
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, reeferNumber);
				ps.setString(2, deadId);				
				rs=ps.executeQuery();
				
				if (rs.next())
				{
					rs.last();
					carryNumber = rs.getString("carryNumber");
					endTime = rs.getString("endTime");
				}
				else
				{
					this.returnString = null;
					return returnString;
				}				
				
				if (endTime != null)
				{
					this.returnString = null;
					return returnString;
				}
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, carryNumber);			
				rs2=ps2.executeQuery();
				
			    String result = "";
							
				
				if (rs2.next())
				{
					rs2.last();
					result = "{carryType:\""+rs2.getString("carryType")
							+"\",address:\""+rs2.getString("address")
							+"\",contactName:\""+ rs2.getString("contactName")								
							+"\",contactMobile:\"" + rs2.getString("contactMobile") 
							+"\",carryTime:\"" + rs2.getString("startTime").toString().substring(0,16) 
							+"\",returnTime:\"" + rs2.getString("returnTime").toString().substring(0,16) 
							+"\",bInternalCar:\"" + rs2.getString("bInternalCar")
							+"\",carNumber:\"" + rs2.getString("carNumber")
							+"\",carBeCost:\"" + rs2.getString("carBeCost")
							+"\",carRealCost:\"" + rs2.getString("carRealCost")
							+"\",deadId:\""+rs.getString("deadId")
							+"\",memberName:\""+rs.getString("contactName")
							+"\",memberMobile:\""+rs.getString("contactMobile")
							+"\",reeferNo:\""+rs.getString("reeferNo")
							+"\",deadSource:\""+rs.getString("deadSource")
							+"\",startTime:\""+rs.getString("startTime").toString().substring(0,16)
							+"\",endTime:\""+ date
							+"\",staffName:\""+rs.getString("staffName")
							+"\",belongings:\""+rs.getString("belongings")
							+"\",memo:\""+rs.getString("memo")
							+"\",carryNumber:\""+rs.getString("carryNumber")
							+"\",reeferBeCost:\""+rs.getString("reeferBeCost")
							+"\",reeferRealCost:\""+rs.getString("reeferRealCost") + "\"}";																					 							
				}
				else
				{
					result = "{deadId:\""+rs.getString("deadId")
							+"\",memberName:\""+rs.getString("contactName")
							+"\",memberMobile:\""+rs.getString("contactMobile")
							+"\",reeferNo:\""+rs.getString("reeferNo")
							+"\",deadSource:\""+rs.getString("deadSource")
							+"\",startTime:\""+rs.getString("startTime").toString().substring(0,16)
							+"\",endTime:\""+ date
							+"\",staffName:\""+rs.getString("staffName")
							+"\",belongings:\""+rs.getString("belongings")
							+"\",memo:\""+rs.getString("memo")
							+"\",carryNumber:\""+rs.getString("carryNumber")
							+"\",reeferBeCost:\""+rs.getString("reeferBeCost")
							+"\",reeferRealCost:\""+rs.getString("reeferRealCost") + "\"}";	
				}		
			    
				this.returnString = result;
				
				ps.close();		
				conn.close();			
			}
			catch(SQLException e)
			{
			    e.printStackTrace();							
				this.returnString = null;			
			}
		}
		else
		{
			this.returnString = null;
		}
		return returnString;
	}
	
	public String updateRemainsReefer(String reeferNumber, String endTime, String reeferBeCost, String reeferRealCost, String deadId, String belongings, String memo, String reeferNo, String carryNumber) throws UnsupportedEncodingException
	{
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		
		belongings = new String(belongings.getBytes("ISO-8859-1"), "GB2312");
		memo = new String(memo.getBytes("ISO-8859-1"), "GB2312");
		
		if(conn!=null)
		{
			String sql = "update remainsReefer set endTime=?, reeferBeCost=?, reeferRealCost=?, deadId=?, belongings=?, memo=? where reeferNumber=?";
			String sql2 = "update reefer set bAvailable=? where reeferNumber=?";
			String sql3 = "update remainsCarry set deadId=? where carryNumber=?";
			try
			{
				conn.setAutoCommit(false);
				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, endTime);
				ps.setString(2, reeferBeCost);
				ps.setString(3, reeferRealCost);
				ps.setString(4, deadId);
				ps.setString(5, belongings);
				ps.setString(6, memo);
				ps.setString(7, reeferNumber);
				
				row = ps.executeUpdate();
				
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, "1");
				ps2.setString(2, reeferNo);			
				row = ps2.executeUpdate();	
				
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, deadId);
				ps3.setString(2, carryNumber);			
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
