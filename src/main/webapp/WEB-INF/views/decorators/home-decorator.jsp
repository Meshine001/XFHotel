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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- 导航 -->
	<div class="navbar navabar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand hidden-sm" href=""
					onclick="">青舍都市公寓</a>
			</div>

			<nav class="navbar-collapse collapse" role="navigation">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="">酒店型公寓</a></li>
					<li><a href="">短租型公寓</a></li>
					<li><a href="<%=basePath%>/list">在线找房</a></li>
					<li><a href="">青客生活</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right hidden-sm">
					<li><a href="./customer?forword=login">登录</a></li>
					<li><a href="./customer?forword=reg">注册</a></li>
				</ul>
			</nav>
		</div>
	</div>
	<div class="container">
		<sitemesh:write property='my_body' />
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>