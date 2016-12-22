<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<my_body>
	<div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">添加房源</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/admin/apartment/add" method="POST" class="form form-horizontal" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-md-3 control-label">地址</label>
							<div id="location">
								<input type="text" id="location_info" class="form-control"
									placeholder="" name="location" readonly="readonly"> <input
									type="hidden" id="lng" class="form-control" placeholder=""
									name="lng"> <input type="hidden" id="lat"
									class="form-control" placeholder="" name="lat">
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="address">
							</div>
						</div>
						<div id="map" style="width: 500px; height: 500px"></div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="community">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_building">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="text"
										class="form-control" placeholder="" name="floor"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="text"
										class="form-control" placeholder="" name="totalfloor">
									<span class="input-group-addon">层</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="direction" class="">
									<option value="南北">南北</option>
									<option value="东西">东西</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总面积</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="square"><span class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住人数</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="capacity">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bedroom"> <span class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="livingroom"> <span class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bathroom"> <span class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="balcony"> <span class="input-group-addon">阳台</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">特色<small><a
									id="edit-feature">编辑</a></small></label>

							<div class="col-md-9">
								<c:forEach items="${l_feature}" var="feature" varStatus="p">
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="fe-${feature.id}" name="feature"
											value="${feature.id}"> <label for="fe-${feature.id}">${feature.description }
										</label>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="description">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">户型图</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<input type="file" class="btn btn-primary btn-lg"
									id="layout-image-input" name="file" />
							</div>
							<div class="col-md-9">
								<div class="col-md-3">
									<img id="layout-image" alt=""
										src="http://mf.znimg.com/thumb/dress_138x84/house_img/734/bcf19a0e72b41ef93d41f49374167924.jpg"
										class="img-thumbnail">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">公寓照片</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<input type="file" class="btn btn-primary btn-lg"
									name="file" />
									<input type="file" class="btn btn-primary btn-lg"
									 name="file" />
									<input type="file" class="btn btn-primary btn-lg"
									name="file" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设施<small><a>编辑</a></small></label>
							<div class="col-md-9">
								<c:forEach items="${l_facility}" var="facility" varStatus="p">
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="fa-${facility.id}" name="facility"
											value="${facility.id}"> <label
											for="fa-${facility.id}">${facility.description }</label>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可租房间数</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_room">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>
							<div class="col-md-9">
								<input type="hidden" name="apartmenttype" value=""
									id="apartment-type-input"> <select id="apartment-type">
									<option value="-1" selected="selected">请选择</option>
									<c:forEach items="${l_apartmenttype}" var="apartmenttype"
										varStatus="p">
										<option value="${apartmenttype.id }">${apartmenttype.description }</option>
									</c:forEach>
								</select> <select name="type" id="lease-type">
									<option value="0">请选择</option>
									<option value="1">单租型</option>
									<option value="2">合租型</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div id="lease-price" class="col-md-9"></div>
						</div>

						<br>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="feature-modal" tabindex="-1" role="dialog"
		aria-labelledby="feature-modal-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="feature-modal-lael">特色编辑</h4>
				</div>
				<div class="modal-body">
					<ul id="features-li">

					</ul>
					<form action="" id="add-feature-form">
						<input type="text" name="description">
						<button type="button" id="add-feature">添加</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script
		src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1"
		type="text/javascript"></script> <script type="text/javascript">
			
		</script> </my_body>

	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/add-apartment.js"></script></my_script>
</body>
</html>