<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>title-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/vendor.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/flat-admin.css">
<!-- 
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue-sky.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/red.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/yellow.css">
	 -->
<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
<sitemesh:write property='my_header' />
</head>
<body>
	<div class="app app-default">
		<aside class="app-sidebar" id="sidebar">
			<div class="sidebar-header">
				<a class="sidebar-brand" href="#"><span class="highlight">青舍都市</span>
					Admin</a>
				<button type="button" class="sidebar-toggle">
					<i class="fa fa-times"></i>
				</button>
			</div>
			<div class="sidebar-menu">
				<ul class="sidebar-nav">
					<li class="active"><a href="./index.html">
							<div class="icon">
								<i class="fa fa-tasks" aria-hidden="true"></i>
							</div>
							<div class="title">控制面板</div>
					</a></li>

					<li><a href="<%=basePath%>/admin/apartment">
							<div class="icon">
								<i class="fa fa-bed" aria-hidden="true"></i>
							</div>
							<div class="title">房源管理</div>
					</a></li>

					<li><a href="<%=basePath%>/admin/order">
							<div class="icon">
								<i class="fa fa-shopping-cart" aria-hidden="true"></i>
							</div>
							<div class="title">订单管理</div>
					</a></li>
					<li><a href="<%=basePath%>/admin/customer_list">
							<div class="icon">
								<i class="fa fa-users" aria-hidden="true"></i>
							</div>
							<div class="title">用户管理</div>
					</a></li>
					<li><a href="<%=basePath%>/admin/system">
							<div class="icon">
								<i class="fa fa-cog" aria-hidden="true"></i>
							</div>
							<div class="title">系统设置</div>
					</a></li>

				</ul>
			</div>
			<div class="sidebar-footer">
				<ul class="menu">
					<li class=""><a href="/" class="dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false"> <i
							class="fa fa-cogs" aria-hidden="true"></i>
					</a></li>
					<li><a href="#"><span
							class="flag-icon flag-icon-th flag-icon-squared"></span></a></li>
				</ul>
			</div>
		</aside>

		<div class="app-container">
			<nav class="navbar navbar-default" id="navbar">
				<div class="container-fluid">
					<div class="navbar-collapse collapse in">
						<ul class="nav navbar-nav navbar-mobile">
							<li>
								<button type="button" class="sidebar-toggle">
									<i class="fa fa-bars"></i>
								</button>
							</li>
							<li class="logo"><a class="navbar-brand" href="#"><span
									class="highlight">青舍都市</span> Admin</a></li>
							<li>
								<button type="button" class="navbar-toggle">
									<img class="profile-img"
										src="<%=basePath%>/dist/admin/assets/images/profile.png">
								</button>
							</li>
						</ul>
						<ul class="nav navbar-nav navbar-left">
							<li class="navbar-title">Dashboard</li>
							<li class="navbar-search hidden-sm"><input id="search"
								type="text" placeholder="Search.." autocomplete="off">
								<button class="btn-search">
									<i class="fa fa-search"></i>
								</button></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown notification"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">
									<div class="icon">
										<i class="fa fa-shopping-basket" aria-hidden="true"></i>
									</div>
									<div class="title">New Orders</div>
									<div class="count">0</div>
							</a>
								<div class="dropdown-menu">
									<ul>
										<li class="dropdown-header">Ordering</li>
										<li class="dropdown-empty">No New Ordered</li>
										<li class="dropdown-footer"><a href="#">View All <i
												class="fa fa-angle-right" aria-hidden="true"></i></a></li>
									</ul>
								</div></li>
							<li class="dropdown notification warning"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">
									<div class="icon">
										<i class="fa fa-comments" aria-hidden="true"></i>
									</div>
									<div class="title">Unread Messages</div>
									<div class="count">99</div>
							</a>
								<div class="dropdown-menu">
									<ul>
										<li class="dropdown-header">Message</li>
										<li><a href="#"> <span
												class="badge badge-warning pull-right">10</span>
												<div class="message">
													<img class="profile" src="https://placehold.it/100x100">
													<div class="content">
														<div class="title">"Payment Confirmation.."</div>
														<div class="description">Alan Anderson</div>
													</div>
												</div>
										</a></li>
										<li><a href="#"> <span
												class="badge badge-warning pull-right">5</span>
												<div class="message">
													<img class="profile" src="https://placehold.it/100x100">
													<div class="content">
														<div class="title">"Hello World"</div>
														<div class="description">Marco Harmon</div>
													</div>
												</div>
										</a></li>
										<li><a href="#"> <span
												class="badge badge-warning pull-right">2</span>
												<div class="message">
													<img class="profile" src="https://placehold.it/100x100">
													<div class="content">
														<div class="title">"Order Confirmation.."</div>
														<div class="description">Brenda Lawson</div>
													</div>
												</div>
										</a></li>
										<li class="dropdown-footer"><a href="#">View All <i
												class="fa fa-angle-right" aria-hidden="true"></i></a></li>
									</ul>
								</div></li>
							<li class="dropdown notification danger"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">
									<div class="icon">
										<i class="fa fa-bell" aria-hidden="true"></i>
									</div>
									<div class="title">System Notifications</div>
									<div class="count">10</div>
							</a>
								<div class="dropdown-menu">
									<ul>
										<li class="dropdown-header">Notification</li>
										<li><a href="#"> <span
												class="badge badge-danger pull-right">8</span>
												<div class="message">
													<div class="content">
														<div class="title">New Order</div>
														<div class="description">$400 total</div>
													</div>
												</div>
										</a></li>
										<li><a href="#"> <span
												class="badge badge-danger pull-right">14</span> Inbox
										</a></li>
										<li><a href="#"> <span
												class="badge badge-danger pull-right">5</span> Issues Report
										</a></li>
										<li class="dropdown-footer"><a href="#">View All <i
												class="fa fa-angle-right" aria-hidden="true"></i></a></li>
									</ul>
								</div></li>
							<li class="dropdown profile"><a
								href="/html/pages/profile.html" class="dropdown-toggle"
								data-toggle="dropdown"> <img class="profile-img"
									src="<%=basePath%>/dist/admin/assets/images/profile.png">
									<div class="title">Profile</div>
							</a>
								<div class="dropdown-menu">
									<div class="profile-info">
										<h4 class="username">Scott White</h4>
									</div>
									<ul class="action">
										<li><a href="#"> Profile </a></li>
										<li><a href="#"> <span
												class="badge badge-danger pull-right">5</span> My Inbox
										</a></li>
										<li><a href="#"> Setting </a></li>
										<li><a href="#"> Logout </a></li>
									</ul>
								</div></li>
						</ul>
					</div>
				</div>
			</nav>
			<sitemesh:write property='my_body' />
			<footer class="app-footer">
				<div class="row">
					<div class="col-xs-12">
						<div class="footer-copyright">版权所有 © 2016 青舍都市公寓酒店.</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/vendor.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/app.js"></script>
	<sitemesh:write property='my_script' />
</body>
</html>