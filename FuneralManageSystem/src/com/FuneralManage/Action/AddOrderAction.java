package com.FuneralManage.Action;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.FuneralManage.entity.OrderInfo;
import com.FuneralManage.Service.AddOrderService;
public class AddOrderAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private OrderInfo orderInfo=new OrderInfo();
	public OrderInfo getOrderInfo()
	{
		return orderInfo;
	}
	public void setOrderInfo(OrderInfo orderInfo)
	{
		this.orderInfo=orderInfo;
	}
	public String returnString;
	AddOrderService addOrderDao=new AddOrderService();
	public String registOrderInfo() throws SQLException{

		orderInfo.orderNumber=addOrderDao.getMaxOrderNumber(orderInfo);
		returnString=addOrderDao.orderInfoRegister(orderInfo);
		System.out.println(orderInfo);
		return "registOrderInfo";	
	}
}
