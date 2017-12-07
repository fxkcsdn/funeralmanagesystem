package com.FuneralManage.Dao;

import java.util.Map;

import javax.sql.DataSource;

public class WarehouseMoveDAO extends BaseDAO {
	/**
	 * 有参构造器
	 * 
	 * @param dataSource
	 */
	public WarehouseMoveDAO(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 新增调拨单
	 * @param warehouseMoveNumber 调拨单号
	 * @param map 调拨信息
	 * @return 新增结果，true代表新增成功，false为失败
	 */
	public boolean addWarehouseMoveTran(String warehouseMoveNumber,
			Map<String, String> map) {
		// TODO Auto-generated method stub
		String staffName = map.get("staffName");
		String moveDate = map.get("moveDate");
		String outWarehouse = map.get("outWarehouse");
		String inWarehouse = map.get("inWarehouse");
		String goodsName = map.get("goodsName");
		int moveAmount = Integer.parseInt(map.get("moveAmount"));
		String sql = "insert into warehouseMove values(?,?,?,?,?,?,?)";
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseMoveNumber,
				staffName, moveDate, outWarehouse, inWarehouse, goodsName, moveAmount);
		if (result > 0)
			return true;
		return false;
	}

	/**
	 * 删除本地调拨单
	 * @return 删除结果，true为删除成功，false为删除失败
	 */
	public boolean deleteLocalWarehouseMoveTran() {
		// TODO Auto-generated method stub
		String sql = "select max(warehouseMoveNumber) as maxNumber from warehouseMove";
		String warehouseMoveNumber = this.getOneColumnTran(sql);
		String sql2 = "delete from warehouseMove where warehouseMoveNumber=?";
		int result = this.saveOrUpdateOrDeleteTran(sql2, warehouseMoveNumber);
		if (result > 0) return true;
		return false;
	}

	/**
	 * 获取调拨单信息
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param outWarehouse 调出仓库
	 * @param inWarehouse 调入仓库
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 调拨单信息
	 */
	public String getWarehouseMoves(String staffName, String startTime,
			String endTime, String outWarehouse, String inWarehouse,
			int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select distinct warehouseMoveNumber,staffName,moveDate,outWarehouse,inWarehouse from warehouseMove where ";
		// 业务人员不为空
	    if (staffName != null && !"".equals(staffName)) {
			sql += "and staffName='" + staffName + "' ";
		}
		// 开始时间不为空
		if (startTime != null && !"".equals(startTime)) {
			sql += "and moveDate>='" + startTime + "' ";
		}
		// 结束时间不为空
		if (endTime != null && !"".equals(endTime)) {
			sql += "and moveDate<='" + endTime + "' ";
		}
		// 调出仓库不为空
		if (outWarehouse != null && !"".equals(outWarehouse)) {
			sql += "and outWarehouse='" + outWarehouse + "' ";
		}
		// 调入仓库不为空
		if (inWarehouse != null && !"".equals(inWarehouse)) {
			sql += "and inWarehouse='" + inWarehouse + "' ";
		}
		sql += "order by warehouseMoveNumber desc limit ?,?";
		// 如果sql语句里含有and，则把第一个and删除
		if (sql.indexOf("and ") != -1) {
			sql = sql.replaceFirst("and ", "");
		} else {
			sql = sql.replaceFirst("where ", "");
		}	
		String result = this.getForJson(sql, (pageNum - 1) * pageSize, pageSize);
		return result;
	}

	/**
	 * 获取分页数
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param outWarehouse 调出仓库
	 * @param inWarehouse 调入仓库
	 * @param pageSize 每页记录数
	 * @return 分页数
	 */
	public String getPageCount(String staffName, String startTime,
			String endTime, String outWarehouse, String inWarehouse,
			int pageSize) {
		// TODO Auto-generated method stub
		String sql = "select count(distinct warehouseMoveNumber) as count from warehouseMove where ";
		// 业务人员不为空
	    if (staffName != null && !"".equals(staffName)) {
			sql += "and staffName='" + staffName + "' ";
		}
		// 开始时间不为空
		if (startTime != null && !"".equals(startTime)) {
			sql += "and moveDate>='" + startTime + "' ";
		}
		// 结束时间不为空
		if (endTime != null && !"".equals(endTime)) {
			sql += "and moveDate<='" + endTime + "' ";
		}
		// 调出仓库不为空
		if (outWarehouse != null && !"".equals(outWarehouse)) {
			sql += "and outWarehouse='" + outWarehouse + "' ";
		}
		// 调入仓库不为空
		if (inWarehouse != null && !"".equals(inWarehouse)) {
			sql += "and inWarehouse='" + inWarehouse + "' ";
		}
		sql += "order by warehouseMoveNumber desc";
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

}
