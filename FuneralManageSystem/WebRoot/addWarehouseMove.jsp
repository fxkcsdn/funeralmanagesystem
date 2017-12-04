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
    <script type="text/javascript" src="js/warehouseMoveManage/addWarehouseMove.js"></script>
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>
  </head>
  
  <body>
    <form id="form1" name="form1" method="post" action="" onsubmit="return addWarehouseMove();">
      <table>
        <tr>
          <td>业务人员：</td>
          <td><input type="text" id="staffName" name="staffName" required="required"/></td>
          <td>调拨日期：</td>
          <td><input type="text" id="moveDate" name="moveDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" onchange="checkDate();"/></td>
          <td>调出仓库：</td>
          <td><input type="text" id="outWarehouse" name="outWarehouse" required="required"/></td>
        </tr>
        <tr>
          <td>调入仓库：</td>
          <td><input type="text" id="inWarehouse" name="inWarehouse" required="required"/></td>
        </tr>
        <tr>
          <td colspan="6" align="right"><input type="submit" id="saveButton" name="saveButton" value="提交"/></td>
        </tr>
      </table>
      <p><a id="addGoods" href="javascript:void(0)" style="color:blue;" onclick="addRow();">添加商品</a></p>
      <table id="table" name="table" border="1">
        <thead>
          <th style="width:150px;">商品种类</th>
          <th style="width:150px;">品名</th>
          <th style="width:100px;">单位</th>
          <th style="width:120px;">库存数量</th>
          <th style="width:120px;">调拨数量</th>
          <th style="width:120px;">操作</th>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        </tfoot>
      </table>
    </form>
  </body>
</html>
