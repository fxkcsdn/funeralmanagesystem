package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.FuneralManage.Dao.WarehouseBalanceDAO;
import com.FuneralManage.Utility.TransactionManager;

public class WarehouseBalanceService extends BaseService {
	private String returnString;// 返回的字符串数据
	private WarehouseBalanceDAO warehouseBalanceDao = new WarehouseBalanceDAO(dataSource);
	private TransactionManager transactionManager=new TransactionManager(dataSource);

	/**
	 * 增加库存量
	 * @param conn 连接对象
	 * @param warehouseName 收货仓库
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @param unit 单位
	 * @param amountIn 本次收货数量
	 * @return true代表修改成功，false代表失败
	 */
	public boolean increaseBalanceNumber(Connection conn, String warehouseName,
			String goodsType, String goodsName, String unit, int amountIn) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select count(*) from warehouseBalance where warehouseName=? and goodsName=?";
			String sql1 = "insert into warehouseBalance values(?,?,?,?,?,?)";
			String sql2 = "update warehouseBalance set balanceNumber=balanceNumber+? where warehouseName=? and goodsName=?";
			try {
				long count = 0L;
				ps = conn.prepareStatement(sql);
				ps.setString(1, warehouseName);
				ps.setString(2, goodsName);
				rs = ps.executeQuery();
				// 遍历结果集
				while (rs.next())
				{
					count = rs.getLong(1);
				}
				// 库存里已有该商品
				if (count > 0)
				{
					ps = conn.prepareStatement(sql2);
					ps.setInt(1, amountIn);
					ps.setString(2, warehouseName);
					ps.setString(3, goodsName);
					int result = ps.executeUpdate();
					if (result > 0) return true;
				}
				// 库存里不含有该商品
				else
				{
					ps = conn.prepareStatement(sql1);
					ps.setString(1, warehouseName);
					ps.setString(2, goodsType);
					ps.setString(3, goodsName);
					ps.setString(4, unit);
					ps.setInt(5, amountIn);
					ps.setBigDecimal(6, null);
					int result = ps.executeUpdate();
					if (result > 0) return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					// 关闭资源
					if (rs != null) rs.close();
					if (ps != null) ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 减少库存量
	 * @param conn 数据库连接对象
	 * @param warehouseName 收货仓库
	 * @param goodsName 品名
	 * @param amountIn 本次收货数量
	 * @return true代表修改成功，false代表失败
	 */
	public boolean reduceBalanceNumber(Connection conn, String warehouseName
			, String goodsName, int amountIn) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update warehouseBalance set balanceNumber=balanceNumber-? where warehouseName=? and goodsName=?";
			try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, amountIn);
				ps.setString(2, warehouseName);
				ps.setString(3, goodsName);
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} finally {
				try {
					// 关闭资源
					if (rs != null) rs.close();
					if (ps != null) ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 获取仓库里所有商品种类信息
	 * @param warehouseName 仓库名
	 * @return 商品种类信息
	 */
	public List<Map<String, String>> getGoodsTypesInWarehouse(
			String warehouseName) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct goodsType from warehouseBalance where warehouseName=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, warehouseName);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null) value = "";
						map.put(columnName, value);
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
	 * 获取品名
	 * @param warehouseName 仓库名
	 * @param goodsType 商品种类
	 * @return 品名
	 */
	public List<Map<String, String>> getGoodsNamesInWarehouse(
			String warehouseName, String goodsType) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct goodsName from warehouseBalance where warehouseName=? and goodsType=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, warehouseName);
				ps.setString(2, goodsType);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null) value = "";
						map.put(columnName, value);
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
	 * 获取商品单位和库存数量、销售价
	 * @param warehouseName 仓库名
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @return 单位和库存数量、销售价
	 */
	public List<Map<String, String>> getUnitAndNumAndPrice(String warehouseName,
			String goodsType, String goodsName) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select unit,balanceNumber,sellPrice from warehouseBalance where warehouseName=? and goodsType=? and goodsName=?";
			try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				ps = conn.prepareStatement(sql);
				ps.setString(1, warehouseName);
				ps.setString(2, goodsType);
				ps.setString(3, goodsName);
				rs = ps.executeQuery();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount = meta.getColumnCount();
				// 遍历结果集
				while (rs.next())
				{
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 1; i <= columnCount; i++)
					{
						String columnName = meta.getColumnLabel(i);
						String value = rs.getString(columnName);
						if (value == null) value = "";
						map.put(columnName, value);
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
	 * 还原商品库存数量
	 * @param conn 数据库连接对象
	 * @param warehouseName 销售仓库
	 * @param goodsName 品名
	 * @param balanceNumber 库存数量
	 * @return true代表重置成功，false代表失败
	 */
	public boolean resetBalanceNumber(Connection conn, String warehouseName,
			String goodsName, int balanceNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update warehouseBalance set balanceNumber=? where warehouseName=? and goodsName=?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, balanceNumber);
				ps.setString(2, warehouseName);
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
	 * 更新销售价
	 * @param conn 数据库连接对象
	 * @param warehouseName 仓库
	 * @param goodsName 品名
	 * @return true代表更新成功，false代表失败
	 */
	public boolean updateSellPrice(Connection conn, String warehouseName, String goodsName) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "update warehouseBalance wb,goods g set wb.sellPrice=g.sellPrice where wb.goodsName=g.goodsName and wb.warehouseName=? and wb.goodsName=?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, warehouseName);
				ps.setString(2, goodsName);
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
	 * 添加商品信息
	 * @param warehouseName 仓库名称
	 * @param goodsList 商品信息
	 * @return true代表添加成功，false代表失败
	 */
	public boolean addGoods(String warehouseName,
			List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				// 添加商品信息
				warehouseBalanceDao.addGoodTran(warehouseName, map);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	/**
	 * 获取该仓库里的商品信息
	 * @param warehouseName 仓库名称
	 * @return 商品信息
	 */
	public String getGoodsInWarehouse(String warehouseName) {
		// TODO Auto-generated method stub
		// 获取该仓库里的商品信息
		return warehouseBalanceDao.getGoodsInWarehouse(warehouseName);
	}

	/**
	 * 获取该仓库商品种类信息
	 * @param warehouseName 仓库名称
	 * @return 商品种类信息
	 */
	public String getGoodsTypesInWarehouseForJson(String warehouseName) {
		// TODO Auto-generated method stub
		// 获取该仓库商品种类信息
		return warehouseBalanceDao.getGoodsTypesInWarehouse(warehouseName);
	}

	/**
	 * 获取该仓库品名信息
	 * @param warehouseName 仓库名称
	 * @param goodsType 商品种类
	 * @return 品名信息
	 */
	public String getGoodsNamesInWarehouseForJson(String warehouseName, String goodsType) {
		// TODO Auto-generated method stub
		// 获取该仓库品名信息
		return warehouseBalanceDao.getGoodsNamesInWarehouse(warehouseName, goodsType);
	}

	/**
	 * 获取该仓库商品单位和库存数量
	 * @param warehouseName 仓库名称
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @return 单位和库存数量
	 */
	public String getUnitAndNum(String warehouseName, String goodsType,
			String goodsName) {
		// TODO Auto-generated method stub
		// 获取该仓库商品单位和库存数量
		return warehouseBalanceDao.getUnitAndNum(warehouseName, goodsType, goodsName);
	}

	/**
	 * 远程修改仓库信息
	 * @param goodsList 商品信息
	 * @return 远程修改结果，true为修改成功，false为失败
	 */
	public boolean remoteUpdateWarehouseBalance(
			List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String inWarehouse = map.get("inWarehouse");// 调入仓库
				String goodsType = map.get("goodsType");// 商品种类
				String goodsName = map.get("goodsName");// 品名
				String unit = map.get("unit");// 单位
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 增加库存量
				warehouseBalanceDao.increaseWarehouseBalanceTran(inWarehouse, goodsType, goodsName, unit, moveAmount);
				// 更新销售价
				warehouseBalanceDao.updateSellPriceTran(inWarehouse, goodsName);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	/**
	 * 还原本地库存量
	 * @param goodsList 商品信息
	 * @return 还原结果，true为成功，false为失败
	 */
	public boolean resetLocalNumber(List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String outWarehouse = map.get("outWarehouse");// 调出仓库
				String goodsName = map.get("goodsName");// 品名
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 还原本地库存量
				warehouseBalanceDao.increaseWarehouseBalanceTran(outWarehouse, "", goodsName, "", moveAmount);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	/**
	 * 还原远程库存量
	 * @param goodsList 商品信息
	 * @return 还原结果，true为成功，false为失败
	 */
	public boolean resetRemoteNumber(List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String inWarehouse = map.get("inWarehouse");// 调入仓库
				String goodsName = map.get("goodsName");// 品名
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 还原远程库存量
				warehouseBalanceDao.reduceWarehouseBalanceTran(inWarehouse, goodsName, moveAmount);
			}	
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	/**
	 * 远程还原失败后再次还原本地仓库
	 * @param goodsList 商品信息
	 * @return 还原结果，true为成功，false为失败
	 */
	public boolean resetLocalNumberSec(List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String outWarehouse = map.get("outWarehouse");// 调出仓库
				String goodsName = map.get("goodsName");// 品名
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 再次还原本地仓库
				warehouseBalanceDao.reduceWarehouseBalanceTran(outWarehouse, goodsName, moveAmount);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

}
