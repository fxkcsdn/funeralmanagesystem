package com.FuneralManage.Service;

import com.FuneralManage.Dao.ReeferServiceConsumeInfoDAO;
import com.FuneralManage.entity.ReeferServiceConsumeInfo;
import com.FuneralManage.Utility.TransactionManager;

public class ReeferServiceConsumeInfoService extends BaseService{

	private ReeferServiceConsumeInfoDAO reeferServiceConsumeInfoDAO=new ReeferServiceConsumeInfoDAO(dataSource);
	private TransactionManager transactionManager=new TransactionManager(dataSource);
	
	/**
	 * 新增冷藏服务消费信息
	 * @param reeferGood
	 * @param reeferService
	 * @param reeferMeal
	 * @return
	 */
	public boolean addReeferServiceConsumeInfo(ReeferServiceConsumeInfo reeferGood,ReeferServiceConsumeInfo reeferService,ReeferServiceConsumeInfo reeferMeal){
		try{
			this.transactionManager.start();
			if(reeferGood!=null&&reeferGood.getBeCost()!=null){
				reeferServiceConsumeInfoDAO.addReeferServiceConsumeInfoTran(reeferGood);
			}
			if(reeferService!=null&&reeferService.getBeCost()!=null){
				reeferServiceConsumeInfoDAO.addReeferServiceConsumeInfoTran(reeferService);
			}
			if(reeferMeal!=null&&reeferMeal.getBeCost()!=null){
				reeferServiceConsumeInfoDAO.addReeferServiceConsumeInfoTran(reeferMeal);
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
