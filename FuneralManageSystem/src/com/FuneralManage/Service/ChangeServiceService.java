package com.FuneralManage.Service;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.entity.DeadCremation;
import com.FuneralManage.entity.DeadFuneralGoods;
import com.FuneralManage.entity.DeadLeaveRoom;
import com.FuneralManage.entity.DeadMakeBeauty;
import com.FuneralManage.entity.DeadUrn;

public class ChangeServiceService extends BaseService{
	
	private String returnString;
	private String typeNo;
	private String itemNo;
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

	

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
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
	public String insertIntoUrn(String deadId, String urn)throws SQLException {  //插入骨灰盒
		// TODO Auto-generated method stub

		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			DeadUrn deadUrn=new DeadUrn();
			
			try{
				
				conn.setAutoCommit(false); 
				String sql1="delete  from deadChosenUrn where deadId=?";
				String sql2="insert into deadChosenUrn(deadId,urnName,urnBeCost,urnRealCost)values(?,?,?,?)";
				ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
				ps1.executeUpdate();
				
				if(urn.length()>1){
					JSONObject jsonUrn = JSONObject.fromObject(urn);
					String name1=jsonUrn.getString("urnName");
					
					String beCost1=jsonUrn.getString("urnBeCost");
					String realCost1=jsonUrn.getString("urnRealCost");
					deadUrn.setUrnName(name1);
					deadUrn.setUrnBeCost(Integer.parseInt(beCost1));
					deadUrn.setUrnRealCost(Integer.parseInt(realCost1));
					ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, deadId);
					ps2.setString(2, deadUrn.getUrnName());
					ps2.setInt(3, deadUrn.getUrnBeCost());
					ps2.setInt(4, deadUrn.getUrnRealCost());
					row = ps2.executeUpdate();
					if (row > 0) 
					{
						returnString="成功选定" + row + "项骨灰盒！";
					}
					else
					{
						returnString="添加失败,请检查是否重复添加骨灰盒!";
					}
					
				}

				conn.commit();
				
				
				
			}
			catch(SQLException e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					ps1.close();
					ps2.close();
					conn.close();
				}
				returnString="添加失败,请检查是否重复添加骨灰盒!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}
	public void  getFuneralgoods(String deadId) throws SQLException {
		
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn!=null) {
			String sql1 = "select * from deadfuneralgoods where deadId=?";
			String sql2 = "update warehousebalance set balanceNumber=balanceNumber+1 where warehouseName='总库' and goodsName=?";

			PreparedStatement ps1 = conn.prepareStatement(sql1);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			try {
				
			
			ps1.setString(1, deadId);
			ResultSet rs=ps1.executeQuery();			  

             while (rs.next()) {  
            	 
                     String value = rs.getString("goodsName");
                     ps2.setString(1, value);
                     ps2.executeUpdate();
                      
                 }
			}
             catch (Exception e) {
 				// TODO: handle exception
            	 e.printStackTrace();
             
             }finally{
            	 ps1.close();
            	 ps2.close();
            	 conn.close();
             }
			
		}
            					
		}
	
	
	public String insertIntoGoods(String deadId, String funeralGoods) throws SQLException {  //插入丧葬品
		// TODO Auto-generated method stub
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps3 = null;

			
			try{
				conn.setAutoCommit(false); 
			    
				String sql1="delete from deadfuneralgoods where deadId=?";
				String sql2="insert into deadfuneralgoods(deadID,goodsName,goodsBeCost,goodsRealCost)values(?,?,?,?)";
				String sql3="update warehousebalance set balanceNumber=balanceNumber-1 where warehouseName='总库' and goodsName=?";
				ps1 =conn.prepareStatement(sql1);
				ps1.setString(1,deadId);
				
				ps1.executeUpdate();
								
				
				if(funeralGoods.length()>1){
					String funeralGoodsStr="["+funeralGoods+"]";
					JSONArray jsonArrayGoodsArray=JSONArray.fromObject(funeralGoodsStr);
					DeadFuneralGoods deadFuneralGoods=new DeadFuneralGoods();
					
					if(jsonArrayGoodsArray.size()>0){
						for(int i=0;i<jsonArrayGoodsArray.size();i++){
							JSONObject JSONfuneralGoods = jsonArrayGoodsArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
							deadFuneralGoods.setFuneralGoodsName(JSONfuneralGoods.getString("goodsName"));  // 得到 每个对象中的属性值
							deadFuneralGoods.setFuneralGoodsBeCost(Integer.parseInt(JSONfuneralGoods.getString("goodsBeCost")));
							deadFuneralGoods.setFuneralGoodsRealCost(Integer.parseInt(JSONfuneralGoods.getString("goodsRealCost")));			
							ps2 = conn.prepareStatement(sql2);
							ps2.setString(1, deadId);
							ps2.setString(2,deadFuneralGoods.getFuneralGoodsName() );
							ps2.setInt(3, deadFuneralGoods.getFuneralGoodsBeCost());
							ps2.setInt(4, deadFuneralGoods.getFuneralGoodsRealCost());
							row = ps2.executeUpdate();
							ps3=conn.prepareStatement(sql3);
							ps3.setString(1, deadFuneralGoods.getFuneralGoodsName());
							ps3.executeUpdate();
						}
					
				
				
					}
				}
				conn.commit();
				if (row > 0) 
					
				{
					
					returnString="成功加入了" + row + "项丧葬物品！";
				}
				else
				{
					returnString="添加商品失败!";
				}
				
				
			}
			catch(SQLException e){
				
				conn.rollback();
				returnString="数据库操作失败!";
				e.printStackTrace();
				return returnString;
			}finally{
				ps1.close();
				ps2.close();
				conn.close();
			}
		}
		return returnString;
	}
	
	public String insertIntoService(String deadId, String makeBeauty) {  //插入美容服务
		// TODO Auto-generated method stub
		
		
		DeadMakeBeauty deadMakeBeauty = new DeadMakeBeauty();
		
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			
			try{
				conn.setAutoCommit(false); 
			    String sql1="delete from deadserviceitem where deadId=? and cremationTypeNo=?";   
				String sql2="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
				ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
				ps1.setString(2, "01");
				
				ps1.executeUpdate();
				
				
				if(makeBeauty.length()>1){
					
					JSONObject jsonMakeBeauty = JSONObject.fromObject(makeBeauty);
					String name2=jsonMakeBeauty.getString("makeBeautyName");					
					String beCost2=jsonMakeBeauty.getString("makeBeautyBeCost");
					String realCost2=jsonMakeBeauty.getString("makeBeautyRealCost");
					String status1 =jsonMakeBeauty.getString("status");
					
					deadMakeBeauty.setMakeBeautyName(name2);
					deadMakeBeauty.setMakeBeautyBeCost(Integer.parseInt(beCost2));
					deadMakeBeauty.setMakeBeautyRealCost(Integer.parseInt(realCost2));
					deadMakeBeauty.setStatus(status1);
					String typeNo=getTypeNumber(deadMakeBeauty.getMakeBeautyName());
					String itemNo=getItemNumber(deadMakeBeauty.getMakeBeautyName());
					ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, deadId);
					ps2.setString(2, typeNo);
					ps2.setString(3, itemNo);
					ps2.setString(4, deadMakeBeauty.getMakeBeautyBeCost()+"");
					ps2.setString(5, deadMakeBeauty.getMakeBeautyRealCost()+"");
					ps2.setString(6, deadMakeBeauty.getStatus()+"");
					row = ps2.executeUpdate();
					if (row > 0) 
					{
						returnString="成功加入了" + row + "项火化服务！";
					}
					else
					{
						returnString="添加失败,请检查是否重复添加同一类型火化服务!";
					}
				}
			
				
				conn.commit();				
				
				
			}
			catch(SQLException e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						ps1.close();
						ps2.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}
	public String insertIntoService1(String deadId, String leaveRoom) {  //插入告别服务
		// TODO Auto-generated method stub
		DeadLeaveRoom deadLeaveRoom = new DeadLeaveRoom();

		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			
			try{
				conn.setAutoCommit(false);
				String sql1="delete from deadserviceitem where deadId=? and cremationTypeNo=02";
				String sql2="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
				ps1=conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
				
				
				
				 
				ps1.executeUpdate();
				
				if(leaveRoom.length()>1){
					JSONObject jsonLeaveRoom = JSONObject.fromObject(leaveRoom);
					String name3=jsonLeaveRoom.getString("leaveRoomName");
					String beCost3=jsonLeaveRoom.getString("leaveRoomBeCost");
					String realCost3=jsonLeaveRoom.getString("leaveRoomRealCost");
					String status2 =jsonLeaveRoom.getString("status");
					deadLeaveRoom.setLeaveRoomName(name3);
					deadLeaveRoom.setLeaveRoomBeCost(Integer.parseInt(beCost3));
					deadLeaveRoom.setLeaveRoomRealCost(Integer.parseInt(realCost3));
					deadLeaveRoom.setStatus(status2);
					String typeNo=getTypeNumber(deadLeaveRoom.getLeaveRoomName());
					String itemNo=getItemNumber(deadLeaveRoom.getLeaveRoomName());
					ps2=conn.prepareStatement(sql2);

					ps2.setString(1, deadId);
					ps2.setString(2, typeNo);
					ps2.setString(3, itemNo);
					ps2.setString(4, deadLeaveRoom.getLeaveRoomBeCost()+"");
					ps2.setString(5, deadLeaveRoom.getLeaveRoomRealCost()+"");
					ps2.setString(6, deadLeaveRoom.getStatus()+"");
					row = ps2.executeUpdate();
					if (row > 0) 
					{
						returnString="成功加入了" + row + "项火化服务！";
					}
					else
					{
						returnString="添加失败,请检查是否重复添加同一类型火化服务!";
					}
					
					
				}
				
				conn.commit();
			
				
			}
			catch(SQLException e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					try {
						ps1.close();
						ps2.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}
		}
		return returnString;
	}
	public String insertIntoService2(String deadId, String cremation) throws SQLException {
		// TODO Auto-generated method stub
		DeadCremation deadCremation = new DeadCremation();

		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			PreparedStatement ps1=null;
			PreparedStatement ps2=null;
			String typeNo = "";
			String itemNo = "";
			try{
				conn.setAutoCommit(false);
				String sql1="delete from deadserviceitem where deadId=? and cremationTypeNo=03";
				String sql2="insert into deadserviceitem(deadID,cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost,status)values(?,?,?,?,?,?)";
				ps1=conn.prepareStatement(sql1);
				ps1.setString(1, deadId);
			
				
				ps1.executeUpdate();
				
				
				if(cremation.length()>1){
					JSONObject jsonCremation = JSONObject.fromObject(cremation);
					String name4=jsonCremation.getString("cremationName");
					String beCost4=jsonCremation.getString("cremationBeCost");
					String realCost4=jsonCremation.getString("cremationRealCost");
					String status3 =jsonCremation.getString("status");
					
					deadCremation.setCremationName(name4);
					
					deadCremation.setCremationBeCost(Integer.parseInt(beCost4));
					deadCremation.setCremationRealCost(Integer.parseInt(realCost4));
					deadCremation.setStatus(status3);
					 typeNo=getTypeNumber(deadCremation.getCremationName());
					 itemNo=getItemNumber(deadCremation.getCremationName());
					 ps2=conn.prepareStatement(sql2);

						ps2.setString(1, deadId);
						ps2.setString(2, typeNo);
						ps2.setString(3, itemNo);
						ps2.setString(4, deadCremation.getCremationBeCost()+"");
						ps2.setString(5, deadCremation.getCremationRealCost()+"");
						ps2.setString(6, deadCremation.getStatus()+"");
						row = ps2.executeUpdate();
						if (row > 0) 
						{
							returnString="成功加入了" + row + "项火化服务！";
						}
						else
						{
							returnString="添加失败,请检查是否重复添加同一类型火化服务!";
						}
					
				}
				
				
				conn.commit();
				
				
				
			}
			catch(SQLException e){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				returnString="添加失败,请检查是否重复添加同一类型火化服务!";
				e.printStackTrace();
				return returnString;
			}finally{
				
					ps1.close();
					ps2.close();
					conn.close();
				
			}
		}
		return returnString;
	}

}

