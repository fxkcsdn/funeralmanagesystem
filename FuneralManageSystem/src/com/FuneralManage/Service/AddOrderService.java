package com.FuneralManage.Service;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.FuneralManage.entity.OrderInfo;

public class AddOrderService extends BaseService
{
	public String returnString;
	private String itemNo;
	private String typeNo;
	private String typeNoH;
	private String itemNoH; 
	private String typeNoM;
	private String itemNoM;
	private String typeNoG;
	private String itemNoG;
	public String getTypeNoH()
	{
		return typeNoH;
	}
	public void setTypeNoH(String typeNoH)
	{
		this.typeNoH = typeNoH;
	}
	public String getItemNoH()
	{
		return itemNoH;
	}
	public void setItemNoH(String itemNoH)
	{
		this.itemNoH = itemNoH;
	}
	public String getTypeNoM()
	{
		return typeNoM;
	}
	public void setTypeNoM(String typeNoM)
	{
		this.typeNoM = typeNoM;
	}
	public String getItemNoM()
	{
		return itemNoM;
	}
	public void setItemNoM(String itemNoM)
	{
		this.itemNoM = itemNoM;
	}
	public String getTypeNoG()
	{
		return typeNoG;
	}
	public void setTypeNoG(String typeNoG)
	{
		this.typeNoG = typeNoG;
	}
	public String getItemNoG()
	{
		return itemNoG;
	}
	public void setItemNoG(String itemNoG)
	{
		this.itemNoG = itemNoG;
	}
	public String getMaxOrderNumber(OrderInfo orderInfo)
	{
		String orderNumber;
		orderNumber=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");//设置日期格式
		String date = df.format(new Date());
		String result = "";
		
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "select * from deadOrderBasic where orderNumber like ?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, date+"%");
				rs=ps.executeQuery();
				int count = 0;
				while(rs.next())
				{					 
					 result = rs.getString("orderNumber");
					 ++count;
				}		
				System.out.println(count);
				if (result != "")
				{
					//result = result.substring(6, result.length());
					result = date + (count + 1);
				}
				else
				{
					result = date + "1";
				}
				returnString = "{rentNumber:\"" + result + "\"}";				
			    rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();				
			}
		}
		orderNumber=result;
		return orderNumber;
	}
	public String getTypeNumber(String itemName){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select typeNo from cremationserviceitem where itemName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, itemName);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					this.typeNo=rs.getString("typeNo");
				}
				else{
					this.typeNo="";
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return typeNo;
	}	
	public String getItemNumber(String itemName){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select itemNo from cremationserviceitem where itemName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, itemName);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					this.itemNo=rs.getString("itemNo");
				}
				else{
					this.itemNo="";
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return itemNo;
	}
	public String orderInfoRegister(OrderInfo orderInfo) throws SQLException
	{
		Connection conn=DBDao.openDateBase("dongtai");
		//conn.setAutoCommit(false);
		int row=0;	
		if(conn!=null){
			System.out.print("deadInfoRegister中数据库连接成功");
			String sql1 = "insert into deadorderbasic(deadID,estimatedDate,orderNumber,deadName,orderTime,factInTime,contactMobile,contactName)values(?,?,?,?,?,?,?,?)";
			String sql2 = "insert into deadorderservice(orderNumber,cremationTypeNo,cremationItemNo)values(?,?,?)";
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps3 = null;
			PreparedStatement ps4 = null;
//			System.out.println(orderInfo.orderTime);
			try
			{
				ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, orderInfo.deadId);
				ps1.setString(2, orderInfo.estimatedDate);
				ps1.setString(3, orderInfo.orderNumber);
				ps1.setString(4, orderInfo.deadName);
				ps1.setString(5, orderInfo.orderTime);
				ps1.setString(6, orderInfo.factInTime);
				ps1.setString(7, orderInfo.contactMobile);
				ps1.setString(8, orderInfo.contactName);
				row = ps1.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "行数据！";
				}
				else
				{
					returnString="提交数据失败!";
				}
				ps1.close();
//提交美容		
				if(orderInfo.bodyBeauty.equals("0"))
				{
					System.out.println("未选择美容业务！");
				}
				else
				{
					itemNoM=this.getItemNumber(orderInfo.bodyBeauty);
					typeNoM=this.getTypeNumber(orderInfo.bodyBeauty);
					ps2 = conn.prepareStatement(sql2);   
					ps2.setString(1, orderInfo.orderNumber);
					ps2.setString(2, typeNoM);
					ps2.setString(3, itemNoM);				
					row = ps2.executeUpdate();
					if (row > 0) 
					{
						System.out.println("美容成功加入了" + row + "行数据！");
					}
					else
					{
						System.out.println("美容成功加入了0行数据！");
						returnString="提交数据失败!";
					}
					ps2.close();
				}
				
//提交告别		
				if(orderInfo.farewareHall.equals("0"))
				{
					System.out.println("未选择告别业务！");
				}
				else
				{
					itemNoG=this.getItemNumber(orderInfo.farewareHall);
					typeNoG=this.getTypeNumber(orderInfo.farewareHall);
					ps3 = conn.prepareStatement(sql2);   
					ps3.setString(1, orderInfo.orderNumber);
					ps3.setString(2, typeNoG);
					ps3.setString(3, itemNoG);				
					row = ps3.executeUpdate();
					if (row > 0) 
					{
						System.out.println("告别厅成功加入了" + row + "行数据！");
					}
					else
					{
						System.out.println("告别厅加入了0行数据！");
						returnString="提交数据失败!";
					}
					ps3.close();
				}
//提交火化
				if(orderInfo.chooseCremationType.equals("0"))
				{
					System.out.println("未选择火化业务！");
				}
				else
				{
					itemNoH=this.getItemNumber(orderInfo.chooseCremationType);
					typeNoH=this.getTypeNumber(orderInfo.chooseCremationType);
					ps4 = conn.prepareStatement(sql2);    
					ps4.setString(1, orderInfo.orderNumber);
					ps4.setString(2, typeNoH);
					ps4.setString(3, itemNoH);				
					row = ps4.executeUpdate();
					if (row > 0) 
					{
						System.out.println("火化成功加入了" + row + "行数据！");
					}
					else
					{
						System.out.println("火化加入了0行数据！");
						returnString="提交数据失败!";
					}
					ps4.close();
				}
				
				
				
				
				//conn.commit();
				conn.close();
				System.out.println("基本预约信息提交成功！");
				
				return returnString;
			}
			catch(SQLException e)
			{
				//conn.rollback();
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}		
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
	
	}
}
	