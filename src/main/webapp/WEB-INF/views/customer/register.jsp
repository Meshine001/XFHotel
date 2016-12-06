<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户注册-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">

<link href="<%=basePath%>/dist/customer/css/reg.css" rel="stylesheet">
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
				<li class="box-ul-on">注册</li>
				<li id="login">登录</li>
				<li id="download">下载APP</li>
			</ul>
			<div class="box-main">
				<dl>
					<dd class="box-main-left">
						<form>
							<ul>
								<li><input type="tel" id="tel" placeholder="常用手机" onblur=""></li>
								<li><input type="text" id="volidate-code"
									placeholder="请输入验证码"> <input type="button"
									id="first-code" value="获取验证码"></li>
								<li><input type="password" value="" name="password"
									id="password" placeholder="输入密码"></li>
								<li><input type="submit" value="注册" name=""></li>
							</ul>
						</form>
					</dd>

				</dl>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>/dist/customer/js/reg-login.js"></script>

</body>
</html>