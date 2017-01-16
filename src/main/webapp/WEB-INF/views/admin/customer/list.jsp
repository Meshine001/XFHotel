<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<my_body>
	<h3>
		用户列表
	</h3>
	<table class="table">
		<thead>
			<th>序号</th>
			<th>等级</th>
			<th>电话</th>
			<th>注册时间</th>
			<th>消费总额</th>
			<th>消费次数</th>
			<th>状态</th>
			<th></th>
		</thead>
		<tbody id="list">
		</tbody>
	</table>
	<ul id="pagecontroller"  class="pagination">
	</ul>
	</my_body>
	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/list-customer.js"></script> </my_script>
</body>
</html>