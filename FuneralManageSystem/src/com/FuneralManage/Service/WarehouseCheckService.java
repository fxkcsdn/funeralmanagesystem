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

	
}
