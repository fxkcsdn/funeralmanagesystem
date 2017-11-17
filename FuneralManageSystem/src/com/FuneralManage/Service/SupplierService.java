package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierService {
	private String returnString;// 返回的字符串数据
	
	/**
	 * 获取所有厂家信息
	 * @return 厂家信息数据
	 */
	public String getSuppliers()
	{
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		// 连接成功
		if (conn != null)
		{
			String sql = "select distinct supplierName from supplier";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
				String result = "";
				ps = conn.prepareStatement(sql);
				// 执行sql语句
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					result = result + "{supplierName:\"" + rs.getString("supplierName") + "\"},";
				}
				if (result.length() > 0)
				{
					// 去掉最后一个逗号
					result = result.substring(0, result.length() - 1);
				}
				returnString = "[" + result + "]";
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			} finally {
				try {
					// 关闭资源
					if (rs != null) rs.close();
					if (ps != null) ps.close();
					if (conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return returnString;
	}

}
