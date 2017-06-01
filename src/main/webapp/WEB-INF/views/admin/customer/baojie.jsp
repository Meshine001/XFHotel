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
						<h3>保洁服务——订单列表</h3>
					</div>
				</div>
				<!--  
				<ul class="nav nav-tabs">
					<li role="0"><a href="javascript:;">全部订单</a></li>
					<li role="1" class="active"><a href="">已完成订单</a></li>
					<li role="2"><a href="javascript:;">进行中订单</a></li>
					<li role="3"><a href="javascript:;">超时订单</a></li>
					<li role="4"><a href="javascript:;"></a></li>
				</ul>
				-->
				
				
				<div id="topscroll" style="width:100%;height:auto;overflow-x:auto">
				<table class="table">

					<thead>
						<tr>
							<th>保洁ID</th>
							<th>下单时间</th>
							<th>服务订单状态</th>
							<th>服务内容</th>
							<th>房间ID</th>
							<th>打扫时间</th>
							<th>订单ID</th>
							<th>需求</th>
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