<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    	var recIndex = url.lastIndexOf("=");
    	// 取出type值和销售单号
    	var type = url.substring(typeIndex + 1, connectIndex);
    	var sellNumber = url.substring(recIndex + 1);
    	// 保存type和采购编号
    	document.getElementById("typeStorage").value = type;
    	document.getElementById("sellNumberStorage").value = sellNumber;

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
  };
</script>
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
</head>
<body>
<input type="hidden" id="typeStorage"/>
<input type="hidden" id="sellNumberStorage"/>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.querySell.location.reload();">查询销售单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.addSell.location.reload();">新增销售单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.updateSell.location.reload();">修改销售单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.deleteSell.location.reload();">删除销售单</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <!-- 查询 -->
    <div class="TabbedPanelsContent">
    <iframe id="querySell" name="querySell" src="querySell.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
    
    <!-- 新增 -->
    <div class="TabbedPanelsContent">
    <iframe id="addSell" name="addSell" src="addSell.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
 
    <!-- 修改 -->
    <div class="TabbedPanelsContent">
    <iframe id="updateSell" name="updateSell" src="updateSell.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>
    
    <!-- 删除 -->
    <div class="TabbedPanelsContent">
    <iframe id="deleteSell" name="deleteSell" src="deleteSell.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>
      
</div>  
</body>
<script type="text/javascript">
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
