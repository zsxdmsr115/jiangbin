<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.yc.bbs.biz.impl.UserInfoImpl"%>
<%@page import="com.yc.bbs.biz.UserInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   //获取验证码
     String inputCode =  request.getParameter("code");
    
    String sessionCode =(String) session.getAttribute("rCode");
 
   
    String uname = request.getParameter("uname");
    String upass = request.getParameter("upass");
    Map map = new HashMap();
    map.put("uname",uname);
    map.put("upass",upass); //密码abc
   
    if(inputCode.equals(sessionCode)){
    	UserInfo userInfo = new UserInfoImpl();  
    	List u =   userInfo.findUsers(map);
         
    	  if(u!=null&&u.size()>0){
    		 session.setAttribute("user", u.get(0));
    		
    		out.write("1");
    	  }else {
    		  out.write("2");
				/*out
				.println("<script>alert('用户名或密码不正确');location.href='login.jsp';</script>");*/
	}
    	
    }else{
    	out.write("3");
    	/*out.println("<script> alert('验证码不正确');location.href='login.jsp';</script>");*/
    }
  
%>