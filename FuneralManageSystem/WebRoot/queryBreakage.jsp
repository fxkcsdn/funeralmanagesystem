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
    <script type="text/javascript" src="js/breakageManage/queryBreakage.js"></script>
    <script type="text/javascript" src="js/paging.js"></script>
    <link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>

  </head>
  
  <body>
    <table>
      <tr>
        <td>业务人员：</td>
        <td><input type="text" id="staffName" name="staffName"/></td>
        <td>报损日期段：</td>
        <td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"/></td>
        <td>~</td>
        <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"/></td>
      </tr>
      <tr>
        <td>报损仓库：</td>
        <td><input type="text" id="warehouseName" name="warehouseName"/></td>
      </tr>
      <tr>
        <td colspan="6" align="right"><input type="button" id="queryButton" name="queryButton" value="查询" onclick="searchBreakage();"/></td>
      </tr>
    </table><br>
    <table id="table" border="1" style="table-layout:fixed; width:650px; word-wrap:break-word; word-break:break-all;">
      <thead>
        <th style="width:100px;">报损单号</th>
        <th style="width:100px;">业务人员</th>
        <th style="width:150px;">报损日期</th>
        <th style="width:100px;">报损仓库</th>
        <th style="width:100px;">操作1</th>
        <th style="width:100px;">操作2</th>
      </thead>
      <tbody>
      </tbody>
      <tfoot>
      </tfoot>
    </table>
    <div id="page" class="pagination"></div>
  </body>
</html>
