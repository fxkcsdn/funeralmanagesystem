<%@ page language="java" contentType="text/html;charset=GB18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<script language="javascript">
    function realSysTime(clock){
    	var now=new Date();			//创建Date对象
    	var year=now.getFullYear();	//获取年份
    	var month=now.getMonth();	//获取月份
    	var date=now.getDate();		//获取日期
    	var day=now.getDay();		//获取星期
    	var hour=now.getHours();	//获取小时
    	var minu=now.getMinutes();	//获取分钟
    	var sec=now.getSeconds();	//获取秒钟
    	month=month+1;
    	var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
    	var week=arr_week[day];		//获取中文的星期
    	var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec;	//组合系统时间
    	clock.innerHTML="<span style='color:white'>" + "当前时间：" +time+"</span>";
    }
    window.onload=function()
    {
		window.setInterval("realSysTime(clock)",1000);	//实时获取并显示系统时间
    }
</script>
<meta http-equiv="Content-Type" content="text/html;charset=GB18030">
<title>欢迎使用殡葬智能化系统</title>
</head>
<body>
	<table height="100%" width="100%" border="0" background="Images/TitleBackground.jpg">
		
		<tr height="15%">
			<td></td>
			<td></td>
			<td>
				<span style="color: white"></span>
			</td>
		</tr>
		
		<tr height="15%">
			<td width="57%" align="center"><font size="8" color="white"><b>东台市殡仪馆智能管控系统</b></font></td>
			<td width="10"></td>
			<td width="33%"></td>
		</tr>
		
		<tr height="15%">
			<td></td>
			<td></td>
			<td>
				<div id="clock"></div>
			</td>
		</tr>
	</table>
</body>
</html>
