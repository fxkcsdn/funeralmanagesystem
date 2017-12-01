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
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
	<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
	<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
  </head>
  
  <body>
   <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
    
       
   <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.registerReeferRemainsCarry.location.reload(); ">登记冷藏遗体接运信息</li>
     <li class="TabbedPanelsTab" tabindex="0" >冷藏接运信息查询</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.registerReeferInfo.location.reload(); ">登记冷藏信息</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.reeferBill.location.reload(); ">冷藏结算</li>
</ul>

  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent"> 
      <iframe name="registerReeferRemainsCarry" src="registerReeferRemainsCarry.jsp" width="100%" height="90%"></iframe>    
    </div>
    <div class="TabbedPanelsContent">
	  	<table style="cellspacing:20px;">
	  		<tr>
	  			<td>查询时间：</td>
	  			<td><input type="text" id="queryDate" name="carryOrderDate" style="font-size:20px;width:150px" onchange="queryBills()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
	  		</tr>
	  	</table>
	  	<hr>
	  	<div id="backDetailDiv" align="center">
	  		<table id="detailTab" border=1 style="display:none;text-align:center">
	  			<tr>
	  				<th>接运单号</th><th>车牌号</th><th>联系人</th><th>派车地点</th><th>预计时间</th><th>应收费</th><th>本馆车</th>
	  			</tr>
	  		</table>
	  	</div>
	  </div>
    <div class="TabbedPanelsContent">            
      <iframe name="registerReeferInfo" src="registerReeferInfo.jsp" width="100%" height="90%"></iframe>    
    </div>
    
    <div class="TabbedPanelsContent"> 
      <iframe name="reeferBill" src="reeferBill.jsp" width="100%" height="90%"></iframe>  
    </div>
    
  </div>
</div>  
 
  </body>
 <script>
	
		var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
		function queryBills() {
			var queryDate = document.getElementById("queryDate").value;
				if (queryDate == "" || queryDate == null) {
					alert("请选择查询时间！");
					document.getElementById("queryDate").focus();
					return false;
			}
			var url = "RemainsReeferAction!queryRemainsCarry";
			var data="queryDate="+queryDate;
			alert(data);
			sendRequest("post", url, data, queryBillsback);
		}

		function queryBillsback() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("detailTab").style.display = "";
				var b = xmlHttpRequest.responseText;
				b = eval("(" + b + ")");
				var c = eval("(" + b + ")");
				var tab = document.getElementById("detailTab");
				var len = c.length;
				var i = 0;
				while (tab.rows.length > 1) {
					for (var rows = tab.rows.length - 1; rows > 0; rows--)
						tab.deleteRow(rows);
				}
				for (i = 0; i < len; i++) {
					var row = tab.insertRow(tab.rows.length);
					var cell = row.insertCell(-1);
					cell.innerText = c[i].carryNumber;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].carNumber;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].contactName;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].address;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].startTime;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].carBeCost;
					var cell = row.insertCell(-1);
					cell.innerText = c[i].bInternalCar;
				}
			} else {
				alert(xmlhttp.status);
				alert("Problem retrieving XML data");
			}
		}
	</script>
</html>
