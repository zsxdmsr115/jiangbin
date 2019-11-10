<%@page import="com.yc.bbs.bean.PageBean"%>
<%@page import="com.yc.bbs.bean.Reply"%>
<%@page import="com.yc.bbs.biz.impl.ReplyBizImpl"%>
<%@page import="com.yc.bbs.biz.ReplyBiz"%>
<%@page import="com.yc.bbs.bean.User"%>
<%@page import="com.yc.bbs.biz.impl.UserInfoImpl"%>
<%@page import="com.yc.bbs.biz.UserInfo"%>

<%@page import="com.yc.bbs.bean.Topic"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    UserInfo u =new UserInfoImpl();

  
	List<Topic>  toplist;
    if(!"".equals(request.getParameter("topicid"))&& request.getParameter("topicid")!=null){
    	
    	int topicid=Integer.parseInt(request.getParameter("topicid"));
		
    	  toplist = (List<Topic> ) session.getAttribute("topicList");
    	
     	if(toplist!=null && toplist.size()>0){
    	 	for(int i=0;i<toplist.size();i++){
    		 Topic topic =   toplist.get(i);
    		
    	   	if(topic.getTopicid()==topicid){
    	   		int uid = topic.getUid();
    		  	User user =   u.getUserById(uid);
    		  	
    		  	session.setAttribute("showUser", user);
    		   	session.setAttribute("showTopic", topic);//帖子
    		   }
    	 }
    	 	 System.out.println(  request.getParameter("topicid"));	
     }
  /*   if("1".equals(request.getParameter("op"))){
    	 //回复帖
         ReplyBiz rb  = new ReplyBizImpl();
           
         	PageBean pageBean=  new  PageBean();
         	pageBean.setPagesize(1);
          	int  tid =  Integer.parseInt( request.getParameter("topicid"));
          	session.setAttribute("tid", tid);
        	List<Reply>rblist =  rb.selectLimit(pageBean);
        	
        	session.setAttribute("page", pageBean);
        	
        	request.setAttribute("rblist", rblist);
     	}*/	
     
    }
   
   request.getRequestDispatcher("/PageServelt").forward(request, response);
%>