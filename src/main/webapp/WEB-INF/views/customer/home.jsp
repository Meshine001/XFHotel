<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>青舍公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<%=basePath%>/dist/customer/css/bootstrap.css"
	rel="stylesheet">
<link href="<%=basePath%>/dist/customer/css/style.css" rel="stylesheet">
<link rel='stylesheet' id='prettyphoto-css'
	href="<%=basePath%>/dist/customer/css/prettyPhoto.css" type='text/css'
	media='all'>
<link href="<%=basePath%>/dist/customer/css/fontello.css"
	type="text/css" rel="stylesheet">
<!--[if lt IE 7]>
<link href="css/fontello-ie7.css" type="text/css" rel="stylesheet">  
<![endif]-->
<link href='http://fonts.googleapis.com/css?family=Quattrocento:400,700'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Patua+One'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<style type="text/css">
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="<%=basePath%>/dist/customer/css/bootstrap-responsive.css"
	rel="stylesheet">
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<![endif]-->
<script src="<%=basePath%>/dist/customer/js/jquery.js"></script>
<script
	src="<%=basePath%>/dist/customer/js/jquery.scrollTo-1.4.2-min.js"></script>
<script
	src="<%=basePath%>/dist/customer/js/jquery.localscroll-1.2.7-min.js"></script>
<script charset="utf-8">
	$(document).ready(function() {
		$("a[rel^='prettyPhoto']").prettyPhoto();
	});
</script>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a>
					<h1 class="brand">
						<a href="#top">青舍公寓</a>
					</h1>
					<nav class="pull-right nav-collapse collapse">
						<ul id="menu-main" class="nav">
							<li><a title="home" href="">首页</a></li>
							<li><a title="services" href="#services">在线找房</a></li>
							<li><a title="news" href="#news">青舍生活</a></li>
							<li><a title="team" href="#team">服务中心</a></li>
							<li><a title="contact" href="#contact">在线管家</a></li>
							<li><a title="login">登录</a></li>
							<li><a title="register">注册</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<div id="top"></div>
	<div id="headerwrap">
		<header class="clearfix">
			<h1>
				<span>青舍公寓</span> We make world a beautiful place.
			</h1>
			<div class="container">
				<div class="row">
					<div class="span12">
						<h2></h2>
						<input type="text" name="your-email" placeholder="搜索位置/区域/关键字"
							class="cform-text" size="40" title="your email"> <input
							type="submit" value="搜索公寓" class="cform-submit">
					</div>
				</div>
				<div class="row">
					<div class="span12">
						<ul class="icon">
							<li><a href="#"><i class="icon-pinterest-circled"></i></a></li>
							<li><a href="#"><i class="icon-facebook-circled"></i></a></li>
							<li><a href="#"><i class="icon-twitter-circled"></i></a></li>
							<li><a href="#"><i class="icon-gplus-circled"></i></a></li>
							<li><a href="#"><i class="icon-skype-circled"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
	</div>
	<div class="scrollblock">
		<section id="feature">
			<div class="container">
				<div class="row">
					<div class="span12">
						<article>
							<p>We work to make world a beautiful place.</p>
							<p>We craft beautiful designs and convert them into</p>
							<p>fully functional and user-friendy web app.</p>
						</article>
					</div>
				</div>
			</div>
		</section>
	</div>
	<hr>
	<section id="portfolio" class="single-page scrollblock">
		<div class="container">
			<div class="align">
				<i class="icon-desktop-circled"></i>
			</div>
			<h1 id="folio-headline">Portfolio...</h1>
			<div class="row">
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-01.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-01.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>Ethan Marcotte Design</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-02.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-02.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>A Book Apart</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-03.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-03.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>Four Rules for Designers</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-01.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-03.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>Ethan Marcotte Design</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-02.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-01.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>A Book Apart</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="mask2">
						<a href="<%=basePath%>/dist/customer/img/portfolio-03.jpg" rel="prettyPhoto"><img
							src="<%=basePath%>/dist/customer/img/portfolio-02.jpg" alt=""></a>
					</div>
					<div class="inside">
						<hgroup>
							<h2>Four Rules for Designers</h2>
						</hgroup>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s, when an unknown
								printer took a galley of type and scrambled it to make a type
								specimen book.</p>
							<a class="more-link" href="#">view project</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<hr>
	<section id="services" class="single-page scrollblock">
		<div class="container">
			<div class="align">
				<i class="icon-cog-circled"></i>
			</div>
			<h1>Services</h1>
			<div class="row">
				<div class="span3">
					<div class="align">
						<i class="icon-desktop sev_icon"></i>
					</div>
					<h2>Web design</h2>
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s.</p>
				</div>
				<div class="span3">
					<div class="align">
						<i class="icon-vector sev_icon"></i>
					</div>
					<h2>Print Design</h2>
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s.</p>
				</div>
				<div class="span3">
					<div class="align">
						<i class="icon-basket sev_icon"></i>
					</div>
					<h2>Ecommerce</h2>
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s.</p>
				</div>
				<div class="span3">
					<div class="align">
						<i class="icon-mobile-1 sev_icon"></i>
					</div>
					<h2>Marketing</h2>
					<p>Lorem Ipsum is simply dummy text of the printing and
						typesetting industry. Lorem Ipsum has been the industry's standard
						dummy text ever since the 1500s.</p>
				</div>
			</div>
		</div>
	</section>
	<hr>
	<section id="testimonials" class="single-page hidden-phone">
		<div class="container">
			<div class="row">
				<div class="blockquote-wrapper">
					<blockquote class="mega">
						<div class="span4">
							<p class="cite">John Doe &amp; Sons:</p>
						</div>
						<div class="span8">
							<p class="alignright">"We highly appreciate the enthusiasm
								and creative talent of the people at Legend!"</p>
						</div>
					</blockquote>
				</div>
			</div>
		</div>
	</section>
	<hr>
	<section id="news" class="single-page scrollblock">
		<div class="container">
			<div class="align">
				<i class="icon-pencil-circled"></i>
			</div>
			<h1>Our Blog</h1>
			<div class="row">
				<article class="span4 post">
					<img class="img-news" src="<%=basePath%>/dist/customer/img/blog_img-01.jpg" alt="">
					<div class="inside">
						<p class="post-date">
							<i class="icon-calendar"></i> March 17, 2013
						</p>
						<h2>A girl running on a road</h2>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s. &hellip;</p>
							<a href="#" class="more-link">read more</a>
						</div>
					</div>
				</article>
				<article class="span4 post">
					<img class="img-news" src="<%=basePath%>/dist/customer/img/blog_img-02.jpg" alt="">
					<div class="inside">
						<p class="post-date">February 28, 2013</p>
						<h2>A bear sleeping on a tree</h2>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s. &hellip;</p>
							<a href="#" class="more-link">read more</a>
						</div>
					</div>
				</article>
				<article class="span4 post">
					<img class="img-news" src="<%=basePath%>/dist/customer/img/blog_img-03.jpg" alt="">
					<div class="inside">
						<p class="post-date">February 06, 2013</p>
						<h2>A Panda playing with his baby</h2>
						<div class="entry-content">
							<p>Lorem Ipsum is simply dummy text of the printing and
								typesetting industry. Lorem Ipsum has been the industry's
								standard dummy text ever since the 1500s. &hellip;</p>
							<a href="#" class="more-link">read more</a>
						</div>
					</div>
				</article>
			</div>
			<a href="#" class="btn btn-large">Go to our blog</a>
		</div>
	</section>
	<hr>
	<section id="team" class="single-page scrollblock">
		<div class="container">
			<div class="align">
				<i class="icon-group-circled"></i>
			</div>
			<h1>Meet the team</h1>
			<div class="row">
				<div class="span2 offset1">
					<div class="teamalign">
						<img class="team-thumb img-circle" src="<%=basePath%>/dist/customer/img/portrait-1.jpg" alt="">
					</div>
					<h3>Andrew</h3>
					<div class="job-position">web designer</div>
				</div>
				<div class="span2">
					<div class="teamalign">
						<img class="team-thumb img-circle" src="<%=basePath%>/dist/customer/img/portrait-2.jpg" alt="">
					</div>
					<h3>Stephen</h3>
					<div class="job-position">web developer</div>
				</div>
				<div class="span2">
					<div class="teamalign">
						<img class="team-thumb img-circle" src="<%=basePath%>/dist/customer/img/portrait-3.jpg" alt="">
					</div>
					<h3>Maria</h3>
					<div class="job-position">graphic designer</div>
				</div>
				<div class="span2">
					<div class="teamalign">
						<img class="team-thumb img-circle" src="<%=basePath%>/dist/customer/img/portrait-4.jpg" alt="">
					</div>
					<h3>John</h3>
					<div class="job-position">project manager</div>
				</div>
				<div class="span2">
					<div class="teamalign">
						<img class="team-thumb img-circle" src="<%=basePath%>/dist/customer/img/portrait-2.jpg" alt="">
					</div>
					<h3>Ashton</h3>
					<div class="job-position">real owner</div>
				</div>
			</div>
			<div class="row">
				<div class="span10 offset1">
					<hr class="featurette-divider">
					<div class="featurette">
						<h2 class="featurette-heading">
							Want to know more? <span class="muted">| a little about us</span>
						</h2>
						<p>Li Europan lingues es membres del sam familie. Lor separat
							existentie es un myth. Por scientie, musica, sport etc, litot
							Europa usa li sam vocabular. Li lingues differe solmen in li
							grammatica, li pronunciation e li plu commun vocabules. Omnicos
							directe al desirabilite de un nov lingua franca: On refusa
							continuar payar custosi traductores.</p>
						<p>At solmen va esser necessi far uniform grammatica,
							pronunciation e plu sommun paroles. Ma quande lingues coalesce,
							li grammatica del resultant lingue es plu simplic e regulari quam
							ti del coalescent lingues.</p>
						<p>A un Angleso it va semblar un simplificat Angles, quam un
							skeptic Cambridge amico dit me que Occidental es.</p>
					</div>
					<hr class="featurette-divider">
				</div>
			</div>
		</div>
	</section>
	<section id="contact" class="single-page scrollblock">
		<div class="container">
			<div class="align">
				<i class="icon-mail-2"></i>
			</div>
			<h1>Contact us now!</h1>
			<div class="row">
				<div class="span12">
					<div class="cform" id="theme-form">
						<form action="#" method="post" class="cform-form">
							<div class="row">
								<div class="span6">
									<span class="your-name"> <input type="text"
										name="your-name" placeholder="Your Name" class="cform-text"
										size="40" title="your name">
									</span>
								</div>
								<div class="span6">
									<span class="your-email"> <input type="text"
										name="your-email" placeholder="Your Email" class="cform-text"
										size="40" title="your email">
									</span>
								</div>
							</div>
							<div class="row">
								<div class="span6">
									<span class="company"> <input type="text" name="company"
										placeholder="Your Company" class="cform-text" size="40"
										title="company">
									</span>
								</div>
								<div class="span6">
									<span class="website"> <input type="text" name="website"
										placeholder="Your Website" class="cform-text" size="40"
										title="website">
									</span>
								</div>
							</div>
							<div class="row">
								<div class="span12">
									<span class="message"> <textarea name="message"
											class="cform-textarea" cols="40" rows="10"
											title="drop us a line."></textarea>
									</span>
								</div>
							</div>
							<div>
								<input type="submit" value="Send message"
									class="cform-submit pull-left">
							</div>
							<div class="cform-response-output"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<hr>
	<div class="footer-wrapper">
		<div class="container">
			<footer>
				<small>&copy; 2013 Inbetwin Network. All rights reserved. |
					Template By: <a href="http://www.dzyngiri.com">Dzyngiri</a>
				</small>
			</footer>
		</div>
	</div>
	<script src="<%=basePath%>/dist/customer/js/bootstrap.js"></script>
	<script src="<%=basePath%>/dist/customer/js/jquery.prettyPhoto.js"></script>
	<script src="<%=basePath%>/dist/customer/js/site.js"></script>
</body>
</html>