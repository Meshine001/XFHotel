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
	href="<%=basePath%>/dist/customer/css/order-list.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/jquery-ui.css">
<script type="text/javascript"
	src="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/datepicker-zh-CN.js"></script>
</my_header>
</head>
<body>
	<my_body>
	<h1 class="e_tit">我的订单</h1>
	<div class="order-main-wrap">
		<!-- 订单选项 -->
		<div id="js-category-panel">
			<ul class="order-remind-list" id="category-list">
				<li class="cur "><a class="category-a" href="javascript:"
					data-category="0" title="" data-beacon="tab-全部订单">全部订单<em></em></a></li>
				<li><a class="category-a" href="javascript:" data-category="1"
					title="" data-beacon="tab-有效订单">有效订单<em></em></a></li>
				<li><a class="category-a" href="javascript:" data-category="2"
					title="" data-beacon="tab-退款订单">退款订单<em></em></a></li>
				<li style="display: none;"><a class="link" href="/?category=4"
					data-category="4" title="" data-beacon="tab-待绑定订单">待绑定订单<em></em></a></li>
				<li class="last" style="display: none;"><a class="order-mobile"
					href="/mobileorder"><i class="icon-mobile"
						data-beacon="tab-手机号查询-"></i>手机号查询</a> <a class="order-back"
					href="/recycle"><i class="icon-delete" data-beacon="tab-订单回收站-"></i>订单回收站</a>
				</li>
			</ul>
		</div>
		<!-- 订单选项 -->
		<!-- 订单筛选 -->
		<div class="searchbar">
			<form id="search-form">
				<input type="hidden" name="cId" value="${c.id}"> <input
					type="hidden" name="category" value="0" id="category-input">
				<div class="simple clearfix">
					<!-- 订单类型 -->
					<span class="title">订单类型</span>
					<div class="simple-main input-w-76 m-r-16"
						id="order-type-sel-wrapper">

						<select class="font-color-666" name="type" id="s-type">
							<option selected="true" value="0">全部</option>
							<option value="1">酒店式</option>
							<option value="2">休闲式</option>
						</select>

					</div>
					<!-- 订单类型 -->
					<!-- 订单时间 退款订单暂时不显示-->
					<span class="title">订单时间</span>
					<div class="hotel-dp">
						<div class="dp-info">
							<b id="startDateIcon"></b><span class="dp-text"></span>
						</div>
						<input id="startDate" name="startDate" value="2016-01-16"
							maxlength="10" class="textbox" readonly="true">
					</div>
					<div class="hotel-dp">
						<div class="dp-info">
							<b id="endDateIcon"></b><span class="dp-text"></span>
						</div>
						<input id="endDate" name="endDate" value="2017-01-16"
							maxlength="10" class="textbox" readonly="true">
					</div>
					<input type="hidden" name="range" value="12" id="range">
					<ul class="order-time-list" id="order-time-list">
						<li><a class="range" href="javascript:;" data-range="1"
							data-beacon="时间范围-最近一个月">最近一个月</a></li>
						<li><a class="range" href="javascript:;" data-range="3"
							data-beacon="时间范围-三个月">三个月</a></li>
						<li class="cur"><a class="range" href="javascript:;"
							data-range="12" data-beacon="时间范围-一年">一年</a></li>
					</ul>
					<a href="javascript:" class="order-search-btn"
						id="order-search-btn" data-beacon="订单查询按钮">查询</a>
					<!-- 订单时间 -->
				</div>
			</form>
		</div>
		<!-- 订单筛选 -->

		<!-- 订单列表 -->
		<div class="order-list">
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/dist/customer/js/order-list.js"></script>
	</my_body>
</body>
</html>