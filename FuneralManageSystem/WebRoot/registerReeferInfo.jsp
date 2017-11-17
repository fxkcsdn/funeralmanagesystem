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
      var xmlhttp;
           
      function initialization()
      {                  
          getSystemTime();
          //getDeadType();
          //getVillaNumber();
          getReefer();         
          //getDeadReason();
      }
      
       function getSystemTime() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getSystemTime.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getSystemTimeBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getSystemTimeBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {
            
            var dataOfSystemTime = eval("("+ xmlhttp.responseText +")");
            dataOfSystemTime = eval("("+ dataOfSystemTime +")");
            showTimeOfSystem(dataOfSystemTime);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      } 
          
      function getReefer() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getReefer.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getReeferBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getReeferBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {            
            var dataOfReefer = eval("("+ xmlhttp.responseText +")");
            dataOfReefer = eval("("+ dataOfReefer +")");
            showNumberOfReefer(dataOfReefer);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function showNumberOfReefer(dataOfReefer)
      {             
         var dataLength = dataOfReefer.length;        
         var p = document.getElementById("reefer");        
         for (var i = 0; i < dataLength; ++i)
         {
              var image = document.createElement("img");
              image.setAttribute("id", dataOfReefer[i].reeferNumber);
              if (dataOfReefer[i].bAvailable == 1)
              {
                  image.setAttribute("src","Images/green.png");
              }
              else
              {
                  image.setAttribute("src","Images/red.png");
              }
              image.setAttribute("alt",dataOfReefer[i].bAvailable); 
              image.setAttribute("width","35px");
              image.setAttribute("height","20px");             
              image.setAttribute("onclick","chooseNumberOfReefer("+ dataOfReefer[i].reeferNumber +',' + dataOfReefer[i].bAvailable +")");
              p.appendChild(image); 
              p.innerHTML +="<strong>"+(Number(dataOfReefer[i].reeferNumber) > 9?dataOfReefer[i].reeferNumber:"0"+ dataOfReefer[i].reeferNumber) +"</strong>";
              p.innerHTML +="&nbsp;&nbsp;";
              if ((i+1) % 4 == 0)
              {
                 p.innerHTML += "<br>";
              }                                 
         }       
      }
      
       function chooseNumberOfReefer(reeferNumber, bAvailable)
      {
         if (bAvailable == 0)
         {
            alert("该冰柜不可用，请重新选择！");
         }
         else
         {
            document.getElementById("reeferNumber").value = reeferNumber;
         }
      }
      
      function showTimeOfSystem(dataOfSystemTime)
      {
          document.getElementById("startTime").value = dataOfSystemTime.startTime;
      }          
     
       
      function getRemainsCarry() 
      {
        xmlhttp = null;         
        var carryNumber = document.getElementById("carryNumberToSearch").value; 
        document.getElementById("carryNumber").value = carryNumber;  
        var coffinNumber = "";    
        var url="/FuneralManageSystem/getRemainsCarry.action?coffinNumber=" + coffinNumber + "&&carryNumber=" + carryNumber + "&&carryType=冷藏";
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
      
       function getReeferNumber() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getReeferNumber.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getReeferNumberBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getReeferNumberBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {           
            var dataOfReeferNumber = eval("("+ xmlhttp.responseText +")");
            dataOfReeferNumber = eval("("+ dataOfReeferNumber +")");       
            document.getElementById("reeferNo").value = dataOfReeferNumber.reeferNumber;
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function saveRemainsReefer(reeferNo, carryNumber, deadId, contactName, contactMobile, reeferNumber, startTime, deadSource, staffName, belongings, memo) {
        xmlhttp = null;         
        var url="/FuneralManageSystem/saveRemainsReefer.action?reeferNumber="+reeferNo+"&&carryNumber="+carryNumber+"&&deadId="+deadId+"&&contactName="+contactName+ "&&contactMobile="+ contactMobile + "&&reeferNo="+reeferNumber+"&&startTime="+startTime +"&&deadSource=" + deadSource + "&&staffName=" + staffName + "&&belongings=" + belongings + "&&memo=" + memo;  
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = saveRemainsReeferBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function saveRemainsReeferBack() {
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
         //document.getElementById("btnPrint").style.display = 'block';
         document.getElementById("deadId").setAttribute("disabled","true");
         document.getElementById("memberName").setAttribute("disabled","true"); 
         document.getElementById("memberMobile").setAttribute("disabled","true");           
         document.getElementById("reeferNumber").setAttribute("disabled","true");  
         document.getElementById("startTime").setAttribute("disabled","true");   
         document.getElementById("endTime").setAttribute("disabled","true");
         document.getElementById("deadSource").setAttribute("disabled","true");
         document.getElementById("staffName").setAttribute("disabled","true");
         document.getElementById("belongings").setAttribute("disabled","true");
         document.getElementById("memo").setAttribute("disabled","true");
         document.getElementById("reefer").style.display = 'none';
         document.getElementById("way").style.display = 'none';
       }
       
      
      function showRemainsCarry(dataOfRemainsCarry)
      {
          if (dataOfRemainsCarry != null)
          {
             document.getElementById("carryInfo").style.display = 'block';         
             document.getElementById("carryNumber").value = document.getElementById("carryNumberToSearch").value;                   
             document.getElementById("carryTime").value = dataOfRemainsCarry.carryTime;             
             document.getElementById("carryType").value = dataOfRemainsCarry.carryType;       
             document.getElementById("address").value = dataOfRemainsCarry.address;
             document.getElementById("contactMobile").value = dataOfRemainsCarry.contactMobile;
             document.getElementById("contactName").value = dataOfRemainsCarry.contactName;  
             document.getElementById("carNumber").value = dataOfRemainsCarry.carNumber;
             document.getElementById("carCost").value = dataOfRemainsCarry.carCarryBeCost;
             document.getElementById("carRealCost").value = dataOfRemainsCarry.carCarryRealCost;
             document.getElementById("bInternalCar").value = (dataOfRemainsCarry.bInternalCar == "0")?"否":"是";
             document.getElementById("refrigerationInfo").style.display = 'block'; 
             document.getElementById("reefer").innerHTML = "";                 
             initialization();          
          }
          else
          {            
             document.getElementById("carryInfo").style.display = 'none';    
             document.getElementById("refrigerationInfo").style.display = 'none';            
             alert("接运信息不存在！");            
             document.getElementById("carryNumberToSearch").value = "";
             document.getElementById("carryNumber").value = "";                  
             document.getElementById("carryTime").value = "";          
             document.getElementById("carryType").value = "";         
             document.getElementById("address").value = "";
             document.getElementById("contactMobile").value = "";
             document.getElementById("contactName").value = "";             
             document.getElementById("carNumber").value = "";
             document.getElementById("carCost").value = "";
             document.getElementById("carRealCost").value = "";
             document.getElementById("bInternalCar").value = "";
          }
      }
      
      
      function choose(index)
      {
          //var choose = document.getElementsByName("carry");
          if (index == 0)
          {
             document.getElementById("search").style.display = 'block'; 
             document.getElementById("refrigerationInfo").style.display = 'none';            
          }
          else
          {
             document.getElementById("search").style.display = 'none';
             document.getElementById("carryInfo").style.display = 'none'; 
             document.getElementById("reefer").innerHTML = "";              
             initialization();    
             document.getElementById("refrigerationInfo").style.display = 'block';       
             //initialization();         
          }
      }   
    
      
      function saveInformationOfRefrigerationInfo()
      {
          var carryNumber = document.getElementById("carryNumber").value;
          var deadId = document.getElementById("deadId").value;
          var contactName = document.getElementById("memberName").value;
          var contactMobile = document.getElementById("memberMobile").value;        
          var reeferNumber = document.getElementById("reeferNumber").value;
          var deadSource = document.getElementById("deadSource").value;
          var staffName = document.getElementById("staffName").value;
          var belongings = document.getElementById("belongings").value;
          var memo = document.getElementById("memo").value;
        
          var startTime = document.getElementById("startTime").value;
          
          if (!(deadId == null || deadId == ""))
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
          if (contactName == null || contactName == "")
          {
             alert("请输入经办人姓名！");
             return;
          }
           if (contactMobile == null || contactMobile == "")
          {
            alert("请输入经办人手机！");
            return;
          }
         else
          {
             var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
             if(!reg.test(contactMobile))
             {
                alert("经办人手机输入有误，请重新输入!");
                document.getElementById("contactMobile").value = "";
                return;
             }
          }         
          if (reeferNumber == null || reeferNumber == "")
          {
             alert("请输入冰柜号！");
             return;
          }
          if (deadSource == null || deadSource == "")
          {
             alert("请输入尸体来源！");
             return;
          }
        
           if (startTime == null || startTime == "")
          {
             alert("请输入开始时间！");
             return;
          }
          if (staffName == null || staffName == "")
          {
             alert("请输入馆内业务人员姓名！");
             return;
          }
          
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          
          getReeferNumber();
          var reeferNo = document.getElementById("reeferNo").value;
          
          saveRemainsReefer(reeferNo, carryNumber, deadId, contactName, contactMobile, reeferNumber, startTime, deadSource, staffName, belongings, memo);
      }
    </script>
  </head>
  
  <body>
    <input type="hidden" id="carryNumber" value=""/>
    <input type="hidden" id="reeferNo" value=""/>
    
    <div id="way"> 
              接运方式：<br/><br/>
    <label><input name="carry" type="radio" value="" checked="checked" onclick="choose(0)" />本馆接运 </label> 
    <label><input name="carry" type="radio" value="" onclick="choose(1)"/>第三方接运 </label> 
    <br><br>
    </div>
    
    <div id="search">
       <table>
           <tr>
              <td style="text-align:right">接运编号：</td>
              <td><input type="text" id="carryNumberToSearch" name="carryNumberToSearch"/></td>
              <td><input type="button" value="查询" onclick="getRemainsCarry()"/></td>
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
              <td style="text-align:right">是否馆车辆：</td>
              <td><input type="text" id="bInternalCar" name="bInternalCar" disabled="true"/></td>
           </tr>
           <tr>
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
    
    <div id="refrigerationInfo" style="display:none;">
       <table>
           <tr>
              <td style="text-align:right">逝者身份证号：</td>
              <td><input type="text" id="deadId" name="deadId"/></td>             
           </tr>
            <tr>
              <td style="text-align:right">经办人姓名：</td>
              <td><input type="text" id="memberName" name="contactName"/></td>
              <td style="text-align:right">经办人手机：</td>
              <td><input type="text" id="memberMobile" name="contactMobile"/></td>
           </tr>
           <tr>
              <td style="text-align:right">冰柜号：</td>
              <td><input type="text" id="reeferNumber" name="reeferNumber" readonly="readonly" disabled="true"/></td>
              <td style="text-align:right">尸体来源：</td>
              <td><input type="text" id="deadSource" name="deadSource"/></td>
           </tr>
           <tr>      
              <td></td>                   
              <td id="reefer" colspan="3"></td>              
           </tr>         
           <tr>             
              <td style="text-align:right">开始时间：</td>
              <td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})"/></td>
              <td style="text-align:right">结束时间：</td>
              <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})" disabled="true"/></td>
           </tr>
           <tr>
              <td style="text-align:right">馆内业务人员姓名：</td>
              <td><input type="text" id="staffName" name="staffName"/></td>
              <td style="text-align:right">逝者物品：</td>
              <td><input type="text" id="belongings" name="belongings"/></td>
           </tr>
            <tr>
              <td style="text-align:right">备注：</td>
              <td><input type="text" id="memo" name="memo"/></td>              
           </tr>
       </table>
        <input type="submit" id="save" value="保存" onclick="saveInformationOfRefrigerationInfo()">  
    </div> 
  </body>
</html>