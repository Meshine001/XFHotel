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
	<div class="modal fade" id="avatar-modal" aria-hidden="true"
		aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1"
		style="display: none;">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<form class="avatar-form"
					action="http://www.jq22.com/demo/jquery-html5-upload20160106/%7B%7Burl('admin/upload-logo')%7D%7D"
					enctype="multipart/form-data" method="post">
					<div class="modal-header">
						<button class="close" data-dismiss="modal" type="button">×</button>
						<h4 class="modal-title" id="avatar-modal-label">Change Logo
							Picture</h4>
					</div>
					<div class="modal-body">
						<div class="avatar-body">
							<div class="avatar-upload">
								<input class="avatar-src" name="avatar_src" type="hidden">
								<input class="avatar-data" name="avatar_data" type="hidden"
									value="{&quot;x&quot;:80,&quot;y&quot;:30.000000000000004,&quot;height&quot;:239.99999999999997,&quot;width&quot;:239.99999999999997,&quot;rotate&quot;:0}">
								<label for="avatarInput">图片上传</label> <input
									class="avatar-input" id="avatarInput" name="avatar_file"
									type="file">
							</div>
							<div class="row">
								<div class="col-md-9">
									<div class="avatar-wrapper">
										<img src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
											class="cropper-hidden">
										<div class="cropper-container cropper-bg"
											style="width: 621px; height: 364px;">
											<div class="cropper-canvas"
												style="width: 485.333px; height: 364px; left: 67.8333px; top: 0px;">
												<img src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
													class=""
													style="width: 485.333px; height: 364px; margin-left: 0px; margin-top: 0px; transform: none;">
											</div>
											<div class="cropper-drag-box cropper-modal cropper-crop"
												data-drag="move"></div>
											<div class="cropper-crop-box"
												style="width: 291.2px; height: 291.2px; left: 164.9px; top: 36.4px;">
												<span class="cropper-view-box"><img
													src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
													style="width: 485.333px; height: 364px; margin-left: -97.0667px; margin-top: -36.4px; transform: none;"></span><span
													class="cropper-dashed dashed-h"></span><span
													class="cropper-dashed dashed-v"></span><span
													class="cropper-face" data-drag="all"></span><span
													class="cropper-line line-e" data-drag="e"></span><span
													class="cropper-line line-n" data-drag="n"></span><span
													class="cropper-line line-w" data-drag="w"></span><span
													class="cropper-line line-s" data-drag="s"></span><span
													class="cropper-point point-e" data-drag="e"></span><span
													class="cropper-point point-n" data-drag="n"></span><span
													class="cropper-point point-w" data-drag="w"></span><span
													class="cropper-point point-s" data-drag="s"></span><span
													class="cropper-point point-ne" data-drag="ne"></span><span
													class="cropper-point point-nw" data-drag="nw"></span><span
													class="cropper-point point-sw" data-drag="sw"></span><span
													class="cropper-point point-se" data-drag="se"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-3">
									<div class="avatar-preview preview-lg"
										style="width: 184px; height: 184px;">
										<img src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
											style="display: block; width: 303.333px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 227.5px; margin-left: -60.6667px; margin-top: -22.75px; transform: none;">
									</div>
									<div class="avatar-preview preview-md"
										style="width: 100px; height: 100px;">
										<img src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
											style="display: block; width: 163.333px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 122.5px; margin-left: -32.6667px; margin-top: -12.25px; transform: none;">
									</div>
									<div class="avatar-preview preview-sm"
										style="width: 50px; height: 50px;">
										<img src="blob:null/43067964-d381-4d9a-a7d0-4784a54c5c48"
											style="display: block; width: 80px; min-width: 0px !important; min-height: 0px !important; max-width: none !important; max-height: none !important; height: 60px; margin-left: -16px; margin-top: -6px; transform: none;">
									</div>
								</div>
							</div>
							<div class="row avatar-btns">
								<div class="col-md-9">
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
										type="submit">
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
	<div class="row">
		<form class="form-horizontal" role="form" id="details-form">
			<input type="hidden" name="customerId" value="${c.id}"> <input
				type="hidden" name="id" value="${c.details.id}"> <input
				type="hidden" id="avatar" name="avatar" value="${c.details.avatar}">
			<img id="avatarUrl" alt=""
				src="<%=basePath%>/images/${c.details.avatar}" class="img-circle">
			<a type="button" class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#avatar-modal">修改头像</a>
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
				<input type="hidden" id="education" name="education"
					value="${c.details.education}"> <label>教育程度</label> <select
					class="form-control" id="education-select">
					<c:if test="${c.details.education == null }">
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