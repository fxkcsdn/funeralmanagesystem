package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReeferRemainsCarryService {
	private String returnString;// 返回的字符串数据

	/**
	 * 添加接运信息
	 * @param carryNumber 接运编号
	 * @param contactName 联系人姓名
	 * @param contactMobile 联系人电话
	 * @param carryTime 接运时间
	 * @param address 地址
	 * @param carNumber 车牌号
	 * @param bInternalCar 是否本馆车辆
	 * @param carBeCost 预收费用
	 * @return 布尔型，true为保存成功，false为保存失败
	 */
	public boolean addReeferRemainsCarry(String carryNumber,String contactName
			, String contactMobile, String carryTime, String address, String carNumber
			, String bInternalCar, int carBeCost) 
	{
		Connection conn=DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "insert into reeferRemainsCarry values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, carryNumber);
				ps.setString(2, contactName);
				ps.setString(3, contactMobile);
				ps.setString(4, carryTime);
				ps.setString(5, address);
				ps.setString(6, carNumber);
				ps.setBoolean(7, "1".equals(bInternalCar) ? true : false);
				ps.setInt(8, carBeCost);
				// 执行sql语句
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					// 关闭资源
					if (ps != null) ps.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	

}
