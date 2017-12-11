package com.FuneralManage.Service;

import java.util.Date;

import com.FuneralManage.Dao.CoffinDAO;
import com.FuneralManage.Dao.WatchSpiritCarryDAO;
import com.FuneralManage.Dao.VillaDAO;
import com.FuneralManage.Dao.WatchSpiritDAO;
import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Utility.DateUtil;
import com.FuneralManage.Utility.TransactionManager;
import com.FuneralManage.entity.WatchSpirit;
import com.FuneralManage.entity.WatchSpiritCarry;

public class WatchSpiritCarryService extends BaseService{
	
	private WatchSpiritCarryDAO watchSpiritCarryDAO=new WatchSpiritCarryDAO(dataSource);
	
	
	/**
	 * 新增守灵接运
	 * @param wathchSpiritCarry
	 * @return
	 */
	public boolean addWatchSpiritCarry(WatchSpiritCarry watchSpiritCarry){
		return watchSpiritCarryDAO.addWatchSpiritCarry(watchSpiritCarry);
	}
	
	/**
	 * 生成最新的守灵接运编号
	 * @param carryTime
	 * @return
	 * @throws Exception
	 */
	public String createReeferSendNumber(Date carryTime) throws Exception{
		String currentNumber="";
		System.out.println(carryTime);
		if(carryTime==null||"".equals(carryTime))
			throw new MyException("接运时间不能为空");
		String yearAndMonthAndDay = DateUtil.getYearAndMonthAndDay(carryTime);
		String maxNumber = watchSpiritCarryDAO.getMaxWatchSpiritCarryNumber(yearAndMonthAndDay);
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
