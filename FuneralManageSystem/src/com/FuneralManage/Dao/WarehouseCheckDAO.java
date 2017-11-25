package com.FuneralManage.Dao;

import java.util.Map;

import javax.sql.DataSource;

public class WarehouseCheckDAO extends BaseDAO {
	/**
	 * 有参构造器
	 * @param dataSource
	 */
	public WarehouseCheckDAO(DataSource dataSource)
	{
		super(dataSource);
	}

	/**
	 * 添加盘点信息
	 * @param warehouseCheckNumber 盘点单号
	 * @param map 商品信息
	 * @param true代表添加成功，false代表失败
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
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseCheckNumber, staffName, checkDate, warehouseName
				, goodsName, normalAmount, realAmount, amountDifference);
		if (result > 0) return true;
		return false;
	}

}
