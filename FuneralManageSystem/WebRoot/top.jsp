<%@ page language="java" contentType="text/html;charset=GB18030"
	pageEncoding="GB18030"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<script language="javascript">
    function realSysTime(clock){
    	var now=new Date();			//����Date����
    	var year=now.getFullYear();	//��ȡ���
    	var month=now.getMonth();	//��ȡ�·�
    	var date=now.getDate();		//��ȡ����
    	var day=now.getDay();		//��ȡ����
    	var hour=now.getHours();	//��ȡСʱ
    	var minu=now.getMinutes();	//��ȡ����
    	var sec=now.getSeconds();	//��ȡ����
    	month=month+1;
    	var arr_week=new Array("������","����һ","���ڶ�","������","������","������","������");
    	var week=arr_week[day];		//��ȡ���ĵ�����
    	var time=year+"��"+month+"��"+date+"�� "+week+" "+hour+":"+minu+":"+sec;	//���ϵͳʱ��
    	clock.innerHTML="<span style='color:white'>" + "��ǰʱ�䣺" +time+"</span>";
    }
    window.onload=function()
    {
		window.setInterval("realSysTime(clock)",1000);	//ʵʱ��ȡ����ʾϵͳʱ��
    }
</script>
<meta http-equiv="Content-Type" content="text/html;charset=GB18030">
<title>��ӭʹ���������ܻ�ϵͳ</title>
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
			<td width="57%" align="center"><font size="8" color="white"><b>��̨�����ǹ����ܹܿ�ϵͳ</b></font></td>
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
