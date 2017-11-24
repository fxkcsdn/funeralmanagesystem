package com.FuneralManage.Dao;

import java.math.BigDecimal;
import java.util.Map;

import javax.sql.DataSource;

public class WarehouseBalanceDao extends BaseDAO {
	/**
	 * 有参构造器
	 * @param dataSource
	 */
	public WarehouseBalanceDao(DataSource dataSource)
	{
		super(dataSource);
	}

	/**
	 * 添加商品信息
	 * @param warehouseName 仓库名称
	 * @param map 一条商品信息
	 * @return true代表添加成功，false为失败
	 */
	public boolean addGoodTran(String warehouseName, Map<String, String> map) {
		// TODO Auto-generated method stub
		String goodsType = map.get("goodsType");
		String goodsName = map.get("goodsName");
		String unit = map.get("unit");
		BigDecimal sellPrice = new BigDecimal(map.get("sellPrice"));
		int balanceNumber = Integer.parseInt(map.get("balanceNumber"));
		String sql = "insert into warehouseBalance values(?,?,?,?,?,?)";
		// 保存商品信息
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseName, goodsType, goodsName, unit
				, balanceNumber, sellPrice);
		if (result > 0) return true;
		return false;
	}
	
	

}
