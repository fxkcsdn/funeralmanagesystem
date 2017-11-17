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

public class PurchaseDetailService {
	private String returnString;// 返回的字符串数据

	/**
	 * 添加一条商品信息
	 * @param map 商品信息记录
	 * @return 是否添加成功
	 */
	public boolean addPurchaseDetail(Connection conn, Map<String, String> map, String purchaseNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "insert into purchaseDetail values(?,?,?,?,?,?)";
			// 遍历每条商品信息
			try (PreparedStatement ps = conn.prepareStatement(sql))
			{
				ps.setString(1, purchaseNumber);
				ps.setString(2, map.get("goodsType"));
				ps.setString(3, map.get("goodsName"));
				ps.setString(4, map.get("unit"));
				ps.setInt(5, Integer.parseInt(map.get("amount")));
				if (map.get("buyPrice").equals(""))
				{
					ps.setBigDecimal(6, null);
				}
				else ps.setBigDecimal(6, new BigDecimal(map.get("buyPrice")));
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
	 * 获取采购明细信息
	 * @param purchaseNumber 采购编号
	 * @return 采购明细信息
	 */
	public String getPurchaseDetails(String purchaseNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from purchaseDetail where purchaseNumber=?";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				rs = ps.executeQuery();
				// 遍历所有记录
				while (rs.next())
				{
					result += "{purchaseNumber:\"" + rs.getString("purchaseNumber") + "\",goodsType:\""
							+ rs.getString("goodsType") + "\",goodsName:\"" + rs.getString("goodsName")
							+ "\",unit:\"" + rs.getString("unit") + "\",amount:\"" + rs.getString("amount") 
							+ "\",buyPrice:\"" + rs.getString("buyPrice") + "\"},";
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
	 * 获取收货单中采购明细信息
	 * @param purchaseNumber 采购编号
	 * @return 采购明细信息
	 */
	public String getPurchaseDetailsInReceipt(String receiptNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select pd.* from purchaseDetail pd,receipt r where r.purchaseNumber=pd.purchaseNumber and "
					+ "r.goodsName=pd.goodsName and r.receiptNumber=?";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, receiptNumber);
				rs = ps.executeQuery();
				// 遍历所有记录
				while (rs.next())
				{
					result += "{purchaseNumber:\"" + rs.getString("purchaseNumber") + "\",goodsType:\""
							+ rs.getString("goodsType") + "\",goodsName:\"" + rs.getString("goodsName")
							+ "\",unit:\"" + rs.getString("unit") + "\",amount:\"" + rs.getString("amount") 
							+ "\",buyPrice:\"" + rs.getString("buyPrice") + "\"},";
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
	 * 修改入库价
	 * @param conn 数据库连接
	 * @param purchaseNumber 采购编号
	 * @param goodsName 品名
	 * @param buyPrice 入库价
	 * @return true为更新成功，false为更新失败
	 */
	public boolean updateBuyPrice(Connection conn, String purchaseNumber,
			String goodsName, BigDecimal buyPrice) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update purchaseDetail set buyPrice=? where purchaseNumber=? and goodsName=?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setBigDecimal(1, buyPrice);
				ps.setString(2, purchaseNumber);
				ps.setString(3, goodsName);
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * 获取采购明细信息
	 * @param purchaseNumber 采购编号
	 * @return 采购明细信息
	 */
	public List<Map<String, String>> getPurchaseDetailsForList(String purchaseNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from purchaseDetail where purchaseNumber=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				// 获取列数
				int columnCount = meta.getColumnCount();
				// 遍历所有记录
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
}
