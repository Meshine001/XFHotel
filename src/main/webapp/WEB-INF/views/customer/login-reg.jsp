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

<link href="<%=basePath%>/dist/customer/css/log-reg.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">登录</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">注册</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<form id="login-form" action="" method="post" role="form"
									style="display: block;">
									<div class="form-group">
										<input type="text" name="tel" id="username" tabindex="1"
											class="form-control" placeholder="请输入手机号" value="18710579465">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"
											tabindex="2" class="form-control" placeholder="请输入密码" value="123123">
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember"> 记住密码？</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login">登录</button>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="" tabindex="5" class="forgot-password">忘记密码?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" action="" method="post" role="form"
									style="display: none;">
									<div class="form-group">
										<input type="text" name="tel" id="reg-tel" tabindex="1"
											class="form-control" placeholder="请输入手机号" value="">
									</div>
									<div class="form-group">
										<div class="input-group">

											<input type="text" name="validateCode" id="validate-code"
												tabindex="1" class="form-control" placeholder="请输入验证码"
												value="">
											<div class="input-group-addon" style="background-color: rgba(238, 238, 238, 0);border: none;">
												<button type="button" class="btn btn-info">获取验证码</button>
											</div>
										</div>
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"
											tabindex="2" class="form-control" placeholder="请输入密码">
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register">现在注册</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=basePath%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/dist/customer/js/log-reg.js"></script>
	<c:if test="${forword == 'login' }">
		<script type="text/javascript">
			$("#login-form").delay(100).fadeIn(100);
			$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$('#login-form-link').addClass('active');
		</script>
	</c:if>
	<c:if test="${forword == 'reg' }">
		<script type="text/javascript">
			$("#register-form").delay(100).fadeIn(100);
			$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$('#register-form-link').addClass('active');
		</script>
	</c:if>
</body>
</html>