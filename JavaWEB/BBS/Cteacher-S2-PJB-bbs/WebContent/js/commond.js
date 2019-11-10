/**
 * 发表 list.jsp?bid=<%=bid%>
 */

function publish(obj) {
	var bid = obj.getAttribute("bid");
	$
			.ajax({
				url : "userisLogin.jsp",
				data : {
					"op" : "publish",
					"bid" : bid
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {

					if (data.code == 0) {
	
					// window.location.href="doList.jsp?bid="+data.obj;
						$.messager.lays(300, 200);
						$.messager
								.show(0,
										'<a href="#"><font >温馨提示</font></a><br><font >您尚未登录，不能发表帖子</font>');
					} else {
						location.href = "dopost.jsp?bid=" + data.obj;
					}
				}

			}

			);
}
var flag = true;
//回帖判断用户是否登录
function doReply(){
	var value =  $("#action").val();
     if(value=="no"){
    	 $.messager.lays(300, 200);
			$.messager
					.show(0,
							'<a href="#"><font >温馨提示</font></a><br><font >您尚未登录，不能发表帖子</font>'); 
     }else{
    	 location.href="rply.jsp";
     }
}
// 回复
function reply(obj) {
	if (flag) {
		$(obj)
				.parent()
				.prepend(
						"<div id='replybox'></br><input class='replyText' type='text'/><button  class='send' >确定</button><br/></div>");
		flag = false;
	}
	var uid = $(obj).attr("uid");
	var topicid = $(obj).attr('topicid');
	$('.send').click(
			function() {
				var content = $('.replyText').val();
				if (content == "" || content == null) {
					alert("回复消息不能为空");
					return;
				}
				$
						.ajax({
							url : 'ajaxjsp/commond.jsp',
							data : {
								'op' : 'rply',
								'topicid' : topicid,
								'content' : content,
								"uid" : uid
							},
							dataType : 'json',
							success : function(data) {
								if (data.code > 0) {
									flag = true;
									$('#replybox').remove();
									var result = data.obj;
									var text = "【回复消息】：" + result.content
											+ "&nbsp;&nbsp;&nbsp;"
											+ result.publishtime;
									$(".tipad:first").before(
											"<div>" + text + "<div>")
									
								}
							}
						});
			}

	);

}
/**
 * 在线考试
 */
function Examination(obj) {
	var bid = obj.getAttribute("bid");
	$
			.ajax({
				url : "userisLogin.jsp",
				data : {
					"op" : "examin",
					"bid" : bid
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {

					if (data.code == 0) {

						// window.location.href="doList.jsp?bid="+data.obj;
						$.messager.lays(300, 200);
						$.messager
								.show(0,
										'<a href="#"><font >温馨提示</font></a><br><font >您尚未登录，不能发表帖子</font>');
					} else {
						location.href = "Examin/index.jsp?uid=" + data.obj;
					}
				}

			}

			);
}
/**
 * 退出
 */

function signout() {
	// 创建ajax对象
	if (confirm("是否真的退出")) {

		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {// Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		xmlHttp.open("POST", "loginout.jsp?op=loginout");
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;");
		xmlHttp.onreadystatechange = processRequest;
		xmlHttp.send(null);

		function processRequest() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var data = xmlHttp.responseText;
					if (data == 1) {

						window.open("index.jsp", "_self");
					}
				}
			}
		}

		// 绑定监听函数
		/*
		 * xmlHttp.onreadystatechange=function(){ alert(xmlHttp.readyState);
		 * //判断数据是否正常返回xmlHttp.readyState==4响应结束状态 if(xmlHttp.readyState==4 &&
		 * xmlHttp.status==200){ //接收数据 var data = xmlHttp.responseText;
		 * alert(data); } //3.绑定处理请求的地址,true为异步，false为同步 xmlHttp.open("POST",
		 * "../dologin.jsp", true); //post提交设置协议头（GET方式省略）
		 * xmlHttp.setRequestHeader("Content-type",
		 * "application/x-www-form-urlencoded"); //Post提交参数，如果是Get提交不用提交参数
		 * //发送请求 xmlHttp.send("op=loginout");
		 *  }
		 */
	}
}
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		var code = $("#code").val();
		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {// Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		xmlHttp.open("POST", "dologin.jsp?op=login&uname=" + username
				+ "&upass=" + password + "&code=" + code);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;");
		xmlHttp.onreadystatechange = processRequest;
		xmlHttp.send(null);

		function processRequest() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var data = xmlHttp.responseText;
					if (data == 1) {
						window.location.href = "index.jsp";
					}
					if (data == 2) {
						alert('用户名或密码不正确');
					}
					if (data == 3) {
						alert("验证码不正确");
					}

				}
			}
		}

	}

	//搜索
	function search(){
		var  title= $('#search').val();
		alert(title);
		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {// Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		xmlHttp.open("POST", "doSearch.jsp?title="+title);
		xmlHttp.setRequestHeader("Content-Type",
		"application/x-www-form-urlencoded;");
		xmlHttp.send(null);
		xmlHttp.onreadystatechange = processRequest;

		function processRequest() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					var data =	xmlHttp.responseText;
					if(data==1){
						confirm("查询成功");
					}
				}
			}
		}
	}
	
function check(){
	var title =  $("#title").val().trim();
	var content = CKEDITOR.instances.content.getData();
	 if(title==null || title==""){
		  alert("标题不能为空");
		 return false;
	 }
	 if(content==null || content==""){
		 alert("内容不能为空"); 
		 return false;
	 }
	 return true;
}
	
	
	
	
	
