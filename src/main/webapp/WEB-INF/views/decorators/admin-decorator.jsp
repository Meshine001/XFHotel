<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title><sitemesh:write property='title' />-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/vendor.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/flat-admin.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/iconcaomei/style.css">
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
	 <!-- 
	   <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/jquery-1.7.1.min.js"></script>
 -->


  
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
					<li><a href="<%=basePath%>/admin/dashboard">
							<div class="title"><i class="czs-layout-grid"></i>菜单主页</div>
					</a></li>
					<li><a href="<%=basePath%>/admin/apartment">
							<div class="title"><i class="czs-home-l"></i>房源管理</div>
					</a></li>
					<li><a href="<%=basePath%>/admin/customer_houseStatus">
							<div class="title"><i class="czs-home-l"></i>房态设置</div>
					</a></li>	
					<li><a href="<%=basePath%>/admin/order">
							<div class="title"><i class="czs-paper"></i>订单管理</div>
					</a></li>
					
					<li class="user"><a href="<%=basePath%>/admin/customer_list">
							<div class="title"><i class="czs-people"></i>用户管理</div>
					</a></li>

				</ul>
			</div>
			<div class="sidebar-footer">
<!-- 				<ul class="menu">
					<li class=""><a href="/" class="dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false"> <i
							class="fa fa-cogs" aria-hidden="true"></i>
					</a></li>
					<li><a href="#"><span
							class="flag-icon flag-icon-th flag-icon-squared"></span></a></li>
				</ul> -->
			</div>
		</aside>


		<div class="app-container">
			<nav class="navbar navbar-default" id="navbar">
				<div class="container-fluid">
					<div class="navbar-collapse collapse in">
						<ul class="nav navbar-nav navbar-mobile">
							<li class="logo"><a class="navbar-brand" href="<%=basePath%>/admin/dashboard"><span
									class="highlight">青舍都市</span> Admin</a></li>
							<li>
								<button type="button" class="navbar-toggle">
									<i class="czs-menu-l"></i>
								</button>
							</li>
						</ul>
						<ul class="nav navbar-nav navbar-left">
							<li class="navbar-title"><a href="<%=basePath%>/admin/dashboard" style="color:#fff;"><i class="czs-microsoft" style="margin-right:15px;font-size:18px;"></i>青舍都市公寓后台管理</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown profile">
								<a href="<%=basePath%>/admin/apartment">
									<div class="title">房源管理 </div>
								</a>
							</li>
							<li class="dropdown profile">
								<a href="<%=basePath%>/admin/order"   >
									<div class="title">订单管理 </div>
								</a>
							</li>
							<li class="dropdown profile">
								<a href="<%=basePath%>/admin/apartment">
									<div class="title">房态设置</div>
								</a>
							</li>
							<li class="dropdown profile">
								<a href="<%=basePath%>/admin/customer_list"  >
									<div class="title">用户管理 </div>
								</a>
							</li>
							<li class="dropdown profile">
								<a href="javascript:;" id="loginOuts" >
									<div class="title">退出登录 </div>
								</a>
							</li>

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
	<script>

		$(document).ready(function(){
			var userType=window.localStorage.getItem('userType');//0:超级管理员 1:管理员
			if(userType==1){
				$(".sidebar-nav .user").hide();
			}else{
				$(".sidebar-nav .user").show();
			}
			
			$("#loginout,#loginOuts").click(function(){//退出登录
				  sessionStorage.clear();
				  localStorage.removeItem('uid');
			      setTimeout(function(){
			    	  //    window.location.href='http://localhost/admin/login';   //测试地址
			         window.location.href='http://www.yiyunzn.xyz/admin/login'; // 线上地址
			      },300)  
			})
 
		})
	</script>
</body>
</html>