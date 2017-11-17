<%@ page language="java" import="java.util.*" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'checkin.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
	<object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA"
		width=0 height=0>
		<embed id="LODOP_EM" type="application/x-print-lodop" witdh=0 heigth=0></embed>
	</object>
	<OBJECT id=CVR_IDCard height=0 width=0
		classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
	<div id="TabbedPanels1" class="TabbedPanels">
		<div class="TabbedPanelsContentGroup">
			<div class="TabbedPanelsContent">
				<form name="form3" method="post" id="form3" action="">
					<!--startprint1-->
					<p>
						<label for="deadId">逝者身份证号码：</label> <input type="text"
							name="deadInfo.deadId" id="deadId"
							onchange="return showData(this)"> <input type="button"
							name="ReadCard" id="ReadCard" value="读取身份证号码"
							onclick="return ReadIDCardForService()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
						<label for="deadName">逝者姓名：</label> <input type="text"
							name="deadInfo.deadName" id="deadName">
					</p>
					<p>
						<label for="inTime">遗体进馆时间：</label> <input type="text"
							name="inTime" id="inTime" disabled="true" /> <label
							for="deadNumber">遗体编号：</label> <input type="text"
							name="deadNumber" id="deadNumber" disabled="true" /> <label
							for="deadResidence">户籍所在地：</label> <select
							name="deadInfo.deadResidence" id="deadResidence">
							<option selected>请选择...</option>
							<option>盐城市东台市溱东镇</option>
							<option>盐城市东台市时堰镇</option>
							<option>盐城市东台市五烈镇</option>
							<option>盐城市东台市梁垛镇</option>
							<option>盐城市东台市安丰镇</option>
							<option>盐城市东台市南沈灶镇</option>
							<option>盐城市东台市富安镇</option>
							<option>盐城市东台市唐洋镇</option>
							<option>盐城市东台市新街镇</option>
							<option>盐城市东台市许河镇</option>
							<option>盐城市东台市三仓镇</option>
							<option>盐城市东台市头灶镇</option>
							<option>盐城市东台市弶港镇</option>
							<option>盐城市东台市东台镇</option>
							<option>盐城市东台市开发区</option>
							<option>盐城市大丰区</option>
							<option>泰州市兴化市</option>
						</select>
					</p>
					<p>
						<label for="deadSex">逝者性别：</label> <input name="deadInfo.deadSex"
							id="deadSex" /> <label for="deadAge">逝者年龄：</label> <input
							name="deadInfo.deadAge" id="deadAge" /> <label
							for="relationship">与经办人关系：</label> <select
							name="deadInfo.operatorRelation" id="operatorRelation">
							<option>请选择...</option>
							<option>配偶</option>
							<option>子/婿</option>
							<option>女/媳</option>
							<option>（外）孙子女</option>
							<option>父母/岳父母/公婆</option>
							<option>（外）祖父母</option>
							<option>兄弟姐妹</option>
							<option>其他</option>
						</select>
					</p>
					<hr>
					<p>
						<label for="dealerId">经办人身份证号码：</label> <input type="text"
							name="deadInfo.dealerId" id="dealerId"> <input
							type="button" name="ReadDealerCard" id="ReadDealerCard"
							value="读取身份证号码" onclick="return ReadForm3DealerCard()"
							style="width: 125px; ">&nbsp;&nbsp;&nbsp; <label
							for="dealerName">经办人姓名：</label> <input type="text"
							name="deadInfo.dealerName" id="dealerName">&nbsp;&nbsp;&nbsp;
					</p>

					<p>
						<label for="directorName">承办人姓名：</label> <input type="text"
							name="deadInfo.directorName" id="directorName">&nbsp;&nbsp;&nbsp;
						<label for="dealerAddress">经办人住址：</label> <input type="text"
							name="deadInfo.dealerAddress" id="dealerAddress"
							style="width: 311px; ">&nbsp;&nbsp;&nbsp;
					</p>
					<hr>
					<p>
						<label for="deadTime">死亡时间：</label> <input type="text"
							name="deadInfo.deadTime" id="deadTime"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})">&nbsp;&nbsp;&nbsp;
						<label for="deadKind">死亡类型：</label> <select
							name="deadInfo.deadKind" id="deadKind">
							<option selected>正常</option>
							<option>非正常</option>
						</select>&nbsp;&nbsp;&nbsp; <label for="deadReason">死亡原因：</label> <select
							name="deadInfo.deadReason" id="deadReason"
							onChange="return getPathogenyDetail()">
							<option selected>请选择</option>
							<option>自然死亡</option>
							<option>病故</option>
							<option>事故</option>
							<option>车祸</option>
							<option>他杀</option>
							<option>自杀</option>
							<option>溺水</option>
							<option>中毒</option>
							<option>自然灾害</option>
							<option>其他</option>
						</select> <select name="deadInfo.pathogeny" id="pathogeny"
							style="display:none">
							<option selected>传染病</option>
							<option>肿瘤</option>
							<option>血液病</option>
							<option>呼吸/消化系统疾病</option>
							<option>皮肤病</option>
							<option>神经病</option>
							<option>心脑血管疾病</option>
							<option>骨骼/肌肉系统疾病</option>
							<option>免疫系统疾病</option>
							<option>泌尿生殖系统疾病</option>
							<option>其他</option>
						</select>
					</p>
					<p>
						<label for="deadAddress">逝者家庭地址：</label> <input type="text"
							name="deadInfo.deadAddress" id="deadAddress">&nbsp;&nbsp;&nbsp;
						<label for="area">死者所属区域：</label> <select name="deadInfo.area"
							id="area">
							<option selected>请选择...</option>
							<option>城东新区</option>
							<option>开发区</option>
							<option>五烈镇</option>
							<option>东台镇</option>
							<option>西溪景区</option>
							<option>梁垛镇</option>
							<option>三仓镇</option>
							<option>头灶镇</option>
							<option>富安镇</option>
							<option>安丰镇</option>
							<option>溱东镇</option>
							<option>时堰镇</option>
							<option>许河镇</option>
							<option>唐洋镇</option>
							<option>弶港镇</option>
							<option>大丰市</option>
							<option>兴化市</option>
							<option>其他</option>
						</select>&nbsp;&nbsp; <label for="memberMobile">丧属手机号码：</label> <input
							type="text" name="deadInfo.memberMobile" id="memberMobile">
					</p>
					<p>
						<label for="deadProveUnit">死亡证明出具单位：</label> <input type="text"
							name="deadInfo.deadProveUnit" id="deadProveUnit">&nbsp;&nbsp;&nbsp;
						<label for="deadExtraInfo">备注：</label> <input type="text"
							name="deadInfo.deadExtraInfo" id="deadExtraInfo">&nbsp;&nbsp;&nbsp;
						<label for="ashesDisposition">骨灰去向：</label> <select
							name="deadInfo.ashesDisposition" id="ashesDisposition">
							<option>请选择...</option>
							<option>撒散[撒海、撒江、可降解]</option>
							<option>经营性公墓[骨灰堂]</option>
							<option>公益性公墓[骨灰堂]</option>
							<option>家中存放</option>
							<option>回原籍</option>
							<option>其他</option>
						</select>
					</p>
					<hr>
					<p>
						<label for="invoiceNo"><b>发票号码：</b></label> <input type="text"
							name="invoiceNo" id="invoiceNo" style="width: 133px; ">&nbsp;&nbsp;
						<label for="subsidyNo"><b>惠民补贴凭证号码：</b></label> <input type="text"
							name="subsidyNo" id="subsidyNo" style="width: 133px; ">&nbsp;&nbsp;
						<label for="subsidyMoney"><b>惠民补贴金额：</b></label> <input
							type="text" name="subsidyMoney" id="subsidyMoney"
							style="width: 80px; ">&nbsp;&nbsp; <input type="button"
							value="确认信息" id="decideInvoiceAndSubsidy"
							onclick="return decideInvoice()">
					</p>
					<hr>
					<p>
						<label for="remainsCarryBeCost">代收车费应收费用：</label> <input
							type="input" id="remainsCarryBeCost" value="0"
							style="width: 87px; " readonly />元&nbsp;&nbsp; <label
							for="remainsCarryRealCost">代收车费实收费用：</label> <input type="input"
							id="remainsCarryRealCost" value="0" style="width:87px;"
							onchange="return remainsCarryChangeWholeCost();">元 <input
							type="hidden" id="lastRemainsCarryRealCost" value="0"><br>

						<label for="rentCrystalBeCost">租用冰棺应收费用：</label> <input
							type="input" id="rentCrystalBeCost" value="0"
							style="width: 87px; " readonly />元&nbsp;&nbsp; <label
							for="rentCrystalRealCost">租用冰棺实收费用：</label> <input type="input"
							id="rentCrystalRealCost" value="0" style="width:87px;"
							onchange="return rentCrystalChangeWholeCost();">元 <input
							type="hidden" id="lastRentCrystalRealCost" value="0"><br>

						<label for="rentCrystalCarBeCost">送棺费应收费用：</label> <input
							type="input" id="rentCrystalCarBeCost" value="0"
							style="width: 87px; " readonly />元&nbsp;&nbsp; <label
							for="rentCrystalCarRealCost">送棺费实收费用：</label> <input type="input"
							id="rentCrystalCarRealCost" value="0" style="width:87px;"
							onchange="return rentCrystalCarChangeWholeCost();">元 <input
							type="hidden" id="lastRentCrystalCarRealCost" value="0"><br>

						<label for="watchSpiritVillaBeCost">守灵别墅应收费用：</label> <input
							type="input" id="watchSpiritVillaBeCost" value="0"
							style="width: 87px; " readonly />元&nbsp;&nbsp; <label
							for="watchSpiritVillaRealCost">守灵别墅实收费用：</label> <input
							type="input" id="watchSpiritVillaRealCost" value="0"
							style="width:87px;"
							onchange="return watchSpiritVillaChangeWholeCost();">元 <input
							type="hidden" id="lastWatchSpiritVillaRealCost" value="0"><br>

						<label for="watchSpiritCoffinBeCost">别墅用棺应收费用：</label> <input
							type="input" id="watchSpiritCoffinBeCost" value="0"
							style="width: 87px; " readonly />元&nbsp;&nbsp; <label
							for="watchSpiritCoffinRealCost">别墅用棺实收费用：</label> <input
							type="input" id="watchSpiritCoffinRealCost" value="0"
							style="width:87px;"
							onchange="return watchSpiritCoffinChangeWholeCost();">元 <input
							type="hidden" id="lastWatchSpiritCoffinRealCost" value="0">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="decideMixedCost" value="确认以上服务收费信息"
							style="width: 176px;" onclick="return decideMixedServiceCost();">
					</p>
					<hr>
					<p>
						<input type="checkbox" id="setServiceCheckBox"
							onchange="return chooseSetService()"> <label
							for="setService">套餐类型：</label> <select name="setService"
							id="setService" disabled="true"
							onchange="return showSetServiceDetail()">
							<option>-请选择-</option>
						</select>&nbsp;&nbsp; <input type="hidden" id="setServiceDetailContent"
							style="width: 40%; "> <input type="hidden"
							id="setServiceAllCost" value="0">
					</p>
					<p>
						<input type="checkbox" id="urnChooseBox" checked="checked"
							onchange="return cancelUrnCost()"> <label for="urnChoose">骨灰盒类型：</label>
						<select name="urnChoose" id="urnChoose"
							onchange="return getUrnBeCost()">
							<option>-请选择-</option>
						</select>&nbsp;&nbsp; <label for="urnBeCost">应收费用：</label> <input
							type="text" name="urnBeCost" id="urnBeCost" value="0"
							style="width: 99px;" readonly />元&nbsp;&nbsp; <label
							for="urnRealCost">备注：</label> <input type="text"
							name="urnRealCost" id="urnRealCost" value="0"
							style="width: 95px; " onchange="return changeUrnRealCost()">元
						<input name="preUrnBeCost" id="preUrnBeCost" type="hidden"
							value="0" /> <input name="preUrnRealCost" id="preUrnRealCost"
							type="hidden" value="0" />
					</p>
					<p>
						<input type="checkbox" id="makeBeautyBox" checked="checked"
							onchange="return cancelBeautyCost()"> <label
							for="makeBeautyGrade">整容类型：</label> <select
							name="makeBeautyGrade" id="makeBeautyGrade"
							onChange="return getMakeBeautyBeCost()">
							<option>-请选择-</option>
							<option>整容</option>
						</select>&nbsp;&nbsp; <label for="makeBeautyBeCost">应收费用：</label> <input
							type="text" name="makeBeautyBeCost" id="makeBeautyBeCost"
							value="0" style="width: 92px;" readonly />元&nbsp;&nbsp; <label
							for="makeBeautyRealCost">备注：</label> <input type="text"
							name="makeBeautyRealCost" id="makeBeautyRealCost" value="0"
							onchange="return changeBeautyRealCost()" style="width: 86px; ">元
						<input name="preMakeBeautyBeCost" id="preMakeBeautyBeCost"
							type="hidden" value="0" /> <input name="preMakeBeautyRealCost"
							id="preMakeBeautyRealCost" type="hidden" value="0" />
					</p>
					<p>
						<input type="checkbox" id="leaveRoomBox" checked="checked"
							onchange="return cancelLeaveRoomCost()"> <label
							for="leaveRoomGrade">告别厅类型：</label> <select name="leaveRoomGrade"
							id="leaveRoomGrade" onChange="return getLeaveRoomBeCost()">
							<option>-请选择-</option>
						</select>&nbsp;&nbsp; <label for="leaveRoomBeCost">应收费用：</label> <input
							type="text" name="leaveRoomBeCost" id="leaveRoomBeCost" value="0"
							style="width: 92px;" readonly />元&nbsp;&nbsp; <label
							for="leaveRoomRealCost">备注：</label> <input type="text"
							name="leaveRoomRealCost" id="leaveRoomRealCost" value="0"
							onchange="return changeLeaveRoomRealCost()" style="width: 86px; ">元
						<input name="preLeaveRoomBeCost" id="preLeaveRoomBeCost"
							type="hidden" value="0" /> <input name="preLeaveRoomRealCost"
							id="preLeaveRoomRealCost" type="hidden" value="0" /> <input
							name="preSetFarewellBeCost" id="preSetFarewellBeCost"
							type="hidden" value="0" />
					</p>
					<p>
						<input type="checkbox" id="cremationBox" checked="checked"
							onchange="return cancelCremationCost()"> <label
							for="cremationGrade">火化炉类型：</label> <select name="cremationGrade"
							id="cremationGrade" onChange="return getCremationBeCost()">
							<option>-请选择-</option>
							<option>普通炉</option>
							<option>高档炉</option>
							<option>VIP区</option>
						</select>&nbsp;&nbsp; <label for="cremationBeCost">应收费用：</label> <input
							type="text" name="cremationBeCost" id="cremationBeCost" value="0"
							style="width: 92px;" readonly />元&nbsp;&nbsp; <label
							for="cremationRealCost">备注：</label> <input type="text"
							name="cremationRealCost" id="cremationRealCost" value="0"
							onchange="return changeCremationRealCost()" style="width: 86px; ">元
						<input name="preCremationBeCost" id="preCremationBeCost"
							type="hidden" value="0" /> <input name="preCremationRealCost"
							id="preCremationRealCost" type="hidden" value="0" /> <input
							name="preSetFurnaceBeCost" id="preSetFurnaceBeCost" type="hidden"
							value="0" />
					</p>
					<p>
					<div id="funeralGoodsPart">
						<table border="1" width="90%" id="allFuneralGoods">
							<tr>
								<td align="center" colspan="11"><label for="funeralGoods"><b>丧葬物品及其他小商品选择</b></label>
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
					</div>
					</p>
					<hr>
					<p>
						<label for="allBeCost"><b>火化服务费用应收：</b></label> <input type="text"
							name="allBeCost" id="allBeCost" value="0" style="width: 100px;"
							readonly />元&nbsp;&nbsp;&nbsp;&nbsp; <input type="hidden"
							id="allBeCostHidden" value="0"> <label for="allRealCost"><b>火化服务费用备注：</b></label>
						<input type="text" name="allRealCost" id="allRealCost" value="0"
							style="width: 100px;" readonly />元
					</p>
					<p>
						<label for="theWholeCost"><b>总费用：</b></label> <input type="text"
							name="theWholeCost" id="theWholeCost" value="0"
							style="width: 100px;" readonly />元 <input type="hidden"
							id="lastWholeCost" value="0">
					</p>

					<hr>
					<!--endprint1-->
					<p>
						<input type="button" name="printServiceList" id="printServiceList"
							value="生成服务详单" onclick="return outPutServiceList()"
							style="width: 125px; ">&nbsp;&nbsp;&nbsp; <input
							type="button" name="printServiceList" id="printServiceList"
							value="打印服务详单" onclick="return outPrintServiceList()"
							style="width: 125px; ">&nbsp;&nbsp;&nbsp; <input
							type="button" name="createSetOrder" id="createSetOrder"
							value="生成套餐单据" disabled="true"
							onclick="return outPutSetServiceOrder()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
						<input type="button" name="printSetServiceList"
							id="printSetServiceList" value="打印套餐单据" disabled="true"
							onclick="return outPrintSetServiceList()" style="width: 125px; ">&nbsp;&nbsp;&nbsp;
						<input type="button" name="ensureChoose" id="ensureChoose"
							value="确认服务" onclick="return decideYourChoose()"
							style="width: 125px; ">&nbsp;&nbsp;&nbsp; <br>
					</p>
					<hr>
					&nbsp;<a href="javascript:;"
						style="text-decoration:none;color:black;"
						onclick="javascript:CreatePrintPage();LODOP.PREVIEW();">税票打印</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" style="text-decoration:none;color:black;"
						onclick="javascript:CreateCremationPrint();LODOP.PREVIEW();">火化证打印</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" style="text-decoration:none;color:black;"
						onclick="javascript:CreateSubsidyPrintPage();LODOP.PREVIEW();">惠民补助打印</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" style="text-decoration:none;color:black;"
						onclick="javascript:setDetailPrint();LODOP.PREVIEW();">套餐详情打印</a>
					<hr>
					<!--- style="display:none"----------------------------------------------------------------------------- 打印单据 ---------------------------------------------------------------------------------------------->
					<div id="goodsPartList">
						<table border="0" width="95%" id="basicInfo"
							style="font-size:12px;border-collapse:collapse;border:0px solid #000;margin-left:30;">

							<tr>
								<td align="center" colspan="12"><label for="funeralGoods"><b><font
											style="font-size:22px">东台市殡仪馆服务收费单</font></b></label>(东台分馆)</td>
							</tr>
							<tr>
								<td><b>当日序号：</b></td>
								<td id="currentDateTh2"></td>
								<td colspan="1"><b>No:</b></td>
								<td id="no" colspan="2"></td>
								<td colspan="1"><b>日期:</b></td>
								<td id="taxDate" colspan="6"></td>
							</tr>
							<tr>
								<td colspan="1"><b>姓名:</b></td>
								<td colspan="1" id="name"></td>
								<td colspan="1"><b>性别:</b></td>
								<td colspan="1" id="sex"></td>
								<td colspan="1"><b>年龄:</b></td>
								<td colspan="1" id="age"></td>
								<td colspan="1"><b>住址:</b></td>
								<td colspan="5" id="address"></td>
							</tr>

						</table>
						<table border="2" width="95%" id="allGoods"
							style="font-size:12px;border-collapse:collapse;border:1px solid #000;margin-left:30;">
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
								<td><b>火化炉</b></td>
								<td id="cremation"></td>
								<td id="cremation1"></td>
								<td id="cremation2"></td>

								<td><b>告别厅</b></td>
								<td id="farewell"></td>
								<td id="farewell1"></td>
								<td id="farewell2"></td>

								<td><b>骨灰盒</b></td>
								<td id="urn"></td>
								<td id="urn1"></td>
								<td id="urn2"></td>
							</tr>
							<tr>
								<td><b>整容</b></td>
								<td id="face"></td>
								<td id="face1"></td>
								<td id="face2"></td>

								<td><b>租用冰棺</b></td>
								<td id="crystal"></td>
								<td id="crystal1"></td>
								<td id="crystal2"></td>

								<td><b>守灵</b></td>
								<td id="watch"></td>
								<td id="watch1"></td>
								<td id="watch2"></td>
							</tr>
							<tr>
								<td><b>遗体接运</td>
								<td id="car"></td>
								<td id="car1"></td>
								<td id="car2"></td>
								<td><b>用棺接运</td>
								<td id="car3"></td>
								<td id="car4"></td>
								<td id="car5"></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>

							<tbody id="all"></tbody>

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
								<td colspan="4"><b>(明白消费 确认签字) 客户主办人签字</b></td>
								<td colspan="8"></td>
							</tr>
						</table>
					</div>
					<!------------------------------------------------------------ 打印单据 ------------------------------------------------------------------------->
					<div id="setGoodsPartList">
						<table border="0" width="95%" id="setBasicInfo"
							style="font-size:12px;border-collapse:collapse;border:0px solid #000;margin-left:30;">
							<tr>
								<td align="center" colspan="12"><label><b><font
											style="font-size:22px">东台市殡仪馆服务收费单</font></b></label>(东台分馆)</td>
							</tr>
							<tr>
								<td><b>当日序号：</b></td>
								<td id="currentDateTh"></td>
								<td colspan="1"><b>No:</b></td>
								<td id="setNo" colspan="2"></td>

								<td colspan="1"><b>日期:</b></td>
								<td id="setTaxDate" colspan="6"></td>
							</tr>
							<tr>
								<td colspan="1"><b>姓名:</b></td>
								<td colspan="1" id="setName"></td>
								<td colspan="1"><b>性别:</b></td>
								<td colspan="1" id="setSex"></td>
								<td colspan="1"><b>年龄:</b></td>
								<td colspan="1" id="setAge"></td>
								<td colspan="1"><b>住址:</b></td>
								<td colspan="5" id="setAddress"></td>
							</tr>
						</table>
						<table border="2" width="95%" height="22%" id="setAllGoods"
							style="font-size:12px;border-collapse:collapse;border:1px solid #000;margin-left:30;">
							<tr>
								<td colspan="1" width="15%"><b>项目</b></td>
								<td colspan="1"><b>金额</b></td>
								<td colspan="8" width="80%"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem1"></td>
								<td colspan="1" id="itemPrice1"></td>
								<td colspan="8" id="setServiceDetail"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem2"></td>
								<td colspan="1" id="itemPrice2"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem3"></td>
								<td colspan="1" id="itemPrice3"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem4"></td>
								<td colspan="1" id="itemPrice4"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem5"></td>
								<td colspan="1" id="itemPrice5"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem6"></td>
								<td colspan="1" id="itemPrice6"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem7"></td>
								<td colspan="1" id="itemPrice7"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem8"></td>
								<td colspan="1" id="itemPrice8"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="15%" id="setItem9"></td>
								<td colspan="1" id="itemPrice9"></td>
								<td colspan="8"></td>
							</tr>
							<tr>
								<td colspan="1" height="10%"><b>合计</b></td>
								<td colspan="1" id="setAllCost"></td>
								<td colspan="1"><b>大写</b></td>
								<td colspan="7" id="setAllCostUp" width="70%"></td>
							</tr>
							<tr>
								<td colspan="1" height="10%"><b>开票员</b></td>
								<td colspan="1"></td>
								<td colspan="1"><b>管理员</b></td>
								<td colspan="1" width="10%"></td>
								<td colspan="2"><b>(明白消费 确认签字)</b></td>
								<td colspan="1"><b>客户签字</b></td>
								<td colspan="3" width="30%"></td>
							</tr>
						</table>
					</div>
				</form>
				<p>&nbsp;</p>
			</div>
		</div>
	</div>
	<script>
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	</script>
</body>
<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
</script>

<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
<script src="js/jquery-1.7.1.min.js"></script>
<script src="js/webVideoCtrl.js"></script>
</html>
