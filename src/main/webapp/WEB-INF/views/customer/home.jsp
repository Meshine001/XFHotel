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

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/v1/css/base.css?r=3">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/v1/css/layout.css?r=3">


<script src="<%=basePath%>/dist/public/js/jquery.min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/dist/public/js/jquery.lazyload.min.js"
	type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/colorbox.css?r=2">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/commons/boostrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/commons/awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/calendar.css">
<script src="<%=basePath%>/dist/public/js/jquery.colorbox-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/dist/public/js/swipe.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>

<style type="text/css">
#jxfocus {
	width: 320px;
	height: 497px;
	overflow: hidden;
	position: relative;
}

.hua_d {
	height: 497px;
	position: absolute;
}

#jxfocus dl {
	float: left;
	width: 320px;
	height: 497px;
	overflow: hidden;
	position: relative;
}

.readyload {
	background: #E4EBEC url('/images/loading.gif') no-repeat scroll center
		center;
}

.swipe {
	overflow: hidden;
	visibility: hidden;
	position: relative;
	left: 135px;
	width: 930px;
	height: 620px;
	margin: 0 auto;
	margin-top: 40px;
}

.swipe-wrap {
	overflow: hidden;
	position: relative;
}

.swipe-wrap>div {
	float: left;
	width: 100%;
	position: relative;
}

.pics_pre {
	background: url(http://mf.znimg.com/public/v1/images/ck_left.png)
		no-repeat;
	width: 44px;
	height: 44px;
	display: block;
	position: absolute;
	left: 0px;
	top: 50%;
	-webkit-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	-o-transform: translateY(-50%);
	transform: translateY(-50%);
	cursor: pointer;
}

.pics_pre:hover {
	background: url(http://mf.znimg.com/public/v1/images/ck_lf.png)
		no-repeat;
}

.pics_next {
	background: url(http://mf.znimg.com/public/v1/images/ck_right.png)
		no-repeat;
	width: 44px;
	height: 44px;
	display: block;
	position: absolute;
	right: -280px;
	top: 50%;
	-webkit-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	-o-transform: translateY(-50%);
	transform: translateY(-50%);
	cursor: pointer;
}

.te_num {
	width: 90px;
	height: 30px;
	position: absolute;
	right: -194px;
	top: 101%;
	font-size: 20px;
	color: #ffffff;
}

.te_fg {
	width: 150px;
	height: 30px;
	position: absolute;
	left: 134px;
	top: 101%;
	font-size: 20px;
	color: #ffffff;
}

.pics_next:hover {
	background: url(http://mf.znimg.com/public/v1/images/ck_rg.png)
		no-repeat;
}

.colse {
	background: url(http://mf.znimg.com/public/v1/images/ck_close.png)
		no-repeat;
	height: 40px;
	width: 40px;
	display: inline-block;
	right: -280px;
	position: absolute;
	text-align: center;
	line-height: 50px;
	font-size: 70px;
	top: -40px;
	cursor: pointer;
}

.exm_ul li {
	cursor: pointer;
}
</style>
</head>
<body>
	<header>
		<div class="main">
			<a href="/" title="青舍首页" class="logo"></a>
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
				<div class="lgorrg" onmouseover="showHomeMenu()" onmouseout="hideHomeMenu()">
					<c:choose>
						<c:when test="${c != null }">
							<a href="<%=basePath%>/customer/details" title="${c.details.nick}">
								<div class="name">
									<img
										src="<%=basePath%>/images/${c.details.avatar}"
										width="40" height="40"><span>${c.details.nick}</span>
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
	</header>
	<article>
		<div class="banner po_r">
			<c:forEach items="${homeBanner}" var="banner">
				<a href="${banner.linkUrl }" title="${banner.title}" target="_blank"
					style="width: 100%; height: 100%;">
					<div class="hr_img banner_img img-responsive"
						style="position: absolute; width: 100%; height: 100%; background: url(&quot;<%=basePath%>/images/${banner.pic}&quot;) center 0px no-repeat; display: block; opacity: 0.733271;">
					</div>
				</a>
			</c:forEach>
			<a href="" title="" target="" style="width: 100%; height: 100%;">
				<div class="hr_img banner_img"
					style="position: absolute; width: 100%; height: 100%; background: url(&quot;dist/public/v1/images/banner.jpg&quot;) center 0px no-repeat; display: block; opacity: 0.733271;">
				</div>
			</a> <a href="" title="" target="" style="width: 100%; height: 100%;">
				<div class="hr_img banner_img"
					style="position: absolute; width: 100%; height: 100%; background: url(&quot;dist/public/v1/images/banner1.jpg&quot;) center 0px no-repeat; display: block; opacity: 0.733271;">
				</div>
			</a> <span class="pics_pre1" style="" onclick="pre_pic()"></span> <span
				class="pics_next1" style="" onclick="next_pic()"></span>
			<div class="main">
				<form action="<%=basePath%>/homeSearch" method="get"
					name="searchForm" id="searchForm">
					<div class="search clearfix">
						<div class="search_input">
							<span><i class="icon-calendar"></i></span><input
								readonly="readonly" id="search-input-time" placeholder="入住时间"
								type="text" class="search-input-item"><span><i
								class="icon-search"></i></span><input id="search-input-location"
								readonly="readonly" placeholder="西安" type="text" value="西安"
								class="search-input-item">
						</div>
						<input type="hidden" name="checkinday" id="checkinday" value="">
						<input type="hidden" name="checkoutday" id="checkoutday" value="">
						<div id="index_list_calendar-box" style="display: none;"></div>
						<input class="search_btn" value="搜索公寓" type="submit">
						<div class="search_map" style="display: none;">
							<i></i><a title="地图搜房" href="/maplist/">地图搜房</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="apartment">
			<div class="main">
				<h3 class="tit">这里有你需要的公寓</h3>
				<div class="exm_nav fj_nav">
					<span id="fj_on_1" onmouseover="on_fj(1);" class="exm_nav_on"></span>
				</div>
				<ul class="apt_ul" id="fj_1" style="display: block;">
					<c:forEach items="${homeRoom}" var="room">
						<li><a href="" target="_blank" title="${room.square}"> <img
								class="readyload" src="<%=basePath%>/images/${room.pic2[0]}"
								data-original="" width="378" height="251"
								style="display: inline;">
								<p class="apt_jg">
									<span>${room.dayPrice}</span>元/天
								</p>
								<p class="apt_bt">${room.apartmenttype}-${room.community}-${room.direction}</p>
						</a></li>
					</c:forEach>
				</ul>
				<input type="hidden" name="fangjian_id_2" id="fangjian_id_2"
					value="133,2407,2300,2290,2282,2165"> <input type="hidden"
					name="fangjian_id_3" id="fangjian_id_3"
					value="2405,2348,2284,2263,2216,2207"> <input type="hidden"
					name="fangjian_id_4" id="fangjian_id_4"
					value="2273,1188,2119,913,158,1154">
				<div class="more">
					<a title="查看更多房源" href=""></a>
				</div>
			</div>
		</div>

		<div class="service">
			<div class="main">
				<h3 class="tit">动动手指享受智能服务</h3>
				<h4 class="tit-sub">全新安全机制&nbsp;&nbsp;自助智能管家</h4>
				<ul class="service_ul">
					<li><img src="dist/public/v1/images/xuzu.png">
						<p>续租，退租</p></li>
					<li><img src="dist/public/v1/images/mimasuo.png">
						<p>密码锁</p></li>
					<li><img src="dist/public/v1/images/shiyou.png">
						<p>我的室友</p></li>
					<li><img src="dist/public/v1/images/yongche.png">
						<p>用车服务</p></li>
					<li><img src="dist/public/v1/images/shebei.png">
						<p>设备添加</p></li>
					<li><img src="dist/public/v1/images/baojie.png">
						<p>保洁服务</p></li>
					<li><img src="dist/public/v1/images/jianyi.png">
						<p>投诉建议</p></li>
					<li><img src="dist/public/v1/images/guzhang.png">
						<p>故障报修</p></li>
					<li><img src="dist/public/v1/images/wenti.png">
						<p>常见问题</p></li>
					<li><img src="dist/public/v1/images/guanjia.png">
						<p>呼叫管家</p></li>
				</ul>
			</div>
		</div>

		<div class="hai">
			<div class="main">
				<h3 class="tit">青舍生活</h3>
				<div class="exm_nav fj_nav">
					<span class="exm_nav_on"></span>
				</div>
				<div class="hai_list" id="jx_div">
					<div class="hai_lf">
						<div class="two">
							<a onmouseover="showMask(1,0)" onmouseout="hideMask(1,0)" href=""
								title="青舍“绘”生活零基础绘画活动" target="_blank"> <img
								class="readyload" src=""
								data-original=""
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_0">
									<span>青舍“绘”生活零基础绘画活动</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(1,1)" onmouseout="hideMask(1,1)"
								href="" title="明星同款火锅，爆辣来袭" target="_blank"> <img
								class="readyload" src="/hotel/dist/public/images/blank.gif"
								data-original=""
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_1">
									<span>明星同款火锅，爆辣来袭</span><input class="mask_btn" value="点击查看">
								</div>
							</a>
						</div>
						<div class="three">
							<a onmouseover="showMask(2,0)" onmouseout="hideMask(2,0)"
								href="http://www.monph.com/shenghuo/info-390.html" title="青舍嗨歌会"
								target="_blank"> <img class="readyload"
								src="/hotel/dist/public/images/blank.gif"
								data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_0">
									<span>青舍嗨歌会</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,1)" onmouseout="hideMask(2,1)"
								href="http://www.monph.com/shenghuo/info-455.html?app=1"
								title="2016/17青舍跨年音乐节" target="_blank"> <img
								class="readyload" src="/hotel/dist/public/images/blank.gif"
								data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_1">
									<span>2016/17青舍跨年音乐节</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,2)" onmouseout="hideMask(2,2)"
								href="http://www.monph.com/shenghuo/info-442.html"
								title="青舍公寓首届脱单趴" target="_blank"> <img class="readyload"
								src="/hotel/dist/public/images/blank.gif"
								data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_2">
									<span>青舍公寓首届脱单趴</span><input class="mask_btn" value="点击查看">
								</div>
							</a>

						</div>
					</div>
					<div class="hai_rg">
						<div id="jxfocus">
							<div class="hua_d" style="left: -960px; width: 1280px;">
								<a href="/gushi/info-82.html" target="_blank" title="速度与激情，青舍风云">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-82.html" target="_blank"
										title="速度与激情，青舍风云">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150706/2180504d1853f6c725bec581a03eb26c.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-82.html" target="_blank"
											title="速度与激情，青舍风云">
											<h3>速度与激情，青舍风云</h3>
											<p></p>
										</a><a href="/gushi/info-82.html" target="_blank"
											title="速度与激情，青舍风云" class="all_text">阅读全文</a>
									</dt>
								</dl>

								<a href="/gushi/info-81.html" target="_blank" title="萌妹纸的青舍小生活">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-81.html" target="_blank" title="萌妹纸的青舍小生活">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150629/f3edcb5920aa0daa90e924e549618660.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-81.html" target="_blank"
											title="萌妹纸的青舍小生活">
											<h3>萌妹纸的青舍小生活</h3>
											<p>一位小学英语老师，孩子给我很多的快乐，他们简单善良，有时会在教师节出其不意的送我礼物。平时除了工作，就是喜欢和大家…...</p>
										</a><a href="/gushi/info-81.html" target="_blank"
											title="萌妹纸的青舍小生活" class="all_text">阅读全文</a>
									</dt>
								</dl>

								<a href="/gushi/info-80.html" target="_blank" title="青舍，我在这里">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-80.html" target="_blank" title="青舍，我在这里">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150629/9dcd063a56672b52d8ebee7a26c985b6.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-80.html" target="_blank" title="青舍，我在这里">
											<h3>青舍，我在这里</h3>
											<p>毕业工作两年了，从来到青舍公寓，第一感觉就是服务非常的不错，管家们也非常积极负责。之前有过类似的这样公寓居住的经历…...</p>
										</a><a href="/gushi/info-80.html" target="_blank" title="青舍，我在这里"
											class="all_text">阅读全文</a>
									</dt>
								</dl>

								<a href="/gushi/info-79.html" target="_blank"
									title="初次见到，便决定就是这里"> </a>
								<dl class="hai_dl">
									<a href="/gushi/info-79.html" target="_blank"
										title="初次见到，便决定就是这里">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150629/5fa2351558c479857e2fd51aec7681df.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-79.html" target="_blank"
											title="初次见到，便决定就是这里">
											<h3>初次见到，便决定就是这里</h3>
											<p></p>
										</a><a href="/gushi/info-79.html" target="_blank"
											title="初次见到，便决定就是这里" class="all_text">阅读全文</a>
									</dt>
								</dl>

							</div>
						</div>
						<ul class="hai_ul">
							<li class=""></li>
							<li class=""></li>
							<li class=""></li>
							<li class="hai_on"></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="more">
					<a title="查看更多房源" href=""></a>
				</div>
			</div>
		</div>
		<div class="customer">
			<div class="main">
				<h3 class="tit">合作伙伴</h3>
				<div class="qy_div" id="qyfocus" style="overflow: hidden;">
					<ul class="cut_list"
						style="position: relative; height: 90px; padding: 0px; margin: 50px 0px; left: -4029.51px; width: 1920px;">
						<li><img src="<%=basePath%>/dist/public/v1/images/guojia.jpg"></li>
						<li><img src="<%=basePath%>/dist/public/v1/images/keer.jpg"></li>
						<li><img src="<%=basePath%>/dist/public/v1/images/wecat.jpg"></li>
						<li><img
							src="<%=basePath%>/dist/public/v1/images/xiecheng.jpg"></li>
						<li><img src="<%=basePath%>/dist/public/v1/images/yijia.jpg"></li>
						<li><img
							src="<%=basePath%>/dist/public/v1/images/zhifubao.jpg"></li>
						<li><img
							src="<%=basePath%>/dist/public/v1/images/zhunaer.jpg"></li>
					</ul>
				</div>
				<ul class="cut_ul">
					<li class=""></li>
					<li class=""></li>
					<li class=""></li>
					<li class=""></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		<div class="celan" style="top: 75%;">
			<div class="celan_list">
				<span class="weixin"><img
					src="<%=basePath%>/dist/public/v1/images/wxx.png" width="60"
					height="60"></span> <span><a href="/down/" title="官方APP"
					target="_blank"><img
						src="<%=basePath%>/dist/public/v1/images/app.png" width="60"
						height="60"></a></span> <span class="customer2"><a
					href="http://wpa.qq.com/msgrd?v=3&uin=790101285&site=qq&menu=yes"><img
						src="<%=basePath%>/dist/public/v1/images/kf.png" width="60"
						height="60"></a></span> <span><a href="javascript:goTop();"
					title="返回顶部"><img
						src="<%=basePath%>/dist/public/v1/images/top.png" width="60"
						height="60"></a></span>
			</div>
			<div class="em" style="display: none;">
				<div class="em_pr">
					<img src="<%=basePath%>/dist/public/v1/images/em.png" width="149"
						height="149"> <i></i>
				</div>
			</div>
		</div>

	</article>
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
	<script type="text/javascript"
		src="<%=basePath%>/dist/public/js/calendar.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/customer/js/home.js"></script>

</body>

</html>