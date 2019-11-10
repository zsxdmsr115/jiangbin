<%@page import="com.yc.bbs.biz.impl.TopicImpl"%>
<%@page import="com.yc.bbs.biz.TopicBiz"%>
<%@page import="com.yc.bbs.bean.Board"%>
<%@page import="com.yc.bbs.bean.User"%>
<%@page import="com.yc.bbs.bean.Topic"%>
<%@page import="com.yc.util.RequestUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
 	String path = request.getContextPath();
	
 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	System.out.println(basePath);
%>
<%
	RequestUtils ru = new RequestUtils();
	Topic  topic =  ru.parseRequest(request, Topic.class);

	TopicBiz tb = new TopicImpl();
	
	//取出用户
	User u = (User)session.getAttribute("user");
	if(u!=null){  
		
    	topic.setUid(u.getUid());
    	tb.addTopic(topic);
    	Board board =(Board) session.getAttribute("board");
    	response.sendRedirect("doList.jsp?bid="+board.getBoardid());
    }else{
    	%>
    	 <script type="text/javascript">
          
          window.location.href="dopost.jsp";
		</script>
    	<%
    }
%>