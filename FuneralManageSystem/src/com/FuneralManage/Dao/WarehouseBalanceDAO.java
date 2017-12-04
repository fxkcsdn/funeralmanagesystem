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

	/**
	 * 获取该仓库商品种类信息
	 * @param warehouseName 仓库名称
	 * @return 商品种类信息
	 */
	public String getGoodsTypesInWarehouse(String warehouseName) {
		// TODO Auto-generated method stub
		String sql = "select distinct goodsType from warehouseBalance where warehouseName=?";
		String result = this.getForJson(sql, warehouseName);
		return result;
	}

	/**
	 * 获取该仓库品名信息
	 * @param warehouseName 仓库名称
	 * @param goodsType 商品种类
	 * @return 品名信息
	 */
	public String getGoodsNamesInWarehouse(String warehouseName, String goodsType) {
		// TODO Auto-generated method stub
		String sql = "select distinct goodsName from warehouseBalance where warehouseName=? and goodsType=?";
		String result = this.getForJson(sql, warehouseName, goodsType);
		return result;
	}

	/**
	 * 获取该仓库商品单位和库存数量
	 * @param warehouseName 仓库名称
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @return 单位和库存数量
	 */
	public String getUnitAndNum(String warehouseName, String goodsType,
			String goodsName) {
		// TODO Auto-generated method stub
		String sql = "select distinct unit,balanceNumber from warehouseBalance where warehouseName=? and goodsType=? and goodsName=?";
		return this.getForJson(sql, warehouseName, goodsType, goodsName);
	}

	/**
	 * 减少库存量
	 * @param outWarehouse 调出仓库
	 * @param goodsName 品名
	 * @param moveAmount 调拨数量
	 * @return 更新结果，true为更新成功，false为失败
	 */
	public boolean reduceWarehouseBalanceTran(String outWarehouse,
			String goodsName, int moveAmount) {
		// TODO Auto-generated method stub
		String sql = "update warehouseBalance set balanceNumber=balanceNumber-? where warehouseName=? and goodsName=?";
		int result = this.saveOrUpdateOrDeleteTran(sql, moveAmount, outWarehouse, goodsName);
		if (result > 0) return true;
		return false;
	}

	/**
	 * 增加库存量
	 * @param inWarehouse 调入仓库
	 * @param goodsType 商品种类
	 * @param goodsName 品名
	 * @param unit 单位
	 * @param moveAmount 调拨数量
	 * @return 更新结果，true为更新成功，false为失败
	 */
	public boolean increaseWarehouseBalanceTran(String inWarehouse, String goodsType,
			String goodsName, String unit, int moveAmount) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from warehouseBalance where warehouseName=? and goodsName=?";
		String sql1 = "insert into warehouseBalance values(?,?,?,?,?,?)";
		String sql2 = "update warehouseBalance set balanceNumber=balanceNumber+? where warehouseName=? and goodsName=?";
		// 判断库存中是否有该商品
		int count = (int)this.getCountTran(sql, inWarehouse, goodsName);
		int result = 0;
		// 如果库存中有商品，则更新库存量
		if (count > 0)
		{
			result = this.saveOrUpdateOrDeleteTran(sql2, moveAmount, inWarehouse, goodsName);
		}
		// 否则，插入一条商品库存记录，库存数量为调拨数量
		else
		{
			result = this.saveOrUpdateOrDeleteTran(sql1, inWarehouse, goodsType, goodsName, unit, moveAmount, null);
		}
		if (result > 0) return true;
		return false;
	}

	/**
	 * 更新销售价
	 * @param inWarehouse 调入仓库
	 * @param goodsName 品名
	 * @return 更新结果，true为更新成功，false为失败
	 */
	public boolean updateSellPriceTran(String inWarehouse, String goodsName) {
		// TODO Auto-generated method stub
		String sql = "update warehouseBalance wb,goods g set wb.sellPrice=g.sellPrice where wb.goodsName=g.goodsName and wb.warehouseName=? and wb.goodsName=?";
		int result = this.saveOrUpdateOrDeleteTran(sql, inWarehouse, goodsName);
		if (result > 0) return true;
		return false;
	}
	
}
