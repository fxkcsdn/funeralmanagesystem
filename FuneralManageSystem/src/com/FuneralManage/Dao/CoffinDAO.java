package com.FuneralManage.Dao;

import javax.sql.DataSource;

public class CoffinDAO extends BaseDAO{

	public CoffinDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 更新水晶棺租借状态
	 * @param coffinNumber
	 * @return
	 */
	public boolean updateCoffinStateTran(int state,String coffinNumber){
		String sql = "update coffin set bAvailable=? where coffinNumber=?";
		int result=this.saveOrUpdateOrDeleteTran(sql, state,coffinNumber);
		return result>0?true:false;
	}

}
