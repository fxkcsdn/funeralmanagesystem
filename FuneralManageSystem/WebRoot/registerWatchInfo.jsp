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
<script src="js/json2.js">
</script>
<script src="js/AjaxJson.js" type="text/javascript" charset="utf-8"></script>
<script src="js/Setting.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery-1.8.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
</script>
<script type="text/javascript" src="js/watchSpirit/registerWatchInfo.js"></script>
<style type="text/css">
    body,td,th {
    	font-size: 20px;
    }
</style>
  </head>

<body>
	<div id="wakeInfo">
		<table>
			
			<tr>
				<td style="text-align:right">别墅号：</td>
				<td><input type="text" id="villaNumber" name="villaNumber"
					readonly="readonly" style="margin-right:50px;background-color:#DDD;" /></td>
				<td style="text-align:right">水晶棺号：</td>
				<td><input type="text" id="coffinNumber" name="coffinNumber"
					readonly="readonly"  style="margin-right:50px;background-color:#DDD;"/></td>
			</tr>
			<tr>
				<td></td>
				<td id="villa" colspan="2"></td>
				<td id="coffin" colspan="2"></td>
			</tr>
			<tr>
				<td style="text-align:right">接运编号(本馆接运需填写)：</td>
				<td><input type="text" id="carryNumber" name="carryNumber" /></td>
			</tr>
			<tr>
				<td style="text-align:right">逝者身份证号：</td>
				<td><input type="text" id="deadId" name="deadId" /></td>
				<td><input type="button" id="readDeadIDBtn" value="读取身份证号码" /></td>
			</tr>
			<tr>
				<td style="text-align:right">逝者姓名：</td>
				<td><input type="text" id="deadName" name="deadName" /></td>
				<td style="text-align:right">逝者性别：</td>
				<td><input type="text" id="deadSex" name="deadSex" /></td>
			</tr>
			<tr>
				<td style="text-align:right">家庭地址：</td>
				<td colspan="4"><input type="text" id="deadAddress" name="deadAddress" style="width:515px;" /></td>
			</tr>
			<tr>
				<td style="text-align:right">家属姓名：</td>
				<td><input type="text" id="memberName" name="memberName" /></td>
				<td style="text-align:right">家属手机：</td>
				<td><input type="text" id="memberMobile" name="memberMobile" /></td>
			</tr>
			<tr>
				<td style="text-align:right">开始时间：</td>
				<td><input type="text" id="startTime" name="startTime"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" /></td>
			</tr>
		</table>
		<input type="submit" id="save" value="保存"
			onclick="saveInformationOfWakeInfo()">
	</div>
</body>
</html>
