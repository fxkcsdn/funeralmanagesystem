package com.FuneralManage.Action;

import com.FuneralManage.Service.AddFuneralGoodsService;
import com.opensymphony.xwork2.ActionSupport;

public class AddFuneralGoodsAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String goodsName;
	private int goodsBeCost;
	private int goodsRealCost;
	private String returnString;

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}

	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsBeCost() {
		return goodsBeCost;
	}

	public void setGoodsBeCost(int goodsBeCost) {
		this.goodsBeCost = goodsBeCost;
	}

	public int getGoodsRealCost() {
		return goodsRealCost;
	}
	
	public void setGoodsRealCost(int goodsRealCost) {
		this.goodsRealCost = goodsRealCost;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
	public String addFuneralGoods(){
		
		AddFuneralGoodsService addFuneralGoodsDao=new AddFuneralGoodsService();
		returnString=addFuneralGoodsDao.addFuneralGoodsDao(deadId,goodsName,goodsBeCost,goodsRealCost);
		return "addFuneralGoods";
	}
}
