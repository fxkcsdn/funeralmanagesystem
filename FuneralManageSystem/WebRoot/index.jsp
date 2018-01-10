<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>

<%
session.setAttribute("form",session.getAttribute("form"));
%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>欢迎使用殡葬智能化系统</title>
</head>
<frameset rows="15%,*" cols="*" frameborder="1" border="1" framespacing="0">
	<frame src="top.jsp" name="top" scrolling="NO">
	<frameset rows="*" cols="15%,*" framespacing="0" frameborder="yes" border="1">
		<frame src="left.jsp" name="left" scrolling="NO">
		<frame src="rentCoffin.jsp" name="right">
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
