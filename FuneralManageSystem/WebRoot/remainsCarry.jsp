<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'remainsCarry.jsp' starting page</title>
      <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/json2.js"></script>
    <script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
    <script>
      var xmlhttp;
      //连接服务器请求数据          
    
       window.onload = initialization;
       function initialization()
       {
       	   var deadIdFromRegister=window.parent.document.getElementById("deadIdStorage").value;
       	   
       	   document.getElementById("deadID").value=deadIdFromRegister;
           getCoffin();
           getCurrentTime();        //获取当前日期，填入“接运时间”   
       }
      function gotoPrintCode(){
      
      var type=document.cookie.indexOf("type");
      
      var index=document.cookie.indexOf("index");
      var length=document.cookie.length;
     
      
      
      type=document.cookie.substring(type+5,index-1);
      index=document.cookie.substring(index+7,length-1);
     
      
      if(type==="zhenghuo"){
      		if(window.parent.document.getElementById("deadIdStorage").value!=""){
      		
	       		window.parent.location.reload("right-zhenghuo.jsp?deadId="
			+ window.parent.document.getElementById("deadIdStorage").value + "&index="+2);
	       }else{
	       	
	       	window.parent.location.reload("right-zhenghuo.jsp?deadId="
			+ document.getElementById("deadID").value + "&index="+2);
	       }
	       		
	       		}else if(type==="yuyue"){
	       		window.parent.location.reload("right-yuhuo.jsp?deadId="
			+ window.parent.document.getElementById("deadIdStorage").value + "&index="+2);
	       		
	       		}else{
	       		    	window.parent.location.reload("right-zhenghuo.jsp?deadId="+document.getElementById("deadID").value+"&index="+2);
	       		
	       		}
	       		
	       		
	       		
      }
      function getCoffin() 
      {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getCoffin.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getCoffinBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getCoffinBack() 
      {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfCoffin = eval("("+ xmlhttp.responseText +")");
            dataOfCoffin = eval("("+ dataOfCoffin +")");
            showNumberOfCoffin(dataOfCoffin);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function getOutCoffinNo()  //点击查询租棺信息
      {
        xmlhttp = null;  
        var coffinNumberToSearch = document.getElementById("coffinNumberToSearch").value; 
        var rentNumberToSearch = document.getElementById("rentNumberToSearch").value;      
        var url="/FuneralManageSystem/getOutCoffinNo.action?coffinNumber=" + coffinNumberToSearch + "&&rentNumber=" + rentNumberToSearch;
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getOutCoffinNoBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
        var deadIdFromRegister=window.document.getElementById("deadID").value;
       	document.getElementById("deadId").value=deadIdFromRegister;
      }

      //回调函数
      function getOutCoffinNoBack() 
      {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {                               
            var dataOfCoffinNo = eval("("+ xmlhttp.responseText +")");       
            dataOfCoffinNo = eval("("+ dataOfCoffinNo +")");            
            showCoffinNo(dataOfCoffinNo);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function getCarryNumber() 
      {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getCarryNumber.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getCarryNumberBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getCarryNumberBack() 
      {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {
            var dataOfCarryNumber = eval("("+ xmlhttp.responseText +")");
            dataOfCarryNumber = eval("("+ dataOfCarryNumber +")");       
            document.getElementById("carryNumber").value = dataOfCarryNumber.carryNumber;
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      function chooseNumberOfCoffin(coffinNumber, bAvailable)
      {
         if (bAvailable == 1)
         {
            alert("该水晶棺未租用，请重新选择！");
         }
         else
         {
            document.getElementById("coffinNumberToSearch").value = coffinNumber;
         }
      }
      
      function showNumberOfCoffin(dataOfCoffin)
      {             
         var dataLength = dataOfCoffin.length;        
         var p = document.getElementById("coffin");        
         for (var i = 0; i < dataLength; ++i)
         {
              var image = document.createElement("img");
              image.setAttribute("id", dataOfCoffin[i].coffinNumber);
              if (dataOfCoffin[i].bAvailable == 1)
              {
                  image.setAttribute("src","Images/green.png");
              }
              else
              {
                  image.setAttribute("src","Images/red.png");
              }
              image.setAttribute("alt",dataOfCoffin[i].bAvailable); 
              image.setAttribute("width","35px");
              image.setAttribute("height","20px");             
              image.setAttribute("onclick","chooseNumberOfCoffin("+ dataOfCoffin[i].coffinNumber +',' + dataOfCoffin[i].bAvailable +")");
              p.appendChild(image); 
              p.innerHTML +="<strong>"+(Number(dataOfCoffin[i].coffinNumber) > 9?dataOfCoffin[i].coffinNumber:"0"+ dataOfCoffin[i].coffinNumber) +"</strong>";
              p.innerHTML +="&nbsp;&nbsp;";
              if ((i+1) % 4 == 0)
              {
                 p.innerHTML += "<br>";
              }                                      
         }       
      }
      
      
      function showCoffinNo(dataOfCoffinNo)
      {
          if (dataOfCoffinNo != null)
          {
             document.getElementById("detail").style.display = 'block';            
             
             document.getElementById("rentNumber").value = dataOfCoffinNo.rentNumber;
             document.getElementById("deadId").value = dataOfCoffinNo.deadId;
             document.getElementById("coffinNumber").value = dataOfCoffinNo.coffinNumber;
             document.getElementById("startTime").value = dataOfCoffinNo.startTime;
             //document.getElementById("carryTime").value = dataOfCoffinNo.carryTime;          
             document.getElementById("address").value = dataOfCoffinNo.address;
             document.getElementById("contactMobile").value = dataOfCoffinNo.contactMobile;
             document.getElementById("contactName").value = dataOfCoffinNo.contactName;   
             document.getElementById("carCost").value = ""; 
             document.getElementById("carRealCost").value = "";   
             document.getElementById("driverName").value = "";        
             document.getElementById("driverPhone").value = ""; 
             document.getElementById("carNumber").selectedIndex = 0;
             document.getElementById("bInternalCar").selectedIndex = 0;
             document.getElementById("carryType").selectedIndex = 0;
             document.getElementById("carNumber").removeAttribute("disabled");
             document.getElementById("driverName").removeAttribute("disabled");
             document.getElementById("driverPhone").removeAttribute("disabled");
          }
          else
          {            
             document.getElementById("detail").style.display = 'none';            
             alert("租棺信息不存在！");
             document.getElementById("coffinNumberToSearch").value = "";
             document.getElementById("rentNumberToSearch").value = "";
             document.getElementById("rentNumber").value = "";
             document.getElementById("deadId").value = "";
             document.getElementById("coffinNumber").value = "";
             document.getElementById("startTime").value = "";
             document.getElementById("carryTime").value = "";         
             document.getElementById("address").value = "";
             document.getElementById("contactMobile").value = "";
             document.getElementById("contactName").value = "";  
             document.getElementById("carCost").value = ""; 
             document.getElementById("carRealCost").value = ""; 
             document.getElementById("driverName").value = "";        
             document.getElementById("driverPhone").value = ""; 
             document.getElementById("carNumber").selectedIndex = 0;
             document.getElementById("bInternalCar").selectedIndex = 0;
             document.getElementById("carryType").selectedIndex = 0;
             document.getElementById("carNumber").removeAttribute("disabled");
             document.getElementById("driverName").removeAttribute("disabled");
             document.getElementById("driverPhone").removeAttribute("disabled");
          }
      }   
      
      function calculate()
      { 
       //alert("进入计算函数！");      
       var startTime =  document.getElementById("startTime").value;
       var endTime =  document.getElementById("carryTime").value;
       var date1 = Date.parse(startTime.replace(/-/g, "/")); //begintime 为开始时间
       var date2 = Date.parse(endTime.replace(/-/g, "/"));   // endtime 为结束时间      
       
       var date3=date2-date1;  //时间差的毫秒数
       var days=Math.floor(date3/(24*3600*1000));
       var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
       var hours=Math.floor(leave1/(3600*1000))+days*24;
       var money = hours * 8;
       document.getElementById("duration").value = hours;
       document.getElementById("beRentCost").value = money; //填写应收费用
       //document.getElementById("realRentCost").value = document.getElementById("beRentCost").value;
      // return money;       
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
   	   function linkRentCoffinInfo() //关联租棺信息和逝者身份证
   	   {
   	   		var a =  document.getElementById("deadId").value; //身份证
   	   		var b =  document.getElementById("carryTime").value;//还棺时间
   	   		if(a.length==0||b.length==0)
   	   		{
   	   			alert("请完善信息！");
   	   		}
   	   		else
   	   		{
   	   			var c =  document.getElementById("beRentCost").value;//应收费用
   	   		var d =  document.getElementById("rentNumber").value;//租棺单号
   	   		var e =  document.getElementById("coffinNumber").value;//水晶棺号
   	   		var url = "linkCoffinInfoAction!connectCoffin";
   	   		xmlHttpRequest = createXmlHttpRequest();
   	   		xmlHttpRequest.onreadystatechange = linkRentCoffinInfoBack;
   	   		xmlHttpRequest.open("POST",url,true);
   	   		xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
   	   		xmlHttpRequest.send("deadID="+a+"&returnTime="+b+"&beRentCost="+c+"&rentNumber="+d+"&coffinNumber="+e);
   	   		}	
   	   		//alert(a+b+c+d);
   	   }
   	   function linkRentCoffinInfoBack() //关联租棺信息回调函数
   	   {
   	   		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			var b = xmlHttpRequest.responseText;
			var c = b;
			alert(c);
			location.reload(); 
		}
   	   }
   	   
   	   function getCurrentTime()
   	   {
   	   		var myDate = new Date();
   	   		var b = myDate.toLocaleDateString();     //获取当前日期
   	   		var c = b.replace("/","-");
   	   		var d = c.replace("/","-");
   	   		document.getElementById("carryOrderDate").value = d;
   	   }
   	   
   	   function searchCarryOrder()      //查询预计时间为当前日期的接运订单
   	   {
   	   		var myDate = new Date();
   	   		var monthTmp = myDate.getMonth()+1;
   	   		var dayTmp = myDate.getDate();
   	   		var month = 0;
   	   		var day = 0;
   	   		if(dayTmp<10)
   	   		{
   	   			day="0"+dayTmp;
   	   		}
   	   		else{
   	   			day=dayTmp;
   	   		}
   	   		if(monthTmp<10)
   	   		{
   	   			month = "0"+monthTmp;
   	   		}
   	   		else
   	   		{
   	   			month = monthTmp;
   	   		}
   	   		var d = myDate.getFullYear()+"-"+month+"-"+day;
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
          	xmlhttp.send("time="+d);
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
   	   					cell.innerText=c[i].contactName;
   	   					var cell = row.insertCell(-1);
   	   					cell.innerText=c[i].carryTime;
   	   					var cell = row.insertCell(-1);
   	   					cell.innerText=c[i].carryAddress;
   	   					var cell = row.insertCell(-1);
   	   					cell.innerText=c[i].carNumber;
   	   					var cell = row.insertCell(-1);
   	   					cell.innerHTML="<input type='button' value='确认' onclick='proptInfo(\""+c[i].carryNumber+"\")'>";
   	   				}           
          		}
          		else
          		{
            		alert(xmlhttp.status);
            		alert("Problem retrieving XML data");
          		}
        	}
   	   }
   	   function proptInfo(a)
   	   {
   	   		
   	   		var se = confirm("你选中的接运单号为："+a);
   	   		var id = document.getElementById("deadID").value;
   	   		if(id.length==0)
   	   		{
   	   			alert("还未填写身份证！");
   	   		}
   	   		else
   	   		{
   	   			if(se==true)
   	   			{
   	   				var carryNumber = a;
   	   				connectDeadId(carryNumber);
   	   			}
   	   			else
   	   			{
   	   				alert("请重新选择！");
   	   				searchCarryOrder();
   	   			}	
   	   		}	
   	   }
   	   function connectDeadId(carryNumber)   //将身份证填入选中的选中的接运单号中
   	   {
   	   		var url = "linkCarryInfoAction!connectCarryInfo";
   	   		var deadId = document.getElementById("deadID").value;
   	   		//alert(deadId);
   	   		//alert(carryNumber);
   	   		xmlHttpRequest = createXmlHttpRequest(); 
		    xmlHttpRequest.onreadystatechange = connectDeadIdCallback; 
		    xmlHttpRequest.open("POST",url,true);
		    xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		    xmlHttpRequest.send("deadId="+deadId+"&carryNumber="+carryNumber); 
   	   }
   	   function connectDeadIdCallback()
   	   {
   	   		if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200)
		{
			var b = xmlHttpRequest.responseText;
			var c = b;
			alert(c);
			var tab = document.getElementById("carryTab"); //点击提交，刷新表格
			while(tab.rows.length>1)
   	   		{
   	   			for(var rows=tab.rows.length-1;rows>0;rows--)
   	   		    tab.deleteRow(rows);
   	   		}
			//location.reload()
		}
   	   }
    </script>
    <style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>  
  </head>
  
  <body>
    <input type="hidden" id="carryNumber"/> 
    <input type="hidden" id="saveRentNumber"/> 
    <input type="hidden" id="saveDeadId"/> 
    <input type="hidden" id="saveContactName"/>
    <input type="hidden" id="saveContactPhone"/>
    <input type="hidden" id="saveCoffinNumber"/>
    <input type="hidden" id="saveAddress"/>
    <input type="hidden" id="saveStartTime"/>
    <input type="hidden" id="saveCarryTime"/>
    <input type="hidden" id="saveCarryType"/>
    <input type="hidden" id="saveCarCost"/>
    <input type="hidden" id="saveCarRealCost"/>
    <input type="hidden" id="saveCarNumber"/>
    <input type="hidden" id="saveDriverName"/>
    <input type="hidden" id="saveDriverPhone"/>
    <input type="hidden" id="saveBInternalCar"/>
    <div style="font-weight: bold;font-family:黑体;color:blue;">关联接运信息：</div>
    <div>
    <table style="cellspacing:20px;">
    	<tr>
    		<td>接运时间：</td>
    		<td><input type="text" id="carryOrderDate" name="carryOrderDate" style="font-size:20px;width:150px" readonly="readonly"/></td>
    		<td><input type="button" id="carryButton" name="carryButton" value="查询" onclick="searchCarryOrder()"></td>
    		<td>逝者身份证</td>
    		<td><input type="text" id="deadID" name="deadID" style="font-size:20px;width:210px"/></td>
    	</tr>
    </table>
    </div>
    <div id="carryDiv" style="display:none">
    	<table id="carryTab" width=98% border="1" style="text-align:center;">
    		<tr>
    			<th>接运单号</th><th>联系人</th><th>接运时间</th><th>接运地点</th><th>车牌号</th><th>操作</th>
    		</tr>
    	</table>
    </div>
    <hr>
    <div style="font-weight: bold;font-family:黑体;color:blue;">关联租棺信息：</div>   
    <div id="search" >
    <table>
       <tr>
          <td>水晶棺编号：</td>
          <td><input type="text" id="coffinNumberToSearch" name="coffinNumberToSearch" readonly="readonly" disabled="true"/></td>
          <td>租棺编号：</td>
          <td><input type="text" id="rentNumberToSearch" name="rentNumberToSearch"/></td>
          <td><input type="submit" value="查询" onclick="getOutCoffinNo()"></td>
          <td><input type="button" id="gotoPrintCode" onclick="gotoPrintCode()" value="打印遗体二维码"></td>
       </tr>
       <tr>
          <td id="coffin" colspan="3"></td>
       </tr>
    </table>
     <br>
    <br>
    </div>
    <div id="detail" style="display:none;">
    <table>
       <tr>
          <td style="text-align:right;">租棺编号：</td>
          <td><input type="text" id="rentNumber" name="rentNumber" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">逝者身份证号：</td>
          <td><input type="text" id="deadId" name="deadId"/></td>
          <td style="text-align:right">水晶棺编号：</td>
          <td><input type="text" id="coffinNumber" name="coffinNumber" readonly="readonly" disabled="true"/></td>
       </tr>
       <tr>
          <td style="text-align:right">租棺时间：</td>
          <td><input type="text" id="startTime" name="startTime" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">还棺时间：</td>
          <td><input type="text" id="carryTime" name="carryTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" onchange="calculate()"/></td>
          <td style="text-align:right">租棺类型：</td>
          <td><select id="carryType" name="carryType" disabled="true"><option value="火化">火化</option><option value="守灵">守灵</option><option value="冷藏">冷藏</option></select></td>
       </tr>
       <tr>
          <td style="text-align:right">租棺地址：</td>
          <td><input type="text" id="address" name="address" readonly="readonly" disabled="true" /></td>
          <td style="text-align:right">联系人手机：</td>
          <td><input type="text" id="contactMobile" name="contactMobile" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">联系人姓名：</td>
          <td><input type="text" id="contactName" name="contactName" readonly="readonly" disabled="true"/></td>
       </tr>
       <tr>
       	  <td style="text-align:right">用棺时长：</td>
          <td><input type="text" id="duration" name="address" style="width:50px" readonly="readonly" disabled="true"/>小时</td>
       	  <td style="text-align:right">应收费用：</td>
          <td><input type="text" id="beRentCost" name="address" style="width:50px" readonly="readonly" disabled="true"/>元</td>
       </tr>
    </table>
    <input type="submit" id="save" value="保存" onclick="linkRentCoffinInfo()">  
    </div> 
  </body>
</html>
