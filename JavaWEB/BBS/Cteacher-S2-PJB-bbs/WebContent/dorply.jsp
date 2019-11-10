<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bbs.bean.JsonModel"%>
<%@page import="com.yc.bbs.biz.impl.ReplyBizImpl"%>
<%@page import="com.yc.bbs.biz.ReplyBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    ReplyBiz rb = new ReplyBizImpl();
 	Integer rid =	 Integer.parseInt(request.getParameter("rid"));
 	  
 	  JsonModel jm = new JsonModel();
 		 Gson gson = new Gson();
 		 System.out.println("rid====="+ rb.deleteReply(rid));
 		//System.out.println("rid====="+ (rb.deleteReply(rid)));
		 rb.deleteReply(rid);
		  jm.setCode(1);
	 	 jm.setObj(rid);
		  System.out.println(jm);
	 
	  String json = gson.toJson(jm);
		out.write(json);
%>