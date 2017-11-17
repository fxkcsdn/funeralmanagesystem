package com.FuneralManage.Action;

import com.FuneralManage.Service.QueryChosenGoodsService;
import com.opensymphony.xwork2.ActionSupport;

public class QueryChosenGoodsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String deadId ;
	private String returnString;
	


	public void setDeadId(String deadId) {
		this.deadId = deadId;
//		System.out.println("后台deadId赋值");
//		System.out.println(deadId);
	}

	
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	QueryChosenGoodsService QCGD=new QueryChosenGoodsService();
	public String QueryChosenGoods() throws Exception{
		returnString=QCGD.QueryChosenGoods(deadId);
		
		
		System.out.println(returnString);
		
		return "queryChosenGoods";
		
	}

}
