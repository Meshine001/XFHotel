<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>智能公寓平台-西安租房_西安合租</title>
<meta charset="utf-8">
<link rel='stylesheet'
	href='<%=basePath%>/dist/commons/fullcalendar/fullcalendar.css' />
<script src='<%=basePath%>/dist/commons/fullcalendar/lib/jquery.min.js'></script>
<script src='<%=basePath%>/dist/commons/fullcalendar/lib/moment.min.js'></script>
<script src='<%=basePath%>/dist/commons/fullcalendar/fullcalendar.js'></script>
<script src='<%=basePath%>/dist/commons/fullcalendar/locale/zh-cn.js'></script>
<style type="text/css">
table {
	text-align: center;
}
</style>
</head>
<body>
	<div id="calendar"></div>
	<script type="text/javascript">
		$(document).ready(function() {

			$('#calendar').fullCalendar({
				header : {
					left : 'prev,next today ',
					center : 'title',
					right : ''
				},
				eventTextColor:'black',
				height : 650,
				eventSources : [

				// your event source
				{
					events : [ // put the array in the `events` property
					{
						title : '￥2900',
						start : '2017-01-01',
					}, {
						title : '￥2900',
						start : '2017-01-03',
					}, {
						title : '￥2900',
						start : '2017-01-04',
					}, {
						title : '￥2900',
						start : '2017-01-05',
					},

					],
					
				}

				// any other event sources...

				]
			});

		});
	</script>
</body>
</html>