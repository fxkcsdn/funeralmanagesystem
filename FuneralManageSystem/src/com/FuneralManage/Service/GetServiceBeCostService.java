package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetServiceBeCostService extends BaseService{

	private String returnString;
	private String typeNo;
	private String itemNo;
	private int price;
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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
	
	public String getItemPrice(String itemName){
		
		typeNo=getTypeNumber(itemName);
		itemNo=getItemNumber(itemName);
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select price from cremationserviceprice where typeNo=? and itemNo=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, typeNo);
				ps.setString(2, itemNo);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					this.price=rs.getInt("price");
					String priceString=String.valueOf(price);
					returnString="{\"price\":\"" + priceString + "\"}";
				}
				else{
					this.returnString="";
				}
				rs.close();
				ps.close();
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
}
