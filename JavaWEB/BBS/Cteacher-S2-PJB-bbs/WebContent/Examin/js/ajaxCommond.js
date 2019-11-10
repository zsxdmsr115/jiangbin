var questions;
/**
 * 试题
 */
var flag = true;
var prev = [];
var count = 0;// 记录次数
var index = 0;
var answerList = [];
var ss;
function kaoshi() {
	var id = $(".select option:selected").val();
 
	$
			.ajax({
				url : "domain.jsp",
				data : {
					'op' : 'lesson',
					'id' : id
				},
				dataType : 'json',
				success : function(data) {

					if (data.code > 0) {
						if (confirm("开始考试")) {

							$("#forms").hide();
							$('#table').show();
							var arr = data.obj;
							questions = arr;
							$("#qtype").text(arr[0].type);

							$("#qname").text(arr[0].subject);
							// 选项
							$("#optiona").html(arr[0].optiona);

							$("#optionb").html(arr[index].optionb);
							$("#optionc").html(arr[index].optionc);
							$("#optiond").html(arr[index].optiond);

							var $tmnum = $('#tmnum');// 题目数
							for (var i = 0; i < arr.length; i++) {

								$tmnum
										.append("<li style=' text-align:center; line-height:50px; background:#08c; width:50px; height:50px; float:left;display:block; margin-left:5px;'>"
												+ (i + 1) + "</li>");//
							}
							$('#tmnum').children('li').eq(0).css({
								'background' : 'green'
							});
							var $this;
							$('#tmnum')
									.children('li')
									.hover(function() {
										$this = $(this)
										$(this).css({
											'cursor' : 'hand'
										});
									})
									.click(
											function() {
												var a;

												var arr = $('input[name=option]:checked');// 选中的复选框

												var str = "";

												for (var i = 0; i < arr.length; i++) {
													str += arr[i].value + ",";
													$(arr[i]).prop('checked',
															false);
												}
												;
												str = str.substring(0,
														str.length - 1).trim();
												
												prev[count] = index;
												answerList[prev[count]] = str;
												count++;

												flag = false;
												str = "";

												for (var i = 0; i < arr.length; i++) {
													str += arr[i].value + ",";
													$(arr[i]).prop('checked',
															false);
												}
												;
												str = str.substring(0,
														str.length - 1).trim();
												index = $(this).text() - 1;
												prev[count] = index;
												count++;
												// console.log(prev[count-2],str);
												answerList[prev[count - 2]] = str;
												$('#tmnum')
														.children('li')
														.css(
																{
																	'background' : '#08c'
																})

												$(this).css({
													'background' : 'green'
												});
												$("#qtype").html(
														questions[index].type);
												$("#qname")
														.html(
																questions[index].subject);
												$("#optiona")
														.html(
																questions[index].optiona);
												$("#optionb")
														.html(
																questions[index].optionb);
												$("#optionc")
														.html(
																questions[index].optionc);
												$("#optiond")
														.html(
																questions[index].optiond);
												$('#tmnum').children('li').eq(
														index - 1).css({
													'background' : '#08c'
												});
												$('#tmnum').children('li').eq(
														index).css({
													'background' : 'green'

												});

												ss = str.substring(0,
														str.length - 1).trim();

												if (answerList[index] != null
														&& answerList[index] != "") {

													var value = answerList[index]
															.split(",");
													var op = $('input[name=option]');
													for (var i = 0; i < value.length; i++) {
														if (value[i] == "A") {

															op.eq(0).attr(
																	'checked',
																	true);
														}
														if (value[i] == "B") {
															op.eq(1).attr(
																	'checked',
																	true);
														}
														if (value[i] == "C") {
															op.eq(2).attr(
																	'checked',
																	true);
														}
														if (value[i] == "D") {
															op.eq(3).attr(
																	'checked',
																	true);
														}
													}
												}
											});

						} else {
							$("#forms").show();
						}

					}
				}

			})
}

function nextquestion() {
	index++;
	count++;
	if (index < questions.length) {
		$("#qtype").text(questions[index].type);
		$("#qname").text(questions[index].subject);
		$("#optiona").html(questions[index].optiona);
		$("#optionb").html(questions[index].optionb);
		$("#optionc").html(questions[index].optionc);
		$("#optiond").html(questions[index].optiond);
		$('#tmnum').children('li').eq(index - 1).css({
			'background' : '#08c'
		});
		$('#tmnum').children('li').eq(index).css({
			'background' : 'green'
		});
		var arr = $('input[name=option]:checked');// 选中的复选框

		var str = "";

		for (var i = 0; i < arr.length; i++) {
			str += arr[i].value + ",";
			$(arr[i]).prop('checked',
					false);
		};
		str = str.substring(0,
				str.length - 1).trim();
		answerList[index-1]=str;//自己选项
		}
	
}
var tatoleScore=0;
var correct=0;

var error;
var objarr=[];
var errorcode =0;
//算出分数
function socre() {
	
	objarr.length=0;
	var arr = $('input[name=option]:checked');//选中的复选框
	var str = "";
	for (var i = 0; i < arr.length; i++) {//最后一题
		str += arr[i].value + ",";
		$(arr[i]).prop('checked', false);
	}
	;
	str = str.substring(0, str.length - 1).trim();
	answerList[index] = str;
	var $list = $('#tmnum').children("li");;
	for(var i=0;i<questions.length;i++){
		 
		  if(answerList[i]==questions[i].answer){
			  correct++;
			  errorcode=1;
			  tatoleScore++;
		  }else{
			  errorcode=0;  
		  }
		  if(answerList[i]==undefined||answerList[i]==""){
			  answerList[i]='未做';
		  }
		  var obj={
			  'errorcode':errorcode,
			  'selfanswer':answerList[i],
			  'uid':$('#inputUid').val(),
			  'qid':questions[i].qid,
			  'nonum':i+1
		  }
		  objarr.push(obj);
	  }
	
	error=	questions.length-correct;
	alert("得分:"+tatoleScore);
	var text =	$(".select option:selected").text();
	//console.log(questions);
	//$.post('exajsp/commod.jsp', {'op':'record','lessonid':questions[0].lessonid, 'uid':$('#inputUid').val(),'totalScore':tatoleScore,'correct':correct,"error":error,'lessonname':text});
	$.post('exajsp/commod.jsp',{'op':'tabl_score','objarr':JSON.stringify(objarr)});
}




