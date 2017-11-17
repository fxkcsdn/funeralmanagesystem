package com.FuneralManage.entity;

import java.sql.Date;

/*—————————————————————————————这是预约火化时传输数据用的类—————————————————————————————*/
public class OrderInfo 
{
	public String deadId;                 //逝者身份证号码
	public String deadName;               //逝者姓名
	public String contactName;            //联系人姓名
	public String contactMobile;          //联系人手机号码
	public String estimatedDate;          //预计到达时间
	public String factInTime;             //实际到达时间
	public String orderTime;              //预约时间
	public String orderNumber;            //预约编号
	public String bodyBeauty;
	public String farewareHall;
	public String chooseCremationType;
	public String getDeadId()
	{
		return deadId;
	}
	public void setDeadId(String deadId)
	{
		this.deadId = deadId;
	}
	public String getDeadName()
	{
		return deadName;
	}
	public void setDeadName(String deadName)
	{
		this.deadName = deadName;
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
	public String getEstimatedDate()
	{
		return estimatedDate;
	}
	public void setEstimatedDate(String estimatedDate)
	{
		this.estimatedDate = estimatedDate;
	}
	public String getFactInTime()
	{
		return factInTime;
	}
	public void setFactInTime(String factInTime)
	{
		this.factInTime = factInTime;
	}
	public String getOrderTime()
	{
		return orderTime;
	}
	public void setOrderTime(String orderTime)
	{
		this.orderTime = orderTime;
	}
	public String getOrderNumber()
	{
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}
	public String getBodyBeauty()
	{
		return bodyBeauty;
	}
	public void setBodyBeauty(String bodyBeauty)
	{
		this.bodyBeauty = bodyBeauty;
	}
	public String getFarewareHall()
	{
		return farewareHall;
	}
	public void setFarewareHall(String farewareHall)
	{
		this.farewareHall = farewareHall;
	}
	public String getChooseCremationType()
	{
		return chooseCremationType;
	}
	public void setChooseCremationType(String chooseCremationType)
	{
		this.chooseCremationType = chooseCremationType;
	}
	
}
