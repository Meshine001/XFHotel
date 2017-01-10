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
	background: #E4EBEC url('http://mf.znimg.com/public/images/loading.gif')
		no-repeat scroll center center;
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
					<li><a href="/" title="魔飞首页">首页</a></li>
					<li><a href="/list/" title="我要租房">我要租房</a></li>
					<li><a href="/maplist/" title="地图找房">地图找房</a></li>
					<li><a href="/shenghuo/" title="嗨生活">嗨生活</a></li>
					<li><a href="/qilejia.html" target="_blank" title="企乐家">企乐家</a></li>
					<li><a href="/jiameng.html" target="_blank" title="业主加盟">业主加盟</a></li>
				</ul>
				<div class="lgorrg" onmouseover="" onmouseout="">
					<a class="lg" href="/login.html" title="登录">登录</a>&nbsp;·&nbsp;<a
						class="rg" href="/reg.html" title="注册魔飞用户">注册</a>

				</div>
				<a target="_blank" title="下载APP" href="/down/">
					<div class="download">
						<i></i>下载APP
					</div>
				</a>
			</div>
		</div>
	</header>
	<article>
		<div class="banner po_r">

			<a href="http://www.monph.com/kuaixun/info-459.html"
				title="魔飞年终欢乐送，元旦春节不打烊" target="_blank"
				style="width: 100%; height: 100%;">
				<div class="hr_img banner_img"
					style="position: absolute; width: 100%; height: 100%; background: url(&quot;http://mf.znimg.com/upload/home/20170103/80ca7da79d6d1fcc45f3dab8b383c65c.jpg&quot;) center 0px no-repeat; display: block; opacity: 0.733271;">
				</div>
			</a> <span class="pics_pre1" style="" onclick="pre_pic()"></span> <span
				class="pics_next1" style="" onclick="next_pic()"></span>
			<div class="main">
				<form action="/list/" method="get" name="searchForm" id="searchForm">
					<div class="search clearfix">
						<div class="search_input">
							<span><i class="icon-calendar"></i></span><input
								readonly="readonly" id="search-input-time" placeholder="入住时间"
								type="text" class="search-input-item"><span><i
								class="icon-search"></i></span><input id="search-input-location"
								readonly="readonly" placeholder="城市或目的地" type="text"
								class="search-input-item">
						</div>
						<input type="hidden" name="checkinday" id="checkinday" value="">
						<input type="hidden" name="checkoutday" id="checkoutday" value="">
						<div id="index_list_calendar-box" style="display: none;"></div>
						<input class="search_btn" value="搜索公寓" type="submit">
						<div class="search_map">
							<i></i><a title="地图搜房" href="/maplist/">地图搜房</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="example">
			<div class="main">
				<h3 class="tit">走哪玩哪 不亦乐乎</h3>
				<div class="exm_nav fg_nav">
					<span id="fg_on_1" onmouseover="on_fg(1);" class="exm_nav_on">日租单身间</span>
					<span id="fg_on_2" onmouseover="on_fg(2);" class="">游戏娱乐间</span>
				</div>
				<ul class="exm_ul" id="fg_1">
					<li onclick="fg_big(1,1);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_1.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,2);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_2.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,3);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_3.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,4);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_4.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,5);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_5.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,6);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_6.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,7);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_7.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(1,8);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/sg_8.jpg"
						width="277.5" height="210"></li>
				</ul>
				<ul class="exm_ul" id="fg_2" style="display: none;">
					<li onclick="fg_big(2,1);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_1.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,2);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_2.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,3);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_3.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,4);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_4.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,5);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_5.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,6);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_6.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,7);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_7.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(2,8);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/mkl_8.jpg"
						width="277.5" height="210"></li>
				</ul>
				<ul class="exm_ul" id="fg_3" style="display: none;">
					<li onclick="fg_big(3,1);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_1.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,2);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_2.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,3);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_3.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,4);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_4.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,5);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_5.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,6);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_6.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,7);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_7.jpg"
						width="277.5" height="210"></li>
					<li onclick="fg_big(3,8);"><img
						src="%&gt;/dist/public/v1/images//fg_list_s/ql_8.jpg"
						width="277.5" height="210"></li>
				</ul>
			</div>
		</div>
		<div class="service">
			<div class="main">
				<h3 class="tit">动动手指享受智能服务</h3>
				<ul class="service_ul">
					<li><i class="sev_1"></i>
						<p>
							<span class="service_bt">押一付一</span><span class="service_tit">房租月付资金无压力</span>
						</p></li>
					<li><i class="sev_2"></i>
						<p>
							<span class="service_bt">免费WiFi</span><span class="service_tit">百兆光纤入户随心用</span>
						</p></li>
					<li><i class="sev_3"></i>
						<p>
							<span class="service_bt">精致装修</span><span class="service_tit">品牌家电设施，拎包入住</span>
						</p></li>
					<li><i class="sev_4"></i>
						<p>
							<span class="service_bt">快速维修</span><span class="service_tit">售后无忧，专业维修24H响应</span>
						</p></li>
					<li><i class="sev_5"></i>
						<p>
							<span class="service_bt">一客一锁</span><span class="service_tit">智能门锁，安全有保障</span>
						</p></li>
					<li><i class="sev_6"></i>
						<p>
							<span class="service_bt">每周保洁</span><span class="service_tit">专业保洁，生活更省心</span>
						</p></li>
					<li><i class="sev_7"></i>
						<p>
							<span class="service_bt">免中介费</span><span class="service_tit">透明收费无虚假</span>
						</p></li>
					<li><i class="sev_8"></i>
						<p>
							<span class="service_bt">专属客服</span><span class="service_tit">专业客服，全天候为您解忧</span>
						</p></li>
					<li><i class="sev_9"></i>
						<p>
							<span class="service_bt">专属活动</span><span class="service_tit">观影爬山聚会趴，嗨生活</span>
						</p></li>
				</ul>
			</div>
		</div>

		<div class="apartment">
			<div class="main">
				<h3 class="tit">这里有你需要的小窝</h3>
				<div class="exm_nav fj_nav">
					<span id="fj_on_1" onmouseover="on_fj(1);" class="exm_nav_on"></span>
				</div>
				<ul class="apt_ul" id="fj_1" style="display: none;">
					<li><a href="/info-41014266102.html" target="_blank"
						title="正商城泰园4居室-卧-B房间-阳台"> <img class="readyload"
							src="%&gt;/dist/public/v1/images//5dea91f8fd5804252d49c1a786521cf8.jpg"
							data-original="%>/dist/public/v1/images//5dea91f8fd5804252d49c1a786521cf8.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1185</span>元/月
							</p>
							<p class="apt_bt">正商城泰园4居室-卧-B房间-阳台</p>
					</a></li>
					<li><a href="/info-41014253805.html" target="_blank"
						title="风和日丽四期5居室-卧-E房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/94/5a30ab5239efe90ca5a78bc613fd7ab6.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/94/5a30ab5239efe90ca5a78bc613fd7ab6.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1241</span>元/月
							</p>
							<p class="apt_bt">风和日丽四期5居室-卧-E房间</p>
					</a></li>
					<li><a href="/info-41015446003.html" target="_blank"
						title="白庙社区3居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/508/0f3a19792bab5c60e597031f54a61862.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/508/0f3a19792bab5c60e597031f54a61862.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1177</span>元/月
							</p>
							<p class="apt_bt">白庙社区3居室-卧-C房间</p>
					</a></li>
					<li><a href="/info-41011503001.html" target="_blank"
						title="紫薇里3居室-卧-A房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/272/c2abaccd6523d697da36b05d641951e4.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/272/c2abaccd6523d697da36b05d641951e4.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1200</span>元/月
							</p>
							<p class="apt_bt">紫薇里3居室-卧-A房间</p>
					</a></li>
					<li><a href="/info-41013200802.html" target="_blank"
						title="白庙社区4居室-卧-B房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/502/0da363c3de5667b98caa07859778775f.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/502/0da363c3de5667b98caa07859778775f.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1257</span>元/月
							</p>
							<p class="apt_bt">白庙社区4居室-卧-B房间</p>
					</a></li>
					<li><a href="/info-41014215803.html" target="_blank"
						title="白庙社区3居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/424/bf612cd6b6e60757dfb71f4e818e425a.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/424/bf612cd6b6e60757dfb71f4e818e425a.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span>1276</span>元/月
							</p>
							<p class="apt_bt">白庙社区3居室-卧-C房间</p>
					</a></li>
				</ul>

				<ul class="apt_ul" id="fj_2">
					<li><a href="/info-41017631406.html" target="_blank"
						title="翡翠公寓6居室-卧-F房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/100/ba0bea531d953466f778498e7c4a0c80.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/100/ba0bea531d953466f778498e7c4a0c80.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_133">826元/月</span>
							</p>
							<p class="apt_bt">翡翠公寓6居室-卧-F房间</p>
					</a></li>
					<li><a href="/info-41014419103.html" target="_blank"
						title="罗庄4居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/710/2a39faf3111325fd38c7be207ef1d4a0.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/710/2a39faf3111325fd38c7be207ef1d4a0.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_2407">854元/月</span>
							</p>
							<p class="apt_bt">罗庄4居室-卧-C房间</p>
					</a></li>
					<li><a href="/info-41012709003.html" target="_blank"
						title="栖湖怡家3居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/685/38ccee116b1cb66545ad8296921da225.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/685/38ccee116b1cb66545ad8296921da225.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_2300">854元/月</span>
							</p>
							<p class="apt_bt">栖湖怡家3居室-卧-C房间</p>
					</a></li>
					<li><a href="/info-41017124503.html" target="_blank"
						title="栖湖怡家3居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/682/7f465cd8d7cf345a9c0f8897db448a0a.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/682/7f465cd8d7cf345a9c0f8897db448a0a.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_2290">854元/月</span>
							</p>
							<p class="apt_bt">栖湖怡家3居室-卧-C房间</p>
					</a></li>
					<li><a href="/info-41014239802.html" target="_blank"
						title="栖湖怡家3居室-卧-B房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/680/ad97e096070bfd73e4e0b6a7169f9458.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/680/ad97e096070bfd73e4e0b6a7169f9458.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_2282">854元/月</span>
							</p>
							<p class="apt_bt">栖湖怡家3居室-卧-B房间</p>
					</a></li>
					<li><a href="/info-41019014403.html" target="_blank"
						title="清城美苑6居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/652/b202cf8625656a3428e9f46025a51052.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/652/b202cf8625656a3428e9f46025a51052.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_2_2165">904元/月</span>
							</p>
							<p class="apt_bt">清城美苑6居室-卧-C房间</p>
					</a></li>

				</ul>
				<ul class="apt_ul" id="fj_3" style="display: none;">
					<li><a href="/info-41014419101.html" target="_blank"
						title="罗庄4居室-卧-A房间-独卫"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/710/a8d49514a29d356c5e241d1fabf125bf.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/710/a8d49514a29d356c5e241d1fabf125bf.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2405">1424元/月</span>
							</p>
							<p class="apt_bt">罗庄4居室-卧-A房间-独卫</p>
					</a></li>
					<li><a href="/info-41016419701.html" target="_blank"
						title="正商明钻4居室-卧-A房间-独卫-飘窗"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/698/0e39def4d8ddc20184aa45473f63db9a.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/698/0e39def4d8ddc20184aa45473f63db9a.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2348">1329元/月</span>
							</p>
							<p class="apt_bt">正商明钻4居室-卧-A房间-独卫-飘窗</p>
					</a></li>
					<li><a href="/info-41011939301.html" target="_blank"
						title="紫英佳苑4居室-卧-A房间-独卫-飘窗"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/681/1f1c050c1731b638cc15455729b03c44.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/681/1f1c050c1731b638cc15455729b03c44.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2284">1334元/月</span>
							</p>
							<p class="apt_bt">紫英佳苑4居室-卧-A房间-独卫-飘窗</p>
					</a></li>
					<li><a href="/info-41017798101.html" target="_blank"
						title="开元银田花园4居室-卧-A房间-阳台-独卫"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/675/002791d43add40c0f59b7811ca89c380.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/675/002791d43add40c0f59b7811ca89c380.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2263">1329元/月</span>
							</p>
							<p class="apt_bt">开元银田花园4居室-卧-A房间-阳台-独…</p>
					</a></li>
					<li><a href="/info-41011549901.html" target="_blank"
						title="和昌湾景国际4居室-卧-A房间-独卫-飘窗"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/664/ac8aee8caebea098c41eb33ad6ba6f48.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/664/ac8aee8caebea098c41eb33ad6ba6f48.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2216">1329元/月</span>
							</p>
							<p class="apt_bt">和昌湾景国际4居室-卧-A房间-独卫-飘…</p>
					</a></li>
					<li><a href="/info-41017011301.html" target="_blank"
						title="九龙城龙腾西城5居室-卧-A房间-独卫-飘窗"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/662/1dce2deca0349a50b6f83fe85ea59102.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/662/1dce2deca0349a50b6f83fe85ea59102.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_3_2207">1329元/月</span>
							</p>
							<p class="apt_bt">九龙城龙腾西城5居室-卧-A房间-独卫-…</p>
					</a></li>
				</ul>
				<ul class="apt_ul" id="fj_4" style="display: none;">
					<li><a href="/info-41016114103.html" target="_blank"
						title="栖湖怡家3居室-卧-C房间-飘窗"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/677/f4a43436d42742034de3a175a3f2175a.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/677/f4a43436d42742034de3a175a3f2175a.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_2273">800元/月</span>
							</p>
							<p class="apt_bt">栖湖怡家3居室-卧-C房间-飘窗</p>
					</a></li>
					<li><a href="/info-41015278803.html" target="_blank"
						title="紫薇里3居室-卧-C房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/375/dc4524ed361683e61a43fdec0592b1ef.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/375/dc4524ed361683e61a43fdec0592b1ef.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_1188">987元/月</span>
							</p>
							<p class="apt_bt">紫薇里3居室-卧-C房间</p>
					</a></li>
					<li><a href="/info-41011009501.html" target="_blank"
						title="六合幸福门3居室-卧-A房间-阳台"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/642/444c2bd095c44859cb1f7c77cd45c203.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/642/444c2bd095c44859cb1f7c77cd45c203.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_2119">1462元/月</span>
							</p>
							<p class="apt_bt">六合幸福门3居室-卧-A房间-阳台</p>
					</a></li>
					<li><a href="/info-41016446101.html" target="_blank"
						title="紫薇里3居室-卧-A房间"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/293/ee478129f49b8e044e559b202e3b4887.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/293/ee478129f49b8e044e559b202e3b4887.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_913">1177元/月</span>
							</p>
							<p class="apt_bt">紫薇里3居室-卧-A房间</p>
					</a></li>
					<li><a href="/info-41017563105.html" target="_blank"
						title="威尼斯水城5居室-卧-E房间-阳台"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/106/70bbd76352e684fed4a1ad6aa1733bd8.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/106/70bbd76352e684fed4a1ad6aa1733bd8.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_158">1241元/月</span>
							</p>
							<p class="apt_bt">威尼斯水城5居室-卧-E房间-阳台</p>
					</a></li>
					<li><a href="/info-41017046602.html" target="_blank"
						title="紫薇里3居室-卧-B房间-阳台"> <img class="readyload"
							src="/hotel/dist/thumb/dress_378x251/house_img/364/709039d211e735c492735b2a577d39c7.jpg"
							data-original="/hotel/dist/thumb/dress_378x251/house_img/364/709039d211e735c492735b2a577d39c7.jpg"
							width="378" height="251" style="display: inline;">
							<p class="apt_jg">
								<span id="zujin_4_1154">1283元/月</span>
							</p>
							<p class="apt_bt">紫薇里3居室-卧-B房间-阳台</p>
					</a></li>
				</ul>
				<input type="hidden" name="fangjian_id_2" id="fangjian_id_2"
					value="133,2407,2300,2290,2282,2165"> <input type="hidden"
					name="fangjian_id_3" id="fangjian_id_3"
					value="2405,2348,2284,2263,2216,2207"> <input type="hidden"
					name="fangjian_id_4" id="fangjian_id_4"
					value="2273,1188,2119,913,158,1154">
				<div class="more">
					<a title="查看更多房源" href="/list/">MORE</a>
				</div>
			</div>
		</div>

		<div class="hai">
			<div class="main">
				<h3 class="tit">青舍生活</h3>
				<div class="hai_list" id="jx_div">
					<div class="hai_lf">
						<div class="two">
							<a onmouseover="showMask(1,0)" onmouseout="hideMask(1,0)"
								href="http://www.monph.com/shenghuo/info-421.html"
								title="魔飞“绘”生活零基础绘画活动" target="_blank"> <img
								class="readyload" src="/hotel/dist/public/images/blank.gif"
								data-original="/hotel/dist/upload/home/20161123/f22a9fb377dd061dcd3f790136511a4e.jpg"
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_0">
									<span>魔飞“绘”生活零基础绘画活动</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(1,1)" onmouseout="hideMask(1,1)"
								href="http://www.monph.com/shenghuo/info-446.html"
								title="明星同款火锅，爆辣来袭" target="_blank"> <img class="readyload"
								src="/hotel/dist/public/images/blank.gif"
								data-original="/hotel/dist/upload/home/20161123/345e225babe35559b8960b8fc009cf59.jpg"
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_1">
									<span>明星同款火锅，爆辣来袭</span><input class="mask_btn" value="点击查看">
								</div>
							</a>
						</div>
						<div class="three">
							<a onmouseover="showMask(2,0)" onmouseout="hideMask(2,0)"
								href="http://www.monph.com/shenghuo/info-390.html" title="魔飞嗨歌会"
								target="_blank"> <img class="readyload"
								src="/hotel/dist/public/images/blank.gif"
								data-original="/hotel/dist/upload/home/20161123/b428c6e726bf717402d4ffecb012414c.jpg"
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_0">
									<span>魔飞嗨歌会</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,1)" onmouseout="hideMask(2,1)"
								href="http://www.monph.com/shenghuo/info-455.html?app=1"
								title="2016/17魔飞跨年音乐节" target="_blank"> <img
								class="readyload" src="/hotel/dist/public/images/blank.gif"
								data-original="/hotel/dist/upload/home/20161216/38afeb5a645e898f80febadaa66ed5f3.png"
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_1">
									<span>2016/17魔飞跨年音乐节</span><input class="mask_btn" value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,2)" onmouseout="hideMask(2,2)"
								href="http://www.monph.com/shenghuo/info-442.html"
								title="魔飞公寓首届脱单趴" target="_blank"> <img class="readyload"
								src="/hotel/dist/public/images/blank.gif"
								data-original="/hotel/dist/upload/home/20161123/143f7acc3e0699e26b178dc8aaaeb64a.jpg"
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_2">
									<span>魔飞公寓首届脱单趴</span><input class="mask_btn" value="点击查看">
								</div>
							</a>

						</div>
					</div>
					<div class="hai_rg">
						<div id="jxfocus">
							<div class="hua_d" style="left: -960px; width: 1280px;">
								<a href="/gushi/info-82.html" target="_blank" title="速度与激情，魔飞风云">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-82.html" target="_blank"
										title="速度与激情，魔飞风云">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150706/2180504d1853f6c725bec581a03eb26c.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-82.html" target="_blank"
											title="速度与激情，魔飞风云">
											<h3>速度与激情，魔飞风云</h3>
											<p>我叫程龙，2012年底退伍，我感受着这座城市带给我的美好和快乐，和魔飞的邂逅就始于那时。还记得搬进魔飞之前，我和女…...</p>
										</a><a href="/gushi/info-82.html" target="_blank"
											title="速度与激情，魔飞风云" class="all_text">阅读全文</a>
									</dt>
								</dl>

								<a href="/gushi/info-81.html" target="_blank" title="萌妹纸的魔飞小生活">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-81.html" target="_blank" title="萌妹纸的魔飞小生活">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150629/f3edcb5920aa0daa90e924e549618660.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-81.html" target="_blank"
											title="萌妹纸的魔飞小生活">
											<h3>萌妹纸的魔飞小生活</h3>
											<p>一位小学英语老师，孩子给我很多的快乐，他们简单善良，有时会在教师节出其不意的送我礼物。平时除了工作，就是喜欢和大家…...</p>
										</a><a href="/gushi/info-81.html" target="_blank"
											title="萌妹纸的魔飞小生活" class="all_text">阅读全文</a>
									</dt>
								</dl>

								<a href="/gushi/info-80.html" target="_blank" title="魔飞，我在这里">
								</a>
								<dl class="hai_dl">
									<a href="/gushi/info-80.html" target="_blank" title="魔飞，我在这里">
										<dd>
											<img
												src="/hotel/dist/thumb/dress_155x155/gushi/20150629/9dcd063a56672b52d8ebee7a26c985b6.jpg"
												width="155" height="155">
										</dd>
									</a>
									<dt>
										<a href="/gushi/info-80.html" target="_blank" title="魔飞，我在这里">
											<h3>魔飞，我在这里</h3>
											<p>毕业工作两年了，从来到魔飞公寓，第一感觉就是服务非常的不错，管家们也非常积极负责。之前有过类似的这样公寓居住的经历…...</p>
										</a><a href="/gushi/info-80.html" target="_blank" title="魔飞，我在这里"
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
											<p>从信阳到郑州，338.5公里。数字是与家相隔的距离，可是我却从未在这里失去家的舒适与温馨。第一次听到魔飞公寓，是朋…...</p>
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
					<a title="查看更多房源" href="/shenghuo/">MORE</a>
				</div>
			</div>
		</div>
		<div class="customer">
			<div class="main">
				<h3 class="tit">合作伙伴</h3>
				<div class="qy_div" id="qyfocus" style="overflow: hidden;">
					<ul class="cut_list"
						style="position: relative; height: 70px; padding: 0px; margin: 50px 0px; left: -4029.51px; width: 1920px;">
						<li><img src="%&gt;/dist/public/v1/images//hzlogo1.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo2.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo3.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo4.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo5.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo6.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo7.jpg"></li>
						<li><img src="%&gt;/dist/public/v1/images//hzlogo8.jpg"></li>
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
					<li></li>
					<li class=""></li>
					<li class=""></li>
					<li class=""></li>
					<li class=""></li>
				</ul>
			</div>
		</div>
		<div class="celan" style="top: 75%;">
			<div class="celan_list">
				<span class="weixin"><img
					src="%&gt;/dist/public/v1/images//wxx.png" width="60" height="60"></span>
				<span><a href="/down/" title="魔飞公寓官方APP" target="_blank"><img
						src="%&gt;/dist/public/v1/images//app.png" width="60" height="60"></a></span>
				<span class="customer2"><a href="javascript:;"><img
						src="%&gt;/dist/public/v1/images//kf.png" width="60" height="60"></a></span>
				<span><a href="javascript:goTop();" title="返回顶部"><img
						src="%&gt;/dist/public/v1/images//top.png" width="60" height="60"></a></span>
			</div>
			<div class="em" style="display: none;">
				<div class="em_pr">
					<img src="%&gt;/dist/public/v1/images//em.png" width="149"
						height="149"> <i></i>
				</div>
			</div>
		</div>

		<div class="wind" style="display: none;">
			<div id="dt_div_1" class="dili po_r" style="width: 930px;">
				<div id="mySwipe_1" class="swipe">
					<div class="swipe-wrap">
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_1.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_2.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_3.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_4.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_5.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_6.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_7.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/sg_8.jpg"
								width="930" height="620">
						</div>
					</div>
				</div>
				<span class="pics_pre" onclick="mySwipe.prev();" index="">&nbsp;</span>
				<span class="pics_next" onclick="mySwipe.next();" index="">&nbsp;</span>
				<span class="colse"></span> <span class="te_fg">时光系列</span> <span
					class="te_num"></span>
			</div>
			<div id="dt_div_2" class="dili po_r" style="width: 930px;">
				<div id="mySwipe_2" class="swipe">
					<div class="swipe-wrap">
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_1.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_2.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_3.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_4.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_5.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_6.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_7.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/mkl_8.jpg"
								width="930" height="620">
						</div>
					</div>
				</div>
				<span class="pics_pre" onclick="mySwipe.prev();" index="">&nbsp;</span>
				<span class="pics_next" onclick="mySwipe.next();" index="">&nbsp;</span>
				<span class="colse"></span> <span class="te_fg">马卡龙系列</span> <span
					class="te_num"></span>
			</div>
			<div id="dt_div_3" class="dili po_r" style="width: 930px;">
				<div id="mySwipe_3" class="swipe">
					<div class="swipe-wrap">
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_1.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_2.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_3.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_4.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_5.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_6.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_7.jpg"
								width="930" height="620">
						</div>
						<div>
							<img src="%&gt;/dist/public/v1/images//fg_list/ql_8.jpg"
								width="930" height="620">
						</div>
					</div>
				</div>
				<span class="pics_pre" onclick="mySwipe.prev();" index="">&nbsp;</span>
				<span class="pics_next" onclick="mySwipe.next();" index="">&nbsp;</span>
				<span class="colse"></span> <span class="te_fg">晴朗系列</span> <span
					class="te_num"></span>
			</div>
		</div>
	</article>
	<footer>
		<div class="main">
			<div class="ftr_top">
				<div class="dld clearfix">
					<div class="social">
						<a href="http://weibo.com/5763512393" rel="nofollow" title="魔飞微博"
							target="_blank" class="sina"></a> <a href="javascript:;"
							title="魔飞微信" class="wx">
							<div class="btm_em" style="display: none;">
								<div class="em_pr">
									<img src="%>/dist/public/v1/images//em.png" width="149"
										height="149"> <i></i>
								</div>
							</div>
						</a>
					</div>
					<div class="app">
						<h4>下载官方APP</h4>
						<a href="http://www.monph.com/app/Monph.apk" target="_blank"
							title="Android下载" class="android"><img
							src="%>/dist/public/v1/images//and.jpg" width="26" height="29"></a>
						<a
							href="https://itunes.apple.com/us/app/mo-fei-gong-yu-zheng-zhou/id887776293?mt=8"
							target="_blank" title="IOS下载" class="ios"><img
							src="%>/dist/public/v1/images/ios.jpg" width="26" height="29"></a>
					</div>
				</div>
				<div class="ftr_nav">
					<ul class="ftr_ul">
						<li><span>网站首页</span><a href="/list/" target="_blank"
							title="合租公寓">合租公寓</a><a href="javascript:;" title="整租公寓"
							style="display: none;">整租公寓</a><a href="/notice.html"
							target="_blank" title="租前须知">租前须知</a><a href="/shenghuo/"
							target="_blank" title="魔飞生活">魔飞生活</a></li>
						<li><span>关于魔飞</span><a href="/about_us.html" target="_blank"
							title="关于我们">关于我们</a><a href="/mianze.html" target="_blank"
							title="免责声明">免责声明</a><a href="/contact_us.html" target="_blank"
							title="联系我们">联系我们</a><a href="/join_us.html" target="_blank"
							title="加入我们">加入我们</a></li>
						<li><span>业务合作</span><a href="/qilejia.html" target="_blank"
							title="商务合作">商务合作</a><a href="/jiameng.html" target="_blank"
							title="业主加盟">业主加盟</a><a href="javascript:;"
							style="display: none;" title="广告合作">广告合作</a><a
							href="javascript:;" style="display: none;" target="_blank"
							title="友情链接">友情链接</a><a href="/webmap/" target="_blank"
							title="网站地图">网站地图</a></li>

					</ul>
					<div class="ftr_tel">
						<span class="tel400">400-0371-921</span> <span class="ftr_date"
							style="color: #dbdbdb;">周一至周六 09:00 - 20:00<br>（仅收市话费）
						</span> <span class="ol_kefu customer2">在线客服</span>
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
					| <a href="http://www.meilele.com/zhengzhou/" title="郑州家具"
						target="_blank">郑州家具</a> | <a href="http://www.weilver.com/"
						title="微驴儿机票" target="_blank">微驴儿机票</a> | <a
						href="http://www.tielu.cn/" title="铁路网" target="_blank">铁路网</a>
				</p>
				<p>Copyright © 2014-2016 monph.com 河南魔飞同创电子科技有限公司 版权所有
					豫ICP备16002206号-9</p>
			</div>
		</div>
	</footer>

	<script type="text/javascript"
		src="<%=basePath%>/dist/public/js/calendar.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/customer/js/home.js"></script>

</body>

</html>