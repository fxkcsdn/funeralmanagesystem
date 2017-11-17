package com.FuneralManage.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class BreakageService {
	private String returnString;// 返回的字符串数据

	/**
	 * 新增报损信息
	 * @param conn 数据库连接对象
	 * @param map 报损信息
	 * @param breakageNumber 报损单号
	 * @return true代表添加成功，false代表失败
	 */
	public boolean addBreakage(Connection conn, Map<String, String> map,
			String breakageNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "insert into breakage values(?,?,?,?,?,?)";
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, breakageNumber);
				ps.setString(2, map.get("staffName"));
				ps.setString(3, map.get("breakageDate"));
				ps.setString(4, map.get("warehouseName"));
				ps.setString(5, map.get("goodsName"));
				ps.setInt(6, Integer.parseInt(map.get("amount")));
				int result = ps.executeUpdate();
				if (result > 0) return true;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}

}
