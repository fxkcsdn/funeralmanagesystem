package com.FuneralManage.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.FuneralManage.Dao.WarehouseBalanceDAO;
import com.FuneralManage.Dao.WarehouseMoveDAO;
import com.FuneralManage.Utility.TransactionManager;

public class WarehouseMoveService extends BaseService {
	private WarehouseBalanceDAO warehouseBalanceDao = new WarehouseBalanceDAO(dataSource);
	private WarehouseMoveDAO warehouseMoveDAO = new WarehouseMoveDAO(dataSource);
	private TransactionManager transactionManager=new TransactionManager(dataSource);
	
	/**
	 * 保存调拨信息
	 * @param warehouseMoveNumber 调拨单号
	 * @param goodsList 商品信息
	 * @return 保存结果
	 */
	public boolean addWarehouseMove(String warehouseMoveNumber,
			List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String outWarehouse = map.get("outWarehouse");// 调出仓库
				String goodsName = map.get("goodsName");// 品名
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 添加调拨信息
				warehouseMoveDAO.addWarehouseMoveTran(warehouseMoveNumber, map);
				// 减少库存量
				warehouseBalanceDao.reduceWarehouseBalanceTran(outWarehouse, goodsName, moveAmount);
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
	 * 重置库存信息
	 * @param goodsList 商品信息
	 * @return 重置结果，true为重置成功，false为失败
	 */
	public boolean resetLocalWarehouseBalance(
			List<Map<String, String>> goodsList) {
		// TODO Auto-generated method stub
		try {
			transactionManager.start();
			// 删除本地调拨单
			warehouseMoveDAO.deleteLocalWarehouseMoveTran();
			// 遍历商品信息
			for (Map<String, String> map : goodsList)
			{
				String outWarehouse = map.get("outWarehouse");// 调出仓库
				String goodsName = map.get("goodsName");// 品名
				int moveAmount = Integer.parseInt(map.get("moveAmount"));// 调拨数量
				// 增加库存量
				warehouseBalanceDao.increaseWarehouseBalanceTran(outWarehouse, "", goodsName, "", moveAmount);	
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
