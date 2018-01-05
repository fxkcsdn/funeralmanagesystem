package com.FuneralManage.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FuneralManage.Service.DBDao;

public class NumberUtil {
	/**
	 * 生成本月最新的采购编号
	 * @param createDate 创建日期
	 * @return 本月最新的采购编号
	 */
	public static String createPurchaseNumber(String createDate)
	{
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大采购编号
				// 获取创建日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(createDate);
				String sql = "select max(purchaseNumber) as maxNumber from purchaseMaster where purchaseNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大采购单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有采购单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}

	/**
	 * 获取本日最新接运编号
	 * @param carryTime 接运时间
	 * @return 接运编号，JSON格式字符串
	 */
	public static String createReeferCarryNumber(String carryTime)
	{
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大接运编号
				// 获取接运时间的年月日字符串
				String yearAndMonthAndDay = DateUtil.getYearAndMonthAndDay(carryTime);
				String sql = "select max(carryNumber) as maxNumber from reeferRemainsCarry where carryNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonthAndDay + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本日最大接运单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本日没有接运单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonthAndDay + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(8));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonthAndDay + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}
	
	/**
	 * 获取本日最新冷藏编号
	 * @param arrivalTime 到馆时间
	 * @return 最新冷藏编号，JSON格式字符串
	 */
	public static String createRemainsReeferNumber(String arrivalTime)
	{
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大冷藏编号
				// 获取到馆时间的年月日字符串
				String yearAndMonthAndDay = DateUtil.getYearAndMonthAndDay(arrivalTime);
				String sql = "select max(reeferNumber) as maxNumber from remainsReefer where reeferNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonthAndDay + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本日最大冷藏单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本日没有冷藏单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonthAndDay + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(8));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonthAndDay + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}
	
	/**
	 * 生成本月最新的收货单号
	 * @param receiptDate 收货日期
	 * @return 本月最新的收货单号
	 */
	public static String createReceiptNumber(String receiptDate)
	{
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大采购编号
				// 获取创建日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(receiptDate);
				String sql = "select max(receiptNumber) as maxNumber from receipt where receiptNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大采购单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有采购单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}

	/**
	 * 生成本月最新的销售单号
	 * @param sellDate 销售日期
	 * @return 最新的销售单号
	 */
	public static String createSellNumber(String sellDate) {
		// TODO Auto-generated method stub
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大采购编号
				// 获销售建日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(sellDate);
				String sql = "select max(sellNumber) as maxNumber from sell where sellNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大采购单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有采购单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}

	/**
	 * 创建新的报损单号
	 * @param breakageDate 报损日期
	 * @return 最新的报损单号
	 */
	public static String createBreakageNumber(String breakageDate) {
		// TODO Auto-generated method stub
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大采购编号
				// 获销售建日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(breakageDate);
				String sql = "select max(breakageNumber) as maxNumber from breakage where breakageNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大采购单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有采购单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;
	}

	/**
	 * 生成最新的盘点单号
	 * @param checkDate 盘点日期
	 * @return 最新的盘点单号
	 */
	public static String createWarehouseCheckNumber(String checkDate) {
		// TODO Auto-generated method stub
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大盘点单号
				// 获取盘点日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(checkDate);
				String sql = "select max(warehouseCheckNumber) as maxNumber from warehouseCheck where warehouseCheckNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大采购单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有采购单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;	
	}

	/**
	 * 生成新的调拨单号
	 * @param moveDate 调拨单号
	 * @return 最新的调拨单号
	 */
	public static String createWarehouseMoveNumber(String moveDate) {
		// TODO Auto-generated method stub
		String currentNumber = "";
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String maxNumber = "";// 最大调拨单号
				// 获取盘点日期的年月字符串
				String yearAndMonth = DateUtil.getYearAndMonth(moveDate);
				String sql = "select max(warehouseMoveNumber) as maxNumber from warehouseMove where warehouseMoveNumber like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yearAndMonth + "%");
				// 执行sql语句
				rs = ps.executeQuery();	
				// 遍历记录
				while (rs.next())
				{
					// 获取本月最大调拨单编号
					maxNumber = rs.getString("maxNumber");
					// 如果本月没有调拨单
					if (maxNumber == null || maxNumber.equals(""))
					{
						currentNumber = yearAndMonth + "01";
					}
					else
					{
						// 得到当前流水号
						int serialNumber = Integer.parseInt(maxNumber.substring(6));
						String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
						currentNumber = yearAndMonth + currentSerialNumber;
					}
				}
			} catch (SQLException e) {
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
		return currentNumber;	
	}
	
	
}


