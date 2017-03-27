<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>

<div>
	<div class="order_briefs">
		<span class="briefs_item price">订单金额： <span class="price_aera">
				<dfn>¥</dfn> <b class="num_b">${order.totalPrice }<i
					class="num_s" style="display: none;">.00</i></b>
		</span>
		</span> <span class="briefs_item title"> ${order.description}<em>${order.type}1间&nbsp;&nbsp;&nbsp;&nbsp;入住：${order.startTime }&nbsp;&nbsp;&nbsp;&nbsp;退房：${order.endTime}&nbsp;&nbsp;&nbsp;&nbsp;入住${order.totalDay}晚</em>
		</span>
	</div>
	<div class="order_detail" style="display: block;">
		<dl class="detail_list">
			<dt>费用明细</dt>
			<dd>
				<span class="ti">房费</span><span class="list_ite"><dfn>¥</dfn>${order.totalPrice }</span>
			</dd>
		</dl>
	</div>
	<a href="javascript:;" class="flex_btn" ng-click="details.toggle()"
		style="display: none;"> <span ng-bind="details.action_get()"
		class="ng-binding">订单详情</span> <i class="icon_arrow_up"></i>
	</a>
</div>
<div class="hotel_tip">
	<dl class="tip_info">
		<dd>...............一些说明.......</dd>
	</dl>
</div>
