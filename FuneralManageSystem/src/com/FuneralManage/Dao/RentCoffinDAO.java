package com.FuneralManage.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public class RentCoffinDAO extends BaseDAO{

	public RentCoffinDAO(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 新增租棺信息
	 * @param contactName
	 * @param contactMobile
	 * @param startTime
	 * @param coffinNumber
	 * @param address
	 * @param rentNumber
	 * @param carCost
	 * @param carNumber
	 * @param bInternalCar
	 * @param carRealCost
	 * @return
	 */
	public boolean addRentConffinTran(String contactName, String contactMobile, String startTime, String coffinNumber,
			 String address, String rentNumber, String carCost, String carNumber, String bInternalCar, String carRealCost){
		String sql = "insert into rentCoffin(contactName,contactMobile,startTime,coffinNumber,address, rentNumber, carBeCost, carNumber, bInternalCar, carRealCost)values(?,?,?,?,?,?,?,?,?,?)";
		int result=this.saveOrUpdateOrDeleteTran(sql,contactName,contactMobile,startTime,coffinNumber,address, rentNumber, carCost, carNumber, bInternalCar, carRealCost );
		return result>0?true:false;
	}
	

}
