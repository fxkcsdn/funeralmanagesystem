package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.FuneralManage.Dao.ReeferDAO;
import com.FuneralManage.Dao.ReeferRemainsCarryDAO;
import com.FuneralManage.Dao.ReeferRemainsSendDAO;
import com.FuneralManage.Dao.ReeferServiceConsumeInfoDAO;
import com.FuneralManage.Dao.RemainsReeferDAO;
import com.FuneralManage.Exception.MyException;
import com.FuneralManage.entity.ReeferRemainsCarry;
import com.FuneralManage.entity.ReeferRemainsSend;
import com.FuneralManage.entity.RemainsReefer;
import com.FuneralManage.Utility.TransactionManager;

public class RemainsReeferService extends BaseService{
	private TransactionManager transactionManager=new TransactionManager(dataSource);
	private RemainsReeferDAO remainsReeferDAO=new RemainsReeferDAO(dataSource);
	private ReeferRemainsSendDAO reeferRemainsSendDAO=new ReeferRemainsSendDAO(dataSource);
	private ReeferRemainsCarryDAO reeferRemainsCarryDAO=new ReeferRemainsCarryDAO(dataSource);
	private ReeferServiceConsumeInfoDAO reeferServiceConsumeInfoDAO=new ReeferServiceConsumeInfoDAO(dataSource);
	private ReeferDAO reeferDAO=new ReeferDAO(dataSource);
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
	
	/**
	 * 保存遗体冷藏信息
	 * @param reeferNumber 冷藏编号
	 * @param carryNumber 接运编号
	 * @param deadId 逝者身份证号
	 * @param contactMobile 经办人手机
	 * @param contactName 经办人姓名
	 * @param sendRemainsUnit 送尸单位
	 * @param arrivalTime 到馆时间
	 * @param familyName 家属姓名
	 * @param reeferNo 冰柜号
	 * @param accidentAddress 事故地址
	 * @param deposit 押金 
	 * @param staffName 业务员姓名
	 * @param familyMobile 家属电话
	 * @param memo 备注
	 * @return 布尔值，true为保存成功，false为保存失败
	 */
	public boolean addRemainsReefer(Connection conn, String reeferNumber, String carryNumber,
			String deadId, String contactMobile, String contactName,
			String sendRemainsUnit, String arrivalTime, String familyName,
			String reeferNo, String accidentAddress, int deposit,
			String staffName, String familyMobile, String memo) 
	{
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "insert into remainsReefer(reeferNumber,carryNumber,deadId,contactMobile,"
					+ "contactName,sendRemainsUnit,arrivalTime,familyName,reeferNo,accidentAddress,deposit,"
					+ "staffName,familyMobile,memo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, reeferNumber);
				ps.setString(2, carryNumber);
				ps.setString(3, deadId);
				ps.setString(4, contactMobile);
				ps.setString(5, contactName);
				ps.setString(6, sendRemainsUnit);
				ps.setString(7, arrivalTime);
				ps.setString(8, familyName);
				ps.setString(9, reeferNo);
				ps.setString(10, accidentAddress);
				ps.setInt(11, deposit);
				ps.setString(12, staffName);
				ps.setString(13, familyMobile);
				ps.setString(14, memo);
				// 执行sql语句
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * 根据冰柜号获取冷藏编号
	 * @param reeferNo
	 * @return
	 * @throws Exception 
	 */
	public String getReeferNumberByReeferNo(String reeferNo) throws Exception{
		//首先判断该水晶棺是否在使用中,如果已经使用了，则获取对应的冷藏编号
		if(reeferNo==null)
			throw new MyException("冷藏柜不能为空！");	
		boolean state=reeferDAO.getReeferState(reeferNo);
		if(state)
			throw new MyException("该冷藏柜还未使用!");
		return remainsReeferDAO.getReeferNumberByReeferNo(reeferNo);
	}
	
	/**
	 * 根据冰柜号获取相关的应收费用
	 * @param reeferNo
	 * @return
	 * @throws Exception
	 */
	public String getAllBeCostOfReeferRemains(String reeferNo) throws Exception{
		if(reeferNo==null||reeferNo.equals(""))
			throw new MyException("冷藏柜不能为空！");
		boolean state=reeferDAO.getReeferState(reeferNo);
		if(state)
			throw new MyException("该冷藏柜还未使用!");
		RemainsReefer remainsReefer=remainsReeferDAO.getRemainsReeferByReeferNo(reeferNo);
		if(remainsReefer==null)
			throw new MyException("数据库中尚未有此冰柜对应的遗体冷藏信息！");
		String reeferNumber=remainsReefer.getReeferNumber();
		if(reeferNumber==null||reeferNumber.equals(""))
			throw new MyException("该冷藏信息没有冷藏编号，数据库设计问题！");
		String carryNumber=remainsReefer.getCarryNumber();
		//第一步，获取遗体到馆时间
		String arriveTime="";
		if(remainsReefer.getArrivalTime()==null){
			throw new MyException("该冷藏信息没有到馆时间，数据库数据异常！");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		arriveTime = df.format(remainsReefer.getArrivalTime());		
		//第二步，获取接运应收
		String carryBeCost="0";
		if(carryNumber!=null&&!carryNumber.equals("")){
			ReeferRemainsCarry reeferRemainsCarry=reeferRemainsCarryDAO.getBeCostOfRemainCarry(carryNumber);
			if(reeferRemainsCarry!=null&&reeferRemainsCarry.getCarBeCost()!=null)
				carryBeCost=reeferRemainsCarry.getCarBeCost().toString();
		}
		//第三步，获取送运应收
		String sendBeCost="0";
		ReeferRemainsSend reeferRemainsSend =reeferRemainsSendDAO.getBeCostOfRemainSend(reeferNumber);
		if(reeferRemainsSend!=null&&reeferRemainsSend.getCarBeCost()!=null)
			sendBeCost=reeferRemainsSend.getCarBeCost().toString();
		//第四步，获取冷藏服务应收
		String serviceBeCost=reeferServiceConsumeInfoDAO.getBeCostSumOfReeferService(reeferNumber);
		String result="{\"arriveTime\":\""+arriveTime+"\",\"carryBeCost\":\""+carryBeCost+"\","
				+ "\"sendBeCost\":\""+sendBeCost+"\",\"serviceBeCost\":\""+serviceBeCost+"\"}";
		return result;
	}
	
	/**
	 * 冷藏结算
	 * @param remainsReefer
	 * @return
	 */
	public boolean reeferfillOfRemains(RemainsReefer remainsReefer,String reeferNo) throws Exception{
		if(remainsReefer==null)
			throw new MyException("遗体冷藏信息为空！");
		try{
			this.transactionManager.start();
			remainsReeferDAO.reeferfillOfRemainsTran(remainsReefer);
			reeferDAO.updateReeferStateTran(true, reeferNo);
			this.transactionManager.commit();
			return true;
		} catch (Exception e) {
			this.transactionManager.rollback();
			return false;
		} finally {
			this.transactionManager.close();
		}
	}
}
