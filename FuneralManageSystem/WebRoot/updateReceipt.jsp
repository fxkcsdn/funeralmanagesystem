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
    <script type="text/javascript" src="js/receiptManage/updateReceipt.js"></script>
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
            <td>当前收货单号：</td>
            <td><input type="text" id="receiptNumber" name="receiptNumber" disabled="disabled"/></td>
            <td>收货人：</td>
            <td><input type="text" id="staffName" name="staffName" required="required"/></td>
            <td>收货日期：</td>
            <td><input type="text" id="receiptDate" name="receiptDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:''})" onchange="checkDate();"/></td>
          </tr>
          <tr>
            <td>收货仓库：</td>
            <td><input type="text" id="warehouse" name="warehouse" value="总库" disabled="disabled"/></td>
          </tr>
          <tr>
            <td colspan="6" align="right"><input type="submit" id="saveButton" name="saveButton" value="提交"/></td>
          </tr>
        </table>
        <p><b>收货详细信息：</b></p>
        <label>采购单号：</label><input type="text" id="purchaseNumber" name="purchaseNumber" disabled="disabled"/><br><br>
        <table id="receiptDetails" border="1">
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
          <tfoot>
          </tfoot>
        </table>
  </form>
  </body>
</html>
