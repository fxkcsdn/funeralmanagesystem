package com.FuneralManage.Dao;

import javax.sql.DataSource;

public class VillaDAO extends BaseDAO{

	public VillaDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 更新别墅租借状态
	 * @param state
	 * @param villaNumber
	 * @return
	 */
	public boolean updateVillaStateTran(int state,String villaNumber){
		String sql = "update villa set bAvailable=? where villaNumber=?";
		int result=this.saveOrUpdateOrDeleteTran(sql, state,villaNumber);
		return result>0?true:false;
	}

	/**
	 * 判断该别墅当前状态，0-使用中(false)；1-空闲(true)
	 * @param villaNumber
	 * @return
	 */
	public boolean getVillaState(String villaNumber){
		String sql="select bAvailable from Villa where villaNumber=?";
		String result=this.getOneColumn(sql, villaNumber);
		return "0".equals(result)?false:true;
	}
}
