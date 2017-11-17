<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateRelatedInfo.jsp' starting page</title>
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
	<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
    <script>
        var xmlhttp;
      //连接服务器请求数据    
        window.onload = getCoffin;
        
        function getCoffin() {
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
      function getCoffinBack() {
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
      
        function getRemainsCarry() {
        xmlhttp = null;         
        var carryNumber = document.getElementById("carryNumberToSearch").value; 
        document.getElementById("carryNumber").value = carryNumber;  
        var coffinNumber = document.getElementById("coffinNumberToSearch").value;     
        var url="/FuneralManageSystem/getRemainsCarry.action?coffinNumber=" + coffinNumber + "&&carryNumber=" + carryNumber + "&&carryType=火化";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getRemainsCarryBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getRemainsCarryBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfRemainsCarry = eval("("+ xmlhttp.responseText +")");       
            dataOfRemainsCarry = eval("("+ dataOfRemainsCarry +")");            
            showRemainsCarry(dataOfRemainsCarry);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
       function updateRentCoffin(rentNumber, deadId, endTime, coffinNumber, beRentCost, realRentCost) {
        xmlhttp = null;         
        var url="/FuneralManageSystem/updateRentCoffin.action?rentNumber=" + rentNumber + "&&deadId=" + deadId + "&&returnTime=" + endTime + "&&coffinNumber=" + coffinNumber + "&&beRentCost=" + beRentCost + "&&realRentCost=" + realRentCost;     
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = updateRentCoffinBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function updateRentCoffinBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfRemainsCarry = eval("("+ xmlhttp.responseText +")");       
            dataOfRemainsCarry = eval("("+ dataOfRemainsCarry +")");            
            if (dataOfRemainsCarry.result == "no")
            {
               alert("保存失败！");
            }
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
       function updateRemainsCarry(carryNumber, deadId, returnTime) {
        xmlhttp = null;         
        var url="/FuneralManageSystem/updateRemainsCarry.action?carryNumber=" + carryNumber + "&&deadId=" + deadId + "&&returnTime=" + returnTime;       
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = updateRemainsCarryBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function updateRemainsCarryBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfRemainsCarry = eval("("+ xmlhttp.responseText +")");       
            dataOfRemainsCarry = eval("("+ dataOfRemainsCarry +")");            
            if (dataOfRemainsCarry.result == "yes")
            {
               alert("保存成功！");
               change();
            }
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function change()
      {
         document.getElementById("save").style.display = 'none';  
         document.getElementById("search").style.display = 'none';      
         document.getElementById("coffin").style.display = 'none'; 
         document.getElementById("ReadCard").style.display = 'none';           
         document.getElementById("rentNumber").setAttribute("disabled","true");
         document.getElementById("coffinNumber").setAttribute("disabled","true");
         document.getElementById("deadId").setAttribute("disabled","true");
         document.getElementById("contactName").setAttribute("disabled","true");
         document.getElementById("contactMobile").setAttribute("disabled","true"); 
         document.getElementById("address").setAttribute("disabled","true");       
         document.getElementById("startTime").setAttribute("disabled","true");  
         document.getElementById("endTime").setAttribute("disabled","true"); 
         document.getElementById("realRentCost").setAttribute("disabled","true"); 
         document.getElementById("carryTime").setAttribute("disabled","true");   
         document.getElementById("returnTime").setAttribute("disabled","true");  
         document.getElementById("carryType").setAttribute("disabled","true");        
         document.getElementById("bInternalCar").setAttribute("disabled","true");
         //document.getElementById("carCost").setAttribute("disabled","true");
         document.getElementById("carNumber").setAttribute("disabled","true");
      }
      
      function showRemainsCarry(dataOfRemainsCarry)
      {
          if (dataOfRemainsCarry != null)
          {                            
             document.getElementById("detail").style.display = 'block';            
             
             document.getElementById("rentNumber").value = dataOfRemainsCarry.rentNumber;
             document.getElementById("carryNumber").value = dataOfRemainsCarry.carryNumber;
             document.getElementById("coffinNumber").value = dataOfRemainsCarry.coffinNumber;
             document.getElementById("deadId").value = dataOfRemainsCarry.deadId;            
             document.getElementById("startTime").value = dataOfRemainsCarry.startTime;
             document.getElementById("endTime").value = dataOfRemainsCarry.endTime;
             //document.getElementById("beRentCost").value = calculate();
             //document.getElementById("realRentCost").value = document.getElementById("beRentCost").value;
             calculate();
             document.getElementById("carBeCost").value = dataOfRemainsCarry.carBeCost;
             document.getElementById("carRealCost").value = dataOfRemainsCarry.carRealCost;
             document.getElementById("carryTime").value = dataOfRemainsCarry.carryTime; 
             document.getElementById("returnTime").value = dataOfRemainsCarry.returnTime;  
             document.getElementById("carryType").value = dataOfRemainsCarry.carryType;       
             document.getElementById("address").value = dataOfRemainsCarry.address;
             document.getElementById("contactMobile").value = dataOfRemainsCarry.contactMobile;
             document.getElementById("contactName").value = dataOfRemainsCarry.contactName;  
             document.getElementById("carNumber").value = dataOfRemainsCarry.carNumber;
             document.getElementById("carCarryBeCost").value = dataOfRemainsCarry.carCarryBeCost;
             document.getElementById("carCarryRealCost").value = dataOfRemainsCarry.carCarryRealCost;
             document.getElementById("bInternalCar").value = (dataOfRemainsCarry.bInternalCar == "0")?"否":"是";                    
          }
          else
          {            
             document.getElementById("detail").style.display = 'none';            
             alert("接运信息不存在！");
             document.getElementById("coffinNumberToSearch").value = "";
             document.getElementById("carryNumberToSearch").value = "";
             document.getElementById("rentNumber").value = "";
             document.getElementById("carryNumber").value = "";
             document.getElementById("coffinNumber").value = "";
             document.getElementById("deadId").value = "";           
             document.getElementById("startTime").value = "";
             document.getElementById("endTime").value = "";
             document.getElementById("beRentCost").value = "";
             document.getElementById("realRentCost").value = "";
             document.getElementById("carBeCost").value = "";
             document.getElementById("carRealCost").value = "";
             document.getElementById("carryTime").value = "";  
             document.getElementById("returnTime").value = ""; 
             document.getElementById("carryType").value = "";         
             document.getElementById("address").value = "";
             document.getElementById("contactMobile").value = "";
             document.getElementById("contactName").value = "";            
             document.getElementById("carNumber").value = "";
             document.getElementById("carCarryBeCost").value = "";
             document.getElementById("carCarryRealCost").value = "";
             document.getElementById("bInternalCar").value = "";
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
      
      function saveInformation()
      {
          var rentNumber = document.getElementById("rentNumber").value;
          var carryNumber = document.getElementById("carryNumber").value;
          var deadId = document.getElementById("deadId").value;
          var endTime = document.getElementById("endTime").value;
          var returnTime = document.getElementById("returnTime").value;
          var coffinNumber = document.getElementById("coffinNumber").value;
          var beRentCost = document.getElementById("beRentCost").value;
          var realRentCost = document.getElementById("realRentCost").value;
          if (deadId == null || deadId == "")
         {
            alert("请输入逝者身份证号！");
            return;
         }
          else
         {
             var reg15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
             var reg18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
             if(!reg15.test(deadId) && !reg18.test(deadId))
             {
                  alert("逝者身份证号输入有误，请重新输入!");
                  document.getElementById("deadId").value = "";
                  return;               
             }            
         }
         if (endTime == null || endTime == "")
         {
            alert("请输入还棺时间！");
            return;
         }
         if (returnTime == null || returnTime == "")
         {
            alert("请输入到馆时间！");
            return;
         }
         
         if (realRentCost == null || realRentCost == "")
         {
            alert("请输入租棺实收费用！");
            return;
         }
         else
         {
            var reg = /^[1-9]\d*|0$/;
            if(!reg.test(realRentCost))
             {
                  alert("租棺实收费用输入有误，请重新输入!");
                  document.getElementById("realRentCost").value = beRentCost;
                  return;               
             }
             else if (Number(realRentCost) > Number(beRentCost))
             {
                  alert("租棺实收费用超出租棺应收费用，请重新输入!");
                  document.getElementById("realRentCost").value = beRentCost;
                  return;
             } 
         }
          
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          updateRentCoffin(rentNumber, deadId, endTime, coffinNumber, beRentCost, realRentCost);
          updateRemainsCarry(carryNumber, deadId, returnTime);          
      }
      
      function ReadIDCard() 
      {
  		 document.getElementById("deadId").value = "";
 		 var ret = CVR_IDCard.ReadCard();
		 if (ret == "0")
		 {
      		fillForm();      
     		 return;
   		 } 
   		alert("读卡错误,错误原因:" + ret);
	}

	function fillForm() 
	{     
 		 var pCardNo=CVR_IDCard.CardNo;
 		 document.getElementById("deadId").value = pCardNo;
	}

    function calculate()
   {       
       var startTime =  document.getElementById("startTime").value;
       var endTime =  document.getElementById("endTime").value;
       var date1 = Date.parse(startTime.replace(/-/g, "/")); //begintime 为开始时间
       var date2 = Date.parse(endTime.replace(/-/g, "/"));   // endtime 为结束时间      
       
       var date3=date2-date1;  //时间差的毫秒数
       var days=Math.floor(date3/(24*3600*1000));
       var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
       var hours=Math.floor(leave1/(3600*1000))+days*24;
      
       var money = hours * 8;
       
       document.getElementById("beRentCost").value = money;
       document.getElementById("realRentCost").value = document.getElementById("beRentCost").value;
      // return money;       
   }
    </script>
   <style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>  
  </head>
  
  <body>
  <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
     <input type="hidden" id="carryNumber"/>
     <div id="search">
    <table>
       <tr>
          <td>水晶棺编号：</td>
          <td><input type="text" id="coffinNumberToSearch" name="coffinNumberToSearch" readonly="readonly" disabled="true"/></td>
          <td>接运编号：</td>
          <td><input type="text" id="carryNumberToSearch" name="carryNumberToSearch"/></td>       
          <td><input type="submit" value="查询" onclick="getRemainsCarry()"></td>
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
          <td style="text-align:right">租棺编号：</td>
          <td><input type="text" id="rentNumber" name="rentNumber" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">水晶棺编号：</td>
          <td><input type="text" id="coffinNumber" name="coffinNumber" readonly="readonly" disabled="true"/></td>        
       </tr>
        <tr>         
          <td style="text-align:right">逝者身份证号：</td>
          <td><input type="text" id="deadId" name="deadId"/></td>  
           <td><input type="button" name="ReadCard" id="ReadCard" value="读取身份证号码" onclick="return ReadIDCard()" style="width: 125px; ">      </td>
       </tr>
       <tr>
          <td style="text-align:right">送棺时间：</td>
          <td><input type="text" id="startTime" name="startTime" readonly="readonly" disabled="true"/></td>
           <td style="text-align:right">还棺时间：</td>
          <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'returnTime\')}'})" onchange="calculate()"/></td>
       </tr>
        <tr>
          <td style="text-align:right">租棺应收费用：</td>
          <td><input type="text" id="beRentCost" name="beRentCost" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">租棺实收费用：</td>
          <td><input type="text" id="realRentCost" name="realRentCost" /></td>        
       </tr>
         <tr>
          <td style="text-align:right">租棺用车应收费用：</td>
          <td><input type="text" id="carBeCost" name="carBeCost" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">租棺用车实收费用：</td>
          <td><input type="text" id="carRealCost" name="carRealCost" readonly="readonly" disabled="true"/></td>        
       </tr>
       <tr>
          <td style="text-align:right">接运时间：</td>
          <td><input type="text" id="carryTime" name="carryTime" readonly="readonly" disabled="true"/></td>
           <td style="text-align:right">到馆时间：</td>
          <td><input type="text" id="returnTime" name="returnTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'#F{$dp.$D(\'endTime\')}'})" /></td>         
       </tr>
       <tr>
          <td style="text-align:right">接运类型：</td>
          <td><input type="text" id="carryType" name="carryType" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">接运地址：</td>
          <td><input type="text" id="address" name="address" readonly="readonly" disabled="true"/></td>
       </tr>
       <tr>
          <td style="text-align:right">联系人手机：</td>
          <td><input type="text" id="contactMobile" name="contactMobile" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">联系人姓名：</td>
          <td><input type="text" id="contactName" name="contactName" readonly="readonly" disabled="true"/></td>
       </tr>
       <tr>
          <td style="text-align:right">是否本馆车辆：</td>
          <td><input type="text" id="bInternalCar" name="bInternalCar" readonly="readonly" disabled="true"/></td>
          <td style="text-align:right">接运车牌号：</td>
          <td><input type="text" id="carNumber" name="carNumber" readonly="readonly" disabled="true"/></td>
       </tr>
       <tr>         
          <td style="text-align:right">接运用车应收费用：</td>
          <td><input type="text" id="carCarryBeCost" name="carCarryBeCost" readonly="readonly" disabled="true"/></td>  
          <td style="text-align:right">接运用车实收费用：</td>
          <td><input type="text" id="carCarryRealCost" name="carCarryRealCost" readonly="readonly" disabled="true"/></td>        
       </tr>
    </table>
    <input type="submit" id="save" value="保存" onclick="saveInformation()">  
    </div> 
  </body>
</html>