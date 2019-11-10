<%@page import="com.yc.bbs.biz.impl.UserInfoImpl"%>
<%@page import="com.yc.bbs.biz.UserInfo"%>
<%@page import="com.yc.bbs.bean.User"%>
<%@page import="com.yc.util.RequestUtils"%>
<%@ page language="java"
	
	pageEncoding="UTF-8"%>
<%
	//获
	RequestUtils ru = new RequestUtils();
	User user = ru.parseRequest(request, User.class);
	String uPass1 = request.getParameter("upass1"); //  User类中没有这个属性，所以要单独获取..

	String code = request.getParameter("code");
	String rCode = (String) session.getAttribute("rCode");

	if (rCode.equals(code)) {
		// System.out.println(   user+"\t"+ uPass1 );
		if (uPass1 == null || !uPass1.equals(user.getUpass())) {
			out
					.println("<script>alert('两次密码输入不正确');location.href='reg.jsp';</script>");
		} else {

			UserInfo ub = new UserInfoImpl();

			try {
				User u = ub.reg(user);
				if (u != null) {
					response.sendRedirect("login.jsp");
				} else {
					out
							.println("<script>alert('用户名或密码不正确');location.href='reg.jsp';</script>");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				out.println("<script>alert('" + ex.getMessage()
						+ " ');location.href='reg.jsp';</script>");
			}

		}
	} else {

		out
				.println("<script>alert('验证码错误，请重新输入');location.href='reg.jsp';</script>");
	}
%>