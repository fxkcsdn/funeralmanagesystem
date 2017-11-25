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
<script type="text/javascript">
  /**
   * 页面加载时触发该事件
   */
  window.onload = function()
  {
  	// 截取路径?后的内容
    var url = window.location.search;
    if (url.indexOf("?") != -1)
    {
    	var typeIndex = url.indexOf("=");
    	var connectIndex = url.indexOf("&");
    	var wcIndex = url.lastIndexOf("=");
    	// 取出type值和盘点单号
    	var type = url.substring(typeIndex + 1, connectIndex);
    	var warehouseCheckNumber = url.substring(wcIndex + 1);
    	// 保存type和盘点单号
    	document.getElementById("typeStorage").value = type;
    	document.getElementById("warehouseCheckNumberStorage").value = warehouseCheckNumber;
    	// 跳转到修改界面
    	if (type == "update")
    	{
    	  document.getElementById("TabbedPanels1").children[0].children[2].click();
    	}
    	// 跳转到删除界面
    	if (type == "delete")
    	{
    	  document.getElementById("TabbedPanels1").children[0].children[3].click();
    	}
    }
  }
</script>
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<body>
<input type="hidden" id="typeStorage"/>
<input type="hidden" id="warehouseCheckNumberStorage"/>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.queryWarehouseCheck.location.reload();">查询盘点单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.addWarehouseCheck.location.reload();">新增盘点单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.updateWarehouseCheck.location.reload();">修改盘点单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.deleteWarehouseCheck.location.reload();">删除盘点单</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <!-- 查询 -->
    <div class="TabbedPanelsContent">
    <iframe id="queryWarehouseCheck" name="queryWarehouseCheck" src="queryWarehouseCheck.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
    
    <!-- 新增 -->
    <div class="TabbedPanelsContent">
    <iframe id="addWarehouseCheck" name="addWarehouseCheck" src="addWarehouseCheck.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
 
    <!-- 修改 -->
    <div class="TabbedPanelsContent">
    <iframe id="updateWarehouseCheck" name="updateWarehouseCheck" src="updateWarehouseCheck.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>
    
    <!-- 删除 -->
    <div class="TabbedPanelsContent">
    <iframe id="deleteWarehouseCheck" name="deleteWarehouseCheck" src="deleteWarehouseCheck.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>

  </div>
</div>  
</body>
<script type="text/javascript">
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
