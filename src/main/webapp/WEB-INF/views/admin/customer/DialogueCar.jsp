<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>订单管理-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<my_body>
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>叫车服务</h3>
					</div>
				</div>
	
				<div style="overflow: auto;width:100%;height:auto">
				<table class="table">

					<thead>
						<tr>
							<th>订单id</th>
							<th>起止时间</th>
							<th>房间</th>
							<th>车程内容</th>
							<th>支付状态</th>
							<th>其他需求</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
								<td>${order.time}</td>
								<td>${order.status}</td>
								<td>${order.content}</td>
								<td>${order.roomId}</td>
								<td>${order.cleanTime}</td>
								<td>${order.oederId}</td>
								<td>${order.demand}</td>
								<td>
								<c:if test="${order.status=='等待管理员呼叫保洁'}">
									<a href="javascript:;" class="comfirm-order" order-id="${order.id}" style="display:block">确定服务</a>
									</c:if>
									<c:if test="${order.status=='正在清扫'}">
									<a href="javascript:;" class="success-order" order-id="${order.id}" style="display:block">确定完成</a>
									</c:if>
									<!-- 
									<c:if test="${order.status=='确认中'}">
									<a href="javascript:;" class="btn comfirm-order" data-id="${order.id}">确认订单</a><br>
									<a href="javascript:;" class="btn close-order" data-id="${order.id}">关闭订单</a>
									</c:if>
									 -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				<ul id="pagecontroller" class="pagination">
					
				</ul>
				
				</div>
				
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	    $("table th,table td").css('min-width','200px')
		
		//确认订单
		$('.comfirm-order').click(function(){
			var url = '../order/cleanOrder';
			var id = $(this).attr('order-id');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id,
				},
				url : url,
				error : function(data) {
					
				},
				success : function(data) {
					console.log(data)
					if(data.statusCode == 1){
						window.location.href = '../admin/customer_baojie';
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//关闭订单
		$('.success-order').click(function(){
			var url = '../order/cleanOrders';
			var id = $(this).attr('order-id');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id,
				},
				url : url,
				error : function(data) {
					
				},
				success : function(data) {
					console.log(data)
				
					
					
					if(data.statusCode == 1){
						window.location.href = '../admin/customer_baojie';
					}else{
						alert(data.content);
					}
					
				}
			});
		});
	</script>
	</my_body>
</body>
</html>