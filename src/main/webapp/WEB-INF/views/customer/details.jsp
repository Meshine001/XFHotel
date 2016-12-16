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
	<div class="row">
		<div id="crop-avatar" class="col-md-6">
			<div class="avatar-view" title="" data-original-title="修改头像">
				<img src="<%=basePath %>/images/${c.details.avatar}" alt="">
			</div>
		</div>
	</div>
	<div class="modal fade" id="avatar-modal" aria-hidden="true"
		aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1"
		style="display: none;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<form class="avatar-form" action="<%=basePath%>/file/uploadAvatar"
					enctype="multipart/form-data" method="post">
					<div class="modal-header">
						<button class="close" data-dismiss="modal" type="button">×</button>
						<h4 class="modal-title" id="avatar-modal-label">修改头像</h4>
					</div>
					<div class="modal-body">
						<div class="avatar-body">
							<div class="avatar-upload">
								<input class="avatar-data" name="avatarData" type="hidden"
									value="{&quot;x&quot;:80,&quot;y&quot;:30.000000000000004,&quot;height&quot;:239.99999999999997,&quot;width&quot;:239.99999999999997,&quot;rotate&quot;:0}">
								<label for="avatarInput">图片上传</label> <input
									class="avatar-input" id="avatarInput" name="avatarFile"
									type="file">
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="avatar-wrapper">
										<img src="" class="cropper-hidden">
									</div>
								</div>
								<div class="col-md-3">
									<div class="avatar-preview preview-lg"
										style="width: 184px; height: 184px;">
										<img src=""
											style="display: block; width: 303.333px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 227.5px; margin-left: -60.6667px; margin-top: -22.75px; transform: none;">
									</div>
									<div class="avatar-preview preview-md"
										style="width: 100px; height: 100px;">
										<img src=""
											style="display: block; width: 163.333px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 122.5px; margin-left: -32.6667px; margin-top: -12.25px; transform: none;">
									</div>
									<div class="avatar-preview preview-sm"
										style="width: 50px; height: 50px;">
										<img src=""
											style="display: block; width: 80px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 60px; margin-left: -16px; margin-top: -6px; transform: none;">
									</div>
								</div>
							</div>
							<div class="row avatar-btns">
								<div class="col-md-9" style="display: none;">

									<div class="btn-group">
										<button class="btn" data-method="rotate" data-option="-90"
											type="button" title="Rotate -90 degrees">
											<i class="fa fa-undo"></i> 向左旋转
										</button>
									</div>
									<div class="btn-group">
										<button class="btn" data-method="rotate" data-option="90"
											type="button" title="Rotate 90 degrees">
											<i class="fa fa-repeat"></i> 向右旋转
										</button>
									</div>
								</div>
								<div class="col-md-3">
									<button class="btn btn-success btn-block avatar-save"
										type="button">
										<i class="fa fa-save"></i> 保存修改
									</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
	<div class="row">
		<form class="form-horizontal" role="form" id="details-form">
			<input type="hidden" name="customerId" value="${c.id}"> <input
				type="hidden" name="id" value="${c.details.id}"> <input
				type="hidden" id="avatar" name="avatar" value="${c.details.avatar}">
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
					<c:if test="${c.details.sex == '男' }">
						<label> <input type="radio" name="sex" id="men" value="男"
							checked="checked">男
						</label>
					</c:if>
					<c:if test="${c.details.sex != '男' }">
						<label> <input type="radio" name="sex" id="men" value="男">男
						</label>
					</c:if>
				</div>
				<div class="radio">
					<c:if test="${c.details.sex == '女' }">
						<label> <input type="radio" name="sex" id="women"
							value="女" checked="checked">女
						</label>
					</c:if>
					<c:if test="${c.details.sex != '女' }">
						<label> <input type="radio" name="sex" id="women"
							value="女">女
						</label>
					</c:if>
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
				<input type="hidden" id="education" name="education"
					value="${c.details.education}"> <label>教育程度</label> <select
					class="form-control" id="education-select">
					<c:if test="${c.details.education != null }">
						<option>${c.details.education}</option>
					</c:if>
					<c:if test="${c.details.education == null }">
						<option>请选择</option>
					</c:if>
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
				<label>个性宣言</label> <input type="hidden" id="declaration"
					name="declaration" value="${c.details.declaration}">
				<textarea class="form-control" rows="3" id="declaration-area">${c.details.declaration}</textarea>
			</div>
			<div class="form-group">
				<label>兴趣爱好</label> <input type="hidden" id="hobby" name="hobby"
					value="${c.details.hobby}">
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