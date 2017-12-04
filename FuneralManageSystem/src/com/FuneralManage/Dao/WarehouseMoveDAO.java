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

}
