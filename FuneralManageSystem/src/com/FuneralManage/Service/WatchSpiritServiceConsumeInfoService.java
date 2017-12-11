package com.FuneralManage.Service;

import com.FuneralManage.Dao.WatchSpiritServiceConsumeInfoDAO;
import com.FuneralManage.Utility.TransactionManager;
import com.FuneralManage.entity.WatchSpiritServiceConsumeInfo;

public class WatchSpiritServiceConsumeInfoService extends BaseService{
	private WatchSpiritServiceConsumeInfoDAO watchSpiritServiceConsumeInfoDAO=new WatchSpiritServiceConsumeInfoDAO(dataSource);
	private TransactionManager transactionManager=new TransactionManager(dataSource);
	
	/**
	 * 保存守灵服务消费信息
	 * @param watchSpiritGood
	 * @param watchSpiritService
	 * @param watchSpiritMeal
	 * @return
	 */
	public boolean addWatchSpiritServiceConsumeInfo(WatchSpiritServiceConsumeInfo watchSpiritGood,WatchSpiritServiceConsumeInfo watchSpiritService,WatchSpiritServiceConsumeInfo watchSpiritMeal){
		try{
			this.transactionManager.start();
			if(watchSpiritGood!=null&&watchSpiritGood.getBeCost()!=null){
				watchSpiritServiceConsumeInfoDAO.addWatchSpiritServiceConsumeInfoTran(watchSpiritGood);
			}
			if(watchSpiritService!=null&&watchSpiritService.getBeCost()!=null){
				watchSpiritServiceConsumeInfoDAO.addWatchSpiritServiceConsumeInfoTran(watchSpiritService);
			}
			if(watchSpiritMeal!=null&&watchSpiritMeal.getBeCost()!=null){
				watchSpiritServiceConsumeInfoDAO.addWatchSpiritServiceConsumeInfoTran(watchSpiritMeal);
			}
			this.transactionManager.commit();
		} catch (Exception e) {
			this.transactionManager.rollback();
			return false;
		} finally {
			this.transactionManager.close();
		}
		return true;
	}
}
