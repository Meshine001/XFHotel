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

<my_header>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/order-pre.css">
</my_header>
</head>
<body>
	<my_body>
	<div class="order-wrapper" id="order_wrapper">
		<div class="order_infos">
				<div class="order_avalaibel_time">
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
				</div>
			<div class="order_briefs">
				<span class="briefs_item price">订单金额： <span
					class="price_aera"> <dfn>¥</dfn> <b class="num_b">${order.totalPrice }<i
							class="num_s" style="display: none;">.00</i></b>
				</span>
				</span> <span class="briefs_item title"> ${order.description}<em>${order.type}1间&nbsp;&nbsp;&nbsp;&nbsp;入住：${order.startTime }&nbsp;&nbsp;&nbsp;&nbsp;退房：${order.endTime}&nbsp;&nbsp;&nbsp;&nbsp;入住${order.totalDay}晚</em>
				</span>
			</div>
			<div class="order_detail" style="display: block;"
				>
				<dl class="detail_list">
					<dt>费用明细</dt>
					<dd>
						<span class="ti">房费</span><span class="list_ite"><dfn>¥</dfn>${order.totalPrice }</span>
					</dd>
				</dl>
			</div>
			<a href="javascript:;" class="flex_btn" ng-click="details.toggle()"
				style="display: none;"> <span ng-bind="details.action_get()"
				class="ng-binding">订单详情</span> <i class="icon_arrow_up"
				></i>
			</a>
		</div>
		<div class="hotel_tip">
			<dl class="tip_info">
				<dd>...............一些说明.......</dd>
			</dl>
		</div>
		<div class="mo ng-scope" ng-controller="CmoneyCtrl">
			<p class="pay_price" ng-show="others.amount">
				需支付： <span class="price_aera"> <dfn>¥</dfn> <b
					class="num_b ng-binding" >${order.totalPrice}<i
						class="num_s" style="display: none">.00</i></b> <span style="display: none;"
					> + <b class="num_b ng-binding"
						ng-bind-html="others.fee|amount">0<i class="num_s">.00</i></b>
				</span>
				</span> <em style="display: none;" pay-show="others.fee" class="ng-binding">（外卡需加收%服务费）</em>
			</p>
		</div>
		<div class="tabs">
			<div class="tab_nav_nt1">
				<ul class="clearfix">
					<li class="tabnav_special  ng-isolate-scope curr"
						ng-class="{curr:Pay.Others[0].selected}" pay-catalog="Pay.Others"
						show-notice="1" notice="" ubt="09c"><i
						class="tab_icon_onlinePay"></i> 在线支付</li>
				</ul>
			</div>
			<div class="tab_aera" style="">
				<div class="bank_list noti">
					<ul class="bank_items">
						<li class="ng-isolate-scope act"><i class="more_wecatpay"></i><em
							class="hint ng-hide" title=""></em></li>
						<li class="ng-isolate-scope "><i class="more_alipay"></i><em
							class="hint ng-hide" title=""></em></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="order_step">
			<a href="javascript:;" class="btn_sub ng-binding">下一步</a>
		</div>
	</div>
	<div style="margin-top: 50px;">
		<ul>
			<li>订单有效时间:
				
			</li>
			<li>编号：${order.id}</li>
			<li></li>
			<li>${order.cusName}</li>
			<li>${order.cusTel}</li>
			<li>${order.cusIdCard}</li>
			<li>${order.personal}</li>
			<li></li>
			<li><c:choose>
					<c:when test="${order.needFapiao == true }">需要发票</c:when>
					<c:otherwise>
					不需要发票
				</c:otherwise>
				</c:choose></li>
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