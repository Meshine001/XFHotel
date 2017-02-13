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
</head>
<body>
	<my_body>
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>公寓列表</h3>
					</div>
					<ul class="card-action">
						<a class="btn " href="<%=basePath%>/admin/apartment/add">添加</a>
					</ul>
				</div>
				<table class="table">
					<thead>
						<tr>
							<th>编号</th>
							<th>类型</th>
							<th>状态</th>
							<th>地址</th>
							<th>位置</th>
							<th>小区</th>
							<th>楼号</th>
							<th>楼层</th>
							<th>朝向</th>
							<th>面积</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${apartments}" var="apartment">
							<tr>
								<td>${apartment.id}</td>
								<td>${apartment.apartmenttype}</td>
								<td>${apartment.rooms[0].status}</td>
								<td>${apartment.location}</td>
								<td>${apartment.address}</td>
								<td>${apartment.community}</td>
								<td>${apartment.num_building}</td>
								<td>${apartment.floor}/${apartment.totalfloor}</td>
								<td>${apartment.direction}</td>
								<td>${apartment.square}</td>
								<th><a
									href="<%=basePath %>/admin/apartment/update/${apartment.id}">编辑详情</a>&nbsp;&nbsp;<a>删除</a></th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</my_body>
</body>
</html>