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

<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/v1/css/base.css?r=3">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/layout.css">

<sitemesh:write property='my_header' />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body style="background-color: rgb(244, 244, 245);">
	<header>
		<div class="main">
			<a href="/" title="青舍首页" class="logo"></a>
			<div class="nav clearfix">
				<ul class="nav_ul">
					<li><a href="<%=basePath%>/" title="">首页</a></li>
					<li><a href="<%=basePath%>/list?type=酒店式公寓" title="">酒店式公寓</a></li>
					<li><a href="<%=basePath%>/list?type=休闲式公寓" title="">休闲式公寓</a></li>
					<li><a href="<%=basePath%>/story" title="">青客生活</a></li>
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
							<a class="lg" href="<%=basePath%>/customer?forword=login"
								title="登录">登录</a>&nbsp;&nbsp;<span><img alt=""
								src="dist/public/v1/images/phone.jpg"></span>&nbsp;&nbsp;<a
								class="rg" href="<%=basePath%>/customer?forword=reg"
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
			function showHomeMenu() {
				$("#home-menu").css('display', '');
			}
			function hideHomeMenu() {
				$("#home-menu").hide();
			}
		</script>
	</header>
	<sitemesh:write property='my_banner' />
	<div class="main">
		
		<sitemesh:write property='my_body' />
	</div>
	<footer>
		<div class="main">
			<div class="ftr_top">
				<div class="dld clearfix">
					<div class="social">
						<a href="http://weibo.com/" rel="nofollow" title="青舍微博"
							target="_blank" class="sina"></a> <a href="javascript:;"
							title="青舍微信" class="wx">
							<div class="btm_em" style="display: none;">
								<div class="em_pr">
									<img src="/hotel/dist/public/v1/images/em.png" width="149"
										height="149"> <i></i>
								</div>
							</div>
						</a>
					</div>
					<div class="app">
						<span class="tel400">400-0000-000</span><span class="ftr_date"
							style="color: #dbdbdb;">周一至周六 09:00 - 20:00<br>（仅收市话费）
						</span>


					</div>
				</div>
				<div class="ftr_nav">
					<ul class="ftr_ul">
						<li><span>网站首页</span><a href="/list/" target="_blank"
							title="">房屋租赁</a><a href="javascript:;" title="" style="">租房须知</a><a
							href="/notice.html" target="_blank" title="">青客生活</a></li>
						<li><span>关于我们</span><a href="/mianze.html" target="_blank"
							title="免责声明">免责声明</a><a href="/contact_us.html" target="_blank"
							title="联系我们">联系我们</a><a href="/join_us.html" target="_blank"
							title="加入我们">加入我们</a><a href="/join_us.html" target="_blank"
							title="">呼叫管家</a></li>
						<li><span>合作交流</span><a href="/qilejia.html" target="_blank"
							title="商务合作">商务合作</a><a href="/jiameng.html" target="_blank"
							title="业主加盟">业主加盟</a><a href="/webmap/" target="_blank"
							title="网站地图">网站地图</a></li>

					</ul>
					<div class="ftr_tel">

						<a href="" target="_blank" title="Android下载" class="android"><img
							src="dist/public/v1/images/em.png" width="100" height="100"></a><a
							href="" target="_blank" title="IOS下载" class="ios"><img
							src="dist/public/v1/images/em.png" width="100" height="100"></a>
					</div>
				</div>

			</div>
		</div>
		<div class="ftr_btm">
			<div class="main">
				<p>
					<span>友情链接：</span><a href="http://www.zhuna.cn/" title="住哪网"
						target="_blank">住哪网</a> | <a href="http://www.9tour.cn/"
						title="九游网" target="_blank">九游网</a> | <a
						href="http://m.rong360.com/" title="融360" target="_blank">融360</a>
					| <a href="http://www.cthy.com/" title="中国旅游信息网" target="_blank">中国旅游信息网</a>
					| <a href="http://www.weilver.com/" title="微驴儿机票" target="_blank">微驴儿机票</a>
					| <a href="http://www.tielu.cn/" title="铁路网" target="_blank">铁路网</a>
				</p>
				<p>Copyright © 2014-2016 qinshe.com 青舍 版权所有 陕ICP备16002206号-9</p>
			</div>
		</div>
	</footer>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.timers-1.2.js"></script>
	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.easing.1.3.js"></script>
	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.galleryview-3.0-dev.js"></script>
	<sitemesh:write property='my_script' />
</body>
</html>