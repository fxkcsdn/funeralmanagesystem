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
    	var purIndex = url.lastIndexOf("=");
    	// 取出type值和采购编号
    	var type = url.substring(typeIndex + 1, connectIndex);
    	var purchaseNumber = url.substring(purIndex + 1);
    	// 保存type和采购编号
    	document.getElementById("typeStorage").value = type;
    	document.getElementById("purchaseNumberStorage").value = purchaseNumber;
    	// 跳转到审核界面
    	if (type == "financeAudit" || type == "viceCuratorAudit" || type == "curatorAudit")
    	{
    	  document.getElementById("TabbedPanels1").children[0].children[4].click();
    	}
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
    	// 跳转到取消审核界面
    	if (type == "cancelFinanceAudit" || type == "cancelViceCuratorAudit" || type == "cancelCuratorAudit")
    	{
    	  document.getElementById("TabbedPanels1").children[0].children[5].click();
    	}
    	// 跳转到结案界面
    	if (type == "close")
    	{
    	  document.getElementById("TabbedPanels1").children[0].children[6].click();
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
<input type="hidden" id="purchaseNumberStorage"/>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.queryPurchase.location.reload();">查询采购单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.addPurchase.location.reload();">新增采购单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.updatePurchase.location.reload();">修改采购单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.deletePurchase.location.reload();">删除采购单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.auditPurchase.location.reload();">审核采购单</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.cancelAuditPurchase.location.reload();">取消采购单审核</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.closePurchase.location.reload();">结案采购单</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <!-- 查询 -->
    <div class="TabbedPanelsContent">
    <iframe id="queryPurchase" name="queryPurchase" src="queryPurchase.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
    
    <!-- 新增 -->
    <div class="TabbedPanelsContent">
    <iframe id="addPurchase" name="addPurchase" src="addPurchase.jsp" width="100%" height="90%">
      
    </iframe>
    </div>
 
    <!-- 修改 -->
    <div class="TabbedPanelsContent">
    <iframe id="updatePurchase" name="updatePurchase" src="updatePurchase.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>
    
    <!-- 删除 -->
    <div class="TabbedPanelsContent">
    <iframe id="deletePurchase" name="deletePurchase" src="deletePurchase.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>

    <!-- 审核 -->    
    <div class="TabbedPanelsContent">
    <iframe id="auditPurchase" name="auditPurchase" src="auditPurchase.jsp" width="100%" height="90%">
    
    </iframe> 
    </div>
    
    <!-- 取消审核 -->    
    <div class="TabbedPanelsContent">
    <iframe id="cancelAuditPurchase" name="cancelAuditPurchase" src="cancelAuditPurchase.jsp" width="100%" height="90%">
    
    </iframe>      
    </div>
    
    <!-- 结案 -->    
    <div class="TabbedPanelsContent">
    <iframe id="closePurchase" name="closePurchase" src="closePurchase.jsp" width="100%" height="90%">
    
    </iframe>
    </div>
    
  </div>
</div>  
</body>
<script type="text/javascript">
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
