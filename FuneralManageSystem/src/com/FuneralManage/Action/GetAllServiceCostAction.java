package com.FuneralManage.Action;

import com.FuneralManage.Service.GetAllServiceService;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllServiceCostAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String returnString;
	private String urnName;
	private String itemName;
	private String goodsName;
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUrnName() {
		return urnName;
	}

	public void setUrnName(String urnName) {
		this.urnName = urnName;
	}

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	GetAllServiceService getAllServiceDao=new GetAllServiceService();
	
	public String getRemainsCarryCost(){
		returnString=getAllServiceDao.getRemainsCarryCostDao(deadId);
		return "getRemainsCarryCost";
	}
	
	public String getRentCoffinCost(){
		returnString=getAllServiceDao.getRentCoffinCostDao(deadId);
		return "getRentCoffinCost";
	}
	
	public String getWatchSpiritCost(){
		returnString=getAllServiceDao.getWatchSpiritCostDao(deadId);
		return "getWatchSpiritCost";
	}
	
	public String getDeadChosenUrnCost(){
		returnString=getAllServiceDao.getDeadChosenUrnCostDao(deadId);
		return "getDeadChosenUrnCost";
	}
	
	public String delChosenUrn(){
		returnString=getAllServiceDao.delChosenUrnDao(deadId,urnName);
		return "delChosenUrn";
	}
	
	public String getDeadServiceItemCost(){
		returnString=getAllServiceDao.getDeadServiceItemCostDao(deadId);
		return "getDeadServiceItemCost";
	}
	
	public String delServiceItem(){
		returnString=getAllServiceDao.delServiceItemDao(deadId,itemName);
		return "delServiceItem";
	}
	
	public String getFuneralGoodsCost(){
		returnString=getAllServiceDao.getFuneralGoodsCostDao(deadId);
		return "getFuneralGoodsCost";
	}
	
	public String delGoods(){
		returnString=getAllServiceDao.delGoodsDao(deadId,goodsName);
		return "delGoods";
	}
	
	public String calculateAllService(){
		int remainsCarryCost=getAllServiceDao.getRemainsCarryCost(deadId);
		int rentCoffinCost=getAllServiceDao.getRentCoffinCost(deadId);
		int watchSpiritCost=getAllServiceDao.getWatchSpiritCost(deadId);
		int coffinRealCost=getAllServiceDao.getCoffinRealCost(deadId);
		int deadChosenUrnCost=getAllServiceDao.getDeadChosenUrnCost(deadId);
		int deadServiceItemCost=getAllServiceDao.getDeadServiceItemCost(deadId);
		int deadFuneralGoodsCost=getAllServiceDao.getDeadFuneralGoodsCost(deadId);
		String allCost=remainsCarryCost+rentCoffinCost+watchSpiritCost+coffinRealCost+deadChosenUrnCost+deadServiceItemCost+deadFuneralGoodsCost+"";
		returnString="1111";
		return "calculateAllService";
	}
}
