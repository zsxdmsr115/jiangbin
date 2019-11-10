
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
		  
		
<BR/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	
		<br/>用户名 &nbsp;<INPUT id="username" class="input" tabIndex="1"  type="text"      maxLength="20" size="35" name="uname">
		<br/>密　码 &nbsp;<INPUT id="password" class="input" tabIndex="2"  type="password"  maxLength="20" size="40" name="upass">
		<br />验证码:<input id="code" type="text" name="code" /> <img id="showcode" src="code.jsp"  /> <a href="javascript:showCodeAgain()">看不清,请换一张</a>
		<br/><button onclick="login()">登录</button>
	
</DIV>


	<%@include file="footer.jsp" %>
	</body>
</html>


   <script>
   	    function showCodeAgain(){
   	   	    var r=new Date();
			var img=document.getElementById("showcode");
			img.src="code.jsp?d="+r;
   	   	}
   </script>
