
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.yc.bbs.bean.User"%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.yc.bbs.bean.Board"%>

<%
 	String path = request.getContextPath();
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE html >
<html>
<head>
<base href="<%=basePath%>"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/jquery.messager.js">


</script>
<script type="text/javascript" src="js/commond.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<title>源辰论坛</title>
<script type="text/javascript">

  window.onload=function(){
	
	  CKEDITOR.replace( 'content',{width:' 800px',height:'500px'});
  }
</script>
</head>

<body>

<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->

	<DIV class="h" id="logindiv">
	      <%
	      User u =   (User)session.getAttribute("user");
	     
	         if(u!=null  ){
	        	 
	       %>
	           <input type="hidden" id="action" value="yes"/>
	          	欢迎您<a href="#"> <%=u.getUname() %></a>
	          	<a href="javascript:void(0)" onclick="signout()">退出</a>
	       <% 
	         }else{
	        %>
	         <input type="hidden" id="action" value="no"/>
	      	  您尚未　<a href="login.jsp">登录</a>
			&nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
	        	<% 
	         }
	      %>
		
	</DIV>
