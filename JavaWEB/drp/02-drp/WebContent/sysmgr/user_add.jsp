<%@page import="com.yc.drp.bean.User"%>
<%@page import="com.yc.drp.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String command = request.getParameter("command");
	String userId="";
	String username="";
	String contactTel="";
	String email="";
	if("add".equals(command)){
	
		if(UserManager.getInstance().findUserById(request.getParameter("userId"))==null){
			User user = new User();
			user.setUserId(request.getParameter("userId"));
		//	request.setCharacterEncoding("UTF-8");
		//	System.out.println(new String( request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8"));
			user.setUserName(new String( request.getParameter("userName").getBytes("ISO-8859-1"),"UTF-8"));
			user.setPassword(request.getParameter("password"));
			user.setContactTel(request.getParameter("contactTel"));
			user.setEmail(request.getParameter("email"));
			
			UserManager.getInstance().addUser(user);
			out.println("添加用户成功！");
		}else{

			userId = request.getParameter("userId");
			username = request.getParameter("userName");
			contactTel = request.getParameter("contactTel");
			email = request.getParameter("email");
			out.println("用户代码已经存在，代码=【" + request.getParameter("userId") + "】");		
		
		}
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
		<title>添加用户</title>
		<link rel="stylesheet" href="../style/drp.css">
		<script src="../script/client_validate.js"></script>
		<script type="text/javascript">
	function goBack() {
		window.self.location="user_maint.html"
	}
	
	function addUser() {
		var userIdField = document.getElementById("userId");
		//用户名不能为空
		if(trim(userIdField.value)==""){
			alert("用户代码不能为空");
			userIdField.focus();//赋予焦点
			return;
		}
		//密码至少四个字符
		if(trim(userIdField.value).length<4){
			alert("用户代码至少4个字符！");
			userIdField.focus();
			return;
		}
		//第一个字符必须是字母
		if (!(trim(userIdField.value).charAt(0) >='a' && trim(userIdField.value).charAt(0) <='z')) {
			alert("用户代码首字符必须为字母！");
			userIdField.focus();
			return;
		}
		
		var re = new RegExp(/^[A-z0-9]{4,6}$/);
		if(!re.test(trim(userIdField.value))){
			alert("用户代码必须为数字或字母,只能为4~6位！");
			userIdField.focus();
			return;
		}
		//密码至少6位
		if (trim(document.getElementById("password").value).length < 6) {
			alert("密码至少6位！");
			document.getElementById("password").focus();
			return;
		}
		//如果联系电话不为空，进行判断，判断规则：都为数字，采用两种方式：1、采用正则，2、不采用正则（中午作业）
		var contactTelField = document.getElementById("contactTel");
		if (trim(contactTelField.value) != "") { 
			//采用正则
			re.compile(/^[0-9]*$/);
			if (!re.test(trim(contactTelField.value))) {
				alert("电话号码不合法！");
				contactTelField.focus();
				return;
			}	
		}
	

	//如果emial不能空，进行判断，判断规则：只要包含@即可，@最好不再最前面和最后面（中午作业）
		var emailField = document.getElementById("email");
		if (trim(emailField.value).length != 0) {
			var emailValue = trim(emailField.value);
			if ((emailValue.indexOf("@") == 0) || (emailValue.indexOf("@") == (emailValue.length - 1))) {
				alert("email地址不正确！");
				emailField.focus();
				return;				
			}
			if (emailValue.indexOf("@") < 0) {
				alert("email地址不正确！");
				emailField.focus();
				return;				
			}
		}
		
		
		/*
		document.getElementById("userForm").action="user_add.jsp";
		document.getElementById("userForm").method="post";
		document.getElementById("userForm").submit();
		*/
		
		//等同上面的写法
		with (document.getElementById("userForm")) {
			action="user_add.jsp";
			method="post";
			submit();
		}
	}
	function init() {
		var userIdField = 	document.getElementById("userId");
	}
</script>
	</head>

	<body class="body1" onload="init()">
		<form name="userForm" target="_self" id="userForm">
		<input type="hidden" name="command" value="add" >
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>&nbsp;
							
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="../images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>系统管理&gt;&gt;用户维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="22%" height="29">
							<div align="right">
								<font color="#FF0000">*</font>用户代码:&nbsp;
							</div>
						</td>
						<td width="78%">
							<input name="userId" type="text" class="text1" id="userId"
								size="10" maxlength="10">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>用户名称:&nbsp;
							</div>
						</td>
						<td>
							<input name="userName" type="text" class="text1" id="userName"
								size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								<font color="#FF0000">*</font>密码:&nbsp;
							</div>
						</td>
						<td>
							<label>
								<input name="password" type="password" class="text1"
									id="password" size="20" maxlength="20">
							</label>
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								联系电话:&nbsp;
							</div>
						</td>
						<td>
							<input name="contactTel" type="text" class="text1"
								id="contactTel" size="20" maxlength="20">
						</td>
					</tr>
					<tr>
						<td height="26">
							<div align="right">
								email:&nbsp;
							</div>
						</td>
						<td>
							<input name="email" type="text" class="text1" id="email"
								size="20" maxlength="20">
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="添加" onClick="addUser()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onClick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
