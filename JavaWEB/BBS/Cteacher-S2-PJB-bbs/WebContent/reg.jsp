<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<TITLE>论坛--注册</TITLE>

		

		<Link rel="stylesheet" type="text/css" href="style/style.css" />
	
		
		
		
		
		
	</head>

	<body>
		
		
	
		
		
		
		





<BR/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户注册表单        -->
<DIV  class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="regForm" action="doReg.jsp" method="post">
		<br/>用&nbsp;户&nbsp;名 &nbsp;
			<INPUT class="input" tabIndex="1" tryp="text" maxLength="20" size="35" name="uname" onblur="checkuname( this.value)">  <div id="userinfoid" style="display:none"></div>
		<br/>密&nbsp;&nbsp;&nbsp;&nbsp;码 &nbsp;
			<INPUT class="input" tabIndex="2" type="password" maxLength="20" size="40" name="upass">
		<br/>重复密码 &nbsp;
			<INPUT class="input" tabIndex="3" type="password" maxLength="20" size="40" name="upass1">
			
			
			
		<br />验证码:<input type="text" name="code" /> <img id="showcode" src="code.jsp"  /> <a href="javascript:showCodeAgain()">看不清,请换一张</a>
		
		
		<br/>性别 &nbsp;
			女<input type="radio" name="gender" value="1">
			男<input type="radio" name="gender" value="2" checked="checked" />
		<br/>请选择头像 <br/>
		<image src="image/head/1.gif"/><input type="radio" name="head" value="1.gif" checked="checked">
		<img src="image/head/2.gif"/><input type="radio" name="head" value="2.gif">
		<img src="image/head/3.gif"/><input type="radio" name="head" value="3.gif">
		<img src="image/head/4.gif"/><input type="radio" name="head" value="4.gif">
		<img src="image/head/5.gif"/><input type="radio" name="head" value="5.gif">
		<BR/>
		<img src="image/head/6.gif"/><input type="radio" name="head" value="6.gif">
		<img src="image/head/7.gif"/><input type="radio" name="head" value="7.gif">
		<img src="image/head/8.gif"/><input type="radio" name="head" value="8.gif">
		<img src="image/head/9.gif"/><input type="radio" name="head" value="9.gif">
		<img src="image/head/10.gif"/><input type="radio" name="head" value="10.gif">
		<BR/>
		<img src="image/head/11.gif"/><input type="radio" name="head" value="11.gif">
		<img src="image/head/12.gif"/><input type="radio" name="head" value="12.gif">
		<img src="image/head/13.gif"/><input type="radio" name="head" value="13.gif">
		<img src="image/head/14.gif"/><input type="radio" name="head" value="14.gif">
		<img src="image/head/15.gif"/><input type="radio" name="head" value="15.gif">
		<br/>
			<INPUT class="btn" tabIndex="4" type="submit" value="注 册">
	</FORM>
</DIV>

<%@include file="footer.jsp" %>


   <script>
   	    function showCodeAgain(){
   	   	    var r=new Date();
			var img=document.getElementById("showcode");
			img.src="code.jsp?d="+r;
   	   	}
   </script>

	</body>
</html>



<script type="text/javascript">
<!--
	function checkuname(  uname  ){
		if(   uname==null||uname==''){
			$("userinfoid").innerHTML="<font color='red'>用户名不能为空</font>";
			$("userinfoid").style.display='inline';
			return;
		}
		startRequestByPost('checkuname.jsp','uname='+uname, ckName);
	}


	//异步处理函数：当服务器给客户端响应时，会自动调用这个函数做处理. 
	function ckName(){
		//首先要判断状态
		//服务器端的就绪状态
		 if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
		 	//http响应码
     		if (xmlHttp.status == 200) { // 如果伺服端回應OK
          		//alert("服务器端的响应" + xmlHttp.responseText);  // 這邊只取得回應文字
          		if(  xmlHttp.responseText==1){
          			$("userinfoid").innerHTML="<font color='red'>用户名已经存在,请重新命名</font>";
          			$("userinfoid").style.display='inline';
          		}else{
          			$("userinfoid").style.display='none';
          		}
          		
     		}
   		}	
	}
//-->
</script>
