package com.FuneralManage.Action;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONException;
import net.sf.json.JSONArray;

import com.FuneralManage.Service.GetAuditService;

public class GetAuditAction 
{
	private static final long serialVersionUID = 1L;
	GetAuditService getAuditDao = new GetAuditService();  //这个对象用来调用Dao层的各个函数
	public String returnString;
	HttpServletRequest request;
	HttpServletResponse response;
	public void setServletResponse(HttpServletResponse arg0) 
	{
		this.response=arg0;
	}
	public void setServletRequest(HttpServletRequest arg0) 
	{
		this.request=arg0;
	}
	public String handleAudit() throws IOException, JSONException, SQLException   //此函数作为这个action的主函数，接收参数、处理查询结果并返回给前台
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		JSONArray jsonArray=new JSONArray();                    //数组用于存储查询结果
		jsonArray.add(getAuditDao.findUrn(startTime,endTime));  //骨灰盒1
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"03","01"));  //高档炉2
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"03","00"));  //普通炉3
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"01","00"));  //一般美容4
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","00"));  //普泽厅5
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","01"));  //福佑厅6
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","02"));  //祥和厅7
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","03"));  //永泰厅8
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","04"));  //博爱厅9
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","05"));  //仁孝厅10
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","06"));  //慈恩厅11
		jsonArray.add(getAuditDao.findTopFire(startTime,endTime,"02","07"));  //宝莲厅12
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"告别厅布置费")); //13
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"鲜花布置")); //14
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"告别照")); //15
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"摄像")); //16
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"打字布置")); //17
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"礼仪费")); //18
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"司仪")); //19
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"代打挽联")); //20
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"多媒体制作")); //21
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"休息室")); //22
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"包炉费")); //23
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"红布")); //24
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"灰布")); //25
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"纪念币")); //26
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"小商品")); //27   小商品
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"骨灰保护剂")); //28
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"万年青")); //29
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"环保寿毯")); //30
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"环保寿托"));//31
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"卫生袋")); //32
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"卫生棺")); //33
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"铺毯")); //34
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"铺金盖银")); //35
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"照片黑纱")); //36
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"手捧花")); //37
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"花篮")); //38
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"寿衣")); //39
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"花圈")); //40
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"代收现场接尸")); //代收现场接尸41
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"骨灰寄存费")); //骨灰寄存费42
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"更衣费")); //更衣费43
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"整容费")); //整容费44
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"特殊整容")); //特殊整容45
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"消毒费")); //消毒费46
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"劳务费")); //劳务费47
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"认尸费")); //认尸费48
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"抬尸费")); //抬尸费49
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"尸体移动费")); //尸体移动费50
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"防腐整理费")); //防腐整理费51
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"更衣场地费")); //更衣场地费52
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"验尸服务费")); //验尸服务费53
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"验尸场地费")); //验尸场地费54
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"验尸材料费")); //验尸材料费55
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"预定费")); //预定费56
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"骨灰包装费")); //装灰费57
		jsonArray.add(getAuditDao.findRemainsCarryCar(startTime,endTime,"1")); //代收遗体接运车费(1，本馆)-58
		jsonArray.add(getAuditDao.findRemainsCarryCar(startTime,endTime,"2")); //代收遗体接运车费(2，外部)-59
		jsonArray.add(getAuditDao.findRentCoffinCarryCar(startTime,endTime)); //送棺车费60
		jsonArray.add(getAuditDao.findRentCoffin(startTime, endTime)); //租棺费61
		jsonArray.add(getAuditDao.findReefer(startTime, endTime)); //遗体冷藏费62
		jsonArray.add(getAuditDao.findWatch(startTime, endTime)); //守灵室租用63
		jsonArray.add(getAuditDao.findGoods(startTime,endTime,"代收守灵餐费")); //代收守灵餐费64
		jsonArray.add(getAuditDao.findGoods(startTime, endTime,"其它"));   //其它 65
		jsonArray.add(getAuditDao.findGoods(startTime, endTime,"绢花布置"));   //绢花布置 66
		returnString = jsonArray.toString();
		System.out.println(returnString);
		return "getAudit";
	}
}
