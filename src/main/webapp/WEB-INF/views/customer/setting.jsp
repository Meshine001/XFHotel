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
</head>
<body>
	<my_body>
	<h1 class="page-header">账户设置</h1>

	<div class="row placeholders"></div>

	<div class="row">
		<div class="span12">
			<form id="psd-form" class="form-horizontal" action="./changePsd" method="post">
				<input type="hidden" name="id" value="${c.id}">
				<div class="control-group">
					<label class="control-label" for="oldpsd">原密码</label>
					<div class="controls">
						<input id="oldpsd" type="password" name="oldPsd" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">新密码</label>
					<div class="controls">
						<input id="password" type="password" name="psd"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password1">再次输入密码</label>
					<div class="controls">
						<input id="password1" type="password" />
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						 <button type="button" class="btn" id="change-psd-submit">重设密码</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	</my_body>
	<my_script>
		<script src="<%=basePath%>/dist/customer/js/setting.js"></script>
	</my_script>
</body>
</html>