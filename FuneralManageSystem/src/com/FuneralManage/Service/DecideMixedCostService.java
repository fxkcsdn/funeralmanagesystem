package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DecideMixedCostService extends BaseService{
	
	private String returnString;

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String updateRealCost(String deadId, String remainsCarryRealCost, String watchSpiritVillaRealCost, String watchSpiritCoffinRealCost, String rentCrystalRealCost, String rentCrystalCarRealCost){
		Connection conn=DBDao.openDateBase("dongtai");
		int row=0;
		if(conn!=null)
		{
			String sql1 = "update remainsCarry set carRealCost=? where deadId=?";
			String sql2 = "update watchSpirit set villaRealCost=?, coffinRealCost=? where deadId=?";
			String sql3 = "update rentCoffin set realRentCost=?, carRealCost=? where deadId=?";
			try
			{
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setInt(1, Integer.parseInt(remainsCarryRealCost));
				ps1.setString(2, deadId);
				row = ps1.executeUpdate();
				if (row > 0) 
				{
					returnString="确认费用成功！";
				}
				else
				{
					returnString="确认费用失败1";
				}
				ps1.close();
//				if(returnString=="确认遗体接运费用成功！"){
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setInt(1, Integer.parseInt(watchSpiritVillaRealCost));
				ps2.setInt(2, Integer.parseInt(watchSpiritCoffinRealCost));
				ps2.setString(3, deadId);
				row=ps2.executeUpdate();
				if(row>0){
					returnString="确认费用成功！";
				}
				else{
					returnString="确认费用失败2";
				}
				ps2.close();
//				}
//				if(returnString=="确认守灵费用成功！"){
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setInt(1, Integer.parseInt(rentCrystalRealCost));
				ps3.setInt(2, Integer.parseInt(rentCrystalCarRealCost));
				ps3.setString(3, deadId);
				row=ps3.executeUpdate();
				if(row>0){
					returnString="确认费用成功！";
				}
				else{
					returnString="确认费用失败3"+row;
				}
				ps3.close();
//				}
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
//		if(returnString.equals("确认租棺模块费用成功！")){
//			returnString = "确认以上费用成功！";
//		}
//		else{
//			returnString = "确认费用失败！";
//		}
//		System.out.println(returnString);
		return returnString;
	}
}
