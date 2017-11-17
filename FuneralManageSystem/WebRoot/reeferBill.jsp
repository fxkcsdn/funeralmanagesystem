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
    <title>My JSP 'ReeferBill.jsp' starting page</title>
    
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
       function getRemainsReefer() 
       {
          xmlhttp = null;         
          var reeferNumber = document.getElementById("reeferNumberToSearch").value; 
          document.getElementById("reeferNumber").value = reeferNumber;   
          var deadId = document.getElementById("deadIdToSearch").value; 
          document.getElementById("savedeadId").value = deadId;            
          var url="/FuneralManageSystem/getRemainsReefer.action?reeferNumber=" + reeferNumber + "&&deadId=" + deadId;                  
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
             xmlhttp.onreadystatechange = getRemainsReeferBack;
             xmlhttp.open("POST", url, false);
             xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
             xmlhttp.send(null);
          }
       }

      //回调函数
      function getRemainsReeferBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfRemainsReefer = eval("("+ xmlhttp.responseText +")");       
            dataOfRemainsReefer = eval("("+ dataOfRemainsReefer +")");            
            showRemainsReefer(dataOfRemainsReefer);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function updateRemainsReefer(reeferNumber, endTime, reeferBeCost, reeferRealCost, deadId, belongings, memo, reeferNo, carryNumber) 
       {
          xmlhttp = null;         
           var url="/FuneralManageSystem/updateRemainsReefer.action?reeferNumber=" + reeferNumber + "&&endTime=" + endTime + "&&reeferBeCost=" + reeferBeCost + "&&reeferRealCost=" + reeferRealCost + "&&deadId=" + deadId + "&&belongings=" + belongings + "&&memo=" + memo + "&&reeferNo=" + reeferNo + "&&carryNumber=" + carryNumber;     
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
             xmlhttp.onreadystatechange = updateRemainsReeferBack;
             xmlhttp.open("POST", url, false);
             xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
             xmlhttp.send(null);
          }
       }

      //回调函数
      function updateRemainsReeferBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfRemainsReefer = eval("("+ xmlhttp.responseText +")");       
            dataOfRemainsReefer = eval("("+ dataOfRemainsReefer +")");            
             if (dataOfRemainsReefer.result == "yes")
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
         document.getElementById("deadId").setAttribute("disabled","true");
         document.getElementById("reeferRealCost").setAttribute("disabled","true");
         document.getElementById("belongings").setAttribute("disabled","true");
         document.getElementById("memo").setAttribute("disabled","true");
      }
      
       function showRemainsReefer(dataOfRemainsReefer)
      {
          if (dataOfRemainsReefer != null)
          {                  
             document.getElementById("reeferInfo").style.display = 'block';   
             if (dataOfRemainsReefer.carryNumber != "")
             {
                document.getElementById("carryInfo").style.display = 'block'; 
                
                document.getElementById("carryType").value = dataOfRemainsReefer.carryType;
                document.getElementById("address").value = dataOfRemainsReefer.address;
                document.getElementById("contactName").value = dataOfRemainsReefer.contactName;
                document.getElementById("contactMobile").value = dataOfRemainsReefer.contactMobile;
                document.getElementById("returnTime").value = dataOfRemainsReefer.returnTime;
                document.getElementById("bInternalCar").value = dataOfRemainsReefer.bInternalCar;
                document.getElementById("carNumber").value = dataOfRemainsReefer.carNumber;
                document.getElementById("carCost").value = dataOfRemainsReefer.carBeCost;
                document.getElementById("carRealCost").value = dataOfRemainsReefer.carRealCost;
                document.getElementById("carryTime").value = dataOfRemainsReefer.carryTime;    
                document.getElementById("carryNumber").value = dataOfRemainsReefer.carryNumber;          
             }               
                              
             document.getElementById("memberName").value = dataOfRemainsReefer.memberName;
             document.getElementById("memberMobile").value = dataOfRemainsReefer.memberMobile;
             document.getElementById("deadId").value = dataOfRemainsReefer.deadId;
             document.getElementById("reeferNo").value = dataOfRemainsReefer.reeferNo;
             document.getElementById("deadSource").value = dataOfRemainsReefer.deadSource;
             document.getElementById("startTime").value = dataOfRemainsReefer.startTime;
             document.getElementById("endTime").value = dataOfRemainsReefer.endTime;
             document.getElementById("staffName").value = dataOfRemainsReefer.staffName;
             document.getElementById("belongings").value = dataOfRemainsReefer.belongings;
             document.getElementById("memo").value = dataOfRemainsReefer.memo;
             document.getElementById("reeferBeCost").value = dataOfRemainsReefer.reeferBeCost;
             document.getElementById("reeferRealCost").value = dataOfRemainsReefer.reeferRealCost;             
             calculate();                                               
          }
          else
          {            
             document.getElementById("reeferInfo").style.display = 'none'; 
             document.getElementById("carryInfo").style.display = 'none';           
             alert("冷藏信息不存在！");
             
             document.getElementById("carryType").value = "";
             document.getElementById("address").value = "";
             document.getElementById("contactName").value = "";
             document.getElementById("contactMobile").value = "";
             document.getElementById("returnTime").value = "";
             document.getElementById("bInternalCar").value = "";
             document.getElementById("carNumber").value = "";
             document.getElementById("carCost").value = "";
             document.getElementById("carRealCost").value = "";
             document.getElementById("carryTime").value = "";     
             document.getElementById("carryNumber").value = "";
             
             document.getElementById("memberName").value = "";
             document.getElementById("memberMobile").value = "";
             document.getElementById("deadId").value = "";
             document.getElementById("reeferNo").value = "";
             document.getElementById("deadSource").value = "";
             document.getElementById("startTime").value = "";
             document.getElementById("endTime").value = "";
             document.getElementById("staffName").value = "";
             document.getElementById("belongings").value = "";
             document.getElementById("memo").value = "";
             document.getElementById("reeferDays").value = "";
             document.getElementById("reeferBeCost").value = "";
             document.getElementById("reeferRealCost").value = ""; 
             
          }
      }
      
       function calculate()
      {       
         var startTime = document.getElementById("startTime").value;
         var endTime = document.getElementById("endTime").value;
         var date1 = Date.parse(startTime.replace(/-/g, "/")); //begintime 为开始时间
         var date2 = Date.parse(endTime.replace(/-/g, "/"));   // endtime 为结束时间      
       
         var date3=date2-date1;  //时间差的毫秒数
         var days=Math.floor(date3/(24*3600*1000));
         var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
         var hours=Math.floor(leave1/(3600*1000));
         
         var leave2=leave1%(100*3600);
         var minutes = Math.floor(leave2/(1000*60));            
             
         if (hours > 0 || minutes > 0)
         {
               days = days + 1;
         }
         var money = days * 120;           
          
         document.getElementById("reeferDays").value = days;
         document.getElementById("reeferBeCost").value = money;         
         document.getElementById("reeferRealCost").value = money;         
                 
        // return money;       
      }
      
      function saveInformationOfReeferInfo()
      {
         var reeferNumber = document.getElementById("reeferNumber").value;
         var endTime = document.getElementById("endTime").value;
         var reeferBeCost = document.getElementById("reeferBeCost").value;
         var reeferRealCost = document.getElementById("reeferRealCost").value;
         var deadId = document.getElementById("deadId").value;
         var belongings = document.getElementById("belongings").value;
         var memo = document.getElementById("memo").value;
         var reeferNo = document.getElementById("reeferNo").value;
         var carryNumber = document.getElementById("carryNumber").value;
         
         
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
            alert("请输入结束时间！");
            return;
         }
           if (reeferRealCost == null || reeferRealCost == "")
          {
             alert("请输入冷藏实收费用！");
             return;
          }
          else
          {
            var reg = /^[1-9]\d*|0$/;
            if(!reg.test(reeferRealCost))
             {
                  alert("租棺实收费用输入有误，请重新输入!");
                  document.getElementById("reeferRealCost").value = reeferBeCost;
                  return;               
             }
             else if (Number(reeferRealCost) > Number(reeferBeCost))
             {
                  alert("租棺实收费用超出租棺应收费用，请重新输入!");
                  document.getElementById("reeferRealCost").value = reeferBeCost;
                  return;
             } 
          }
          
          
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          
         updateRemainsReefer(reeferNumber, endTime, reeferBeCost, reeferRealCost, deadId, belongings, memo, reeferNo, carryNumber);
      }
     </script>
  </head>
  
  <body>
    <input type="hidden" id="reeferNumber"/>
    <input type="hidden" id="savedeadId"/>
    <input type="hidden" id="carryNumber"/>
    <div id="search">
     <table>
       <tr>         
          <td>冷藏编号：</td>
          <td><input type="text" id="reeferNumberToSearch" name="reeferNumberToSearch"/></td>  
          <td>逝者身份证号：</td>
          <td><input type="text" id="deadIdToSearch" name="deadIdToSearch"/></td>     
          <td><input type="button" value="查询" onclick="getRemainsReefer()"></td>
       </tr>     
    </table>
    <br><br>
    </div>
    
    <div id="carryInfo" style="display:none;">
       <table>
           <tr>
              <td style="text-align:right">接运类型：</td>
              <td><input type="text" id="carryType" name="carryType" disabled="true"/></td>
              <td style="text-align:right">地点：</td>
              <td><input type="text" id="address" name="address" disabled="true"/></td>
           </tr>
           <tr>
              <td style="text-align:right">经办人姓名：</td>
              <td><input type="text" id="contactName" name="contactName" disabled="true"/></td>
              <td style="text-align:right">经办人手机：</td>
              <td><input type="text" id="contactMobile" name="contactMobile" disabled="true"/></td>
           </tr>
           <tr>
              <td style="text-align:right">接运时间：</td>
              <td><input type="text" id="carryTime" name="carryTime" disabled="true"/></td>
              <td style="text-align:right">到馆时间：</td>
              <td><input type="text" id="returnTime" name="returnTime" disabled="true"/></td>
           </tr>
           <tr>
              <td style="text-align:right">是否馆车辆：</td>
              <td><input type="text" id="bInternalCar" name="bInternalCar" disabled="true"/></td>
              <td style="text-align:right">车牌号：</td>
              <td><input type="text" id="carNumber" name="carNumber" disabled="true"/></td>             
           </tr>
           <tr>             
              <td style="text-align:right">接运用车应收费用：</td>
              <td><input type="text" id="carCost" name="carCost" disabled="true"/></td>
              <td style="text-align:right">接运用车实收费用：</td>
              <td><input type="text" id="carRealCost" name="carRealCost" disabled="true"/></td>
           </tr>
       </table>
       <br><br>
    </div>
    
    <div id="reeferInfo" style="display:none;">
        <table>
           <tr>
              <td style="text-align:right">逝者身份证号：</td>
              <td><input type="text" id="deadId" name="deadId"/></td>             
           </tr>
            <tr>
              <td style="text-align:right">经办人姓名：</td>
              <td><input type="text" id="memberName" name="contactName" readonly="readonly" disabled="true"/></td>
              <td style="text-align:right">经办人手机：</td>
              <td><input type="text" id="memberMobile" name="contactMobile" readonly="readonly" disabled="true"/></td>
           </tr>
           <tr>
              <td style="text-align:right">冰柜号：</td>
              <td><input type="text" id="reeferNo" name="reeferNo" readonly="readonly" disabled="true"/></td>
              <td style="text-align:right">尸体来源：</td>
              <td><input type="text" id="deadSource" name="deadSource" readonly="readonly" disabled="true"/></td>
           </tr>               
           <tr>             
              <td style="text-align:right">开始时间：</td>
              <td><input type="text" id="startTime" name="startTime" readonly="readonly" disabled="true"/></td>
              <td style="text-align:right">结束时间：</td>
              <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})" onchange="calculate()"/></td>
           </tr>
           <tr>
              <td style="text-align:right">馆内业务人员姓名：</td>
              <td><input type="text" id="staffName" name="staffName" readonly="readonly" disabled="true"/></td>
              <td style="text-align:right">逝者物品：</td>
              <td><input type="text" id="belongings" name="belongings"/></td>
           </tr>
            <tr>
              <td style="text-align:right">备注：</td>
              <td><input type="text" id="memo" name="memo"/></td>  
              <td style="text-align:right">冷藏天数：</td>
              <td><input type="text" id="reeferDays" name="reeferDays" readonly="readonly" disabled="true"/></td>              
           </tr>
           <tr>
              <td style="text-align:right">冷藏应收费用：</td>
              <td><input type="text" id="reeferBeCost" name="reeferBeCost" readonly="readonly" disabled="true"/></td>  
              <td style="text-align:right">冷藏实收费用：</td>
              <td><input type="text" id="reeferRealCost" name="reeferRealCost" /></td>              
           </tr>
       </table>
       <input type="button" id="save" value="保存" onclick="saveInformationOfReeferInfo()">
    </div>
  </body>
</html>
