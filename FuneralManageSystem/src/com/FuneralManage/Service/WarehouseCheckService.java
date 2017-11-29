package com.FuneralManage.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.FuneralManage.Dao.WarehouseBalanceDAO;
import com.FuneralManage.Dao.WarehouseCheckDAO;
import com.FuneralManage.Utility.TransactionManager;

public class WarehouseCheckService extends BaseService {
	private WarehouseBalanceDAO warehouseBalanceDao = new WarehouseBalanceDAO(dataSource);
	private WarehouseCheckDAO warehouseCheckDAO = new WarehouseCheckDAO(dataSource);
	private TransactionManager transactionManager=new TransactionManager(dataSource);
	
	/**
	 * 新增盘点信息
	 * @param goodsList 商品信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 添加结果，true为添加成功，false为失败
	 */
	public boolean addWarehouseCheck(String warehouseCheckNumber, List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String warehouseName = map.get("warehouseName");// 盘点仓库
				String goodsName = map.get("goodsName");// 品名
				int realAmount = Integer.parseInt(map.get("realAmount"));// 实际数量
				// 添加盘点信息
				warehouseCheckDAO.addWarehouseCheckTran(warehouseCheckNumber, map);
				// 修改库存信息
				warehouseBalanceDao.updateWarehouseBalanceTran(warehouseName, goodsName, realAmount);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	/**
	 * 获取盘点单信息
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param warehouseName 盘点仓库
	 * @param pageNum 当前页数
	 * @param pageSize 每页记录数
	 * @return 盘点单信息
	 */
	public String getWarehouseChecks(String staffName, String startTime,
			String endTime, String warehouseName, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		// 获取盘点单信息
		return warehouseCheckDAO.getWarehouseChecks(staffName, startTime, endTime, warehouseName
				, pageNum, pageSize);
	}

	/**
	 * 获取分页数
	 * @param staffName 业务人员
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param warehouseName 盘点仓库
	 * @param pageSize 每页记录数
	 * @return 分页数
	 */
	public String getPageCount(String staffName, String startTime,
			String endTime, String warehouseName, int pageSize) {
		// TODO Auto-generated method stub
		// 获取分页数
		return warehouseCheckDAO.getPageCount(staffName, startTime, endTime, warehouseName, pageSize);
	}

	/**
	 * 获取盘点单主信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 盘点单主信息
	 */
	public String getWarehouseCheck(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		// 获取盘点单主信息
		return warehouseCheckDAO.getWarehouseCheck(warehouseCheckNumber);
	}

	/**
	 * 获取盘点单明细信息
	 * @param warehouseCheckNumber 盘点单号
	 * @return 盘点单明细信息
	 */
	public String getWarehouseCheckDetails(String warehouseCheckNumber) {
		// TODO Auto-generated method stub
		// 获取盘点单明细信息
		return warehouseCheckDAO.getWarehouseCheckDetails(warehouseCheckNumber);
	}

	/**
	 * 修改盘点单信息
	 * @param warehouseCheckNumber 盘点单号
	 * @param goodsList 商品信息
	 * @return 修改结果，true代表修改成功，false为失败
	 */
	public boolean updateWarehouseCheck(String warehouseCheckNumber,
			List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 删除原有的盘点单
			warehouseCheckDAO.deleteWarehouseCheckTran(warehouseCheckNumber);
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String warehouseName = map.get("warehouseName");// 盘点仓库
				String goodsName = map.get("goodsName");// 品名
				int realAmount = Integer.parseInt(map.get("realAmount"));// 实际数量
				// 添加盘点信息
				warehouseCheckDAO.addWarehouseCheckTran(warehouseCheckNumber, map);
				// 修改库存信息
				warehouseBalanceDao.updateWarehouseBalanceTran(warehouseName, goodsName, realAmount);
			}
			transactionManager.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			transactionManager.rollback();
			return false;
		} finally {
			transactionManager.close();
		}
		return true;
	}

	
}
