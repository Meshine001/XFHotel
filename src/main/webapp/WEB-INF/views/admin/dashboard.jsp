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
	<my_body>
	<div class="row">
		<div class="col-xs-12">
			<div class="card card-banner card-chart card-green no-br">
				<div class="card-header">
					<div class="card-title">
						<div class="title">Top Sale Today</div>
					</div>
					<ul class="card-action">
						<li><a href="/"> <i class="fa fa-refresh"></i>
						</a></li>
					</ul>
				</div>
				<div class="card-body"></div>
			</div>
		</div>
	</div>
	<div class="row"  style="display: none;">
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
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-orange-light" href="<%=basePath%>/admin/apartment">
				<div class="card-body">
					<i class="icon fa fa-bed fa-4x"></i>
					<div class="content">
						<div class="title">房源管理</div>
						<div class="value">
							<span class="sign"></span>${apartmentCount}
						</div>
					</div>
				</div>
			</a>

		</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-green-light" href="<%=basePath%>/admin/order">
				<div class="card-body">
					<i class="icon fa fa-shopping-cart fa-4x"></i>
					<div class="content">
						<div class="title">订单管理</div>
						<div class="value">
							<span class="sign"></span>${orderCount}
						</div>
					</div>
				</div>
			</a>

		</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-yellow-light" href="<%=basePath%>/admin/customer_list">
				<div class="card-body">
					<i class="icon fa fa-users fa-4x"></i>
					<div class="content">
						<div class="title">用户管理</div>
						<div class="value">
							<span class="sign"></span>${customerCount}
						</div>
					</div>
				</div>
			</a>

		</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-blue-light" href="<%=basePath%>/admin/blog/list">
				<div class="card-body">
					<i class="icon fa fa-tags fa-4x"></i>
					<div class="content">
						<div class="title">青舍生活</div>
						<div class="value">
							<span class="sign"></span>${blogCount}
						</div>
					</div>
				</div>
			</a>

		</div>
		  <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-blue-light" href="<%=basePath%>/admin/customer_sendlist">
				<div class="card-body">
					<i class="icon fa fa-tags fa-4x"></i>
					<div class="content"> 
						<div class="title">优惠卷管理</div>
						<div class="value">
							<span class="sign"></span>${blogCount}
						</div>
					</div>
				</div>
			</a>

		</div>
	</div>
	</my_body>
</body>
</html>