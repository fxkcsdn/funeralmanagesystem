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
<script src="js/json2.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
<style type="text/css">
body {
	font-size: 24px;
	text-align: center;
}
td,th{
	font-size: 16px;
	white-space: nowrap;
	text-align: center;
}
label{
	font-color:blue;
}
</style>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" >省厅数据上报</li>
    <li class="TabbedPanelsTab" >查询未上传数据</li>
    <li class="TabbedPanelsTab" >查询已上传数据</li>
  </ul>
  <div class="TabbedPanelsContentGroup">  
    <div class="TabbedPanelsContent" style="text-align:center">
    	<table>
    		<tr>
    			<td style="font-size:24px;">申报日期：</td>
    			<td><input type="text" id="exchangeTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" style="width:150px;height:35px;font-size:24px;" /></td>
    			<td><input type="button" id="submitDate" value="上传当天数据" onclick="dataExchange()"/></td>
    		</tr>
    	</table>
    	<hr/>
    	<div align="center" id="resultDiv" style="display:none">
    	<table border=1 id="succTab" style="width:600px;">
    		<tr>
    			<th>逝者身份证</th>
    			<th>状态</th>
    		</tr>
    	</table>
    	<hr/>  	
    	<table border=1 id="failTab" style="width:600px;">
    		<tr>
    			<th>逝者身份证</th>
    			<th>状态</th>
    			<th>操作</th>
    		</tr>
    	</table>
    	</div>
    </div> 
  	<div class="TabbedPanelsContent" style="text-align:center">
  		<input type="button" value="查询未上传数据" onclick="queryDate()"/>
  	<div id="resultDateDiv" style="width:100%;display:none;">
  		<table id="resultDateTab" border=1 style="margin:auto;width:70%;">
  		<tr>
  			<th>日期</th>
  			<th>姓名</th>
  			<th>身份证号</th>
  			<th>状态</th>
  		</tr>
  		</table>
  	</div>
  	</div>
  	<div class="TabbedPanelsContent" style="text-align:center">
  		<table>
    		<tr>
    			<td style="font-size:24px;">查询日期：</td>
    			<td><input type="text" id="queryDataTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" style="width:150px;height:35px;font-size:24px;" /></td>
    			<td><input type="button" value="查询" style="height:35px;width:50px;" onclick="querySuccessData()"/></td>
    			<td>当天入馆总人数：</td>
    			<td><input type="text" id="shouldBe" style="border:0;height:40px;width:40px;font-size:30;color:blue;font-weight:bold;text-align:center;" readonly="readonly"/></td>
    			<td>已成功上传：</td>
    			<td><input type="text" id="hasBeen" style="border:0;height:40px;width:40px;font-size:30;color:blue;font-weight:bold;text-align:center;" readonly="readonly"/></td>
    		</tr>
    	</table>
    	<hr/>
    	<div id="successDateDiv" style="width:100%;display:none;">
  		<table id="successDateTab" border=1 style="margin:auto;width:70%;">
  		<tr>
  			<th>编号</th>
  			<th>日期</th>
  			<th>姓名</th>
  			<th>身份证号</th>
  			<th>状态</th>
  		</tr>
  		</table>
  	</div>
  	</div>
   </div>
   </div>
	<script >
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1"); 
	var xmlhttp;
	var jsonarray;
	onload=initialization;
	function initialization()
    {           
       getDate();
    }
    function getDate()
    {
    	var myDate = new Date();
    	var showYear = myDate.getFullYear();
    	var showMonthTmp = myDate.getMonth()+1;
    	
    	if(showMonthTmp<10)
    	{
    		showMonth = 0+""+showMonthTmp;
    	}
    	else
    	{
    		showMonth = showMonthTmp+"";
    	}
    	var showDateTmp = myDate.getDate();
    	if(showDateTmp<10)
    	{
    		showDate = 0+""+showDateTmp;
    	}
    	else
    		{
    			showDate = showDateTmp+"";
    		}
    	document.getElementById("exchangeTime").value = showYear+"-"+showMonth+"-"+showDate;
    }
    function dataExchange()
    {
    	var queryDate = document.getElementById("exchangeTime").value;
    	xmlhttp = null;
    	var url = "ExchangeDataAction!exchangeData";
    	 if (window.XMLHttpRequest)
         {// code for all new browsers
           xmlhttp = new XMLHttpRequest();
         }
         else if (window.ActiveXObject)
         {// code for IE5 and IE6
           xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
         }

         if (xmlhttp != null) {
           xmlhttp.onreadystatechange = queryBusinessBack;
           xmlhttp.open("POST", url, false);
           xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
           xmlhttp.send("queryDate="+queryDate);
           }
    }
    function queryBusinessBack()
    {
    	 if (xmlhttp.readyState == 4)
         {
           if (xmlhttp.status == 200)
           {
           		document.getElementById("resultDiv").style.display="";            
        	   	var b = xmlhttp.responseText;
        	   	b = eval("("+b+")");
        	   	b = eval("("+b+")");
        	   	jsonarray = b;
        	   	fillResultTab(jsonarray);                       //生成结果表	
           }
           else
           {
             alert(xmlhttp.status);
             alert("Problem retrieving XML data");
           }
         }
   	}
   	function fillResultTab(jsonarray){                 
   			var sTab = document.getElementById("succTab");
        	var fTab = document.getElementById("failTab");
        	var len = jsonarray.length;
        	var i = 0;
       		while(sTab.rows.length>1){                       //清空成功列表
       			for(var rows=sTab.rows.length-1;rows>0;rows--)
   	   			sTab.deleteRow(rows);
   	   		}
   	   		while(fTab.rows.length>1){						//清空失败列表
       			for(var rows=fTab.rows.length-1;rows>0;rows--)
   	   			fTab.deleteRow(rows);
   	   		}
   	   		for(i=0;i<len;i++){
   	   			var flag = jsonarray[i].status;
   	   			if(flag=="success"){
   	   			var row = sTab.insertRow(sTab.rows.length);
   	   			var cell = row.insertCell(-1);
   	   			cell.innerText = jsonarray[i].deadID;
   	   			var cell = row.insertCell(-1);
   	   			cell.innerText = "成功";
   	   		}
   	   		else{
   	   			var row = fTab.insertRow(fTab.rows.length);
   	   			var cell = row.insertCell(-1);
   	   			cell.innerText = jsonarray[i].deadID;
   	   			var cell = row.insertCell(-1);
   	   			cell.innerText = "失败";
   	   			var cell = row.insertCell(-1);
   	   			cell.innerHTML = "<input type='button' id='aaa' value='上传' onclick='uploadSingleRecord(this)'/>";
   	   		}
   	   	}
   	}
   	function uploadSingleRecord(obj)
   	{
   		var rowObj = obj.parentNode.parentNode;
   		var deadid = rowObj.childNodes[0].innerText;
   		xmlhttp = null;
   		var url = "ExchangeSingleDataAction!exchangeSingleData";
   		if (window.XMLHttpRequest)
         {// code for all new browsers
           xmlhttp = new XMLHttpRequest();
         }
         else if (window.ActiveXObject)
         {// code for IE5 and IE6
           xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
         }

         if (xmlhttp != null) {
           xmlhttp.onreadystatechange = uploadSingleRecordBack;
           xmlhttp.open("POST", url, false);
           xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
           xmlhttp.send("deadid="+deadid);
           }
   	}
   	function uploadSingleRecordBack()
   	{
   		if (xmlhttp.readyState == 4)
         {
           if (xmlhttp.status == 200)
           {
           		document.getElementById("resultDiv").style.display="";            
        	   	var b = xmlhttp.responseText;
        	   	b = eval("("+b+")");
        	   	b = eval("("+b+")");
        	   	var len = jsonarray.length;
        	   	for(var i=0;i<len;i++){
        	   		if(jsonarray[i].deadID==b[0].deadID)
        	   		{
        	   			jsonarray[i].status=b[0].status;
        	   		}
        	   	}
        	   	fillResultTab(jsonarray);
           }
           else
           {
             alert(xmlhttp.status);
             alert("Problem retrieving XML data");
           }
         }
   	}
   	function queryDate(){                                        //查询未上传的日期
   		xmlhttp = null;
   		var url = "QueryDateAction!queryDate";
   		if (window.XMLHttpRequest)
         {// code for all new browsers
           xmlhttp = new XMLHttpRequest();
         }
         else if (window.ActiveXObject)
         {// code for IE5 and IE6
           xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
         }
         if (xmlhttp != null) {
           xmlhttp.onreadystatechange = queryDateBack;
           xmlhttp.open("POST", url, false);
           xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
           xmlhttp.send("status=0&date=0");
           }
   	}
   	function queryDateBack(){
   		 if (xmlhttp.readyState == 4)
         {
           if (xmlhttp.status == 200)
           {
           		document.getElementById("resultDateDiv").style.display="";            
        	   	var b = xmlhttp.responseText;
        	   	b = eval("("+b+")");
        	   	b = eval("("+b+")");
        	   	showresult(b);
           }
           else
           {
             alert(xmlhttp.status);
             alert("Problem retrieving XML data");
           }
         }
   	}
   	function showresult(json)
   	{
   		var tab = document.getElementById("resultDateTab");
   		var len = json.length;
   		var i=0;
   		while(tab.rows.length>1){                       //清空结果列表
       		for(var rows=tab.rows.length-1;rows>0;rows--)
   	   		tab.deleteRow(rows);
   	   	}
   	   	for(i=0;i<len;i++){
   	   		var row = tab.insertRow(tab.rows.length);
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].time.substring(0,10);
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].name;
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].id;
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = "未上传";
   	   	}
   	}
   	function querySuccessData()                      //查询成功上传的数据
   	{
   		var date = document.getElementById("queryDataTime").value;
   		if(date.length<1)
   		{
   			alert("请选择查询日期！");
   			document.getElementById("queryDataTime").focus();
   			return false;
   		}
   		xmlhttp = null;
   		var url = "QuerySuccessAction!queryDate";
   		if (window.XMLHttpRequest)
         {// code for all new browsers
           xmlhttp = new XMLHttpRequest();
         }
         else if (window.ActiveXObject)
         {// code for IE5 and IE6
           xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
         }
         if (xmlhttp != null) {
           xmlhttp.onreadystatechange = querySuccessDataBack;
           xmlhttp.open("POST", url, false);
           xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
           xmlhttp.send("status=1&"+"date="+date);
           }
   	}
   	function querySuccessDataBack()
   	{
   		 if (xmlhttp.readyState == 4)
         {
           if (xmlhttp.status == 200)
           {
           		document.getElementById("successDateDiv").style.display="";            
        	   	var b = xmlhttp.responseText;
        	   	b = eval("("+b+")");
        	   	b = eval("("+b+")");
        	   	showSuccessResult(b);
           }
           else
           {
             alert(xmlhttp.status);
             alert("Problem retrieving XML data");
           }
         }
   	}
   	function showSuccessResult(json)
   	{
   		var all = json.length;
   		document.getElementById("shouldBe").value = all;
   		var tab = document.getElementById("successDateTab");
   		var len = json.length;
   		var i = 0;
   		var j = 0;
   		while(tab.rows.length>1){                       //清空结果列表
       		for(var rows=tab.rows.length-1;rows>0;rows--)
   	   		tab.deleteRow(rows);
   	   	}
   	   	for(i=0;i<len;i++){
   	   		var row = tab.insertRow(tab.rows.length);
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = i+1;
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].time.substring(0,10);
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].name;
   	   		var cell = row.insertCell(-1);
   	   		cell.innerText = json[i].id;
   	   		if(json[i].status==1)
   	   		{
   	   			j++;
   	   			var cell = row.insertCell(-1);
   	   			cell.innerHTML = "<label style='color:blue'>成功</lebal>";
   	   		}
   	   		else{
   	   			var cell = row.insertCell(-1);
   	   			cell.innerHTML = "<label style='color:red'>失败</lebal>";
   	   		}
   	   	}
   	   	document.getElementById("hasBeen").value = j;
   	}
</script> 
</body>
</html>