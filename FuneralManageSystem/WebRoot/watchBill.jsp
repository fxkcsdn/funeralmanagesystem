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
    <script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
    </script>
    
    <script>
       function getWatchSpirit() 
       {
          xmlhttp = null;         
          var watchNumber = document.getElementById("watchNumberToSearch").value; 
          document.getElementById("watchNumber").value = watchNumber;               
          var url="/FuneralManageSystem/getWatchSpirit.action?watchNumber=" + watchNumber;          
          if (window.XMLHttpRequest)
          {// code for all new browsers
             xmlhttp = new XMLHttpRequest();
          }
          else if (window.ActiveXObject)
          {// code for IE5 and IE6
             xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
          }

          if (xmlhttp != null) 
          {
             xmlhttp.onreadystatechange = getWatchSpiritBack;
             xmlhttp.open("POST", url, false);
             xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
             xmlhttp.send(null);
          }
       }

      //回调函数
      function getWatchSpiritBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfWatchSpirit = eval("("+ xmlhttp.responseText +")");       
            dataOfWatchSpirit = eval("("+ dataOfWatchSpirit +")");            
            showWatchSpirit(dataOfWatchSpirit);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function updateWatchSpirit(endTime, coffinRealCost, villaRealCost, watchNumber, villaNumber, coffinNumber, coffinBeCost, villaBeCost) 
       {
          xmlhttp = null;         
           var url="/FuneralManageSystem/updateWatchSpirit.action?watchNumber=" + watchNumber + "&&endTime=" + endTime + "&&coffinRealCost=" + coffinRealCost + "&&villaRealCost=" + villaRealCost + "&&villaNumber=" + villaNumber + "&&coffinNumber=" + coffinNumber + "&&coffinBeCost=" + coffinBeCost + "&&villaBeCost=" + villaBeCost;          
          if (window.XMLHttpRequest)
          {// code for all new browsers
             xmlhttp = new XMLHttpRequest();
          }
          else if (window.ActiveXObject)
          {// code for IE5 and IE6
             xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
          }

          if (xmlhttp != null) 
          {
             xmlhttp.onreadystatechange = updateWatchSpiritBack;
             xmlhttp.open("POST", url, false);
             xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
             xmlhttp.send(null);
          }
       }

      //回调函数
      function updateWatchSpiritBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfWatchSpirit = eval("("+ xmlhttp.responseText +")");       
            dataOfWatchSpirit = eval("("+ dataOfWatchSpirit +")");            
             if (dataOfWatchSpirit.result == "yes")
            {
               alert("保存成功!");
               change();
            } 
            else
            {
               alert("保存失败!");
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
         document.getElementById("endTime").setAttribute("disabled","true");
         document.getElementById("coffinRealCost").setAttribute("disabled","true");
         document.getElementById("villaRealCost").setAttribute("disabled","true");
      }
      
      function showWatchSpirit(dataOfWatchSpirit)
      {
          if (dataOfWatchSpirit != null)
          {                            
             document.getElementById("detail").style.display = 'block';            
             
             document.getElementById("deadId").value = dataOfWatchSpirit.deadId;
             document.getElementById("memberName").value = dataOfWatchSpirit.memberName;
             document.getElementById("memberMobile").value = dataOfWatchSpirit.memberMobile;
             document.getElementById("villaNumber").value = dataOfWatchSpirit.villaNumber;            
             document.getElementById("coffinNumber").value = dataOfWatchSpirit.coffinNumber;
             document.getElementById("startTime").value = dataOfWatchSpirit.startTime;
             document.getElementById("endTime").value =  dataOfWatchSpirit.endTime;
             //document.getElementById("coffinBeCost").value = calculate("1");
             //document.getElementById("coffinRealCost").value = document.getElementById("coffinBeCost").value;
             calculate();
             //document.getElementById("villaBeCost").value = calculate("2");
             //document.getElementById("villaRealCost").value = document.getElementById("villaBeCost").value;                                  
          }
          else
          {            
             document.getElementById("detail").style.display = 'none';            
             alert("守灵信息不存在！");
             
             document.getElementById("watchNumberToSearch").value ="";
             document.getElementById("deadId").value ="";
             document.getElementById("memberName").value = "";
             document.getElementById("memberMobile").value = "";
             document.getElementById("villaNumber").value = "";          
             document.getElementById("coffinNumber").value = "";
             document.getElementById("startTime").value = "";
             document.getElementById("endTime").value =  "";
             document.getElementById("coffinBeCost").value = "";
             document.getElementById("coffinRealCost").value = "";
             document.getElementById("villaBeCost").value = "";
             document.getElementById("villaRealCost").value = "";  
          }
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
         var hours=Math.floor(leave1/(3600*1000));
         var leave2=leave1%(100*3600);
         var minutes1 = Math.floor(leave2/(1000*60));
         
         var days2 = days;
         var hours2 = hours;
         var minutes2 = minutes1;
         
         hours = hours + days * 24;
         var money1 = hours * 8;
         
         if (hours2 > 0 || minutes2 > 0)
         {
               days2 = days2 + 1;
         }
         var money2 = days2 * 1500;   
         
          
         document.getElementById("coffinBeCost").value = money1;
         document.getElementById("coffinRealCost").value = document.getElementById("coffinBeCost").value;
         
         document.getElementById("villaBeCost").value = money2;
         document.getElementById("villaRealCost").value = document.getElementById("villaBeCost").value;
         
                 
        // return money;       
      }
      
      function saveInformationOfWatch()
      {
         var watchNumber = document.getElementById("watchNumber").value;
         var endTime = document.getElementById("endTime").value;
         var coffinBeCost = document.getElementById("coffinBeCost").value;
         var coffinRealCost = document.getElementById("coffinRealCost").value;
         var villaBeCost = document.getElementById("villaBeCost").value;
         var villaRealCost = document.getElementById("villaRealCost").value;
         var villaNumber = document.getElementById("villaNumber").value;
         var coffinNumber = document.getElementById("coffinNumber").value;
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          
         updateWatchSpirit(endTime, coffinRealCost, villaRealCost, watchNumber, villaNumber, coffinNumber, coffinBeCost, villaBeCost);
      }
    </script>
  </head>
  
  <body>
     <input type="hidden" id="watchNumber"/>
     <div id="search">
     <table>
       <tr>         
          <td>守灵编号：</td>
          <td><input type="text" id="watchNumberToSearch" name="watchNumberToSearch"/></td>       
          <td><input type="button" value="查询" onclick="getWatchSpirit()"></td>
       </tr>     
    </table>
    <br><br>
    </div>
    <div id="detail" style="display:none">
       
       <table>
          <tr>
             <td style="text-align:right">逝者身份证号：</td>
             <td><input type="text" id="deadId" name="deadId" readonly="readonly" disabled="true"/></td>
          </tr>
          <tr>
             <td style="text-align:right">家属姓名：</td>
             <td><input type="text" id="memberName" name="memberName" readonly="readonly" disabled="true"/></td>
             <td style="text-align:right">家属手机：</td>
             <td><input type="text" id="memberMobile" name="memberMobile" readonly="readonly" disabled="true"/></td>
          </tr>
          <tr>
             <td style="text-align:right">别墅号：</td>
             <td><input type="text" id="villaNumber" name="villaNumber" readonly="readonly" disabled="true"/></td>
             <td style="text-align:right">水晶棺号：</td>
             <td><input type="text" id="coffinNumber" name="coffinNumber" readonly="readonly" disabled="true"/></td>
          </tr>
          <tr>
             <td style="text-align:right">开始时间：</td>
             <td><input type="text" id="startTime" name="startTime" readonly="readonly" disabled="true"/></td>
             <td style="text-align:right">结束时间：</td>
             <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" onchange="calculate()"/></td>
          </tr>
          <tr>
             <td style="text-align:right">水晶棺应收费用：</td>
             <td><input type="text" id="coffinBeCost" name="coffinBeCost" readonly="readonly" disabled="true"/></td>
             <td style="text-align:right">水晶棺实收费用：</td>
             <td><input type="text" id="coffinRealCost" name="coffinRealCost" /></td>
          </tr>
          <tr>
             <td style="text-align:right">别墅应收费用：</td>
             <td><input type="text" id="villaBeCost" name="villaBeCost" readonly="readonly" disabled="true"/></td>
             <td style="text-align:right">别墅实收费用：</td>
             <td><input type="text" id="villaRealCost" name="villaRealCost" /></td>
          </tr>
       </table>
       <input type="button" id="save" value="保存" onclick="saveInformationOfWatch()"> 
    </div>
  </body>
</html>
