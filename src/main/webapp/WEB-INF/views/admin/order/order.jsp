<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>title-青舍都市公寓-西安租房_西安合租</title>
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
							<th>发票</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
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
								<td><c:choose>
										<c:when test="${order.needFapiao == true}">
								需要
							</c:when>
										<c:otherwise>
								不需要
							</c:otherwise>
									</c:choose></td>
								<td>${order.preferential}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</my_body>
</body>
</html>