package com.FuneralManage.Action;


import java.util.Date;

import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Service.ReeferRemainsCarryService;
import com.FuneralManage.Service.ReeferServiceConsumeInfoService;
import com.FuneralManage.Service.RemainsReeferService;
import com.FuneralManage.entity.ReeferServiceConsumeInfo;
import com.opensymphony.xwork2.ActionSupport;

public class RemainsReeferAction extends ActionSupport{
	private ReeferServiceConsumeInfo reeferGood;
	private ReeferServiceConsumeInfo reeferService;
	private ReeferServiceConsumeInfo reeferMeal;
	private Date queryDate;
	private String returnString;// 返回的字符串
	private String reeferNo;
	public ReeferServiceConsumeInfo getReeferService() {
		return reeferService;
	}

	public void setReeferService(ReeferServiceConsumeInfo reeferService) {
		this.reeferService = reeferService;
	}

	public ReeferServiceConsumeInfo getReeferMeal() {
		return reeferMeal;
	}

	public void setReeferMeal(ReeferServiceConsumeInfo reeferMeal) {
		this.reeferMeal = reeferMeal;
	}

	
	public String getReeferNo() {
		return reeferNo;
	}

	public void setReeferNo(String reeferNo) {
		this.reeferNo = reeferNo;
	}

	public ReeferServiceConsumeInfo getReeferGood() {
		return reeferGood;
	}

	public void setReeferGood(ReeferServiceConsumeInfo reeferGood) {
		this.reeferGood = reeferGood;
	}

	
	public String getReturnString() {
		return returnString;
	}
	
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	
	/**
	 * 获取冷藏遗体接运信息
	 * @return
	 */
	public String queryRemainsCarry(){
		if(queryDate==null){
			throw new RuntimeException("RemainsReeferAction queryDate is null!");
		}
		returnString=new ReeferRemainsCarryService().getReeferRemainsCarryInfo(queryDate);
		return SUCCESS;
	}
	
	/**
	 * 获取遗体冷藏信息
	 * @return
	 */
	public String queryRemainsReefer(){
		if(queryDate==null){
			throw new RuntimeException("RemainsReeferAction queryDate is null!");
		}
		returnString=new ReeferRemainsCarryService().getReeferRemainsReefer(queryDate);
		return SUCCESS;
	}
	
	/**
	 * 增加冷藏服务消费信息
	 * @return
	 * @throws Exception 
	 */
	public String addReeferServiceConsume() throws Exception{	
		if(reeferNo==null){
			throw new MyException("冷藏柜不能为空！");
		}
		String reeferNumber=new RemainsReeferService().getReeferNumberByReeferNo(reeferNo);
		if(reeferNumber==null||reeferNumber.equals("")){
			throw new MyException("该冷藏柜没有对应的冷藏信息！");
		}
		if(reeferGood!=null){
			reeferGood.setReeferNumber(reeferNumber);
			reeferGood.setType("0");//0-物品；1-服务；2-用餐
		}
		if(reeferService!=null){
			reeferService.setReeferNumber(reeferNumber);
			reeferService.setType("1");//0-物品；1-服务；2-用餐
		}
		if(reeferMeal!=null){
			reeferMeal.setReeferNumber(reeferNumber);
			reeferMeal.setType("2");//0-物品；1-服务；2-用餐
		}
		boolean result=new ReeferServiceConsumeInfoService().addReeferServiceConsumeInfo(reeferGood,reeferService,reeferMeal);
		returnString=result?"{result:\"success\"}":"{result:\"failure\"}";
		return SUCCESS;
	}
	
}
