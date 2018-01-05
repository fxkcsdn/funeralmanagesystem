package com.FuneralManage.Service;

import com.FuneralManage.entity.DeadInfo;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

public class DeadInfoRegisterService extends BaseService{    										//遗体登记实现
	
	public String returnString;
	
	public int getMaxRemainsNumber(DeadInfo deadInfo){											//自动产生遗体编号
		int deadNumber;
		deadNumber=1;
		String curDate=deadInfo.inTime;
		String curDay=curDate.substring(0,10);
		String startDate=null;
		String endDate=null;
		startDate = curDay+" 00:00:00";
		endDate = curDay+" 23:59:59";
		
//		String time=formater.format(date);
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			System.out.println("getMaxRemainsNumber中连接数据库成功");
			String sql="SELECT MAX(remainsNumber) FROM remainsIn WHERE inTime BETWEEN STR_TO_DATE('"+startDate+"','%Y-%m-%d %H:%i:%s')AND STR_TO_DATE('"+endDate+"','%Y-%m-%d %H:%i:%s')";
			//从数据库表格中选择出与界面上输入的进馆时间相同的一天中已经登记的遗体的编号的最大值
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					deadNumber=rs.getInt(1)+1;
				}else{
					deadNumber=1;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("获取最大遗体编号失败");
			}
		}
		return deadNumber;
	}
	
	public int getMaxOrderRemainsNumber(DeadInfo deadInfo){											//自动产生yuyue遗体编号
		int deadNumber;
		deadNumber=0;
		String curDate=deadInfo.inTime;
		String curDay=curDate.substring(0,10);
		String startDate=null;
		String endDate=null;
		startDate = curDay+" 00:00:00";
		endDate = curDay+" 23:59:59";
		
//		String time=formater.format(date);
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			System.out.println("getMaxRemainsNumber中连接数据库成功");
			String sql="SELECT MAX(remainsOrderNumber) FROM remainsIn WHERE inTime BETWEEN STR_TO_DATE('"+startDate+"','%Y-%m-%d %H:%i:%s')AND STR_TO_DATE('"+endDate+"','%Y-%m-%d %H:%i:%s')";
			//从数据库表格中选择出与界面上输入的进馆时间相同的一天中已经登记的遗体的编号的最大值
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					deadNumber=rs.getInt(1)+1;
				}else{
					deadNumber=1;
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("获取最大遗体编号失败");
			}
		}
		return deadNumber;
	}
	
	public String deadInfoRegister(DeadInfo deadInfo){							//登记遗体信息，将前台传过来的信息放入数据库表格
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			System.out.print("deadInfoRegister中数据库连接成功");
			String sql = "insert into remainsIn(deadID,deadName,deadSex,memberMobile,remainsNumber,deadType,deadAge,inTime,address,proofUnit,memo,deadReason,deadTime,area,dealerId,dealerName,dealerAddress,directorName,pathogeny,operatorRelation,deadResidence,ashesDisposition,benefitTime,remainsOrderNumber)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				System.out.println(deadInfo.deadId);
				ps.setString(1, deadInfo.deadId);
				ps.setString(2, deadInfo.deadName);
				ps.setString(3, deadInfo.deadSex);
				ps.setString(4, deadInfo.memberMobile);
				ps.setInt(5, deadInfo.deadNumber);
				ps.setString(6, "");
				ps.setInt(7, deadInfo.deadAge);
				ps.setString(8, deadInfo.inTime+"");
				
				ps.setString(9, deadInfo.deadAddress);
				ps.setString(10, "");
				ps.setString(11, deadInfo.deadExtraInfo);
				ps.setString(12, "");
				ps.setString(13, "0001-01-01 00:00:00");
				ps.setString(14, "");
				ps.setString(15, "");
				ps.setString(16, "");
				ps.setString(17, "");
				ps.setString(18, "");
				ps.setString(19, "");
				ps.setString(20, "");
				ps.setString(21, "");
				ps.setString(22, "");
				ps.setString(23, "0001-01-01 00:00:00");
				ps.setInt(24, 0);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "行数据！";
				}
				else
				{
					returnString="数据库操作失败!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				returnString="数据库操作失败!";
				e.printStackTrace();
				return returnString;
			}
		}
		else
		{
			returnString="数据库操作失败!";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
	}
	
	public String orderDeadInfoRegister(DeadInfo deadInfo){							//登记yuyue遗体信息，将前台传过来的信息放入数据库表格
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null){
			System.out.print("deadInfoRegister中数据库连接成功");
			String sql = "insert into remainsIn(deadID,deadName,deadSex,memberMobile,remainsOrderNumber,deadType,deadAge,inTime,address,proofUnit,memo,deadReason,deadTime,area,dealerId,dealerName,dealerAddress,directorName,pathogeny,operatorRelation,deadResidence,ashesDisposition,benefitTime,remainsNumber)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadInfo.deadId);
				ps.setString(2, deadInfo.deadName);
				ps.setString(3, deadInfo.deadSex);
				ps.setString(4, "");
				ps.setInt(5, deadInfo.deadNumber);
				ps.setString(6, "");
				ps.setInt(7, deadInfo.deadAge);
				ps.setString(8, deadInfo.inTime+"");
				ps.setString(9, deadInfo.deadAddress);
				ps.setString(10, "");
				ps.setString(11, "");
				ps.setString(12, "");
				ps.setString(13, "0001-01-01 00:00:00");
				ps.setString(14, "");
				ps.setString(15, "");
				ps.setString(16, "");
				ps.setString(17, "");
				ps.setString(18, "");
				ps.setString(19, "");
				ps.setString(20, "");
				ps.setString(21, "");
				ps.setString(22, "");
				ps.setString(23, "0001-01-01 00:00:00");
				ps.setInt(24, 0);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="成功加入了" + row + "行数据！";
				}
				else
				{
					returnString="数据库操作失败!";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				returnString="挂号失败，检查是否重复挂号";
				e.printStackTrace();
				return returnString;
			}
		}
		else
		{
			returnString="数据库操作失败!";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html"); // 火狐浏览器必须加上这句
		response.setCharacterEncoding("UTF-8");
		return returnString;
	}
	
	public String alterDeadInvoiceAndSubsidyInfo(DeadInfo deadInfo, String invoiceNo, String subsidyNo, String subsidyMoney,String benefitTime){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null)
		{
			String sql = "update remainsin set invoiceNo=?, subsidyNo=?, subsidyMoney=?, dealerId=?, dealerName=?, directorName=?, dealerAddress=?, deadTime=?, deadType=?, deadReason=?, address=?, area=?, proofUnit=?, memo=?, memberMobile=?, deadSex=?, deadAge=?, deadName=?, pathogeny=?, operatorRelation=?, deadResidence=?, ashesDisposition=?,benefitTime=? where deadId=?";
			try
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, invoiceNo);
				ps.setString(2, subsidyNo);
				ps.setInt(3, Integer.parseInt(subsidyMoney));				
				ps.setString(4, deadInfo.dealerId);
				ps.setString(5, deadInfo.dealerName);
				ps.setString(6, deadInfo.directorName);
				ps.setString(7, deadInfo.dealerAddress);
				ps.setString(8, deadInfo.deadTime);
				ps.setString(9, deadInfo.deadKind);
				ps.setString(10, deadInfo.deadReason);
				ps.setString(11, deadInfo.deadAddress);
				ps.setString(12, deadInfo.area);
				ps.setString(13, deadInfo.deadProveUnit);
				ps.setString(14, deadInfo.deadExtraInfo);
				ps.setString(15, deadInfo.memberMobile);
				ps.setString(16, deadInfo.deadSex);
				ps.setInt(17, deadInfo.deadAge);
				ps.setString(18, deadInfo.deadName);				
				ps.setString(19, deadInfo.pathogeny);
				ps.setString(20, deadInfo.operatorRelation);
				ps.setString(21, deadInfo.deadResidence);
				ps.setString(22, deadInfo.ashesDisposition);
				ps.setString(23,benefitTime);			
				ps.setString(24, deadInfo.deadId);
				row = ps.executeUpdate();
				if (row > 0) 
				{
					returnString="完善基本信息、惠民和发票号成功！";
				}
				else
				{
					returnString="完善基本信息、惠民和发票号失败";
				}
				ps.close();
				conn.close();
			}
			catch(SQLException e)
			{
				returnString="数据库操作失败";
				e.printStackTrace();				
			}
		}
		else
		{
			returnString="数据库连接失败";
		}		
		return returnString;
	}
	public String updateDeadId(String wrongDeadId,String latestDeadId) throws SQLException {
		Connection conn = DBDao.openDateBase("dongtai");
		PreparedStatement ps1 =null;
		PreparedStatement ps2 =null;
		PreparedStatement ps3 =null;
		PreparedStatement ps4 =null;
		if(conn!=null){
			try {
				conn.setAutoCommit(false);
				String sql1 = "update remainsIn set deadId=? where deadId=?";
				String Sql2 = "update deadchosenurn set deadId=? where deadId=?";
				String Sql3 = "update deadfuneralgoods set deadId=? where deadId=?";
				String Sql4 = "update deadserviceitem set deadId=? where deadId=?";
				ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, latestDeadId);
				ps1.setString(2, wrongDeadId);
				ps1.executeUpdate();
				
				ps2 = conn.prepareStatement(Sql2);
				ps2.setString(1, latestDeadId);
				ps2.setString(2, wrongDeadId);
				ps2.executeUpdate();
				
				ps3 = conn.prepareStatement(Sql3);
				ps3.setString(1, latestDeadId);
				ps3.setString(2, wrongDeadId);
				ps3.executeUpdate();
				
				ps4 = conn.prepareStatement(Sql4);
				ps4.setString(1, latestDeadId);
				ps4.setString(2, wrongDeadId);
				ps4.executeUpdate();
				conn.commit();
						
				returnString=latestDeadId;
			} catch (Exception e) {
				conn.rollback();
				returnString="修改失败,请检查输入错误的逝者身份证号是否正确";
				return returnString;
			}finally{
				ps1.close();
				ps2.close();
				ps3.close();
				ps4.close();
				conn.close();
			}
		}
		
		return returnString;
	}
	public String getCurrDeadNumber()
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		String startDate=null;
		String endDate=null;
		startDate = dateString+" 00:00:00";
		endDate = dateString+" 23:59:59";
		
//		String time=formater.format(date);
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="SELECT MAX(remainsNumber) FROM remainsIn WHERE inTime BETWEEN STR_TO_DATE('"+startDate+"','%Y-%m-%d %H:%i:%s')AND STR_TO_DATE('"+endDate+"','%Y-%m-%d %H:%i:%s')";
			//从数据库表格中选择出与界面上输入的进馆时间相同的一天中已经登记的遗体的编号的最大值
			ResultSet rs=null;
			try{
				JSONArray array = new JSONArray();
				JSONObject object = new JSONObject();
				PreparedStatement ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1){
					int deadNumber=rs.getInt(1)+1;
					
					object.put("deadNumber", deadNumber);
					array.add(object);
					returnString=array.toString();
					
				}else{
					
					object.put("deadNumber", 1);
					array.add(object);
					returnString=array.toString();
				}
				rs.close();
				ps.close();
				conn.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				System.out.println("获取最大遗体编号失败");
			}
		}
		return returnString;
	}
}
