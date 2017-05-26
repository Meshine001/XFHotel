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
						<h3>订单列表</h3>
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
							<th>订单号</th>
							<th>下单时间</th>
							<th>订单状态</th>
							<th>下单人</th>
							<th>联系手机</th>
							<th>房间</th>
							<th>住房时间</th>
							<th>总天数</th>
							<th>单价</th>
							<th>总价</th>
							<th>优惠</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.payNo}</td>
								<td>${order.timeStr}</td>
								<td>${order.status}</td>
								<td>${order.cusName}</td>
								<td>${order.cusTel}</td>
								<td>${order.description}</td>
								<td>${order.startTime}至${order.endTime}</td>
								<td>${order.totalDay}</td>
								<td>${order.price}</td>
								<td>${order.totalPrice}</td>
								<td>${order.preferential}</td>
								<td>
									<c:if test="${order.status=='确认中'}">
									<a href="javascript:;" class="btn comfirm-order" data-id="${order.id}">确认订单</a><br>
									<a href="javascript:;" class="btn close-order" data-id="${order.id}">关闭订单</a>
									</c:if>
									<c:if test="${order.status=='退租确认中'}">
									<a href="javascript:;" class="btn comfirm-order" data-id="${order.id}">确认退租</a><br>
									</c:if>
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
			var url = '../order/comfirm';
			var id = $(this).attr('data-id');
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
					if(data.statusCode == 1){
						window.location.href = '../admin/order';
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//关闭订单
		$('.close-order').click(function(){
			var url = '../order/close';
			var id = $(this).attr('data-id');
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
					if(data.statusCode == 1){
						window.location.href = '../admin/order';
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