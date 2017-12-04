package com.FuneralManage.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.FuneralManage.Dao.ReeferRemainsSendDAO;
import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Utility.DateUtil;
import com.FuneralManage.entity.ReeferRemainsSend;

public class ReeferRemainsSendService extends BaseService{

	private ReeferRemainsSendDAO reeferRemainsSendDAO=new ReeferRemainsSendDAO(dataSource);
	
	/**
	 * 新增冷藏遗体送运
	 * @param reeferRemainsSend
	 * @return
	 */
	public boolean addReeferRemainsSend(ReeferRemainsSend reeferRemainsSend){
		return reeferRemainsSendDAO.addReeferRemainsSend(reeferRemainsSend);
	}
	
	/**
	 * 获取即将新增的送运订单编号
	 * @param carryTime
	 * @return
	 * @throws Exception
	 */
	public String createReeferSendNumber(String carryTime) throws Exception{
		String currentNumber="";
		if(carryTime==null||"".equals(carryTime))
			throw new MyException("接运时间不能为空");
		String yearAndMonthAndDay = DateUtil.getYearAndMonthAndDay(carryTime);
		String maxNumber = reeferRemainsSendDAO.getMaxReeferCarryNumber(yearAndMonthAndDay);
		if (maxNumber == null || maxNumber.equals("")){
			currentNumber = yearAndMonthAndDay + "01";
		}else{
			// 得到当前流水号
			int serialNumber = Integer.parseInt(maxNumber.substring(8));
			String currentSerialNumber = serialNumber + 1 < 10 ? "0" + (serialNumber + 1) : "" + (serialNumber + 1);
			currentNumber = yearAndMonthAndDay + currentSerialNumber;
		}
		return currentNumber;
	}
}
