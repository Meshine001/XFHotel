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
				<div class="card-header">编辑房源</div>
				<div class="card-body">
					<form action="/hotel/admin/apartment/update" method="POST"
						class="form form-horizontal" enctype="multipart/form-data">
						<input type="hidden" id="apartmentid" name="apartmentid"
							value="${apartmentid}">
						<div class="form-group">
							<label class="col-md-3 control-label">区域</label>
							<div class="col-md-9">
								<div id="location">
									<input type="text" id="location_info" class="form-control"
										placeholder="" name="location" readonly="readonly"> <input
										type="hidden" id="lng" class="form-control" placeholder=""
										name="lng" value=""> <input type="hidden" id="lat"
										class="form-control" placeholder="" name="lat" value="">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">详细地址</label>

							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="address" id="address">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="community" id="community">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_building" id="num_building">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="text"
										class="form-control" placeholder="" name="floor" id="floor">
									<span class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="text"
										class="form-control" placeholder="" name="totalfloor"
										id="totalfloor"> <span class="input-group-addon">层</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="direction" class="" id="direction">
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
										name="square" id="square"> <span
										class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住人数</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="capacity" id="capacity">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bedroom" id="bedroom"> <span
										class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="livingroom" id="livingroom"> <span
										class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bathroom" id="bathroom"> <span
										class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="balcony" id="balcony"> <span
										class="input-group-addon">阳台</span>
								</div>
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
								</c:forEach>

							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="description" id="description">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">设施</label>
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
							<label class="col-md-3 control-label">房间</label>
							<div class="col-md-9">
								<div id="rooms"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">图片</label>
							<div class="col-md-9">
								<table class="table">
									<tbody>
										<tr>
											<td>布局图
											</td>
											<td>
												<ul>
													<li><img alt="" src="" class="img-thumbnail"
														width="120px" height="80px" id="pic1-1-img"> 
														<input type="hidden" name="pic1" id="pic1-1-input">
														<a class="btn upload-label" id="pic1-1">更换</a>
													</li>
												</ul>
											</td>
										</tr>
										<tr>
											<td>客厅图
											</td>
											<td>
												<ul id="livingroom-ul">

												</ul>
											</td>
										</tr>
										<tr>
											<td>餐厅图
											</td>
											<td>
												<ul id="eattingroom-ul">

												</ul>
											</td>
										</tr>
										<tr>
											<td>浴室图
											</td>
											<td>
												<ul id="bathroom-ul">

												</ul>
											</td>
										</tr>
										<tr>
											<td>厨房图
											</td>
											<td>
												<ul id="kitchen-ul">

												</ul>
											</td>
										</tr>
										<tr>
											<td>小区实景图
											</td>
											<td>
												<ul id="community-ul">

												</ul>
											</td>
										</tr>
									</tbody>
								</table>


							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>
							<div class="col-md-9">
								<select name="apartmenttype" id="apartment-type">
									<option value="-1" selected="selected">请选择</option>
									<option value="酒店型">酒店型</option>
									<option value="短租型">短租型</option>
								</select> <select name="type" id="lease-type">
									<option value="-1">请选择</option>
									<option value="单租型">单租型</option>
									<option value="合租型">合租型</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div id="lease-price" class="col-md-9">
								<div class="input-group day" style="display: none;">
									<span class="input-group-addon">天</span> <input type="text"
										name="prices" class="price-day"> <span
										class="input-group-addon">元</span>
								</div>
								<div class="input-group week" style="display: none;">
									<span class="input-group-addon">周</span> <input type="text"
										name="prices" class="price-week"> <span
										class="input-group-addon">元</span>
								</div>
								<div class="input-group month" style="display: none;">
									<span class="input-group-addon">月</span> <input type="text"
										name="prices" class="price-month"> <span
										class="input-group-addon">元</span>
								</div>
								<div class="input-group year" style="display: none;">
									<span class="input-group-addon">年</span> <input type="text"
										name="prices" class="price-year"> <span
										class="input-group-addon">元</span>
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
			<div class="card">
				<div id="map" style="width: 100%; height: 800px"></div>
			</div>
		</div>
		<form id="upload-form" action="../file/upload" enctype="multipart/form-data" method="post" style="display: none;">
			<input type="file" name="file" id="upload-file-input">
		</form>
	</div>

	<script
		src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1"
		type="text/javascript"></script> </my_body>
	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/update-apartment.js"></script></my_script>
</body>
</html>