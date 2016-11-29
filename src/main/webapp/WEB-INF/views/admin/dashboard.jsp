<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<title>Flat Admin V.3 - Free flat-design bootstrap administrator
	templates</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/vendor.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/flat-admin.css">

<!-- Theme -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue-sky.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue.css">
<link rel="stylesheet" type="text/css" href="./assets/css/theme/red.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/yellow.css">

</head>
<body>
	<div class="app app-default">

		<aside class="app-sidebar" id="sidebar">
			<div class="sidebar-header">
				<a class="sidebar-brand" href="#"><span class="highlight">西风客</span>
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

					<li><a href="./index.html">
							<div class="icon">
								<i class="fa fa-bed" aria-hidden="true"></i>
							</div>
							<div class="title">添加房源</div>
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
									class="highlight">Flat v3</span> Admin</a></li>
							<li>
								<button type="button" class="navbar-toggle">
									<img class="profile-img" src="./assets/images/profile.png">
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
									src="./assets/images/profile.png">
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




			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
					<a class="card card-banner card-green-light">
						<div class="card-body">
							<i class="icon fa fa-shopping-basket fa-4x"></i>
							<div class="content">
								<div class="title">Sale Today</div>
								<div class="value">
									<span class="sign">$</span>420
								</div>
							</div>
						</div>
					</a>

				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
					<a class="card card-banner card-blue-light">
						<div class="card-body">
							<i class="icon fa fa-thumbs-o-up fa-4x"></i>
							<div class="content">
								<div class="title">Page Likes</div>
								<div class="value">
									<span class="sign"></span>2453
								</div>
							</div>
						</div>
					</a>

				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
					<a class="card card-banner card-yellow-light">
						<div class="card-body">
							<i class="icon fa fa-user-plus fa-4x"></i>
							<div class="content">
								<div class="title">New Registration</div>
								<div class="value">
									<span class="sign"></span>50
								</div>
							</div>
						</div>
					</a>

				</div>
			</div>

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="card card-mini">
						<div class="card-header">
							<div class="card-title">Last Statuses</div>
							<ul class="card-action">
								<li><a href="/"> <i class="fa fa-refresh"></i>
								</a></li>
							</ul>
						</div>
						<div class="card-body no-padding table-responsive">
							<table class="table card-table">
								<thead>
									<tr>
										<th>Products</th>
										<th class="right">Amount</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>MicroSD 64Gb</td>
										<td class="right">12</td>
										<td><span class="badge badge-success badge-icon"><i
												class="fa fa-check" aria-hidden="true"></i><span>Complete</span></span></td>
									</tr>
									<tr>
										<td>MiniPC i5</td>
										<td class="right">5</td>
										<td><span class="badge badge-warning badge-icon"><i
												class="fa fa-clock-o" aria-hidden="true"></i><span>Pending</span></span></td>
									</tr>
									<tr>
										<td>Mountain Bike</td>
										<td class="right">1</td>
										<td><span class="badge badge-info badge-icon"><i
												class="fa fa-credit-card" aria-hidden="true"></i><span>Confirm
													Payment</span></span></td>
									</tr>
									<tr>
										<td>Notebook</td>
										<td class="right">10</td>
										<td><span class="badge badge-danger badge-icon"><i
												class="fa fa-times" aria-hidden="true"></i><span>Cancelled</span></span></td>
									</tr>
									<tr>
										<td>Raspberry Pi2</td>
										<td class="right">6</td>
										<td><span class="badge badge-primary badge-icon"><i
												class="fa fa-truck" aria-hidden="true"></i><span>Shipped</span></span></td>
									</tr>
									<tr>
										<td>Flashdrive 128Mb</td>
										<td class="right">40</td>
										<td><span class="badge badge-info badge-icon"><i
												class="fa fa-credit-card" aria-hidden="true"></i><span>Confirm
													Payment</span></span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="card card-tab card-mini">
						<div class="card-header">
							<ul class="nav nav-tabs tab-stats">
								<li role="tab1" class="active"><a href="#tab1"
									aria-controls="tab1" role="tab" data-toggle="tab">Browsers</a>
								</li>
								<li role="tab2"><a href="#tab2" aria-controls="tab2"
									role="tab" data-toggle="tab">OS</a></li>
								<li role="tab2"><a href="#tab3" aria-controls="tab3"
									role="tab" data-toggle="tab">More</a></li>
							</ul>
						</div>
						<div class="card-body tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab1">
								<div class="row">
									<div class="col-sm-8">
										<div class="chart ct-chart-browser ct-perfect-fourth">
											<svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct"
												width="100%" height="100%" class="ct-chart-pie"
												style="width: 100%; height: 100%;">
												<g class="ct-series ct-series-e">
												<path
													d="M93.461,265.367A260.367,260.367,0,0,0,95.927,301.119L353.828,265.367Z"
													class="ct-slice-pie" value="50"></path></g>
												<g class="ct-series ct-series-d">
												<path
													d="M95.804,300.218A260.367,260.367,0,0,0,116.056,371.46L353.828,265.367Z"
													class="ct-slice-pie" value="105"></path></g>
												<g class="ct-series ct-series-c">
												<path
													d="M115.687,370.629A260.367,260.367,0,0,0,529.755,457.306L353.828,265.367Z"
													class="ct-slice-pie" value="705"></path></g>
												<g class="ct-series ct-series-b">
												<path
													d="M529.084,457.919A260.367,260.367,0,0,0,587.135,149.787L353.828,265.367Z"
													class="ct-slice-pie" value="480"></path></g>
												<g class="ct-series ct-series-a">
												<path
													d="M587.537,150.602A260.367,260.367,0,0,0,93.461,265.367L353.828,265.367Z"
													class="ct-slice-pie" value="1000"></path></g>
												<g>
												<text dx="324.37291494540875" dy="138.55962194374092"
													text-anchor="middle" class="ct-label">43%</text>
												<text dx="481.71889111270264" dy="289.69248348527094"
													text-anchor="middle" class="ct-label">21%</text>
												<text dx="326.9324183103204" dy="392.7421812912766"
													text-anchor="middle" class="ct-label">30%</text>
												<text dx="228.54411110278716" dy="300.74633310115917"
													text-anchor="middle" class="ct-label">4%</text>
												<text dx="223.93773614042874" dy="274.0995963071052"
													text-anchor="middle" class="ct-label">2%</text></g>
												<svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct"
													width="100%" height="100%" class="ct-chart-pie"
													style="width: 100%; height: 100%;">
													<g class="ct-series ct-series-c">
													<path d="M5.5,5A0.5,0.5,0,0,0,5.068,5.752L5.5,5.5Z"
														class="ct-slice-pie" value="4"></path></g>
													<g class="ct-series ct-series-b">
													<path d="M5.067,5.75A0.5,0.5,0,0,0,5.752,5.932L5.5,5.5Z"
														class="ct-slice-pie" value="3"></path></g>
													<g class="ct-series ct-series-a">
													<path d="M5.75,5.933A0.5,0.5,0,0,0,5.5,5L5.5,5.5Z"
														class="ct-slice-pie" value="5"></path></g>
													<g>
													<text dx="5.741481456572267" dy="5.43529523872437"
														text-anchor="middle" class="ct-label">42%</text>
													<text dx="5.43529523872437" dy="5.741481456572267"
														text-anchor="middle" class="ct-label">25%</text>
													<text dx="5.283493649053891" dy="5.375"
														text-anchor="middle" class="ct-label">33%</text></g></svg></svg>
										</div>
									</div>
									<div class="col-sm-4">
										<ul class="chart-label">
											<li class="ct-label ct-series-a">Google Chrome</li>
											<li class="ct-label ct-series-b">Firefox</li>
											<li class="ct-label ct-series-c">Safari</li>
											<li class="ct-label ct-series-d">IE</li>
											<li class="ct-label ct-series-e">Opera</li>
										</ul>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="tab2">
								<div class="row">
									<div class="col-sm-8">
										<div class="chart ct-chart-os ct-perfect-fourth">
											<svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct"
												width="100%" height="100%" class="ct-chart-donut"
												style="width: 100%; height: 100%;">
												<g class="ct-series ct-series-e">
												<path d="M15,5.5A-9.5,-9.5,0,0,0,14.67,3.019"
													class="ct-slice-donut" value="100"
													style="stroke-width: 20px"></path></g>
												<g class="ct-series ct-series-d">
												<path d="M14.679,3.051A-9.5,-9.5,0,0,0,12.128,-1.306"
													class="ct-slice-donut" value="205"
													style="stroke-width: 20px"></path></g>
												<g class="ct-series ct-series-c">
												<path d="M12.152,-1.283A-9.5,-9.5,0,0,0,-1.349,-1.083"
													class="ct-slice-donut" value="605"
													style="stroke-width: 20px"></path></g>
												<g class="ct-series ct-series-b">
												<path d="M-1.326,-1.107A-9.5,-9.5,0,0,0,-3.718,3.203"
													class="ct-slice-donut" value="200"
													style="stroke-width: 20px"></path></g>
												<g class="ct-series ct-series-a">
												<path d="M-3.71,3.171A-9.5,-9.5,0,1,0,15,5.5"
													class="ct-slice-donut" value="1300"
													style="stroke-width: 20px"></path></g>
												<g>
												<text dx="0.6208642175132564" dy="44.697500354181486"
													text-anchor="middle" class="ct-label">54%</text>
												<text dx="-29.004399139069548" dy="-13.727491777446602"
													text-anchor="middle" class="ct-label">8%</text>
												<text dx="4.985106243302064" dy="-33.99664396400425"
													text-anchor="middle" class="ct-label">25%</text>
												<text dx="39.621862724178435" dy="-14.398454317668044"
													text-anchor="middle" class="ct-label">9%</text>
												<text dx="44.664866623478204" dy="0.3654871345800448"
													text-anchor="middle" class="ct-label">4%</text></g>
												<svg xmlns:ct="http://gionkunz.github.com/chartist-js/ct"
													width="100%" height="100%" class="ct-chart-donut"
													style="width: 100%; height: 100%;">
													<g class="ct-series ct-series-c">
													<path d="M5.5,15A-9.5,-9.5,0,0,0,13.711,0.721"
														class="ct-slice-donut" value="4"
														style="stroke-width: 20px"></path></g>
													<g class="ct-series ct-series-b">
													<path d="M13.727,0.75A-9.5,-9.5,0,0,0,0.721,-2.711"
														class="ct-slice-donut" value="3"
														style="stroke-width: 20px"></path></g>
													<g class="ct-series ct-series-a">
													<path d="M0.75,-2.727A-9.5,-9.5,0,0,0,5.5,15"
														class="ct-slice-donut" value="5"
														style="stroke-width: 20px"></path></g>
													<g>
													<text dx="-32.6540701384182" dy="15.72335228154957"
														text-anchor="middle" class="ct-label">42%</text>
													<text dx="15.723352281549573" dy="-32.6540701384182"
														text-anchor="middle" class="ct-label">25%</text>
													<text dx="39.708003449485325" dy="25.250000000000004"
														text-anchor="middle" class="ct-label">33%</text></g></svg></svg>
										</div>
									</div>
									<div class="col-sm-4">
										<ul class="chart-label">
											<li class="ct-label ct-series-a">iOS</li>
											<li class="ct-label ct-series-b">Android</li>
											<li class="ct-label ct-series-c">Windows</li>
											<li class="ct-label ct-series-d">OSX</li>
											<li class="ct-label ct-series-e">Linux</li>
										</ul>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="tab3"></div>
						</div>
					</div>
				</div>
			</div>
			<footer class="app-footer">
				<div class="row">
					<div class="col-xs-12">
						<div class="footer-copyright">版权所有 © 2016 西风客公寓酒店.</div>
					</div>
				</div>
			</footer>
		</div>

	</div>
	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/vendor.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/app.js"></script>
	<div class="autocomplete-suggestions"
		style="position: absolute; display: none; max-height: 300px; z-index: 9999;"></div>


</body>
</html>