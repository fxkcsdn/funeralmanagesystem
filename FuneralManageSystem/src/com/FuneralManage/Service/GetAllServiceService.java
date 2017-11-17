package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAllServiceService extends BaseService{
	
	private String returnString;
	private String itemNameByTwoInfo;
	private int remainsCarryCost;
	private int remainsCarryRealCost;
	private int rentCoffinCost;
	private int rentCoffinRealCost;
	private int rentCoffinCarBeCost;		//租棺部分用车费用
	private int rentCoffinCarRealCost;
	
	private int watchSpiritCost;
	private int watchSpiritRealCost;
	
	private int coffinBeCost;
	private int coffinRealCost;
	private int deadChosenUrnCost;
	private int deadServiceItemCost;
	private int deadFuneralGoodsCost;
	
	public int getCoffinBeCost() {
		return coffinBeCost;
	}

	public void setCoffinBeCost(int coffinBeCost) {
		this.coffinBeCost = coffinBeCost;
	}

	public int getWatchSpiritRealCost() {
		return watchSpiritRealCost;
	}

	public void setWatchSpiritRealCost(int watchSpiritRealCost) {
		this.watchSpiritRealCost = watchSpiritRealCost;
	}

	public int getRentCoffinCarRealCost() {
		return rentCoffinCarRealCost;
	}

	public void setRentCoffinCarRealCost(int rentCoffinCarRealCost) {
		this.rentCoffinCarRealCost = rentCoffinCarRealCost;
	}

	public int getRentCoffinRealCost() {
		return rentCoffinRealCost;
	}

	public void setRentCoffinRealCost(int rentCoffinRealCost) {
		this.rentCoffinRealCost = rentCoffinRealCost;
	}

	public int getRentCoffinCarBeCost() {
		return rentCoffinCarBeCost;
	}

	public void setRentCoffinCarBeCost(int rentCoffinCarBeCost) {
		this.rentCoffinCarBeCost = rentCoffinCarBeCost;
	}

	public int getRemainsCarryRealCost() {
		return remainsCarryRealCost;
	}

	public void setRemainsCarryRealCost(int remainsCarryRealCost) {
		this.remainsCarryRealCost = remainsCarryRealCost;
	}

	public int getCoffinRealCost() {
		return coffinRealCost;
	}

	public void setCoffinRealCost(int coffinRealCost) {
		this.coffinRealCost = coffinRealCost;
	}

	public int getRentCoffinCost() {
		return rentCoffinCost;
	}

	public void setRentCoffinCost(int rentCoffinCost) {
		this.rentCoffinCost = rentCoffinCost;
	}

	public int getWatchSpiritCost() {
		return watchSpiritCost;
	}

	public void setWatchSpiritCost(int watchSpiritCost) {
		this.watchSpiritCost = watchSpiritCost;
	}

	public int getDeadChosenUrnCost() {
		return deadChosenUrnCost;
	}

	public void setDeadChosenUrnCost(int deadChosenUrnCost) {
		this.deadChosenUrnCost = deadChosenUrnCost;
	}

	public int getDeadServiceItemCost() {
		return deadServiceItemCost;
	}

	public void setDeadServiceItemCost(int deadServiceItemCost) {
		this.deadServiceItemCost = deadServiceItemCost;
	}

	public int getDeadFuneralGoodsCost() {
		return deadFuneralGoodsCost;
	}

	public void setDeadFuneralGoodsCost(int deadFuneralGoodsCost) {
		this.deadFuneralGoodsCost = deadFuneralGoodsCost;
	}

	public int getRemainsCarryCost() {
		return remainsCarryCost;
	}

	public void setRemainsCarryCost(int remainsCarryCost) {
		this.remainsCarryCost = remainsCarryCost;
	}

	public String getItemNameByTwoInfo() {
		return itemNameByTwoInfo;
	}

	public void setItemNameByTwoInfo(String itemNameByTwoInfo) {
		this.itemNameByTwoInfo = itemNameByTwoInfo;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	//getRemainsCarryCost
	public String getRemainsCarryCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select carryNumber,carryType,carNumber,carBeCost,carRealCost from remainscarry where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("carryNumber", rs.getString("carryNumber"));
					jsonObject.put("carryType", rs.getString("carryType"));
					jsonObject.put("carNumber", rs.getString("carNumber"));
					jsonObject.put("carBeCost", rs.getInt("carBeCost"));
					jsonObject.put("carRealCost", rs.getInt("carRealCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String getRentCoffinCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select rentNumber,beRentCost,realRentCost from rentCoffin where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("rentNumber", rs.getString("rentNumber"));
					jsonObject.put("beRentCost", rs.getInt("beRentCost"));
					jsonObject.put("realRentCost", rs.getInt("realRentCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String getWatchSpiritCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select watchNumber,villaBeCost,villaRealCost,coffinBeCost,coffinRealCost from watchspirit where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("watchNumber", rs.getString("watchNumber"));
					jsonObject.put("villaBeCost", rs.getInt("villaBeCost"));
					jsonObject.put("villaRealCost", rs.getInt("villaRealCost"));
					jsonObject.put("coffinBeCost", rs.getInt("coffinBeCost"));
					jsonObject.put("coffinRealCost", rs.getInt("coffinRealCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String getDeadChosenUrnCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select urnName,urnBeCost,urnRealCost from deadchosenurn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("urnName", rs.getString("urnName"));
					jsonObject.put("urnBeCost", rs.getInt("urnBeCost"));
					jsonObject.put("urnRealCost", rs.getInt("urnRealCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String delChosenUrnDao(String deadId,String urnName){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="delete from deadchosenurn where deadID=? and urnName=?";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, urnName);
				row = ps.executeUpdate();
				if(row>0){
					returnString="成功删除了" + row + "项骨灰盒！";
				}
				else{
					returnString="删除骨灰盒失败！";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String getItemName(String TypeNo,String ItemNo){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select itemName from cremationserviceitem where typeNo=? and itemNo=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, TypeNo);
				ps.setString(2, ItemNo);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					itemNameByTwoInfo=rs.getString("itemName");
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
		return itemNameByTwoInfo;
	}
	
	public String getDeadServiceItemCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select cremationTypeNo,cremationItemNo,itemBeCost,itemRealCost from deadserviceitem where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					String TypeNo=rs.getString("cremationTypeNo");
					String ItemNo=rs.getString("cremationItemNo");
					String itemName=getItemName(TypeNo,ItemNo);
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("itemName", itemName);
					jsonObject.put("itemBeCost", rs.getInt("itemBeCost"));
					jsonObject.put("itemRealCost", rs.getInt("itemRealCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	AddServiceService addServiceDao=new AddServiceService();
	
	public String delServiceItemDao(String deadId, String itemName){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="delete from deadServiceItem where deadID=? and cremationTypeNo=? and cremationItemNo=?";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				String typeNo=addServiceDao.getTypeNumber(itemName);
				String itemNo=addServiceDao.getItemNumber(itemName);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				row = ps.executeUpdate();
				if(row>0){
					returnString="成功删除了" + row + "项火化服务！";
				}
				else{
					returnString="删除火化服务失败！";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String getFuneralGoodsCostDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select goodsName,goodsBeCost,goodsRealCost from deadFuneralGoods where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("goodsName", rs.getString("goodsName"));
					jsonObject.put("goodsBeCost", rs.getInt("goodsBeCost"));
					jsonObject.put("goodsRealCost", rs.getInt("goodsRealCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public String delGoodsDao(String deadId, String goodsName){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			String sql="delete from deadFuneralGoods where deadID=? and goodsName=?";
			try{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, goodsName);
				row = ps.executeUpdate();
				if(row>0){
					returnString="成功删除了" + row + "项丧葬品服务！";
				}
				else{
					returnString="删除丧葬品服务失败！";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		return returnString;
	}
	
	public int getRemainsCarryCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(carBeCost) from remainscarry where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.remainsCarryCost=rs.getInt(1);
				}
				else{
					this.remainsCarryCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return remainsCarryCost;
			}
		}
		else{
			returnString="数据库连接失败！";
		}
		return remainsCarryCost;
	}
	
	public int getRemainsCarryRealCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(carRealCost) from remainscarry where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.remainsCarryRealCost=rs.getInt(1);
				}
				else{
					this.remainsCarryRealCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return remainsCarryRealCost;
			}
		}
		else{
			returnString="数据库连接失败！";
		}
		return remainsCarryRealCost;
	}
	
	public int getRentCoffinCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(beRentCost) from rentCoffin where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.rentCoffinCost=rs.getInt(1);
				}
				else{
					this.rentCoffinCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				rentCoffinCost=0;
				return rentCoffinCost;
			}
		}
		else{
			rentCoffinCost=0;
		}
		return rentCoffinCost;
	}
	
	public int getRentCoffinRealCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(realRentCost) from rentCoffin where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.rentCoffinRealCost=rs.getInt(1);
				}
				else{
					this.rentCoffinRealCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				rentCoffinRealCost=0;
				return rentCoffinRealCost;
			}
		}
		else{
			rentCoffinRealCost=0;
		}
		return rentCoffinRealCost;
	}
	
	public int getRentCrystalCarCost(String deadId){		//租棺部分用车费用
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(carBeCost) from rentCoffin where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.rentCoffinCarBeCost=rs.getInt(1);
				}
				else{
					this.rentCoffinCarBeCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				rentCoffinCarBeCost=0;
				return rentCoffinCarBeCost;
			}
		}
		else{
			rentCoffinCarBeCost=0;
		}
		return rentCoffinCarBeCost;
	}
	
	public int getRentCrystalCarRealCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(carRealCost) from rentCoffin where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.rentCoffinCarRealCost=rs.getInt(1);
				}
				else{
					this.rentCoffinCarRealCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				rentCoffinCarRealCost=0;
				return rentCoffinCarRealCost;
			}
		}
		else{
			rentCoffinCarRealCost=0;
		}
		return rentCoffinCarRealCost;
	}
	
	public int getWatchSpiritCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(villaBeCost) from watchspirit where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.watchSpiritCost=rs.getInt(1);
				}
				else{
					this.watchSpiritCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				watchSpiritCost=0;
				return watchSpiritCost;
			}
		}
		else{
			watchSpiritCost=0;
		}
		return watchSpiritCost;
	}
	
	public int getWatchSpiritRealCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(villaRealCost) from watchspirit where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.watchSpiritRealCost=rs.getInt(1);
				}
				else{
					this.watchSpiritRealCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				watchSpiritRealCost=0;
				return watchSpiritRealCost;
			}
		}
		else{
			watchSpiritRealCost=0;
		}
		return watchSpiritRealCost;
	}
	
	public int getCoffinBeCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(coffinBeCost) from watchspirit where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.coffinBeCost=rs.getInt(1);
				}
				else{
					this.coffinBeCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				coffinBeCost=0;
				return coffinBeCost;
			}
		}
		else{
			coffinBeCost=0;
		}
		return coffinBeCost;
	}
	
	public int getCoffinRealCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(coffinRealCost) from watchspirit where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.coffinRealCost=rs.getInt(1);
				}
				else{
					this.coffinRealCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				coffinRealCost=0;
				return coffinRealCost;
			}
		}
		else{
			coffinRealCost=0;
		}
		return coffinRealCost;
	}
	
	public int getDeadChosenUrnCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(urnRealCost) from deadChosenUrn where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.deadChosenUrnCost=rs.getInt(1);
				}
				else{
					this.deadChosenUrnCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				deadChosenUrnCost=0;
				return deadChosenUrnCost;
			}
		}
		else{
			deadChosenUrnCost=0;
		}
		return deadChosenUrnCost;
	}
	
	public int getDeadServiceItemCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(itemRealCost) from deadServiceItem where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.deadServiceItemCost=rs.getInt(1);
				}
				else{
					this.deadServiceItemCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				deadServiceItemCost=0;
				return deadServiceItemCost;
			}
		}
		else{
			deadServiceItemCost=0;
		}
		return deadServiceItemCost;
	}
	
	public int getDeadFuneralGoodsCost(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select sum(goodsRealCost) from deadFuneralGoods where deadID=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					this.deadFuneralGoodsCost=rs.getInt(1);
				}
				else{
					this.deadFuneralGoodsCost=0;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				deadFuneralGoodsCost=0;
				return deadFuneralGoodsCost;
			}
		}
		else{
			deadFuneralGoodsCost=0;
		}
		return deadFuneralGoodsCost;
	}
}
