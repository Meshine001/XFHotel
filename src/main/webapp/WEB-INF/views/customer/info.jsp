<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>公寓详情-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<my_header>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/info.css">
<script type="text/javascript"
	src="<%=basePath%>/dist/zui/lib/price-calendar/js/yui-min.js"></script>
<script type="text/javascript">
	
</script>
<style type="text/css">
#newBridge ins#nb_icon_wrap {
	display: none;
}
</style>
</my_header>
</head>
<body>
	<my_body>
	<article>
		<div class="x">
			<div class="x_l">
				<div class="x_t">
					<h1>${apartment.position.xiao_qu}-${apartment.position.xa_wei_zhi}-${apartment.basic_info.cao_xiang}朝向</h1>
					<div class="x_add">
						<span class="x_dizhi"> <i></i>${apartment.position.bd_wei_zhi}-${apartment.position.jie_dao}
						</span> <a href="javascrip:;" id="call-gps" class="f_map"
							data-iframeUrl="<%=basePath%>/utils/roomGps?lng=${apartment.position.jing_du}&lat=${apartment.position.wei_du}"
							data-target="#gpsModal" data-toggle="modal"> <i></i> 导航
						</a> <span class="b_ewm" style="display: none;"> <i
							class="ewm_i"></i> 在线分享
						</span> <span class="b_sc" style="display: none; cursor: pointer;">
							<i></i> 加入收藏
						</span>
					</div>
					<div class="f_img">
						<img id="datu" src="../images/${apartment.pic_show[0]}"
							width="810" height="497" index="0">
					</div>
					<div class="f_img_list">
						<i id="pre" class="bt_left"></i>
						<div class="w00" style="width: 770px">
							<ul
								style="width: 2190px; margin-left: -10px; position: relative;">
								<c:forEach items="${apartment.pic_show}" var="pic" varStatus="p">
									<li index="${p.index }"><img src="../images/${pic}"
										width="138" height="84" /></li>
								</c:forEach>
							</ul>
						</div>
						<i id="next" class="bt_right"></i>
					</div>
				</div>
				<div class="x_i">
					<div style="clear:both">
						<div class="i_z">综合描述</div>
						<p class="det-line-h"></p>
						<div class="x_fj_info">
							<p>${apartment.description}</p>
						</div>
					</div>
					<div>
						<div class="i_z">配套设施</div>
						<p class="det-line-h"></p>
						<div class="x_fj_info">
							<div style="clear:both">
								<div class="i_z">特色</div>
								<p class="i_span">
									<c:forEach items="${apartment.te_se}" var="f">
										<span><i></i>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">家居</div>
								<p class="i_span">
									<c:forEach items="${apartment.jia_ju}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">卫浴</div>
								<p class="i_span">
									<c:forEach items="${apartment.wei_yu}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">餐厨</div>
								<p class="i_span">
									<c:forEach items="${apartment.can_chu}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">配套</div>
								<p class="i_span">
									<c:forEach items="${apartment.pei_tao}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">周边</div>
								<p class="i_span">
									<c:forEach items="${apartment.zou_bian}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
							<div style="clear:both">
								<div class="i_z">其他</div>
								<p class="i_span">
									<c:forEach items="${apartment.qi_ta}" var="f">
										<span>${f}</span>
									</c:forEach>
								</p>

							</div>
						</div>
					</div>

					<div>
						<div class="i_z">位置地图</div>
						<p class="det-line-h"></p>
						<div class="wz_ditu">
							<img alt="" width="810" height="420"
								src="http://api.map.baidu.com/staticimage?center=${apartment.position.jing_du},${apartment.position.wei_du}&width=810&height=420&zoom=16&markers=${apartment.position.jing_du},${apartment.position.wei_du}" />
						</div>
					</div>
					<div style="display: none;">
						<div class="i_z">公寓户型</div>
						<div class="hx_tu">
							<img alt="户型图" width="810"
								src="../images/${apartment.hu_xing_tu}" />
						</div>
					</div>
				</div>
				<div class="x_tiao_kuan">
					<div class="tiao_kuan_meta">
						<h1>押金及其他费用</h1>
						<p>

							在线收取押金：${yajin}元 <br> 是否允许加客：允许<br>
						</p>
						<div class="clearfix">其他额外费用：无</div>
					</div>
					<div class="tiao_kuan_meta">
						<h1>预定条款</h1>
						<p>描述</p>
					</div>
					<div class="tiao_kuan_meta">
						<h1>退订政策</h1>
						<div class="tui_ding">
							<div class="order_h">
								<div class="order_bar">
									<ul class="clearfix">
										<li></li>
									</ul>
									<div class="order_tip1 pos_1">如取消订单，订金全部退还</div>
									<div class="order_tip2 pos_2">如取消订单，扣除前1天的订金。</div>
									<div class="order_tip2 pos_3">如提前退房，扣除之后1天的订金。</div>
									<div class="day_tip">
										<span class="point1"></span>
										<p id="pos_n_1">预订成功</p>
									</div>
									<div class="day_tip pos_4">
										<span class="point1"></span>
										<p id="pos_n_2">入住前1天14：00前</p>
									</div>
									<div class="day_tip pos_5">
										<span class="point2"></span>
										<p id="pos_n_3">入住当天 14：00前</p>
									</div>
									<div class="day_tip pos_6">
										<span class="point3"></span>
										<p id="pos_n_4">退房当天 12：00前</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="x_ping_jia">
					<h1 style="font-size:18px;color:#5ccece;">服务评价</h1>
					<div id="comment_box">
						<div class="comment_box clearfix">
							<div class="score_l">
								<span class="x_textscore">综合评分；<i>${roomRates.pingjun}</i><em
									class="f_24">/5分</em></span>
							</div>
							<ul class="score_r clearfix">
								<li><span>整洁卫生</span><i>${roomRates.weisheng}</i>分</li>
								<li><span>描述相符</span><i>${roomRates.miaoshu}</i>分</li>
								<li><span>交通位置</span><i>${roomRates.jiaotong}</i>分</li>
								<li><span>安全程度</span><i>${roomRates.anquan}</i>分</li>
								<li><span>性价比</span><i>${roomRates.xingjiabi}</i>分</li>
							</ul>
						</div>
						<div class="comment_list" data-url="<%=basePath%>"
							data-room="${apartment.id}" data-page="1"></div>
					</div>
				</div>
			</div>
			<div class="x_r">
				<div class="x_m">
					<div class="m_q">
						<span class="m_jiage"><em>￥</em><span id="zujin">${apartment.basic_info.jia_ge}</span><font>起</font></span>
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
												readonly="" value="请选择入离时间"> <input type="hidden"
												name="startdate" id="startdate" value="${startDate}"
												class="hasDatepicker"> <input type="hidden"
												name="enddate" id="enddate" value="${endDate}"
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
									<div class="order_btn_container">
										<c:choose>
											<c:when test="${c != null }">
												<a style="cursor: pointer;" class="order_btn"
													id="day_yuding" data-show-header="false"
													data-baseurl="<%=basePath%>/order/module"
													data-remote="<%=basePath%>/order/module?"
													data-toggle="modal">立即预订<span class="f14"></span></a>
											</c:when>
											<c:otherwise>
												<a style="cursor: pointer;" class="order_btn"
													data-toggle="modal" data-target="#loginModal"
													id="need_login">立即预订<span class="f14"></span></a>
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

										<li class="line_none_r" style="display: none;"><a
											id="showMoreCalendar" href="#">全部<br>日历
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
							<li style="display: none;"><span class="c99">编号</span><span
								class="c66">${apartment.id}</span></li>
							<li><span class="c99">面积</span><span class="c66">${apartment.basic_info.mian_ji}㎡</span></li>
							<li><span class="c99">朝向</span><span class="c66">${apartment.basic_info.cao_xiang}</span></li>
							<li><span class="c99">可住</span><span class="c66">${apartment.basic_info.reng_shu}人</span></li>
							<li><span class="c99">居室</span><span class="c66">${apartment.basic_info.shi}室${apartment.basic_info.ting}厅${apartment.basic_info.wei}卫${apartment.basic_info.yang_tai}阳台</span></li>
							<li><span class="c99">小区</span><span class="c66">${apartment.basic_info.cao_xiang}</span></li>
						</ul>
					</div>
				</div>
				<div class="x_gj">
					<h5>管家信息</h5>
					<div class="gj_info">
						<img src="<%=basePath%>/dist/public/images/kefu.jpg" width="110"
							height="110">
						<div class="gj_mz">
							<span class="gj_name">在线管家</span><span class="gj_tel"></span>周一至周日
							07:00 - 23:00 <span class="gj_chat online-chat"
								style="cursor: pointer;"><i></i>在线聊天</span>
						</div>
					</div>
					<p></p>
				</div>
				<div class="inter">
					<h4>你可能会喜欢</h4>
					<ul>
						<c:forEach items="${allApartment}" var="r" varStatus="p">
							<c:if test="${p.index < 5}">
							<c:if test="${apartment.id != r.id}">
								<a href="../info/${r.id}"> <img alt="140x140"
									class="img-responsive" src="../images/${r.fang_jian_tu[0]}" /> <em>${apartment.basic_info.xiao_qu}-${apartment.basic_info.cao_xiang}朝向</em>
								</a>
							</c:if>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</article>
	<div class="modal fade" id="gpsModal">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
				</button>
			</div>
			<div class="modal-body">
				<iframe id="map-iframe" width="100%" height="100%"
					style="border: none;" src=""> </iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#call-gps').click(function() {
			$('#map-iframe').attr('src', $(this).attr('data-iframeUrl'));
		});
	</script> <script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?de892eb7bcb9d66253676ee4d1a31276";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
		$('.online-chat').click(function() {
			$('#nb_icon_wrap').click();
		});
	</script> </my_body>
	<my_script> <script
		src="<%=basePath%>/dist/customer/js/info.js"></script> <script
		src="<%=basePath%>/dist/customer/js/comment-list.js"></script> </my_script>
</body>
</html>