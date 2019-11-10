<%@page import="com.yc.examin.bean.Question"%>
<%@page import="com.yc.util.RequestUtils"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bbs.bean.JsonModel"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.bbs.bean.Reply"%>
<%@page import="com.yc.bbs.biz.impl.ReplyBizImpl"%>
<%@page import="com.yc.bbs.biz.ReplyBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
  String op = request.getParameter("op");
  int topicid =  Integer.parseInt(request.getParameter("topicid"));
  String content = request.getParameter("content");
 
  int uid = Integer.parseInt( request.getParameter("uid"));
  JsonModel jm = new JsonModel();
  Gson gson = new Gson();
   if("rply".equals(op)){
	 Reply ry = new Reply();
	 ReplyBiz rbiz = new ReplyBizImpl();
	 ry.setTitle("");
	 ry.setContent(content);
   	 ry.setUid(uid);
   	 ry.setTopicid(topicid);
	 int reuslt= rbiz.addReply(ry);
	 if(reuslt>0){
		List<Reply> list = rbiz.findReply(topicid);
	    jm.setCode(1);
	    jm.setObj(list.get(list.size()-1));
	  	String json = gson.toJson(jm);
	    out.write(json);
	 }
	   
   }
 
%>