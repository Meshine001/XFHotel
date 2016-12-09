<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户登录-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">

<link href="<%=basePath%>/dist/customer/css/login.css" rel="stylesheet">
</head>
<body>
	<div class="bg">
		<div class="bg-top">
			<div class="back-home" id="back-home">
				<span> 返回主页 <i>></i>
				</span>
			</div>
		</div>
		<div class="box">
			<ul class="box-ul">
				<li id="reg">注册</li>
				<li class="box-ul-on">登录</li>
				<li>下载APP</li>
			</ul>
			<div class="box-main">
				<dl>
					<dd class="box-main-left">
						<form action="<%=basePath%>/customer/login" method="post">
							<ul>
								<li><input name="tel" type="tel" id="tel" placeholder="输入手机号"
									onblur=""></li>

								<li><input type="password" value="" name="password"
									id="password" placeholder="输入密码"></li>
								<li><input type="submit" value="登录" name=""></li>
							</ul>
						</form>
					</dd>

				</dl>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/customer/js/reg-login.js"></script>
</body>
</html>