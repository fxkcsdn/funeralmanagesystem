package com.FuneralManage.Dao;

import java.util.Map;

import javax.sql.DataSource;

public class WarehouseCheckDAO extends BaseDAO {
	/**
	 * 有参构造器
	 * 
	 * @param dataSource
	 */
	public WarehouseCheckDAO(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 添加盘点信息
	 * 
	 * @param warehouseCheckNumber
	 *            盘点单号
	 * @param map
	 *            商品信息
	 * @param true代表添加成功
	 *            ，false代表失败
	 */
	public boolean addWarehouseCheckTran(String warehouseCheckNumber,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		String staffName = map.get("staffName");
		String checkDate = map.get("checkDate");
		String warehouseName = map.get("warehouseName");
		String goodsName = map.get("goodsName");
		int normalAmount = Integer.parseInt(map.get("normalAmount"));
		int realAmount = Integer.parseInt(map.get("realAmount"));
		int amountDifference = Integer.parseInt(map.get("amountDifference"));
		String sql = "insert into warehouseCheck values(?,?,?,?,?,?,?,?)";
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseCheckNumber,
				staffName, checkDate, warehouseName, goodsName, normalAmount,
				realAmount, amountDifference);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 获取盘点单信息
	 * 
	 * @param staffName
	 *            业务人员
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param warehouseName
	 *            盘点仓库
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return 盘点单信息
	 */
	public String getWarehouseChecks(String staffName, String startTime,
			String endTime, String warehouseName, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select distinct warehouseCheckNumber,staffName,checkDate,warehouseName from warehouseCheck where ";
		// 业务人员不为空
		if (staffName != null && !"".equals(staffName)) {
			sql += "and staffName='" + staffName + "' ";
		}
		// 开始时间不为空
		if (startTime != null && !"".equals(startTime)) {
			sql += "and checkDate>='" + startTime + "' ";
		}
		// 结束时间不为空
		if (endTime != null && !"".equals(endTime)) {
			sql += "and checkDate<='" + endTime + "' ";
		}
		// 销售仓库不为空
		if (warehouseName != null && !"".equals(warehouseName)) {
			sql += "and warehouseName='" + warehouseName + "' ";
		}
		sql += "order by warehouseCheckNumber desc limit ?,?";
		// 如果sql语句里含有and，则把第一个and删除
		if (sql.indexOf("and ") != -1) {
			sql = sql.replaceFirst("and ", "");
		} else {
			sql = sql.replaceFirst("where ", "");
		}
		String result = this
				.getForJson(sql, (pageNum - 1) * pageSize, pageSize);
		return result;
	}

	/**
	 * 获取分页数
	 * 
	 * @param staffName
	 *            业务人员
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param warehouseName
	 *            盘点仓库
	 * @param pageSize
	 *            每页记录数
	 * @return 分页数
	 */
	public String getPageCount(String staffName, String startTime,
			String endTime, String warehouseName, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select count(distinct warehouseCheckNumber) as count from warehouseCheck where ";
		// 业务人员不为空
		if (staffName != null && !"".equals(staffName)) {
			sql += "and staffName='" + staffName + "' ";
		}
		// 开始时间不为空
		if (startTime != null && !"".equals(startTime)) {
			sql += "and checkDate>='" + startTime + "' ";
		}
		// 结束时间不为空
		if (endTime != null && !"".equals(endTime)) {
			sql += "and checkDate<='" + endTime + "' ";
		}
		// 销售仓库不为空
		if (warehouseName != null && !"".equals(warehouseName)) {
			sql += "and warehouseName='" + warehouseName + "' ";
		}
		sql += "order by warehouseCheckNumber desc";
		// 如果sql语句里含有and，则把第一个and删除
		if (sql.indexOf("and ") != -1) {
			sql = sql.replaceFirst("and ", "");
		} else {
			sql = sql.replaceFirst("where ", "");
		}
		// 获取总记录数
		long count = this.getCount(sql);
		count = ((count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1));
		return String.valueOf(count);
	}

	/**
	 * 获取盘点单主信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 盘点单主信息
	 */
	public String getWarehouseCheck(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		String sql = "select distinct staffName,checkDate,warehouseName from warehouseCheck where warehouseCheckNumber=?";
		return this.getForJson(sql, warehouseCheckNumber);
	}

	/**
	 * 获取盘点单明细信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 盘点单明细信息
	 */
	public String getWarehouseCheckDetails(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		String sql = "select distinct wb.goodsType,wb.goodsName,wb.unit,wb.balanceNumber,wc.realAmount,wc.amountDifference from warehouseBalance wb"
				+ ",warehouseCheck wc where wb.goodsName=wc.goodsName and wc.warehouseCheckNumber=?";
		return this.getForJson(sql, warehouseCheckNumber);
	}

	/**
	 * 删除盘点单信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 删除结果，true为删除成功，false为失败
	 */
	public boolean deleteWarehouseCheckTran(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		String sql = "delete from warehouseCheck where warehouseCheckNumber=?";
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseCheckNumber);
		if (result > 0) return true;
		return false;
	}

}
