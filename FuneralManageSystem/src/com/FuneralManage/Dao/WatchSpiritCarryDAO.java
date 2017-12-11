package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.WatchSpiritCarry;


public class WatchSpiritCarryDAO extends BaseDAO{

	public WatchSpiritCarryDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 新增守灵接运
	 * @param wathchSpiritCarry
	 * @return
	 */
	public boolean addWatchSpiritCarry(WatchSpiritCarry wathchSpiritCarry){
		String sql="insert into watchSpiritCarry(carryNumber,contactName,contactMobile,carryTime,address,carNumber,"
				+ "bInternalCar,carBeCost) values(?,?,?,?,?,?,?,?)";
		int result=this.saveOrUpdateOrDelete(sql,
										wathchSpiritCarry.getCarryNumber(),
										wathchSpiritCarry.getContactName(),
										wathchSpiritCarry.getContactMobile(),
										wathchSpiritCarry.getCarryTime(),
										wathchSpiritCarry.getAddress(),
										wathchSpiritCarry.getCarNumber(),
										wathchSpiritCarry.getBInternalCar(),
										wathchSpiritCarry.getCarBeCost());
		return result>0?true:false;
	}
	
	/**
	 * 获取最大的守灵接运编号
	 * @param carryTime
	 * @return
	 */
	public String getMaxWatchSpiritCarryNumber(String carryTime){
		String sendTimeTemp=carryTime+"%";
		String sql = "select max(carryNumber) as maxNumber from watchSpiritCarry where carryNumber like ?";
		return this.getOneColumn(sql, sendTimeTemp);
	}
	
}
