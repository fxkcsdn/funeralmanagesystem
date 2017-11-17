<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/json2.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
body,td,th {
	font-size: 20px;
}
</style>
</head>
<body>
	<div align="center">
		<table>
    		<tr>
    			<td>查询日期：</td>
    			<td><input id="queryTime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
       			<td><input type="button" value="查询" onclick="searchBill()" style="width: 60px;height:25px;"/></td>
    		</tr>
    	</table>
    	</div>
    	<hr>
    	<div id="billDiv" align="center" style="display:none;">
    	<table border=1 id="allBills">
    		<tr>
    			<th>接运单号</th>
    			<th>逝者身份证号</th>
    			<th>接运地址</th>
    			<th>接运车牌号</th>
    			<th>司机姓名</th>
    			<th>接运时间</th>
    			<th>结束时间</th>
    		</tr>
    	</table>
    	<hr>
    	<h5>填入待修改信息</h5>
    		
    		接运单号1：<input type="text" id="jydh1"/><br>
    		身份证号1：<input type="text" id="szsfz1"/><br>
    		<br>
    		接运单号2：<input type="text" id="jydh2"/><br>
    		身份证号2：<input type="text" id="szsfz2"/><br>
    		<br>
    		<input type="button" value="提交修改" onclick="doTheChange()"/>
	</div>
	<script>
	window.onload = initialization;
	function initialization()
	{
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
		document.getElementById("queryTime").value=myYear+"-"+myMonth+"-"+myDay;
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
	function searchBill()
	{
		var time = document.getElementById("queryTime").value;
		var url="editbingding!getBill";
		xmlHttpRequest = createXmlHttpRequest();
		xmlHttpRequest.onreadystatechange = searchBillback;
		xmlHttpRequest.open("POST",url,true);
		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlHttpRequest.send("time="+time); 
	}
	function searchBillback()
	{
		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
			{
				var b = xmlHttpRequest.responseText;
				b=eval("("+b+")");
				b=eval("("+b+")");   //json数组
				showBill(b);
			}
	}
	function showBill(jsonArray)
	{
		document.getElementById("billDiv").style.display="";//显示表头
		var table = document.getElementById("allBills");
		var length = jsonArray.length;
		var i = 0;
		while(table.rows.length>1)
				{
					for(var rows=table.rows.length-1;rows>0;rows--)
					table.deleteRow(rows);
				}
		for(i=0;i<length;i++)
				{
					var row = table.insertRow(table.rows.length);
					var cell = row.insertCell(-1);
					cell.innerText=jsonArray[i].carryNumber;
					var cell = row.insertCell(-1);
					cell.innerText=jsonArray[i].deadID;
					var cell = row.insertCell(-1);
					cell.innerText=jsonArray[i].address;
					var cell = row.insertCell(-1);
					cell.innerText=jsonArray[i].carNumber;
					var cell = row.insertCell(-1);
					cell.innerText=jsonArray[i].contactName;
					var cell = row.insertCell(-1);
					cell.innerText= jsonArray[i].startTime;
					var cell = row.insertCell(-1);
					cell.innerText= jsonArray[i].returnTime;
				}  
	}
	function doTheChange()
	{
		var cn1=document.getElementById("jydh1").value;
		var id1=document.getElementById("szsfz1").value;
		var cn2=document.getElementById("jydh2").value;
		var id2=document.getElementById("szsfz2").value;
		if(cn1==""&&cn2==""&&id1==""&&id2=="")
			{
				alert("请填写待修改信息！");
				document.getElementById("jydh1").focus();
				return false;
			}
		if(cn1!=""&&id1=="")
			{
				alert("请填写逝者身份证1！");
				document.getElementById("szsfz1").focus();
				return false;
			}
		if(cn2!=""&&id2=="")
		{
			alert("请填写逝者身份2！");
			document.getElementById("szsfz2").focus();
			return false;
		}
		if(cn2==""&&id2!="")
		{
			alert("请填写接运单号2！");
			document.getElementById("jydh2").focus();
			return false;
		}
		if(cn1==""&&id1!="")
		{
			alert("请填写接运单号1！");
			document.getElementById("jydh1").focus();
			return false;
		}
		if(cn2!="")   //说明需要同时修改两条记录
		{
			xmlHttpRequest = createXmlHttpRequest();
			var url = "handleChange!handleData";
			xmlHttpRequest.open("POST",url,true);
			
   	   		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = doTheChangeback;
			xmlHttpRequest.send("cn1="+cn1+"&id1="+id1+"&cn2="+cn2+"&id2="+id2);
		}
		else          //修改一条记录
		{
			xmlHttpRequest = createXmlHttpRequest();
			var url = "handleChange!handleData";
			xmlHttpRequest.open("POST",url,true);
   	   		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttpRequest.onreadystatechange = doTheChangeback;
			xmlHttpRequest.send("cn1="+cn1+"&id1="+id1+"&cn2="+cn2+"&id2="+id2);
		}
	}
	function doTheChangeback()
	{
		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			var b = xmlHttpRequest.responseText;
			var c = b;
			alert("成功修改了："+c+"条数据！");
			clearInputArea();
			searchBill();              //刷新接运订单列表
		}
	}
	function clearInputArea()
	{
		document.getElementById("jydh1").value="";
		document.getElementById("szsfz1").value="";
		document.getElementById("jydh2").value="";
		document.getElementById("szsfz2").value="";
	}
	</script>
</body>
</html>