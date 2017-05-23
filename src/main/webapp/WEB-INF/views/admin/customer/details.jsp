<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>title-333青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<my_body>
	<h1 class="page-header">个人资料</h1>
	<div class="row placeholders"></div>
	<div class="row">
		<div class="col-md-6">
			<form class="form-horizontal" role="form" id="details-form">
				<input type="hidden" name="customerId" value="${c.id}"> <input
					type="hidden" name="id" value="${c.details.id}"> <input
					type="hidden" id="avatar" name="avatar" value="${c.details.avatar}">
				<div class="form-group">
					<label>昵称</label> <input class="form-control" readonly="readonly"
						name="nick" value="${c.details.nick }">
				</div>
				<div class="form-group">
					<label>手机</label> <input class="form-control" name="tel"
						readonly="readonly" value="${c.details.tel}">
				</div>
				<div class="form-group">
					<label>身份证</label> <input class="form-control" name="idCard"
						readonly="readonly" value="${c.details.idCard}">
				</div>
				<div class="form-group">
					<label>性别</label> <input class="form-control" name="sex" id="men"
						value="${c.details.sex}" readonly="readonly" checked="checked">
				</div>
				<div class="form-group" style="display: none;">
					<label>生日</label> <input class="form-control" name="birthday"
						readonly="readonly" value="${c.details.birthday}">
				</div>

				<div class="form-group">
					<label>工作</label> <input class="form-control" name="job"
						readonly="readonly" value="${c.details.job}">
				</div>
				<div class="form-group">
					<label>教育程度</label> <input class="form-control" id="education"
						name="education" readonly="readonly"
						value="${c.details.education}">
				</div>
			</form>
		</div>
		<div class="col-md-6">
			<div id="crop-avatar" class="col-md-6">
				<div class="avatar-view" title="" data-original-title="修改头像">
					<img style="width:300px;margin-left:60px" src="<%=basePath %>/images/${c.details.avatar}" alt="">
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label>个性宣言</label> <input type="hidden" id="declaration"
				 name="declaration"
				value="${c.details.declaration}">
			<textarea class="form-control" rows="3" id="declaration-area" readonly="readonly">${c.details.declaration}</textarea>
		</div>
		<div class="form-group">
			<label>兴趣爱好</label> <input type="hidden" id="hobby" name="hobby"
				readonly="readonly" value="${c.details.hobby}">
			<textarea class="form-control" rows="3" id="hobby-area" readonly="readonly">${c.details.hobby}</textarea>
		</div>
		<a href='#' onClick='javascript :history.go(-1);'>返回</a>
	</div>
	</my_body>

	<my_script> <script
		src="<%=basePath%>/dist/customer/js/details.js"></script> </my_script>

</body>
</html>