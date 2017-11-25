<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
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
    <script type="text/javascript" src="js/warehouseCheckManage/addWarehouseCheck.js"></script>
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>

  </head>
  
  <body>
    <form id="form1" name="form1" method="post" action="" onsubmit="return saveWarehouseCheckInfo();">
      <table>
        <tr>
          <td>业务人员：</td>
          <td><input type="text" id="staffName" name="staffName" required="required"/></td>
          <td>盘点日期：</td>
          <td><input type="text" id="checkDate" name="checkDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" onchange="checkCheckDate();"/></td>
          <td>盘点仓库：</td>
          <td><input type="text" id="warehouseName" name="warehouseName" required="required"/></td>
        </tr>
        <tr>
          <td colspan="6" align="right"><input type="button" id="checkButton" name="checkButton" value="盘点" onclick="checkGoods();"/></td>
        </tr>
      </table><br>
      <table id="table" border="1">
        <thead>
          <th width="150px">商品种类</th>
          <th width="150px">品名</th>
          <th width="100px">单位</th>
          <th width="120px">理论数量</th>
          <th width="120px">实际数量</th>
          <th width="120px">盈亏数量</th>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        </tfoot>
      </table><br>
      <input type="submit" id="saveButton" name="saveButton" value="提交" style="margin-left:750px;"/>
    </form>
  </body>
</html>
