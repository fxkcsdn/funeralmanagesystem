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
<script src="js/importToExcel.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">新增接运任务</li>
    <li class="TabbedPanelsTab" tabindex="0">查询接运任务</li>
    <li class="TabbedPanelsTab" tabindex="0">修改接运任务</li>
</ul>
  <div class="TabbedPanelsContentGroup"> 
	  <div class="TabbedPanelsContent">
	    <table id="tab1" width=98% border="0">
	<tr>
		<td style="text-align:left">车牌号：</td>
		<td><select id="carNumber" name="carNumber" onchange="driverDetailInfo()"></select></td>
		<td style="text-align:left">司机姓名：</td>
	    <td><input type="text" id="driverName" name="driverName" readonly="readonly" disabled="true"/></td>
	    <td style="text-align:left">司机手机：</td>
	    <td><input type="text" id="driverPhone" name="driverPhone" readonly="readonly" disabled="true"/></td>
	</tr>
	<tr>
		<td style="text-align:left">联系人：</td>
	    <td><select id="contactName" name="contactName">
	    </select></td>
	    <td style="text-align:left">派车地点：</td>
	    <td><input type="text" id="address" name="address"/></td>
	    <td style="text-align:left">预计时间：</td>
	    <td><input type="text" name="estimatedTime" id="estimatedTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})"/></td>
	</tr>
	<tr>
		<td style="text-align:left" >应收费：</td>
		<td><input type="text" id="beCost" name="beCost" readonly="readonly" disabled="true" style="width: 40px;height:25px;font-weight:bold;"/>元</td>
		<td style="text-align:left" >本馆车辆：</td>
		<td><select id="bInternal">
			<option selected="selected">请选择</option>
			<option>1</option>
			<option>2</option>
		</select></td>
	</tr>
	</table>
	<div align="center">
	<input type="button" id="addToAll" value="新增" style="width: 60px;height:25px;" onclick="updateAll()"/>
	<span style="width:100px;"></span>
	<input type="button" id ="startPrint" value="打印" style="width: 60px;height:25px;" onclick="printTable()" disabled="true"/>
	<span style="width:100px;"></span>
	<input type="button" value="重置" style="width: 60px;height:25px;" onclick="reloadTable()"/>
	</div>
	<hr>
	<div  id="inAll" style="display:none;">
	<label>已添加的任务：</label>
	<input type="button" value="全部提交" onclick="tableToJson()" style="width: 80px;height:25px;"/>
	<form>
	<table id="tab2" width=98% border="1" style="text-align:center">
	<th>车牌号</th><th>司机姓名</th><th>司机手机</th><th>联系人</th><th>派车地点</th><th>预计时间</th><th>应收费</th><th>本馆车</th><th>操作</th>
	</table>
	</form>
	</div>   
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
		  	<table style="cellspacing:20px;">
		    	<tr>
		    		<td>接运时间：</td>
		    		<td><input type="text" name="carryOrderDate" id="carryOrderDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" onchange="searchCarryOrder()"/></td>
		    	</tr>
		    </table>
		    <div id="carryDiv" style="display:none">
		    	<table id="carryTab" width=98% border="1" style="text-align:center;">
		    		<tr>
		    			<th>接运单号</th><th>车牌号</th><th>接运时间</th><th>接运地点</th><th>联系人</th><th>操作</th>
		    		</tr>
		    	</table>
		    </div>
		    <hr>
		    <div id="editChange" style="display:none">
		    	<table border="1" style="text-align:center;">
		    		<tr>
		    			<th>接运单号</th><th>车牌号</th><th>接运时间</th><th>接运地点</th><th>联系人</th><th>确认修改</th>
		    		</tr>
		    		<tr>
		    			<td><input type="text" id="editCarryNumber" style="text-align:center;" readonly="readonly" disabled="disabled"/></td>
		    			<td><select id="editCarNum" onchange="releaseButton()"></select></td>
		    			<td><input type="text" id="editCarryTime" style="text-align:center;" readonly="readonly" disabled="disabled"/></td>
		    			<td><input type="text" id="editCarryAddress" style="text-align:center;" readonly="readonly" disabled="disabled"/></td>
		    			<td><input type="text" id="editContactName" style="text-align:center;" readonly="readonly" disabled="disabled"></td>
		    			<td><input type="button" id="confirm" value="确认修改" onclick="linkedit()" disabled="disabled"/></td>
		    		</tr>
		    	</table>
		    </div>
 		 </div>
  </div>
</div>
<script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
	var xmlhttp;
	var agentInfo;
	var dataOfCarNumber;
	window.onload = initialization;
	 function initialization()
	       {           
	           getCarNumber();   //页面加载时获取车牌号生成下拉框
	           getAgent();       //页面加载时获取一条龙信息生成下拉框
	           getCarBeCost();	//页面加载时自动填充接运应收费用
	           getCurrentTime();   //获取当前日期，填入“接运时间” 
	       }
	// function getCurrentTime()
	//	   {
		   		//var myDate = new Date();
		   		//var b = myDate.toLocaleDateString();     //获取当前日期
		   		//var c = b.replace("/","-");
		   		//var d = c.replace("/","-");
		   		//document.getElementById("carryOrderDate").value = d;
		  // }
	function getCarNumber() {
	        xmlhttp = null;         
	        var url="/FuneralManageSystem/getCarNumber.action";
	        if (window.XMLHttpRequest)
	        {// code for all new browsers
	          xmlhttp = new XMLHttpRequest();
	        }
	        else if (window.ActiveXObject)
	        {// code for IE5 and IE6
	          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	        }

	        if (xmlhttp != null) {
	          xmlhttp.onreadystatechange = getCarNumberBack;
	          xmlhttp.open("POST", url, false);
	          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	          xmlhttp.send(null);
	        }
	      }

	      //回调函数
	      function getCarNumberBack() {
	        if (xmlhttp.readyState == 4)
	        {
	          if (xmlhttp.status == 200)
	          {            
	            dataOfCarNumber = eval("("+ xmlhttp.responseText +")");
	            dataOfCarNumber= eval("("+ dataOfCarNumber +")");      
	            showCarNumber(dataOfCarNumber);
	          }
	          else
	          {
	            alert(xmlhttp.status);
	            alert("Problem retrieving XML data");
	          }
	        }
	      }
	      function showCarNumber(dataOfCarNumber) //生成车牌号下拉框
	      {
	          var dataLength = dataOfCarNumber.length;
	          var sel = document.getElementById("carNumber");
	          var js;
	          //sel.options.add(new Option("",""));
	          for (var i = 0; i < dataLength; ++i)
	          {
	              js = "";
	              js = "{carNumber:\""+ dataOfCarNumber[i].carNumber + "\",driverName:\"" + dataOfCarNumber[i].driverName +"\",driverMoblie:\"" + dataOfCarNumber[i].driverMobile + "\"}";             
	              //sel.options.add(new Option(dataOfCarNumber[i].carNumber,dataOfCarNumber[i].carNumber));
	              sel.options.add(new Option(dataOfCarNumber[i].carNumber,js));
	          }
	          var sel2 = document.getElementById("editCarNum");
	          var js2;
	          //sel.options.add(new Option("",""));
	          for (var i = 0; i < dataLength; ++i)
	          {
	              js2 = "";
	              js2 = "{carNumber:\""+ dataOfCarNumber[i].carNumber + "\",driverName:\"" + dataOfCarNumber[i].driverName +"\",driverMoblie:\"" + dataOfCarNumber[i].driverMobile + "\"}";             
	              //sel.options.add(new Option(dataOfCarNumber[i].carNumber,dataOfCarNumber[i].carNumber));
	              sel2.options.add(new Option(dataOfCarNumber[i].carNumber,js2));
	          }
	      }
	      function driverDetailInfo() //选择车牌号，关联司机姓名、司机手机号
	      {
	         var info = document.getElementById("carNumber").value;           
	         if (info != "")
	         {
	            info = JSON.stringify(info);     
	            info = eval("("+ eval("("+ info +")") +")");           
	            document.getElementById("driverName").value = (info.driverName == "null")?"":info.driverName;
	            document.getElementById("driverPhone").value = (info.driverMoblie == "null")?"":info.driverMoblie;
	          }
	         else
	         {
	            document.getElementById("driverName").value = "";
	            document.getElementById("driverPhone").value = "";           
	         }
	      } 
	      	function getAgent()        //获取一条龙信息
	      {
	      	 	xmlhttp = null;         
	        	var url="/FuneralManageSystem/getAgentAction!searchAgent";
	        	if (window.XMLHttpRequest)
	        	{// code for all new browsers
	          		xmlhttp = new XMLHttpRequest();
	        	}
	       		else if (window.ActiveXObject)
	        	{// code for IE5 and IE6
	          		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	        	}

	        	if (xmlhttp != null) {
	        	//http_request=createHttpRequest();
	          	xmlhttp.onreadystatechange = getAgentBack;
	          	xmlhttp.open("POST", url, false);
	          	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	          	xmlhttp.send(null);
	        	}
	      }
	      	function getAgentBack()    //回调函数
	      {
	      	//alert(http_request.readyState);
	        if (xmlhttp.readyState == 4)
	          {
	          	if (xmlhttp.status == 200)
	          	{
	          		var b = eval("("+xmlhttp.responseText+")");
	          		var c = eval("("+b+")");
	          		agentInfo = c;
	          		//alert(agentInfo);
	          		var selectNode=document.getElementById("contactName");
	          		selectNode.options.length=0;
	          		var length=c.length;
	          		//alert(length);
	          		for(var i=0;i<length;i++)
	          		{
	          			var optNode=document.createElement("option");
	          			optNode.innerText=c[i].agentName;
	          			selectNode.appendChild(optNode);         		
	          		}
	          	}
	          	else
	          	{
	            alert(xmlhttp.status);
	            alert("Problem retrieving XML data");
	          	}
	        }
	      }
	      function updateAll()				//点击“新增”按钮，把信息显示到下面的form中，并用reloadTable()函数
	      {
	      	if(document.getElementById("inAll").style.display=="none")
	      	{
	      		document.getElementById("inAll").style.display="";
	      	}
	      	var g = document.getElementById("carNumber").value;
	      	if(g!="")
	      	{
	      		g = JSON.stringify(g);
	      	g = eval("("+ eval("("+ g +")") +")");
	      	a = g.carNumber;
	      	var b = document.getElementById("driverName").value;
	      	var c = document.getElementById("driverPhone").value;
	      	var d = document.getElementById("contactName").options[document.getElementById("contactName").selectedIndex].text;  //IE下取下拉框当前选项
	      	var e = document.getElementById("address").value;
	      	var f = document.getElementById("estimatedTime").value;
	      	var j = document.getElementById("bInternal").options[document.getElementById("bInternal").selectedIndex].text; 	
	      	
	      	if((d!="")&&(e!="")&&(f!=""))
	      	{
	      		if(j=="请选择")
	      		{
	      		alert("请选择是否为本馆车辆！");
	      		document.getElementById("bInternal").focus();
	      		return false;
	      		}
	      		var i = document.getElementById("beCost").value;
	      		var table=document.getElementById("tab2");
	      		var row = table.insertRow(table.rows.length);
	      		var cell = row.insertCell(-1);
	      		cell.innerText=a;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=b;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=c;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=d;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=e;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=f;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=i;
	      		var cell = row.insertCell(-1);
	      		cell.innerText=j;
	      		var cell = row.insertCell(-1); 
				cell.innerHTML="<input type='button' value='删除' onclick='deleteRow(this)'>";
				document.getElementById("startPrint").disabled=false;
				document.getElementById("addToAll").disabled=true;
	      	} 
	      	else
	      	{
	      		alert("请完善任务信息！");
	      	}
	      	}
	      	else
	      	{
	      		alert("车牌信息不存在！");
	      	}	
	      }
	      
	      function reloadTable()   //添加到汇总表格后，新增接运任务表内容清空
	      {
	      	document.getElementById("address").value="";
	      	document.getElementById("estimatedTime").value=""; 
	      	document.getElementById("bInternal").selectedIndex=0;
	      	getCarBeCost(); 
	      	document.getElementById("addToAll").disabled=false;
	      	document.getElementById("startPrint").disabled=true;
	      }
	      function deleteRow(obj)    //点击删除按钮，删除某行记录
	      {
	      	
				obj.parentNode.parentNode.removeNode(true);
			
	      }
	      function getCarBeCost()  //获取车辆应收费用
	      {
	        http_request=createHttpRequest();
			http_request.onreadystatechange=getCarBeCostBack;
			http_request.open('POST',"GetCarBeCostAction!findCarBeCost",false);
			http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			http_request.send(null);
	      }
	      function getCarBeCostBack()
	      {
	         if (http_request.readyState == 4)
	        {
	          if (http_request.status == 200)
	          {
	          	
	          	var b = eval("("+http_request.responseText+")");
	          	b=eval("("+b+")");
	          	
	          	var c = b[0].beCost;
	          	
	          	document.getElementById("beCost").value=c;
	          }
	          else
	          {
	            alert(http_request.status);
	            alert("Problem retrieving XML data");
	          }
	        }
	      }  
	      
	     function tableToJson() {
	     var table = document.getElementById("tab2");
		 var data = [];
		// first row needs to be headers
		 headers =["车牌号","司机姓名","司机手机","联系人","派车地点","预计时间","应收费","本馆车"];
			
		// go through cells
		for (var i=1; i<table.rows.length; i++) {
			var tableRow = table.rows[i];
			var rowData = {};
			//8
			for (var j=0; j<8; j++) {
				rowData[ headers[j] ] = tableRow.cells[j].innerHTML;
			}		
			data.push(rowData);
		}		
		var data1=JSON.stringify(data);
		//alert(data1);
		uploadTab(data1);	
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
		function uploadTab(data1) 
		{
			var url="uploadTableAction!uploadData";
			xmlHttpRequest = createXmlHttpRequest(); 
			xmlHttpRequest.onreadystatechange = uploadTabCallback; 
			xmlHttpRequest.open("POST",url,true);
			xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			/* alert(data1); */
			xmlHttpRequest.send("json="+data1); 
		}
		function uploadTabCallback()
		{
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
			{
				var b = xmlHttpRequest.responseText;
				var c = b;
				alert("成功添加了："+c+"条数据！");
				location.reload()
			}
		}
		function printTable() //打印功能
		{
		    var g = document.getElementById("carNumber").value;
			g = JSON.stringify(g);
	      	g = eval("("+ eval("("+ g +")") +")");
			var carNumber = g.carNumber;
			var driverName = document.getElementById("driverName").value;
			var estimatedTime = document.getElementById("estimatedTime").value;
			var address = document.getElementById("address").value;
			var d = document.getElementById("contactName").options[document.getElementById("contactName").selectedIndex].text;
			//var contactName = document.getElementById("contactName").value;
			var contactNum;
			for(var i=0;i<agentInfo.length;i++)
			{
				if(agentInfo[i].agentName==d)
				contactNum = agentInfo[i].agentMobile;
			}
			var url = 'printCarryBill.html?carNumber='+carNumber+'&driverName='+driverName+'&estimatedTime='+estimatedTime+'&address='+address+'&contactName='+d+'&contactNum='+contactNum;
			var URL = encodeURI(url);
			//updateAll();
			reloadTable();   //reload表格，同时还原按钮初始状态
			window.open(URL);
		}
		/*下面是查询遗体接运单的脚本*/
		 function searchCarryOrder()      //查询预计时间为当前日期且逝者身份证为空的接运订单
	   	   {
	   	   		var time = document.getElementById("carryOrderDate").value;
	   	   		xmlhttp = null;
	   	   		var url="getCarryOrderAction!findCarryOrder";
	   	   		if (window.XMLHttpRequest)
	        	{// code for all new browsers
	          		xmlhttp = new XMLHttpRequest();
	        	}
	       		else if (window.ActiveXObject)
	        	{// code for IE5 and IE6
	          		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	        	}
	        	if (xmlhttp != null) {
	        	//http_request=createHttpRequest();
	          	xmlhttp.onreadystatechange = searchCarryOrderBack;
	          	xmlhttp.open("POST", url, false);
	          	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	          	xmlhttp.send("time="+time);
	        	}
	   	   }
	   	   function searchCarryOrderBack() //查询接运信息回调函数
	   	   {
	   	   		if (xmlhttp.readyState == 4)
	            {
	          		if (xmlhttp.status == 200)
	          		{
	          			var b = eval("("+xmlhttp.responseText+")");
	          		    var c = eval("("+b+")");
	          			//alert(c);
	          			document.getElementById("carryDiv").style.display="";//显示表头
	          			var tab = document.getElementById("carryTab");  //动态生成表
	   	   				var len = c.length;
	   	   				var i = 0;
	   	   				while(tab.rows.length>1)
	   	   				{
	   	   					for(var rows=tab.rows.length-1;rows>0;rows--)
	   	   					tab.deleteRow(rows);
	   	   				}
	   	   				for(i=0;i<len;i++)
	   	   				{
	   	   					var row = tab.insertRow(tab.rows.length);
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerText=c[i].carryNumber;
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerText=c[i].carNumber;
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerText=c[i].carryTime;
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerText=c[i].carryAddress;
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerText=c[i].contactName;
	   	   					var cell = row.insertCell(-1);
	   	   					cell.innerHTML="<input type='button' value='修改' onclick='proptInfo(this,\""+c[i].carryNumber+"\")'>"+
	   	   					"<input type='button' value='删除' onclick='deleteCarryOrder(\""+c[i].carryNumber+"\")'>";
	   	   				}           
	          		}
	          		else
	          		{
	            		alert(xmlhttp.status);
	            		alert("Problem retrieving XML data");
	          		}
	        	}
	   	   }
	   	   function proptInfo(ele,a)
	   	   {
	   		document.getElementById("editChange").style.display="";//显示表头
	   		    var target= ele.parentNode.parentNode;
	   			var valuea=target.childNodes[0].innerText;
	   			var valueb=target.childNodes[1].innerText;
	   			var valuec=target.childNodes[2].innerText;
	   			var valued=target.childNodes[3].innerText;
	   			var valuee=target.childNodes[4].innerText;
	   			document.getElementById("editCarryNumber").value =valuea;
	   			var carNumSelect = document.getElementById("editCarNum");
	   			var carNum=valueb;
	   			for(var k=0;k<carNumSelect.options.length;k++){
	   				var jsonString = carNumSelect.options[k].value; 
	   				jsonString = eval("("+jsonString+")");
	   				if(jsonString.carNumber==carNum){
	   					carNumSelect.options[k].selected = true;
	   				}
	   			}
	   			document.getElementById("editContactName").value =valuee;
	   			document.getElementById("editCarryTime").value =valuec;
	   			document.getElementById("editCarryAddress").value =valued;
	   	   }
	   	   function releaseButton()
	   	   {
	   		document.getElementById("confirm").disabled=false;
	   	   }
	   	   
	   	   function linkedit()
	   	   {
	   		xmlhttp = null;         
	        var url="changeCarryOrder!execute";
	        if (window.XMLHttpRequest)
	        {// code for all new browsers
	          xmlhttp = new XMLHttpRequest();
	        }
	        else if (window.ActiveXObject)
	        {// code for IE5 and IE6
	          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	        }
	        var carryNum = document.getElementById("editCarryNumber").value;
	        var carNum = document.getElementById("editCarNum").options[document.getElementById("editCarNum").selectedIndex].text;
	        if (xmlhttp != null) {
	          xmlhttp.onreadystatechange = linkeditBack;
	          xmlhttp.open("POST", url, false);
	          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	          xmlhttp.send("carNum="+carNum+"&carryNum="+carryNum);
	        }
	      }
	   	   function linkeditBack()
	   	   {
	   		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
	   		{
	   			var b = xmlhttp.responseText;
				var c = b;
				alert(c);
				searchCarryOrder();
	   		}
	   	   }
	   	   /*删除未绑定身份证的接运单*/
	   	   function deleteCarryOrder(carryNumber){
		   	   if(carryNumber==null||carryNumber.toString().length<=0){
		   	   	alert("接运单号不能为空！");
		   	   	return false;
		   	   }
		   		xmlhttp = null;         
		        var url="changeCarryOrder!deleteCarryOrder";
		        if (window.XMLHttpRequest)
		        {// code for all new browsers
		          xmlhttp = new XMLHttpRequest();
		        }
		        else if (window.ActiveXObject)
		        {// code for IE5 and IE6
		          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		        }
		        var carryNum = carryNumber;
		        var ifDelete=confirm
		        if (xmlhttp != null) {
		         if(confirm("确定要删除该接运单吗？")){
				 	xmlhttp.onreadystatechange = deleteCarryOrderBack;
		          	xmlhttp.open("POST", url, false);
		          	xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		          	xmlhttp.send("carryNum="+carryNum);
				 }
		        }
	        
	      }
	      
	      /*删除接运单返回函数*/
	      function deleteCarryOrderBack(){
	   		if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
	   			var b = xmlhttp.responseText;
				if(b=="\"deleteSuccess\""){
					alert("刪除成功！");
				}else if(b=="\"deleteFailure\""){
					alert("刪除失敗！");
				}else{
					alert("没有匹配！");
				}
				searchCarryOrder();
	   		  }
	   	   }
	   	    
	   	 function queryBills()
	   	   {
	   		  var queryDate = document.getElementById("queryDate").value;
	   		  if(queryDate==""||queryDate==null)
	   		  {
	   			  alert("请选择查询时间！");
	   			  document.getElementById("queryDate").focus();
	   			  return false;
	   		  }         
	        	var url="queryCarryBills!execute";
	        	xmlHttpRequest = createXmlHttpRequest();
	        	xmlHttpRequest.onreadystatechange = queryBillsback; 
	        	xmlHttpRequest.open("POST", url, false);
	        	xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	        	xmlHttpRequest.send("queryDate="+queryDate);
	        	}
	   	   function queryBillsback()
	   	   {
	   		if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
	   		{
	   			document.getElementById("detailTab").style.display="";
	   			var b = xmlHttpRequest.responseText;
				b=eval("("+b+")");
				var c=eval("("+b+")");
				var tab = document.getElementById("detailTab");
				var len = c.length;
	   			var i = 0;
	   			while(tab.rows.length>1)
	   			{
	   				for(var rows=tab.rows.length-1;rows>0;rows--)
	   				tab.deleteRow(rows);
	   			}
	   			for(i=0;i<len;i++){
	   				var row = tab.insertRow(tab.rows.length);
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].carryNumber;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].carNumber;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].contactName;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].address;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].startTime;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].carBeCost;
	   				var cell = row.insertCell(-1);
	   				cell.innerText=c[i].bInternalCar;
	   			}
	   		}
	   		else{
	   			alert(xmlhttp.status);
        		alert("Problem retrieving XML data");
	   		}
	   	   }
</script>  
</body>
</html>