<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;">
<title>欢迎使用殡葬智能化系统</title>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<script type="text/javascript" src="js/paging.js"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<script src="js/json2.js"></script>
<script src="js/importToExcel.js"></script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.warehouseBegin.location.reload();">期初入库</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <!-- 期初入库 -->
    <div class="TabbedPanelsContent">
    <iframe id="warehouseBegin" name="warehouseBegin" src="warehouseBegin.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
    
  </div>
</div>  
</body>
<script type="text/javascript">
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
