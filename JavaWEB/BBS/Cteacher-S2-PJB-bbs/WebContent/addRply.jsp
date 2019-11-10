<%@page import="com.yc.bbs.biz.impl.ReplyBizImpl"%>
<%@page import="com.yc.bbs.biz.ReplyBiz"%>
<%@page import="com.yc.bbs.bean.Reply"%>
<%@page import="com.yc.util.RequestUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  	request.setCharacterEncoding("utf-8");
	RequestUtils rutil =  new 	RequestUtils();
    ReplyBiz  rbiz = new ReplyBizImpl();
    
	Reply  reply = rutil.parseRequest(request,Reply.class);
	//System.out.println(reply);
	rbiz.addReply(reply);
    request.getRequestDispatcher("/doDetails.jsp").forward(request, response);
	
%>