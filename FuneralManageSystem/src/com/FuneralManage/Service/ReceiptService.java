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

public class ReceiptService {
	private String returnString;// 返回的字符串数据

	/**
	 * 根据采购编号计算每个商品已到货数量
	 * @param purchaseNumber 采购编号
	 * @return 该采购单已到货商品名称和数量
	 */
	public String getHaveGotGoods(String purchaseNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select goodsName,sum(amountIn) as haveGotAmount from receipt where audit is not null and purchaseNumber=? "
					+ "group by goodsName";
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					result += "{goodsName:\"" + rs.getString("goodsName") + "\",haveGotAmount:\""
							+ rs.getString("haveGotAmount") + "\"},";
				}
				if (result.length() > 0) 
				{
					// 去掉最后一个逗号
					result = result.substring(0, result.length() - 1);
				}	
				returnString = "[" + result + "]";
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
	 * 添加收货信息
	 * @param conn 数据库连接
	 * @param m 收货信息
	 * @param receiptNumber 收货单号
	 * @return true为添加成功，false为添加失败
	 */
	public boolean addReceipt(Connection conn, Map<String, String> m,
			String receiptNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "insert into receipt values(?,?,?,?,?,?,?,?,?)";
			// 遍历每条商品信息
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, receiptNumber);
				ps.setString(2, m.get("purchaseNumber"));
				ps.setString(3, m.get("staffName"));
				ps.setString(4, m.get("receiptDate"));
				ps.setString(5, m.get("warehouse"));
				ps.setString(6, m.get("goodsName"));
				ps.setInt(7, Integer.parseInt(m.get("amountIn")));
				ps.setBigDecimal(8, new BigDecimal(m.get("buyPrice")));
				ps.setString(9, null);
				// 执行sql语句
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
	 * 获取收货单信息
	 * @param purchaseNumber 采购编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param state 状态
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 收货单信息
	 */
	public List<Map<String, String>> getReceipts(String purchaseNumber, String startTime,
			String endTime, String state, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct receiptNumber,purchaseNumber,staffName,receiptDate"
					+ ",warehouseName,audit from receipt where ";
			// 如果采购编号不为空
			if (purchaseNumber != null && !"".equals(purchaseNumber))
			{
				sql += "and purchaseNumber='" + purchaseNumber + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and receiptDate>='" + startTime + "' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and receiptDate<='" + endTime + "' ";
			}
			// 状态不为空
			if (state != null && !"".equals(state))
			{
				if ("未审核".equals(state))
				{
					sql += "and audit is null ";
				}
				else sql += "and audit is not null ";
			}
			sql += "order by receiptNumber desc limit ?,?";
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
				List<Map<String, String>> result = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (pageNum - 1) * pageSize);
				ps.setInt(2, pageSize);
				// 查询收货单信息
				rs = ps.executeQuery();
				// 获取列数  
	            ResultSetMetaData metaData = rs.getMetaData();  
	            int columnCount = metaData.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					// 遍历每一列
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = metaData.getColumnLabel(i);
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
		return null;
	}

	/**
	 * 查询分页数
	 * @param purchaseNumber 采购编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param state 状态
	 * @param pageSize 每页记录数
	 * @return 页数
	 */
	public String getPageCount(String purchaseNumber, String startTime,
			String endTime, String state, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select count(distinct receiptNumber) as count from receipt where ";
			// 如果采购编号不为空
			if (purchaseNumber != null && !"".equals(purchaseNumber))
			{
				sql += "and purchaseNumber='" + purchaseNumber + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and receiptDate>='" + startTime + "' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and receiptDate<='" + endTime + "' ";
			}
			// 状态不为空
			if (state != null && !"".equals(state))
			{
				if ("未审核".equals(state))
				{
					sql += "and audit is null ";
				}
				else sql += "and audit is not null ";
			}
			sql += "order by receiptNumber desc ";
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
	 * 获取收货单主信息
	 * @param receiptNumber 收货单号
	 * @return 收货单主信息
	 */
	public List<Map<String, String>> getReceipt(String receiptNumber) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct purchaseNumber,staffName,receiptDate,warehouseName,audit from receipt where receiptNumber=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, receiptNumber);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				// 获取列数
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null) map.put(columnName, "");
						else map.put(columnName, value);
					}
					list.add(map);
				}
				return list;
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
	 * 获取收货单明细信息
	 * @param receiptNumber 收货单号
	 * @return 收货单明细信息
	 */
	public List<Map<String, String>> getReceiptDetails(String receiptNumber) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select pd.goodsType,pd.goodsName,pd.unit,pd.amount,r.buyPrice,r.amountIn from receipt r,purchaseDetail pd "
						+ "where r.purchaseNumber=pd.purchaseNumber and r.goodsName=pd.goodsName and r.receiptNumber=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, receiptNumber);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				// 获取列数
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null) map.put(columnName, "");
						else map.put(columnName, value);
					}
					list.add(map);
				}
				return list;
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
	 * 审核收货单
	 * @param conn 连接对象
	 * @param receiptNumber 收货单号
	 * @param audit 审核人
	 * @return 布尔型，true代表审核成功，false代表失败
	 */
	public boolean auditReceipt(Connection conn, String receiptNumber, String audit) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update receipt set audit=? where receiptNumber=?";
			// 遍历每条商品信息
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, audit);
				ps.setString(2, receiptNumber);
				// 执行sql语句
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
	 * 根据采购编号计算每个商品已到货数量
	 * @param purchaseNumber 采购编号
	 * @return 该采购单已到货商品名称和数量
	 */
	public List<Map<String, String>> getHaveGotGoodsForList(String purchaseNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			String sql = "select goodsName,sum(amountIn) as haveGotAmount from receipt where audit is not null and purchaseNumber=? "
					+ "group by goodsName";
			ResultSet rs = null;
			PreparedStatement ps = null;
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					map.put("goodsName", rs.getString("goodsName"));
					map.put("haveGotAmount", rs.getString("haveGotAmount"));
					list.add(map);
				}
				return list;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
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
	 * 取消收货单审核
	 * @param conn 数据库连接对象
	 * @param receiptNumber 收货单号
	 * @return true代表取消审核成功，false代表失败
	 */
	public boolean cancelAuditReceipt(Connection conn, String receiptNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update receipt set audit=NULL where receiptNumber=?";
			// 遍历每条商品信息
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, receiptNumber);
				// 执行sql语句
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
	 * 删除收货单信息
	 * @param conn 数据库连接对象
	 * @param receiptNumber 收货单号
	 * @return true代表删除成功，false代表失败
	 */
	public boolean deleteReceiptTran(Connection conn, String receiptNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "delete from receipt where receiptNumber=?";
			// 遍历每条商品信息
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, receiptNumber);
				// 执行sql语句
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
	 * 删除收货单
	 * @param receiptNumber 收货单号
	 * @return 删除结果，true代表删除成功，false代表删除失败
	 */
	public boolean deleteReceipt(String receiptNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			String sql = "delete from receipt where receiptNumber=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, receiptNumber);
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
