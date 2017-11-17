package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetSubsidyInfoService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	public String GetSubsidyInfo(String deadId){
	 
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql="select deadName,dealerName,dealerId,invoiceNo,subsidyMoney from remainsIn where deadId=?";
			ResultSet rs=null;
			try{
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, deadId);
				rs=pst.executeQuery();
				rs.last();
				if(rs.getRow()==1)
				{
					String deadName=rs.getString("deadName");
					String dealerName=rs.getString("dealerName");
					String dealerId=rs.getString("dealerId")+"";
					String invoiceNo=rs.getString("invoiceNo")+"";
					String subsidyMoney=rs.getInt("subsidyMoney")+"";
					
					this.returnString="{\"deadName\":\"" + deadName + "\", \"dealerName\":\"" + dealerName + "\", \"dealerId\":\"" + dealerId + "\", \"invoiceNo\":\"" + invoiceNo+"\",\"subsidyMoney\":\"" + subsidyMoney + "\"}" ;
					
					
				//	this.returnString="{\"deadName\":\"" + deadName + "\", \"deadSex\":\"" + deadSex + "\", \"deadAge\":\"" + deadAge + "\", \"address\":\"" + address+"\",\"deadReason\":\"" + deadReason + "\",\"proofUnit\":\"" + proofUnit + "\",\"deadTime\":\"" + deadTime + "\",\"inTime\":\"" + inTime + "\"}" ;
				}
				else
				{
					this.returnString="获取火化证信息失败！";
				}
				rs.close();
				pst.close();
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
			
		}
		
	


