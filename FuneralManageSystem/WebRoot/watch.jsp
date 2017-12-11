<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>My JSP 'watch.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/json2.js"></script>
    <script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
	<script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
	<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
  </head>
  
  <body>
   <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
    
       
   <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.registerWatchRemainsCarry.location.reload(); ">登记守灵遗体接运信息</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.registerWatchInfo.location.reload(); ">登记守灵信息</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.registerWatchServiceInfo.location.reload(); ">登记守灵服务信息</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.watchBill.location.reload(); ">守灵结算</li>
</ul>

  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent">
      <form name="form1" method="post" action="">
           <iframe name="registerWatchRemainsCarry" src="registerWatchRemainsCarry.jsp" width="100%" height="90%"></iframe>
      </form>    
    </div>
    
    <div class="TabbedPanelsContent">
      <form action="" method="post" enctype="multipart/form-data" name="form2">            
          <iframe name="registerWatchInfo" src="registerWatchInfo.jsp" width="100%" height="90%"></iframe> 
      </form>      
    </div>
    
    <div class="TabbedPanelsContent">
      <form action="" method="post" enctype="multipart/form-data" name="form2">            
          <iframe name="registerWatchInfo" src="registerWatchServiceInfo.jsp" width="100%" height="90%"></iframe> 
      </form>      
    </div>
    
    <div class="TabbedPanelsContent">
      <form name="form3" method="post" action="">  
          <iframe name="watchBill" src="watchBill.jsp" width="100%" height="90%"></iframe> 
      </form>     
    </div>
    
  </div>
</div>  
 
  </body>
 <script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>
