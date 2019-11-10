<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<script>


	
	function showDetail(roid) {
	
	
	 	$.ajax({
			url : "resuser/resorder.action",
			data : "op=showOrderDetail&roid=" + roid,
			dataType : "JSON",
			success : function(data) {
				if (data.code == 1) {
					$(".orderitem").hide();
					var str = "";
					if (data.obj) {
						 
						str+="<table  border='1' style='margin-left:20px; width:400px ;cellspaceing:0; cellpadding:0;border-collapse: collapse;text-align:center;'><thead><th>订单号</th><th>菜名</th><th>价格</th><th>数量</th><th>小计</th></thead>"
						str+="<tbody style='max-height:20px; overflow:auto;'>"
						for (var i = 0; i < data.obj.length; i++) {
							var resorderitem = data.obj[i];
							str+="</tr>"   
							str += "<td>"+resorderitem.fid + "&nbsp;&nbsp;&nbsp;&nbsp;"+"</td>"
									+"<td>"+resorderitem.fname+"&nbsp;&nbsp;"+"</td>"
									+"<td>"+ resorderitem.dealprice + "&nbsp;&nbsp;"+"</td>"
									+"<td>"+ resorderitem.num + "&nbsp;&nbsp;"+"</td>"
									+"<td>￥"+ resorderitem.subtotal + "&nbsp;&nbsp;"+"</td>";
							str+="</tr>"
							
						}
						str+="</tbody>"
						str+="</table>"
					}
					$("#orderitem_" + roid).html(str);
					$("#orderitem_" + roid).show();
				}
			}
		});
	}
</script>
<c:forEach items="${resorderJsonModel.rows }" var="resorder">
<hr />
	<div>
		<div>
			<span>${resorder.ordertime }</span>
			&nbsp;&nbsp;
			<span>
				${resorder.statusStr }
			</span>
			
			&nbsp;&nbsp;
			<input type='hidden' value='${resorder.roid }' id="roid">
			 
			&nbsp;&nbsp;
			<span> 地址:&nbsp;&nbsp;&nbsp;${resorder.address }</span>
			&nbsp;&nbsp;&nbsp;
			<span> 总价：&nbsp;&nbsp;&nbsp;￥${resorder.totalprice }&nbsp;&nbsp;&nbsp;</span>
			<span>
				<a href="javascript:showDetail(${resorder.roid })">查看详情</a>
			</span>
		</div>
		
		
		<div class="orderitem" id="orderitem_${resorder.roid }"></div>
		
	</div>
</c:forEach>
<div>
    <hr />
	<yc:pageBar pages="${resorderJsonModel.pages }" pagesize="${resorderJsonModel.pageSize }" total="${resorderJsonModel.total }" action="resuser/resorder.action?op=showOrders"></yc:pageBar>
</div>
<%@ include file="bottom.jsp"%>













