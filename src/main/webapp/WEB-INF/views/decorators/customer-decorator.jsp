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

<!-- ZUI 标准版压缩后的 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>/dist/zui/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="<%=basePath%>/dist/zui/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="<%=basePath%>/dist/zui/js/zui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/v1/css/base.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/layout.css">
<!-- cropper -->
<link href="<%=basePath%>/dist/commons/cropper/cropper.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/crop-avatar.css" />

<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>

<link href="<%=basePath%>/dist/commons/colorbox/colorbox.css"
	rel="stylesheet">
<script src="<%=basePath%>/dist/commons/colorbox/jquery.colorbox-min.js"></script>

<style type="text/css">
.b_txt p {
    font-size: 14px;
    color: #666;
    height: 28px;
    line-height: 28px;
    margin: 0px;
}
</style>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<sitemesh:write property='my_header' />
</head>
<body>
	<header>
		<div class="main">
			<a href="<%=basePath%>" title="青舍首页" class="logo"></a>
			<div class="nav clearfix">
				<ul class="nav_ul">
					<li><a href="<%=basePath%>/" title="">首页</a></li>
					<li><a href="<%=basePath%>/list?type=酒店式公寓" title="">酒店式公寓</a></li>
					<li><a href="<%=basePath%>/list?type=休闲式公寓" title="">休闲式公寓</a></li>
					<li><a href="<%=basePath%>/story?page=1" title="">青客生活</a></li>
					<li><a href="<%=basePath%>/serviceCenter" target="_blank"
						title="">服务中心</a></li>
					<li><a href="" target="_blank" title="">在线管家</a></li>
				</ul>
				<div class="lgorrg" onmouseover="showHomeMenu()"
					onmouseout="hideHomeMenu()">
					<c:choose>
						<c:when test="${c != null }">
							<a href="<%=basePath%>/customer/details"
								title="${c.details.nick}">
								<div class="name">
									<img src="<%=basePath%>/images/${c.details.avatar}" width="40"
										height="40"><span>${c.details.nick}</span>
								</div>
							</a>
							<ul class="name_ul" id="home-menu" style="display: none;">
								<li><a href="<%=basePath%>/customer/details">个人资料</a></li>
								<li><a href="<%=basePath%>/customer/logout">退出</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<a class="lg" href="<%=basePath%>/customer/login?forword=login"
								title="登录">登录</a>&nbsp;&nbsp;<span><img alt=""
								src="<%=basePath%>/dist/public/v1/images/phone.jpg"></span>&nbsp;&nbsp;<a
								class="rg" href="<%=basePath%>/customer/login?forword=reg"
								title="注册青舍用户">注册</a>
						</c:otherwise>
					</c:choose>
				</div>
				<a target="_blank" title="下载APP" href="/down/"
					style="display: none;">
					<div class="download">
						<i></i>下载APP
					</div>
				</a>
			</div>
		</div>
		<script type="text/javascript">
		function showHomeMenu()
		{		
			$("#home-menu").css('display','');
		}
		function hideHomeMenu()
		{				
			$("#home-menu").hide();
		}
		</script>
	</header>
	<article>
		<div class="main">
			<div class="e_my">
				<div class="e_left">
					<div class="e_name">
						<div class="y_100"></div>
						<script type="text/javascript">
							$(document).ready(function(){
								$('#myOrder').click(function(){
									location.href='/hotel/customer/myOrder';
								});
								$('#serviceCenter').click(function(){
									location.href='/hotel/customer/serviceCenter';
								});
								$('#preferential').click(function(){
									location.href='/hotel/customer/preferential';
								});
								$('#myDetails').click(function(){
									location.href='/hotel/customer/details';
								});
								$('#feedback').click(function(){
									location.href='/hotel/customer/feedback';
								});
							});
						
						</script>
						<c:choose>
							<c:when test="${c != null}">
								<img src="<%=basePath%>/images/${c.details.avatar}" id="my-face"
									width="90" height="90" onclick="<%=basePath%>/customer/details">
								<p class="e_name_m">
									${c.details.nick}<a href="<%=basePath%>/customer/logout"
										title="退出登录">退出</a>
								</p>
							</c:when>
							<c:otherwise>
								<img src="<%=basePath%>/images/avatar_default.jpg" id="my-face"
									width="90" height="90" onclick="<%=basePath%>/customer/details">
								<p class="e_name_n">
									你还没有编辑详细资料<a href="<%=basePath%>/customer/details">点击这里</a>编辑吧
								</p>
							</c:otherwise>
						</c:choose>
					</div>
					<ul class="e_list">
						<li id="myOrder" class="">
							<p>我的订单</p> <i></i>
						</li>
						<li id="serviceCenter">
							<p>服务中心</p> <i></i>
						</li>
						<li id="preferential">
							<p>我的优惠</p> <i></i>
						</li>
						<li id="myDetails" class="">
							<p>个人资料</p> <i></i>
						</li>
						<li id="" class="" style="display: none;">
							<p>帐号设置</p> <i></i>
						</li>
						<li id="feedback" class="">
							<p>意见反馈</p> <i></i>
						</li>
					</ul>
					<div class="anquan">
						<p>
							<span class="fr"> 中等 </span> 账户安全级别：
						</p>
						<!--账户安全级别只需要更改<em>的长度即可。长度为百分比。-->
						<i class="bg_f"> <em class="bg_b" style="width: 60%;"></em>
						</i> <a href="<%=basePath%>/customer/setting" title="修改密码"
							class="xg_pwd fr">修改密码&gt;</a>
					</div>
					<div class="e_ewm">
						<img src="<%=basePath%>/dist/public/v1/images/em.png" width="121"
							height="121">
						<p>扫一扫 下载青舍APP</p>
					</div>
				</div>
				<div class="e_right">
					<sitemesh:write property='my_body' />
				</div>
			</div>
		</div>
	</article>
	
	<!--  
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar" id="sidebar">
					<li id="reservation"><a
						href="<%=basePath%>/customer/reservation">我的预约</a></li>
					<li><a href="<%=basePath%>/customer/myOrder">我的订单</a></li>
					<li><a href="#">我的舍友</a></li>
					<li id="details"><a href="<%=basePath%>/customer/details">我的资料</a></li>
					<li><a href="#">我的优惠券</a></li>
					<li id="setting"><a href="<%=basePath%>/customer/setting">账户设置</a></li>
				</ul>


			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

			</div>
		</div>
	</div>
	  -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="<%=basePath%>/dist/commons/cropper/cropper.min.js"></script>
	<script src="<%=basePath%>/dist/customer/js/dashboard.js"></script>

	<sitemesh:write property='my_script' />
</body>
</html>