package com.FuneralManage.Action;

import java.sql.Connection;
import java.sql.SQLException;

import com.FuneralManage.Service.ReeferService;
import com.FuneralManage.Service.RemainsReeferService;
import com.FuneralManage.Service.TransactionManager;
import com.FuneralManage.Utility.NumberUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AddRemainsReeferAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String returnString;// 返回的字符串
	private String carryNumber;// 接运编号
	private String deadId;// 逝者身份证号
	private String contactName;// 经办人姓名
	private String contactMobile;// 经办人手机
	private String reeferNo;// 冰柜号
	private String arrivalTime;// 到馆时间
	private String familyName;// 家属姓名
	private String familyMobile;// 家属电话
	private String staffName;// 业务人员姓名
	private String deposit;// 押金
	private String memo;// 备注
	private String sendRemainsUnit;// 送尸单位
	private String accidentAddress;// 事故地址
	
	public String getReturnString() 
	{
		return returnString;
	}
	
	public void setReturnString(String returnString) 
	{
		this.returnString = returnString;
	}
	
	public String getCarryNumber() 
	{
		return carryNumber;
	}

	public void setCarryNumber(String carryNumber) 
	{
		this.carryNumber = carryNumber;
	}

	public String getDeadId() 
	{
		return deadId;
	}

	public void setDeadId(String deadId) 
	{
		this.deadId = deadId;
	}

	public String getContactName() 
	{
		return contactName;
	}

	public void setContactName(String contactName) 
	{
		this.contactName = contactName;
	}

	public String getContactMobile() 
	{
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) 
	{
		this.contactMobile = contactMobile;
	}

	public String getReeferNo() 
	{
		return reeferNo;
	}

	public void setReeferNo(String reeferNo) 
	{
		this.reeferNo = reeferNo;
	}

	public String getArrivalTime() 
	{
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}

	public String getFamilyName() 
	{
		return familyName;
	}

	public void setFamilyName(String familyName) 
	{
		this.familyName = familyName;
	}

	public String getFamilyMobile() 
	{
		return familyMobile;
	}

	public void setFamilyMobile(String familyMobile) 
	{
		this.familyMobile = familyMobile;
	}

	public String getStaffName() 
	{
		return staffName;
	}

	public void setStaffName(String staffName) 
	{
		this.staffName = staffName;
	}

	public String getDeposit() 
	{
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getMemo() 
	{
		return memo;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getSendRemainsUnit() 
	{
		return sendRemainsUnit;
	}

	public void setSendRemainsUnit(String sendRemainsUnit) 
	{
		this.sendRemainsUnit = sendRemainsUnit;
	}

	public String getAccidentAddress() 
	{
		return accidentAddress;
	}

	public void setAccidentAddress(String accidentAddress) 
	{
		this.accidentAddress = accidentAddress;
	}

	/**
	 * 获取可用的冰柜号
	 * @return 冰柜号，JSON字符串
	 */
	public String getReefer()
	{
		ReeferService reeferService = new ReeferService();
		// 获取可用的冰柜号信息
		returnString = reeferService.getReefer();
		return "getReefer";
	}
	
	/**
	 * 保存遗体冷藏信息
	 * @return 
	 */
	public String addRemainsReefer()
	{
		ReeferService reeferService = new ReeferService();
		RemainsReeferService remainsReeferService = new RemainsReeferService();
		TransactionManager transactionManager = new TransactionManager();
		// 根据到馆时间创建最新冷藏编号
		String reeferNumber = NumberUtil.createRemainsReeferNumber(arrivalTime);
		try {
			// 事务开始
			transactionManager.start();
			// 获取Connnection对象
			Connection conn = transactionManager.getConnection();
			// 保存遗体冷藏信息
			remainsReeferService.addRemainsReefer(conn, reeferNumber, carryNumber, deadId, contactMobile, contactName,
					sendRemainsUnit, arrivalTime, familyName, reeferNo, accidentAddress, Integer.parseInt(deposit),
					staffName, familyMobile, memo);
			// 修改冰柜可用状态
			reeferService.updateReeferAvailable(conn, reeferNo);
			// 提交事务
			transactionManager.commit();
			returnString = "true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 回滚事务
			transactionManager.rollback();
			returnString = "false";
		}
		finally {
			// 关闭事务
			transactionManager.close();
		}
		return "getSaveResult";
	}
}
