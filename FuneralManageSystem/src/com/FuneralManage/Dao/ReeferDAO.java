package com.FuneralManage.Dao;

import javax.sql.DataSource;

public class ReeferDAO extends BaseDAO{

	public ReeferDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 判断该水晶棺当前状态，0-使用中(false)；1-空闲(true)
	 * @param reeferNo
	 * @return
	 */
	public boolean getReeferState(String reeferNo){
		String sql="select bAvailable from reefer where reeferNo=?";
		String result=this.getOneColumn(sql, reeferNo);
		return "0".equals(result)?false:true;
	}

}
