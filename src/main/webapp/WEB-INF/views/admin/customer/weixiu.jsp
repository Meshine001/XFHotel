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
						<h4>故障维修</h4>
					</div>
				</div>
		
				<div style="overflow: auto;width:100%;height:auto">
				<table class="table table-bordered table-hover">

					<thead>
						<tr>
							<th>id</th>
							<th>下单时间</th>
							<th>订单ID</th>
							<th>房间</th>
							<th>状态</th>
							<th>维修内容</th>
							<th>需求</th>
							<th>维修时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
								<td>${order.time}</td>
								<td>${order.oederId}</td>
								<td>${order.roomId}</td>
								<td>${order.status}</td>
								<td>${order.faultItem}</td>
								<td>${order.demand}</td>
								<td>${order.maintainTime}</td>
								<td>
								<c:if test="${order.status=='等待管理员呼叫维修'}">
									<a href="javascript:;" class="comfirm-order btn btn-success" order-id="${order.id}" style="display:block;width:120px;">确定服务</a>
								</c:if>
								<c:if test="${order.status=='正在维修'}">
									<a href="javascript:;" class="success-order btn btn-warning " order-id="${order.id}" style="display:block;width:120px;">确定完成</a>
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
	    $("table th,table td").css('min-width','120px')
		$("table th:first-child,table td").css('min-width','60px')
		$("tbody tr").css('vertical-align','middle')
		//确认订单
		$('.comfirm-order').click(function(){
			var url = '../order/faultOrder';
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
						window.location.href = '../admin/customer_weixiu';
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//关闭订单
		$('.success-order').click(function(){
			var url = '../order/faultOrders';
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
						window.location.href = '../admin/customer_weixiu';
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