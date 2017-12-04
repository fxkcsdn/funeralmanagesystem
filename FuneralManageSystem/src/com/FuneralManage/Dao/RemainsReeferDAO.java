package com.FuneralManage.Dao;

import javax.sql.DataSource;

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
}
