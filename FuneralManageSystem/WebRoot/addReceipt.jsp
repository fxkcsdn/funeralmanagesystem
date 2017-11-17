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
    <script type="text/javascript" src="js/receiptManage/addReceipt.js"></script>
    <script type="text/javascript" src="js/paging.js"></script>
    <link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>
  </head>
  
  <body>
  <form id="form1" name="form1" method="post" action="" onsubmit="return saveReceiptInfo();">
    <table>
        <tr>
          <td>采购编号：</td>
          <td><input type="text" id="purchaseNumber" name="purchaseNumber"/></td>
          <td>厂家：</td>
          <td>
            <select id="supplierName" name="supplierName" style="width:150px;">
              <option value=""></option>
            </select>
          </td>
          <td>日期段：</td>
          <td><input type="text" id="startTime" name="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"/></td>
          <td>~</td>
          <td><input type="text" id="endTime" name="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})"></td>
        </tr>
        <tr>
          <td colspan="8" align="right"><input type="button" id="queryButton" value="查询" onclick="searchPurchase();"/></td>
        </tr>
      </table><br>
      <table id="table" border="1" style="table-layout:fixed; width:1000px; word-wrap:break-word; word-break:break-all;">
        <thead>
          <tr>
            <th style="width:100px;">采购编号</th>
            <th style="width:150px;">厂家</th>
            <th style="width:100px;">申请人</th>
            <th style="width:150px;">创建日期</th>
            <th style="width:100px;">财务审批</th>
            <th style="width:100px;">分馆长审批</th>
            <th style="width:100px;">馆长审批</th>
            <th style="width:100px;">是否结案</th>
            <th style="width:100px;">操作</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
      <div id="page" class="pagination"></div>
      <br><hr><br>
      <div id="receiptInfo" style="display:none;">
        <table>
          <tr>
            <td>收货人：</td>
            <td><input type="text" id="staffName" name="staffName" required="required"/></td>
            <td>收货日期：</td>
            <td><input type="text" id="receiptDate" name="receiptDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})" onchange="checkDate();"/></td>
            <td>收货仓库：</td>
            <td><input type="text" id="warehouse" name="warehouse" value="总库" disabled="disabled"/></td>
          </tr>
        </table>
        <p><b>当前采购单号：</b><input type="text" id="curPurchaseNumber" name="curPurchaseNumber" value="" disabled="disabled"/></p>
        <table id="detail" border="1">
          <thead>
            <tr>
              <th width="175px">商品种类</th>
              <th width="175px">品名</th>
              <th width="120px">单位</th>
              <th width="120px">采购数量</th>
              <th width="150px">入库价</th>
              <th width="120px">未到货数量</th>
              <th width="150px">本次收货数量</th>
            </tr>
          </thead>
          <tbody>
          </tbody>
        </table><br>
        <input type="submit" id="saveButton" name="saveButton" value="提交" style="margin-left:1008px;"/>
      </div>
  </form>
  </body>
</html>
