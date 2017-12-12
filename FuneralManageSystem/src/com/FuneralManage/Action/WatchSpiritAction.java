package com.FuneralManage.Action;

import com.FuneralManage.Exception.MyException;
import com.FuneralManage.Service.ReeferRemainsSendService;
import com.FuneralManage.Service.RemainsReeferService;
import com.FuneralManage.Service.WatchSpiritCarryService;
import com.FuneralManage.Service.WatchSpiritService;
import com.FuneralManage.Service.WatchSpiritServiceConsumeInfoService;
import com.FuneralManage.entity.ReeferServiceConsumeInfo;
import com.FuneralManage.entity.WatchSpirit;
import com.FuneralManage.entity.WatchSpiritCarry;
import com.FuneralManage.entity.WatchSpiritServiceConsumeInfo;
import com.opensymphony.xwork2.ActionSupport;

public class WatchSpiritAction extends ActionSupport{
	private WatchSpiritServiceConsumeInfo watchSpiritGood;
	private WatchSpiritServiceConsumeInfo watchSpiritService;
	private WatchSpiritServiceConsumeInfo watchSpiritMeal;
	private WatchSpiritCarry watchSpiritCarry;
	private WatchSpirit watchSpirit;
	private String  villaName;//别墅名
	private String returnString;// 返回的字符串
	

	public String getVillaName() {
		return villaName;
	}

	public void setVillaName(String villaName) {
		this.villaName = villaName;
	}

	public WatchSpiritServiceConsumeInfo getWatchSpiritGood() {
		return watchSpiritGood;
	}

	public void setWatchSpiritGood(WatchSpiritServiceConsumeInfo watchSpiritGood) {
		this.watchSpiritGood = watchSpiritGood;
	}

	public WatchSpiritServiceConsumeInfo getWatchSpiritService() {
		return watchSpiritService;
	}

	public void setWatchSpiritService(
			WatchSpiritServiceConsumeInfo watchSpiritService) {
		this.watchSpiritService = watchSpiritService;
	}

	public WatchSpiritServiceConsumeInfo getWatchSpiritMeal() {
		return watchSpiritMeal;
	}

	public void setWatchSpiritMeal(WatchSpiritServiceConsumeInfo watchSpiritMeal) {
		this.watchSpiritMeal = watchSpiritMeal;
	}

	public WatchSpiritCarry getWatchSpiritCarry() {
		return watchSpiritCarry;
	}

	public void setWatchSpiritCarry(WatchSpiritCarry watchSpiritCarry) {
		this.watchSpiritCarry = watchSpiritCarry;
	}

	public WatchSpirit getWatchSpirit() {
		return watchSpirit;
	}

	public void setWatchSpirit(WatchSpirit watchSpirit) {
		this.watchSpirit = watchSpirit;
	}

	

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	/**
	 * 保存守灵服务信息
	 * @return
	 * @throws Exception
	 */
	public String addWatchSpiritServiceConsume() throws Exception{	
		if(villaName==null)
			throw new MyException("守灵别墅不能为空！");
		String watchSpiritNumber=new WatchSpiritService().getWatchNumberByVillaNumber(villaName);
		if(watchSpiritNumber==null||watchSpiritNumber.equals(""))
			throw new MyException("该别墅没有对应的守灵信息！");
		if(watchSpiritGood!=null){
			watchSpiritGood.setWatchNumber(watchSpiritNumber);
			watchSpiritGood.setType("0");//0-物品；1-服务；2-用餐
		}
		if(watchSpiritService!=null){
			watchSpiritService.setWatchNumber(watchSpiritNumber);
			watchSpiritService.setType("1");//0-物品；1-服务；2-用餐
		}
		if(watchSpiritMeal!=null){
			watchSpiritMeal.setWatchNumber(watchSpiritNumber);
			watchSpiritMeal.setType("2");//0-物品；1-服务；2-用餐
		}
		boolean result=new WatchSpiritServiceConsumeInfoService().addWatchSpiritServiceConsumeInfo(watchSpiritGood, watchSpiritService, watchSpiritMeal);
		returnString=result?"success":"failure";
		return SUCCESS;
	}

	/**
	 * 登记守灵信息
	 * @return
	 * @throws Exception
	 */
	public String saveWatchSpirit()throws Exception{
		if(watchSpirit==null)
			throw new MyException("守灵信息为空！");
		String watchNumber=new WatchSpiritService().createWatchNumber();
		if(watchNumber==null||watchNumber.equals(""))
			throw new MyException("生成守灵编号错误！");
		watchSpirit.setWatchNumber(watchNumber);
		boolean result=new WatchSpiritService().addWatchSpirit(watchSpirit);
		returnString=result?"success":"failure";
		return SUCCESS;
	}

	/**
	 * 新增守灵接运订单
	 * @return
	 * @throws Exception
	 */
	public String addWatchSpiritCarry()throws Exception{
		if(watchSpiritCarry==null)
			throw new MyException("首领遗体接运信息为空！");
		String carryNumber=new WatchSpiritCarryService().createReeferSendNumber(watchSpiritCarry.getCarryTime());
		if(carryNumber==null||carryNumber.equals(""))
			throw new MyException("生成送运编号错误！");
		watchSpiritCarry.setCarryNumber(carryNumber);
		boolean result=new WatchSpiritCarryService().addWatchSpiritCarry(watchSpiritCarry);
		returnString=result?"success":"failure";
		return SUCCESS;
	}
	
	/**
	 * 根据别墅号获取相应的守灵应收费用
	 * @return
	 * @throws Exception
	 */
	public String getAllBeCostOfWatchSpirit() throws Exception{
		if(villaName==null)
			throw new MyException("别墅号不能为空！");
		returnString=new WatchSpiritService().getAllBeCostOfWatchSpirit(villaName);
		return SUCCESS;
	}
	
	/**
	 * 守灵结算
	 * @return
	 * @throws Exception
	 */
	public String watchSpiritBill() throws Exception{
		if(watchSpirit==null)
			throw new MyException("守灵信息不能为空！");
		if(villaName==null)
			throw new MyException("别墅不能为空！");
		//获取冷藏编号
		String watchNumber=new WatchSpiritService().getWatchNumberByVillaNumber(villaName);
		if(watchNumber==null||watchNumber.equals(""))
			throw new MyException("该别墅没有对应的守灵信息！");
		watchSpirit.setWatchNumber(watchNumber);;
		boolean result=new WatchSpiritService().watchSpiritBill(watchSpirit, villaName);
		returnString=result?"success":"failure";
		return SUCCESS;
	}
}
