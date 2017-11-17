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
	
	<script type="text/javascript" src="js/purchaseManage/closePurchase.js"></script>
	<style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>

  </head>
  
  <body>
    <form id="form1" method="post" action="" onsubmit="return saveCloseInfo();">
      <table>
        <tr>
          <td>采购编号:</td>
          <td><input type="text" id="purchaseNumber" name="purchaseNumber" disabled="disabled"/></td>
          <td>厂家:</td>
          <td><input type="text" id="supplier" name="supplier" disabled="disabled"/></td>
          <td>申请人:</td>
          <td><input type="text" id="operator" name="operator" disabled="disabled"/></td>
        </tr>
        <tr>
          <td>创建日期:</td>
          <td><input type="text" id="createDate" name="createDate" disabled="disabled"/></td>
          <td>财务审批人:</td>
          <td><input type="text" id="financeAudit" name="financeAudit" disabled="disabled"/></td>
          <td>分馆长审批人:</td>
          <td><input type="text" id="viceCuratorAudit" name="viceCuratorAudit" disabled="disabled"/></td>
        </tr>
        <tr>
          <td>馆长审批人:</td>
          <td><input type="text" id="curatorAudit" name="curatorAudit" disabled="disabled"/></td>
          <td>备注:</td>
          <td><input type="text" id="memo" name="memo" disabled="disabled"/></td>
        </tr>
        <tr>
          <td colspan="8" align="right">
            <input type="submit" id="saveButton" name="saveButton" value="提交"/>
          </td>
        </tr>
      </table>
      <p><b>采购单明细信息:</b></p>
      <table id="goodsInfo" border="1">
        <thead>
          <th width="150px">商品种类</th>
          <th width="150px">品名</th>
          <th width="150px">单位</th>
          <th width="150px">数量</th>
          <th width="150px">入库价</th>
        </thead>
        <tbody>
        </tbody>
        <tfoot></tfoot>
      </table>
    </form>
  </body>
</html>
