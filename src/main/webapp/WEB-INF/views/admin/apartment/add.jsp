<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>添加房源-青舍都市公寓-西安租房_西安合租</title>
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
					<form action="<%=request.getContextPath()%>/admin/apartment/add"
						method="POST" class="form form-horizontal form-add-apartment"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-md-3 control-label">区域</label>
							<div class="col-md-9">
								<div id="location">
									<input type="text" id="location_info" class="form-control"
										placeholder="" name="bd_wei_zhi" readonly="readonly">
									<input type="hidden" id="lng" class="form-control"
										placeholder="" name="jing_du"> <input type="hidden"
										id="lat" class="form-control" placeholder="" name="wei_du">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">位置</label>
							<div class="col-md-9">
								<select name="xa_wei_zhi" class="">
									<option value="城东">城东</option>
									<option value="城西">城西</option>
									<option value="城南">城南</option>
									<option value="城北">城北</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">街道</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="如：xxx路xxx号"
									name="jie_dao">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="如：xxx小区"
									name="xiao_qu">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写数字"
									name="lou_hao">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">单元号</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写数字"
									name="dan_yuan">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="number"
										class="form-control" placeholder="" name="lou_ceng"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="number"
										class="form-control" placeholder="" name="zong_lou_ceng">
									<span class="input-group-addon">层</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">门牌号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="请填写门牌号"
									name="men_pai">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">锁地址</label>
							<div class="col-md-9">
								<input type="text" class="form-control"
									placeholder="如：129.11.11.22" name="suo_di_zhi">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="cao_xiang" class="">
									<option value="南北">南北</option>
									<option value="东西">东西</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总面积</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="number" class="form-control" placeholder="请填写房源面积"
										name="mian_ji"><span class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="shi"> <span class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="ting"> <span class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="wei"> <span class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="input-group">
									<input type="number" class="form-control" placeholder=""
										name="yang_tai"> <span class="input-group-addon">阳台</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住几人</label>
							<div class="col-md-9">
								<input type="number" class="form-control" placeholder="请填写可住人数"
									name="reng_shu">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">床</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="对床的描述"
									name="chuang">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">户型图</label>
							<div class="col-md-9">
								<ul>
									<li><input type="hidden" name="pic1" id="pic1-1-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic1-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic1-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">综合描述</label>
							<div class="col-md-9">
								<textarea name="miao_su" rows="3" class="form-control"
									placeholder="对公寓的综合描述"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">特色</label>
							<div class="col-md-9">
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的特色" name="te_se" >
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的家居" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的卫浴" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的餐厨" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="可选的配套" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="周边" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<div class="informers">
								  <input type="text" class="form-control" placeholder="其他" name="te_se">
								  <input type="text" class="form-control-class" placeholder="" name="te_se_class" style="display:none">
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
								<ul>
									<li><input type="hidden" name="pic2" id="pic2-1-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic2-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-2-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic2-2-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-2"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-3-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic2-3-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-3"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-4-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic2-4-img"><span
										class="badge badge-info badge-icon upload-image" id="pic2-4"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic2" id="pic2-5-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic2-5-img"><span
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
									<li><input type="hidden" name="pic3" id="pic3-1-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic3-1-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-1"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic3" id="pic3-2-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic3-2-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-2"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
									<li><input type="hidden" name="pic3" id="pic3-3-input">
										<img alt="" src="" class="img-thumbnail" width="120px"
										height="80px" id="pic3-3-img"><span
										class="badge badge-info badge-icon upload-image" id="pic3-3"><i
											class="fa fa-edit" aria-hidden="true"></i><span>更换</span></span></li>
								</ul>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>
							<div class="col-md-9">
								<select name="lei_xing" id="apartment-type">
									<option value="酒店型">酒店型</option>
									<option value="休闲型">休闲型</option>
								</select>

							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div class="col-md-9">
								<div class="input-group">
									<input type="text" name=jia_ge class="price-day"> <span
										class="input-group-addon">元/天</span>
								</div>
							</div>
						</div>

						<br>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="button" class="btn btn-primary btn-submit">保存</button>
									<a type="button" class="btn btn-default" href="<%=basePath%>/admin/apartment">取消</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<script type="text/javascript">
				$('.btn-submit').click(function(){
					$('.form-add-apartment').submit();
				});
			</script>
		</div>
		<div class="col-md-6">
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
		type="text/javascript"></script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/add-apartment.js"></script> 
			
	</my_script>
</body>
</html>