<%@page import="com.yc.bbs.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.yc.bbs.biz.impl.BoardBizImpl"%>
<%@page import="com.yc.bbs.biz.BoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

    BoardBiz boardBiz = new BoardBizImpl();
	Map<Integer,List<Board>> boardMap =  boardBiz.findAllBoard();
	System.out.print(boardMap);
 
%>