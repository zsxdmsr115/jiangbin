<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.getRequestDispatcher("/resfood.action?op=paging&pageNo=1").forward(request, response);
%>