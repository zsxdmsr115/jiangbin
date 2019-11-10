<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.yc.bbs.bean.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<%@ include file="doIndex.jsp" %>
	<div class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr2" align="center">
				<TD colSpan="2">论坛</TD>
				<TD style="WIDTH: 10%;">主题</TD>

				<TD style="WIDTH: 30%">最后发表</TD>
			</TR>
			<!-- 主版块 -->
			<%
			
			
			
		 	List <Board> fatherBoard =null; 
			//获取所有的父版块
			if(boardMap!=null){
				fatherBoard=boardMap.get(0);
			}
		 
			 if(fatherBoard!=null && fatherBoard.size()>0){
				 for( Board fb : fatherBoard){
				%>
				 <tr class="tr3">
				   <td colspan="4">
				     <%=fb.getBoardname() %>
				   </td>
				 </tr>
				 <!-- 子板块 -->
				<%
				 List<Board> sonList = boardMap.get(fb.getBoardid());
				  if(sonList!=null){
					  for(Board sb : sonList){
				%>
				 <tr class="tr3">
				   <td width="5%">
				      &nbsp;
				   </td>
				   <th align="left">
				     <img src="image/board.gif"/>
				     <a href="doList.jsp?bid=<%=sb.getBoardid() %>"> <%=sb.getBoardname() %></a>
				   </th>
				   <td align="center">
				    <!-- 帖子数 -->
				     <%=sb.getTotal() %>
				   </td>
				   <th>
				     <!-- 帖子标题 -->
				     <%
				        
				       if(sb.getTitle()==null){
				    %>
				    	<h3><a href="#">这个版块暂无帖子</a></h3>
				    <% 
				    	   
				       }else{
				    	 %>
				    	   <a href="#">这个板块最新帖子的标题:</a> &nbsp;&nbsp; 
				    	  <span><a href="#"><%=sb.getTitle() %></a></span><br/>
						   <a href="#">帖子的作者是:</a>&nbsp;&nbsp; 
						   <span><%=sb.getUname() %></span><br/>
						   <span>帖子最后修改的时间：<%=sb.getModifytime() %></span>&nbsp;&nbsp; 
						   <span></span>
				    	 <%   
				    	   
				       }
				     
				     %>
				   </th>
				 </tr>		  
				<% 
					  }
				  }
				%>
				
				<% 
				 }
			 } 
			 
			%>
		</TABLE>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>