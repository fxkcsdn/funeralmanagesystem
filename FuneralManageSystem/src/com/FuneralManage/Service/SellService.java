package com.FuneralManage.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SellService {
	private String returnString;// 返回的字符串数据

	/**
	 * 新增销售信息
	 * @param conn 数据库连接对象
	 * @param map 销售信息
	 * @param sellNumber 销售单号
	 * @return true代表新增成功，false代表失败
	 */
	public boolean addSell(Connection conn, Map<String, String> map,
			String sellNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "insert into sell values(?,?,?,?,?,?,?,?)";
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, sellNumber);
				ps.setString(2, map.get("staffName"));
				ps.setString(3, map.get("sellDate"));
				ps.setString(4, map.get("warehouseName"));
				ps.setString(5, map.get("goodsName"));
				ps.setInt(6, Integer.parseInt(map.get("amount")));
				ps.setBigDecimal(7, new BigDecimal(map.get("sellPrice")));
				ps.setBigDecimal(8, new BigDecimal(map.get("sum")));
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

	/**
	 * 获取销售单信息
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param warehouseName 销售仓库
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 销售单信息
	 */
	public List<Map<String, String>> getSells(String staffName,
			String startTime, String endTime, String warehouseName,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct sellNumber,staffName,sellDate,warehouseName from sell where ";
			// 业务人员不为空
			if (staffName != null && !"".equals(staffName))
			{
				sql += "and staffName='" + staffName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and sellDate>='" + startTime + "' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and sellDate<='" + endTime + "' ";
			}
			// 销售仓库不为空
			if (warehouseName != null && !"".equals(warehouseName))
			{
				sql += "and warehouseName='" + warehouseName + "' ";
			}
			sql += "order by sellNumber desc limit ?,?";
			// 如果sql语句里含有and，则把第一个and删除
			if (sql.indexOf("and ") != -1)
			{
				sql = sql.replaceFirst("and ", "");
			}
			else
			{
				sql = sql.replaceFirst("where ", "");
			}
			
			try {
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (pageNum - 1) * pageSize);
				ps.setInt(2, pageSize);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					// 遍历每一列
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null)
						{
							map.put(columnName, "");
						}
						else map.put(columnName, value);
					}
					// 将该条记录放入List中
					result.add(map);
				}
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		return null;
	}

	/**
	 * 获取分页数
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param warehouseName 销售仓库
	 * @param pageSize 每页记录数
	 * @return 分页数
	 */
	public String getPageCount(String staffName, String startTime,
			String endTime, String warehouseName, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select count(distinct sellNumber) as count from sell where ";
			// 业务人员不为空
			if (staffName != null && !"".equals(staffName))
			{
				sql += "and staffName='" + staffName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and sellDate>='" + startTime + "' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and sellDate<='" + endTime + "' ";
			}
			// 销售仓库不为空
			if (warehouseName != null && !"".equals(warehouseName))
			{
				sql += "and warehouseName='" + warehouseName + "' ";
			}
			sql += "order by sellNumber desc";
			// 如果sql语句里含有and，则把第一个and删除
			if (sql.indexOf("and ") != -1)
			{
				sql = sql.replaceFirst("and ", "");
			}
			else
			{
				sql = sql.replaceFirst("where ", "");
			}
			try
			{
				long count = 0L;
				ps = conn.prepareStatement(sql);
				// 查询记录数
				rs = ps.executeQuery();
				while (rs.next())
				{
					// 计算分页数
					count = (Long.parseLong(rs.getString("count")) % pageSize == 0) ? (Long.parseLong(rs.getString("count")) / pageSize)
							: (Long.parseLong(rs.getString("count")) / pageSize + 1);
				}
				returnString = String.valueOf(count);
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
	 * 获取销售主信息
	 * @param sellNumber 销售单号
	 * @return 销售主信息
	 */
	public List<Map<String, String>> getSell(String sellNumber) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct staffName,sellDate,warehouseName from sell where sellNumber=?";
			try {
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, sellNumber);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					// 遍历每一列
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null)
						{
							map.put(columnName, "");
						}
						else map.put(columnName, value);
					}
					// 将该条记录放入List中
					result.add(map);
				}
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		return null;
	}

	/**
	 * 获取销售明细信息
	 * @param sellNumber 销售单号
	 * @return 销售明细信息
	 */
	public List<Map<String, String>> getSellDetails(String sellNumber) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select wb.goodsType,wb.goodsName,wb.unit,wb.balanceNumber,s.sellPrice,s.amount,s.sum from "
					+ "sell s,warehouseBalance wb where s.goodsName=wb.goodsName and s.warehouseName=wb.warehouseName "
					+ "and s.sellNumber=?";
			try {
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, sellNumber);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					// 遍历每一列
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null)
						{
							map.put(columnName, "");
						}
						else map.put(columnName, value);
					}
					// 将该条记录放入List中
					result.add(map);
				}
				return result;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		return null;
	}

	/**
	 * 删除销售单
	 * @param conn 数据库连接对象
	 * @param sellNumber 销售单号
	 * @return true代表删除成功，false代表失败
	 */
	public boolean deleteSell(Connection conn, String sellNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			PreparedStatement ps = null;
			String sql = "delete from sell where sellNumber=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, sellNumber);
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					// 关闭资源
					if (ps != null) ps.close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
		}
		return false;
	}

}
