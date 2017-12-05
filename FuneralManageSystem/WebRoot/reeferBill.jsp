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

<title>My JSP 'reeferBill.jsp' starting page</title>

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
<script type="text/javascript" src="js/deadReefer/reeferBill.js"></script>
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
				正在使用的冰柜号：
				<td id="reefer" colspan="4"></td>
			</tr>
			<tr>
				<td>冰柜号：</td>
				<td><input type="text" id="reeferNo" name="reeferNo"
					readonly="readonly" disabled="true" style="width:80%;"  /></td>
				<td>到馆时间：</td>
				<td><input type="text" id="arriveTime" name="arriveTime"
					required="required" readonly="readonly" disabled="true" /></td>
				<td>结束时间：</td>
				<td><input type="text" id="endTime"
					name="remainsReefer.endTime" required="required"
					onchange="calculate();"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" />
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td>逝者身份证号（必填）：</td>
				<td><input type="text" id="deadID" name="remainsReefer.deadID"
					required="required" /></td>
				<td><input type="button" id="readDeadIDBtn" value="读取身份证号码" /></td>
			</tr>
			<tr>
				<td>冷藏遗体接运应收：</td>
				<td><input type="text" id="carryBeCost" name="carryBeCost"
					readonly="readonly"  style="margin-right:50px;background-color:#DDD;" /></td>
				<td>冷藏遗体接运实收：</td>
				<td><input type="text" id="carryRealCost"
					name="remainsReefer.carryRealCost" style="margin-right:50px;" /></td>
			</tr>
			<tr>
				<td>冷藏冰柜应收(120/天)：</td>
				<td><input type="text" id="reeferBeCost"  name="remainsReefer.reeferBeCost" 
					readonly="readonly" style="margin-right:50px;background-color:#DDD;" required="required" /></td>
				<td>冷藏冰柜实收：</td>
				<td><input type="text" id="reeferRealCost"
					name="remainsReefer.reeferRealCost" style="margin-right:50px;"
					required="required" /></td>
			</tr>
			<tr>
				<td>冷藏服务应收：</td>
				<td><input type="text" id="serviceBeCost" name="serviceBeCost"
					readonly="readonly"  style="margin-right:50px;background-color:#DDD;" />
				</td>
				<td>冷藏服务实收：</td>
				<td><input type="text" id="serviceRealCost"
					name="remainsReefer.serviceRealCost" style="margin-right:50px;" />
				</td>
			</tr>

			<tr>
				<td>总应收：</td>
				<td><input type="text" id="allBeCost" name="remainsReefer.allBeCost" 
			     readonly="readonly" style="margin-right:50px;background-color:#DDD;" required="required" /></td>
				<td>总实收：</td>
				<td><input type="text" id="allRealCost"
					name="remainsReefer.allRealCost" style="margin-right:50px;"
					required="required" /></td>
			</tr>
			<tr>
				<td>冷藏遗体送运应收：</td>
				<td><input type="text" id="sendBeCost" name="sendBeCost"
					readonly="readonly"  style="margin-right:50px;background-color:#DDD;" />
				</td>
				<td>冷藏遗体送运实收：</td>
				<td><input type="text" id="sendRealCost"
					name="remainsReefer.sendRealCost" style="margin-right:50px;" /></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" id="save"
					value="保存"> <input type="button" id="btnPrint"
					disabled="disabled" value="打印" onclick="printReeferCarryBill();">
				</td>
			</tr>

		</table>
	</form>
</body>
</html>
