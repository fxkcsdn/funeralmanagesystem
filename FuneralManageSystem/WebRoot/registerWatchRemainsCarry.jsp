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
      //连接服务器请求数据
      
       window.onload = initialization;
       function initialization()
       {                      
           getSystemTime();
           getCarNumber();
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
            var dataOfCarNumber = eval("("+ xmlhttp.responseText +")");
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
      
      
      function getCarryNumber() {
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
      function getCarryNumberBack() {
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
      
      function addRemainsCarry(carryNumber, rentNumber, deadId, carryTime, carryType, address, contactMobile, contactName, carNumber, carCost, bInternalCar, carRealCost) {
        xmlhttp = null;             
        var url="/FuneralManageSystem/addRemainsCarry.action?contactName="+contactName+"&&contactMobile="+contactMobile+"&&address="+address+"&&carryNumber=" + carryNumber + "&&rentNumber=" + rentNumber + "&&deadId=" + deadId + "&&startTime=" + carryTime + "&&carryType=" + carryType + "&&carNumber=" + carNumber + "&&carCost=" + carCost + "&&bInternalCar=" + bInternalCar + "&&carRealCost="+carRealCost;
        if (window.XMLHttpRequest)
        {// code for all new browsers
          xmlhttp = new XMLHttpRequest();
        }
        else if (window.ActiveXObject)
        {// code for IE5 and IE6
          xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
          xmlhttp.onreadystatechange = addRemainsCarryBack;
          xmlhttp.open("POST", url, false);
          xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
          xmlhttp.send(null);
        }
      }

      //回调函数
      function addRemainsCarryBack() {
        if (xmlhttp.readyState == 4)
        {
          if (xmlhttp.status == 200)
          {
            var dataOfCoffin = eval("("+ xmlhttp.responseText +")");
            dataOfCoffin = eval("("+ dataOfCoffin +")");       
            if (dataOfCoffin.result == "yes")
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
         document.getElementById("btnPrint").style.display = 'block';
         document.getElementById("contactName").setAttribute("disabled","true");
         document.getElementById("contactMobile").setAttribute("disabled","true"); 
         document.getElementById("address").setAttribute("disabled","true");
         document.getElementById("carryTime").setAttribute("disabled","true");   
         document.getElementById("carryType").setAttribute("disabled","true");   
         document.getElementById("carNumber").setAttribute("disabled","true");
         document.getElementById("driverName").setAttribute("disabled","true");
         document.getElementById("driverPhone").setAttribute("disabled","true");
         document.getElementById("bInternalCar").setAttribute("disabled","true");
         document.getElementById("carCost").setAttribute("disabled","true");
         document.getElementById("carRealCost").setAttribute("disabled","true");
       }
      
       function showTimeOfSystem(dataOfSystemTime)
       {
          document.getElementById("carryTime").value = dataOfSystemTime.startTime;
       }
      
      function showCarNumber(dataOfCarNumber)
      {
          var dataLength = dataOfCarNumber.length;
          var sel = document.getElementById("carNumber");
          var js;
          sel.options.add(new Option("",""));
          for (var i = 0; i < dataLength; ++i)
          {
              js = "";
              js = "{carNumber:\""+ dataOfCarNumber[i].carNumber + "\",driverName:\"" + dataOfCarNumber[i].driverName +"\",driverMoblie:\"" + dataOfCarNumber[i].driverMobile + "\"}";                        
              sel.options.add(new Option(dataOfCarNumber[i].carNumber,js));
          }
      }               
      
      function driverDetailInfo()
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
      
      function saveInformationOfRemainsCarry()
      {
          var carryType = document.getElementById("carryType").value;
          var carryTime = document.getElementById("carryTime").value;
          var address = document.getElementById("address").value;
          var contactMobile = document.getElementById("contactMobile").value;
          var contactName = document.getElementById("contactName").value;
               
          var carNumber = document.getElementById("carNumber").value;
          var carCost = document.getElementById("carCost").value;
          var carRealCost = document.getElementById("carRealCost").value;
          var bInternalCar = document.getElementById("bInternalCar").value;  
          var rentNumber = "";
          var deadId = "";        
         
          if (carryTime == null || carryTime == "")
          {
             alert("请输入接运时间！");
             return;
          }
          if (address == null || address == "")
          {
             alert("请输入地址！");
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
          if (contactName == null || contactName == "")
          {
             alert("请输入经办人姓名！");
             return;
          } 
          
          if (carCost == null || carCost == "")
         {
            alert("请输入用车应收费用！");
            return;
         }
         else
         {
            var reg = /^[1-9]\d*$/;
            if(!reg.test(carCost))
            {
                alert("用车应收费用输入有误，请重新输入!");
                document.getElementById("carCost").value = "";
                return;
            }
         }
         if (carRealCost == null || carRealCost == "")
         {
            alert("请输入用车实收费用！");
            return;
         }
         else
         {
            var reg = /^[1-9]\d*$/;
            if(!reg.test(carRealCost))
            {
                alert("用车实收费用输入有误，请重新输入!");
                document.getElementById("carRealCost").value = carCost;
                return;
            }
            else if (Number(carRealCost) > Number(carCost))
            {
                alert("用车实收费用超出用车应收费用，请重新输入!");
                document.getElementById("carRealCost").value = carCost;
                return;
            }
         }
          
          var r=confirm("是否确认保存?");
          if (r == false)
          {
              return;
          }
          
          getCarryNumber();
          var carryNumber = document.getElementById("carryNumber").value;
          
          
         document.getElementById("saveContactName").value = document.getElementById("contactName").value;        
         document.getElementById("saveContactMobile").value = document.getElementById("contactMobile").value;        
         document.getElementById("saveAddress").value = document.getElementById("address").value;        
         document.getElementById("saveCarryTime").value = document.getElementById("carryTime").value;        
         document.getElementById("saveCarryType").value = document.getElementById("carryType").selectedIndex;       
         document.getElementById("saveCarCost").value = document.getElementById("carCost").value; 
         document.getElementById("saveCarRealCost").value = document.getElementById("carRealCost").value;              
         document.getElementById("saveDriverName").value = document.getElementById("driverName").value;        
         document.getElementById("saveDriverMobile").value = document.getElementById("driverPhone").value;       
         document.getElementById("saveBInternalCar").value = document.getElementById("bInternalCar").selectedIndex;       
       
         var info = document.getElementById("carNumber").value;     
         if (info != "")
         {
            info = JSON.stringify(info);          
            info = eval("("+ eval("("+ info +")") +")");                
            carNumber = info.carNumber;                          
         }
         else
         {
            carNumber = "";        
         } 
         var saveCarNumber = document.getElementById("carNumber");   
         document.getElementById("saveCarNumber").value = saveCarNumber.selectedIndex; 
       
         addRemainsCarry(carryNumber, rentNumber, deadId, carryTime, carryType, address, contactMobile, contactName, carNumber, carCost, bInternalCar, carRealCost);
      }
      
      
       function preview(oper)
      {  
         document.getElementById("numberOfremainsCarry").innerText = document.getElementById("carryNumber").value;
         document.getElementById("driverNameList").innerText = document.getElementById("driverName").value;
         document.getElementById("driverPhoneList").innerText = document.getElementById("driverPhone").value;
         document.getElementById("carCostList").innerText = document.getElementById("carCost").value;           
         document.getElementById("carryTimeList").innerText = document.getElementById("carryTime").value;   
         document.getElementById("carryTypeList").innerText = document.getElementById("carryType").value;   
         
         var info = document.getElementById("carNumber").value;     
         if (info != "")
         {
            info = JSON.stringify(info);          
            info = eval("("+ eval("("+ info +")") +")");                
           document.getElementById("carNumberList").innerText = info.carNumber;           
         }
         else
         {
            document.getElementById("carNumberList").innerText = "";        
         }                  
        
         document.getElementById("addressList").innerText = document.getElementById("address").value;
         document.getElementById("contactNameList").innerText = document.getElementById("contactName").value;
         document.getElementById("contactPhoneList").innerText = document.getElementById("contactMobile").value;
         if (oper < 10)
         {  
            bdhtml=window.document.body.innerHTML;//获取当前页的html代码 
            sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域  
            eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域  
            prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html  
  
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html  
            window.document.body.innerHTML=prnhtml;  
            window.print();  
            window.document.body.innerHTML=bdhtml;  
         } 
         else 
         {  
            window.print(); 
         }
         
         document.getElementById("contactName").value = document.getElementById("saveContactName").value;
         document.getElementById("contactMobile").value = document.getElementById("saveContactMobile").value;
         document.getElementById("address").value = document.getElementById("saveAddress").value;
         document.getElementById("carryTime").value = document.getElementById("saveCarryTime").value;
         document.getElementById("carryType").selectedIndex = document.getElementById("saveCarryType").value;
         document.getElementById("carNumber").selectedIndex = document.getElementById("saveCarNumber").value; 
         document.getElementById("driverName").value = document.getElementById("saveDriverName").value;
         document.getElementById("driverPhone").value = document.getElementById("saveDriverMobile").value;
         document.getElementById("carCost").value = document.getElementById("saveCarCost").value;
         document.getElementById("carRealCost").value = document.getElementById("saveCarRealCost").value;
         document.getElementById("bInternalCar").selectedIndex = document.getElementById("saveBInternalCar").value;
      }
      
      function updateCarRealCost()
      {
          document.getElementById("carRealCost").value = document.getElementById("carCost").value;
      }
    </script>
  </head>
  
  <body>
    <input type="hidden" id="carryNumber"/> 
    <input type="hidden" id="saveCarryType"/>
    <input type="hidden" id="saveAddress"/>
    <input type="hidden" id="saveContactName"/>
    <input type="hidden" id="saveContactMobile"/>
    <input type="hidden" id="saveCarryTime"/>
    <input type="hidden" id="saveCarNumber"/>
    <input type="hidden" id="saveCarCost"/>
    <input type="hidden" id="saveCarRealCost"/>
    <input type="hidden" id="saveDriverName"/>
    <input type="hidden" id="saveDriverMobile"/>
    <input type="hidden" id="saveBInternalCar"/>
    
    <table>
      <tr>
         <td style="text-align:right">接运类型：</td>
         <td><select id="carryType" name="carryType" disabled="true"><option value="火化">火化</option><option value="守灵" selected="true">守灵</option><option value="冷藏">冷藏</option></select></td>
         <td style="text-align:right">地点：</td>
         <td><input type="text" id="address" name="address"/></td>
      </tr>
      <tr>        
         <td style="text-align:right">经办人姓名：</td>
         <td><input type="text" id="contactName" name="contactName"/></td>
         <td style="text-align:right">经办人手机：</td>
         <td><input type="text" id="contactMobile" name="contactMobile"/></td>
      </tr>
      <tr>
         <td style="text-align:right">接运时间：</td>
         <td><input type="text" id="carryTime" name="carryTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})"/></td>
                   
      </tr>
      <tr>
         <td style="text-align:right">是否本馆车辆：</td>
         <td><select id="bInternalCar" name="bInternalCar" ><option value="1">是</option><option value="0">否</option></select></td>
         <td style="text-align:right">接运车牌号：</td>
         <td><select id="carNumber" name="carNumber" onchange="driverDetailInfo()"></select></td>       
      </tr>
      <tr>        
        <td style="text-align:right">司机姓名：</td>
        <td><input type="text" id="driverName" name="driverName" readonly="readonly" disabled="true"/></td>
        <td style="text-align:right">司机手机：</td>
        <td><input type="text" id="driverPhone" name="driverPhone" readonly="readonly" disabled="true"/></td> 
      </tr>
       <tr>         
         <td style="text-align:right">接运用车应收费用：</td>
         <td><input type="text" id="carCost" name="carCost" onchange="updateCarRealCost()" onkeyup="updateCarRealCost()"/></td>  
         <td style="text-align:right">接运用车实收费用：</td>
         <td><input type="text" id="carRealCost" name="carRealCost"/></td>         
      </tr>
    </table>
     <input type="submit" id="save" value="保存" onclick="saveInformationOfRemainsCarry()">  
     <input type="submit" id="btnPrint" style="display: none;" value="打印" onclick=preview(1) /> 
      <div id="remainsCarryList" style="display:none"> 
    <!--startprint1-->
    <div style="text-align:center">
      <table>
         <tr>
            <td colspan="6" style="text-align:center"><font size="4" face="黑体" ><B>东台市永泰园接运单</B></font></td>
         </tr>
          <tr>
            <td colspan="3" style="text-align:center"><font size="3" face="宋体" >接运编号：</font></td>
            <td colspan="3" id="numberOfremainsCarry" style="text-align:left"><font size="3" face="宋体" ></font></td>
         </tr>
         <tr>
            <td><font size="3" face="宋体" >司机姓名：</font></td>
            <td id="driverNameList"><font size="3" face="宋体" ></font></td>
            <td><font size="3" face="宋体">司机手机：</font></td>
            <td id="driverPhoneList"><font size="3" face="宋体" ></font></td>
            <td><font size="3" face="宋体" >车牌号：</font></td>
            <td id="carNumberList"><font size="3" face="宋体" ></font></td>
         </tr>
      </table>      
      <table border="1" style="text-align:center">         
         <tr>
             <td><font size="3" face="宋体" >接运时间：</font></td>
             <td id="carryTimeList"></td>
         </tr>
         <tr>
             <td><font size="3" face="宋体" >接运类型：</font></td>
             <td id="carryTypeList"></td>
         </tr>
         <tr>
             <td><font size="3" face="宋体" >接运地址：</font></td>
             <td id="addressList"></td>
         </tr>
         <tr>
             <td><font size="3" face="宋体" >接运费用：</font></td>
             <td id="carCostList"></td>
         </tr>
         <tr>
             <td><font size="3" face="宋体" >经办人姓名：</font></td>
             <td id="contactNameList"></td>
         </tr>
         <tr>
             <td><font size="3" face="宋体" >经办人电话：</font></td>
             <td id="contactPhoneList"></td>
         </tr>
      </table>
    </div>
     <!--endprint1-->
     </div>
  </body>
</html>
