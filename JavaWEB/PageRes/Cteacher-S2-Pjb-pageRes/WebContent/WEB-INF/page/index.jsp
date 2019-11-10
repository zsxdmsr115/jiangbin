<%@page import="com.yc.bean.Page"%>
<%@page import="com.yc.bean.Resfood"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="jiangbin"  uri="http://www.hyycinfo.com/taglib/c" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    		Page p =	(Page)session.getAttribute("page");
  		   	List<Resfood> listFood =p.getList();
      		if(listFood==null || listFood.size()==0){
      			response.sendRedirect("index.jsp");
      		}
			//Page p = new Page();
		 	
			  
			try{
				if(session.getAttribute("pageTotal")!=null &&request.getAttribute("pageNo")!=null){
					
					p.setTotalPage(Integer.parseInt(session.getAttribute("pageTotal")+""));
					p.setPageNo(Integer.parseInt( request.getAttribute("pageNo")+""));
				}
			}catch(Exception e){
				e.printStackTrace();
				response.sendRedirect("index.jsp");
				
			}
			
  	%>
  		
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#big{
		padding:0 auto;
		width:90%;
		border:1px solid red;
		height:98%;
	}
	#fav{
		float:left;
		width:20%;
		border: 1px solid yellow;
	}
	#mainDiv{
		float:right;
		width:76%;
		border: 1px solid yellow;
	}
	ul#history{
	 list-style:none;
	}
	ul#history img{
	  width:50px;
	  height:50px;
	}
</style>
	<script type="text/javascript" src="js/jquery.min.js">
	 
	</script>
	<script >
	var arr;
	var pageno=0;
	function search(){
		//alert(1);
		var value = $('search').val();
		var pagesize=1;
	
		 $.ajax(
					{
						type:'POST',
						url:'resfood.action',
						data:'op=search&searchValue='+value,
						dataType:'JSON',
						success:function(result){
							
							if(result.code>0){
								$("#t1").hide();
								$('#box').hide();
								 arr = result.obj;
								
							
							 	$('#mainDiv').append("<table id='t2' width='90%'><tr></tr></table>");
							   var tab = $("#t2 tr");
							   
								for( var i =0;i<pagesize;i++){
									tab.append("<td><dl>"
									+"<dt><img src='images/"+arr[i].fphoto+"' width='100px' height='100px'></dt>"
									+"<dt><a href='resfood.action?op=findbyId&fid="+arr[i].fid+"'>"+arr[i].fname+"</a></dt>"
									+"<dt>原价"+arr[i].normprice+"</dt>"
									+"<dt>跳楼价"+arr[i].realprice+"</dt>"
									+"<dt>访问数"+arr[i].visited+"</dt>"
									+"<dt>点赞数<span id='praiseResult_"+arr[i].fid+"'>"+arr[i].parise+" </span><a href='javascript:void(0)' onclick='praise("+arr[i].fid+")'>赞一个</a></dt>"
									+"</dl></td></tr>");
								}
							
							}
							$('#mainDiv').append("<a href='javascript:void(0)' onclick='next("+arr.length+","+pagesize+")'>下一页</a>");
							$('#mainDiv').append("<a href='javascript:void(0)' onclick='prev("+arr.length+","+pagesize+")'>上一页</a>");
						
						}
					}	  
				)
	}
	function prev(length,pagesize){
		 
		 var totalpage = length%pagesize!=0?length/pagesize+1:length/pagesize;
		  pageno--;
		
		 var td =   $("#t2 td");
		 var tab = $('#t2');
		 console.log( pageno);
		  if(pageno>=0){
			
			 
			 td.remove();
			  
			  for(var i=pageno;i<pageno+pagesize;i++){
				 // console.log(i,arr.length);
				   if(i>=arr.length){
					 break;  
				   }
				  // console.log(arr[i]);
				  tab.append("<td><dl>"
							+"<dt><img src='images/"+arr[i].fphoto+"' width='100px' height='100px'></dt>"
							+"<dt><a href='resfood.action?op=findbyId&fid="+arr[i].fid+"'>"+arr[i].fname+"</a></dt>"
							+"<dt>原价"+arr[i].normprice+"</dt>"
							+"<dt>跳楼价"+arr[i].realprice+"</dt>"
							+"<dt>访问数"+arr[i].visited+"</dt>"
							+"<dt>点赞数<span id='praiseResult_"+arr[i].fid+"'>"+arr[i].parise+" </span><a href='javascript:void(0)' onclick='praise("+arr[i].fid+")'>赞一个</a></dt>"
							+"</dl></td></tr>");
			  }
		  }
		
		  //页面总数
	   
	}
	function next(length,pagesize){
		 
		 var totalpage = length%pagesize!=0?length/pagesize+1:length/pagesize;
		  pageno++;
		
		 var td =   $("#t2 td");
		 var tab = $('#t2');
		  if(pageno<totalpage){
			
			 
			 td.remove();
			  
			  for(var i=pageno;i<pageno+pagesize;i++){
				
				   if(i>=arr.length){
					 break;  
				   }
				   console.log(arr[i]);
				  tab.append("<td><dl>"
							+"<dt><img src='images/"+arr[i].fphoto+"' width='100px' height='100px'></dt>"
							+"<dt><a href='resfood.action?op=findbyId&fid="+arr[i].fid+"'>"+arr[i].fname+"</a></dt>"
							+"<dt>原价"+arr[i].normprice+"</dt>"
							+"<dt>跳楼价"+arr[i].realprice+"</dt>"
							+"<dt>访问数"+arr[i].visited+"</dt>"
							+"<dt>点赞数<span id='praiseResult_"+arr[i].fid+"'>"+arr[i].parise+" </span><a href='javascript:void(0)' onclick='praise("+arr[i].fid+")'>赞一个</a></dt>"
							+"</dl></td></tr>");
			  }
		  }
		
		  //页面总数
	
	}
	 function	praise(fid){
		
		  $.ajax(
			{
				type:'POST',
				url:'resfood.action',
				data:'op=praise&fid='+fid,
				dataType:'JSON',
				success:function(result){
				
					//alert('f服务器结果json'+result.obj+","+result.code);
					//document.getElementById("praiseResult_"+fid).innerHTML=result.obj;
					console.log(document.getElementById("praiseResult_"+fid));
					document.getElementById("praiseResult_"+fid).innerHTML=result.obj;
				}
			}	  
		  )
		 
	  }
	
	 
	 $(function(){
		
		 $.ajax({
			 type:'POST',
				url:'resfood.action',
				data:'op=showHistory',
				dataType:'JSON',
				success:function(result){
				
					//obj应该包含一个集合，存fid ,fphoto
					//document.getElementById("praiseResult_"+fid).innerHTML=result.obj;
					document.getElementById("history").innerHTML='';
					var str="";
					for(var i =0;i<result.obj.length;i++){
						
						console.log(food);
						var food = result.obj[i];
						str+="<li>";
						str+="<a href='resfood.action?op=findbyId&fid="+food.fid+"'><img src='images/"+food.fphoto+"'/></a>"
						str+="</li>";
					}
					document.getElementById("history").innerHTML=str;
				}
			 
		 })
	 });
	 

	//form表单提交
	function mysubmit(input){
	  var value =  input.value;
	  var pageNo= $('#currentpage').text();
	  var spageNo =  $('#specifiedPage').val();
	  var totalpage =   $("#totalpage").text();
	  if($('#searchInput').val()==""){
		  $('#searchHidden').val("");
	  }else{
		  $('#searchHidden').val($('#searchInput').val());
			 
	  }
	   if(value=='上一页'){
		   $('#op').val('paging');
		   if(pageNo>1){
			   pageNo--; 
		   }
		  
		   $("#pageNo").val(pageNo);
		   $('#currentpage').text( pageNo);
		   $('#form').submit();
	   }else if(value=='下一页'){
		   $('#op').val('paging');
		 
		   var total =  $('#totalpage').text();
		
		   if(pageNo<total){
			   pageNo++;
		   }
		  
		  
		   $("#pageNo").val(pageNo);
		   $('#currentpage').text( pageNo);
		   $('#form').submit();
	   }else if(value=='首页'){
		
		   $('#op').val('paging');
		   $("#pageNo").val(1);
		   $('#form').submit();
	   }else if(value=='尾页'){
		  $('#op').val('paging');
		  $("#pageNo").val(totalpage);
		  $('#form').submit();
	   }else if(value=='跳转'){
		   $('#op').val('paging');
		   $("#pageNo").val(spageNo);
		   $('#form').submit();
	   }else if(value=='搜索'){
		   $('#op').val('paging');
		   $('#currentpage').text( pageNo);
		
		   $('#form').submit();
	   }
	}
	function  search(obj){
		 
		$('#searchHidden').val($('#searchInput').val());
	
		mysubmit(obj);
	}
	</script>
</head>
<body>
  <div id="big">
  	<div id="fav">
  		显示我看过的菜
  		<ul id="history">
  			
  		</ul>
  	</div>
  	<div id="mainDiv">
  		 <%
  			 	 	String searchValue="";
  			 	 	if(session.getAttribute("searchValue")!=null){
  			 	 		searchValue=session.getAttribute("searchValue")+"";
  			 	}
  			 	 %> 
  					 	
  		<input  id="searchInput" value="<%=searchValue %>"/><input type="button" value="搜索" onclick="search(this)"/>
  		
  		<table id="t1" width="90%">
  		   <%
  		      for(int i=0;i<listFood.size();i++){
  		    	Resfood r = listFood.get(i);
  		    	   %>
  		    	   <%
  		    	     if(i%3==0){
  		    	    	%>
  		    	    	<tr>
  		    	    	<% 
  		    	     }
  		    	   %>
  		    	    	<td>
  		    	    	<dl>
  		    	    		<dt><img alt="" src="images/<%=r.getFphoto() %>" width="100px" height="100px">  </dt>
  		    	    		<dd><a href="resfood.action?op=findbyId&fid=<%=r.getFid() %>"><%=r.getFname() %></a></dd>
  		    	    		<dd>原价<%=r.getNormprice() %></dd>
  		    	    		<dd>跳楼价<%=r.getRealprice() %></dd>
  		    	    		<dd>访问数:<%=r.getVisited() %></dd>
  		    	    		<dt>点赞数:<span id="praiseResult_<%=r.getFid() %>"> <%=r.getParise() %> </span>  <a href="javascript:void" onclick="praise(<%=r.getFid()%>)">赞一个</a>  </dt>
  		    	    	</dl>
  		    	    	</td>
  		    	  <%
  		    	  if(i%3==2){
  		    		%>
  		    		</tr>
  		    		<%  
  		    	  } %>
  		    	    
  		    	   
  		    	   <% 
  		      }
  		   
  		   %>
  		</table>
 
  		<form id="form" action="resfood.action" method="GET" enctype="multipart/form-data" >
  		
  			 <jiangbin:pageBar page="${page}"></jiangbin:pageBar>
  		
  		</form>
  		
  	</div>
  </div>
</body>
</html>