<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>青舍都市公寓-西安租房_西安合租</title>

<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/dist/commons/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- cropper -->
<link href="<%=basePath%>/dist/commons/cropper/cropper.min.css"
	rel="stylesheet">

<link href="<%=basePath%>/dist/customer/css/dashboard.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/crop-avatar.css" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<sitemesh:write property='my_header' />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">青舍都市公寓</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=basePath%>">首页</a></li>
					<li><a href="#">我要找房</a></li>
					<li><a href="#">地图找房</a></li>
					<li><a href="#">青舍生活</a></li>

				</ul>

			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar" id="sidebar">
					<li id="reservation"><a
						href="<%=basePath%>/customer/reservation">我的预约</a></li>
					<li><a href="#">我的订单</a></li>
					<li><a href="#">我的舍友</a></li>
					<li id="details"><a href="<%=basePath%>/customer/details">个人资料</a></li>
					<li id="settings"><a href="#">账户设置</a></li>
				</ul>


			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<sitemesh:write property='my_body' />

			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/dist/commons/cropper/cropper.min.js"></script>
	<script
		src="<%=basePath%>/dist/commons/ajaxfile-upload/ajaxfileupload.js"></script>
	</my_script>
	<script src="<%=basePath%>/dist/customer/js/dashboard.js"></script>

	<sitemesh:write property='my_script' />
</body>
</html>