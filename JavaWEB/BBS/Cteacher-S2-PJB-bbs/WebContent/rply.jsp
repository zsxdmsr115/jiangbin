<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="header.jsp" %>
   <!-- 导航 -->
    &gt;&gt;<b><a href="index.jsp">论坛首页</a></b>&gt;&gt;
   <div>
     <%
      request.setCharacterEncoding("utf-8");
   	  Board board =(Board) session.getAttribute("board");
   	  User user = (User) session.getAttribute("user");
      int tid= (Integer)session.getAttribute("tid");
         
        
     %>
     <B><a href="list.jsp?bid=<%=board.getBoardid() %>"><%=board.getBoardname() %></a></B>
   </div>
 <DIV>
		<FORM name="postForm" onSubmit="return check()" action="addRply.jsp" method="POST"> 
			<INPUT type="hidden" name="boardid" value="<%=board.getBoardid()  %>"/>
			
			<DIV class="t">
				<TABLE cellSpacing="0" cellPadding="0" align="center" >
				    <TR>
					    <TD class="h" colSpan="3" align="center"><B>回帖</B></TD>
				    </TR>
	
				    <TR class="tr3">
					    <TH width="20%"><B>标题</B></TH>
					    <TH><INPUT class="input" id="title" style="PADDING-LEFT: 2px; FONT: 14px Tahoma" tabIndex="1" size="60" name="title"></TH>
				    	<input type="hidden" name="topicid" value="<%=tid %>"/>
				    	<input type="hidden" name="uid" value="<%=user.getUid() %>"/>
				    </TR>
	
				    <TR class="tr3">
					    <TH vAlign=top>
					      <DIV><B>内容</B></DIV>
					    </TH>
					    <TH colSpan=2>
					        <DIV>	
						        <span><textarea id="content"  class="content" style="WIDTH: 500px;" name="content" rows="20" cols="90" tabIndex="2" ></textarea></span>
						   
						    </DIV>
					      (不能大于:<FONT color="blue">1000</FONT>字)
					    </TH>
					</TR>
				</TABLE>
			</DIV>		
	
			<DIV style="MARGIN: 15px 0px; TEXT-ALIGN: center">
				<INPUT class="btn" tabIndex="3" type="submit" value="提 交"> 
				<INPUT class="btn" tabIndex="4" type="reset"  value="重 置">
			</DIV>
		</FORM>	
	</DIV>
</DIV>
  
<%@include file="footer.jsp" %>
</body>
</html>