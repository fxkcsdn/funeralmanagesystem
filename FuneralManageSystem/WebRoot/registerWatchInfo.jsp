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
          getVillaNumber();
          getCoffin();         
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
      
      function getDeadType() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getDeadType.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getDeadTypeBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getDeadTypeBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {                    
            var dataOfDeadType = eval("("+ xmlhttp.responseText +")");
            dataOfDeadType = eval("("+ dataOfDeadType +")");
            showDeadType(dataOfDeadType);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
       function getDeadReason() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getDeadReason.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getDeadReasonBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getDeadReasonBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {              
            var dataOfDeadReason = eval("("+ xmlhttp.responseText +")");
            dataOfDeadReason = eval("("+ dataOfDeadReason +")");
            showDeadReason(dataOfDeadReason);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function getVillaNumber() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getVillaNumber.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getVillaNumberBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getVillaNumberBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {              
            var dataOfVillaNumber = eval("("+ xmlhttp.responseText +")");
            dataOfVillaNumber = eval("("+ dataOfVillaNumber +")");
            showVillaNumber(dataOfVillaNumber);
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
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
      
       function chooseNumberOfCoffin(coffinNumber, bAvailable)
      {
         if (bAvailable == 0)
         {
            alert("该水晶棺不可用，请重新选择！");
         }
         else
         {
            document.getElementById("coffinNumber").value = coffinNumber;
         }
      }
      
      function showTimeOfSystem(dataOfSystemTime)
      {
          document.getElementById("startTime").value = dataOfSystemTime.startTime;
      }
       
      function showDeadType(dataOfDeadType)
      {          
          var dataLength = dataOfDeadType.length;          
          var sel = document.getElementById("deadType");        
          sel.options.add(new Option("",""));
          for (var i = 0; i < dataLength; ++i)
          {             
              sel.options.add(new Option(dataOfDeadType[i].typeName,dataOfDeadType[i].typeName));
          }
      } 
      
      function showDeadReason(dataOfDeadReason)
      {          
          var dataLength = dataOfDeadReason.length;          
          var sel = document.getElementById("deadReason");        
          sel.options.add(new Option("",""));
          for (var i = 0; i < dataLength; ++i)
          {             
              sel.options.add(new Option(dataOfDeadReason[i].reasonName,dataOfDeadReason[i].reasonName));
          }
      }
      
      function showVillaNumber(dataOfVillaNumber)
      {          
          var dataLength = dataOfVillaNumber.length;          
          var sel = document.getElementById("villaNumber");        
          sel.options.add(new Option("",""));
          for (var i = 0; i < dataLength; ++i)
          {             
              sel.options.add(new Option(dataOfVillaNumber[i].villaNumber,dataOfVillaNumber[i].villaNumber));
          }
      }
       
      function getRemainsCarry() 
      {
        xmlhttp = null;         
        var carryNumber = document.getElementById("carryNumberToSearch").value; 
        document.getElementById("carryNumber").value = carryNumber;  
        var coffinNumber = "";    
        var url="/FuneralManageSystem/getRemainsCarry.action?coffinNumber=" + coffinNumber + "&&carryNumber=" + carryNumber + "&&carryType=守灵";
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
      
       function getWatchNumber() {
        xmlhttp = null;         
        var url="/FuneralManageSystem/getWatchNumber.action";
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = getWatchNumberBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function getWatchNumberBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {           
            var dataOfWatchNumber = eval("("+ xmlhttp.responseText +")");
            dataOfWatchNumber = eval("("+ dataOfWatchNumber +")");       
            document.getElementById("watchNumber").value = dataOfWatchNumber.watchNumber;
          }
          else
          {
            alert(xmlhttp.status);
            alert("Problem retrieving XML data");
          }
        }
      }
      
      function saveWatchSpirit(watchNumber, carryNumber, deadId, memberName, memberMobile, villaNumber, coffinNumber, startTime) {
        xmlhttp = null;         
        var url="/FuneralManageSystem/saveWatchSpirit.action?watchNumber="+watchNumber+"&&carryNumber="+carryNumber+"&&deadId="+deadId+"&&memberName="+memberName+ "&&memberMobile="+ memberMobile+"&&villaNumber="+villaNumber+"&&coffinNumber="+coffinNumber+"&&startTime="+startTime;  
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = saveWatchSpiritBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function saveWatchSpiritBack() {
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
         //document.getElementById("btnPrint").style.display = 'block';
         document.getElementById("deadId").setAttribute("disabled","true");
         document.getElementById("memberName").setAttribute("disabled","true"); 
         document.getElementById("memberMobile").setAttribute("disabled","true");
         document.getElementById("villaNumber").setAttribute("disabled","true");  
         document.getElementById("coffinNumber").setAttribute("disabled","true");  
         document.getElementById("startTime").setAttribute("disabled","true");   
         document.getElementById("endTime").setAttribute("disabled","true");
         document.getElementById("coffin").style.display = 'none';
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
             document.getElementById("wakeInfo").style.display = 'block'; 
             document.getElementById("coffin").innerHTML = "";  
             document.getElementById("villaNumber").innerHTML = "";  
             initialization();          
          }
          else
          {            
             document.getElementById("carryInfo").style.display = 'none';    
             document.getElementById("wakeInfo").style.display = 'none';            
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
             document.getElementById("wakeInfo").style.display = 'none';            
          }
          else
          {
             document.getElementById("search").style.display = 'none';
             document.getElementById("carryInfo").style.display = 'none'; 
             document.getElementById("coffin").innerHTML = "";  
             document.getElementById("villaNumber").innerHTML = "";
             initialization();    
             document.getElementById("wakeInfo").style.display = 'block';       
             //initialization();         
          }
      }   
    
      
      function saveInformationOfWakeInfo()
      {
          var carryNumber = document.getElementById("carryNumber").value;
          var deadId = document.getElementById("deadId").value;
          var memberName = document.getElementById("memberName").value;
          var memberMobile = document.getElementById("memberMobile").value;
          var villaNumber = document.getElementById("villaNumber").value;
          var coffinNumber = document.getElementById("coffinNumber").value;
          /*
          var deadType = document.getElementById("deadType").value;
          var deadReason = document.getElementById("deadReason").value;
          var deadTime = document.getElementById("deadTime").value;
          */
          var startTime = document.getElementById("startTime").value;
          
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
          if (memberName == null || memberName == "")
          {
             alert("请输入家属姓名！");
             return;
          }
           if (memberMobile == null || memberMobile == "")
          {
            alert("请输入家属手机！");
            return;
          }
         else
          {
             var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
             if(!reg.test(memberMobile))
             {
                alert("家属手机输入有误，请重新输入!");
                document.getElementById("memberMobile").value = "";
                return;
             }
          }
          if (villaNumber == null || villaNumber == "")
          {
             alert("请输入别墅号！");
             return;
          }
          if (coffinNumber == null || coffinNumber == "")
          {
             alert("请输入水晶棺号！");
             return;
          }
          /*
          if (deadType == null || deadType == "")
          {
             alert("请输入死亡类型！");
             return;
          }
          if (deadReason == null || deadReason == "")
          {
             alert("请输入死亡原因！");
             return;
          }
          if (deadTime == null || deadTime == "")
          {
             alert("请输入死亡时间！");
             return;
          }
          */
           if (startTime == null || startTime == "")
          {
             alert("请输入开始时间！");
             return;
          }
          
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          
          getWatchNumber();
          var watchNumber = document.getElementById("watchNumber").value;
          
          saveWatchSpirit(watchNumber, carryNumber, deadId, memberName, memberMobile, villaNumber, coffinNumber, startTime);
      }
    </script>
  </head>
  
  <body>
    <input type="hidden" id="carryNumber" value=""/>
    <input type="hidden" id="watchNumber" value=""/>
    
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
    
    <div id="wakeInfo" style="display:none;">
       <table>
           <tr>
              <td style="text-align:right">逝者身份证号：</td>
              <td><input type="text" id="deadId" name="deadId"/></td>             
           </tr>
            <tr>
              <td style="text-align:right">家属姓名：</td>
              <td><input type="text" id="memberName" name="memberName"/></td>
              <td style="text-align:right">家属手机：</td>
              <td><input type="text" id="memberMobile" name="memberMobile"/></td>
           </tr>
           <tr>
              <td style="text-align:right">别墅号：</td>
              <td><select id="villaNumber" name="villaNumber" ></select></td>
              <td style="text-align:right">水晶棺号：</td>
              <td><input type="text" id="coffinNumber" name="coffinNumber" readonly="readonly" disabled="true"/></td>
           </tr>
           <tr>
              <td></td>
              <td></td>
              <td id="coffin" colspan="2"></td>
           </tr>
           <!--  
           <tr>
              <td style="text-align:right">死亡类型：</td>
              <td><select id="deadType" name="deadType" onchange="deadTypeInfo()"></select></td>
              <td style="text-align:right">死亡原因：</td>
              <td><select id="deadReason" name="deadReason" onchange="deadReasonInfo()"></select></td>
           </tr>
           -->
           <tr>
              <!--  
              <td style="text-align:right">死亡时间：</td>
              <td><input type="text" id="deadTime" name="deadTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,maxDate:'%y-%M-{%d}'})"/></td>
              -->
              <td style="text-align:right">开始时间：</td>
              <td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})"/></td>
              <td style="text-align:right">结束时间：</td>
              <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})" disabled="true"/></td>
           </tr>
       </table>
        <input type="submit" id="save" value="保存" onclick="saveInformationOfWakeInfo()">  
    </div> 
  </body>
</html>
