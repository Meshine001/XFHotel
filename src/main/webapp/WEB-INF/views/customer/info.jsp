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
				<li>西安租房</li>
				<li class="active">${apartment.community}-${apartment.capacity }居室-${room.direction}-${room.description}</li>
			</ul>
			<div class="row clearfix">
				<div class="col-md-8 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>${apartment.community}-${apartment.capacity }居室-${room.direction}-${room.description}</h3>
							<p>${apartment.address}</p>
							<ul id="gallery">
								<c:forEach items="${room.pics}" var="pic">
									<li><img src="../images/${pic}" /></li>
								</c:forEach>
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
							<h3>房间基本情况.</h3>
							<c:forEach items="${room.facilityEntity}" var="f">
								<span class="label label-warning">${f.description}</span>
							</c:forEach>
						</div>
					</div>
					<div class="row clearfix">
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
					<div class="row clearfix" style="display: none;">
						<div class="col-md-12 column">
							<h3>我的室友</h3>
							<table class="table">
								<thead>
									<tr>
										<th>房间</th>
										<th>面积</th>
										<th>朝向</th>
										<th>特色</th>
										<th>价格</th>
										<th>性别</th>
										<th>职业</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>A房间</td>
										<td>32.67㎡</td>
										<td>南</td>
										<td>独卫 飘窗</td>
										<td>已出租</td>
										<td>男</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>B房间</td>
										<td>32.67㎡</td>
										<td>南</td>
										<td>独卫 飘窗</td>
										<td>已出租</td>
										<td>男</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>C房间</td>
										<td>32.67㎡</td>
										<td>南</td>
										<td>独卫 飘窗</td>
										<td>已出租</td>
										<td>男</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td>D房间</td>
										<td>32.67㎡</td>
										<td>南</td>
										<td>独卫 飘窗</td>
										<td>已出租</td>
										<td>男</td>
										<td></td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>公共设施</h3>
							<p>${apartment.description}</p>
							<c:forEach items="${apartment.facilityEntity}" var="f">
								<span class="label label-warning">${f.description}</span>
							</c:forEach>

							<p style="display: none;">
								温馨舒适，品牌家电，免费无线，单周保洁，合理布局，特制装修风格，品质家具，你想要的，魔飞应有尽有，让你享尽家的温暖。</p>
						</div>
					</div>

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
				</div>
				<div class="col-md-4 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3 class="text-left">
								<small>￥</small>${room.prices[2]}<small>/月</small><small
									style="display: none;"> 原价:1480元/月</small>
							</h3>
							<c:forEach items="${room.facilityEntity}" var="f">
								<span class="label label-default">${f.description}</span>
							</c:forEach>
							<dl class="dl-horizontal">
								<dt>编号</dt>
								<dd>${room.id}</dd>
								<dt>楼层</dt>
								<dd>第${apartment.floor}层/共${apartment.totalfloor}层</dd>
								<dt>卧室</dt>
								<dd>${room.type}</dd>
								<dt>面积</dt>
								<dd>${room.square}㎡</dd>
								<dt>朝向</dt>
								<dd>${room.direction}</dd>
								<dt>可住</dt>
								<dd>${room.capacity}人</dd>
								<dt>居室</dt>
								<dd>${apartment.bedroom}室${apartment.livingroom}厅${apartment.bathroom}卫${apartment.balcony}阳台</dd>
								<dt>小区</dt>
								<dd>${apartment.community}</dd>
							</dl>

							<hr class="divider">
							<br>
							<button type="button" class="btn btn-info btn-block"
								data-toggle="modal" data-target="#orderModal">立即预定</button>
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
													<h3>${apartment.community}-${apartment.capacity }居室-${room.direction}-${room.description}</h3>
													<strong>编号:</strong>${room.id} <br> <strong>楼层:</strong>第${apartment.floor}层/共${apartment.totalfloor}层
													<br> <strong>卧室:</strong>类型：${room.type}&nbsp;面积：${room.square}㎡&nbsp;朝向：${room.direction}
													<br> <strong>可住:</strong>${room.capacity}人 <br> <strong>小区:</strong>${apartment.community}
													<br> <strong>设施:</strong>
													<c:forEach items="${room.facilityEntity}" var="f">
														<span class="label label-warning">${f.description}</span>
													</c:forEach>
													<br> <strong>姓名:</strong><input type="text"
														name="name"> <br> <strong>手机:</strong><input
														type="text" name="tel"> <br> <strong>个人需求:</strong><input
														type="text" name="personal"> <br> <strong>入住时间:</strong><input
														type="date" name="startTime"> 至<input type="date"
														name="endTime"> <br> <br>
													<strong>共:</strong><input type="text" name="totalDay">天
													<br>
													<strong>总价:</strong><input type="text" name="totalPice">元
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">取消</button>
											<button type="button" class="btn btn-primary">提交</button>
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