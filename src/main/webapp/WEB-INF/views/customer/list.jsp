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
	<div class="row clearfix" style="margin-top: 50px">
		<div class="col-md-12 column">
			<ul class="breadcrumb">
				<li>青舍首页</li>
				<li class="active">西安租房</li>
			</ul>
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="col-md-4 column">
						<form action="">
							<div class="input-group">
								<input class="form-control" type="text" placeholder="">
								<div class="input-group-addon">搜索</div>
							</div>
						</form>
					</div>
					<div class="col-md-4 column">
						<span class="label label-info">Info</span>
					</div>
					<div class="col-md-4 column">
						<p>tips.....</p>
					</div>
				</div>
				<div class="col-md-12 column">
					<ul>
						<li id="area"><label>区域:</label> <c:forEach items="${areas}"
								var="a">
								<span class="label label-info area" id="${a.areaId}">${a.areaName}</span>
							</c:forEach></li>
						<li><label>地铁:</label> <c:forEach items="${subways}" var="s">
								<span class="label label-info subway" id="${s.subwayId}">${s.subwayName}</span>
							</c:forEach></li>
						<li><label>租金:</label> <c:forEach items="${leasePrices}"
								var="p">
								<span class="label label-info lease-price" id="${p.priceId}">${p.price}</span>
							</c:forEach></li>
						<li><label>户型:</label> <c:forEach items="${layoutTypes}"
								var="l">
								<span class="label label-info layoutTypes" id="${l.layoutId}">${l.layout}</span>
							</c:forEach></li>
						<li><label>类型:</label><span
							class="label label-info lease-type" id="0">全部</span> <span
							class="label label-info lease-type" id="1">整租</span> <span
							class="label label-info lease-type" id="2">合租</span></li>
						<li><label>特色:</label> <c:forEach items="${features}" var="f">
								<label class="checkbox-inline"> <input type="checkbox"
									id="${f.featureId}" value="${f.feature}">${f.feature}
								</label>
							</c:forEach></li>
						<li><label>状态:</label> <c:forEach items="${roomStatus}"
								var="s">
								<span class="label label-info roomStatus" id="${s.statusId}">${s.status}</span>
							</c:forEach></li>
					</ul>
				</div>
			</div>
			<div class="col-md-12 column">推荐/价格/面积</div>
			<div class="col-md-12 column">
				<div class="col-md-4 column">
					<img width="" height="" class="img-responsive"
						src="<%=basePath%>/dist/customer/img/apartment-01.jpg" alt="...">
				</div>
				<div class="col-md-8 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>安和小区5居室-南卧-C房间</h3>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<div class="col-md-8 column">
								<ul>
									<li>风格：<span>致青春</span></li>
									<li>楼层：<span>第26层/共32层</span> 面积：<span>21.61平方</span> 朝向：<span>南</span></li>
									<li>地址：<span>郑东新区文苑南路与邢庄北街交叉口</span></li>
								</ul>
							</div>
							<div class="col-md-4 column">
								<h3>
									1302<small>元/月</small>
								</h3>
								<h4>原价:￥1480/月</h4>
							</div>
						</div>
						<div class="col-md-12 column">
							<span class="label label-default">市体育中心地铁口附近</span> <span
								class="label label-default">供暖</span>
						</div>
						<div class="col-md-12 column">
							<p>舒适温馨布局，精心时尚装修，青春的气息布满房间，向青春致敬，家的温暖在此展现。</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 column">
				<nav style="text-align: center">
					<ul class="pagination">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	</my_body>
</body>
</html>