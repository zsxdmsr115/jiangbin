<%@page import="com.yc.bbs.bean.Board"%>
<%@page import="com.yc.bbs.bean.Topic"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="com.yc.bbs.biz.impl.BoardBizImpl"%>
<%@page import="com.yc.bbs.biz.BoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

   int pageSize=3;
   int pageNo=1;//默认第一页
  
   int bid = Integer.parseInt(request.getParameter("bid"));
   String op = request.getParameter("op");
  
   		BoardBiz bdbiz = new BoardBizImpl();
        if(request.getParameter("pageNo")!=null){
        	pageNo=Integer.parseInt((String) request.getParameter("pageNo"));
        	
        }
       
        
      List<Topic> topicList;
      if("search".equals(op)){
    	  String title = request.getParameter("title");
    	 
    	  topicList=bdbiz.findAllTopiByBoardIdLimitByTitle(bid, pageNo, pageSize, title);
      }else{
    	
    	  topicList=bdbiz.findAllTopiByBoardIdLimit(bid, pageNo, pageSize);
      }
      session.setAttribute("topicList", topicList);//帖子集合
      request.setAttribute("pageSize", pageSize);//每页的记录数
      request.setAttribute("pageNo", pageNo);
     
      bdbiz.findBoardById(bid);
      
      //获取当前版块
      Board board =  bdbiz.findBoardById(bid);
      System.out.println(board);
      session.setAttribute("board", board);
      
      //统计当前版块有多少的帖子
    int topicCount =   (int) bdbiz.topicCountByBoardId(bid);
    request.setAttribute("topicCount", topicCount);
    //算出多少页
    int countpage = topicCount%pageSize==0 ? topicCount/pageSize : topicCount/pageSize+1;
    request.setAttribute("countpage", countpage);
  	request.getRequestDispatcher("list.jsp").forward(request, response);

%>