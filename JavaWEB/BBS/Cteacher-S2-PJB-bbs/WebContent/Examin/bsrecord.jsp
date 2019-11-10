<%@page import="com.yc.bbs.bean.User"%>
<%@page import="com.yc.examin.bean.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
User user =(User) session.getAttribute("user");
 
%>

<!DOCTYPE html PUBLIC>

<html lang="zh-CN">
<head>
<meta charset="UTF-8">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/ajaxCommond.js">


<script type="text/javascript">
      $(function(){  
        $(".list_table").colResizable({
          liveDrag:true,
          gripInnerHtml:"<div class='grip'></div>", 
          draggingClass:"dragging", 
          minWidth:30
        }); 
        
      }); 
   </script>

<style>
*{
       font-family:'微软雅黑';
       font-size:14px;
    }
   .container{
     width:100%;
     height:100%;
   }
   .content{
       background:lightgrey;
       margin:20 auto;
       width:80%;
       
       height:80%;
       border:1px solid grey;
       border-radius:20px;
   }
   .stList{
     width:100%;
     height:100%;
     margin:10px auto;
   }
   .paging{
      width:400px;
      margin:0 auto;
   }
   li:hover{
   }
   ul li{
      width:20%;
      height:40%;
      background:white;
      display:block;
      float: left;
      margin-left:3%; 
      margin-top:5px;
      border-radius:20px;  
    
   }
 
   .title{
      margin-top:20px;
      height:30px;
      line-height:30px;
      background:yellow;
      text-align:center;
   }

</style>
</head>
<body>
	
	<div class="container">
	    <div id="box">
	    
	    <div class="content">
          <ul>
           <c:if test="${not empty rblist }">
             <c:forEach  var="obj" items="${rblist }">
              <li>
             <p class="title">${obj.lessonname }</p>
             <p>完成时间:${obj.completime }</p>
             <p>正确数：${obj.correct }</p>
             <p>错误数：${obj.error }&nbsp;&nbsp;&nbsp;<a href="details.jsp" >查看详情</a></p>
            </li>
             </c:forEach>
           </c:if>
          
          </ul>
        
        </div>
	     <div class="paging">
	        <input type="button"  value="上一页" src="images/btn.png"/>
	        <span>当前第1页/ 共1页</span>
	        <select>
	           <option>一周内</option>
	        </select>
	         <input type="button"  value="下一页" src="images/btn.png"/>
	    </div>
	     </div>
	</div>
	<script type="text/javascript" src="js/ajaxCommond.js"></script>
</body>
</html>
