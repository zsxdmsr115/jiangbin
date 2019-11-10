<%@page import="com.google.gson.Gson"%>
<%@page import="com.yc.bbs.bean.JsonModel"%>
<%@page import="com.yc.examin.bean.Question"%>
<%@page import="java.util.List"%>
<%@page import="com.yc.examin.biz.impl.LessonBizeImp"%>
<%@page import="com.yc.examin.biz.LessonBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

  String op = request.getParameter("op");
  JsonModel jm = new JsonModel();
   if("lesson".equals(op)){
	  int id = Integer.parseInt( request.getParameter("id"));
	    LessonBiz lbiz = new LessonBizeImp();
	   
	   List<Question> questionList = lbiz.getQuestionById(id);
	   System.out.println(questionList.get(0));
	   if(questionList!=null && questionList.size()>0){
		   jm.setCode(1);
		   jm.setObj(questionList);
		   Gson gson = new Gson();
		 	String json =   gson.toJson(jm);
		 	session.setAttribute("questionList", questionList);
		   out.write(json);
	   }else{
		   jm.setCode(0);
		   Gson gson = new Gson();
		   String json =   gson.toJson(jm);
			
		 out.write(json);
	   }
   }


%>