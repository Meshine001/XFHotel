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
<link rel="stylesheet"
	href="<%=basePath%>/dist/commons/awesome/css/font-awesome.min.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link href="<%=basePath%>/dist/customer/css/carousel.css"
	rel="stylesheet">
<link href="<%=basePath%>/dist/commons/web/css/base.css"
	rel="stylesheet">
<link href="<%=basePath%>/dist/customer/css/home.css" rel="stylesheet">
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
				<a class="navbar-brand hidden-sm" href="" onclick="">青舍都市公寓</a>
			</div>

			<nav class="navbar-collapse collapse" role="navigation">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=basePath%>">首页</a></li>
					<li><a href="<%=basePath%>/list?type=酒店型公寓">酒店型公寓</a></li>
					<li><a href="<%=basePath%>/list?type=休闲型公寓">休闲型公寓</a></li>
					<li><a href="">青客生活</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right hidden-sm">
					<c:if test="${c != null }">
						<li><a href="./customer/logout">登出</a></li>
					</c:if>
					<c:if test="${c == null }">
						<li><a href="./customer?forword=login">登录</a></li>
						<li><a href="./customer?forword=reg">注册</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	<!-- ./导航 -->


	<!-- 滑动窗口 -->
	<!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img
					src="http://mf.znimg.com/upload/home/20160622/6a986c6d837a9c4916fca037366bd2ea.jpg"
					alt="...">
				<div class="carousel-caption">...</div>
			</div>
			<div class="item">
				<img
					src="http://mf.znimg.com/upload/home/20161114/fe17f97f95b3c337a97203eaab62d87d.jpg"
					alt="...">
				<div class="carousel-caption">...</div>
			</div>

			<div class="item">
				<img
					src="http://mf.znimg.com/upload/home/20161207/0277b9bab58d5ac95497125646572b1a.jpg"
					alt="...">
				<div class="carousel-caption">...</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
			<span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
		<div class="search-row">
			<form class="" role="search">
				<div class="search-col">
					<span><i class="icon-calendar icon-large"></i></span><input
						class="search-input" name="" type="text" value="入住时间">
				</div>
				<div class="search-col">
					<span><i class="icon-search icon-large"></i></span><input
						class="search-input" name="" type="text" value="城市">
				</div>
				<div class="search-col">
					<button class="search-submit">搜索</button>
				</div>
			</form>
		</div>
	</div>
	<div class="container">
		<div class="home-section tuijian">
			<h2 class="section-title">走到哪玩到哪 不亦乐乎</h2>
			<ul>
				<li class="tuijian-hotel"><span><i
						class=" icon-caret-right icon-small"></i></span> 日租</li>
				<li class="tuijian-play"><span><i
						class=" icon-caret-right icon-small"></i></span> 游戏</li>
			</ul>
			<div class="row tuijian-box">
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
				<div class="col-md-3">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive"></a>
				</div>
			</div>
		</div>
		<!-- 服务 -->
		<div class="home-section service well">
			<h2 class="section-title">动动手指享受智能服务</h2>
			<h3 class="section-title-sub">全新的安全机制 自助智能管家</h3>
			<div class="row">
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
				<div class="col-md-2 service-items">
					<i class="icon-coffee icon-large"></i>
					<p>续租
				</div>
			</div>
		</div>
		<!-- ./服务 -->

		<div class="home-section ">
			<h2 class="section-title">这里有你需要的小窝</h2>
			<div class="row">
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
				<div class="col-md-4 tuijian2-items">
					<a><img alt=""
						src="http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg"
						class="img-responsive">
					</a>
					<h4 class="tuijian2-title">200元/天</h4>
					<h4 class="tuijian2-content">幸福家园一室-C房间</h4>
				</div>
			</div>
		</div>
		
		<div class="home-section story">
		
			
		</div>
		
		<div class="home-section company">
		
			
		</div>

	</div>
	<!-- /.container -->

	<!-- FOOTER -->
	<footer>
		<div class="main">
			<div class="top">
				<div class="nav">
					<ul class="nav-ul">
						<li><span>网站首页</span> <a>酒店型公寓</a> <a>短租型公寓</a> <a>租前须知</a> <a>青舍生活</a>
						</li>
						<li><span>关于青舍</span> <a>关于我们</a> <a>免责声明</a> <a>联系我们</a> <a>加入我们</a>
						</li>
						<li><span>业务合作</span> <a>业主加盟</a> <a>网站地图</a></li>
					</ul>
					<div class="service-tel">
						<span class="tel">888-8888-888</span> <span class="date"
							style="color: #dbdbdb;">周一至周六 09:00 - 20:00<br>（仅收市话费）
						</span> <span class="kefu">在线客服</span>
					</div>
				</div>
			</div>
		</div>
		<div class="bottom">
			<div class="main">
				<p>
					<span>友情链接：</span><a href="" title="" target="_blank">去哪网</a> | <a
						href="" title="" target="_blank">途牛网</a> | <a href="" title=""
						target="_blank">阿里旅游</a> | <a href="" title="" target="_blank">中国旅游信息网</a>
					| <a href="" title="" target="_blank"></a>
				</p>
				<p>Copyright © 2016-2018 qingshe.com xxxxxxxxx有限公司 版权所有
					陕ICP备xxxxxx号-x</p>
			</div>
		</div>

	</footer>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>