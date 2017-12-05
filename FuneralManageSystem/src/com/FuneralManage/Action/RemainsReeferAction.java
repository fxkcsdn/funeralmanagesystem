package com.FuneralManage.Action;


import java.util.Date;

import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Service.ReeferRemainsCarryService;
import com.FuneralManage.Service.ReeferRemainsSendService;
import com.FuneralManage.Service.ReeferServiceConsumeInfoService;
import com.FuneralManage.Service.RemainsReeferService;
import com.FuneralManage.Utility.NumberUtil;
import com.FuneralManage.entity.ReeferRemainsSend;
import com.FuneralManage.entity.ReeferServiceConsumeInfo;
import com.FuneralManage.entity.RemainsReefer;
import com.opensymphony.xwork2.ActionSupport;

public class RemainsReeferAction extends ActionSupport{
	private ReeferServiceConsumeInfo reeferGood;
	private ReeferServiceConsumeInfo reeferService;
	private ReeferServiceConsumeInfo reeferMeal;
	private ReeferRemainsSend reeferRemainsSend;
	private RemainsReefer remainsReefer;
	private Date queryDate;
	private String returnString;// 返回的字符串
	private String reeferNo;
	
	public RemainsReefer getRemainsReefer() {
		return remainsReefer;
	}

	public void setRemainsReefer(RemainsReefer remainsReefer) {
		this.remainsReefer = remainsReefer;
	}

	public ReeferRemainsSend getReeferRemainsSend() {
		return reeferRemainsSend;
	}

	public void setReeferRemainsSend(ReeferRemainsSend reeferRemainsSend) {
		this.reeferRemainsSend = reeferRemainsSend;
	}
	
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
		if(reeferNo==null)
			throw new MyException("冷藏柜不能为空！");
		String reeferNumber=new RemainsReeferService().getReeferNumberByReeferNo(reeferNo);
		if(reeferNumber==null||reeferNumber.equals(""))
			throw new MyException("该冷藏柜没有对应的冷藏信息！");
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
	
	/**
	 * 获取最新接运编号
	 * @return 接运编号，JSON格式字符串
	 */
	public String getReeferRemainsSendNumber() throws Exception{
		// 获取接运编号
		if(reeferRemainsSend==null)
			throw new MyException("冷藏遗体送运时间为空！");
		String carryNumber=new ReeferRemainsSendService().createReeferSendNumber(reeferRemainsSend.getSendTime());
		returnString = "{carryNumber:\"" +carryNumber  + "\"}";
	    return SUCCESS;
	}
	/**
	 * 新增冷藏遗体送运
	 * @return
	 * @throws Exception
	 */
	public String addReeferRemainsSend()throws Exception{
		if(reeferRemainsSend==null)
			throw new MyException("冷藏遗体送运信息为空！");
		if(reeferNo==null)
			throw new MyException("冷藏柜不能为空！");
		//获取冷藏编号
		String reeferNumber=new RemainsReeferService().getReeferNumberByReeferNo(reeferNo);
		if(reeferNumber==null||reeferNumber.equals(""))
			throw new MyException("该冷藏柜没有对应的冷藏信息！");
		//获取送运编号
		System.out.println("time="+reeferRemainsSend.getSendTime());
		String carryNumber=new ReeferRemainsSendService().createReeferSendNumber(reeferRemainsSend.getSendTime());
		if(carryNumber==null||carryNumber.equals(""))
			throw new MyException("生成送运编号错误！");
		reeferRemainsSend.setReeferNumber(reeferNumber);
		reeferRemainsSend.setCarryNumber(carryNumber);
		boolean result=new ReeferRemainsSendService().addReeferRemainsSend(reeferRemainsSend);
		returnString=result?"success":"failure";
		return SUCCESS;
	}
	
	/**
	 * 根据冰柜号获取相关的应收费用
	 * @param reeferNo
	 * @return
	 * @throws Exception
	 */
	public String getAllBeCostOfReeferRemains() throws Exception{
		if(reeferNo==null)
			throw new MyException("冷藏柜不能为空！");
		returnString=new RemainsReeferService().getAllBeCostOfReeferRemains(reeferNo);
		return SUCCESS;
	}
	
	
	public String reeferBillOfRemains() throws Exception{
		if(remainsReefer==null)
			throw new MyException("遗体冷藏信息不能为空！");
		if(reeferNo==null)
			throw new MyException("冷藏柜不能为空！");
		//获取冷藏编号
		String reeferNumber=new RemainsReeferService().getReeferNumberByReeferNo(reeferNo);
		if(reeferNumber==null||reeferNumber.equals(""))
			throw new MyException("该冷藏柜没有对应的冷藏信息！");
		remainsReefer.setReeferNumber(reeferNumber);
		boolean result=new RemainsReeferService().reeferfillOfRemains(remainsReefer,reeferNo);
		returnString=result?"success":"failure";
		return SUCCESS;
	}
}
