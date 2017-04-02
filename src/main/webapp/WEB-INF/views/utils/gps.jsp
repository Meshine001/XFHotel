<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#map {width: 80%;height: 100%;}
		#search{float:left;width:20%;height:100%;}
		#r-result,#r-result table{width:100%;height:95%;font-size:12px;overflow:auto}
		#search_type{width:100%,height:5%;float:top;font-size:20px;}
	</style>
<title>title-青舍都市公寓-西安租房_西安合租</title>

<meta charset="utf-8">
</head>
<body>
	<my_body>
	<div id="search">
		<div id="search_type">
			<a class="on" href="#void();" onclick="walk();">步行</a>
			<a class="btn" href="#void();" onclick="bus();">公交</a>
			<a class="btn" href="#void();" onclick="drive();">驾车</a>
		</div>
		<div id="r-result"></div>
	</div>
	<div id="map"></div>
	</my_body>
	<my_script>
		<script src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr&s=1" type="text/javascript"></script>
	
		<script type="text/javascript">
			var map = new BMap.Map("map");
			var geolocation = new BMap.Geolocation();
			var point = new BMap.Point(116.331398, 39.897445);
			var p1 = new BMap.Point(116.301934, 39.977552);
			var p2 = new BMap.Point(109.208317, 34.310408);
			map.centerAndZoom(point, 16);
			map.enableScrollWheelZoom(true);
			var wr = new BMap.WalkingRoute(map, {
				renderOptions : {
					map : map,
					panel: "r-result"
				}
			});
			var br = new BMap.TransitRoute(map, {
				renderOptions : {
					map : map,
					panel: "r-result"
				}
			});
			var dr = new BMap.DrivingRoute(map, {
				renderOptions : {
					map : map,
					panel: "r-result"
				}
			});
			geolocation.getCurrentPosition(function(r) {
				if (this.getStatus() == BMAP_STATUS_SUCCESS) {
					map.panTo(r.point);
					p1 = r.point;
					clear();
					walk();
				} else {
					alert("定位失败！");
				}
			}, {
				enableHighAccuracy : true
			});
			function walk(){
				clear();
				wr.search(p1, p2);
			};
			function bus(){
				clear();
				br.search(p1, p2);
			};
			
			function drive(){
				clear();
				dr.search(p1, p2);
			};
			function clear(){
				wr.clearResults();
				br.clearResults();
				dr.clearResults();
			}
		</script>
		
	</my_script>
</body>
</html>

