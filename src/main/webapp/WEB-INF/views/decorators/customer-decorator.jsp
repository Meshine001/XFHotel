<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>${title}-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<link href="<%=basePath%>/dist/customer/css/base.css" rel="stylesheet">
<link href="<%=basePath%>/dist/customer/css/panel.css" rel="stylesheet">
<link href="<%=basePath%>/dist/customer/css/e-right.css"
	rel="stylesheet">
<link href="<%=basePath%>/dist/commons/colorbox/colorbox.css"
	rel="stylesheet">
<sitemesh:write property='my_header' />
</head>
<body class="po_r">
	<header class="ty">
		<div class="main">
			<div class="logo" onclick="location.href='/'"></div>
			<div class="nav">

				<ul id="nav_ul" class="nav_ul">
					<li class="nav_on"><a href="http://www.monph.com/"
						title="魔飞首页">首页</a></li>
					<li><a href="/list/" title="我要找房">我要找房</a></li>
					<li><a href="/maplist/" title="地图搜房">地图搜房</a></li>
					<li><a href="/shenghuo/" title="青舍生活">青舍生活</a></li>

				</ul>
			</div>
		</div>
	</header>
	<article>
		<div class="main">
			<div class="e_my">
				<div class="e_left">
					<div class="e_name">
						<div class="y_100"></div>
						<img
							src="http://mf.znimg.com/public/images/face-90x90.png?r=90x90_1481006350"
							id="my-face" width="90" height="90"
							onclick="location.href='/home/ziliao.html'">
						<p class="e_name_m">
							187****9465<a href="/logout.php" title="退出登录">退出</a>
						</p>
						<p class="e_name_n">
							您还没有撰写您的个性宣言，请先<a href="/home/ziliao.html">完善资料</a>吧~
						</p>
					</div>
					<ul class="e_list">
						<li id="wodeyuyue" class="e_list_on">
							<p>我的预约</p> <i></i>
						</li>
						<!-- <li id="wodegongyu" class="">
			<p>我的公寓</p><i></i>
		</li>
		<li id="jiaofei" class="">
			<p>缴费</p><i></i>
		</li>	 -->
						<li id="wodeshoucang" class="">
							<p>我的收藏</p> <i></i>
						</li>
						<li id="youhuiquan" class="">
							<p>我的优惠券</p> <i></i>
						</li>
						<li id="tuiguang" class="">
							<p>我的推广</p> <i></i>
						</li>
						<li id="wodetousu" class="">
							<p>我的投诉</p> <i></i>
						</li>
						<li id="gerenziliao" class="">
							<p>个人资料</p> <i></i>
						</li>
						<li id="zhanghaoshezhi" class="">
							<p>帐号设置</p> <i></i>
						</li>
					</ul>
					<div class="anquan">
						<p>
							<span class="fr"> 中等 </span> 账户安全级别：
						</p>
						<!--账户安全级别只需要更改<em>的长度即可。长度为百分比。-->
						<i class="bg_f"> <em class="bg_b" style="width: 60%;"></em>
						</i> <a href="/home/change_password.html" title="修改密码"
							class="xg_pwd fr">修改密码&gt;</a>
					</div>
					<div class="e_ewm">
						<img src="" width="121" height="121">
						<p>扫一扫 下载青舍APP</p>
					</div>
				</div>
				<div class="e_right">
					<sitemesh:write property='e_right' />
				</div>
			</div>
		</div>
	</article>
	<div class="flt" style="display: none;">
		<!-- <a target="_blank" style="width:100%;display:block;height:100%;" href="/hd/wuzhe.html"> -->
		<div class="main">
			<div class="d_2"></div>
			<div class="d_3">
				<i></i> <a class="ios" target="_blank"
					href="https://itunes.apple.com/us/app/mo-fei-gong-yu-zheng-zhou/id887776293?mt=8">IOS下载</a><a
					class="and" target="_blank"
					href="http://www.monph.com/app/Monph.apk">Android下载</a>
			</div>
			<div class="d_4"></div>

			<!-- <img src="http://mf.znimg.com/upload/home/20161104/01471d1806b2327b1afb786384b4fdb1.png"> -->
			<div class="clse"></div>
		</div>

	</div>
	<footer>
		<div class="f_a">
			<div class="main">
				<ul>
					<li><i class="fic_1"></i>
						<p>在线选房</p></li>
					<span class="triangle"></span>
					<li><i class="fic_2"></i>
						<p>青舍管家陪同看房</p></li>
					<span class="triangle"></span>
					<li><i class="fic_3"></i>
						<p>签订合同并付款</p></li>
					<span class="triangle"></span>
					<li><i class="fic_4"></i>
						<p>入住青舍都市公寓</p></li>
				</ul>
			</div>
		</div>
		<div class="f_b">
			<div class="main">
				<ul class="f_tit">
					<li>
						<h5>网站首页</h5> <a href="/list/" title="酒店型公寓">酒店型公寓</a> <a
						href="/maplist/" title="短租型公寓">短租型公寓</a> <a href="/jiameng.html"
						title="在线找房">在线找房</a> <a href="/notice.html" title="青客生活">青客生活</a>

					</li>
					<li>
						<h5>关于青舍</h5> <a href="/about_us.html" title="关于我们">关于我们</a> <a
						href="/fuwu.html">服务协议</a> <a href="/mianze.html">免责声明</a> <a
						href="/yinsi.html">隐私条款</a> <a href="/contact_us.html">联系我们</a> <a
						href="/join_us.html">加入我们</a>
					</li>
					<li>
						<h5>关注青舍</h5> <a href="http://weibo.com/5763512393" rel="nofollow"
						title="魔飞微博" target="_blank"> <i class="sina"></i> 新浪微博
					</a> <a href="javascript:void(0);" title=""
						onmouseover="$('#showWeixin').show();"
						onmouseout="$('#showWeixin').hide();"> <i class="weixin"></i>
							微信服务号 <i id="showWeixin" style="display: none;" class="db_ewm1"></i>
					</a> <a href="/webmap/">网站地图</a>
					</li>
				</ul>
				<div class="t_right">
					<div class="er">
						<img src="" width="106" height="106">
						<p>扫一扫，下载APP</p>
					</div>
					<div class="f_tel">
						<span class="tel400">888-8888-888</span> <span class="f_data">
							周一至周日 07:00 - 23:00 <br> （仅收市话费）
						</span> <span class="kefu" id="customer">在线客服</span>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<script type="text/javascript"
		src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/commons/colorbox/jquery.colorbox-min.js"></script>
	<sitemesh:write property='my_script' />
</body>
</html>