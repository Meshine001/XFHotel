<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<div class="modal fade" id="loginModal">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="panel">
				<div class="panel-heading">请登录</div>
				<div class="panel-body">
					<form action="<%=basePath%>/customer/login" id="login-form">
						<div class="form-group">
							<label for="exampleInputAccount1">账号</label> <input type="text"
								class="form-control" id="username" name="tel"
								placeholder="手机号">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">密码</label> <input
								type="password" class="form-control" id="password" name="password"
								placeholder="">
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-submit">登录</button>
							<span>或者</span>&nbsp;&nbsp;<a href="<%=basePath%>/customer/login>forword=reg">注册账号</a>
						</div>
					</form>
				</div>
			</div>
			<script type="text/javascript">
				$('.btn-submit').click(function(){
					$.ajax({
						url:$('#login-form').attr('action'),
						type:'POST',
						dataType:'json'
					});
				});
			</script>
		</div>
	</div>
</div>