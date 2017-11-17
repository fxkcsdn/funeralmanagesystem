package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReeferService extends BaseService
{
    private String returnString;
	
	public String getReefer()
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select * from reefer where bAvailable=1";
			ResultSet rs=null;
			try
			{
				PreparedStatement ps=conn.prepareStatement(sql);		
				String result = "";
				rs=ps.executeQuery();				
				while(rs.next())
				{					 
					 result = result + "{reeferNo:\""+rs.getString("reeferNo")+"\",bAvailable:\""+rs.getString("bAvailable")+"\"},";					 
				}							
				result = result.substring(0, result.length()-1);							
				returnString = "[" + result + "]";				
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

	/**
	 * 修改冰柜状态
	 * @param reeferNo 冰柜号
	 * @return 布尔值，true代表修改成功，false代表修改失败
	 */
	public boolean updateReeferAvailable(Connection conn, String reeferNo) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update reefer set bAvailable=0 where reeferNo=?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, reeferNo);
				int result = ps.executeUpdate();
				ps.close();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
}
