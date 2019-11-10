<%@page import="com.yc.bean.Resfood"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Resfood resfood = (Resfood)	request.getAttribute("rsfood");
	%>
	<%=resfood.getDetail() %>
	<a href="javascript:history.back()">返回上一级</a>
</body>
</html>