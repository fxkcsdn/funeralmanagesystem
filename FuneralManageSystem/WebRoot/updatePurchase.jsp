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

	<script type="text/javascript" src="js/purchaseManage/updatePurchase.js"></script>
    <style type="text/css">
      body,td,th {
	    font-size: 18px;
      }
    </style>

  </head>
  
  <body>
  <form id="form1" name="form1" method="post" action="" onsubmit="return savePurchaseInfo();">
    <table>
        <tr>
          <td>当前采购单号：</td>
          <td><input type="text" id="purchaseNumber" disabled="disabled"/></td>
          <td>厂家：</td>
          <td>
            <select id="supplierName" name="supplierName" style="width:150px;">
              <option value="请选择">请选择</option>
            </select>
          </td>
          <td>申请人：</td>
          <td><input type="text" id="operator" name="operator" required="required"/></td>
        </tr>
        <tr>
          <td>创建日期：</td>
          <td><input type="text" id="createDate" name="createDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',readOnly:true,minDate:'%y-%M-{%d}'})" required="required"></td>
          <td>备注：</td>
          <td><input type="text" id="memo" name="memo"/></td>
        </tr>
        <tr>
          <td colspan="6" align="right"><input id="saveButton" type="submit" value="提交" style="width:50px; margin-right:10px;"/><input id="createTableButton" type="button" value="打印" disabled="disabled" onclick=""/></td>
        </tr>
      </table> 
      <p><a id="addGoods" href="javascript:void(0)" style="color:blue;" onclick="addRow();">添加商品</a></p>
      <table id="goodsInfo" border="1">
        <thead>
          <tr>
            <th width="150px">商品种类</th>
            <th width="150px">品名</th>
            <th width="150px">单位</th>
            <th width="150px">数量</th>
            <th width="150px">入库价</th>
            <th width="150px">操作</th>
          </tr>
        </thead>
        <tbody>
        </tbody>
      </table>
  </form>
  </body>
</html>
