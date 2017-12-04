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
    <script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/deadReefer/registerReeferInfo.js"></script>
    <style type="text/css">
      body,td,th {
	    font-size: 20px;
      }
    </style>
  </head>
  
  <body>
    <object id="LODOP" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
      <embed id="LODOP_EM" type="application/x-print-lodop" witdh=0 heigth=0></embed>
    </object>
    <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
    <form id="form1" name="form1" method="post" action="" onsubmit="return saveInformationOfRefrigerationInfo();">
       <table>
        	<tr>
              <td id="reefer" colspan="4"></td>
           </tr>
           <tr>
              <td>冰柜号：</td>
              <td><input type="text" id="reeferNo" name="reeferNo" readonly="readonly" disabled="true" style="width:80%;"/></td>           
           </tr>
           <tr>
              <td colspan="2">接运编号（<font color="#dd0000">本馆接运需填写</font>）：</td>
              <td><input type="text" id="carryNumber" name="carryNumber" style="width:90%;"/></td>
           </tr>
           <tr>
              <td colspan="2">逝者身份证号（<font color="#dd0000">可不填</font>）：</td>
              <td colspan="2"><input type="text" id="deadId" name="deadId" style="width:90%;"/></td>
              <td><input type="button" id="getDeadId" name="getDeadId" value="读取身份证号码" onclick="readIDCard();"/></td>
           </tr>
           <tr>
              <td>逝者姓名：</td>
              <td><input type="text" id="deadName" name="deadName" style="width:80%;"/></td>
              <td>逝者性别：</td>
              <td>
                 <select id="deadGender" name="deadGender" style="width:80%;">
                   <option value="请选择">请选择</option>
                   <option value="男">男</option>
                   <option value="女">女</option>
                 </select>
              </td>
           </tr>
           <tr>
              <td>经办人姓名：</td>
              <td><input type="text" id="contactName" name="contactName" required="required" style="width:80%;"/></td>
              <td>经办人手机：</td>
              <td><input type="text" id="contactMobile" name="contactMobile" required="required" style="width:80%;"/></td>
           </tr>
           <tr>
              <td>到馆时间：</td>
              <td><input type="text" id="arrivalTime" name="arrivalTime" required="required" style="width:80%;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})"/></td>
              <td>业务人员姓名：</td>
              <td><input type="text" id="staffName" name="staffName" required="required" style="width:80%;"/></td>
           </tr>
           <tr>
              <td>家属姓名：</td>
              <td><input type="text" id="familyName" name="familyName" style="width:80%;"/></td>
              <td>家属电话：</td>
              <td><input type="text" id="familyMobile" name="familyMobile" style="width:80%;"/></td>
           </tr>
           <tr>
              <td>押金：</td>
              <td><input type="text" id="deposit" name="deposit" style="width:80%;"/></td>
              <td>备注：</td>
              <td><input type="text" id="memo" name="memo" style="width:80%;"/></td>     
           </tr> 
           <tr>
              <td>送尸单位：</td>
              <td colspan="3"><input type="text" id="sendRemainsUnit" name="sendRemainsUnit" style="width:93.5%;" required="required"/></td>
           </tr>
           <tr>
              <td>事故地址：</td>
              <td colspan="3"><input type="text" id="accidentAddress" name="accidentAddress" style="width:93.5%;" required="required"/></td>
           </tr>
           <tr>
              <td>家庭住址：</td>
              <td colspan="3"><input type="text" id="familyAddress" name="familyAddress" style="width:93.5%;"/></td>
           </tr>   
           <tr>
              <td colspan="4"><input type="submit" id="save" value="保存" style="width:60px;"/></td>         
           </tr>
       </table>
    </form>
  </body>
</html>