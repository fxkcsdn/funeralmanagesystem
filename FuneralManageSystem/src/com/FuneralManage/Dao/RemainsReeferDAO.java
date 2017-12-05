package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.RemainsReefer;

public class RemainsReeferDAO extends BaseDAO{

	public RemainsReeferDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据冰柜号获取冷藏编号
	 * @param reeferNo
	 * @return
	 */
	public String getReeferNumberByReeferNo(String reeferNo){
		String sql="select reeferNumber from remainsreefer where reeferNo=? order by id desc limit 1";
		return this.getOneColumn(sql, reeferNo);
	}
	
	/**
	 * 根据租棺编号获取遗体冷藏实例
	 * @param reeferNo
	 * @return 要查询的实体，无结果时返回null 
	 */
	public RemainsReefer getRemainsReeferByReeferNo(String reeferNo){
		String sql="select * from remainsreefer where reeferNo=? order by id desc limit 1";
		return this.getForEntity(sql, RemainsReefer.class, reeferNo);
	}
	
	/**
	 * 冷藏结算
	 * @param remainsReefer
	 * @return
	 */
	public boolean reeferfillOfRemainsTran(RemainsReefer remainsReefer){
		String sql="update remainsreefer set deadID=?,endTime=?,carryRealCost=?,sendRealCost=?,reeferBeCost=?,"
				+ "reeferRealCost=?,serviceRealCost=?,allBeCost=?,allRealCost=? where reeferNumber=?";
		Integer result= this.saveOrUpdateOrDeleteTran(sql, remainsReefer.getDeadID(),
											remainsReefer.getEndTime(),
											remainsReefer.getCarryRealCost(),
											remainsReefer.getSendRealCost(),
											remainsReefer.getReeferBeCost(),
											remainsReefer.getReeferRealCost(),
											remainsReefer.getServiceRealCost(),
											remainsReefer.getAllBeCost(),
											remainsReefer.getAllRealCost(),
											remainsReefer.getReeferNumber());
		return result>0?true:false;
	}
}
