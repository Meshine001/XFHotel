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
	<div class="row clearfix" style="margin-top: 50px">
		<div class="col-md-12 column">
			<ul class="breadcrumb">
				<li>青舍首页</li>
				<li id="search-type">${sessionScope.searchType}</li>
				<li class="active">${apartment.community}-${apartment.capacity }居室</li>
			</ul>
			<div class="row clearfix">
				<div class="col-md-8 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>${apartment.community}-${apartment.capacity }居室</h3>
							<p>${apartment.address}</p>
							<ul id="gallery">
								<c:forEach items="${apartment.pic2}" var="pic">
									<li><img src="../images/${pic}" /></li>
								</c:forEach>
								<c:forEach items="${apartment.pic3}" var="pic">
									<li><img src="../images/${pic}" /></li>
								</c:forEach>
							</ul>
						</div>

					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>公共设施</h3>
							<p>${apartment.description}</p>
							<c:forEach items="${apartment.facilityEntity}" var="f">
								<span class="label label-warning">${f.description}</span>
							</c:forEach>

							<p>${apartment.descriptionAround}</p>

						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>房间基本情况.</h3>
							<p>${room.descriptionPersonal}</p>
						</div>
					</div>
					<!--  
					<div class="row clearfix paymethod">
						<div class="col-md-12 column">
							<h3>付款方式</h3>
							<table class="table">
								<thead>
									<tr>
										<th>付款方式</th>
										<th>月租金</th>
										<th>押金</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>天付</td>
										<td>${room.prices[0]}元/天</td>
										<td>${room.prices[0]}元/天</td>
									</tr>
									<tr>
										<td>周付</td>
										<td>${room.prices[1]}元/周</td>
										<td>${room.prices[1]}元/周</td>
									</tr>
									<tr>
										<td>月付</td>
										<td>${room.prices[2]}元/月</td>
										<td>${room.prices[2]}元/月</td>
									</tr>
									<tr>
										<td>年付</td>
										<td>${room.prices[3]}元/年</td>
										<td>${room.prices[3]}元/年</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix my-apartment-friend">
						<div class="col-md-12 column">
							<h3>我的室友</h3>
							<table class="table">
								<thead>
									<tr>
										<th>房间</th>
										<th>面积</th>
										<th>朝向</th>
										<th>价格</th>
										<th>性别</th>
										<th>职业</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${apartment.rooms}" var="r">
										<c:if test="${r.id == room.id }">
											<tr class="info">
										</c:if>
										<c:if test="${r.id != room.id }">
											<tr class="">
										</c:if>
										<td>${r.description}</td>
										<td>${r.square}㎡</td>
										<td>${r.direction}</td>
										<c:if test="${r.status == '已出租' }">
											<td>已出租</td>
										</c:if>
										<c:if test="${r.status != '已出租' }">
											<td>${r.prices[2]}元/月</td>
										</c:if>
										<td></td>
										<td></td>
										<td></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
					 -->


					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>位置周边</h3>

							<img alt="140x140" class="img-responsive"
								src="http://api.map.baidu.com/staticimage?center=${apartment.longitude},${apartment.latitude}&width=810&height=420&zoom=16&markers=${apartment.longitude},${apartment.latitude}" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>公寓户型图</h3>
							<img alt="户型图" class="img-responsive"
								src="../images/${apartment.pic1}" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>用户评价</h3>
							<ul class="media-list">
								<li class="media">
									<div class="media-body">
										<div class="media">
											<a class="pull-left"> <img
												class="media-object img-circle "
												src="http://themeandphoto.com/taplivedemos/2014/09/15/bootstrap-chat-example/assets/img/user.png">
											</a>
											<div class="media-body">
												住着舒服，环境很好！！！ <br> <small class="text-muted">Alex
													Deo | 23rd June at 5:00pm</small>
												<hr>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-4 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3 class="text-left type-hotel-price">
								<small>￥</small>${room.prices[0]}<small>/天</small><small
									style="display: none;"> 原价:1480元/天</small>
							</h3>
							<h3 class="text-left type-apartment-price">
								<small>￥</small>${room.prices[2]}<small>/月</small><small
									style="display: none;"> 原价:1480元/月</small>
							</h3>
							<c:forEach items="${room.facilityEntity}" var="f">
								<span class="label label-default">${f.description}</span>
							</c:forEach>
							<ul>
								<li><strong>编号</strong> <span>${room.id}</span></li>
								<li><strong>楼层</strong> <span>第${apartment.floor}层/共${apartment.totalfloor}层</span>
								</li>
								<li><strong>单元</strong> <span>${apartment.num_unit}</span></li>
								<li><strong>房间</strong> <span>${apartment.num_door}</span></li>
								<li><strong>面积</strong> <span>${room.square}㎡</span></li>
								<li><strong>朝向</strong> <span>${room.direction}</span></li>
								<li><strong>可住</strong> <span>${room.capacity}人</span></li>
								<li><strong>居室</strong> <span>${apartment.bedroom}室${apartment.livingroom}厅${apartment.bathroom}卫${apartment.balcony}阳台</span></li>
								<li><strong>小区</strong> <span>${apartment.community}</span>
								</li>
							</ul>

							<hr class="divider">
							<br>
							<c:if test="${searchType == '酒店型公寓' }">
								<button type="button" class="btn btn-info btn-block"
									data-toggle="modal" data-target="#orderModal">立即预定</button>
							</c:if>
							<c:if test="${searchType == '短租型公寓' }">
								<button type="button" class="btn btn-info btn-block"
									data-toggle="modal" data-target="#reservationModal">预约看房</button>
							</c:if>
							<!-- Modal -->
							<div class="modal fade" id="reservationModal" tabindex="-1"
								role="dialog" aria-labelledby="reservationModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="reservationModalLabel">在线预约</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<form action="" method="post">
														姓名：<input type="text" name="cusName" value=""><br>
														手机：<input type="text" name="cusTel" value=""><br>
														个人需求：<input type="text" name="cusPersonal" value=""><br>
													</form>
												</div>
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary "
												id="resercation-submit">提交</button>
										</div>

									</div>
								</div>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="orderModal" tabindex="-1"
								role="dialog" aria-labelledby="orderModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
											</button>
											<h4 class="modal-title" id="orderModalLabel">确认订单</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<div class="col-md-12">
													<form action="../order/add" method="post" id="order-form">
														<input type="hidden" name="cusId" value="${c.id}"
															id="cusId"> <input type="hidden" name="roomId"
															value="${room.id}"> <input type="hidden"
															name="apartmentId" value="${room.apartment}"> <input
															type="hidden" name="type" value="1">
														<ul>
															<li><span>入离时间</span><span><input type="date"
																	class="order-date" id="order-start" name="startTime"></span>至<span><input
																	type="date" class="order-date" id="order-end"
																	name="endTime"></span>共<span id="order-total-day">0</span>晚<a
																class="btn">修改时间</a></li>
															<li><span>房费</span><span><small>￥</small><span
																	id="order-price">${room.prices[0]}</span></span><span></span></li>
															<li><span>住客姓名</span><span><input type="text"
																	name="cusName"></span></li>
															<li><span>身份证</span><input type="text"
																name="cusIdCard"></li>
															<li><span>联系手机</span><input type="text"
																name="cusTel"></li>
															<li><span>个人需求</span><input type="text"
																name="cusPersonal"></li>
															<li><span>发票</span><span><input
																	type="checkbox" name="needFapiao">是否需要发票</span></li>
																	<li><a>入住须知</a></li>
														</ul>
														<span>订单总额￥</span><span id="order-total-price">0.00</span>
													</form>
												</div>
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary "
												id="order-submit">提交</button>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>管家信息</h3>

							<em><img alt=""
								src="http://mf.znimg.com/public/images/mfxgj.jpg"
								class="img-responsiv img-circle" width="60px"></em>
							<ul>
								<li><strong>青舍小管家</strong></li>
								<li><small>400-0322-222</small>
								<li><small>周一至周日 07:00 - 23:00</small>
							</ul>
							<p>用心倾听每一通来电，认真记录每一个需求，快速解决生活当中的每一个麻烦，我们就是您在青舍都市公寓的贴心小管家，随时恭候您的来电！</p>

						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>此公寓其他房间</h3>
							<c:forEach items="${apartment.rooms}" var="r">
								<c:if test="${room.id != r.id}">
									<a href="../info/${r.id}"> <img alt="140x140"
										class="img-responsive" src="../images/${r.pics[0]}" /> <em>${apartment.community}-${apartment.bedroom}居室-${r.direction}卧-${r.description}</em>
										<strong>${r.status}</strong>
									</a>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</my_body>
	<my_script> <script
		src="<%=basePath%>/dist/customer/js/info.js"></script></my_script>
</body>
</html>