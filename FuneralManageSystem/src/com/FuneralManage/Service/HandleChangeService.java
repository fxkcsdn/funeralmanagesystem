package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleChangeService extends BaseService{
	public int updateInfo(String carryNumber,String deadId)
	{
		int result=0;
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn !=null){
			String sql="UPDATE remainscarry SET deadID=? WHERE carryNumber=?";
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, carryNumber);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					conn=null;
				}
			}
		}
		return result;
	}
}
