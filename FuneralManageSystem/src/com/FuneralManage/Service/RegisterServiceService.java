package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RegisterServiceService extends BaseService{//登记服务页面显示初始化信息
	
	public String inTime;
	public String memberMobile;
	public int deadNumber;
	public String deadName;
	private String flag;
	private String orderNoresult; //用于存储根据deadId取出来的orderNumber
	private String cremationItemNo;
	private String cremationTypeNo;
	private String orderNumber;
	private String returnString;
	
	public String getInTime() {
		return inTime;
	}
	
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	
	public String getMemberMobile() {
		return memberMobile;
	}
	
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}
	
	public int getDeadNumber() {
		return deadNumber;
	}
	
	public void setDeadNumber(int deadNumber) {
		this.deadNumber = deadNumber;
	}
	
	public String getDeadName() {
		return deadName;
	}
	
	public void setDeadName(String deadName) {
		this.deadName = deadName;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getOrderNoresult() {
		return orderNoresult;
	}

	public void setOrderNoresult(String orderNoresult) {
		this.orderNoresult = orderNoresult;
	}

	public String getCremationItemNo() {
		return cremationItemNo;
	}

	public void setCremationItemNo(String cremationItemNo) {
		this.cremationItemNo = cremationItemNo;
	}

	public String getCremationTypeNo() {
		return cremationTypeNo;
	}

	public void setCremationTypeNo(String cremationTypeNo) {
		this.cremationTypeNo = cremationTypeNo;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String showDeadInfoDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		GetAllServiceService getAllServiceDao=new GetAllServiceService();
		if(conn!=null){
			String sql = "select deadName,benefitTime,deadSex,deadAge,remainsNumber,memberMobile,inTime,dealerId,dealerName,dealerAddress,directorName,deadTime,deadType,deadReason,address,area,proofUnit,memo,invoiceNo,subsidyNo,subsidyMoney,deadResidence,operatorRelation,ashesDisposition,pathogeny from remainsIn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					this.memberMobile=rs.getString("memberMobile");
					this.deadNumber=rs.getInt("remainsNumber");
					
					Date date=new Date();
					Timestamp timestamp = rs.getTimestamp("inTime");
					date=new java.util.Date(timestamp.getTime());
					this.inTime=formatter.format(date);
					
					Date date2=new Date();
					Timestamp timestamp2=rs.getTimestamp("deadTime");
					date2=new java.util.Date(timestamp2.getTime());					
					String deadTime=formatter.format(date2);
					
					Date date3=new Date();
					Timestamp timestamp3=rs.getTimestamp("benefitTime");
					date3=new java.util.Date(timestamp3.getTime());					
					String benefitTime=formatter.format(date3);
					
					this.deadName=rs.getString("deadName");
					this.flag="1";
					String deadAge=rs.getString("deadAge");
					String deadSex=rs.getString("deadSex");
					String pathogeny=rs.getString("pathogeny");
					String operatorRelation=rs.getString("operatorRelation");
					String deadResidence=rs.getString("deadResidence");
					String ashesDisposition=rs.getString("ashesDisposition");
					
					String remainsCarryBeCost=getAllServiceDao.getRemainsCarryCost(deadId)+"";			//遗体接运用车费用 sum(carBeCost)
					String remainsCarryRealCost=getAllServiceDao.getRemainsCarryRealCost(deadId)+"";
					
					String rentCrystalBeCost=getAllServiceDao.getRentCoffinCost(deadId)+"";				//租棺部分用棺费用 sum(beRentCost)
					String rentCrystalRealCost=getAllServiceDao.getRentCoffinRealCost(deadId)+"";
					
					String rentCrystalCarBeCost=getAllServiceDao.getRentCrystalCarCost(deadId)+"";		//租棺部分用车费用 sum(carBeCost)
					String rentCrystalCarRealCost=getAllServiceDao.getRentCrystalCarRealCost(deadId)+"";
					
					String watchSpiritVillaBeCost=getAllServiceDao.getWatchSpiritCost(deadId)+"";		//守灵部分别墅费用 sum(villaBeCost)
					String watchSpiritVillaRealCost=getAllServiceDao.getWatchSpiritRealCost(deadId)+"";
					
					String watchSpiritCoffinBeCost=getAllServiceDao.getCoffinBeCost(deadId)+"";	//别墅内部棺材费用 sum(coffinBeCost)
					String watchSpiritCoffinRealCost=getAllServiceDao.getCoffinRealCost(deadId)+"";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"benefitTime\":\"" + benefitTime +"\", \"ashesDisposition\":\"" + ashesDisposition + "\", \"deadResidence\":\"" + deadResidence + "\", \"operatorRelation\":\"" + operatorRelation + "\", \"pathogeny\":\"" + pathogeny + "\", \"watchSpiritCoffinRealCost\":\"" + watchSpiritCoffinRealCost + "\", \"watchSpiritVillaRealCost\":\"" + watchSpiritVillaRealCost + "\", \"rentCrystalCarRealCost\":\"" + rentCrystalCarRealCost + "\", \"rentCrystalRealCost\":\"" + rentCrystalRealCost + "\", \"remainsCarryRealCost\":\"" + remainsCarryRealCost + "\", \"watchSpiritCoffinBeCost\":\"" + watchSpiritCoffinBeCost + "\", \"rentCrystalCarBeCost\":\"" + rentCrystalCarBeCost + "\", \"deadSex\":\"" + deadSex + "\", \"deadAge\":\"" + deadAge + "\", \"rentCrystalBeCost\":\"" + rentCrystalBeCost + "\", \"watchSpiritVillaBeCost\":\"" + watchSpiritVillaBeCost + "\", \"remainsCarryBeCost\":\"" + remainsCarryBeCost + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"dealerId\":\"" + rs.getString("dealerId") + "\", \"dealerName\":\"" + rs.getString("dealerName") + "\", \"dealerAddress\":\"" + rs.getString("dealerAddress") + "\", \"directorName\":\"" + rs.getString("directorName") + "\", \"deadTime\":\"" + deadTime + "\", \"deadType\":\"" + rs.getString("deadType") + "\", \"deadReason\":\"" + rs.getString("deadReason") + "\", \"address\":\"" + rs.getString("address") + "\", \"area\":\"" + rs.getString("area") + "\", \"proofUnit\":\"" + rs.getString("proofUnit") + "\", \"memo\":\"" + rs.getString("memo") + "\", \"invoiceNo\":\"" + rs.getString("invoiceNo") + "\", \"subsidyNo\":\"" + rs.getString("subsidyNo") + "\", \"subsidyMoney\":\"" + rs.getInt("subsidyMoney") + "\", \"flag\":\"" + flag+"\"" ;
					
//					System.out.println(returnString);
				}
				else
				{
					this.memberMobile="";
					this.deadNumber=0;
					this.inTime="";
					this.flag="0";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"";
				}
				rs.close();
				ps.close();
				
				this.findOrderNo(deadId);
				if(conn!=null)
				{
					String sql2="select orderNumber,cremationTypeNo,cremationItemNo from deadorderservice where orderNumber=?";	
					ResultSet rs2=null;
					try
					{
						PreparedStatement ps2=conn.prepareStatement(sql2);
						ps2.setString(1, orderNoresult);
						rs2=ps2.executeQuery();
						while(rs2.next())
						{
							this.returnString=returnString+",\"A"+rs2.getString("cremationTypeNo")+"\":\""+rs2.getString("cremationItemNo")+"\"";
						}
						rs2.close();
						ps2.close();

					}
					catch(SQLException e)
					{
						e.printStackTrace();
						returnString="数据库操作失败！";
						return returnString;
					}
				}
				
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		this.returnString=returnString+"}";
		return returnString;
	}
	
	public String showOrderDeadInfoDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		GetAllServiceService getAllServiceDao=new GetAllServiceService();
		if(conn!=null){
			String sql = "select deadName,benefitTime,deadSex,deadAge,remainsOrderNumber,memberMobile,inTime,dealerId,dealerName,dealerAddress,directorName,deadTime,deadType,deadReason,address,area,proofUnit,memo,invoiceNo,subsidyNo,subsidyMoney,deadResidence,operatorRelation,ashesDisposition,pathogeny from remainsIn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					this.memberMobile=rs.getString("memberMobile");
					this.deadNumber=rs.getInt("remainsOrderNumber");
					
					
					Date date=new Date();
					Timestamp timestamp = rs.getTimestamp("inTime");
					date=new java.util.Date(timestamp.getTime());
					this.inTime=formatter.format(date);
					
					Date date2=new Date();
//					Timestamp timestamp2=rs.getTimestamp("deadTime");
//					date2=new java.util.Date(timestamp2.getTime());
					date2 = rs.getDate("deadTime");
					String deadTime=formatter.format(date2);
					
					Date date3=new Date();
					Timestamp timestamp3 = rs.getTimestamp("benefitTime");
					date3=new java.util.Date(timestamp3.getTime());
					String benefitTime=formatter.format(date3);
					
					this.deadName=rs.getString("deadName");
					this.flag="1";
					String deadAge=rs.getString("deadAge");
					String deadSex=rs.getString("deadSex");
					String pathogeny=rs.getString("pathogeny");
					String operatorRelation=rs.getString("operatorRelation");
					String deadResidence=rs.getString("deadResidence");
					String ashesDisposition=rs.getString("ashesDisposition");
					
					String remainsCarryBeCost=getAllServiceDao.getRemainsCarryCost(deadId)+"";			//遗体接运用车费用 sum(carBeCost)
					String remainsCarryRealCost=getAllServiceDao.getRemainsCarryRealCost(deadId)+"";
					
					String rentCrystalBeCost=getAllServiceDao.getRentCoffinCost(deadId)+"";				//租棺部分用棺费用 sum(beRentCost)
					String rentCrystalRealCost=getAllServiceDao.getRentCoffinRealCost(deadId)+"";
					
					String rentCrystalCarBeCost=getAllServiceDao.getRentCrystalCarCost(deadId)+"";		//租棺部分用车费用 sum(carBeCost)
					String rentCrystalCarRealCost=getAllServiceDao.getRentCrystalCarRealCost(deadId)+"";
					
					String watchSpiritVillaBeCost=getAllServiceDao.getWatchSpiritCost(deadId)+"";		//守灵部分别墅费用 sum(villaBeCost)
					String watchSpiritVillaRealCost=getAllServiceDao.getWatchSpiritRealCost(deadId)+"";
					
					String watchSpiritCoffinBeCost=getAllServiceDao.getCoffinBeCost(deadId)+"";	//别墅内部棺材费用 sum(coffinBeCost)
					String watchSpiritCoffinRealCost=getAllServiceDao.getCoffinRealCost(deadId)+"";
					
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"benefitTime\":\"" + benefitTime +"\", \"ashesDisposition\":\"" + ashesDisposition + "\", \"deadResidence\":\"" + deadResidence + "\", \"operatorRelation\":\"" + operatorRelation + "\", \"pathogeny\":\"" + pathogeny + "\", \"watchSpiritCoffinRealCost\":\"" + watchSpiritCoffinRealCost + "\", \"watchSpiritVillaRealCost\":\"" + watchSpiritVillaRealCost + "\", \"rentCrystalCarRealCost\":\"" + rentCrystalCarRealCost + "\", \"rentCrystalRealCost\":\"" + rentCrystalRealCost + "\", \"remainsCarryRealCost\":\"" + remainsCarryRealCost + "\", \"watchSpiritCoffinBeCost\":\"" + watchSpiritCoffinBeCost + "\", \"rentCrystalCarBeCost\":\"" + rentCrystalCarBeCost + "\", \"deadSex\":\"" + deadSex + "\", \"deadAge\":\"" + deadAge + "\", \"rentCrystalBeCost\":\"" + rentCrystalBeCost + "\", \"watchSpiritVillaBeCost\":\"" + watchSpiritVillaBeCost + "\", \"remainsCarryBeCost\":\"" + remainsCarryBeCost + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"dealerId\":\"" + rs.getString("dealerId") + "\", \"dealerName\":\"" + rs.getString("dealerName") + "\", \"dealerAddress\":\"" + rs.getString("dealerAddress") + "\", \"directorName\":\"" + rs.getString("directorName") + "\", \"deadTime\":\"" + deadTime + "\", \"deadType\":\"" + rs.getString("deadType") + "\", \"deadReason\":\"" + rs.getString("deadReason") + "\", \"address\":\"" + rs.getString("address") + "\", \"area\":\"" + rs.getString("area") + "\", \"proofUnit\":\"" + rs.getString("proofUnit") + "\", \"memo\":\"" + rs.getString("memo") + "\", \"invoiceNo\":\"" + rs.getString("invoiceNo") + "\", \"subsidyNo\":\"" + rs.getString("subsidyNo") + "\", \"subsidyMoney\":\"" + rs.getInt("subsidyMoney") + "\", \"flag\":\"" + flag+"\"" ;
//					System.out.println(returnString);
				}
				else
				{
					this.memberMobile="";
					this.deadNumber=0;
					this.inTime="";
					this.flag="0";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"";
				}
				rs.close();
				ps.close();
				
				this.findOrderNo(deadId);
				if(conn!=null)
				{
					String sql2="select orderNumber,cremationTypeNo,cremationItemNo from deadorderservice where orderNumber=?";	
					ResultSet rs2=null;
					try
					{
						PreparedStatement ps2=conn.prepareStatement(sql2);
						ps2.setString(1, orderNoresult);
						rs2=ps2.executeQuery();
						while(rs2.next())
						{
							this.returnString=returnString+",\"A"+rs2.getString("cremationTypeNo")+"\":\""+rs2.getString("cremationItemNo")+"\"";
						}
						rs2.close();
						ps2.close();

					}
					catch(SQLException e)
					{
						e.printStackTrace();
						returnString="数据库操作失败！";
						return returnString;
					}
				}
				
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
				return returnString;
			}
		}
		this.returnString=returnString+"}";
		return returnString;
	}
	
	public String showAllFuneralGoodsDao(){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select * from funeralgoods order by goodsID ASC";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("goodsName", rs.getString("goodsName"));
					jsonObject.put("goodsBeCost", rs.getInt("goodsBeCost"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
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
		return returnString;
	}
	
	public String showSetGoodsDetailDao(String setName){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select setGoods,setGoodsPrice from setgoods where setName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, setName);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("setGoods", rs.getString("setGoods"));
					jsonObject.put("setGoodsPrice", rs.getInt("setGoodsPrice"));
					jsonArray.add(jsonObject);
				}
				this.returnString=jsonArray.toString();
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
		return returnString;
	}
	
	public String showSetServiceDetailDao(String setName){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select furnace,furnacePrice,farewellRoom,farewellRoomPrice from setservice where setName=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, setName);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					String furnace=rs.getString("furnace");
					String furnacePrice=rs.getInt("furnacePrice")+"";
					String farewellRoom=rs.getString("farewellRoom");
					String farewellRoomPrice=rs.getInt("farewellRoomPrice")+"";
					returnString="{\"furnace\":\"" + furnace + "\", \"furnacePrice\":\"" + furnacePrice + "\", \"farewellRoom\":\"" + farewellRoom + "\", \"farewellRoomPrice\":\"" + farewellRoomPrice + "\"}";
				}
				else{
					this.returnString="";
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
		return returnString;
	}
	
	public String showUrnDao(){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select * from urn";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("urnName", rs.getString("urnName"));
					jsonObject.put("urnBeCost", rs.getInt("urnBeCost"));
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
	
	public String getLeaveRoomDao(){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select itemName,itemNo from cremationserviceitem where typeNo=02";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("itemName", rs.getString("itemName"));
					jsonObject.put("itemNo", rs.getString("itemNo"));
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
	
	public String getSetServiceDao(){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select distinct(setName) from setService";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				JSONArray jsonArray=new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("setName", rs.getString("setName"));
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
	
	public void findOrderNo(String deadId)   //用于主方法中调用，来获取身份证对应的预约编号
	{
		String resultString="";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql="select deadId,orderNumber from deadorderbasic where deadID=?";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				System.out.println(rs.toString());
				JSONArray jsonArray=new JSONArray();
				JSONObject jsonObject=new JSONObject();
				if(rs.next()){
				jsonObject.put("deadId", rs.getString("deadId"));
				jsonObject.put("orderNumber", rs.getString("orderNumber"));
				jsonArray.add(jsonObject);
				orderNoresult=rs.getString("orderNumber");
//				returnString=jsonArray.toString();
			}
				else
				{
					System.out.println("数据库中无此记录！");
				}
		}
			catch(SQLException e){
				e.printStackTrace();
				returnString="数据库操作失败！";
//				return returnString;
			}
	}
//		System.out.println(returnString);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType("text/html"); // 火狐浏览器必须加上这句
//		response.setCharacterEncoding("UTF-8");
//		return returnString;
	}
	
}
