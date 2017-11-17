package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.FuneralManage.Utility.DateUtil;

public class PurchaseMasterService {
	private String returnString;// 返回的字符串数据
	
	/**
	 * 增加一条采购单主信息记录
	 * @return 是否添加成功
	 */
	public boolean addPurchaseMaster(Connection conn, String purchaseNumber, String supplierName, String operator, String createDate, String memo)
	{
		if (conn != null)
		{
			String sql = "insert into purchaseMaster values(?,?,?,?,?,?,?,?,?)";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, purchaseNumber);
				ps.setString(2, operator);
				ps.setString(3, supplierName);
				ps.setString(4, createDate);
				ps.setString(5, null);
				ps.setString(6, null);
				ps.setString(7, null);
				ps.setString(8, memo);
				ps.setBoolean(9, false);
				// 执行sql语句
				int result = ps.executeUpdate();
				if (result > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 获取采购单中的供货商信息
	 * @return 供货商信息
	 */
	public String getSuppliersInPurchases() {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct supplierName from purchaseMaster where beClosed=0";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				// 遍历所有记录
				while (rs.next())
				{
					result += "{supplierName:\"" + rs.getString("supplierName") + "\"},";
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
			} finally {
				try {
					// 关闭资源
					if(rs != null) rs.close();
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
	 * 根据查询条件获取采购单主信息
	 * @param supplierName 厂家
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param state 状态
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 采购单主信息
	 */
	public String getPurchases(String supplierName, String startTime,
			String endTime, String state, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from purchaseMaster where beClosed=0 ";
			// 如果厂家不为空
			if (supplierName != null && !"".equals(supplierName))
			{
				sql += "and supplierName='" + supplierName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and createDate>='" + startTime + " 00:00:00' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and createDate<='" + endTime + " 23:59:59' ";
			}
			// 状态不为空
			if (state != null && !"".equals(state))
			{
				if ("财务未审核".equals(state))
				{
					sql += "and financeAudit is null ";
				}
				if ("分馆长未审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is null ";
				}
				if ("馆长未审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is not null and curatorAudit is null ";
				}
				if ("已审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is not null and curatorAudit is not null ";
				}
			}
			sql += "order by purchaseNumber desc limit ?,?";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (pageNum - 1) * pageSize);
				ps.setInt(2, pageSize);
				// 查询采购单主信息
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next())
				{
					result += "{purchaseNumber:\"" + rs.getString("purchaseNumber") + "\",supplierName:\"" + rs.getString("supplierName") 
							+ "\",operator:\"" + rs.getString("operator") + "\",createDate:\"" + rs.getString("createDate") + "\",financeAudit:\""
							+ rs.getString("financeAudit") + "\",viceCuratorAudit:\"" + rs.getString("viceCuratorAudit") + "\",curatorAudit:\""
							+ rs.getString("curatorAudit") + "\",memo:\"" + rs.getString("memo") + "\",beClosed:\"" + rs.getString("beClosed") + "\"},";
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
	 * 查询分页数
	 * @param supplierName 厂家
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param state 状态
	 * @param pageSize 每页记录数
	 * @return 分页数
	 */
	public String getPageCount(String supplierName, String startTime,
			String endTime, String state, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		// 赋初值0
		returnString = "0";
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select count(*) as count from purchaseMaster where beClosed=0 ";
			// 如果厂家不为空
			if (supplierName != null && !"".equals(supplierName))
			{
				sql += "and supplierName='" + supplierName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and createDate>='" + startTime + " 00:00:00' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and createDate<='" + endTime + " 23:59:59' ";
			}
			// 状态不为空
			if (state != null && !"".equals(state))
			{
				if ("财务未审核".equals(state))
				{
					sql += "and financeAudit is null ";
				}
				if ("分馆长未审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is null ";
				}
				if ("馆长未审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is not null and curatorAudit is null ";
				}
				if ("已审核".equals(state))
				{
					sql += "and financeAudit is not null and viceCuratorAudit is not null and curatorAudit is not null ";
				}
			}
			sql += "order by purchaseNumber desc ";
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
	 * 获取已通过审核且未结案的采购单主信息中的厂家信息
	 * @return 厂家信息
	 */
	public String getSuppliersInAuditedPurchases() {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select distinct supplierName from purchaseMaster where curatorAudit is not null and beClosed=0";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				// 遍历所有记录
				while (rs.next())
				{
					result += "{supplierName:\"" + rs.getString("supplierName") + "\"},";
				}
				if (result.length() > 0) 
				{
					// 去掉最后一个逗号
					result = result.substring(0, result.length() - 1);
				}	
				returnString = "[" + result + "]";
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
		return returnString;
	}

	/**
	 * 获取所有已通过审核且未结案的采购单主信息
	 * @param purchaseNumber 采购编号
	 * @param supplierName 厂家名称
	 * @param startTime 起始时间
	 * @param endTime 截止时间
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 采购单主信息
	 */
	public String getPurchasesHaveAudit(String purchaseNumber,
			String supplierName, String startTime, String endTime,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from purchaseMaster where curatorAudit is not null and beClosed=0 ";
			// 如果厂家不为空
			if (supplierName != null && !"".equals(supplierName)) {
				sql += "and supplierName='" + supplierName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime)) {
				sql += "and createDate>='" + startTime + " 00:00:00' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime)) {
				sql += "and createDate<='" + endTime + " 23:59:59' ";
			}
			// 采购编号不为空
			if (purchaseNumber != null && !"".equals(purchaseNumber)) {
				sql += "and purchaseNumber='" + purchaseNumber + "' ";
			}
			sql += "order by purchaseNumber desc limit ?,?";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, (pageNum - 1) * pageSize);
				ps.setInt(2, pageSize);
				// 查询采购单主信息
				rs = ps.executeQuery();
				// 遍历每条记录
				while (rs.next()) {
					result += "{purchaseNumber:\""
							+ rs.getString("purchaseNumber")
							+ "\",supplierName:\""
							+ rs.getString("supplierName") + "\",operator:\""
							+ rs.getString("operator") + "\",createDate:\""
							+ rs.getString("createDate") + "\",financeAudit:\""
							+ rs.getString("financeAudit")
							+ "\",viceCuratorAudit:\""
							+ rs.getString("viceCuratorAudit")
							+ "\",curatorAudit:\""
							+ rs.getString("curatorAudit") + "\",memo:\""
							+ rs.getString("memo") + "\",beClosed:\""
							+ rs.getString("beClosed") + "\"},";
				}
				if (result.length() > 0) {
					// 去掉最后一个逗号
					result = result.substring(0, result.length() - 1);
				}
				returnString = "[" + result + "]";
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
		return returnString;
	}

	/**
	 * 查询分页数
	 * @param purchaseNumber 采购编号
	 * @param supplierName 厂家名称
	 * @param startTime 起始时间
	 * @param endTime 截止时间
	 * @param pageSize 每页记录数
	 * @return 总页数
	 */
	public String getPageCountHaveAudit(String purchaseNumber,
			String supplierName, String startTime, String endTime, int pageSize) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		returnString = "0";
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select count(*) as count from purchaseMaster where curatorAudit is not null and beClosed=0 ";
			// 如果厂家不为空
			if (supplierName != null && !"".equals(supplierName))
			{
				sql += "and supplierName='" + supplierName + "' ";
			}
			// 开始时间不为空
			if (startTime != null && !"".equals(startTime))
			{
				sql += "and createDate>='" + startTime + " 00:00:00' ";
			}
			// 结束时间不为空
			if (endTime != null && !"".equals(endTime))
			{
				sql += "and createDate<='" + endTime + " 23:59:59' ";
			}
			// 采购编号不为空
			if (purchaseNumber != null && !"".equals(purchaseNumber))
			{
				sql += "and purchaseNumber='" + purchaseNumber + "' ";  
			}
			sql += "order by purchaseNumber desc ";
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
	 * 获取采购单主信息
	 * @param purchaseNumber 采购单编号
	 * @return 采购单主信息
	 */
	public String getPurchaseMaster(String purchaseNumber) {
		// TODO Auto-generated method stub
		// 连接数据库
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "select * from purchaseMaster where purchaseNumber=?";
			try {
				String result = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				// 查询采购单主信息
				rs = ps.executeQuery();
				// 遍历所有采购单
				while (rs.next())
				{
					result += "{purchaseNumber:\""
							+ rs.getString("purchaseNumber")
							+ "\",supplierName:\""
							+ rs.getString("supplierName") + "\",operator:\""
							+ rs.getString("operator") + "\",createDate:\""
							+ rs.getString("createDate") + "\",financeAudit:\""
							+ rs.getString("financeAudit")
							+ "\",viceCuratorAudit:\""
							+ rs.getString("viceCuratorAudit")
							+ "\",curatorAudit:\""
							+ rs.getString("curatorAudit") + "\",memo:\""
							+ rs.getString("memo") + "\",beClosed:\""
							+ rs.getString("beClosed") + "\"},";
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
	 * 保存审核信息
	 * @param purchaseNumber 采购编号
	 * @param audit 审核人
	 * @param type 审核人类型（财务、分馆长和馆长）
	 * @param auditResult 审核结果
	 * @return 布尔值，true代表保存成功，false代表保存失败
	 */
	public boolean auditPurchase(String purchaseNumber, String audit,
			String type, String auditResult) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			// 审核通过
			if ("通过".equals(auditResult))
			{
				// 如果是财务审核
				if ("financeAudit".equals(type))
				{
					sql = "update purchaseMaster set financeAudit=? where purchaseNumber=?";
				}
				// 如果是分馆长审核
				if ("viceCuratorAudit".equals(type))
				{
					sql = "update purchaseMaster set viceCuratorAudit=? where purchaseNumber=?";
				}
				// 如果是馆长审核
				if ("curatorAudit".equals(type))
				{
					sql = "update purchaseMaster set curatorAudit=? where purchaseNumber=?";
				}
			}
			// 审核不通过
			else
			{
				// 如果是分馆长审核
				if ("viceCuratorAudit".equals(type))
				{
					sql = "update purchaseMaster set financeAudit=NULL where purchaseNumber=?";
				}
				// 如果是馆长审核
				if ("curatorAudit".equals(type))
				{
					sql = "update purchaseMaster set financeAudit=NULL,viceCuratorAudit=NULL where purchaseNumber=?";
				}
			}
			try {
				int result = 0;
				ps = conn.prepareStatement(sql);
				// 审核通过
				if ("通过".equals(auditResult))
				{
					ps.setString(1, audit);
					ps.setString(2, purchaseNumber);
				}
				// 审核不通过
				else
				{
					ps.setString(1, purchaseNumber);
				}
				result = ps.executeUpdate();
				if (result > 0) return true;
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
		return false;
	}

	/**
	 * 保存取消审核信息
	 * @param purchaseNumber 采购编号
	 * @param audit 审批人
	 * @param type 审批人类型（财务、分馆长和馆长）
	 * @return 布尔型，“true”代表取消审核成功，“false”代表取消审核失败
	 */
	public boolean cancelAuditPurchase(String purchaseNumber, String audit,
			String type) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "";
			// 如果是财务取消审核
			if ("financeAudit".equals(type))
			{
				sql = "update purchaseMaster set financeAudit=NULL where purchaseNumber=?";
			}
			// 如果是分馆长审核
			if ("viceCuratorAudit".equals(type))
			{
				sql = "update purchaseMaster set viceCuratorAudit=NULL where purchaseNumber=?";
			}
			// 如果是馆长审核
			if ("curatorAudit".equals(type))
			{
				sql = "update purchaseMaster set curatorAudit=NULL where purchaseNumber=?";
			}
			try {
				int result = 0;
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				result = ps.executeUpdate();
				if (result > 0) return true;
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
		return false;
	}

	/**
	 * 保存结案信息
	 * @param purchaseNumber 采购编号
	 * @return 布尔值，“true”代表结案成功，“false”代表结案失败
	 */
	public boolean closePurchase(String purchaseNumber) {
		// TODO Auto-generated method stub
		Connection conn = DBDao.openDateBase("dongtai");
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update purchaseMaster set beClosed=1 where purchaseNumber=?";
			try {
				int result = 0;
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				result = ps.executeUpdate();
				if (result > 0) return true;
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
		return false;
	}

	/**
	 * 删除采购单主信息和明细信息
	 * @param conn 数据库连接对象
	 * @param purchaseNumber 采购编号
	 */
	public boolean deletePurchase(Connection conn, String purchaseNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			String sql = "delete from purchaseMaster where purchaseNumber=?";
			String sql1 = "delete from purchaseDetail where purchaseNumber=?";
			try (PreparedStatement ps = conn.prepareStatement(sql);
					PreparedStatement ps1 = conn.prepareStatement(sql1)) {
				ps.setString(1, purchaseNumber);
				ps1.setString(1, purchaseNumber);
				// 执行sql语句
				int result = ps.executeUpdate();
				int result1 = ps1.executeUpdate();
				if (result > 0 && result1 > 0) return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
	
	/**
	 * 结案采购单（事务）
	 * @param conn 数据库连接对象
	 * @param purchaseNumber 采购编号
	 * @return true代表结案成功，false代表失败
	 */
	public boolean closePurchaseTran(Connection conn, String purchaseNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update purchaseMaster set beClosed=1 where purchaseNumber=?";
			try {
				int result = 0;
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				result = ps.executeUpdate();
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
	 * 取消采购单结案标志
	 * @param conn 数据库连接对象
	 * @param purchaseNumber 采购编号
	 * @return true代表取消结案成功，false代表失败
	 */
	public boolean cancelClosePurchaseTran(Connection conn, String purchaseNumber) {
		// TODO Auto-generated method stub
		if (conn != null)
		{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "update purchaseMaster set beClosed=0 where purchaseNumber=?";
			try {
				int result = 0;
				ps = conn.prepareStatement(sql);
				ps.setString(1, purchaseNumber);
				result = ps.executeUpdate();
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

	
}
