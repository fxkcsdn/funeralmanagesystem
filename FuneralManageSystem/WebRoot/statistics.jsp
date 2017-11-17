<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;">
<title>欢迎使用殡葬智能化系统</title>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<script src="js/InfoQuery.js"></script>
<script src="js/changeService.js"></script>

<script src="js/json2.js"></script>
<script src="js/paging.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
<style type="text/css">
body,td,th {
	font-size: 18px;
}
</style>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">查询业务状态</li>
    <li class="TabbedPanelsTab" tabindex="0">查询业务报表</li>
    <li class="TabbedPanelsTab" tabindex="0">查询接运业务</li>
    <li class="TabbedPanelsTab" tabindex="0">查询租棺业务</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
       <div class="TabbedPanelsContent" style="text-align:center">
       <form name="form1" id="form1">
  <div id="tableExcel1"  align="center">
  
            <p>
            &nbsp;<label for="startTime">开始时间：</label>
              <input type="text" name="startTime" id="startTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})" >&nbsp;&nbsp;&nbsp;
              
              <label for="endTime">结束时间：</label>
              <input type="text" name="endTime" id="endTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">
              
            
<input type="button" value="查询"  onclick="return CremationQuery()"/>&nbsp;&nbsp;</p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;


           


   <br> 
   
   <div id="infoTable"  align="center">
   <table id="cremationTable" style="display:none" border="1" >
   
   <thead>   
          
            <tr>
            <th colspan="12" align="center"><b><font style="font-size:24px">东台市殡仪馆火化基本信息统计表</font></b>(东台分馆)</th> 
            </tr>
            <tr>
            <th>火化日期</th> 
            <th>序号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>发票号</th>
            <th>住址</th>
            <th>身份证号</th>
            
            <th>死亡日期</th>
            <th>死亡原因</th>
            <th>家属电话</th>
            <th>业务修改</th>
            
            
            </tr>            
            </thead>
            <tbody id="cremationInfo1">
            
            
            
            </tbody>
  </table> 
  
   <div id="Number1" style="display:none" class="pagination"></div>
   </div>
   </div>
   <hr>
   <div id="service" style="display:">当前业务状态
   <p><input type="hidden" id="remainsCarBeCost" value="0"> <input type="hidden" id="remainsCarRealCost" value="0">
   <input type="hidden" id="coffinRentBeCost" value="0"> <input type="hidden" id="coffinRentRealcost" value="0">
   <input type="hidden" id="villaBeCost" value="0"> <input type="hidden" id="villaRealCost" value="0">
   <input type="hidden" id="coffinCarBeCost" value="0"> <input type="hidden" id="coffinCarRealCost" value="0">
    <input type="hidden" id="villaCoffinBeCost" value="0"> <input type="hidden" id="villaCoffinrealCost" value="0">
   <input type="hidden" id="GatecremationType" value="0"> <input type="hidden" id="GatecremationTypeStatus" value="0">
   </p>
<p>   逝者姓名：<input type="text" id="servicedeadName" disabled="true"> 身份证号：<input type="text" id="deadId" >
      <input type="button" value="读取身份证" onclick="return ReadIDCard()"/>  <input type="button" value="点击查询" onclick="return getCremationServicef()"/> 
</p> 
   <p>骨灰盒：<input type="text" id="urnName" disabled="true" >
   <input type="checkbox" id="urnChooseBox"  onclick="return cancelUrnCost()">  
              <label for="urnChoose">骨灰盒类型：</label>
			  <select name="urnChoose" id="urnChoose" onchange="return getUrnBeCost()">
				<option>-请选择-</option>
			  </select>&nbsp;&nbsp;
			  <label for="urnBeCost">应收费用：</label>
        	  <input type="text" name="urnBeCost" id="urnBeCost" value="0" style="width: 99px;" readonly/>元&nbsp;&nbsp;
        	  <label for="urnRealCost">备注：</label>
        	  <input type="text" name="urnRealCost" id="urnRealCost" value="0" style="width: 95px; " onchange="return changeUrnRealCost()">元
        	  <input name="preUrnBeCost" id="preUrnBeCost" type="hidden" value="0"/>
        	  <input name="preUrnRealCost" id="preUrnRealCost" type="hidden" value="0"/>
        	  </p>
   <p>整容：<input type="text" id="beauty" disabled="true">状态：<input type="text" id="beautyStatus" disabled="true">
   <input type="checkbox" id="makeBeautyBox"  onclick="return cancelBeautyCost()"> 
			  <label for="makeBeautyGrade">整容类型：</label>
			  <select name="makeBeautyGrade" id="makeBeautyGrade" onChange="return getMakeBeautyBeCost()">
				<option>-请选择-</option>
				<option>整容</option>
			  </select>&nbsp;&nbsp;
        	  <label for="makeBeautyBeCost">应收费用：</label>
        	  <input type="text" name="makeBeautyBeCost" id="makeBeautyBeCost" value="0" style="width: 92px;" readonly/>元&nbsp;&nbsp;
        	  <label for="makeBeautyRealCost">备注：</label>
        	  <input type="text" name="makeBeautyRealCost" id="makeBeautyRealCost" value="0" onchange="return changeBeautyRealCost()" style="width: 86px; ">元
        	  <input name="preMakeBeautyBeCost" id="preMakeBeautyBeCost" type="hidden" value="0"/>
        	  <input name="preMakeBeautyRealCost" id="preMakeBeautyRealCost" type="hidden" value="0"/></p>
   <p>告别：<input type="text" id="farewell" disabled="true">状态：<input type="text" id="farewellStatus" disabled="true">
   <input type="checkbox" id="leaveRoomBox"  onclick="return cancelLeaveRoomCost()">  
			  <label for="leaveRoomGrade">告别厅类型：</label>
			  <select name="leaveRoomGrade" id="leaveRoomGrade" onChange="return getLeaveRoomBeCost()">
				<option>-请选择-</option>
			  </select>&nbsp;&nbsp;
			  <label for="leaveRoomBeCost">应收费用：</label>
        	  <input type="text" name="leaveRoomBeCost" id="leaveRoomBeCost" value="0" style="width: 92px;" readonly/>元&nbsp;&nbsp;
        	  <label for="leaveRoomRealCost">备注：</label>
        	  <input type="text" name="leaveRoomRealCost" id="leaveRoomRealCost" value="0" onchange="return changeLeaveRoomRealCost()" style="width: 86px; ">元
        	  <input name="preLeaveRoomBeCost" id="preLeaveRoomBeCost" type="hidden" value="0"/>
        	  <input name="preLeaveRoomRealCost" id="preLeaveRoomRealCost" type="hidden" value="0"/></p>
   <p>火化：<input type="text" id="cremationstove" disabled="true">状态：<input type="text" id="cremationStatus" disabled="true">
   <input type="checkbox" id="cremationBox"  onclick="return cancelCremationCost()"> 
			  <label for="cremationGrade">火化炉类型：</label>
			  <select name="cremationGrade" id="cremationGrade" onChange="return getCremationBeCost()">
				<option>-请选择-</option>
				<option>普通炉</option>
				<option>捡灰炉</option>
				<option>VIP区</option>
			  </select>&nbsp;&nbsp;
			  <label for="cremationBeCost">应收费用：</label>
        	  <input type="text" name="cremationBeCost" id="cremationBeCost" value="0" style="width: 92px;" readonly/>元&nbsp;&nbsp;
        	  <label for="cremationRealCost">备注：</label>
        	  <input type="text" name="cremationRealCost" id="cremationRealCost" value="0" onchange="return changeCremationRealCost()" style="width: 86px; ">元
        	  <input name="preCremationBeCost" id="preCremationBeCost" type="hidden" value="0"/>
        	  <input name="preCremationRealCost" id="preCremationRealCost" type="hidden" value="0"/></p>
        	  <div  id="funeralGoodsPart">
        	              
              <table border="1" width="95%" id="showallFuneralGoods" align="center">
              	<tr>
              		<td align="center" colspan="11">
              			<label for="funeralGoods"><b>丧葬物品及其他小商品选择</b></label>
              		</td>
              	</tr>
              	<tr align="center">
              		<td><b>丧葬物品名称</b></td>
              		<td><b>应收金额</b></td>
              		<td><b>备注</b></td>
              		<td><b>添加</b></td>
              		<td><b></b></td>
              		<td><b>&nbsp;</b></td>
              		<td><b></b></td>
              		<td><b>丧葬物品名称</b></td>
              		<td><b>应收金额</b></td>
              		<td><b>备注</b></td>
              		<td><b>添加</b></td>
              	</tr>
              </table>
               <p>
              <label for="allBeCost"><b>火化服务费用应收：</b></label>
              <input type="text" name="allBeCost" id="allBeCost" value="0" style="width: 100px;" readonly/>元&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="hidden" id="allBeCostHidden" value="0">
              <label for="allRealCost"><b>火化服务费用备注：</b></label>
              <input type="text" name="allRealCost" id="allRealCost" value="0" style="width: 100px;" readonly/>元
            </p>
            <p>
              <label for="theWholeCost"><b>总费用：</b></label>
              <input type="text" name="theWholeCost" id="theWholeCost" value="0" style="width: 100px;" readonly/>元
              <input type="hidden" id="lastWholeCost" value="0">
            </p>
              <p>
              <input type="button" name="printServiceList" id="printServiceList" value="生成服务单据" onclick="return outPutServiceList
()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <input type="button" name="printServiceList" id="printServiceList" value="打印服务单据" onclick="return outPrintServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
              <input type="button" name="ensureChoose" id="ensureChoose" value="确认服务选择" onclick="return changeService()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;<br>
            </p>
             </div>
             <hr>
             <div id="goodsPartListPrint">
            <table align="center"  border="0" width="95%" id="basicInfo" style="font-size:12px;border-collapse:collapse;border:0px solid #000;">
            	
            	<tr>
              		<td align="center" colspan="12">
              			<label for="funeralGoods"><b><font style="font-size:22px">东台市殡仪馆服务收费单</font></b></label>(东台分馆)
              		</td>
              	</tr>
              	<tr>
              		<td><b><font style="font-size:12px">当日序号：</font></b></td>
	              	<td id="currentDateTh2"></td>
	              	<td colspan="1"><b><font style="font-size:12px">No:</font></b></td>
	              	<td id="no" colspan="2"></td>
	              	<td colspan="1"><b><font style="font-size:12px">日期:</font></b></td>
	              	<td id="taxDate" colspan="6"></td>
              	</tr>
              	<tr>
	              	<td colspan="1"><b><font style="font-size:12px">姓名:</font></b></td>
	              	<td colspan="1" id="name"></td>
	              	<td colspan="1"><b><font style="font-size:12px">性别:</font></b></td>
	              	<td colspan="1" id="sex"></td>
	              	<td colspan="1"><b><font style="font-size:12px">年龄:</font></b></td>
	              	<td colspan="1" id="age"></td>
	              	<td colspan="1"><b><font style="font-size:12px">住址:</font></b></td>
	              	<td colspan="5" id="address"></td>
              	</tr>
            	
            </table>
            <table  align="center" border="2" width="95%" id="allGoods" style="font-size:12px;border-collapse:collapse;border:1px solid #000;">
              	
              	<tr align="left">
              	
              		<td><b><font style="font-size:16px">名称</font></b></td>
              		<td><b><font style="font-size:16px">摘要</font></b></td>
              		<td><b><font style="font-size:16px">应收</font></b></td>
              		<td><b><font style="font-size:16px">备注</font></b></td>
              		
              		<td><b><font style="font-size:16px">名称</font></b></td>
              		<td><b><font style="font-size:16px">摘要</font></b></td>
              		<td><b><font style="font-size:16px">应收</font></b></td>
              		<td><b><font style="font-size:16px">备注</font></b></td>
              		
              		<td><b><font style="font-size:16px">名称</font></b></td>
              		<td><b><font style="font-size:16px">摘要</font></b></td>
              		<td><b><font style="font-size:16px">应收</font></b></td>
              		<td><b><font style="font-size:16px">备注</font></b></td>
              	</tr>
              	<tr>
              	    <td ><b>火化炉</b></td>
	              	<td id="cremation"></td>
	              	<td id="cremation1"></td>
	              	<td id="cremation2"></td>
	              	
	              	<td ><b>告别厅</b></td>
	              	<td id="farewelll"></td>
	              	<td id="farewell1"></td>
	              	<td id="farewell2"></td>
	              	
	              	<td><b>骨灰盒</b></td>
	              	<td id="urn"></td>
	              	<td id="urn1"></td>
	              	<td id="urn2"></td>
              	</tr>
              	<tr>
	              	<td ><b>整容</b></td>
	              	<td id="face"></td>
	              	<td id="face1"></td>
	              	<td id="face2"></td>
	              	            
	              	<td ><b>租用冰棺</b></td>
	              	<td id="crystal"></td>
	              	<td id="crystal1"></td>
	              	<td id="crystal2"></td>
	              	<td ><b>守灵</b></td>
	              	<td id="watch"></td>
	              	<td id="watch1"></td>
	              	<td id="watch2"></td>
	              	<tr>
	              	<td><b>遗体接运</b></td>
	              	<td id="car"></td>
	              	<td id="car1"></td>
	              	<td id="car2"></td>
	              	<td><b>用棺接运</b></td>
	              	<td id="car3"></td>
	              	<td id="car4"></td>
	              	<td id="car5"></td>

	              	<td></td>
	              	<td></td>
	              	<td></td>
	              	<td></td>
              	</tr>
              	
              	<tbody id="alladd"></tbody>
              	
              	<tr>
              		<td><b>费用应收</b></td>
	              	<td colspan="1" id="beCost"></td>
	              	
	              	<td><b>备注</b></td>
	              	<td colspan="1" id="charge"></td>
	              	
	              	<td><b>费用实收</b></td>
	              	<td colspan="1" id="total"></td>
	              	
	              	<td colspan="1"><b>大写</b></td>
	              	<td colspan="5" id="capitalMoney"></td>
              	</tr>
              	<tr>
	              	<td colspan="1"><b>开票员</b></td>
	              	<td colspan="3"></td>
	              	<td colspan="1"><b>管理员</b></td>
	              	<td colspan="7"></td>
              	</tr>
              	<tr>
	              	<td colspan="4"><b>(明白消费  确认签字) 客户主办人签字</b></td>
	              	<td colspan="8"></td>
              	</tr>
              </table>
              </div>
   </div>
       
           	

   </form>
    </div>    
    <div class="TabbedPanelsContent" style="text-align:center">
    	<table>
    		<tr>
    			<td>开始日期：</td>
    			<td><input id="startTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"/></td>
    			<td>结束日期：</td>
    			<td><input id="endTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}',startDate:'#F{$dp.$D(\'startTime\',{d:+1})}'})"/></td>
    			<td></td>
    			<td><input type="button" value="查询" onclick="searchInfo()" style="width: 60px;height:25px;"/></td>
    		</tr>
    	</table>
    	<hr/>
   		 <div id="tabAll" style="display:none;text-align:center">
    		<h4>东台市殡仪馆东台分馆营业汇总表</h4>
    		<div style="text-align:center">
		 <table id="ta" border="1px" cellspacing="0px" style="border-collapse:collapse;width:100%;text-align:center">
			<tr>
				<th>收费名称</th>
				<th>数量</th>
				<th>金额</th>
				<th>收费名称</th>
				<th>数量</th>
				<th>金额</th>
				<th>收费名称</th>
				<th>数量</th>
				<th>金额</th>
			</tr>
			<tr>
				<td>普通炉火化费</td>
				<td id="pthhlsl"></td>
				<td id="pthhlje"></td>
				<td>代收车费1</td>
				<td id="dscf1sl"></td>
				<td id="dscf1je"></td>
				<td>红布</td>
				<td id="hongbsl"></td>
				<td id="hongbje"></td>
			</tr>
			<tr>
				<td>高档炉火化费</td>
				<td id="gdhhlsl"></td>
				<td id="gdhhlje"></td>
				<td>代收车费2</td>
				<td id="dscf2sl"></td>
				<td id="dscf2je"></td>
				<td>灰布</td>
				<td id="huibsl"></td>
				<td id="huibje"></td>
			</tr>
			<tr>
				<td>永泰厅</td>
				<td id="yttsl"></td>
				<td id="yttje"></td>
				<td>代收现场接尸</td>
				<td id="dsxcjssl"></td>
				<td id="dsxcjsje"></td>
				<td>纪念币</td>
				<td id="jnbsl"></td>
				<td id="jnbje"></td>
			</tr>
			<tr>
				<td>博爱厅</td>
				<td id="batsl"></td>
				<td id="batje"></td>
				<td>送棺费</td>
				<td id="sgfsl"></td>
				<td id="sgfje"></td>
				<td>小商品</td>
				<td id="xhqsl"></td>
				<td id="xhqje"></td>
			</tr>
			<tr>
				<td>福佑厅</td>
				<td id="fytsl"></td>
				<td id="fytje"></td>
				<td>告别厅布置费</td>
				<td id="gbtbzsl"></td>
				<td id="gbtbzje"></td>
				<td>骨灰保护剂</td>
				<td id="hlbsl"></td>
				<td id="hlbje"></td>
			</tr>
			<tr>
				<td>祥和厅</td>
				<td id="xhtsl"></td>
				<td id="xhtje"></td>
				<td>鲜花布置</td>
				<td id="xhbzfsl"></td>
				<td id="xhbzfje"></td>
				<td>万年青</td>
				<td id="wnqsl"></td>
				<td id="wnqje"></td>
			</tr>
			<tr>
				<td>仁孝厅</td>
				<td id="rxtsl"></td>
				<td id="rxtje"></td>
				<td>告别照</td>
				<td id="zxfsl"></td>
				<td id="zxfje"></td>
				<td>环保寿毯</td>
				<td id="hbstsl"></td>
				<td id="hbstje"></td>
			</tr>
			<tr>
				<td>慈恩厅</td>
				<td id="cetsl"></td>
				<td id="cetje"></td>
				<td>摄像</td>
				<td id="sxfsl"></td>
				<td id="sxfje"></td>
				<td>环保寿托</td>
				<td id="hbstuosl"></td>
				<td id="hbstuoje"></td>
			</tr>
			<tr>
				<td>宝莲厅</td>
				<td id="bltsl"></td>
				<td id="bltje"></td>
				<td>打字布置</td>
				<td id="dzfsl"></td>
				<td id="dzfje"></td>
				<td>卫生袋</td>
				<td id="wsdsl"></td>
				<td id="wsdje"></td>
			</tr>
			<tr>
				<td>普泽厅</td>
				<td id="pztsl"></td>
				<td id="pztje"></td>
				<td>司仪</td>
				<td id="syfsl"></td>
				<td id="syfje"></td>
				<td>卫生棺</td>
				<td id="wsgsl"></td>
				<td id="wsgje"></td>
			</tr>
			<tr>
				<td>遗体冷藏费</td>
				<td id="ytlcfsl"></td>
				<td id="ytlcfje"></td>
				<td>礼仪费</td>
				<td id="lyfsl"></td>
				<td id="lyfje"></td>
				<td>寿衣</td>
				<td id="shouyisl"></td>
				<td id="shouyije"></td>
			</tr>
			<tr>
				<td>更衣费</td>
				<td id="gyfsl"></td>
				<td id="gyfje"></td>
				<td>代打挽联</td>
				<td id="dbwlsl"></td>
				<td id="dbwlje"></td>
				<td>花圈</td>
				<td id="huaquansl"></td>
				<td id="huaquanje"></td>
			</tr>
			<tr>
				<td>一般化妆费</td>
				<td id="ybhzfsl"></td>
				<td id="ybhzfje"></td>
				<td>多媒体制作</td>
				<td id="dmtzzsl"></td>
				<td id="dmtzzje"></td>
				<td>骨灰盒</td>
				<td id="ghhsl"></td>
				<td id="ghhje"></td>
			</tr>
			<tr>
				<td>骨灰寄存费</td>
				<td id="ghjcfsl"></td>
				<td id="ghjcfje"></td>
				<td>休息室</td>
				<td id="hhxxssl"></td>
				<td id="hhxxsje"></td>
				<td>铺毯</td>
				<td id="ptsl"></td>
				<td id="ptje"></td>
			</tr>
			<tr>
				<td>整容费</td>
				<td id="zrfsl"></td>
				<td id="zrfje"></td>
				<td>特殊整容</td>
				<td id="tszrsl"></td>
				<td id="tszrje"></td>
				<td>铺金盖银</td>
				<td id="pjgysl"></td>
				<td id="pjgyje"></td>
			</tr>
			<tr>
				<td>消毒费</td>
				<td id="xdfsl"></td>
				<td id="xdfje"></td>
				<td>劳务费</td>
				<td id="lwfsl"></td>
				<td id="lwfje"></td>
				<td>照片黑纱</td>
				<td id="zphssl"></td>
				<td id="zphsje"></td>
			</tr>
			<tr>
				<td>认尸费</td>
				<td id="rsfsl"></td>
				<td id="rsfje"></td>
				<td>抬尸费</td>
				<td id="tsfsl"></td>
				<td id="tsfje"></td>
				<td>手捧花</td>
				<td id="spxhsl"></td>
				<td id="spxhje"></td>
			</tr>
			<tr>
				<td>尸体移动费</td>
				<td id="stydfsl"></td>
				<td id="stydfje"></td>
				<td>防腐整理费</td>
				<td id="ffzlfsl"></td>
				<td id="ffzlfje"></td>
				<td>花篮</td>
				<td id="hlsl"></td>
				<td id="hlje"></td>
			</tr>
			<tr>
				<td>更衣场地费</td>
				<td id="gycdfsl"></td>
				<td id="gycdfje"></td>
				<td>验尸服务费</td>
				<td id="ysfwfsl"></td>
				<td id="ysfwfje"></td>
				<td>验尸场地费</td>
				<td id="yscdfsl"></td>
				<td id="yscdfje"></td>
			</tr>
			<tr>
				<td>验尸材料费</td>
				<td id="ysclfsl"></td>
				<td id="ysclfje"></td>
				<td>守灵室租用</td>
				<td id="slszysl"></td>
				<td id="slszyje"></td>
				<td>代收守灵餐费</td>
				<td id="dslcfsl"></td>
				<td id="dslcfje"></td>
			</tr>
			<tr>
				<td>包炉</td>
				<td id="blsl"></td>
				<td id="blje"></td>
				<td>预定费</td>
				<td id="ydfsl"></td>
				<td id="ydfje"></td>
				<td>水晶棺出租</td>
				<td id="sjgczsl"></td>
				<td id="sjgczje"></td>
			</tr>
			<tr>
				<td>死婴</td>
				<td id="sysl"></td>
				<td id="syje"></td>
				<td>骨灰包装费</td>
				<td id="zhfsl"></td>
				<td id="zhfje"></td>
				<td>绢花布置</td>
				<td id="juanhuabuzhisl"></td>
				<td id="juanhuabuzhije"></td>
			</tr>
			<tr>
				<td>其它</td>
				<td id="othersl"></td>
				<td id="otherje"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>小计</td>
				<td></td>
				<td id="xiaoji1je"></td>
				<td>小计</td>
				<td></td>
				<td id="xiaoji2je"></td>
				<td>小计</td>
				<td></td>
				<td id="xiaoji3je"></td>
			</tr>
			<tr>
				<td>合计</td>
				<td></td>
				<td id="hejije"></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>发票号码：</td>
				<td></td>
				<td></td>
				<td></td>
				<td>作废号码：</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>往来</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>填制人：</td>
				<td></td>
				<td></td>
				<td></td>
				<td>日期：</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
    	</div>
    	<hr/>
    	<div style="text-align:right">
    	<input id="Button1" type="button" value="导出到EXCEL" style="width:100px;height:30px"  onclick="AutoExcel();" />
    	</div>
    </div>   
    </div>
    <div class="TabbedPanelsContent" style="text-align:center">查询接运业务</div>
    <div class="TabbedPanelsContent" style="text-align:center">查询租棺业务</div>       
</div> 
	<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"); 
	window.onload = initialization;
	function initialization() //页面加载

	{
		
		showGoods();		
		var myDate = new Date();
		var myYear = myDate.getFullYear();
		var myDay;
		var myMonth;
		var TmpMonth = myDate.getMonth()+1;
		if(TmpMonth<10)    //原生日历控件要求显示格式为"2016-02-04"，此处对所取的月份进行格式匹配
		{
			myMonth = "0"+TmpMonth;
		}
		else
		{
			myMonth = TmpMonth;
		}
		var TmpDay = myDate.getDate();
		if(TmpDay<10)     //原生日历控件要求显示格式为"2016-02-04"，此处对所取的日期进行格式匹配
		{
			myDay = "0"+TmpDay;
		}
		else
		{
			myDay = TmpDay;
		}
		document.getElementById("startTime").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("startTime2").value=myYear+"-"+myMonth+"-"+myDay;
		document.getElementById("endTime2").value=myYear+"-"+myMonth+"-"+myDay;
		CremationQuery();
	}
	
	function createXmlHttpRequest()  //发送http请求
	{
		var xmlHttpRequest;
		if(window.ActiveXObject)
		{ //如果是IE浏览器    
        return new ActiveXObject("Microsoft.XMLHTTP");    
    	}
    	else if(window.XMLHttpRequest)
    	{ //非IE浏览器    
        	return new XMLHttpRequest();    
    	}     
	} 
	
	function searchInfo() //提交时间，进行查询
	{
		var startTime=document.getElementById("startTime").value;
		if(startTime!="")
		{
			getAudit();           //以时间为参数，跟后台交互
		}
		else
		{
			alert("还没选择时间");
		}
	}
	
	function getAudit()    //以时间为参数，跟后台交互
	{
		var startTime=document.getElementById("startTime").value; //查询时间
		var endTime = "";
		endTime = document.getElementById("endTime").value;
		var url="getAuditAction!handleAudit";
		xmlHttpRequest = createXmlHttpRequest();
		xmlHttpRequest.onreadystatechange = getAuditCallback;
		xmlHttpRequest.open("POST",url,true);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttpRequest.send("startTime="+startTime+"&endTime="+endTime); 
	}
	
	function getAuditCallback()   //回调函数
	{
		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			var b = xmlHttpRequest.responseText;
			var c = eval("("+b+")");
			var json=eval("("+c+")");
			document.getElementById("ghhsl").innerText = json[0].urnCou;//骨灰盒
			document.getElementById("ghhje").innerText = json[0].urnCos;
			document.getElementById("gdhhlsl").innerText = json[1].iCou;//高档炉
			document.getElementById("gdhhlje").innerText = json[1].iCos;
			document.getElementById("pthhlsl").innerText = json[2].iCou;//普通炉
			document.getElementById("pthhlje").innerText = json[2].iCos;
			document.getElementById("ybhzfsl").innerText = json[3].iCou;//一般美容
			document.getElementById("ybhzfje").innerText = json[3].iCos;
			document.getElementById("pztsl").innerText = json[4].iCou;//普泽厅
			document.getElementById("pztje").innerText = json[4].iCos;
			document.getElementById("fytsl").innerText = json[5].iCou;//福佑厅
			document.getElementById("fytje").innerText = json[5].iCos;
			document.getElementById("xhtsl").innerText = json[6].iCou;//祥和厅
			document.getElementById("xhtje").innerText = json[6].iCos;
			document.getElementById("yttsl").innerText = json[7].iCou;//永泰厅
			document.getElementById("yttje").innerText = json[7].iCos;
			document.getElementById("batsl").innerText = json[8].iCou;//博爱厅
			document.getElementById("batje").innerText = json[8].iCos;
			document.getElementById("rxtsl").innerText = json[9].iCou;//仁孝厅
			document.getElementById("rxtje").innerText = json[9].iCos;
			document.getElementById("cetsl").innerText = json[10].iCou;//慈恩厅
			document.getElementById("cetje").innerText = json[10].iCos;
			document.getElementById("bltsl").innerText = json[11].iCou;//宝莲厅
			document.getElementById("bltje").innerText = json[11].iCos;
			document.getElementById("gbtbzsl").innerText = json[12].Cou;//告别厅布置费
			document.getElementById("gbtbzje").innerText = json[12].Cos;
			document.getElementById("xhbzfsl").innerText = json[13].Cou;//鲜花布置费
			document.getElementById("xhbzfje").innerText = json[13].Cos;
			document.getElementById("zxfsl").innerText = json[14].Cou;//照相费
			document.getElementById("zxfje").innerText = json[14].Cos;
			document.getElementById("sxfsl").innerText = json[15].Cou;//摄像费
			document.getElementById("sxfje").innerText = json[15].Cos;
			document.getElementById("dzfsl").innerText = json[16].Cou;//打字费
			document.getElementById("dzfje").innerText = json[16].Cos;
			document.getElementById("lyfsl").innerText = json[17].Cou;//礼仪费
			document.getElementById("lyfje").innerText = json[17].Cos;
			document.getElementById("syfsl").innerText = json[18].Cou;//司仪费
			document.getElementById("syfje").innerText = json[18].Cos;
			document.getElementById("dbwlsl").innerText = json[19].Cou;//代打挽联
			document.getElementById("dbwlje").innerText = json[19].Cos;
			document.getElementById("dmtzzsl").innerText = json[20].Cou;//多媒体制作
			document.getElementById("dmtzzje").innerText = json[20].Cos;
			document.getElementById("hhxxssl").innerText = json[21].Cou;//豪华休息室
			document.getElementById("hhxxsje").innerText = json[21].Cos;
			document.getElementById("blsl").innerText = json[22].Cou;//包炉费
			document.getElementById("blje").innerText = json[22].Cos;
			document.getElementById("hongbsl").innerText = json[23].Cou;//红布
			document.getElementById("hongbje").innerText = json[23].Cos;
			document.getElementById("huibsl").innerText = json[24].Cou;//灰布
			document.getElementById("huibje").innerText = json[24].Cos;
			document.getElementById("jnbsl").innerText = json[25].Cou;//纪念币
			document.getElementById("jnbje").innerText = json[25].Cos;
			document.getElementById("xhqsl").innerText = json[26].Cou;//小商品
			document.getElementById("xhqje").innerText = json[26].Cos;
			document.getElementById("hlbsl").innerText = json[27].Cou;//护灵宝
			document.getElementById("hlbje").innerText = json[27].Cos;
			document.getElementById("wnqsl").innerText = json[28].Cou;//万年青
			document.getElementById("wnqje").innerText = json[28].Cos;
			document.getElementById("hbstsl").innerText = json[29].Cou;//环保寿毯
			document.getElementById("hbstje").innerText = json[29].Cos;
			document.getElementById("hbstuosl").innerText = json[30].Cou;//环保寿托
			document.getElementById("hbstuoje").innerText = json[30].Cos;
			document.getElementById("wsdsl").innerText = json[31].Cou;//卫生袋
			document.getElementById("wsdje").innerText = json[31].Cos;
			document.getElementById("wsgsl").innerText = json[32].Cou;//卫生棺
			document.getElementById("wsgje").innerText = json[32].Cos;
			document.getElementById("ptsl").innerText = json[33].Cou;//铺毯
			document.getElementById("ptje").innerText = json[33].Cos;
			document.getElementById("pjgysl").innerText = json[34].Cou;//铺金盖银
			document.getElementById("pjgyje").innerText = json[34].Cos;
			document.getElementById("zphssl").innerText = json[35].Cou;//照片黑纱
			document.getElementById("zphsje").innerText = json[35].Cos;
			document.getElementById("spxhsl").innerText = json[36].Cou;//手捧花
			document.getElementById("spxhje").innerText = json[36].Cos;
			document.getElementById("hlsl").innerText = json[37].Cou;//花篮
			document.getElementById("hlje").innerText = json[37].Cos;
			document.getElementById("shouyisl").innerText = json[38].Cou;//寿衣
			document.getElementById("shouyije").innerText = json[38].Cos;
			document.getElementById("huaquansl").innerText = json[39].Cou;//花圈
			document.getElementById("huaquanje").innerText = json[39].Cos;
			document.getElementById("dsxcjssl").innerText = json[40].Cou;//代收现场接尸
			document.getElementById("dsxcjsje").innerText = json[40].Cos;
			document.getElementById("ghjcfsl").innerText = json[41].Cou;//骨灰寄存费
			document.getElementById("ghjcfje").innerText = json[41].Cos;
			document.getElementById("gyfsl").innerText = json[42].Cou;//更衣费
			document.getElementById("gyfje").innerText = json[42].Cos;
			document.getElementById("zrfsl").innerText = json[43].Cou;//整容费
			document.getElementById("zrfje").innerText = json[43].Cos;
			document.getElementById("tszrsl").innerText = json[44].Cou;//特殊整容
			document.getElementById("tszrje").innerText = json[44].Cos;
			document.getElementById("xdfsl").innerText = json[45].Cou;//消毒费
			document.getElementById("xdfje").innerText = json[45].Cos;
			document.getElementById("lwfsl").innerText = json[46].Cou;//劳务费
			document.getElementById("lwfje").innerText = json[46].Cos;
			document.getElementById("rsfsl").innerText = json[47].Cou;//认尸费
			document.getElementById("rsfje").innerText = json[47].Cos;
			document.getElementById("tsfsl").innerText = json[48].Cou;//抬尸费
			document.getElementById("tsfje").innerText = json[48].Cos;
			document.getElementById("stydfsl").innerText = json[49].Cou;//尸体移动费
			document.getElementById("stydfje").innerText = json[49].Cos;
			document.getElementById("ffzlfsl").innerText = json[50].Cou;//防腐整理费
			document.getElementById("ffzlfje").innerText = json[50].Cos;
			document.getElementById("gycdfsl").innerText = json[51].Cou;//更衣场地费
			document.getElementById("gycdfje").innerText = json[51].Cos;
			document.getElementById("ysfwfsl").innerText = json[52].Cou;//验尸服务费
			document.getElementById("ysfwfje").innerText = json[52].Cos;
			document.getElementById("yscdfsl").innerText = json[53].Cou;//验尸场地费
			document.getElementById("yscdfje").innerText = json[53].Cos;
			document.getElementById("ysclfsl").innerText = json[54].Cou;//验尸材料费
			document.getElementById("ysclfje").innerText = json[54].Cos;
			document.getElementById("ydfsl").innerText = json[55].Cou;//预定费
			document.getElementById("ydfje").innerText = json[55].Cos;
			document.getElementById("zhfsl").innerText = json[56].Cou;//装灰费
			document.getElementById("zhfje").innerText = json[56].Cos;
			document.getElementById("dscf1sl").innerText = json[57].carCou;//代收车费1
			document.getElementById("dscf1je").innerText = json[57].carCos;
			document.getElementById("dscf2sl").innerText = json[58].carCou;//代收车费2
			document.getElementById("dscf2je").innerText = json[58].carCos;
			document.getElementById("sgfsl").innerText = json[59].carCou;//送棺费
			document.getElementById("sgfje").innerText = json[59].carCos;
			document.getElementById("sjgczsl").innerText = json[60].coffinCou;//租棺费
			document.getElementById("sjgczje").innerText = json[60].coffinCos;
			document.getElementById("ytlcfsl").innerText = json[61].reeferCou;//遗体冷藏费
			document.getElementById("ytlcfje").innerText = json[61].reeferCos;
			document.getElementById("slszysl").innerText = json[62].villaCou;//守灵室租用费
			document.getElementById("slszyje").innerText = json[62].villaCos;
			document.getElementById("dslcfsl").innerText = json[63].Cou;//代收守灵餐费
			document.getElementById("dslcfje").innerText = json[63].Cos;
			document.getElementById("othersl").innerText = json[64].Cou;//其它
			document.getElementById("otherje").innerText = json[64].Cos;
			document.getElementById("juanhuabuzhisl").innerText = json[65].Cou;//绢花布置
			document.getElementById("juanhuabuzhije").innerText = json[65].Cos;
			var xj1 = json[2].iCos+json[1].iCos+json[4].iCos+json[5].iCos+json[6].iCos+json[7].iCos+json[8].iCos+json[9].iCos+json[10].iCos+json[11].iCos+json[61].reeferCos+json[42].Cos+json[3].iCos+json[41].Cos+json[43].Cos+json[45].Cos+json[47].Cos+json[49].Cos+json[51].Cos+json[54].Cos+json[22].Cos+json[64].Cos;
			var xj2 = json[57].carCos+json[58].carCos+json[40].Cos+json[59].carCos+json[12].Cos+json[13].Cos+json[14].Cos+json[15].Cos+json[16].Cos+json[17].Cos+json[18].Cos+json[19].Cos+json[20].Cos+json[21].Cos+json[44].Cos+json[46].Cos+json[48].Cos+json[50].Cos+json[52].Cos+json[62].villaCos+json[55].Cos+json[56].Cos;
			var xj3 = json[23].Cos+json[24].Cos+json[25].Cos+json[26].Cos+json[27].Cos+json[28].Cos+json[29].Cos+json[30].Cos+json[31].Cos+json[32].Cos+json[38].Cos+json[39].Cos+json[0].urnCos+json[33].Cos+json[34].Cos+json[35].Cos+json[36].Cos+json[37].Cos+json[52].Cos+json[60].coffinCos+json[63].Cos+json[65].Cos;
			document.getElementById("xiaoji1je").innerText = xj1;
			document.getElementById("xiaoji2je").innerText = xj2;
			document.getElementById("xiaoji3je").innerText = xj3
			document.getElementById("hejije").innerText = xj1+xj2+xj3;
			document.getElementById("tabAll").style.display="";
		}
	}
	function AutoExcel(){
  var oXL = new ActiveXObject("Excel.Application"); //创建应该对象
  var oWB = oXL.Workbooks.Add();//新建一个Excel工作簿
  var oSheet = oWB.ActiveSheet;//指定要写入内容的工作表为活动工作表
  var table = document.getElementById("ta");//指定要写入的数据源的id
  var hang = table.rows.length;//取数据源行数
  var lie = table.rows(0).cells.length;//取数据源列数
  // Add table headers going cell by cell.
  for (i=0;i<hang;i++){//在Excel中写行
    for (j=0;j<lie;j++){//在Excel中写列
      //定义格式
      oSheet.Cells(i+1,j+1).NumberFormatLocal = "@";//将单元格的格式定义为文本
      //oSheet.Cells(i+1,j+1).Font.Bold = true;//加粗
      //oSheet.Cells(i+1,j+1).Font.Size = 10;//字体大小
      //oSheet.Cells(i+1,j+1).border = 1;
      oSheet.Cells(i+1,j+1).value = table.rows(i).cells(j).innerText;//向单元格写入值
    }
  }
  oXL.Selection.CurrentRegion.Select;
  oXL.Selection.Interior.Pattern = 0;			//设置底色为空
  oXL.Selection.Borders.LineStyle = 1;		//设置单元格边框为实线
  oXL.Selection.ColumnWidth = 13;				//设置列宽
  oXL.Selection.RowHeight = 16;
  oXL.Visible = true;
  oXL.UserControl = true;
  oXL=null
}
</script> 
</body>
</html>