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
    <title>My JSP 'rentCoffin.jsp' starting page</title>
    
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
    <script>
    	window.onload=initialization;
	    function initialization()
	    {
	       document.getElementById("tab2_2").click();
	       var url = location.search; //获取url中"?"符后的字串
//	       var theRequest = new Object();
	       if(url.indexOf("?")!=-1){
	       		var str=url.substr(1);
	       		var deadIdName=str.split("=")[0];
	       		var deadId=str.split("=")[1];
//	       		alert(deadIdName);
//	       		alert(deadId);
	       		document.getElementById("deadIdStorage").value=deadId;
	       }
	    }
    </script>
	<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
  </head>
  <META content="MSHTML 6.00.2900.6287" name=GENERATOR>
  <body>   
  <OBJECT id=CVR_IDCard height=0 width=0 classid=clsid:10946843-7507-44FE-ACE8-2B3483D179B7 name=CVR_IDCard></OBJECT>
  <input type="hidden" id="deadIdStorage">
  <div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.rent.location.reload(); ">登记租棺信息</li>
    <li class="TabbedPanelsTab" tabindex="0" id="tab2_2" onclick="window.frames.remainsCarry.location.reload(); ">还棺、还车</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="window.frames.remainsCarry.location.reload(); ">修改绑定信息</li>
  </ul>

  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent">
      <form name="form1" method="post" action="" >
           <iframe name="rent" src="rent.jsp" width="100%" height="100%"></iframe>
      </form>    
    </div>
    
    <div class="TabbedPanelsContent">
      <form action="" method="post" enctype="multipart/form-data" name="form2">            
          <iframe name="remainsCarry" src="remainsCarry.jsp" width="100%" height="100%"></iframe> 
      </form>      
    </div>
    
     <div class="TabbedPanelsContent">
      <form action="" method="post" enctype="multipart/form-data" name="form2">            
          <iframe name="editBinding" src="editBinding.jsp" width="100%" height="100%"></iframe> 
      </form>      
    </div>
    
  </div>
</div>  
 
  </body>
 <script>
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</html>