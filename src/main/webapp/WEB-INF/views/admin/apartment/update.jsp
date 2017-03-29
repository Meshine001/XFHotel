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
					<form
						action="<%=request.getContextPath()%>/admin/apartment/update/${apartment.id}"
						method="POST" class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">区域</label>
							<div class="col-md-9">
								<div id="location">
									<input type="text" id="location_info" class="form-control"
										placeholder="" name="location" readonly="readonly"
										value="${apartment.location}"> <input type="hidden"
										id="lng" class="form-control" placeholder="" name="lng"
										value="${apartment.longitude}"> <input type="hidden"
										id="lat" class="form-control" placeholder="" name="lat"
										value="${apartment.latitude}">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">详细地址</label>

							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="address" value="${apartment.address}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="community" value="${apartment.community}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_building" value="${apartment.num_building}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">单元号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_unit" value="${apartment.num_unit}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="text"
										class="form-control" placeholder="" name="floor"
										value="${apartment.floor}"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="text"
										class="form-control" placeholder="" name="totalfloor"
										value="${apartment.totalfloor}"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">门牌号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_door" value="${apartment.num_door}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">锁地址</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="lock_address" value="${apartment.lock_address}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="direction" class="">
									<c:if test="${apartment.direction == '南北'}">
										<option value="南北" selected="selected">南北</option>
										<option value="东西">东西</option>
									</c:if>
									<c:if test="${apartment.direction != '南北'}">
										<option value="南北">南北</option>
										<option value="东西" selected="selected">东西</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总面积</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="square" value="${apartment.square }"><span
										class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bedroom" value="${apartment.bedroom}"> <span
										class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="livingroom" value="${apartment.livingroom}"> <span
										class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bathroom" value="${apartment.bathroom}"> <span
										class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="balcony" value="${apartment.balcony}"> <span
										class="input-group-addon">阳台</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住几人</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="capacity" value="${apartment.capacity}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">户型图</label>
							<div class="col-md-9">
								<ul>
									<li><input type="hidden" name="pic1" id="pic1-1-input"
										value="${apartment.pic1}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic1}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic1-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic1-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">周边环境</label>
							<div class="col-md-9">
								<textarea name="descriptionAround" rows="3" class="form-control">${apartment.descriptionAround}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">个性描述</label>
							<div class="col-md-9">
								<textarea name="descriptionPersonal" rows="3"
									class="form-control">${apartment.rooms[0].descriptionPersonal}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">特色</label>

							<div class="col-md-9">
								<c:forEach items="${l_feature}" var="feature" varStatus="p">
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="fe-${feature.id}" name="feature"
											value="${feature.id}"> <label for="fe-${feature.id}">${feature.description }
										</label>
									</div>
									<c:forEach items="${apartment.features}" var="f">
										<c:if test="${f == feature.id }">
											<script type="text/javascript">
												$('#fe-'+${f}).attr('checked','checked');
											</script>
										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">配套设施</label>
							<div class="col-md-9">
								<c:forEach items="${l_facility}" var="facility" varStatus="p">
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="fa-${facility.id}" name="facility"
											value="${facility.id}"> <label
											for="fa-${facility.id}">${facility.description }</label>
										<c:forEach items="${apartment.facilities}" var="f">
											<c:if test="${f == facility.id}">
												<script type="text/javascript">
													$('#fa-'+${f}).attr('checked','checked');
												</script>
											</c:if>
										</c:forEach>
									</div>
								</c:forEach>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">图片展示</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<ul>
									<li><input type="hidden" name="pic2" id="pic2-1-input"
										value="${apartment.pic2[0]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic2[0]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic2-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-2-input"
										value="${apartment.pic2[1]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic2[1]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic2-2-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-2"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-3-input"
										value="${apartment.pic2[2]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic2[2]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic2-3-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-3"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-4-input"
										value="${apartment.pic2[3]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic2[3]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic2-4-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-4"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-5-input"
										value="${apartment.pic2[4]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic2[4]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic2-5-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-5"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区图片</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<ul>
									<li><input type="hidden" name="pic3" id="pic3-1-input"
										value="${apartment.pic3[0]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic3[0]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic3-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic3" id="pic3-2-input"
										value="${apartment.pic3[1]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic3[1]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic3-2-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-2"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic3" id="pic3-3-input"
										value="${apartment.pic3[2]}"> <img alt=""
										src="<%=basePath%>/images/${apartment.pic3[2]}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic3-3-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-3"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>

							<div class="col-md-9">
								<select name="apartmenttype" id="apartment-type">
									<c:forEach items="${l_apartmenttype}" var="type">
										<c:if test="${apartment.apartmenttype == type}">
											<option selected="selected" value="${type}">${type}</option>
										</c:if>
										<c:if test="${apartment.apartmenttype != type}">
											<option value="${type}">${type}</option>
										</c:if>
									</c:forEach>
								</select>

							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" name="dayPrice" class="price-day"
										value="${apartment.dayPrice}"> <span
										class="input-group-addon">元/天</span>
								</div>
								<div class="input-group" style="display: none;">
									<input type="text" name="hourPrice" class="price-hour">
									<span class="input-group-addon">元/小时</span>
								</div>
							</div>
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
		<div class="col-md-6">
			<div id="map" style="width: 500px; height: 500px"></div>
		</div>
		<script
			src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1"
			type="text/javascript"></script>
		<script type="text/javascript">
			var map = new BMap.Map("map");
			var geolocation = new BMap.Geolocation();
			var point = new BMap.Point($('#lng').val(), $('#lat').val());
			var marker = new BMap.Marker(point);
			marker.setPosition(point);
			map.addOverlay(marker);
			map.centerAndZoom(point, 12);
			map.enableScrollWheelZoom(true);
			map.addEventListener('ondragging', function() {
				marker.setPosition(map.getCenter());
			});
			map.addEventListener("dragend", function showInfo() {
				var cp = map.getCenter();
				getaddress(cp.lng, cp.lat);
			});
			function getaddress(lng, lat) {
				var pt = new BMap.Point(lng, lat);
				var geoc = new BMap.Geocoder();
				geoc.getLocation(pt,
						function(rs) {
							var addComp = rs.addressComponents;
							$('#location_info').val(
									addComp.province + ", " + addComp.city
											+ ", " + addComp.district + ", "
											+ addComp.street);
							$('#lng').val(lng);
							$('#lat').val(lat);

						});
			}
		</script>
		<!-- 上传图片 -->
		<form action="" id="upload-image-form">
			<input type="file" id="upload-image-input" name="file"
				style="display: none;">
		</form>

	</div>
	</my_body>

	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/add-apartment.js"></script></my_script>
</body>
</html>