package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FuneralManage.entity.DeadCremation;
import com.FuneralManage.entity.DeadFuneralGoods;
import com.FuneralManage.entity.DeadLeaveRoom;
import com.FuneralManage.entity.DeadMakeBeauty;
import com.FuneralManage.entity.DeadUrn;

public class AddOrderServiceService extends BaseService
{
	private String returnString;
	private String typeNo;
	private String itemNo;
	
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
	
	public String insertIntoService(String deadId, String itemName, String itemBeCost, String itemRealCost, String Status){
		typeNo=getTypeNumber(itemName);
		itemNo=getItemNumber(itemName);
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,inTime,outTime,status)values(?,?,?,?,?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				ps.setString(4, itemBeCost);
				ps.setString(5, itemRealCost);
				ps.setString(6, "");
				ps.setString(7, "");
				ps.setString(8, "0");
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项服务！";
				}
				else
				{
					returnString="添加失败,请检查是否重复添加同一类型服务!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="添加失败,请检查是否重复添加同一类型服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertIntoUrn(String deadId, DeadUrn deadUrn) {
		// TODO Auto-generated method stub
		String itemName=deadUrn.getUrnName();
		int itemBeCost=deadUrn.getUrnBeCost();
		int itemRealCost=deadUrn.getUrnRealCost();
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadChosenUrn(deadId,urnName,urnBeCost,urnRealCost)values(?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, itemName);
				ps.setInt(3, itemBeCost);
				ps.setInt(4, itemRealCost);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功选定" + row + "项骨灰盒！";
				}
				else
				{
					returnString="添加失败,请检查是否重复添加骨灰盒!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="添加失败,请检查是否重复添加骨灰盒!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertIntoService(String deadId, DeadMakeBeauty deadMakeBeauty, String status) {
		// TODO Auto-generated method stub
		typeNo=getTypeNumber(deadMakeBeauty.getMakeBeautyName());
		itemNo=getItemNumber(deadMakeBeauty.getMakeBeautyName());
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				ps.setString(4, deadMakeBeauty.getMakeBeautyBeCost()+"");
				ps.setString(5, deadMakeBeauty.getMakeBeautyRealCost()+"");
				ps.setString(6, status);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项火化服务！";
				}
				else
				{
					returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertIntoService(String deadId, DeadLeaveRoom deadLeaveRoom) {
		// TODO Auto-generated method stub
		typeNo=getTypeNumber(deadLeaveRoom.getLeaveRoomName());
		itemNo=getItemNumber(deadLeaveRoom.getLeaveRoomName());
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				ps.setString(4, deadLeaveRoom.getLeaveRoomBeCost()+"");
				ps.setString(5, deadLeaveRoom.getLeaveRoomRealCost()+"");
				ps.setString(6, "0");
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项火化服务！";
				}
				else
				{
					returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertIntoService(String deadId, DeadCremation deadCremation) {
		// TODO Auto-generated method stub
		typeNo=getTypeNumber(deadCremation.getCremationName());
		itemNo=getItemNumber(deadCremation.getCremationName());
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				ps.setString(4, deadCremation.getCremationBeCost()+"");
				ps.setString(5, deadCremation.getCremationRealCost()+"");
				ps.setString(6, "0");
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项火化服务！";
				}
				else
				{
					returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertIntoGoods(String deadId, DeadFuneralGoods deadFuneralGoods) {
		// TODO Auto-generated method stub
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadfuneralgoods(deadID,goodsName,goodsBeCost,goodsRealCost)values(?,?,?,?)";
			String sql1="update warehousebalance set balanceNumber=balanceNumber-1 where warehouseName='总库' and goodsName=?";

			try{
				conn.setAutoCommit(false);
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps.setString(1, deadId);
				ps.setString(2, deadFuneralGoods.getFuneralGoodsName());
				ps.setInt(3, deadFuneralGoods.getFuneralGoodsBeCost());
				ps.setInt(4, deadFuneralGoods.getFuneralGoodsRealCost());
				ps1.setString(1, deadFuneralGoods.getFuneralGoodsName());
				row = ps.executeUpdate();
				ps1.executeUpdate();
				conn.commit();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "项丧葬物品！";
				}
				else
				{
					returnString="添加商品失败!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="数据库操作失败!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}

	public String insertLastService(String deadId, String typeNo, String itemNo, String status) {
		// TODO Auto-generated method stub
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,status)values(?,?,?,?)";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				ps.setString(4, status);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功确认业务，门禁控制开始";
				}
				else
				{
					returnString="确认业务失败!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				returnString="确认业务失败!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}
}
