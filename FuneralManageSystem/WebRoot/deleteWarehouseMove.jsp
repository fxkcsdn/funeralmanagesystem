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
    <script type="text/javascript" src="js/warehouseMoveManage/deleteWarehouseMove.js"></script>
    <script type="text/javascript" src="js/paging.js"></script>
    <link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>

  </head>
  
  <body>
  <form id="form1" name="form1" method="post" action="" onsubmit="return deleteWarehouseMove();">
      <table>
        <tr>
          <td>调拨单号：</td>
          <td><input type="text" id="warehouseMoveNumber" name="warehouseMoveNumber" disabled="disabled"/></td>
          <td>业务人员：</td>
          <td><input type="text" id="staffName" name="staffName" disabled="disabled"/></td> 
        </tr>
        <tr>
          <td>调拨日期：</td>
          <td><input type="text" id="moveDate" name="moveDate" disabled="disabled"/></td>
          <td>调出仓库：</td>
          <td><input type="text" id="outWarehouse" name="outWarehouse" disabled="disabled"/></td>
        </tr>
        <tr>
          <td>调入仓库：</td>
          <td><input type="text" id="inWarehouse" name="inWarehouse" disabled="disabled"/></td>
        </tr>
        <tr>
          <td colspan="6" align="right"><input type="submit" id="saveButton" name="saveButton" value="提交"/></td>
        </tr>
      </table>
      <p><b>调拨详细信息：</b></p>
      <table id="table" name="table" border="1">
        <thead>
          <tr>
            <th style="width:150px;">商品种类</th>
            <th style="width:150px;">品名</th>
            <th style="width:150px;">单位</th>
            <th style="width:150px;">调拨数量</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
        </tfoot>
      </table>
  </form>    
  </body>
</html>
