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
<my_header>
<script src="<%=basePath%>/dist/public/js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/colorbox.css">
<script src="<%=basePath%>/dist/public/js/jquery.colorbox-min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/info.css">
</my_header>
</head>
<body>
	<my_body>
	<article>
		<div class="x">
			<div class="x_l">
				<div class="x_t">
					<h1>${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间</h1>
					<div class="x_add">
						<span class="x_dizhi"> <i></i>${apartment.address}
						</span> <a class="f_map"> <i></i> 导航
						</a> <span class="b_ewm"> <i class="ewm_i"></i> 在线分享
						</span> <span class="b_sc" style="cursor: pointer;"> <i></i> 加入收藏
						</span>
					</div>
					<div class="f_img">
						<img id="datu" src="../images/${apartment.picShow[0]}" width="810"
							height="497" index="0">
					</div>
					<div class="f_img_list">
						<i id="pre" class="bt_left"></i>
						<div class="w00" style="width: 770px">
							<ul
								style="width: 2190px; margin-left: -10px; position: relative;">
								<c:forEach items="${apartment.picShow}" var="pic" varStatus="p">
									<li index="${p.index }"><img src="../images/${pic}"
										width="138" height="84" /></li>
								</c:forEach>
							</ul>
						</div>
						<i id="next" class="bt_right"></i>
					</div>
				</div>
				<div class="x_i">
					<div>
						<div class="i_z">个性描述</div>
						<div class="x_fj_info">
							<p>${room.descriptionPersonal}</p>
						</div>
					</div>
					<div>
						<div class="i_z">周边描述</div>
						<div class="x_fj_info">
							<p>${apartment.descriptionAround}</p>
						</div>
					</div>
					<div>
						<div class="i_z">房间特色</div>

					</div>
					<div>
						<div class="i_z">配套设施</div>

					</div>
					<div>
						<div class="i_z">位置周边</div>
						<div class="wz_ditu">
							<img alt="" width="810" height="420"
								src="http://api.map.baidu.com/staticimage?center=${apartment.longitude},${apartment.latitude}&width=810&height=420&zoom=16&markers=${apartment.longitude},${apartment.latitude}" />
						</div>
					</div>
					<div>
						<div class="i_z">公寓户型</div>
						<div class="hx_tu">
							<img alt="户型图" width="810" src="../images/${apartment.pic1}" />
						</div>
					</div>
				</div>
				<div class="x_tiao_kuan">
					<div>
						<h1>押金及其他费用</h1>
						<p>描述</p>
					</div>
					<div>
						<h1>预定条款</h1>
						<p>描述</p>
					</div>
					<div>
						<h1>退订政策</h1>
						<div class="tui_ding">
							<img alt="" src="">
						</div>
					</div>
				</div>
				<div class="x_ping_jia">
					<h1>服务评价</h1>
					<div id="comment_box">
						<div class="comment_box clearfix">
							<div class="score_l">
								<span class="x_textscore">4.9<em class="f_24">/5分</em></span>
							</div>
							<ul class="score_r clearfix">
								<li><span>整洁卫生</span>4.9分</li>
								<li><span>描述相符</span>4.9分</li>
								<li><span>交通位置</span>4.9分</li>
								<li><span>安全程度</span>4.9分</li>
								<li><span>性价比</span>4.8分</li>
							</ul>
						</div>
						<div class="comment_list">
							<div class="dp_box clearfix mt_10">
								<img alt="" class="dp_pic72"
									src="http://image.xiaozhustatic1.com/22/8,0,19,8755,260,260,976ffdd9.jpg">
								<div class="dp_con">
									<h6 data-id="7961541214">
										<a href="http://www.xiaozhu.com/fangke/6779940515/"
											target="_blank"><span class="col_pink">leafstar</span></a>入住时间：
										<i>2017年01月</i>
									</h6>

									两家人一起出行，中间出现许多小插曲，差点来不了…很早就预定了这间，与房东沟通了很多次，感觉是很直率的人，比较热情~~~房间有桶装水，很方便，床品质量不错。沙发有些旧，其他设施健全…不过有蚊子，宝宝第一天就被咬了三个包，有一个还在眼睛上…不得不说小区周边环境真的非常好！！！
									<ul class="pic_show clearfix">
										<li><a href="javascript:void(0);"
											class="thumbimg detail_comment_thumbimg" imgid="1"
											commentid="7961541214"><img lazy_src="finish" alt=""
												src="http://image.xiaozhustatic2.com/00,65,87/8,0,17,11608,1500,2000,ded58d3c.jpg"></a></li>
										<li><a href="javascript:void(0);"
											class="thumbimg detail_comment_thumbimg" imgid="2"
											commentid="7961541214"><img lazy_src="finish" alt=""
												src="http://image.xiaozhustatic3.com/00,65,87/8,0,84,11646,1500,2000,760edd13.jpg"></a></li>
										<li><a href="javascript:void(0);"
											class="thumbimg detail_comment_thumbimg" imgid="3"
											commentid="7961541214"><img lazy_src="finish" alt=""
												src="http://image.xiaozhustatic1.com/00,65,87/8,0,49,11824,1500,2000,9f923b86.jpg"></a></li>

									</ul>
									<div class="reply_box">
										<div class="arrow_top"></div>
										<h5>管家回复:</h5>
										<p>谢谢您的选择。祝您生活愉快！</p>
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="x_r">
				<div class="x_m">
					<div class="m_q">
						<span class="m_jiage"><em>￥</em><span id="zujin">${room.prices[0]}</span><font>起</font></span>
					</div>
					<div class="">
					   <input type="hidden" id="apartmentId" value="${apartment.id}">
						<div id="detailBookArea">
							<div class="reserve_box">
								<div class="reserve_space">
									<div class="clearfix">
										<div class="reserve_date">
											<div class="reserve_ico" id="detailCalendarIco"></div>
											<input type="text" class="date_input" id="startenddate"
												readonly="" value="请选择入离时间"> <input
												type="hidden" name="startdate" id="startdate"
												value="${startDate}" class="hasDatepicker"> <input
												type="hidden" name="enddate" id="enddate" value="${endDate}"
												class="hasDatepicker">
											<div id="calendar-box" style="display: none"
												class="calendar_box clearfix"></div>
										</div>
										<div class="select_box">
											<div class="select_arrow"></div>
											<input id="sameRoomNum" readonly="readOnly"
												data-bookroomnum="" type="text" value="日">
											<ul class="select_con room_num_select"
												id="detailRoomNumSelect">
												<li data-num="1">1间</li>
											</ul>
										</div>
									</div>
									<div class="price_top" style="display: none">
										<div class="reserve_text">
											在线收取押金￥<span>300</span>
										</div>
									</div>
									<div class="order_btn_container">
										<c:choose>
											<c:when test="${c != null }">
											<a class="order_btn" href="../order/module?" id="day_yuding">立即预订<span
											class="f14"></span></a>
											</c:when>
											<c:otherwise>
												<a class="order_btn" href="../customer/login?forwad=login" id="need_login">立即预订<span
											class="f14"></span></a>
											</c:otherwise>
										</c:choose>
										
									</div>
								</div>
								<div class="white_bg">
									<input type="hidden" id="avgprice" value="100"> <input
										type="hidden" id="priceTip" value="">
									<ul class="cal_box clearfix">
									<c:forEach items="${prices}" var="p" varStatus="pp">
										<c:choose>
											<c:when test="${pp.index == 0}">
											<li>今天 <br>¥${p.price}
											</li>		
											</c:when>
											<c:otherwise>
												<li>${p.date}<br>¥${p.price}
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
										
										<li class="line_none_r" style="display: none;"><a id="showMoreCalendar" href="#">全部<br>日历
										</a></li>
									</ul>
								</div>
							</div>
						</div>

						<input type="hidden" id="minDay" value="1"> <input
							type="hidden" id="maxDay" value="15">
					</div>
				</div>
				<div class="x_if">
					<div class="if_1">
						<p class="if_biaoqian">
							<c:forEach items="${room.facilityEntity}" var="f">
								<span>${f.description}</span>
							</c:forEach>
						</p>
						<ul>
							<li><span class="c99">编号</span><span class="c66">${room.id}</span></li>
							<li><span class="c99">楼层</span><span class="c66">第${apartment.floor}层/共${apartment.totalfloor}层</span></li>
							<li><span class="c99">单元</span><span class="c66">${apartment.num_unit}</span></li>
							<li><span class="c99">房号</span><span class="c66">${apartment.num_door}</span></li>
							<li><span class="c99">面积</span><span class="c66">${room.square}㎡</span><span
								class="c66">${room.direction}</span></li>
							<li><span class="c99">可住</span><span class="c66">${room.capacity}人</span></li>
							<li><span class="c99">居室</span><span class="c66">${apartment.bedroom}室${apartment.livingroom}厅${apartment.bathroom}卫${apartment.balcony}阳台</span></li>
							<li><span class="c99">小区</span><span class="c66">${apartment.community}</span></li>
						</ul>
					</div>
				</div>
				<div class="x_gj">
					<h5>管家信息</h5>
					<div class="gj_info">
						<img src="" width="110" height="110">
						<div class="gj_mz">
							<span class="gj_name">在线管家</span><span class="gj_tel"></span>周一至周日
							07:00 - 23:00 <span class="gj_chat"><i></i>在线聊天</span>
						</div>
					</div>
					<p></p>
				</div>
				<div class="inter">
					<h4>你可能会喜欢</h4>
					<ul>
						<c:forEach items="${apartment.rooms}" var="r">
							<c:if test="${room.id != r.id}">
								<a href="../info/${r.id}"> <img alt="140x140"
									class="img-responsive" src="../images/${r.pics[0]}" /> <em>${apartment.community}-${apartment.bedroom}居室-${r.direction}卧-${r.description}</em>
									<strong>${r.status}</strong>
								</a>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</article>
	<div class="row clearfix" style="margin-top: 50px; display: none;">
		<div class="col-md-12 column">
			<div class="row clearfix">
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

							<hr class="divider">
							<br>
							<button type="button" class="btn btn-info btn-block"
								data-toggle="modal" data-target="#orderModal">立即预定</button>
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
		src="<%=basePath%>/dist/customer/js/info.js"></script> </my_script>
</body>
</html>