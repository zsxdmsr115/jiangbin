<%@page import="com.yc.bbs.bean.Board"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.bbs.bean.Topic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



  
  <body>
	  <%@include file="header.jsp" %>
	
	  <div>
	   &gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
	    <%
	       int bid = 0;
		//版块
	    Board baoBoard = (Board) session.getAttribute("board");
	      if(baoBoard!=null){
	    	  bid= baoBoard.getBoardid();
	      }
	      int pageSize = 0;
	      if(request.getAttribute("pageSize")!=null){ //每个页面的记录数
	    	  pageSize=(Integer)request.getAttribute("pageSize");
	      }
	       
	      int topicCount = 0;
	      if(request.getAttribute("topicCount")!=null){ //总记录数
	    	  topicCount=(Integer)request.getAttribute("topicCount");
	      }
	      int pageNo=0;
	      if(request.getAttribute("pageNo")!=null){
	    	  pageNo=(Integer)request.getAttribute("pageNo");
	      }
	      
	      int countpage=0;
	      if(request.getAttribute("countpage")!=null){
	    	  countpage=(Integer)request.getAttribute("countpage");
	      }
	      int prevpageNo = pageNo>1?pageNo-1:pageNo;
	      int nextpageNo= pageNo<countpage?pageNo+1 : pageNo;
	    %>
	  <B><a href="list.jsp?bid=<%=bid %>"><%=((Board)session.getAttribute("board")).getBoardname() %></a></B>
	  
	   
	  </div>
	  <!-- 新帖 -->
	  <div>
	  	<button bid="<%=bid %>" onclick="publish(this)"> <IMG src="image/post.gif" name="td_post" border="0" id=td_post></button>
	   <!--   <b><a href="#" bid="<%=bid %>" onclick="publish()"> <IMG src="image/post.gif" name="td_post" border="0" id=td_post></a></b>
	 	--> 
	 	<form action="doList.jsp?bid=<%=bid %>> method="get">
	 	<input type="text"   id="search" name="title">  
	 	   <input type="hidden" name="op" value="search"/> 
	 	   <input type="hidden" name="bid" value="<%=((Board) session.getAttribute("board")).getBoardid() %>"/>
	 	   <input type="submit" value="搜索帖子" />
	 	</form>
	 
	 	
	<!--   <a href="javascript:void(0)" onclick="Examination(this)">在线考试</a>-->
	 
	  </div>
	  <div>
	  <!-- 翻页 -->
	  	每页<%=pageSize %>条 ,总共有 <%=topicCount %>条记录 ,总共有<%=countpage %>页，当前第<%=pageNo %>页
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=1%>">首页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=prevpageNo%>">上一页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=nextpageNo%>">下一页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=countpage%>">尾页</a>
	  </div>
    <div class="t">
      <table cellSpacing="0" cellPadding="0" width="100%">
      	<TR>
				<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
			</TR>
<!--       表 头           -->
			<TR class="tr2">
				<TD>&nbsp;</TD>
				<TD style="WIDTH: 80%" align="center">文章</TD>
				<TD style="WIDTH: 10%" align="center">作者</TD>
				<TD style="WIDTH: 10%" align="center">回复</TD>
			</TR>
			<%
			  List<Topic>  toplist = (List<Topic> ) session.getAttribute("topicList");
				 for(Topic t : toplist){
					 %>
					<tr class="tr3">
					<td><img src="image/topic.gif" /></td>
					
					  <td ><a href="doDetails.jsp?topicid=<%=t.getTopicid()%>&op=1" ><%=t.getTitle() %></a> </td>
					  <td align="center"><%=t.getUname() %> </td>
					  <td align="center"><%=t.getReplcount() %> </td>
					</tr> 
					 <%
				 }
			%>
      
      </table>
    
    </div>
   <div>
	  <!-- 翻页 -->
	  	每页<%=pageSize %>条 ,总共有 <%=topicCount %>条记录 ,总共有<%=countpage %>页，当前第<%=pageNo %>页
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=1%>">首页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=prevpageNo%>">上一页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=nextpageNo%>">下一页</a>
	      <a href="doList.jsp?bid=<%=((Board) session.getAttribute("board")).getBoardid() %>&pageNo=<%=countpage%>">尾页</a>
	  </div>
   <%@include file="footer.jsp" %>
 </body>