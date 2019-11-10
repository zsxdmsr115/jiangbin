<%@page import="java.io.PrintWriter"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bbs.bean.JsonModel"%>
<%@page import="com.yc.bbs.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     
	User loginuser = (User) session.getAttribute("user");

    String op =  request.getParameter("op");
  
    String bid= request.getParameter("bid");
    JsonModel jm = new JsonModel();
    boolean flag = true;
	 if("publish".equals(op)){
		
		jm.setObj(bid);
		Gson gson = new Gson();
	   
	  //  PrintWriter p= response.getWriter();
	    if(loginuser==null){
			jm.setCode(0);
			
		}else{
			jm.setCode(1);
			System.out.print("code======1");
			//System.out.print("loginuser======"+loginuser);
		}
	    String json = gson.toJson(jm);
	    out.print(json);
		
}
	 
	if("examin".equals(op)){
		
		Gson gson = new Gson();
	 
	    if(loginuser==null){
			jm.setCode(0);
		
		}else{
			jm.setObj(loginuser.getUid());
			jm.setCode(1);
			
			//System.out.print("loginuser======"+loginuser);
		}
	    String json = gson.toJson(jm);
	    out.print(json);
}
  if(loginuser==null){
	  flag=false;
  } 
	
	
	
	
	
	
	//out.write(json);
	//response.sendRedirect("detail.jsp");

	
%>