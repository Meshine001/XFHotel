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
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>公告列表</h3>
					</div>
					<ul class="card-action">
						<a  class="btn " href="<%=basePath%>/admin/blog/create">添加</a>
					</ul>
				</div>
				<table class="table" id="list">
					<thead>
						<tr>
							<th width="20%">发布日期</th>
							<th width="50%">标题</th>
							<th width="15%">状态</th>
							<th width="15%"></th>
						</tr>
					</thead>
					<tbody id="t_body">
					</tbody>
				</table>
				<ul id="pagecontroller" class="pagination">
				</ul>
			</div>
		</div>
	</div>
	</my_body>
	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/list-blog.js"></script> </my_script>
</body>