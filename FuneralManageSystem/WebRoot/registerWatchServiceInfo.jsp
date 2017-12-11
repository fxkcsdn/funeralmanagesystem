<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'registerWatchServiceInfo.jsp' starting page</title>

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
	<form id="form1" onsubmit="return submitForm();">
		<table style="cellspacing:20px;">
			<tr>
				<td id="villa" colspan="4"></td>
			</tr>
			<tr>
				<td>别墅号：</td>
				<td><input type="text" id="villaName" name="villaName"
					readonly="readonly" disabled="true" style="width:80%;" /></td>
			</tr>
		</table>

		<table style="cellspacing:20px;">
			<tr>
				<td>物品名：</td>
				<td><input type="text" name="watchSpiritGood.name"
					style="width:80%;" /></td>
				<td>数量：</td>
				<td><input type="text" name="watchSpiritGood.number"
					style="width:80%;" /></td>
				<td>费用：</td>
				<td><input type="text" name="watchSpiritGood.beCost"
					style="width:80%;" /></td>
				<td>时间：</td>
				<td><input type="text" id="arrivalTime"
					name="watchSpiritGood.consumeTime" required="required"
					style="width:80%;"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
			</tr>
		</table>
		<table style="cellspacing:20px;">
			<tr>
				<td>服务名：</td>
				<td><input type="text" name="watchSpiritService.name"
					style="width:80%;" /></td>
				<td>次数：</td>
				<td><input type="text" name="watchSpiritService.number"
					style="width:80%;" /></td>
				<td>费用：</td>
				<td><input type="text" name="watchSpiritService.beCost"
					style="width:80%;" /></td>
				<td>时间：</td>
				<td><input type="text" id="arrivalTime2"
					name="watchSpiritService.consumeTime" required="required"
					style="width:80%;"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>用餐费用：</td>
				<td><input type="text" name="watchSpiritMeal.beCost"
					style="width:80%;" /></td>
				<td>时间：</td>
				<td><input type="text" id="arrivalTime3"
					name="watchSpiritMeal.consumeTime" required="required"
					style="width:80%;"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" id="save" value="保存"
					style="width:60px;" /></td>
			</tr>
		</table>
	</form>
</body>
<script src="js/watchSpirit/registerWatchServiceInfo.js" charset="utf-8"></script>
</html>
