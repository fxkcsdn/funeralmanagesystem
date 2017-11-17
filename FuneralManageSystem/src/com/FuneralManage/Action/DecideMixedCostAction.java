package com.FuneralManage.Action;

import com.FuneralManage.Service.DecideMixedCostService;
import com.opensymphony.xwork2.ActionSupport;

public class DecideMixedCostAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String remainsCarryRealCost;
	private String rentCrystalRealCost;
	private String rentCrystalCarRealCost;
	private String watchSpiritVillaRealCost;
	private String watchSpiritCoffinRealCost;
	private String returnString;
	
	public String getDeadId() {
		return deadId;
	}
	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	public String getRemainsCarryRealCost() {
		return remainsCarryRealCost;
	}
	public void setRemainsCarryRealCost(String remainsCarryRealCost) {
		this.remainsCarryRealCost = remainsCarryRealCost;
	}
	public String getRentCrystalRealCost() {
		return rentCrystalRealCost;
	}
	public void setRentCrystalRealCost(String rentCrystalRealCost) {
		this.rentCrystalRealCost = rentCrystalRealCost;
	}
	public String getRentCrystalCarRealCost() {
		return rentCrystalCarRealCost;
	}
	public void setRentCrystalCarRealCost(String rentCrystalCarRealCost) {
		this.rentCrystalCarRealCost = rentCrystalCarRealCost;
	}
	public String getWatchSpiritVillaRealCost() {
		return watchSpiritVillaRealCost;
	}
	public void setWatchSpiritVillaRealCost(String watchSpiritVillaRealCost) {
		this.watchSpiritVillaRealCost = watchSpiritVillaRealCost;
	}
	public String getWatchSpiritCoffinRealCost() {
		return watchSpiritCoffinRealCost;
	}
	public void setWatchSpiritCoffinRealCost(String watchSpiritCoffinRealCost) {
		this.watchSpiritCoffinRealCost = watchSpiritCoffinRealCost;
	}
	public String getReturnString() {
		return returnString;
	}
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	DecideMixedCostService decideMixedCostDao=new DecideMixedCostService();
	public String decideMixedCost(){
		returnString=decideMixedCostDao.updateRealCost(deadId,remainsCarryRealCost,watchSpiritVillaRealCost,watchSpiritCoffinRealCost,rentCrystalRealCost,rentCrystalCarRealCost);
		return "decideMixedCost";
	}
}
