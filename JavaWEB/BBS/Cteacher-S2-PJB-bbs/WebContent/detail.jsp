<%@page import="com.yc.bbs.bean.Reply"%>
<%@page import="com.yc.bbs.bean.Topic"%>
<%@page import="com.yc.bbs.bean.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.hyycinfo.com/taglib/c"  prefix="jiangbin"%>

   <%@include file="header.jsp" %>
   <%@include file="userisLogin.jsp" %>
   <div>
   <script type="text/javascript">
   
 //form表单提交
	function mysubmit(input){
	  var value =  input.value;
	  var pageNo= $('#currentpage').text();
	  var spageNo =  $('#specifiedPage').val();
	  var totalpage =   $("#totalpage").text();
	  if($('#searchInput').val()==""){
		  $('#searchHidden').val("");
	  }else{
		  $('#searchHidden').val($('#searchInput').val());
			 
	  }
	   if(value=='上一页'){
		 
		   if(pageNo>1){
			   pageNo--; 
		   }
		  
		   $("#pageNo").val(pageNo);
		   $('#currentpage').text( pageNo);
		   $('#form').submit();
	   }else if(value=='下一页'){
		  
		   var total =  $('#totalpage').text();
		
		   if(pageNo<total){
			   pageNo++;
		   }
		  
		 
		   $("#pageNo").val(pageNo);
		   $('#currentpage').text( pageNo);
		   $('#form').submit();
	   }else if(value=='首页'){
		
		   $("#pageNo").val(1);
		   $('#form').submit();
	   }else if(value=='尾页'){
		  $('#op').val('paging');
		  $("#pageNo").val(totalpage);
		  $('#form').submit();
	   }else if(value=='跳转'){
		  
		   $("#pageNo").val(spageNo);
		   $('#form').submit();
	   }else if(value=='搜索'){
		 
		   $('#currentpage').text( pageNo);
		
		   $('#form').submit();
	   }
	}
 
     function deleteReply(rid){
    	  $.ajax({
    		   url:"dorply.jsp",
    		   data:{"rid":rid},
    		   dataType: 'json',
    		   success:function(data){
    			   alert(data.code);
    			 if(data.code>0){
    				 alert("#t"+rid);
    				$("#t"+rid).remove();
    			
    			 }
    			  
    		   }
    	  });
     }
	
   </script>
     <b><a href="index.jsp">论坛首页</a></b>
     <b>
      
       <a href="doList.jsp?bid=<%= ((Board)session.getAttribute("board")).getBoardid() %>"><%=((Board)session.getAttribute("board")).getBoardname() %></a>
     </b>
   </div>
   <div>
     <a href="javascript:void(0)" onclick="doReply()" >
       <img src="image/reply.gif"/>
     </a>
   
   </div>
   <!--         翻 页         -->
	
	
	<%
		  request.getParameter("topicid");
		
	
	  	Topic t =  (Topic)session.getAttribute("showTopic");
		List <Reply> rblist = 	(List)request.getAttribute("rblist");
	%>
	
		<DIV>
				<TABLE cellSpacing="0" cellPadding="0" width="100%">
					<TR>
						<TH class="h">
							本页主题:
							<%=t.getTitle()%>
						</TH>
					</TR>
					<TR class="tr2">
						<TD>
							&nbsp;
						</TD>
					</TR>
				</TABLE>
			</DIV>
		<DIV class="t">
				<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
					cellSpacing="0" cellPadding="0" width="100%">
					<TR class="tr1">
						<TH style="WIDTH: 20%">
							<B><%=t.getUname()%></B>
							<BR />
							<image src="image/head/<%=t.getHead() %>" />
							<BR />
							注册:<%=((User) session.getAttribute("showUser")).getRegtime()%>
							<BR />
						</TH>
						<TH>
							<H4>
								<%=t.getTitle()%>
							</H4>
							<DIV>
								<%=t.getContent()%>
							</DIV>
						<%-- <%//for(int i=0;i<rblist.size();i++){
							 //String time = 	 rblist.get(i).getPublishtime();
							 //String content =  rblist.get(i).getContent();
							 //String text = "【回复消息】:"+content+"&nbsp;&nbsp;&nbsp;"+time;
								%>
							<!--	<div>-->
								   <%-- <%=text %> --%>
							<!--	</div>-->
								
								<% 
								
							//}%>	
							<DIV class="tipad gray">
								发表：[<%=t.getPublishtime()%>] &nbsp; 最后修改:[<%=t.getModifytime()%>]
								<%
							    if(flag==false){
							    	return;
							    }
							//当前主题的发表的用户
							User topicuser = (User) session.getAttribute("showUser");
							
								if (loginuser.getUid()==(topicuser.getUid())) {
							%>
								<A href="#">[删除]</A>
								<A href="#">[修改]</A> 
								<%
							}
								%>
								
							<!-- 	<a href="javascript:void(0);" uid='<%=t.getUid() %>' topicid="<%=t.getTopicid() %>"  onclick="reply(this)">回复</a> -->
							</DIV>
						</TH>
					</TR>
				</TABLE>
			</DIV>
	
			
			<c:if test="${ not empty rblist }">
			      <c:forEach var="obj" items="${rblist }" varStatus="status">
			      
			    <DIV class="t" id="t${obj.getReplyid() }">
				<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed"
					cellSpacing="0" cellPadding="0" width="100%">
					<TR class="tr1">
						<TH style="WIDTH: 20%">
							<B>${obj.getUname()}</B>
							<BR />
							<BR />
							<image src="image/head/${obj.getHead() }" />
							<BR />
							注册:${obj.getRegtime()}
							<BR />
						</TH>
						<TH>
							<H4>
							${obj.getTitle()} ;
							</H4>
							<DIV>
							${obj.getContent() }
							</DIV>
							<DIV class="tipad gray" >
							
								发表：[${obj.getPublishtime() }] &nbsp; 最后修改:[${obj.getModifytime() }]
								
								<c:if test="${obj.getUid()== user.getUid() }">
							
									
									<A href="javascript:void(0)" onclick="deleteReply(${obj.getReplyid() })">[删除]</A> 
									
								 	
								</c:if>
								
							</DIV>
						</TH>
					</TR>
				</TABLE>
			</DIV>
			      
			      </c:forEach>
			
			</c:if>
		  <div style="width:600px;margin:0 auto;">
		   
		  <form id="form" action="PageServelt" method="get" enctype="multipart/form-data" >
  		    <input  type="hidden" name="topicid" value="<%=request.getParameter("topicid") %>">
  			 <jiangbin:pageBar page="${page}"></jiangbin:pageBar>
  		
  		</form>
		  </div>	
		<%@include file="footer.jsp" %>	
</body>
</html>