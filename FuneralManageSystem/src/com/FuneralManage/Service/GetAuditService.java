package com.FuneralManage.Service;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


public class GetAuditService extends BaseService
{
	public JSONObject findUrn(String startTime,String endTime) throws SQLException  //统计骨灰盒
	{
		int totalCost=0; //用于汇总骨收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")  //传进来只有一个开始时间，则执行查询当天的信息
		{
			
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadchosenurn LEFT JOIN remainsin ON deadchosenurn.deadId=remainsin.deadID WHERE remainsin.inTime LIKE ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("urnRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else  //根据开始时间和结束时间查询
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadchosenurn LEFT JOIN remainsin ON deadchosenurn.deadId=remainsin.deadID WHERE remainsin.inTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, startTimePara);
				ps.setString(2, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("urnRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("urnCou", count);
		jsonObject.put("urnCos", totalCost);
		return jsonObject;
	}
	public JSONObject findTopFire(String startTime,String endTime,String cremationTypeNo,String cremationItemNo) throws SQLException  //统计火化、美容、告别
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")  //传进来只有一个开始时间，则执行查询当天的信息
		{	
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadserviceitem LEFT JOIN remainsin ON deadserviceitem.deadID=remainsin.deadID WHERE remainsin.inTime LIKE ? AND deadserviceitem.CremationTypeNo=? AND deadserviceitem.CremationItemNo=?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				//ps.setString(1, "2016-08-18%");
				ps.setString(1, datePara);
				ps.setString(2, cremationTypeNo);
				ps.setString(3, cremationItemNo);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("itemRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadserviceitem LEFT JOIN remainsin ON deadserviceitem.deadID=remainsin.deadID WHERE deadserviceitem.CremationTypeNo=? AND deadserviceitem.CremationItemNo=? AND remainsin.inTime between ? and ? ";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, cremationTypeNo);
				ps.setString(2, cremationItemNo);
				ps.setString(3, startTimePara);
				ps.setString(4, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("itemRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("iCou", count);
		jsonObject.put("iCos", totalCost);
		return jsonObject;
	}
	public JSONObject findGoods(String startTime,String endTime,String goodsName) throws SQLException   //统计小商品
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadfuneralgoods LEFT JOIN remainsin ON deadfuneralgoods.deadID=remainsin.deadID WHERE remainsin.inTime LIKE ? AND deadfuneralgoods.goodsName=?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				ps.setString(2, goodsName);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("goodsRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else 
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadfuneralgoods LEFT JOIN remainsin ON deadfuneralgoods.deadID=remainsin.deadID WHERE deadfuneralgoods.goodsName=? AND remainsin.inTime between ?  and ? ";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, goodsName);
				ps.setString(2, startTimePara);
				ps.setString(3, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("goodsRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("Cou", count);
		jsonObject.put("Cos", totalCost);
		return jsonObject;
	}
	public JSONObject findService(String startTime,String endTime,String typeNo,String itemNo) throws SQLException //统计人工服务
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadchosenaddservice LEFT JOIN remainsin ON deadchosenaddservice.deadID=remainsin.deadID WHERE remainsin.inTime LIKE ? AND deadchosenaddservice.typeNo=? and deadchosenaddservice.itemNo=?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				ps.setString(2, typeNo);
				ps.setString(3, itemNo);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("serviceRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM deadchosenaddservice LEFT JOIN remainsin ON deadchosenaddservice.deadID=remainsin.deadID WHERE deadchosenaddservice.typeNo=? and deadchosenaddservice.itemNo=? AND remainsin.inTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, typeNo);
				ps.setString(2, itemNo);
				ps.setString(3, startTimePara);
				ps.setString(4, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("serviceRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("sCou", count);
		jsonObject.put("sCos", totalCost);
		return jsonObject;
	}
	public JSONObject findRemainsCarryCar(String startTime,String endTime,String status) throws SQLException //统计遗体接运车
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM remainscarry LEFT JOIN remainsin ON remainscarry.deadID=remainsin.deadID WHERE remainsin.inTime LIKE ? AND remainscarry.bInternalCar=?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				ps.setString(2, status);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("carRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT * FROM remainscarry LEFT JOIN remainsin ON remainscarry.deadID=remainsin.deadID WHERE remainscarry.bInternalCar=? AND remainsin.inTime between ? AND ? ";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, status);
				ps.setString(2, startTimePara);
				ps.setString(3, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{					 
					 realCost=rs.getInt("carRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("carCou", count);
		jsonObject.put("carCos", totalCost);
		return jsonObject;
	}
	public JSONObject findRentCoffinCarryCar(String startTime,String endTime) throws SQLException //统计送棺车
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT carRealCost FROM rentcoffin WHERE returnTime LIKE ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("carRealCost");
					 System.out.println(realCost);
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else 
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT carRealCost FROM rentcoffin WHERE startTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, startTimePara);
				ps.setString(2, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("carRealCost");
					 System.out.println(realCost);
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("carCou", count);
		jsonObject.put("carCos", totalCost);
		return jsonObject;
	}
	public JSONObject findRentCoffin(String startTime,String endTime) throws SQLException //统计还棺
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT realRentCost FROM rentcoffin WHERE returnTime LIKE ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("realRentCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else 
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT realRentCost FROM rentcoffin WHERE returnTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, startTimePara);
				ps.setString(2, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("realRentCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("coffinCou", count);
		jsonObject.put("coffinCos", totalCost);
		return jsonObject;
	}
	
	public JSONObject findReefer(String startTime,String endTime) throws SQLException //统计冷藏
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT reeferRealCost FROM remainsreefer WHERE endTime LIKE ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("reeferRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT reeferRealCost FROM remainsreefer WHERE endTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, startTimePara);
				ps.setString(2, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("reeferRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("reeferCou", count);
		jsonObject.put("reeferCos", totalCost);
		return jsonObject;
	}
	
	public JSONObject findWatch(String startTime,String endTime) throws SQLException //统计守灵
	{
		int totalCost=0; //用于汇总收入
		int count = 0;   //用于统计个数
		int realCost=0;  //用于取出每个实收
		String datePara = startTime+"%";   //模糊查询用的时间参数
		String startTimePara = startTime+" 00:00:00";  //查询用的开始时间参数
		String endTimePara = endTime+" 23:59:59";  //查询用的结束时间参数
		JSONObject jsonObject=new JSONObject();
		if(endTime=="")
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT villaRealCost FROM watchspirit WHERE endTime LIKE ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, datePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("villaRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		else 
		{
			Connection conn=DBDao.openDateBase("dongtai");
			if(conn !=null)
			{
				String sql = "SELECT villaRealCost FROM watchspirit WHERE endTime between ? and ?";
				ResultSet rs=null;
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, startTimePara);
				ps.setString(2, endTimePara);
				rs=ps.executeQuery();
				while(rs.next())
				{
					 realCost=rs.getInt("villaRealCost");
					 totalCost = totalCost+realCost;
					 ++count;
				}
				rs.close();
				ps.close();
				conn.close();
			}
		}
		jsonObject.put("villaCou", count);
		jsonObject.put("villaCos", totalCost);
		return jsonObject;
	}
}
