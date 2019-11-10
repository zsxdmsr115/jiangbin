<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
 	String path = request.getContextPath();

 	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  request.setCharacterEncoding("UTF-8");
%>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<base href="<%=basePath %>"/>
<link rel="stylesheet" href="Examin/css/common.css">
<link rel="stylesheet" href="Examin/css/main.css">
<script type="text/javascript" src="Examin/js/jquery.min.js"></script>
<script type="text/javascript" src="Examin/js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="Examin/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>ckeditor/ckeditor.js"></script>
<script type="text/javascript" src='<%=basePath %>js/jquery.messager.js'></script>
<script type="text/javascript">
	var choice=0;//单选还是多选
      $(function(){ 
    	  $('.tare').each(function(i,item){
    		 
    		  if(i==0||i==3){
    			  CKEDITOR.replace(item,{height:'200px',width:'450px'}); 
    		  }else{
    			  CKEDITOR.replace(item,{height:'150px',width:'300px'});  
    		  }
    		 
    	  });
        $(".list_table").colResizable({
          liveDrag:true,
          gripInnerHtml:"<div class='grip'></div>", 
          draggingClass:"dragging", 
          minWidth:30
        }); 
        //单选多选
        timuType();
       
      });
    
         //判断
        function docheck(){
        	Document.charset="UTF-8";
           var sval = CKEDITOR.instances.subject.getData();
           
          if(sval==""||sval==null){
        	  
        	 /* $.messager.lays(300, 200);
				$.messager.show(0, '<a href="#"><font >温馨提示</font></a><br><font >题目内容不能为空</font>');*/
		      $('#messageId').css({
		    	  color:'red'
		      }).html('题目内容不能为空');
				
        	  return false;
          }
          //拼接选项
       var arr =   $('input[name=an]:checked');
       var str = "";
 		for (var i = 0; i < arr.length; i++) {//最后一题
 			str += arr[i].value + ",";
 			$(arr[i]).prop('checked', false);
 		}
 		;
 		str = str.substring(0, str.length - 1).trim();
	    $("#answer").val(str);
	    
          return true;
         }
        
    	function timuType(){
    	 $('.input-text').click(function(){
    		 
            if($(this).val()=="duoxuan"){
              choice=1;
             $(".answer" ).each(function(i,items){
            	items.type='checkbox';
            	if(items.checked){
            		items.checked=false;
            	}
            	
             });
            }else{
            	choice=0;
            	  $('.answer').each(function(i,items){
                    	 
                     	items.type='radio';
                     	if(items.checked){
                     		items.checked=false;
                     	}
                      });
                     
            }
           });
    }
    	  
     
       	   
      
   </script>
<title>Document</title>
<style>
</style>
</head>
<body>
	<div class="container">

		<div id="forms" class="mt10">
			<div class="box">
				<div class="box_border">
					<div class="box_top">
						<b class="pl15">表单</b>
					</div>
					<div class="box_center">
						<form action="Examin/exajsp/commod.jsp" class="jqtransform"
							onsubmit="return docheck()" method="get"  accept-charset="UTF-8" >
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tr>
									<td class="td_right">课程名称：</td>
									<td class=""><select name='lessonid'
										class="input-text lh30" style="width: 100px">
											<c:if test="${not empty llist }">
												<c:forEach var='data' items="${llist }">
													<option value="${data.id }">${data.name }</option>
												</c:forEach>
											</c:if>
											<option value="计算机">计算机</option>

									</select></td>
									<td class="td_right">题目类型：</td>
									<td><input type="radio" name="type"
										class="input-text lh30" value="danxuan" size="40"
										checked='checked'>单选题 <input type="radio" name="type"
										value="duoxuan" class="input-text lh30" size="40">多选题
										<input type="hidden" name="op" value="addKaoti" /></td>

								</tr>


								<tr>
									<td>
										<div>
											请在下面方框中输入题目内容: * <input type="button" value="查找">&nbsp;&nbsp;&nbsp;&nbsp;选中题目一部分，查找题目是否存在
										</div> <textarea name="subject" id="subject" cols="60" rows="10"
											class='tare'></textarea>

									</td>
									<td></td>
									<td>
										<h5>选项A描述: *</h5> <textarea name="optiona" id="" cols="40"
											rows="10" class='tare'></textarea>

									</td>
									<td>
										<h5>选项B描述: *</h5> <textarea name="optionb" id="" cols="40"
											rows="10" class='tare'></textarea>

									</td>

								</tr>
								<tr>
									<td>
										<h5>题目解析</h5> <textarea name="note" id="" cols="60" rows="10"
											class='tare'></textarea>
									</td>
									<td style="width: 10%"></td>
									<td>
										<h5>选项C描述: *</h5> <textarea name="optionc" id="" cols="40"
											rows="10" class='tare'></textarea>

									</td>
									<td>
										<h5>选项D描述: *</h5> <textarea name="optiond" id="" cols="40"
											rows="10" class='tare'></textarea>

									</td>
								</tr>
								<tr>
									<td id="messageId"></td>
									<td><input type="submit" value="添加题目"
										></td>

									<td class="td_right">正确答案选项</td>

									<td class="">
									A: <input class='answer' type="radio"  name="an" checked='checked' VALUE="A">  
									B: <input class='answer' type="radio"  name="an"  value="B">
									C: <input class='answer' type="radio"  name="an"  value="C">
									D: <input class='answer' type="radio"  name="an" value="D"> 
									<input id='answer' type="hide" name="answer" >
									</td>
								</tr>

								<tr>
									<td></td>
								</tr>
								<tr>
									<td></td>
								</tr>

							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
