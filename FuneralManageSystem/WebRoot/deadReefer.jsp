<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<meta charset="utf-8">
<title>My JSP 'deadRefrigeration.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/json2.js"></script>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<script src="js/AjaxJson.js" type="text/javascript" charset="utf-8"></script>
<script src="js/Setting.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-1.8.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
</script>
<style type="text/css">
body, td, th {
	font-size: 24px;
}
</style>
</head>

<body>
	<OBJECT id=CVR_IDCard height=0 width=0
		classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>


	<div id="TabbedPanels1" class="TabbedPanels">
		<ul class="TabbedPanelsTabGroup">
			<li class="TabbedPanelsTab" tabindex="0"
				onclick="window.frames.registerReeferRemainsCarry.location.reload(); ">登记冷藏遗体接运信息</li>
			<li class="TabbedPanelsTab" tabindex="1">冷藏接运信息查询</li>
			<li class="TabbedPanelsTab" tabindex="2"
				onclick="window.frames.registerReeferInfo.location.reload(); ">登记冷藏信息</li>
			<li class="TabbedPanelsTab" tabindex="3">冷藏信息查询</li>
			<li class="TabbedPanelsTab" tabindex="3">登记冷藏服务信息</li>
			<li class="TabbedPanelsTab" tabindex="0"
				onclick="window.frames.registerReeferRemainsSend.location.reload(); ">登记冷藏遗体送运信息</li>
			<li class="TabbedPanelsTab" tabindex="4"
				onclick="window.frames.reeferBill.location.reload(); ">冷藏结算</li>
		</ul>

		<div class="TabbedPanelsContentGroup">

			<div class="TabbedPanelsContent">
				<iframe name="registerReeferRemainsCarry"
					src="registerReeferRemainsCarry.jsp" width="100%" height="90%"></iframe>
			</div>
			<div class="TabbedPanelsContent">
				<table style="cellspacing:20px;">
					<tr>
						<td>查询时间：</td>
						<td><input type="text" id="queryDate" name="carryOrderDate"
							style="font-size:20px;width:150px" onchange="queryBills()"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" /></td>
					</tr>
				</table>
				<hr>
				<div id="backDetailDiv" align="center">
					<table id="detailTab" border=1
						style="display:none;text-align:center">
						<tr>
							<th>接运单号</th>
							<th>车牌号</th>
							<th>联系人</th>
							<th>派车地点</th>
							<th>预计时间</th>
							<th>应收费</th>
							<th>本馆车</th>

						</tr>
					</table>
				</div>
			</div>
			<div class="TabbedPanelsContent">
				<iframe name="registerReeferInfo" src="registerReeferInfo.jsp"
					width="100%" height="90%"></iframe>
			</div>
			<div class="TabbedPanelsContent">
				<table style="cellspacing:20px;">
					<tr>
						<td>查询时间：</td>
						<td><input type="text" id="queryDate2" name="carryOrderDate2"
							style="font-size:20px;width:150px" onchange="queryReeferRemain()"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" /></td>
					</tr>
				</table>
				<hr>
				<div id="backDetailDiv2" align="center">
					<table id="detailTab2" border=1
						style="display:none;text-align:center">
						<tr>
							<th>冷藏单号</th>
							<th>接运编号</th>
							<th>冰柜号</th>
							<th>逝者身份证号</th>
							<th>到馆时间</th>
							<th>经办人姓名</th>
							<th>经办人手机号</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="TabbedPanelsContent">
				<form id="form1" onsubmit="return submitForm();">
					<table style="cellspacing:20px;">
						<tr>
							<td id="reefer" colspan="4"></td>
						</tr>
						<tr>
							<td>冰柜号：</td>
							<td><input type="text" id="reeferNo"  name="reeferNo"
								readonly="readonly" disabled="true" style="width:80%;" /></td>
						</tr>
					</table>

					<table style="cellspacing:20px;">
						<tr>
							<td>冷藏物品名：</td>
							<td><input type="text"  name="reeferGood.name"
								style="width:80%;" /></td>
							<td>数量：</td>
							<td><input type="text" 
								name="reeferGood.number" style="width:80%;" /></td>
							<td>物品费用：</td>
							<td><input type="text" 
								name="reeferGood.beCost" style="width:80%;" /></td>
							<td>时间：</td>
							<td><input type="text" id="arrivalTime"
								name="reeferGood.consumeTime" required="required"
								style="width:80%;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
						</tr>
					</table>
					<table style="cellspacing:20px;">
						<tr>
							<td>冷藏服务名：</td>
							<td><input type="text"  name="reeferService.name"
								style="width:80%;" /></td>
							<td>次数：</td>
							<td><input type="text"  name="reeferService.number"
								style="width:80%;" /></td>
							<td>服务费用：</td>
							<td><input type="text"  name="reeferService.beCost"
								style="width:80%;" /></td>
							<td>时间：</td>
							<td><input type="text" id="arrivalTime2" name="reeferService.consumeTime"
								required="required" style="width:80%;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
						</tr>
					</table>
					<table>
						<tr>
							<td>用餐费用：</td>
							<td><input type="text"  name="reeferMeal.beCost"
								style="width:80%;" /></td>
							<td>时间：</td>
							<td><input type="text" id="arrivalTime3" name="reeferMeal.consumeTime"
								required="required" style="width:80%;"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
						</tr>
						<tr>
							<td colspan="4"><input type="submit" id="save" value="保存"
								style="width:60px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="TabbedPanelsContent">
				<iframe name="registerReeferRemainsSend"
					src="registerReeferRemainsSend.jsp" width="100%" height="90%"></iframe>
			</div>
			<div class="TabbedPanelsContent">
				<iframe name="reeferBill" src="reeferBill.jsp" width="100%"
					height="90%"></iframe>
			</div>

		</div>
	</div>
</body>
<script src="js/deadReefer/deadReefer.js" charset="utf-8"></script>
</html>
