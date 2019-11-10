<%@page import="com.yc.bbs.bean.User"%>
<%@page import="com.yc.examin.bean.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% 
User user =(User) session.getAttribute("user");
 int index=0;
%>

<!DOCTYPE html PUBLIC>

<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/colResizable-1.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style>
    *{
       font-family:'微软雅黑';
       font-size:12px;
    }
 .list_table td{
     border:0;
     text-align:left;
 }
</style>

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
<title>Document</title>
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
						<form action="" class="jqtransform">
							<table class="form_table pt15 pb15" width="100%" border="0"
								cellpadding="0" cellspacing="0">
								<tr>

									<td align="center"><span class="fl">
											<div class="select_border">
												考试课程
												<div class="select_containers ">
													<select style="width: 100px" name="lesson" class="select">
													<c:if test="${ not empty llist }">
														<c:forEach  var="obj" items="${llist }"  varStatus="status">
													   <c:if test="${status.index eq 0 }">
														    <option  value="${obj.id }"  selected="selected">${obj.name }</option>
														    </c:if>
															<option  value="${obj.id }" >${obj.name }</option>
														</c:forEach>
													</c:if>
														
													</select>
												</div>
											</div>
									</span></td>
									<td><input type="button" name="button"
										class="btn btn82 btn_save2" value="确定" onclick="kaoshi()">
										  <input type="hidden" id='inputUid' value="<%=user.getUid()%>"/>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div id="table" class="mt10" style="display: none;">
			<div class="box span10 oh">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="list_table">
					<tr>

						<th width="100" align="center" id="qtype"></th>
						<th width="100">该题目有115人做过 / 正确率达：64%</th>

					</tr>
					<tr class="tr">

						<td align="center" id="qname" align="center" >
						<p>aaa</p>
						</td>
						<td width="100" ></td>

					</tr>
					<tr class="tr ck0">
					   <td width="100" align="center" >A:&nbsp;&nbsp;<input type='checkbox' name='option' value='A'/></td>
						<td align="center" id="optiona" ></td>
					</tr>
					<tr align="center" class="tr ck1">
						<td width="100" >B:&nbsp;&nbsp;<input type='checkbox' name='option' value='B'/></td>
						<td  align="center" id="optionb" ></td>
					</tr>
					<tr class="tr ck2">
						<td width="100" align="center">C:&nbsp;&nbsp;<input type='checkbox' name='option' value='C'/></td>
						<td align="center" id="optionc" ></td>
					</tr>
					<tr class="tr ck3">
						<td width="100" align="center">D:&nbsp;&nbsp;<input type='checkbox' name='option' value='D'/></td>
						<td align="center" id="optiond" ></td>
					</tr>
				</table>
					<div class="page mt10">
					<div class="pagination">
						<ul id="tmnum">
						
						</ul>
					</div>

				</div>
				<div class="page mt10">
					<div class="pagination">
						<ul>
							
							<li><a href="javascript:void(0)" onclick="nextquestion()" >下一题</a></li>
						   <li><a href="javascript:void(0)" onclick="socre()" >提交</a></li>
						</ul>
					</div>

				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="js/ajaxCommond.js"></script>
</body>
</html>
