<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <script type="text/javascript" src="js/purchaseManage/queryPurchase.js"></script>
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
          <td>厂家：</td>
          <td>
            <select id="supplierName" name="supplierName" style="width:150px;">
              <option value=""></option>
            </select>
          </td>
          <td>创建日期段：</td>
          <td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"/></td>
          <td>~</td>
          <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"></td>
          <td>状态：</td>
          <td>
            <select id="state" name="state" style="width:150px;">
              <option value=""></option>
              <option value="财务未审核">财务未审核</option>
              <option value="分馆长未审核">分馆长未审核</option>
              <option value="馆长未审核">馆长未审核</option>
              <option value="已审核">已审核</option>
            </select>
          </td>
        </tr>
        <tr>
          <td colspan="8" align="right"><input type="button" id="queryButton" value="查询" onclick="searchPurchase();"/></td>
        </tr>
      </table><br>
      <table id="table" border="1" style="table-layout:fixed; width:980px; word-wrap:break-word; word-break:break-all;">
        <thead>
          <tr>
            <th style="width:100px;">采购编号</th>
            <th style="width:150px;">厂家</th>
            <th style="width:100px;">申请人</th>
            <th style="width:150px;">创建日期</th>
            <th style="width:100px;">财务审批</th>
            <th style="width:100px;">分馆长审批</th>
            <th style="width:100px;">馆长审批</th>
            <th style="width:60px;">操作1</th>
            <th style="width:60px;">操作2</th>
            <th style="width:60px;">操作3</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      <div id="page" class="pagination"></div>
  </body>
</html>
