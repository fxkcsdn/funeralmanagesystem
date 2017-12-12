<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="js/json2.js"></script>
<script src="js/AjaxJson.js" type="text/javascript" charset="utf-8"></script>
<script src="js/Setting.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-1.8.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
</script>
<script type="text/javascript" src="js/watchSpirit/watchBill.js"></script>
<style type="text/css">
body, td, th {
	font-size: 20px;
}
</style>
</head>

<body>
	<form id="form1" name="form1" onsubmit="return submitForm();">
		<table style="cellspacing:20px;">
			<tr>
				正在使用的别墅号：
				<td id="villa" colspan="4"></td>
			</tr>
			<tr>
				<td>别墅号：</td>
				<td><input type="text" id="villaName" name="villaName"
					readonly="readonly" disabled="true" style="width:80%;" /></td>
				<td>开始时间：</td>
				<td><input type="text" id="startTime" name="startTime"
					required="required" readonly="readonly" disabled="true" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime" name="watchSpirit.endTime"
					required="required" onchange="calculate();"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" />
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>逝者身份证号（必填）：</td>
				<td><input type="text" id="deadID" name="watchSpirit.deadID"
					required="required" /></td>
				<td><input type="button" id="readDeadIDBtn" value="读取身份证号码" /></td>
			</tr>
			<tr>
				<td>守灵接运应收：</td>
				<td><input type="text" id="carryBeCost" name="carryBeCost"
					readonly="readonly"
					style="margin-right:50px;background-color:#DDD;" /></td>
				<td>守灵接运实收：</td>
				<td><input type="text" id="carryRealCost" onchange="reCalculate();"
					name="watchSpirit.carryRealCost" style="margin-right:50px;" /></td>
			</tr>
			<tr>
				<td>守灵别墅应收(1500/天)：</td>
				<td><input type="text" id="villaBeCost"
					name="watchSpirit.villaBeCost" readonly="readonly"
					style="margin-right:50px;background-color:#DDD;"
					required="required" /></td>
				<td>守灵别墅实收：</td>
				<td><input type="text" id="villaRealCost" onchange="reCalculate();"
					name="watchSpirit.villaRealCost" style="margin-right:50px;"
					required="required" /></td>
			</tr>
			<tr>
				<td>守灵水晶棺应收(1500/天)：</td>
				<td><input type="text" id="coffinBeCost" 
					name="watchSpirit.coffinBeCost" readonly="readonly"
					style="margin-right:50px;background-color:#DDD;"
					required="required" /></td>
				<td>守灵水晶棺实收：</td>
				<td><input type="text" id="coffinRealCost" onchange="reCalculate();"
					name="watchSpirit.coffinRealCost" style="margin-right:50px;"
					required="required" /></td>
			</tr>
			<tr>
				<td>守灵服务应收：</td>
				<td><input type="text" id="serviceBeCost" name="serviceBeCost"
					readonly="readonly"
					style="margin-right:50px;background-color:#DDD;" /></td>
				<td>守灵服务实收：</td>
				<td><input type="text" id="serviceRealCost" onchange="reCalculate();"
					name="watchSpirit.serviceRealCost" style="margin-right:50px;" /></td>
			</tr>

			<tr>
				<td>总应收：</td>
				<td><input type="text" id="allBeCost"
					name="watchSpirit.allBeCost" readonly="readonly"
					style="margin-right:50px;background-color:#DDD;"
					required="required" /></td>
				<td>总实收：</td>
				<td><input type="text" id="allRealCost"
					name="watchSpirit.allRealCost" style="margin-right:50px;"
					required="required" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" id="save"
					value="保存"> <input type="button" id="btnPrint"
					disabled="disabled" value="打印"
					onclick=""></td>
			</tr>

		</table>
	</form>
</body>
</html>
