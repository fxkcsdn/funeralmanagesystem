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
}
