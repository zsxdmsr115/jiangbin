<%@page import="com.yc.examin.bean.Lesson"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.examin.biz.impl.LessonBizeImp"%>
<%@page import="com.yc.examin.biz.LessonBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   LessonBiz lbiz = new LessonBizeImp();
   List<Lesson> llist =  lbiz.getAllLesson();
  
   session.setAttribute("llist", llist);
  
%>