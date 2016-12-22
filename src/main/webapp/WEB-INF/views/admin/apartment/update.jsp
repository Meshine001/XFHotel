<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<my_body>
	<form action="<%=request.getContextPath()%>/admin/apartment/update"
		method="POST">
		<div>
			<input type="hidden" id="apartmentid" name="apartmentid" />
			<div id="location">
				<input type="text" id="location_info" name="location"
					readonly="readonly"> <input type="hidden" id="lng"
					name="lng"> <input type="hidden" id="lat" name="lat">
			</div>
			<div>
				地址：<input type="text" id="address" name="address" />
			</div>
			<div id="map" style="width: 500px; height: 500px"></div>
			<div>
				小区名称：<input type="text" id="community" name="community" />
			</div>
			<div>
				楼号：<input type="text" id="num_building" name="num_building" />
			</div>
			<div>
				楼层：第<input type="text" id="floor" name="floor" />层&nbsp;共<input
					type="text" id="totalfloor" name="totalfloor" />层
			</div>
			<div>
				朝向： <select id="direction" name="direction">
					<option value="南北">南北</option>
					<option value="东西">东西</option>
				</select>
			</div>
			<div>
				面积：<input type="text" id="square" name="square" /> 人数：<input
					type="text" id="capacity" name="capacity" />
			</div>
			<div>
				房屋户型：<input type="text" id="bedroom" name="bedroom" />室<input
					type="text" id="livingroom" name="livingroom" />厅<input
					type="text" id="bathroom" name="bathroom" />卫<input type="text"
					id="balcony" name="balcony" />阳台
			</div>
			<div>
				特色：
				<c:forEach items="${l_feature}" var="feature" varStatus="p">
					<input type="checkbox" id="feature${feature.id}" name="feature"
						value="${feature.id}"> ${feature.description }
				</c:forEach>
			</div>
			<div>
				备注：<input type="text" id="description" name="description">
			</div>
			<div>布局图：</div>
		</div>
		<div>
			设施：
			<c:forEach items="${l_facility}" var="facility" varStatus="p">
				<input type="checkbox" id="facility${facility.id}" name="facility"
					value="${facility.id}"> ${facility.description }
		</c:forEach>
		</div>
		<div>
			房间设置： <input type="hidden" name="num_room" value="0">
			<div id="rooms"></div>
		</div>
		<div>
			出租类型：<select id="apartmenttype" name="apartmenttype"
				onchange="type1change(this.options[this.options.selectedIndex].value)">
				<option value="-1" selected="selected">请选择</option>

			</select> <select id="type" name="type"
				onchange="type2change(this.options[this.options.selectedIndex].value)">
				<option value="0">请选择</option>
				<option value="1">单租型</option>
				<option value="2">合租型</option>
				<option value="3">混合型</option>
			</select>
		</div>
		<div id="lease" style="display: none;"></div>
		<button type="submit">提交</button>
	</form>
	<script type="text/javascript">
		document.getElementById("lease").style.display = "none";
		function type1change(op) {
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : 'json',
				data : {'type' : 0,'id' : op},
				url :"<%=request.getContextPath()%>/admin/apartment/getleasetype",//请求的action路径
				error : function() {//请求失败处理函数
					alert("获取数据失败！");
				},
				success : function(data) {
					var leasetypes = data.leasetype;
					var leasetypeids = data.leasetypeid;
					var htmltext = "";
					for ( var i in leasetypes) {
						var ids = leasetypeids[i];
						var types = leasetypes[i];
						htmltext = htmltext + "<div>";
						htmltext = htmltext
								+ types
								+ "<input type='hidden' id='leasetypeid"+ids+"' name='leasetypeid'/>"
								+ "<input type='text' id='leasetype"+ids+"' name='leasetype' />";
						htmltext = htmltext + "</div>";
					}
					$("#lease").html(htmltext);
				}
			});
		}
		function type2change(op) {
			if (op == 1)
				document.getElementById("lease").style.display = "";
			if (op == 2)
				document.getElementById("lease").style.display = "none";
			if (op == 3)
				document.getElementById("lease").style.display = "";
		}
	</script>
	<script
		src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1"
		type="text/javascript"></script> <script type="text/javascript">
		var apartmentid = ${apartmentid};
		$(document).ready(function(){
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : 'json',
				data : {'apartmentid':apartmentid},
				url : "<%=request.getContextPath()%>/admin/apartment/getapartment",//请求的action路径
				error : function() {//请求失败处理函数
					alert("获取数据失败！");
				},
				success : function(data) {
					var map = new BMap.Map("map");
					var geolocation = new BMap.Geolocation();
					var point = new BMap.Point(data.longitude,data.latitude);
					var marker = new BMap.Marker(point);
					marker.setPosition(point);
					map.addOverlay(marker);
					map.centerAndZoom(point,12);
					map.enableScrollWheelZoom(true);
					map.addEventListener('ondragging', function(){
						marker.setPosition(map.getCenter());
        			});
					map.addEventListener("dragend", function showInfo(){
						var cp = map.getCenter();
						getaddress(cp.lng,cp.lat);
					});
					function getaddress(lng,lat){
						var pt = new BMap.Point(lng,lat);
						var geoc = new BMap.Geocoder();  
						geoc.getLocation(pt, function(rs){
							var addComp = rs.addressComponents;
							$('#location_info').val(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street);
							$('#lng').val(lng);
							$('#lat').val(lat);
							
						});        
					}
					$('#apartmentid').val(data.id);
					$('#address').val(data.address);
					$('#community').val(data.community);
					$('#num_building').val(data.num_building);
					$('#floor').val(data.floor);
					$('#totalfloor').val(data.totalfloor);
					$('#square').val(data.square);
					$('#capacity').val(data.capacity);
					$('#bedroom').val(data.bedroom);
					$('#livingroom').val(data.livingroom);
					$('#bathroom').val(data.bathroom);
					$('#balcony').val(data.balcony);
					$('#description').val(data.description);
					$.each(data.facilities,function(index,value){
						$('#facility'+value.id).attr('checked',true);
					});
					$.each(data.features,function(index,value){
						$('#feature'+value.id).attr('checked',true);
					});
					$('#apartmenttype').val(data.apartmentType);
					$('#type').val(data.type);
					$('#location_info').val(data.location);
					$('#lng').val(data.longitude);
					$('#lat').val(data.latitude);
					type1change(data.apartmentType);
					type2change(data.type);
					$.each(data.prices,function(index,value){
						$('#leasetypeid'+value.leasetypeid).val(value.leasetypeid);
						$('#leasetype'+value.leasetypeid).val(value.price);
					});
					var roomshtml = "";
					$.each(data.rooms,function(index,value){
						var url = "./editroom";
						var form = $('<form></form>').attr('action',url).attr('method','post');
						var ul = $('<ul></ul>');
						var li =  $("<li>"+ value.description
							+ "&nbsp" + value.type
							+ "&nbsp" + value.square
							+ "&nbsp" + value.direction
							+ "&nbsp" + value.capacity
							+ "&nbsp" + value.square 
							+ "</li>");
						ul.append(li);
						form.append(ul);
						var submit = $('<input></input>').attr('type','submit').attr('value','编辑');
						var inrid = $('<input></input>').attr('type','hidden').attr('value',value.id).attr('name','roomid');
						var inaid = $('<input></input>').attr('type','hidden').attr('value',data.id).attr('name','apartmentid');
						form.append(submit).append(inrid).append(inaid);
						$("#rooms").append(form);
					});
				}
			});
		});
	</script>
	</my_body>
</body>
</html>