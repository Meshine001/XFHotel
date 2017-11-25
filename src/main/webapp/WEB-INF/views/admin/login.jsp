<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>

<html>

<head>
<title>管理员登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/vendor.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/flat-admin.css">

<!-- Theme -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue-sky.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/blue.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/red.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/admin/assets/css/theme/yellow.css">

</head>
<body>
	<div class="app app-default">

		<div class="app-container app-login">
			<div class="flex-center">
				<div class="app-header"></div>
				<div class="app-body">
					<div class="loader-container text-center">
						<div class="icon">
							<div class="sk-folding-cube">
								<div class="sk-cube1 sk-cube"></div>
								<div class="sk-cube2 sk-cube"></div>
								<div class="sk-cube4 sk-cube"></div>
								<div class="sk-cube3 sk-cube"></div>
							</div>
						</div>
						<div class="title">Logging in...</div>
					</div>
					<div class="app-block">
						<div class="app-form">
							<div class="form-header">
								<div class="app-brand">
									<span class="highlight">青舍都市</span> Admin
								</div>
							</div>
							<div id="login-form">
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon1"> <i
										class="fa fa-user" aria-hidden="true"></i></span> <input type="text"
										class="form-control" placeholder="用户名" id="username"
										aria-describedby="basic-addon1" name="username">
								</div>
								<div class="input-group">
									<span class="input-group-addon" id="basic-addon2"> <i
										class="fa fa-key" aria-hidden="true"></i></span> <input type="password"
										class="form-control" placeholder="密  码" id="password"
										aria-describedby="basic-addon2" name="password">
								</div>
								<div class="text-center">
									<input type="submit" class="btn btn-success btn-submit" id="beginlogin"
										value="登录">
								</div>
							</div>

							<div class="form-line" style="display: none;">
								<div class="title">或者</div>
							</div>
							<div class="form-footer" style="display: none;">
								<button type="button"
									class="btn btn-default btn-sm btn-social __facebook">
									<div class="info">
										<i class="icon fa fa-facebook-official" aria-hidden="true"></i>
										<span class="title">超级管理员</span>
									</div>
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="app-footer"></div>
			</div>
		</div>
	
	</div>

	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/vendor.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/app.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$("#beginlogin").click(function(){
				var userName=$("#username").val();
				var password=$("#password").val();
				if(userName==""||password==""){
					alert('请输入账号密码')
				}else{
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'userName':userName,'password':password},
						url:'../admin/login/',
						success:function(data){
							console.log(data);
							if(data.statusCode==1){
								window.localStorage.setItem('uid',data.content.id);
								window.localStorage.setItem('userType',data.content.authority);
								window.localStorage.setItem('userStatus',data.content.status);
								if(data.content.status==0){// status==0 激活状态
									window.location.href='/admin/dashboard/';
								}else if(data.content.status==1){ // status==1 冻结状态
									alert('您的账号已冻结、如需登录请联系超级管理员。');
									location=location;
								}
							}else{
								alert(data.content)
							}

						}
					})
				}
			})

		})
	
	
		
	</script>
</body>


</html>