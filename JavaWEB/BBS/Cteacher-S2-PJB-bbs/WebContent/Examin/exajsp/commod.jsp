<%@page import="com.yc.examin.bean.TblScore"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.google.gson.JsonParser"%>

<%@page import="com.google.gson.JsonElement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.stream.JsonToken"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page import="com.yc.examin.biz.impl.TblScoreBizImpl"%>
<%@page import="com.yc.examin.biz.TblScoreBiz"%>

<%@page import="com.yc.examin.biz.impl.LessonBizeImp"%>
<%@page import="com.yc.examin.biz.LessonBiz"%>
<%@page import="com.yc.examin.bean.Question"%>
<%@page import="com.yc.util.RequestUtils"%>
<%@page import="com.yc.examin.biz.impl.ScoreBizImpl"%>
<%@page import="com.yc.examin.biz.ScoreBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  		ScoreBiz sbiz = new ScoreBizImpl();
      	String op = request.getParameter("op");
      	request.setCharacterEncoding("UTF-8");
      	if("tabl_score".equals(op)){ //记录分数并且记录刷题记录
      		TblScoreBiz brbiz = new TblScoreBizImpl();
     // 	BsRecordBiz brbiz = new BsRecordBizImpl();	
	 	
          String json =   request.getParameter("objarr");
          System.out.println(json);
          List<TblScore> tblScoreList=new ArrayList();
          Gson gson = new Gson();
          List<JsonElement> list=new ArrayList();
          JsonParser jsonParser=new JsonParser();
          JsonElement jsonElement=jsonParser.parse(json);  //将json字符串转换成JsonElement
         	JsonArray jsonArray =   jsonElement.getAsJsonArray();
         	Iterator it =    jsonArray.iterator();
      	   while(it.hasNext()){
      		  	jsonElement =  (JsonElement)it.next();//提取JsonElement
      			json =  jsonElement.toString(); //JsonElement转换成String
      			TblScore tb = gson.fromJson(json, TblScore.class);
      			int uid = tb.getUid();
      			tblScoreList.add(tb);
      	   }
      	   
      		 brbiz.addRecord(tblScoreList);
      		 //统计错题数和完成的时间
      		 
      	}
      	
        if("addKaoti".equals(op)){
        	LessonBiz lbiz = new LessonBizeImp();
     	   RequestUtils rutil  = new RequestUtils();
     	  request.setCharacterEncoding("UTF-8");
     	
     	
     	  Question qs =  rutil.parseRequest(request,Question.class);
     	   String type =  qs.getType();
     	   
     	  if("duoxuan".equals(type)){
     	    	qs.setType("多选题");
     	    }else{
     	    	qs.setType("单选题");
     	    }
     	  
     	   lbiz.addQuestion(qs);
     	   response.sendRedirect("../back/addkaoti.jsp");
     	 //  request.getRequestDispatcher("../addkaoti.jsp").forward(request, response);
         
        }

%>