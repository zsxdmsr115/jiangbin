<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!DOCTYPE html PUBLIC >
<html>

<head>
  <meta charset="UTF-8">
 
  <link rel="stylesheet" href="css/common.css">
  <link rel="stylesheet" href="css/style.css">
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
   <script type="text/javascript" src="js/ajaxCommond.js"></script>
   <script type="text/javascript" src="js/index.js"></script>
  <script type="text/javascript">
  $(function(){
      $(".sideMenu").slide({
         titCell:"h3", 
         targetCell:"ul",
         defaultIndex:0, 
         effect:'slideDown', 
         delayTime:'500' , 
         trigger:'click', 
         triggerTime:'150', 
         defaultPlay:true, 
         returnDefault:false,
         easing:'easeInQuint',
         endFun:function(){
              scrollWW();
         }
       });
      $(window).resize(function() {
          scrollWW();
      });
      changeOn();
  });
  function scrollWW(){
    if($(".side").height()<$(".sideMenu").height()){
       $(".scroll").show();
       var pos = $(".sideMenu ul:visible").position().top-38;
       $('.sideMenu').animate({top:-pos});
    }else{
       $(".scroll").hide();
       $('.sideMenu').animate({top:0});
       n=1;
    }
  } 

var n=1;
function menuScroll(num){
  var Scroll = $('.sideMenu');
  var ScrollP = $('.sideMenu').position();

  if(num==1){
     Scroll.animate({top:ScrollP.top-38});
     n = n+1;
  }else{
    if (ScrollP.top > -38 && ScrollP.top != 0) { ScrollP.top = -38; }
    if (ScrollP.top<0) {
      Scroll.animate({top:38+ScrollP.top});
    }else{
      n=1;
    }
    if(n>1){
      n = n-1;
    }
  }
}

function changeOn(){
	  $(".sideMenu>ul>li").hover(function(){
		   $(this).addClass('on');
		 },function(){
		  $(this).removeClass();
	  }).click(function(){
		var content = $(this).children().text();
	 	 if('在线刷题'==content){
	    	  $('iframe').remove();
			  $('.main').append(' <iframe name="right" id="rightMain" src="main.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>');;
			
	 	 }
	 	 if('添加考题'==content){
	 		 $('iframe').remove();
	 		$('.main').append(' <iframe name="right" id="rightMain" src="back/addkaoti.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>');
			
	 		 
	 	 }
	 	 if("刷题记录"==content){
          
	 		$.ajax({
	 			url:'doBsRecord',
	 			data:{
	 				'op':'stjl',
	 				'uid':$('#inputUid').val()
	 			},
	 			dataType:'json',
	 			success:function(data){
	 				if(data.code>0){
	 					$('iframe').remove();
	 			 		$('.main').append(' <iframe name="right" id="rightMain" src="bsrecord.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe>');
	 				}
	 			}
	 		});
	 	
	 		
			
	 	 }
	      $('#here_area').text('当前位置:'+content);
		});
}

  </script>
  <title>在线考试</title>
</head>
<body>
 <%@ include file="head.jsp" %>
 <%@include file="doIndex.jsp" %>
     <div class="side">
        <div class="sideMenu" style="margin:0 auto">
          <h3>前台菜单</h3>
          <ul>
            <li><a href="javascript:void(0)">在线刷题</a></li>
            <li><a href="javascript:void(0)" >刷题记录</a></li>
            <li>导航菜单</li>
            <li>导航菜单</li>
            <li>导航菜单</li>
          </ul>
          <h3> 后台菜单</h3>
          <ul>
            <li><a href="javascript:void(0)">添加考题</a></li>
            <li>导航菜单</li>
            <li>导航菜单</li>
            <li>导航菜单</li>
            <li>导航菜单</li>
          </ul>
        </div>
    </div>
    <div class="main">
    <!--    <iframe name="right" id="rightMain" src="main.jsp" frameborder="no" scrolling="auto" width="100%" height="auto" allowtransparency="true"></iframe> -->
    
    </div>
 <%@ include file="footer.jsp" %>
  <script type="text/javascript" src="js/ajaxCommond.js"></script>
</body>
</html>