package com.FuneralManage.Action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.FuneralManage.Service.AddOrderServiceService;
import com.FuneralManage.Service.AddServiceService;
import com.FuneralManage.entity.DeadCremation;
import com.FuneralManage.entity.DeadFuneralGoods;
import com.FuneralManage.entity.DeadLeaveRoom;
import com.FuneralManage.entity.DeadMakeBeauty;
import com.FuneralManage.entity.DeadUrn;
import com.opensymphony.xwork2.ActionSupport;

public class AddOrderServiceAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	private String deadId;
	private String urn;
	private String makeBeauty;
	private String leaveRoom;
	private String cremation;
	private String funeralGoods;
	private String returnString;

	public String getDeadId() {
		return deadId;
	}

	public void setDeadId(String deadId) {
		this.deadId = deadId;
	}
	
	public String getUrn() {
		return urn;
	}

	public void setUrn(String urn) {
		this.urn = urn;
	}

	public String getMakeBeauty() {
		return makeBeauty;
	}

	public void setMakeBeauty(String makeBeauty) {
		this.makeBeauty = makeBeauty;
	}

	public String getLeaveRoom() {
		return leaveRoom;
	}

	public void setLeaveRoom(String leaveRoom) {
		this.leaveRoom = leaveRoom;
	}

	public String getCremation() {
		return cremation;
	}

	public void setCremation(String cremation) {
		this.cremation = cremation;
	}

	public String getFuneralGoods() {
		return funeralGoods;
	}

	public void setFuneralGoods(String funeralGoods) {
		this.funeralGoods = funeralGoods;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	DeadUrn deadUrn=new DeadUrn();
	DeadMakeBeauty deadMakeBeauty=new DeadMakeBeauty();
	DeadLeaveRoom deadLeaveRoom=new DeadLeaveRoom();
	DeadCremation deadCremation=new DeadCremation();
	DeadFuneralGoods deadFuneralGoods=new DeadFuneralGoods();

	AddOrderServiceService addServiceDao=new AddOrderServiceService();
	
	public String addOrderService(){
		if(urn.length()>0){
			JSONObject jsonUrn = JSONObject.fromObject(urn);
			String name1=jsonUrn.getString("urnName");
			String beCost1=jsonUrn.getString("urnBeCost");
			String realCost1=jsonUrn.getString("urnRealCost");
			deadUrn.setUrnName(name1);
			deadUrn.setUrnBeCost(Integer.parseInt(beCost1));
			deadUrn.setUrnRealCost(Integer.parseInt(realCost1));
			returnString=addServiceDao.insertIntoUrn(deadId,deadUrn)+"01";
		}
		if(makeBeauty.length()>0){
			JSONObject jsonMakeBeauty = JSONObject.fromObject(makeBeauty);
			String name2=jsonMakeBeauty.getString("makeBeautyName");
			String beCost2=jsonMakeBeauty.getString("makeBeautyBeCost");
			String realCost2=jsonMakeBeauty.getString("makeBeautyRealCost");
			deadMakeBeauty.setMakeBeautyName(name2);
			deadMakeBeauty.setMakeBeautyBeCost(Integer.parseInt(beCost2));
			deadMakeBeauty.setMakeBeautyRealCost(Integer.parseInt(realCost2));
			returnString=addServiceDao.insertIntoService(deadId,deadMakeBeauty,"1")+"02";
		}
		if(leaveRoom.length()>0){
			JSONObject jsonLeaveRoom = JSONObject.fromObject(leaveRoom);
			String name3=jsonLeaveRoom.getString("leaveRoomName");
			String beCost3=jsonLeaveRoom.getString("leaveRoomBeCost");
			String realCost3=jsonLeaveRoom.getString("leaveRoomRealCost");
			deadLeaveRoom.setLeaveRoomName(name3);
			deadLeaveRoom.setLeaveRoomBeCost(Integer.parseInt(beCost3));
			deadLeaveRoom.setLeaveRoomRealCost(Integer.parseInt(realCost3));
			returnString=addServiceDao.insertIntoService(deadId,deadLeaveRoom)+"03";
		}
		if(cremation.length()>0){
			JSONObject jsonCremation = JSONObject.fromObject(cremation);
			String name4=jsonCremation.getString("cremationName");
			String beCost4=jsonCremation.getString("cremationBeCost");
			String realCost4=jsonCremation.getString("cremationRealCost");
			deadCremation.setCremationName(name4);
			deadCremation.setCremationBeCost(Integer.parseInt(beCost4));
			deadCremation.setCremationRealCost(Integer.parseInt(realCost4));
			returnString=addServiceDao.insertIntoService(deadId,deadCremation)+"04";
		}
		if(funeralGoods.length()>0){
			String funeralGoodsStr="["+funeralGoods+"]";
			JSONArray jsonArrayGoodsArray=JSONArray.fromObject(funeralGoodsStr);
			System.out.println(jsonArrayGoodsArray);
			if(jsonArrayGoodsArray.size()>0){
				for(int i=0;i<jsonArrayGoodsArray.size();i++){
					JSONObject funeralGoods = jsonArrayGoodsArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					deadFuneralGoods.setFuneralGoodsName(funeralGoods.getString("goodsName"));  // 得到 每个对象中的属性值
					deadFuneralGoods.setFuneralGoodsBeCost(Integer.parseInt(funeralGoods.getString("goodsBeCost")));
					deadFuneralGoods.setFuneralGoodsRealCost(Integer.parseInt(funeralGoods.getString("goodsRealCost")));
					returnString=addServiceDao.insertIntoGoods(deadId, deadFuneralGoods)+"0"+i;
				}
			}
		}
		returnString=addServiceDao.insertLastService(deadId,"00","01","0");
		return "addOrderService";
	}
}
//addOrderService