package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.FuneralManage.Dao.CoffinDAO;
import com.FuneralManage.Dao.RentCoffinDAO;
import com.FuneralManage.Utility.TransactionManager;

public class RentCoffinService extends BaseService {
	private TransactionManager transactionManager = new TransactionManager(
			dataSource);
	private RentCoffinDAO rentCoffinDAO = new RentCoffinDAO(dataSource);
	private CoffinDAO coffinDAO = new CoffinDAO(dataSource);
	private String returnString;

	public String getRentCoffinInfoByCoffinNumberOrRentNumber(
			String coffinNumber, String rentNumber) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		String date = df.format(new Date());
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			String sql = "";

			if (!(coffinNumber.equals("") || rentNumber.equals(""))) {
				sql = "select * from rentcoffin where coffinNumber = ? and rentNumber = ? ORDER BY startTime ASC  ";
			} else {
				sql = "select * from rentcoffin where coffinNumber = ? or rentNumber = ? ORDER BY startTime ASC  ";
			}

			ResultSet rs = null;
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, coffinNumber);
				ps.setString(2, rentNumber);
				String result = "";
				rs = ps.executeQuery();
				if (rs.next()) {
					rs.last();
					if (rs.getString("rentNumber") != null) {
						if (rs.getString("returnTime") != null) {
							this.returnString = null;
							return returnString;
						}
						result = "{rentNumber:\""
								+ rs.getString("rentNumber")
								+ "\",deadId:\""
								+ (rs.getString("deadId") == null ? "" : rs
										.getString("deadId"))
								+ "\",coffinNumber:\""
								+ rs.getString("coffinNumber")
								+ "\",startTime:\""
								+ rs.getString("startTime").toString()
										.substring(0, 16) + "\",carryTime:\""
								+ date + "\",address:\""
								+ rs.getString("address")
								+ "\",contactMobile:\""
								+ rs.getString("contactMobile")
								+ "\",contactName:\""
								+ rs.getString("contactName") + "\"}";
						returnString = result;
					} else {
						returnString = null;
					}
					rs.close();
					ps.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				returnString = null;
			}
		}
		return returnString;
	}

	public String getRentCoffinInfoByRentNumber() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		String date = df.format(new Date());
		String result = "";

		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			String sql = "select * from rentCoffin where rentNumber like ?";
			ResultSet rs = null;
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, date + "%");
				rs = ps.executeQuery();
				int count = 0;
				while (rs.next()) {
					result = rs.getString("rentNumber");
					++count;
				}
				if (result != "") {
					// result = result.substring(6, result.length());
					// result = date + (Integer.parseInt(result) + 1);
					result = date + (count + 1);
				} else {
					result = date + "1";
				}
				returnString = "{rentNumber:\"" + result + "\"}";
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}

	public String updateRentCoffin(String deadId, String returnTime,
			String rentNumber, String coffinNumber, String beRentCost,
			String realRentCost) {
		Connection conn = DBDao.openDateBase("dongtai");
		int row = 0;
		if (conn != null) {
			String sql = "update rentcoffin set deadId=?, returnTime=? , beRentCost=?, realRentCost=? where rentNumber=?";
			String sql1 = "update coffin set bAvailable=? where coffinNumber=?";
			try {
				conn.setAutoCommit(false);

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, deadId);
				ps.setString(2, returnTime);
				ps.setString(3, beRentCost);
				ps.setString(4, realRentCost);
				ps.setString(5, rentNumber);
				row = ps.executeUpdate();

				PreparedStatement ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, "1");
				ps1.setString(2, coffinNumber);
				row = ps1.executeUpdate();

				conn.commit();
				returnString = "{result:\"yes\"}";

				ps.close();
				conn.close();
			} catch (SQLException e) {
				try {
					conn.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				returnString = "{result:\"no\"}";
			}
		} else {
			returnString = "{result:\"no\"}";
		}
		return returnString;
	}

	/**
	 * 保存租棺信息
	 * 
	 * @param contactName
	 * @param contactMobile
	 * @param startTime
	 * @param coffinNumber
	 * @param address
	 * @param rentNumber
	 * @param carCost
	 * @param carNumber
	 * @param bInternalCar
	 * @param carRealCost
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String saveRentCoffin(String contactName, String contactMobile,
			String startTime, String coffinNumber, String address,
			String rentNumber, String carCost, String carNumber,
			String bInternalCar, String carRealCost)
			throws UnsupportedEncodingException {
		try {
			this.transactionManager.start();
			rentCoffinDAO.addRentConffinTran(contactName, contactMobile,
					startTime, coffinNumber, address, rentNumber, carCost,
					carNumber, bInternalCar, carRealCost);
			coffinDAO.updateCoffinStateTran(0, coffinNumber);
			this.transactionManager.commit();
			returnString = "{result:\"yes\"}";
		} catch (Exception e) {
			this.transactionManager.rollback();
			returnString = "{result:\"no\"}";
		} finally {
			this.transactionManager.close();
		}
		return returnString;
	}
}
