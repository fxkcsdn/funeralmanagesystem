package com.FuneralManage.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.FuneralManage.Dao.CoffinDAO;
import com.FuneralManage.Dao.VillaDAO;
import com.FuneralManage.Dao.WatchSpiritDAO;
import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Utility.DateUtil;
import com.FuneralManage.Utility.TransactionManager;
import com.FuneralManage.entity.WatchSpirit;

public class WatchSpiritService extends BaseService {
	private WatchSpiritDAO watchSpiritDAO = new WatchSpiritDAO(dataSource);
	private VillaDAO villaDAO = new VillaDAO(dataSource);
	private CoffinDAO coffinDAO = new CoffinDAO(dataSource);
	private TransactionManager transactionManager = new TransactionManager(
			dataSource);
	private String returnString;

	/**
	 * 登记守灵信息
	 * 
	 * @param watchSpirit
	 * @return
	 */
	public Boolean addWatchSpirit(WatchSpirit watchSpirit) {
		try {
			this.transactionManager.start();
			watchSpiritDAO.saveWatchSpiritTran(watchSpirit);
			villaDAO.updateVillaStateTran(0, watchSpirit.getVillaName());
			coffinDAO.updateCoffinStateTran(0, watchSpirit.getCoffinNumber());
			this.transactionManager.commit();
		} catch (Exception e) {
			this.transactionManager.rollback();
			return false;
		} finally {
			this.transactionManager.close();
		}
		return true;
	}

	/**
	 * 生成守灵编号
	 * 
	 * @return
	 */
	public String getWatchNumber() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		String date = df.format(new Date());
		String result = "";

		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			String sql = "select * from watchSpirit where watchNumber like ?";
			ResultSet rs = null;
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, date + "%");
				rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getString("watchNumber");

				}
				if (result != "") {
					result = result.substring(6, result.length());
					result = date + (Integer.parseInt(result) + 1);
				} else {
					result = date + "1";
				}
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public String getWatchSpiritByWatchNumber(String watchNumber) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		String date = df.format(new Date());

		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			String sql = "select * from watchSpirit where watchNumber=?";
			ResultSet rs = null;

			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, watchNumber);

				String result = "";
				rs = ps.executeQuery();
				if (rs.next()) {
					rs.last();

					if (rs.getString("endTime") != null) {
						this.returnString = null;
						return returnString;
					}

					result = "{deadId:\""
							+ rs.getString("deadID")
							+ "\",memberName:\""
							+ rs.getString("memberName")
							+ "\",memberMobile:\""
							+ rs.getString("memberMobile")
							+ "\",startTime:\""
							+ rs.getString("startTime").toString()
									.substring(0, 16) + "\",endTime:\"" + date
							+ "\",villaNumber:\"" + rs.getString("villaName")
							+ "\",coffinNumber:\""
							+ rs.getString("coffinNumber") + "\"}";
					this.returnString = result;
					rs.close();
					ps.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				this.returnString = null;
			}
		}
		return returnString;
	}

	public String updateWatchSpiritByWatchNumber(String watchNumber,
			String endTime, String coffinRealCost, String villaRealCost,
			String villaNumber, String coffinNumber, String coffinBeCost,
			String villaBeCost) {
		Connection conn = DBDao.openDateBase("dongtai");
		int row = 0;

		if (conn != null) {
			String sql = "update watchSpirit set endTime=?, coffinRealCost=?, villaRealCost=?, coffinBeCost=?, villaBeCost=? where watchNumber=?";
			String sql2 = "update coffin set bAvailable=? where coffinNumber=?";
			String sql3 = "update villa set bAvailable=? where villaNumber=?";
			try {
				conn.setAutoCommit(false);

				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, endTime);
				ps.setString(2, coffinRealCost);
				ps.setString(3, villaRealCost);
				ps.setString(4, coffinBeCost);
				ps.setString(5, villaBeCost);
				ps.setString(6, watchNumber);

				row = ps.executeUpdate();

				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(1, "1");
				ps2.setString(2, coffinNumber);
				row = ps2.executeUpdate();

				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, "1");
				ps3.setString(2, villaNumber);
				row = ps3.executeUpdate();

				conn.commit();
				returnString = "{result:\"yes\"}";

				ps.close();
				conn.close();
			} catch (SQLException e) {
				returnString = "{result:\"no\"}";
				e.printStackTrace();
			}
		} else {
			returnString = "{result:\"no\"}";
		}
		return returnString;
	}

}
