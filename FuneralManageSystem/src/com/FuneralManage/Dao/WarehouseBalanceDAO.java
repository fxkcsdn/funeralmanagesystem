package com.FuneralManage.Dao;

import java.math.BigDecimal;
import java.util.Map;

import javax.sql.DataSource;

public class WarehouseBalanceDAO extends BaseDAO {
	/**
	 * 有参构造器
	 * @param dataSource
	 */
	public WarehouseBalanceDAO(DataSource dataSource)
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

	/**
	 * 获取该仓库里的商品信息
	 * @param warehouseName 仓库名称
	 * @return 商品信息
	 */
	public String getGoodsInWarehouse(String warehouseName) {
		// TODO Auto-generated method stub
		String sql = "select * from warehouseBalance where warehouseName=? order by convert(goodsType using gbk) asc,"
				+ "convert(goodsName using gbk) asc";
		String result = this.getForJson(sql, warehouseName);
		return result;
	}

	/**
	 * 修改库存信息
	 * @param warehouseName 仓库名称
	 * @param goodsName 品名
	 * @param realAmount 实际数量
	 * @return true代表修改成功，false为失败
	 */
	public boolean updateWarehouseBalanceTran(String warehouseName,
			String goodsName, int realAmount) {
		// TODO Auto-generated method stub
		String sql = "update warehouseBalance set balanceNumber=? where warehouseName=? and goodsName=?";
		int result = this.saveOrUpdateOrDeleteTran(sql, realAmount, warehouseName, goodsName);
		if (result > 0) return true;
		return false;
	}

	/**
	 * 还原库存信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 更新结果，true代表更新成功，false为失败
	 */
	public boolean resetWarehouseBalanceTran(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		String sql = "update warehouseBalance wb,warehouseCheck wc set wb.balanceNumber=wb.balanceNumber"
				+ "-wc.amountDifference where wb.warehouseName=wc.warehouseName and wb.goodsName=wc.goodsName "
				+ "and wc.warehouseCheckNumber=?";
		int result = this.saveOrUpdateOrDeleteTran(sql, warehouseCheckNumber);
		if (result > 0) return true;
		return false;
	}
	
	

}
