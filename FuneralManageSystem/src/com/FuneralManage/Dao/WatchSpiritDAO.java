package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.WatchSpirit;

public class WatchSpiritDAO extends BaseDAO{

	public WatchSpiritDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 保存守灵信息
	 * @param watchSpirit
	 * @return
	 */
	public Boolean saveWatchSpiritTran(WatchSpirit watchSpirit){
		String sql="insert into watchSpirit(watchNumber, carryNumber, deadID, memberName, memberMobile, villaName, coffinNumber, startTime) values(?,?,?,?,?,?,?,?)";
		Integer result=this.saveOrUpdateOrDeleteTran(sql, watchSpirit.getWatchNumber(),
										watchSpirit.getCarryNumber(),
										watchSpirit.getDeadID(),
										watchSpirit.getMemberName(),
										watchSpirit.getMemberMobile(),
										watchSpirit.getVillaName(),
										watchSpirit.getCoffinNumber(),
										watchSpirit.getStartTime());
		return result>0?true:false;
	}
	
	/**
	 * 根据别墅号获取守灵信息
	 * @param villaName
	 * @return 要查询的实体，无结果时返回null 
	 */
	public WatchSpirit getWatchSpiritByVillaName(String villaName){
		String sql="select * from watchSpirit where villaName=? order by id desc limit 1";
		return this.getForEntity(sql, WatchSpirit.class, villaName);
	}
	
	/**
	 * 守灵结算更新
	 * @param watchSpirit
	 * @return
	 */
	public boolean watchSpiritBillTran(WatchSpirit watchSpirit){
		String sql="update watchSpirit set deadID=?,endTime=?,carryRealCost=?,villaBeCost=?,villaRealCost=?,coffinBeCost=?,"
				+ "coffinRealCost=?,serviceRealCost=?,allBeCost=?,allRealCost=? where watchNumber=?";
		Integer result= this.saveOrUpdateOrDeleteTran(sql, watchSpirit.getDeadID(),
												watchSpirit.getEndTime(),
											watchSpirit.getCarryRealCost(),
											watchSpirit.getVillaBeCost(),
											watchSpirit.getVillaRealCost(),
											watchSpirit.getCoffinBeCost(),
											watchSpirit.getCoffinRealCost(),
											watchSpirit.getServiceRealCost(),
											watchSpirit.getAllBeCost(),
											watchSpirit.getAllRealCost(),
											watchSpirit.getWatchNumber());
		return result>0?true:false;
	}
}
