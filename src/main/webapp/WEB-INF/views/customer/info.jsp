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
				<li class="active">${room.apartment.community}-${room.apartment.capacity }居室-${room.direction}-${room.description}</li>
			</ul>
			<div class="row clearfix">
				<div class="col-md-8 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>${room.apartment.community}-${room.apartment.capacity }居室-${room.direction}-${room.description}</h3>
							<p>${room.apartment.address}</p>

							<img alt="140x140" class="img-responsive"
								src="../images/${room.pics[0]}" />

						</div>
						<div class="gallery">
							<div class="col-md-3 column ">
							<c:forEach items="${room.pics}" var="pic">
								<div>
									<a href="../images/${pic}"><img width="60px" src="../images/${pic}" /></a>
								</div>
							</c:forEach>
							<c:forEach items="${room.department.pics}" var="pic">
								<div>
									<a href="../images/${pic}"><img src="../images/${pic}" /></a>
								</div>
							</c:forEach>
						</div>
						</div>
						
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>房间基本情况.</h3>
							<p>${room.apartment.description}</p>
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
										<th>服务费</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>月付</td>
										<td>¥1302/月</td>
										<td>¥1480</td>
										<td>¥104/月</td>
									</tr>
									<tr class="success">
										<td>季付</td>
										<td>¥1302/月</td>
										<td>¥1480</td>
										<td>¥104/月</td>
									</tr>
									<tr class="error">
										<td>半年付</td>
										<td>T¥1276/月</td>
										<td>¥1480</td>
										<td>¥102/月</td>
									</tr>
									<tr class="warning">
										<td>年付</td>
										<td>¥1250/月</td>
										<td>¥1480</td>
										<td>¥100/月/td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row clearfix">
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
							<p>
								温馨舒适，品牌家电，免费无线，单周保洁，合理布局，特制装修风格，品质家具，你想要的，魔飞应有尽有，让你享尽家的温暖。</p>
						</div>
					</div>

					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>位置周边</h3>
							<img alt="140x140" class="img-responsive"
								src="http://api.map.baidu.com/staticimage?center=113.817967,34.772957&width=810&height=420&zoom=16&markers=113.817967,34.772957" />
						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>公寓户型图</h3>
							<img alt="140x140" class="img-responsive"
								src="http://mf.znimg.com/upload/house_img/734/ab88d32926ff8e5766a1e2903def1b95.jpg" />
						</div>
					</div>
				</div>
				<div class="col-md-4 column">
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3 class="text-left">
								<small>￥</small>1320<small>/月</small><small> 原价:1480元/月</small>
							</h3>
							<span class="label label-default">地铁2号线</span> <span
								class="label label-default">暖气</span>
							<dl class="dl-horizontal">
								<dt>编号</dt>
								<dd>41018236703</dd>
								<dt>楼层</dt>
								<dd>第26层/共32层</dd>
								<dt>卧室</dt>
								<dd>面积：21.61㎡ 朝向：南</dd>
								<dt>可住</dt>
								<dd>2人</dd>
								<dt>居室室</dt>
								<dd>5室1厅2卫0阳台</dd>
								<dt>小区</dt>
								<dd>安和小区</dd>
							</dl>
							<h4>付款方式</h4>
							<div class="col-md-12 column">
								<div class="col-md-3 ">
									<button type="button" class="btn btn-default btn-block">月付</button>
								</div>
								<div class="col-md-3 ">
									<button type="button" class="btn btn-default btn-block">季付</button>
								</div>
								<div class="col-md-3 ">
									<button type="button" class="btn btn-default btn-block">半年付</button>
								</div>
								<div class="col-md-3">
									<button type="button" class="btn btn-default btn-block">年付</button>
								</div>
							</div>
							<hr class="divider">
							<br>
							<button type="button" class="btn btn-default btn-block">预约看房</button>
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
							<p>用心倾听每一通来电，认真记录每一个需求，快速解决生活当中的每一个麻烦，我们就是您在魔飞公寓的贴心小管家，随时恭候您的来电！</p>

						</div>
					</div>
					<div class="row clearfix">
						<div class="col-md-12 column">
							<h3>此公寓其他房间</h3>
							<img alt="140x140" class="img-responsive"
								src="http://mf.znimg.com/thumb/dress_810x497/house_img/734/bcf19a0e72b41ef93d41f49374167924.jpg" />
							<p>
								<em>安和小区5居室-南卧-A房间-带独卫、飘窗</em> <strong>已出租</strong>
							</p>
							<img alt="140x140" class="img-responsive"
								src="http://mf.znimg.com/thumb/dress_810x497/house_img/734/a28958eb2530230ba8b09f63fe7c35b3.jpg" />
							<p>
								<em>安和小区5居室-南卧-A房间-带独卫、飘窗</em> <strong>已出租</strong>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</my_body>
</body>
</html>