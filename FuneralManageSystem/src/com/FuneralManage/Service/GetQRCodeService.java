package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetQRCodeService extends BaseService{				//获取数据库中打印二维码界面中需要的遗体信息
	
	public String inTime;
	public String memberMobile;
	public int deadNumber;
	public String deadName;
	private String flag;		//标记有没有获取到值
	private String returnString;		//返回查询到的字符串数据
	
	public String getDeadName() {
		return deadName;
	}

	public void setDeadName(String deadName) {
		this.deadName = deadName;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQRCodeInfoDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select deadName,deadSex,deadAge,address,remainsNumber,memberMobile,inTime from remainsIn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=new Date();
					Timestamp timestamp = rs.getTimestamp("inTime");
					date=new java.util.Date(timestamp.getTime());
					this.inTime=formatter.format(date);
//					this.inTime=formatter.format(rs.getDate("inTime"));
					
					this.memberMobile=rs.getString("memberMobile");
					this.deadNumber=rs.getInt("remainsNumber");
					this.deadName=rs.getString("deadName");
					String deadSex=rs.getString("deadSex");
					String deadAge=rs.getString("deadAge");
					String address=rs.getString("address");
					this.flag="1";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"address\":\"" + address + "\", \"deadAge\":\"" + deadAge + "\", \"deadSex\":\"" + deadSex + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"}";
				}
				else
				{
					this.memberMobile="";
					this.deadNumber=0;
					this.inTime="";
					this.flag="0";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"}";
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
	
	public String getQRCodeOrderInfoDao(String deadId){
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "select deadName,deadSex,deadAge,address,remainsOrderNumber,memberMobile,inTime from remainsIn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				rs=ps.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=new Date();
					Timestamp timestamp = rs.getTimestamp("inTime");
					date=new java.util.Date(timestamp.getTime());
					this.inTime=formatter.format(date);
//					this.inTime=formatter.format(rs.getDate("inTime"));
					
					this.memberMobile=rs.getString("memberMobile");
					this.deadNumber=rs.getInt("remainsOrderNumber");
					this.deadName=rs.getString("deadName");
					String deadSex=rs.getString("deadSex");
					String deadAge=rs.getString("deadAge");
					String address=rs.getString("address");
					this.flag="1";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"address\":\"" + address + "\", \"deadAge\":\"" + deadAge + "\", \"deadSex\":\"" + deadSex + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"}";
				}
				else
				{
					this.memberMobile="";
					this.deadNumber=0;
					this.inTime="";
					this.flag="0";
					this.returnString="{\"deadNumber\":\"" + deadNumber + "\", \"memberMobile\":\"" + memberMobile + "\", \"deadName\":\"" + deadName + "\", \"inTime\":\"" + inTime + "\", \"flag\":\"" + flag + "\"}";
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
