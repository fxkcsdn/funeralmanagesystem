package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemainsCarryService extends BaseService{
	private String returnString;
	
	public String getRemainsCarryByCarryNumber(String coffinNumber, String carryNumber, String carryType) throws UnsupportedEncodingException
	{			
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());
		
		
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{			
			String sql = "";
			String rentNumber1 = "";
			if (!coffinNumber.equals(""))
			{
				String sql3 = "select * from rentCoffin where coffinNumber = ? ";				
				ResultSet rs3=null;
				try {
					PreparedStatement ps3=conn.prepareStatement(sql3);
					ps3.setString(1, coffinNumber);					
					rs3=ps3.executeQuery();	
					if (rs3.next())
					{
						rs3.last();
						rentNumber1 = rs3.getString("rentNumber");	
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.returnString = null;
				}				
				sql = "select * from remainsCarry where rentCoffinNumber = ? and carryType=?";
			}
			else
			{
				sql = "select * from remainsCarry where carryNumber = ? and carryType=?";
			}
			
			String sql2 = "select * from rentCoffin where rentNumber = ?";
			ResultSet rs=null;
			ResultSet rs2=null;
					
			
			try
			{
				String number = coffinNumber.equals("") == true?carryNumber:rentNumber1;				
			
				PreparedStatement ps=conn.prepareStatement(sql);					
				PreparedStatement ps2=conn.prepareStatement(sql2);	
			
				ps.setString(1, number);
				ps.setString(2, carryType);
				
				
				String result = "";
				rs=ps.executeQuery();	
				if (rs.next())
				{
					rs.last();
					if (rs.getString("carryNumber") != null)
					{					
						if (!coffinNumber.equals("") && !carryNumber.equals("") && !rs.getString("carryNumber").equals(carryNumber))
						{
							this.returnString = null;
							return returnString;
						}
						
						String rentNumber = rs.getString("rentCoffinNumber");					
						String getCoffinNumber="";
						String getStartTime="";
						String getCarBeCost="";
						String getCarRealCost="";
						if (!rentNumber.equals(""))
						{
							ps2.setString(1, rentNumber);	
							rs2=ps2.executeQuery();
							rs2.last();
							getCoffinNumber = rs2.getString("coffinNumber");
							getStartTime = rs2.getString("startTime").toString().substring(0,16);
							getCarBeCost = rs2.getString("carBeCost");
							getCarRealCost = rs2.getString("carRealCost");
						}
						else
						{
							getCoffinNumber = "";
							getStartTime = "";
							getCarBeCost ="";
							getCarRealCost = "";
						}
										
						if (rs.getString("returnTime") != null)
						{
							this.returnString = null;
							return returnString;
						}
						
						result = "{rentNumber:\""+rs.getString("rentCoffinNumber")
								+"\",carryNumber:\""+rs.getString("carryNumber")
								+"\",coffinNumber:\""+ getCoffinNumber
								+"\",deadId:\"" + (rs.getString("deadId") == null ? "" : rs.getString("deadId"))							
								+"\",startTime:\"" + getStartTime 
								+"\",endTime:\"" + date
								+"\",carBeCost:\"" + getCarBeCost 
								+"\",carRealCost:\"" + getCarRealCost 
								+"\",carryTime:\"" + rs.getString("startTime").toString().substring(0,16) 
								+"\",returnTime:\"" + date
								+"\",carryType:\""+rs.getString("carryType")
								+"\",address:\""+ rs.getString("address") 
								+"\",contactMobile:\"" + rs.getString("contactMobile") 
								+"\",carNumber:\"" + rs.getString("carNumber")
								+"\",carCarryBeCost:\"" + rs.getString("carBeCost")
								+"\",carCarryRealCost:\"" + rs.getString("carRealCost")
								+"\",bInternalCar:\"" + rs.getString("bInternalCar")
								+"\",contactName:\"" + rs.getString("contactName") + "\"}";					 
						this.returnString = result;	
						
					}
					else
					{
						this.returnString = null;
					}								
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
	
	public String getCarryNumber()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "select * from remainsCarry where carryNumber like ?";
			ResultSet rs=null;
			try
			{
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
					//result = date + (Integer.parseInt(result) + 1);
					result = date + (count + 1);
				}
				else
				{
					result = date + "1";
				}
				returnString = "{carryNumber:\"" + result + "\"}";
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
	
	public String addRemainsCarry(String contactName, String contactMobile, String startTime, String address, 
			String carryNumber, String deadId, String carryType, String carNumber, String carCost, String bInternalCar, String rentNumber, String carRealCost) throws UnsupportedEncodingException
	{
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;				
		if(conn!=null)
		{			
			String sql = "insert into remainsCarry(contactName,contactMobile,startTime,address, carryNumber, deadId, carryType, carNumber, carBeCost, bInternalCar, rentCoffinNumber, carRealCost)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			try
			{				
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, contactName);
				ps.setString(2, contactMobile);
				ps.setString(3, startTime);				
				ps.setString(4, address);				
				ps.setString(5, carryNumber);
				ps.setString(6, deadId);				
				ps.setString(7, carryType);
				ps.setString(8, carNumber);
				ps.setString(9, carCost);
				ps.setString(10, bInternalCar);
				ps.setString(11, rentNumber);
				ps.setString(12, carRealCost);
				row = ps.executeUpdate();					
				if (row > 0) 
				{
					this.returnString="{result:\"yes\"}";
				}
				else
				{
					this.returnString="{result:\"no\"}";
				}			
				
				ps.close();		
				conn.close();
			}
			catch(SQLException e)
			{				
			    e.printStackTrace();							
				this.returnString="{result:\"no\"}";
			}			
		}
		else
		{
			this.returnString="{result:\"no\"}";
		}
		return returnString;
	}
	
	public String updateRemainsCarry(String deadId, String returnTime, String carryNumber)
	{
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null)
		{
			String sql = "update remainscarry set deadId=?, returnTime=? where carryNumber=?";
			try
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, returnTime);
				ps.setString(3, carryNumber);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="{result:\"yes\"}";
				}
				else
				{
					returnString="{result:\"no\"}";
				}
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
