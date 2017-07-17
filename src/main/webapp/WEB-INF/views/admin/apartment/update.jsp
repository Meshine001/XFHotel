<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>编辑房源-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<my_body>
	<div class="row">
		<div class="col-md-7">
			<div class="card">
				<div class="card-header">修改房源</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/admin/apartment/update/${apartment.id}"
						method="POST" class="form form-horizontal form-add-apartment"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-md-3 control-label">区域</label>
							<div class="col-md-9">
								<div id="location">
									<input type="text" id="location_info" class="form-control"
										placeholder="" name="bd_wei_zhi" readonly="readonly"
										value="${apartment.position.bd_wei_zhi}"> <input
										type="hidden" id="lng" class="form-control" placeholder=""
										name="jing_du" value="${apartment.position.jing_du}"> <input
										type="hidden" id="lat" class="form-control" placeholder=""
										name="wei_du" value="${apartment.position.wei_du}">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">位置</label>
							<div class="col-md-9">
								<select name="xa_wei_zhi" class="form-control xa_wei_zhi"
									data-value="${apartment.position.xa_wei_zhi}">
									<option value="城东">城东</option>
									<option value="城西">城西</option>
									<option value="城南">城南</option>
									<option value="城北">城北</option>
								</select>
								<script type="text/javascript">
									$.each($('.xa_wei_zhi option'), function(
											index, item) {
										console.log(item);
										if ($(item).attr('value') == $(
												'.xa_wei_zhi').attr(
												'data-value')) {
											$(item)
													.attr('selected',
															'selected');
										}
									});
								</script>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">街道</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="如：xxx路xxx号"
									name="jie_dao" value="${apartment.position.jie_dao}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="如：xxx小区"
									name="xiao_qu" value="${apartment.position.xiao_qu}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写数字"
									name="lou_hao" value="${apartment.position.lou_hao}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">单元号</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写数字"
									name="dan_yuan" value="${apartment.position.dan_yuan}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="number"
										class="form-control" placeholder="" name="lou_ceng"
										value="${apartment.position.lou_ceng}"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="number"
										class="form-control" placeholder="" name="zong_lou_ceng"
										value="${apartment.position.zong_lou_ceng}"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">门牌号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="请填写门牌号"
									name="men_pai" value="${apartment.position.men_pai}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">锁地址</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									placeholder="如：129.11.11.22" name="suo_di_zhi"
									value="${apartment.basic_info.suo_di_zhi}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="cao_xiang" class="form-control cao_xiang"
									data-value="${apartment.basic_info.cao_xiang}">
									<option value="南北">南北</option>
									<option value="东西">东西</option>
								</select>
								<script type="text/javascript">
									$.each($('.cao_xiang option'), function(
											index, item) {
										if ($(item).attr('value') == $(
												'.cao_xiang')
												.attr('data-value')) {
											$(item)
													.attr('selected',
															'selected');
										}
									});
								</script>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总面积</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="number" class="form-control" placeholder="请填写房源面积"
										name="mian_ji" value="${apartment.basic_info.mian_ji}"><span
										class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="shi" value="${apartment.basic_info.shi}"> <span
										class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="ting" value="${apartment.basic_info.ting}"> <span
										class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="wei" value="${apartment.basic_info.wei}"> <span
										class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="yang_tai" value="${apartment.basic_info.yang_tai}">
									<span class="input-group-addon">阳台</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住几人</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写可住人数"
									name="reng_shu" value="${apartment.basic_info.reng_shu}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">床</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="对床的描述"
									name="chuang" value="${apartment.basic_info.chuang}">
							</div>
						</div>
						<div class="form-group" style="display:none">
							<label class="col-md-3 control-label">户型图</label>
							<div class="col-md-9">
								<ul>
									<li><input type="hidden" name="pic1" id="pic1-1-input" value="${apartment.hu_xing_tu}">
										<img alt="" src="<%=basePath%>/images/${apartment.hu_xing_tu}"
										class="img-thumbnail" width="120px" height="80px"
										id="pic1-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic1-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">综合描述</label>
							<div class="col-md-9">
								<textarea name="miao_su" rows="3" class="form-control"
									placeholder="对公寓的综合描述">${apartment.description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">特色</label>
							<div class="col-md-9">
								<!--<textarea name="te_se" class="form-control"
									placeholder="如：智能门锁，自动售货机，遥控窗帘等；用中文输入法“，”分隔">
									${te_se}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的特色" name="te_se" value="${te_se}">
								  <input type="text" class="form-control-class" placeholder=""  value="${te_se_class}" name="te_se_class" style="display:none">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-smartlock">智能门锁<span></span></li>
								  			<li data-icon="icon-projector">投影电视<span></span></li>
								  			<li data-icon="icon-Fitness">健身器材<span></span></li>
								  			<li data-icon="icon-mahjong">麻将桌<span></span></li>
								  			<li data-icon="icon-tea">茶桌<span></span></li>
								  			
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">家居</label>
							<div class="col-md-9">
								<!--<textarea name="jia_ju" class="form-control"
									placeholder="如：无线网络，电视，冰箱等；用中文输入法“，”分隔">
								${jia_ju}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的家居" name="jia_ju" value="${jia_ju}">
								  <input type="text" class="form-control-class" placeholder="" name="jia_ju_class" style="display:none" value="${jia_ju_class}">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-wifi">无线网<span></span></li>
								  			<li data-icon="icon-tv">电视<span></span></li>
								  			<li data-icon="icon-icebox">冰箱<span></span></li>
								  			<li data-icon="icon-airconditioner">空调<span></span></li>
								  			<li data-icon="icon-washingmachine">洗衣机<span></span></li>
								  			<li data-icon="icon-hatwort">热水器<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">卫浴</label>
							<div class="col-md-9">
								<!--<textarea name="wei_yu" class="form-control"
									placeholder="如：淋浴，毛巾，浴巾等；用中文输入法“，”分隔">
									${wei_yu}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的卫浴" name="wei_yu" value="${wei_yu}">
								  <input type="text" class="form-control-class" placeholder="" name="wei_yu_class" style="display:none" value="${wei_yu_class}">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-hairandbodylotion">淋浴<span></span></li>
								  			<li data-icon="icon-towel">毛巾<span></span></li>
								  			<li data-icon="icon-slipper">拖鞋<span></span></li>
								  			<li data-icon="icon-liquidshampoo">洗发露<span></span></li>
								  			<li data-icon="icon-toiletthings">牙具<span></span></li>
								  			<li data-icon="icon-tissues">卫生纸<span></span></li>
								  			<li data-icon="icon-hairdryer">电吹风<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">餐厨</label>
							<div class="col-md-9">
								<!--<textarea name="can_chu" class="form-control"
									placeholder="如：燃气灶，烹饪锅具，刀具案板等；用中文输入法“，”分隔">
									${can_chu}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的餐厨" name="can_chu" value="${can_chu}">
								  <input type="text" class="form-control-class" placeholder="" name="can_chu_class" style="display:none" value="${can_chu_class}">
								  <div class="checkBox">
								  		<ul>								  
								  			<li data-icon="icon-cookingpots">烹饪锅具<span></span></li>
								  			<li data-icon="icon-atableware">餐具<span></span></li>
								  			<li data-icon="icon-electriccooker">电饭煲<span></span></li>
								  			<li data-icon="icon-wbl">微波炉<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">配套</label>
							<div class="col-md-9">
								<!--<textarea name="pei_tao" class="form-control"
									placeholder="如：楼宇门禁，小区保安，停车位等；用中文输入法“，”分隔">
									${pei_tao}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的配套" name="pei_tao" value="${pei_tao}">
								  <input type="text" class="form-control-class" placeholder="" name="pei_tao_class" style="display:none" value="${pei_tao_class}">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-accesscontrol">楼宇门禁<span></span></li>
								  			<li data-icon="icon-publicsecurity">小区保安<span></span></li>
								  			<li data-icon="icon-elevator">电梯<span></span></li>
								  			<li data-icon="icon-heating">市政暖气<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">周边</label>
							<div class="col-md-9">
								<!--<textarea name="zou_bian" class="form-control"
									placeholder="如：地铁，公交站，餐馆等；用中文输入法“，”分隔">
									${zou_bian}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="周边" name="zhou_bian" value="${zhou_bian}">
								  <input type="text" class="form-control-class" placeholder="" name="zhou_bian_class" style="display:none" value="${zhou_bian_class}">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-metro">地铁<span></span></li>
								  			<li data-icon="icon-BUS">公交站<span></span></li>
								  			<li data-icon="icon-restaurant">餐馆<span></span></li>
								  			<li data-icon="icon-market">商场<span></span></li>
								  			<li data-icon="icon-bank">银行<span></span></li>
								  			<li data-icon="icon-svs">便利店<span></span></li>
								  			<li data-icon="icon-infirmary">医院<span></span></li>
								  			<li data-icon="icon-drugstore">药店<span></span></li>
								  			<li data-icon="icon-cinema">电影院<span></span></li>
								  			<li data-icon="icon-KTV">KTV<span></span></li>
								  			<li data-icon="icon-recreation">娱乐场所<span></span></li>
								  			<li data-icon="icon-sightspot">景点<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">其他</label>
							<div class="col-md-9">
								<!--<textarea name="qi_ta" class="form-control"
									placeholder="如：可做饭，可吸烟，可聚会等；用中文输入法“，”分隔">
									${qi_ta}
								</textarea>-->
								<div class="informers">
								  <input type="text" class="form-control" placeholder="其他" name="qi_ta" value="${qi_ta}">
								  <input type="text" class="form-control-class" placeholder="" name="qi_ta_class" style="display:none" value="${qi_ta_class}">
								  <div class="checkBox">
								  		<ul>
								  			<li data-icon="icon-Cooking">可做饭<span></span></li>
								  			<li data-icon="icon-smoke">可吸烟<span></span></li>
								  			<li data-icon="icon-dinetogether">可聚会<span></span></li>
								  		</ul>
								  </div>
								  <p><a href="javascript:;" class="confirm">确定</a></p>
								</div>  
								
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">公寓展示</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<ul style="padding:0;">
									<c:forEach items="${apartment.fang_jian_tu}" var="img" varStatus="p">
										<li><input type="hidden" name="pic2" id="pic2-${p.index}-input" value="${img}">
											<img alt="" src="<%=basePath%>/images/${img}" class="img-thumbnail" width="120px"
											height="80px" id="pic2-${p.index}-img"><span
											class="badge badge-info badge-icon upload-image" id="pic2-${p.index}"><i
												class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区图片</label>
							<!-- Button trigger modal -->
							<div class="col-md-9">
								<ul style="padding:0;">
									<c:forEach items="${apartment.xiao_qu_tu}" var="img" varStatus="p">
										<li><input type="hidden" value="${img}" name="pic3" id="pic3-${p.index}-input">
										<img alt="" src="<%=basePath%>/images/${img}" class="img-thumbnail" width="120px"
										height="80px" id="pic3-${p.index}-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-${p.index}"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									</c:forEach>
								</ul>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>
							<div class="col-md-9">
								<select name="lei_xing" id="apartment-type" class="lei_xing form-control" data-value = "${apartment.basic_info.lei_xing}">
									<option value="酒店型">酒店型</option>
									<option value="休闲型">休闲型</option>
								</select>
								<script type="text/javascript">
									$.each($('.lei_xing option'), function(
											index, item) {
										if ($(item).attr('value') == $(
												'.lei_xing')
												.attr('data-value')) {
											$(item)
													.attr('selected',
															'selected');
										}
									});
								</script>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" name=jia_ge class="price-day form-control" value="${apartment.basic_info.jia_ge}"> <span
										class="input-group-addon">元/天</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">VR房屋实景地址</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									placeholder="VR房屋实景链接地址" name="suo_di_zhi"
									value="${apartment.basic_info.suo_di_zhi}">
							</div>
						</div>
						
						<br>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" class="btn btn-primary btn-submit">保存</button>
									<a type="button" class="btn btn-default" href="<%=basePath%>/admin/apartment">取消</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<script type="text/javascript">
				$('.btn-submit').click(function() {
					$('.form-add-apartment').submit();
				});
			</script>
		</div>
		<div class="col-md-5">
			<div id="map" style="width: 500px; height: 500px"></div>
		</div>
		<!-- 上传图片 -->
		<form action="" id="upload-image-form">
			<input type="file" id="upload-image-input" name="file"
				style="display: none;">
		</form>

	</div>
	</my_body>

	<my_script> <script
		src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1"
		type="text/javascript"></script>  <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/update-apartment.js"></script></my_script>
</body>
</html>