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
	<div style="margin-top: 50px;">
		<ul>
			<li>订单有效时间:
				<div>
					<span id="day"></span> <span id="hour"></span> <span id="minute"></span>
					<span id="second"></span>
				</div>
				<h3>${order.status}</h3> <input type="hidden" id="order-time"
				value="${order.time}"> <script type="text/javascript">
			 var orderTime = $('#order-time').val();
			 var nowTime = Date.parse(new Date());
				var intDiff = parseInt((orderTime-nowTime)/1000); //倒计时总秒数量
				function timer(intDiff) {
					window.setInterval(
							function() {
								var day = 0, hour = 0, minute = 0, second = 0; //时间默认值
								if (intDiff > 0) {
									day = Math.floor(intDiff / (60 * 60 * 24));
									hour = Math.floor(intDiff / (60 * 60))
											- (day * 24);
									minute = Math.floor(intDiff / 60)
											- (day * 24 * 60) - (hour * 60);
									second = Math.floor(intDiff)
											- (day * 24 * 60 * 60)
											- (hour * 60 * 60) - (minute * 60);
								}
								if (minute <= 9)
									minute = '0' + minute;
								if (second <= 9)
									second = '0' + second;
								$('#day').html(day + "天");
								$('#hour').html('<s id="h"></s>' + hour + '时');
								$('#minute').html('<s></s>' + minute + '分');
								$('#second').html('<s></s>' + second + '秒');
								intDiff--;
							}, 1000);
				}
				$(function() {
					timer(intDiff);
				});
			</script>
			</li>
			<li>编号：${order.id}</li>
			<li>${order.description}</li>
			<li>${order.cusName}</li>
			<li>${order.cusTel}</li>
			<li>${order.cusIdCard}</li>
			<li>${order.personal}</li>
			<li>${order.startTime}至${order.endTime}共${order.totalDay}天</li>
			<li><c:choose>
					<c:when test="${order.needFapiao == true }">需要发票</c:when>
					<c:otherwise>
					不需要发票
				</c:otherwise>
			</c:choose>
			</li>
		</ul>
		<ul>
			<li><a href="">微信支付</a></li>
			<li><a href="">支付宝</a></li>
		</ul>
		<a href="<%=basePath%>/order/payOver/${order.id}?status=2">支付完成</a>
	</div>
	</my_body>
</body>
</html>