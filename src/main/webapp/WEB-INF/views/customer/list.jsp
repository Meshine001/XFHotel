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
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/list.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/jquery-ui.css">
<script type="text/javascript"
	src="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/dist/commons/jquery-ui-1.12.1.custom/datepicker-zh-CN.js"></script>
</my_header>
</head>
<body>
	<my_body>
	<article>
		<div class="ser_box">
			<div class="b_seach">
				<form action="<%=basePath%>/list" method="get" id="search-box-form">
					<input id="searchtxt" class="b_srh ui-autocomplete-input"
						type="text" value="${info.searchData.moreStr}" name="moreStr"
						placeholder="我想住..." autocomplete="off"> <input
						name="startTime" id="startTime" type="hidden"
						value="${info.searchData.startTime}" autocomplete="off"> <input
						name="endTime" id="endTime" type="hidden"
						value="${info.searchData.endTime}" autocomplete="off"> <input
						name="area" id="area" type="hidden"
						value="${info.searchData.area}" autocomplete="off"> <input
						name="priceRange" id="priceRange" type="hidden"
						value="${info.searchData.priceRange}" autocomplete="off">
					<input name="layout" id="layout" type="hidden"
						value="${info.searchData.layout}" autocomplete="off"> <input
						name="enterTime" id="enterTime" type="hidden"
						value="${info.searchData.enterTime}" autocomplete="off"> <input
						name="leaseType" id="leaseType" type="hidden"
						value="${info.searchData.leaseType}" autocomplete="off">
					<c:forEach items="${info.searchData.features}" var="f"
						varStatus="p">
						<input class="features" name="features" id="features"
							type="hidden" value="${f}" autocomplete="off">
					</c:forEach>
					<input name="sortType" id="sortType" type="hidden"
						value="${info.searchData.sortType}" autocomplete="off"><input
						id="search-submit" class="btn_sh" type="submit"
						value="搜&nbsp;&nbsp;索" name="">
				</form>
				<span style="font-size: 14px; margin-left: 10px; color: #3a5067;">热门搜索：</span><a
					href="/dibiao/bishagang/" title="碧沙岗" target="_blank"
					style="margin-left: 0px;"></a><a href="/dibiao/haiyangguan/"
					title="海洋馆" target="_blank"></a><a
					href="/dibiao/ZhengDongXinQuHuiZhanZhongXin/" title="会展中心"
					target="_blank">xxx</a> <a href="/baojie.html" target="_blank"
					style="color: #ff7e7e; float: right;">tips....</a>
			</div>
			<div class="ser_cg" style="height: 0px;">
				<!--<dl>
                <dd class="ser_cgon">魔飞合租</dd>
                <dd onclick="location.href='/zhengzu/'">魔飞整租</dd>
                <dt></dt>
            </dl>-->
			</div>
			<div class="ser_con">
				<div class="srh_box">
					<!--全部的选中样式及后面的选中样式为class="srh_all_on"-->
					<dl class="srh_list">
						<dd>时间：</dd>
						<dt>
							<ul class="srh_time">
								<li style="width: auto;"><span> <input id="startDateInput"
										value="${info.searchData.startTime}" readonly="readonly"><b
										class="time_icon"></b>
								</span> <span>至</span> <span> <input id="endDateInput"
										value="${info.searchData.endTime}" readonly="readonly"><b
										class="time_icon"></b>
								</span></li>
							</ul>
						</dt>

					</dl>
					<dl id="area_dl" class="srh_list">
						<dd>区域：</dd>
						<dt>
							<ul id="area_ul">
								<c:forEach items="${info.areas}" var="area">
									<c:choose>
										<c:when test="${area.areaId == 0}">
											<span class="srh_all" data-id="${area.areaId}">${area.areaName}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${area.areaId}">${area.areaName}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>

						</dt>
					</dl>
					<dl id="ditie_dl" class="srh_list" style="display: none;">
						<dd>地铁：</dd>
						<dt>
							<ul id="ditie_ul">
								<span class="srh_all srh_all_on">不限</span>
								<li id="1"><span>1号线</span></li>
								<li id="2"><span>2号线</span></li>
							</ul>
						</dt>
					</dl>
					<dl id="dibiao_dl1" class="srh_list"
						style="margin-top: 10px; display: none;">
						<dd>&nbsp;&nbsp;</dd>
						<dt>
							<ul id="dibiao_ul">
								<li id="2884"><span>西流湖站</span></li>
								<li id="2863"><span>西三环站</span></li>
								<li id="2882"><span>秦岭路站</span></li>
								<li id="2865"><span>桐柏路站</span></li>
								<li id="2883"><span>碧沙岗站</span></li>
								<li id="2859"><span>绿城广场站</span></li>
								<li id="2861"><span>医学院站</span></li>
								<li id="2856"><span>郑州火车站</span></li>
								<li id="2864"><span>二七广场站</span></li>
								<li id="2877"><span>人民路站</span></li>
								<li id="2854"><span>紫荆山站</span></li>
								<li id="2858"><span>燕庄站</span></li>
								<li id="2867"><span>民航路站</span></li>
								<li id="2862"><span>会展中心站</span></li>
								<li id="2860"><span>黄河南路站</span></li>
								<li id="2873"><span>农业南路站</span></li>
								<li id="2866"><span>东风南路站</span></li>
								<li id="2855"><span>郑州东站</span></li>
								<li id="2872"><span>博学路站</span></li>
								<li id="2857"><span>市体育中心站</span></li>
							</ul>
						</dt>
					</dl>
					<dl id="dibiao_dl2" class="srh_list"
						style="margin-top: 10px; display: none;">
						<dd>&nbsp;&nbsp;</dd>
						<dt>
							<ul id="dibiao_ul">
								<li id="2868"><span>刘庄站</span></li>
								<li id="2871"><span>柳林站</span></li>
								<li id="2870"><span>沙门站</span></li>
								<li id="2869"><span>北三环站</span></li>
								<li id="2875"><span>东风路站</span></li>
								<li id="2874"><span>关虎屯站</span></li>
								<li id="2876"><span>黄河路站</span></li>
								<li id="2854"><span>紫荆山站</span></li>
								<li id="2879"><span>东大街站</span></li>
								<li id="2880"><span>陇海东路站</span></li>
								<li id="2881"><span>二里岗站</span></li>
								<li id="2878"><span>南五里堡站</span></li>
								<li id="2885"><span>花寨站</span></li>
								<li id="2887"><span>南三环站</span></li>
								<li id="2886"><span>站马屯站</span></li>
								<li id="2888"><span>南四环站</span></li>
							</ul>
						</dt>
					</dl>
					<dl class="srh_list">
						<dd>租金：</dd>
						<dt>
							<ul id="priceRange_ul">
								<c:forEach items="${info.priceRanges}" var="priceRange">
									<c:choose>
										<c:when test="${priceRange.priceId == 0}">
											<span class="srh_all" data-id="${priceRange.priceId}">${priceRange.price}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${priceRange.priceId}">${priceRange.price}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</dt>
					</dl>
					<!--            <dl class="srh_list">
              <dd>面积：</dd>
              <dt>
                <ul id="mianji_ul">
                  <span class="srh_all srh_all_on">全部</span>
                  <li id="1"><span>10㎡以下</span></li>
                  <li id="2"><span>10-15㎡</span></li>
                  <li id="3"><span>15-20㎡</span></li>
                  <li id="4"><span>20㎡以上</span></li>
                </ul>
              </dt>
            </dl>-->
					<dl class="srh_list">
						<dd>户型：</dd>
						<dt>
							<ul id="layout_ul">
								<c:forEach items="${info.layoutTypes}" var="layout">
									<c:choose>
										<c:when test="${layout.layoutId == 0}">
											<span class="srh_all" data-id="${layout.layoutId}">${layout.layout}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${layout.layoutId}">${layout.layout}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</dt>
					</dl>
					<!--因此处的选中样式与其它不一样，此处的选中样<span class="srh_all_no">，全部的选中是统一的。-->
					<dl class="srh_list">
						<dd>特色：</dd>
						<dt>
							<ul id="features_ul">
								<c:forEach items="${info.features}" var="f">
									<c:choose>
										<c:when test="${f.featureId == 0}">
											<span class="srh_all" data-id="${f.featureId}">${f.feature}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${f.featureId}">${f.feature}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</dt>
					</dl>
					<dl class="srh_list">
						<dd>状态：</dd>
						<dt>
							<ul id="enterTime_ul">
								<c:forEach items="${info.enterTimes}" var="enterTime">
									<c:choose>
										<c:when test="${enterTime.statusId == 0}">
											<span class="srh_all" data-id="${enterTime.statusId}">${enterTime.status}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${enterTime.statusId}">${enterTime.status}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</dt>
					</dl>
					<dl class="srh_list">
						<dd>类型：</dd>
						<dt>
							<ul id="leaseType_ul">
								<c:forEach items="${info.leaseTypes}" var="lease">
									<c:choose>
										<c:when test="${lease.typeId == 0}">
											<span class="srh_all" data-id="${lease.typeId}">${lease.type}</span>
										</c:when>
										<c:otherwise>
											<li><span data-id="${lease.typeId}">${lease.type}</span></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</dt>
					</dl>
					<!--            <dl class="srh_list">
              <dd>产品：</dd>
              <dt>
                <ul id="type_ul">
                  <span class="srh_all srh_all_on">全部</span>
                  <li id="1"><span>晴朗系列</span></li>
                  <li id="2"><span>马卡龙系列</span></li>
                  <li id="3"><span>时光系列</span></li>
                </ul>
              </dt>
            </dl>-->
					<!--        <dl class="srh_list">
              <dd>室友：</dd>
              <dt>
                <ul id="leixing_ul">
                  <span class="srh_all srh_all_on">无所谓</span>
                  <li id="3"><span>全姑娘</span></li>
                  <li id="2"><span>全小伙</span></li>
                  <li id="1"><span>爱情公寓</span></li>
                </ul>
              </dt>
            </dl>-->
				</div>
			</div>
		</div>
		<div class="b_tit">
			<span class="fr">共<em id="allcount">673</em>个合适房源
			</span>
			<ul id="sort_ul">
				<li><a href="javascript:void(0);" title="推荐" rel="nofollow"
					data-id="0">推荐<i class="b_up"></i><i class="b_dn"></i></a></li>
				<li>|</li>
				<li><a href="javascript:void(0);" title="价格" rel="nofollow"
					data-id="1">价格<i class="b_up"></i><i class="b_dn"></i></a></li>
				<li>|</li>
				<li><a href="javascript:void(0);" title="面积" rel="nofollow"
					data-id="2">面积<i class="b_up"></i><i class="b_dn"></i></a></li>
			</ul>
		</div>
		<div id="list">
			<div class="b_list">
				<c:forEach items="${info.list}" var="apartment">
					<div class="list_box">
					<dl class="b_dl">
						<dd>
							<a href="info/${apartment.rooms[0].id}" title="${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间"
								target="_blank"><img class="readyload"
								src="<%=basePath%>/images/${apartment.pic2[0]}"
								width="450" height="276" style="display: inline;"></a>
							<!---->
						</dd>
						<dt>
							<!--对比和收藏为鼠标经过时显示，对比的图标选中样式为<i class="duibi_on">，收藏的图标选中样式为<i class="shoucang_on">-->
							<div class="b_list_tit">
								<span title="${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间"
									class="duibi"><i></i>加入对比</span><span
									 class="shoucang"
									style="cursor: pointer;"><i></i>加入收藏</span>
								<h3>
									<a href="info/${apartment.rooms[0].id}" title="${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间"
										target="_blank" rel="nofollow">${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间</a>
								</h3>
							</div>
							<div class="b_txt">
								<p>类型：${apartment.apartmenttype}&nbsp;&nbsp;&nbsp;&nbsp;评价：</p>
								<p>楼层：第${apartment.floor}层/共${apartment.totalfloor}层&nbsp;&nbsp;面积：${apartment.square}㎡&nbsp;&nbsp;朝向：${apartment.direction}</p>
								<p>
									地址：${apartment.location}${apartment.address}<a class="f_map" data-longitude="${apartment.longitude}" data-latitude="${apartment.latitude}"
										href="javascript:void(0);" title="导航"><i></i>导航</a>
								</p>
								<ul>
									<c:forEach items="${apartment.facilityEntity}" var="f">
									<li>${f.description}</li>
									</c:forEach>
								</ul>
								<div class="money">
									<em>${apartment.dayPrice}</em>元/天
								</div>
							</div>
							<div class="jieshao">
								<a href="info/${apartment.rooms[0].id}" title="${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间"
									target="_blank" rel="nofollow"> <i class="quotes"></i> <i
									class="quotes1"></i> <span
									title="${apartment.rooms[0].descriptionPersonal}">[房间特色]<br>${apartment.rooms[0].descriptionPersonal}
								</span></a>
							</div>
						</dt>
					</dl>
				</div>
				</c:forEach>
			
				
				<div class="page">
					<a class="up none" title="首页">&lt;&lt;</a><a class="up none"
						title="上一页">&lt;</a> <a class="page_on" href="javascript:void(0)"
						title="第1页">1</a> <a href="javascript:getAjaxpages(2);"
						title="第2页">2</a><a href="javascript:getAjaxpages(3);" title="第3页">3</a><a
						href="javascript:getAjaxpages(4);" title="第4页">4</a><a
						href="javascript:getAjaxpages(5);" title="第5页">5</a><a
						href="javascript:getAjaxpages(6);" title="第6页">6</a><a
						href="javascript:getAjaxpages(7);" title="第7页">7</a><a
						href="javascript:getAjaxpages(8);" title="第8页">8</a><a
						href="javascript:getAjaxpages(9);" title="第9页">9</a><a
						href="javascript:getAjaxpages(10);" title="第10页">10</a><a
						class="pgdn" title="下一页" href="javascript:getAjaxpages(2);">&gt;</a><a
						href="javascript:getAjaxpages(68);" title="最后一页">&gt;&gt;</a>
				</div>
			</div>
		</div>
	</article>
	<div id="color-box">
		
	</div>
	</my_body>
	<my_script> <script
		src="<%=basePath%>/dist/customer/js/list.js"></script> 
	</my_script>
</body>

</html>