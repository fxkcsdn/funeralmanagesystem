<%@ page language="java" contentType="text/html;charset=gb18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb18030">
<title>欢迎使用殡葬智能化系统</title>
<script src="js/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="css/SpryTabbedPanels.css" rel="stylesheet" type="text/css">
<style type="text/css">
body,td,th {
	font-size: 24px;
}
</style>
<script>
	function validateForm1(form1)
	{
		if(form1.deadId.value == "")
		{
			alert("逝者身份证号码不能为空！");
			form1.deadId.focus();
			return false;
		}
		if(form1.startTime.value=="")
		{
			alert("租棺起始时间不能为空！");
			form1.startTime.focus();
			return false;
		}
		
		url="deadId="+form1.deadId.value+"&";
		url=url+"deadName="+form1.deadName.value+"&";
		url=url+"memberMobile="+form1.memberMobile.value+"&";
		url=url+"startTime="+form1.startTime.value;
		
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateForm1Callback;
		http_request.open('POST',"ShouLing!registWatchSpirit",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
	}
	//处理表单1返回数据
	function validateForm1Callback()
	{
		if(http_request.readyState==4)
			{
				if(http_request.status==200)
					{
						var json=eval("("+http_request.responseText+")");
						json=eval("("+json+")");
						alert(json.returnString);
					}
				else
					{
						alert("您所请求的页面没有响应！");
					}
			}
	}
	function updateData(input){
		url="deadId="+form2.deadId.value;
    	http_request=createHttpRequest();
		http_request.onreadystatechange=updateDataCallback;
		http_request.open('POST',"ShouLing!getWatchSpiritInfo",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;//阻止页面刷新
	}
	function updateDataCallback(){
		 if(http_request.readyState==4){
 			if(http_request.status==200){
 				var json=eval("("+http_request.responseText+")");
 				json=eval("("+json+")");
 				if(json.returnString=="1"){
 					form2.deadName.value=json.deadName;
 					form2.memberMobile.value=json.memberMobile;
 					form2.startTime.value=json.startTime;
 					form2.endTime.value=json.endTime;
 					form2.watchSpiritUsedTime.value=json.watchSpiritUsedTime;
 					if(form2.endTime.value!=""){
	 					var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
	 					var d2=new Date((form2.endTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
	 					var d3=d2-d1;
	 					var h=Math.floor(d3/3600000);
	 					form2.watchSpiritUesdTime.value=h;
 					}
 					else{
 						form2.watchSpiritUesdTime.value="";
 					}
 				}
 				else{
 					form2.deadName.value="";
 					form2.memberMobile.value="";
 					form2.startTime.value="";
 					form2.endTime.value="";
 					form2.watchSpiritUesdTime.value="";
 				}
 			}
 			else{
 				alert("您请求的页面没有反应！");
 			}
 		}
 	}
 	function calculateRentTime()
	{
		if(form2.startTime.value!="" && form2.endTime.value!="")
			{
				var d1=new Date((form2.startTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
				var d2=new Date((form2.endTime.value+":00").replace(/-/g,"/"));//转换成javascript的标准时间
				var d3=d2-d1;
				var h=Math.floor(d3/3600000);
				form2.watchSpiritUsedTime.value=h;
			}
		else
			{
				form2.watchSpiritUsedTime.value="";
			}
	}
	//提交表单2
	function validateForm2(input){
		if(form2.deadId.value==""){
     		alert("逝者身份证号码不能为空！");
     		form2.deadId.focus();
     		return false;
     	}
     	if(form2.endTime.value==""){
     		alert("还棺时间不能为空！");
     		form2.endTime.focus();
     		return false;
     	}
     	url="deadId="+form2.deadId.value+"&";
     	url=url+"endTime="+form2.endTime.value+"&";
     	url=url+"watchSpiritUsedTime="+form2.watchSpiritUsedTime.value;
		http_request=createHttpRequest();
		http_request.onreadystatechange=validateForm2Callback;
		http_request.open('POST',"ShouLing!endWatchSpirit",false);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		http_request.send(url);
		return false;
	}
	function validateForm2Callback(){
		 if(http_request.readyState==4){
 			if(http_request.status==200){
 				var json=eval("("+http_request.responseText+")");
				json=eval("("+json+")");
				alert(json.returnString);
 			}
 			else{
 				alert("请求的页面没有响应！");
 			}
 		}
 	}
</script>
</head>
<body>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">登记守灵信息</li>
    <li class="TabbedPanelsTab" tabindex="0">记录守灵信息</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
      <form name="form1" method="post" onsubmit="return validateForm1(this);">
        <p>
          <label for="deadId">逝者身份证号码：</label>
          <input type="text" name="deadId" id="deadId">
          <label for="deadName">逝者姓名：</label>
          <input type="text" name="deadName" id="deadName">
        </p>
        <p>
          <label for="memberMobile">丧属手机号码：</label>
          <input type="text" name="memberMobile" id="memberMobile">
        </p>
        <p>
          <label for="startTime">守灵起始时间：</label>
          <input type="text" name="startTime" id="startTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
        </p>
        <p>&nbsp;</p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="DeathwatchSubmit3" id="DeathwatchSubmit3" value="提交">
        </p>
        <p>&nbsp;</p>
      </form>
      <p>&nbsp;</p>
    </div>
    <div class="TabbedPanelsContent">
      <form name="form2" method="post"  onsubmit="return validateForm2(this);">
        <p>
          <label for="deadId">逝者身份证号码：</label>
          <input type="text" name="deadId" id="deadId" onkeyup="return updateData(this);">
          <label for="deadName">逝者姓名：</label>
          <input type="text" name="deadName" id="deadName">
        </p>
        <p>
          <label for="memberMobile">丧属手机号码：</label>
          <input type="text" name="memberMobile" id="memberMobile">
        </p>
        <p>
          <label for="startTime">守灵起始时间：</label>
          <input type="text" name="startTime" id="startTime">
        </p>
        <p>
          <label for="endTime">守灵结束时间：</label>
          <input type="text" name="endTime" id="endTime" onchange="return calculateRentTime()" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
          <label for="watchSpiritUsedTime">守灵总时间：</label>
          <input type="text" name="watchSpiritUsedTime" id="watchSpiritUsedTime">
          <label>小时</label><br>
        </p>
        <hr>
        <p>&nbsp;</p>
        <p>
          <input type="submit" name="DeathwatchSubmit4" id="DeathwatchSubmit4" value="提交">
        </p>
        <p>&nbsp;</p>
      </form>
      <p>&nbsp;</p>
    </div>
  </div>
</div>

<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>

 <script src="js/My97DatePicker/WdatePicker.js">
	//这是一个时间控件
 </script>
 
 <script src="js/createHttpRequest.js">
	//创建XMLHttpRequest对象
</script>
</body>
</html>
