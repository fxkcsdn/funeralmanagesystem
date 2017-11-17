package com.FuneralManage.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeCarryOrderService extends BaseService{
	public String updateInfo(String carryNum,String carNum)
	{
		String retunString="";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null)
		{
			String sql = "UPDATE remainscarry SET carNumber = ? WHERE carryNumber = ?";
			int rs=0;
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, carNum);
				ps.setString(2, carryNum);
				rs=ps.executeUpdate();
				if(rs>0)
				{
					retunString="修改成功！";
				}
				else {
					retunString="修改失败！";
				}
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
		return retunString;
	}
	/**
	 * 删除接运单
	 * @param carryNum
	 * @return
	 */
	public String deleteCarryOrder(String carryNum){
		if(carryNum==null){
			throw new NullPointerException("carryNum cannot null!");
		}
		String retunString="deleteFailure";
		Connection conn=DBDao.openDateBase("dongtai");
		if(conn!=null){
			String sql = "delete from remainscarry  WHERE carryNumber = ?";
			int rs=0;
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1, carryNum);
				rs=ps.executeUpdate();
				if(rs>0){
					retunString="deleteSuccess";
				}
				else {
					retunString="deleteFailure";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					conn.close();
				}catch (SQLException e) {
					e.printStackTrace();
					conn=null;
				}
			}
		}
		return retunString;
	}
}
