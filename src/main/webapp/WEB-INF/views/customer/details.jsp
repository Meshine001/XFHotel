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
	<h1 class="page-header">个人资料</h1>

	<div class="row placeholders"></div>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改头像</h4>
				</div>
				<div class="modal-body">
					<form action="" enctype="multipart/form-data" method="post"
						id="avatar-form">
						<img alt="" src="" class="img-circle" id="preAvatar">
						<div class="form-group">
							<label>选取图片</label> <input id="file" type="file"
								name="file">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="uploadAvatar">确定选择</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<form class="form-horizontal" role="form" id="details-form">
		<input type="hidden" name="customerId" value="${c.id}">
			<input type="hidden" name="id" value="${c.details.id}"> <input
				type="hidden" id="avatar" name="avatar" value="${c.details.avatar}"> <img
				id="avatarUrl" alt="" src="<%=basePath%>/images/${c.details.avatar}"
				class="img-circle"> <a type="button"
				class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal">修改头像</a>
			<div class="form-group">
				<label>昵称</label> <input class="form-control" name="nick"
					value="${c.details.nick }">
			</div>
			<div class="form-group">
				<label>手机</label> <input class="form-control" name="tel"
					value="${c.details.tel}">
			</div>
			<div class="form-group">
				<label>身份证</label> <input class="form-control" name="idCard"
					value="${c.details.idCard}">
			</div>
			<div class="form-group">
				<label>性别</label>
				<div class="radio">
					<label> <input type="radio" name="sex" id="men" value="男"
						checked="">男
					</label>
				</div>
				<div class="radio">
					<label> <input type="radio" name="sex" id="women" value="女">女
					</label>
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label>生日</label> <input class="form-control" name="birthday"
					value="${c.details.birthday}">
			</div>

			<div class="form-group">
				<label>工作</label> <input class="form-control" name="job"
					value="${c.details.job}">
			</div>
			<div class="form-group">
			<input type="hidden" id="education" name="education" value="${c.details.education}">
				<label>教育程度</label> <select class="form-control" id="education-select">
				<c:if test="${c.details.education == null }"><option>${c.details.education}</option></c:if>
					<c:if test="${c.details.education == null }"><option>请选择</option></c:if>
					<option>小学</option>
					<option>初中</option>
					<option>高中</option>
					<option>大专</option>
					<option>本科</option>
					<option>硕士</option>
					<option>博士</option>
					<option>博士后</option>
				</select>
			</div>
			<div class="form-group">
				<label>个性宣言</label>
				<input type="hidden" id="declaration" name="declaration" value="${c.details.declaration}">
				<textarea class="form-control" rows="3" id="declaration-area">${c.details.declaration}</textarea>
			</div>
			<div class="form-group">
				<label>兴趣爱好</label>
				<input type="hidden" id="hobby"  name="hobby" value="${c.details.hobby}">
				<textarea class="form-control" rows="3" id="hobby-area">${c.details.hobby}</textarea>
			</div>
			<button type="button" class="btn btn-default" id="modify">确认修改</button>
		</form>
	</div>
	</my_body>

	<my_script> <script
		src="<%=basePath%>/dist/customer/js/details.js"></script> </my_script>

</body>
</html>