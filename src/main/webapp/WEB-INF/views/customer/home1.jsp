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

<!-- Custom styles for this template -->
<link href="<%=basePath%>/dist/customer/css/carousel.css"
	rel="stylesheet">
</head>
<body>
	<!-- 导航 -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
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
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="">酒店型公寓</a></li>
					<li><a href="">短租型公寓</a></li>
					<li><a href="">在线找房</a></li>
					<li><a href="">青客生活</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
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
	</div>

	<!--./滑动窗口 -->





	<div class="container">
		<!-- 搜索框 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form class="" role="search">
					<div class="input-group">
						<input type="text" class="form-control"> <span
							class="input-group-addon"><button>搜索公寓</button></span>
					</div>
				</form>
			</div>
		</div>

		<!-- ./搜索框 -->
		<hr class="featurette-divider">
		<!-- 服务 -->
		<h2>我们提供什么</h2>
		<div class="row">
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="media">
					<div class="media-left media-middle">
						<img alt="" src="<%=basePath%>/images/info.png">
					</div>
					<div class="media-body">
						<h4 class="media-heading">押一付一</h4>
						<p>房租月付资金无压力</p>
					</div>
				</div>
			</div>
		</div>
		<!-- ./服务 -->

		<hr class="featurette-divider">
		<!-- 样板 -->
		<h2>风格样板间</h2>
		<div class="row">
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-01.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-02.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-03.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-04.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-05.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-06.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-01.jpg"
					class="img-responsive ">
			</div>
			<div class="col-md-3" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-03.jpg"
					class="img-responsive ">
			</div>
		</div>
		<!-- ./样板 -->
		<hr class="featurette-divider">
		<!-- 精选 -->
		<h2>精选公寓</h2>
		<div class="row">
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-01.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-04.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-03.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-02.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-05.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
			<div class="col-md-4" style="padding: 10px;">
				<img alt="" src="<%=basePath%>/dist/customer/img/apartment-06.jpg"
					class="img-responsive ">
				<h3>800元/月</h3>
				<p>紫英佳苑4居室-卧-C房间-阳台</p>
			</div>
		</div>
		<!-- ./精选 -->




		<hr class="featurette-divider">
		<h2>青舍生活</h2>
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					魔飞“绘”生活零基础<span class="text-muted">绘画活动</span>
				</h2>
				<p class="lead">喝喝茶 聊聊天 画会画儿 远离喧嚣快节奏，让我们一起在暖暖的秋日午后
					拿着画笔，专注于涂鸦一件事~ 我们每个人都是生活中的艺术家 哪怕你是画画小白！也请放下手机 和魔飞一起，和InArt
					House印绘馆一起 在慢音乐中，画出自己的故事 送给自己一个午后慢生活.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive"
					data-src="holder.js/500x500/auto" alt="500x500"
					src="http://mf.znimg.com/thumb/dress_620x350//gushi/20161128/ffc829177ec87e07e270c5622e025235.jpg"
					data-holder-rendered="true">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-5">
				<img class="featurette-image img-responsive"
					data-src="holder.js/500x500/auto" alt="500x500"
					src="http://mf.znimg.com/upload/gushi/20161207/dc26d7aaea09c732da0d63eaa9d86ef7.jpg"
					data-holder-rendered="true">
			</div>
			<div class="col-md-7">
				<h2 class="featurette-heading">温馨冬至包饺子，这个冬天不太冷</h2>
				<p class="lead">冬至到，吃水饺 每天吃外卖的你，是不是很久没有体会到围坐一桌，盘陷儿、和面、擀皮、包饺子的场景。
					每到这一天，电话总会响起，接起电话，耳畔传来爸妈那亲切关怀的声音：“今天吃饺子了吗？别忘了吃饺子啊！！”独自在外，需要的就是这份温暖的关心。
					现在，你不是一个人了！！！ 魔飞为答谢各位小伙伴们一年来的陪伴与支持，我们将上门和大家一起包饺子，陪大家过冬至，并送上节日大礼包！
					为了让父母放心，为了不让生活变得只剩麻木，为了在这个冬天不被冻掉耳朵，快和魔飞家人们，一起加入到包饺子活动中来吧！！让魔飞带你吃在嘴里，暖在心里！

					活动时间：2016年12月21日周三晚 活动地点：租客房间内
					报名详情：魔飞APP嗨生活版块凭99积分报名（注：租客以整套房为单位，选一人报名即可，如报名成功，屋内所有租客都可参与，报名成功与否以短信通知为准）。</p>
			</div>
		</div>



		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<footer>

			<p>
				© 2014 青舍都市公寓, 西安青舍都市公寓服务有限公司. · <a href="#">版权所有</a> · <a href="#"></a>
			</p>
		</footer>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/dist/customer/assets/js/docs.min.js"></script>
</body>
</html>