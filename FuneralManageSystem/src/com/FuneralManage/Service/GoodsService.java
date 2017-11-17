package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsService {
	private String returnString;// 返回的字符串数据
	
	/**
	 * 获取商品种类数据
	 * @return 商品种类
	 */
	public String getGoodsTypes()
	{
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select distinct goodsType from goods";
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
					result = result + "{goodsType:\"" + rs.getString("goodsType") + "\"},";
				}
				// 去掉最后一个逗号
				result = result.substring(0, result.length() - 1);
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
	
	/**
	 * 获取品名
	 * @param goodsType 商品种类
	 * @return 品名
	 */
	public String getGoodsNames(String goodsType)
	{
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select distinct goodsName from goods where goodsType=?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, goodsType);
				// 执行sql语句
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					result = result + "{goodsName:\"" + rs.getString("goodsName") + "\"},";
				}
				// 去掉最后一个逗号
				result = result.substring(0, result.length() - 1);
				returnString = "[" + result + "]";
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
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

	/**
	 * 获取单位
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @return 单位
	 */
	public String getUnit(String goodsType, String goodsName) 
	{
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select distinct unit from goods where goodsType=? and goodsName=?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try
			{
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, goodsType);
				ps.setString(2, goodsName);
				// 执行sql语句
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					result = result + "{unit:\"" + rs.getString("unit") + "\"},";
				}
				// 去掉最后一个逗号
				result = result.substring(0, result.length() - 1);
				returnString = "[" + result + "]";
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
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
