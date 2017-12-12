package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.WatchSpiritServiceConsumeInfo;

public class WatchSpiritServiceConsumeInfoDAO extends BaseDAO{

	public WatchSpiritServiceConsumeInfoDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 保存守灵服务信息
	 * @param watchSpiritServiceConsumeInfo
	 * @return
	 */
	public boolean addWatchSpiritServiceConsumeInfoTran(WatchSpiritServiceConsumeInfo watchSpiritServiceConsumeInfo){
		String sql="insert into watchspiritserviceconsumeinfo(watchNumber,type,name,number,beCost,consumeTime) values(?,?,?,?,?,?)";
		int result=this.saveOrUpdateOrDeleteTran(sql, 
				watchSpiritServiceConsumeInfo.getWatchNumber(),
				watchSpiritServiceConsumeInfo.getType(),
				watchSpiritServiceConsumeInfo.getName(),
				watchSpiritServiceConsumeInfo.getNumber(),
				watchSpiritServiceConsumeInfo.getBeCost(),
				watchSpiritServiceConsumeInfo.getConsumeTime());
		return result>0?true:false;
	}
	
	/**
	 * 获取该守灵编号对应的服务消费用总和
	 * @param watchNumber
	 * @return
	 */
	public String getBeCostSumOfWatchSpiritService(String watchNumber){
		String sql="SELECT SUM(beCost) AS BeCostSum FROM WatchSpiritServiceConsumeInfo WHERE watchNumber=?";
		String result=this.getOneColumn(sql, watchNumber);
		if(result==null||result.equals(""))
			return "0";
		else
			return result;
	}

}
