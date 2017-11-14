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
<!-- 
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
 -->
	<div class="row"  style="display: none;">
		<div class="col-lg-4 col-md-6 col-sm-6 col-xs-12">
			<a class="card card-banner card-green-light">
				<div class="card-body">
					<i class="icon fa fa-shopping-basket fa-4x"></i>
					<div class="content">
						<div class="title">Sale Today</div>
						<div class="value">
							<span class="sign">$</span>
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
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-house">
			<a class="card card-banner card-orange-light" href="<%=basePath%>/admin/apartment">
				<div class="card-body">
					<i class="icon fa fa-bed fa-4x"></i>
					<div class="content">
						<div class="title">房源管理</div>
						<div class="value">
							<span class="sign"></span><!-- ${apartmentCount} -->
						</div>
					</div>
				</div>
			</a>

		</div>
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-order">
			<a class="card card-banner card-green-light" href="<%=basePath%>/admin/order">
				<div class="card-body">
					<i class="icon fa fa-shopping-cart fa-4x"></i>
					<div class="content">
						<div class="title">订单管理</div>
						<div class="value">
							<span class="sign"></span><!-- ${orderCount} -->
						</div>
					</div>
				</div>
			</a>

		</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-user">
			<a class="card card-banner card-yellow-light" href="<%=basePath%>/admin/customer_list">
				<div class="card-body">
					<i class="icon fa fa-users fa-4x"></i>
					<div class="content">
						<div class="title">用户管理</div>
						<div class="value">
							<span class="sign"></span><!-- ${customerCount} -->
						</div>
					</div>
				</div>
			</a>

		</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-life">
			<a class="card card-banner card-blue-light" href="<%=basePath%>/admin/blog/list">
				<div class="card-body">
					<i class="icon fa fa-chain fa-4x"></i>
					<div class="content">
						<div class="title">青舍生活</div>
						<div class="value">
							<span class="sign"></span><!-- ${blogCount} -->
						</div>
					</div>
				</div>
			</a>

		</div>
		  <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-yhj">
			<a class="card card-banner card-crimson-light" href="<%=basePath%>/admin/customer_sendlist">
				<div class="card-body">
					<i class="icon fa fa-cloud fa-4x"></i>
					<div class="content"> 
						<div class="title">优惠卷管理</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>

		</div>
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-clean">
			<a class="card card-banner card-violet-light" href="<%=basePath%>/admin/customer_baojie">
				<div class="card-body">
					<i class="icon fa fa-flask fa-4x"></i>
					<div class="content"> 
						<div class="title">保洁服务</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>

		</div>
		
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-wx">
			<a class="card card-banner card-gray-light" href="<%=basePath%>/admin/customer_weixiu">
				<div class="card-body">
					<i class="icon fa fa-list-ul fa-4x"></i>
					<div class="content"> 
						<div class="title">故障维修</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-add">
			<a class="card card-banner card-dodgerblue-light" href="<%=basePath%>/admin/customer_addfacility">
				<div class="card-body">
					<i class="icon fa fa-columns fa-4x"></i>
					<div class="content"> 
						<div class="title">添加设施</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
		
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng" id="app-moble-car">
			<a class="card card-banner card-darkviolet-light" href="<%=basePath%>/admin/customer_DialogueCar">
				<div class="card-body">
					<i class="icon fa fa-linkedin fa-4x"></i>
					<div class="content"> 
						<div class="title">叫车服务</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
		
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng"  id="app-moble-app">
			<a class="card card-banner card-sandybrown-light" href="<%=basePath%>/admin/customer_collocation">
				<div class="card-body">
					<i class="icon fa fa-sitemap fa-4x"></i>
					<div class="content"> 
						<div class="title">房东管理</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
			
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng"  id="app-moble-power">
			<a class="card card-banner card-paste-light" href="<%=basePath%>/admin/customer_manager">
				<div class="card-body">
					<i class="icon fa fa-paste fa-4x"></i>
					<div class="content"> 
						<div class="title">成员权限</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
		<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 app-ng"  id="app-moble-coopler">
			<a class="card card-banner card-paste-light" href="<%=basePath%>/admin/customer_jointwork">
				<div class="card-body">
					<i class="icon fa fa-exchange fa-4x"></i>
					<div class="content"> 
						<div class="title">合作商户</div>
						<div class="value">
							<span class="sign"></span>
						</div>
					</div>
				</div>
			</a>
		</div>
		
	</div>
	
	<script>
		$(document).ready(function(){
			var userType=window.localStorage.getItem('userType');//1:超级管理员 0:管理员
			var uid=window.localStorage.getItem('uid');
			if(userType==1){
				$("#app-moble-car,#app-moble-wx,#app-moble-clean,#app-moble-order,#app-moble-house,#app-moble-add").show();
			}else{
				$(".app-ng").show();
			}
			
			$.ajax({
				type:'post',
				dataType:'json',
				data:{'id':uid},
				url:'/admin/getData',
				success:function(data){
					console.log(data);
					
				}
		    });
			
			
			
			
			//实时状态
				$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/admin/user/steward',//统计用户有多少管理的房屋
						success:function(data){
				//			console.log(data);
							$("#app-moble-house .value").html('<span class="sign"></span>'+data.content.length+'')
						}
				});
				$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/admin/user/stewardO',//统计住房订单
						success:function(data){
				//			console.log(data);
							$("#app-moble-order .value").html('<span class="sign"></span>'+data.content.length+'')
						}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardC',//统计保洁订单
					success:function(data){
				//		console.log(data);
						$("#app-moble-clean .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardF',//统计维修订单
					success:function(data){
				//		console.log(data);
						$("#app-moble-wx .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardT',//统计轿车订单
					success:function(data){
				//		console.log(data);
						$("#app-moble-car .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardFO',//统计添加设置订单
					success:function(data){
				//		console.log(data);
						$("#app-moble-add .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				 $.ajax({
                	type:'post',
                	dataType:'json',
                	data:{},
                	url:'/admin/user/all/',//统计有多少管理员
                	success:function(data){
                //		console.log(data)
                		$("#app-moble-power .value").html('<span class="sign"></span>'+data.content.length+'')
                	}
				 });	
			
				   $.ajax({
	                	type:'POST',
	                	dataType:'json',
	                	url:'/landlord/collocation/',//统计房东数量
	                	success:function(data){
	             //   		console.log(data)
	                		$("#app-moble-app .value").html('<span class="sign"></span>'+data.length+'')
	                	}
				   });
				   
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/mobile/getUser',// 用户数量
						success:function(data){
						//	console.log(data);
							$("#app-moble-user .value").html('<span class="sign"></span>'+data.length+'')
						}
				   });
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/mobile/getCoupon',// 优惠卷数量
						success:function(data){
						//	console.log(data);
							$("#app-moble-yhj .value").html('<span class="sign"></span>'+data.length+'')
						}
				   });
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/mobile/getBlog',// 青舍生活文章数量
						success:function(data){
						//	console.log(data);
							$("#app-moble-life .value").html('<span class="sign"></span>'+data.length+'')
						}
				   });	
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/mobile/getTenant',// 商户数量
						success:function(data){
						//	console.log(data);
							$("#app-moble-coopler .value").html('<span class="sign"></span>'+data.length+'')
						}
				   });	
		})
	</script>
	</my_body>

</body>
</html>