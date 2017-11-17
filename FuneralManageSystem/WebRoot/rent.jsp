<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'rent.jsp' starting page</title>  
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script language="javascript" type="text/javascript"  src="js/json2.js"></script>
    <script src="js/AjaxJson.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/Setting.js" type="text/javascript" charset="utf-8"></script>
	<script language="javascript" type="text/javascript"  src="js/My97DatePicker/WdatePicker.js"></script>

	
	<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>    
  </head>  
  <META content="MSHTML 6.00.2900.6287" name=GENERATOR>
  <body>   
  <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
    <input type="hidden" id="rentNumberOfCoffin"/>
    <input type="hidden" id="saveContactName"/>
    <input type="hidden" id="saveContactPhone"/>
    <input type="hidden" id="saveNumberOfCoffin"/>
    <input type="hidden" id="saveAddress"/>
    <input type="hidden" id="saveStartTime"/>
    <input type="hidden" id="saveCarNumber"/>
    <input type="hidden" id="saveBInternalCar"/>
    <input type="hidden" id="saveCarCost"/>
    <input type="hidden" id="saveCarRealCost"/>
    <input type="hidden" id="saveDriverName"/>
    <input type="hidden" id="saveDriverPhone"/>
     
  
      <table id="tab">
    <tr>
        <td style="text-align:right">联系人姓名：</td>
        <td><select id="contactName" name="contactName" onchange="linkContactPhone()"></select></td>
        <td style="text-align:right">联系人手机：</td>
        <td><input type="text" id="contactPhone" name="contactPhone" disabled="disabled" readonly="readonly"/></td>
    </tr>
    <tr>
        <td style="text-align:right">水晶棺编号：</td>
        <td colspan="3"><input type="text" id="numberOfCoffin" name="numberOfCoffin" readonly="readonly" disabled="true"/></td>
    </tr>
     <tr>
        <td></td>
        <td id="coffin" colspan="3"></td>
    </tr>  
    <tr>
        <td style="text-align:right">送棺地址：</td>
        <td><input type="text" id="address" name="address"/></td>
        <td style="text-align:right"> 送棺时间：</td>
        <td><input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true})"/></td>
    </tr>
    <tr>
          <td style="text-align:right">本馆车辆：</td>
          <td><select id="bInternalCar">
								<option selected="selected">请选择</option>
								<option>1</option>
								<option>2</option>
	</select></td>
          <td style="text-align:right">送棺车号：</td>
          <td><select id="carNumber" name="carNumber" onchange="driverDetailInfo()"></select></td>  
    </tr>
    <tr>
        <td style="text-align:right">司机姓名：</td>
        <td><input type="text" id="driverName" name="driverName" readonly="readonly" disabled="true"/></td>
        <td style="text-align:right">司机手机：</td>
        <td><input type="text" id="driverPhone" name="driverPhone" readonly="readonly" disabled="true"/></td>
    </tr>
    <tr>        
        <td style="text-align:right">用车应收：</td>
        <td><input type="text" id="carCost" name="carCost" value="100" disabled="disabled" readonly="readonly"/></td>
        <td style="text-align:right">用车实收：</td>
        <td><input type="text" id="carRealCost" name="carRealCost" value="100" disabled="disabled" readonly="readonly"/></td>    
    </tr>   
    </table> 
    <input type="submit" id="save" value="保存" onclick="saveInformationOfCoffin()">      
    <input type="submit" id="btnPrint" value="打印" onclick="printRentBill()" disabled="true"/>          
    <script src="javascript/rent.js" type="text/javascript" charset="utf-8"></script>
  </body>
</html>
