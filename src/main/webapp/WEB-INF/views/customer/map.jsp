<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
<title>Hello, World</title>  
<title>Insert title here</title>
<style type="text/css">  
</style> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=10NGT8xy035ui6vS5jxirNoGDb0nOsmr">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
</head>
<body>
	<div id="container" style="width: 500px;height: 500px"></div>
	<script type="text/javascript">
		var map = new BMap.Map("container"); // 创建地图实例  
		var point = new BMap.Point(116.404, 39.915); // 创建点坐标  
		map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
	</script>
</body>
</html>