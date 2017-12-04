package com.FuneralManage.Dao;

import javax.sql.DataSource;

import com.FuneralManage.entity.ReeferRemainsSend;

public class ReeferRemainsSendDAO extends BaseDAO{

	public ReeferRemainsSendDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 新增冷藏遗体送运
	 * @param reeferRemainsSend
	 * @return
	 */
	public boolean addReeferRemainsSend(ReeferRemainsSend reeferRemainsSend){
		String sql="insert into reeferremainssend(reeferNumber,carryNumber,contactName,contactMobile,sendTime,address,carNumber,"
				+ "bInternalCar,carBeCost) values(?,?,?,?,?,?,?,?,?)";
		int result=this.saveOrUpdateOrDelete(sql, reeferRemainsSend.getReeferNumber(),
										reeferRemainsSend.getCarryNumber(),
										reeferRemainsSend.getContactName(),
										reeferRemainsSend.getContactMobile(),
										reeferRemainsSend.getSendTime(),
										reeferRemainsSend.getAddress(),
										reeferRemainsSend.getCarNumber(),
										reeferRemainsSend.getBInternalCar(),
										reeferRemainsSend.getCarBeCost());
		return result>0?true:false;
	}
	
	/**
	 * 获取该时间范围内最大冷藏送运单号(可能获取空集)
	 * @param carryTime
	 * @return
	 */
	public String getMaxReeferCarryNumber(String carryTime){
		String sendTimeTemp=carryTime+"%";
		String sql = "select max(carryNumber) as maxNumber from reeferRemainsSend where carryNumber like ?";
		return this.getOneColumn(sql, sendTimeTemp);
	}

}
