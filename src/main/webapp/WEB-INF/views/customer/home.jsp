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

<title>都市公寓-西安租房_西安合租</title>

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
	href="<%=basePath%>/dist/commons/awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/calendar.css">
<script src="<%=basePath%>/dist/public/js/jquery.colorbox-min.js"
	type="text/javascript"></script>
<script src="<%=basePath%>/dist/public/js/swipe.js"
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

#newBridge ins#nb_icon_wrap {
    display: none;
}
</style>
</head>
<body>
	<jsp:include page="../decorators/home-header.jsp"></jsp:include>
	<article>
		<div class="banner po_r">
			<c:forEach items="${homeBanner}" var="banner">
				<a href="${banner.linkUrl }" title="${banner.title}" target="_blank"
					style="width: 100%; height: 100%;">
					<div class="hr_img banner_img img-responsive"
						style="position: absolute; width: 100%; height: 100%; background: url(&quot;<%=basePath%>/images/${banner.pic}&quot;) center 0px no-repeat; display: block; ;">
					</div>
				</a>
			</c:forEach>
			<a href="" title="" target="" style="width: 100%; height: 100%;">
				<div class="hr_img banner_img"
					style="position: absolute; width: 100%; height: 100%; background: url(&quot;dist/public/v1/images/H-315.png&quot;) center 0px no-repeat; background-size:100% 100%;display: block; ;">
				</div>
			</a>
			<a href="" title="" target="" style="width: 100%; height: 100%;">
				<div class="hr_img banner_img"
					style="position: absolute; width: 100%; height: 100%; background: url(&quot;dist/public/v1/images/ttttttttK.png&quot;) center 0px no-repeat; background-size:100% 100%;display: block; ;">
				</div>
			</a>
			
			<span class="pics_pre1" style="" onclick="pre_pic()"></span> <span
				class="pics_next1" style="" onclick="next_pic()"></span>
			<div class="main">
				<form action="<%=basePath%>/homeSearch" method="get"
					name="searchForm" id="searchForm">
					<div class="search clearfix">
						<div class="search_input">
							<span style="display: none;"><i class="icon-calendar"
								style="display: none;"></i></span><input style="display: none;"
								readonly="readonly" id="search-input-time" placeholder="入住时间"
								type="text" class="search-input-item"><span><i
								class="icon-search"></i></span><input id="search-input-txt"
								placeholder="" type="text" value="" class="search-input-item">
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
				<p style="text-align:center;font-family: -webkit-pictograph;font-size:16px">Here's what you want </p>
				<div class="exm_nav fj_nav">
					<span id="fj_on_1" onmouseover="on_fj(1);" class="exm_nav_on"></span>
				</div>
				<ul class="apt_ul" id="fj_1" style="display: block;">
					<c:forEach items="${homeRoom}" var="room">
						<li><a href="<%=basePath%>/info/${room.id}"
							title="${room.square}"> <img class="readyload"
								src="<%=basePath%>/images/${room.fang_jian_tu[0]}" data-original=""
								width="378" height="251" style="display: inline;">
								<p class="apt_jg">
									<span>${room.basic_info.jia_ge}</span>元/天
								</p>
								<p class="apt_bt">${room.basic_info.lei_xing}-${room.position.xa_wei_zhi}-${room.position.xiao_qu}</p>
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
					<a title="查看更多房源" href="">查看更多房源</a>
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
				<p style="text-align:center;font-family: -webkit-pictograph;font-size:16px">Ultimate life</p>
				<div class="exm_nav fj_nav">
					<span class="exm_nav_on"></span>
				</div>
				<div class="hai_list" id="jx_div">
					<div class="hai_lf">
						<div class="two">
							<a onmouseover="showMask(1,0)" onmouseout="hideMask(1,0)"
								href="<%=basePath%>/story/blog_content?id=${homeBlog[0].id}"
								title="${homeBlog[0].title}" target="_blank"> <img
								class="readyload" src="${homeBlog[0].pic}" data-original=""
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_0">
									<span>${homeBlog[0].title}</span><input class="mask_btn"
										value="点击查看">
								</div>
							</a> <a onmouseover="showMask(1,1)" onmouseout="hideMask(1,1)"
								href="<%=basePath%>/story/blog_content?id=${homeBlog[1].id}"
								title="${homeBlog[1].title}" target="_blank"> <img
								class="readyload" src="${homeBlog[1].pic}" data-original=""
								width="425" height="265">
								<div class="mask" style="display: none;" id="mask_1_1">
									<span>${homeBlog[1].title}</span><input class="mask_btn"
										value="点击查看">
								</div>
							</a>
						</div>
						<div class="three">

							<a onmouseover="showMask(2,0)" onmouseout="hideMask(2,0)"
								href="<%=basePath%>/story/blog_content?id=${homeBlog[2].id}"
								title="${homeBlog[2].title}" target="_blank"> <img
								class="readyload" src="${homeBlog[2].pic}" data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_0">
									<span>${homeBlog[2].title}</span><input class="mask_btn"
										value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,1)" onmouseout="hideMask(2,1)"
								href="<%=basePath%>/story/blog_content?id=${homeBlog[3].id}"
								title="${homeBlog[3].title}" target="_blank"> <img
								class="readyload" src="${homeBlog[3].pic}" data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_1">
									<span>${homeBlog[3].title}</span><input class="mask_btn"
										value="点击查看">
								</div>
							</a> <a onmouseover="showMask(2,2)" onmouseout="hideMask(2,2)"
								href="<%=basePath%>/story/blog_content?id=${homeBlog[4].id}"
								title="${homeBlog[4].title}" target="_blank"> <img
								class="readyload" src="${homeBlog[4].pic}" data-original=""
								width="278" height="257">
								<div class="mask" style="display: none;" id="mask_2_2">
									<span>${homeBlog[4].title}</span><input class="mask_btn"
										value="点击查看">
								</div>
							</a>

						</div>
					</div>
					<div class="hai_rg">
						<div id="jxfocus">
							<div class="hua_d" style="left: -960px; width: 1280px;">
								<a href="<%=basePath%>/story/blog_content?id=${homeBlog[5].id}"
									target="_blank" title="${homeBlog[5].title}"> </a>
								<dl class="hai_dl">
									<a href="<%=basePath%>/story/blog_content?id=${homeBlog[5].id}"
										target="_blank" title="${homeBlog[5].title}">
										<dd>
											<img src="${homeBlog[5].pic}" width="155" height="155">
										</dd>
									</a>
									<dt>
										<a
											href="<%=basePath%>/story/blog_content?id=${homeBlog[5].id}"
											target="_blank" title="${homeBlog[5].title}">
											<h3>${homeBlog[5].title}</h3>
											<p>${homeBlog[5].abs_text}</p>
										</a><a
											href="<%=basePath%>/story/blog_content?id=${homeBlog[5].id}"
											target="_blank" title="${homeBlog[5].title}" class="all_text">阅读全文</a>
									</dt>
								</dl>
								<a href="<%=basePath%>/story/blog_content?id=${homeBlog[6].id}"
									target="_blank" title="${homeBlog[5].title}"> </a>
								<dl class="hai_dl">
									<a href="<%=basePath%>/story/blog_content?id=${homeBlog[6].id}"
										target="_blank" title="${homeBlog[6].title}">
										<dd>
											<img src="${homeBlog[6].pic}" width="155" height="155">
										</dd>
									</a>
									<dt>
										<a
											href="<%=basePath%>/story/blog_content?id=${homeBlog[6].id}"
											target="_blank" title="${homeBlog[6].title}">
											<h3>${homeBlog[6].title}</h3>
											<p>${homeBlog[6].abs_text}</p>
										</a><a
											href="<%=basePath%>/story/blog_content?id=${homeBlog[6].id}"
											target="_blank" title="${homeBlog[6].title}" class="all_text">阅读全文</a>
									</dt>
								</dl>

							</div>
						</div>
						<ul class="hai_ul">
							<li class="hai_on"></li>
							<li class=""></li>
						</ul>
					</div>
				</div>
				<div class="more">
					<a title="浏览更多" href="">浏览更多</a>
				</div>
			</div>
		</div>
		<!--<div class="customer">
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
		</div>-->
		<div class="celan" style="top: 75%;">
			<div class="celan_list">
				<span class="weixin"><img
					src="<%=basePath%>/dist/public/v1/images/wxx.png" width="60"
					height="60"></span> <span><a href="/down/" title="官方APP"
					target="_blank"><img
						src="<%=basePath%>/dist/public/v1/images/app.png" width="60"
						height="60"></a></span> <span class="customer2 online-chat"><a
					href="javascript:;"><img
						src="<%=basePath%>/dist/public/v1/images/kf.png" width="60"
						height="60"></a></span> <span><a href="javascript:goTop();"
					title="返回顶部"><img
						src="<%=basePath%>/dist/public/v1/images/top.png" width="60"
						height="60"></a></span>
			</div>
			<div class="em" style="display: none;">
				<div class="em_pr">
					<img src="<%=basePath%>/dist/public/v1/images/em.jpg" width="149"
						height="149"> <i></i>
				</div>
			</div>
		</div>

	</article>

	<jsp:include page="../decorators/home-footer.jsp"></jsp:include>
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?de892eb7bcb9d66253676ee4d1a31276";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
		$('.online-chat').click(function(){
			$('#nb_icon_wrap').click();
		});
	</script>

	<script type="text/javascript"
		src="<%=basePath%>/dist/public/js/calendar.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/customer/js/home.js"></script>
</body>

</html>