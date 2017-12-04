package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.ReeferServiceConsumeInfo;

public class ReeferServiceConsumeInfoDAO extends BaseDAO{

	public ReeferServiceConsumeInfoDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 新增冷藏服务消费信息
	 * @param reeferServiceConsumeInfo
	 * @return
	 */
	public boolean addReeferServiceConsumeInfoTran(ReeferServiceConsumeInfo reeferServiceConsumeInfo){
		String sql="insert into reeferServiceConsumeInfo(reeferNumber,type,name,number,beCost,consumeTime) values(?,?,?,?,?,?)";
		int result=this.saveOrUpdateOrDeleteTran(sql, 
				reeferServiceConsumeInfo.getReeferNumber(),
				reeferServiceConsumeInfo.getType(),
				reeferServiceConsumeInfo.getName(),
				reeferServiceConsumeInfo.getNumber(),
				reeferServiceConsumeInfo.getBeCost(),
				reeferServiceConsumeInfo.getConsumeTime());
		return result>0?true:false;
	}
	

}
