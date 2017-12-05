package com.FuneralManage.Dao;

import java.util.Date;

import javax.sql.DataSource;

import com.FuneralManage.Utility.DateUtil;
import com.FuneralManage.entity.ReeferRemainsCarry;

public class ReeferRemainsCarryDAO extends BaseDAO{

	public ReeferRemainsCarryDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取该日期当天的冷藏遗体接运列表
	 * @param date
	 * @return
	 */
	public String getReeferRemainsCarryInfo(Date date){
		String sql="select * from ReeferRemainsCarry where carryTime>=? and carryTime<?";
		return this.getForJson(sql, date,DateUtil.addDateOneDay(date));
	}
	
	/**
	 * 获取该日期当天的遗体冷藏列表
	 * @param date
	 * @return
	 */
	public String getReeferRemainsReefer(Date date){
		String sql="select * from remainsreefer where arrivalTime>=? and arrivalTime<?";
		return this.getForJson(sql, date,DateUtil.addDateOneDay(date));
	}
	
	/**
	 * 获取一个冷藏接运实例
	 * @param carryNumber
	 * @return reeferRemainsCarry or null
	 */
	public ReeferRemainsCarry getBeCostOfRemainCarry(String carryNumber){
		String sql="select * from ReeferRemainsCarry where carryNumber=?";
		return this.getForEntity(sql, ReeferRemainsCarry.class, carryNumber);
	}

}
