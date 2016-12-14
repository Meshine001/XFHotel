<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=basePath%>/dist/commons/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
</head>
<body >
	<my_body style="margin-top: 20px">
		<div class="row">
			<div class="col-md-12">
				<div class="page-header">
					<h1>
						青舍首页 <small>西安租房</small>
					</h1>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<form action="">
					<div class="input-group">
						<input type="text" class="form-control"> <span
							class="input-group-addon"><button type="submit"
								class="btn btn-default">搜索</button></span> <span
							class="label label-default">热门搜索：</span> <span
							class="label label-default">小寨</span> <span
							class="label label-default">钟楼</span>
					</div>
				</form>
			</div>
		</div>

		<div class="row">
			<!-- 区域 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						区域：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 小区 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						小区：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 地铁 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						地铁：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 租金 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						租金：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 特色 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						户型：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 特色 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						特色：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
			<!-- 状态 -->
			<div class="row">
				<div id="" class="col-md-12">
					<h4>
						状态：<span class="label label-info">全部</span>
					</h4>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<span>推荐</span>|<span>价格</span>|<span>面积</span>
			</div>
		</div>

		<div class="row">
			<div class="col-md-4">
				<img width="" height=""
					src="<%=basePath%>/dist/customer/img/apartment-01.jpg" alt="..."
					class="img-rounded">
			</div>
			<div class="col-md-8">
				<div class="page-header">
					<h2>学府首座3居室-南卧-C房间</h2>
				</div>
				<div class="row">
					<div class="col-md-5">
						<ul>
							<li>风格：<span>致青春</span></li>
							<li>楼层：<span>第26层/共32层</span> 面积：<span>21.61平方</span> 朝向：<span>南</span></li>
							<li>地址：<span>郑东新区文苑南路与邢庄北街交叉口</span></li>
						</ul>
					</div>
					<div class="col-md-3">
						<h3>
							1302<small>元/月</small>
						</h3>
						<h4>原价:￥1480/月</h4>
					</div>
				</div>
				<div>
					<span class="label label-default">市体育中心地铁口附近</span> <span
						class="label label-default">供暖</span>
				</div>
				<div class="well">
					<p>舒适温馨布局，精心时尚装修，青春的气息布满房间，向青春致敬，家的温暖在此展现。</p>
				</div>
			</div>
		</div>
	</my_body>
</body>
</html>