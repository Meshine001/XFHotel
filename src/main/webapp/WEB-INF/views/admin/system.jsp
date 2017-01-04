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
		<div class="col-md-3">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">公寓特色列表</div>
					<ul class="card-action">
						<li><a class="btn " data-toggle="modal"
							data-target="#featuresModal"> 添加 </a></li>
					</ul>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="featuresModal" tabindex="-1"
					role="dialog" aria-labelledby="featuresModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="featuresModalLabel">添加特色</h4>
							</div>
							<div class="modal-body">
								<form role="form"
									action="<%=basePath%>/admin/apartment/features/add"
									method="post" id="features-form">
									<div class="form-group">
										<label for="">特色描述</label> <input name="description"
											type="text" class="form-control" placeholder="输入特色描述">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary features-submit">提交</button>
							</div>

						</div>
					</div>
				</div>
				<div class="card-body no-padding table-responsive">
					<table class="table card-table">
						<thead>
							<tr>
								<th class="right">内容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${features}" var="f">
								<tr>
									<td>${f.description}</td>
									<td><a class="features-delete"
										id="<%=basePath%>/admin/apartment/features/delete/${f.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- 设施 -->
		<div class="col-md-3">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">公寓设施列表</div>
					<ul class="card-action">
						<li><a class="btn " data-toggle="modal"
							data-target="#facilityModal"> 添加 </a></li>
					</ul>
				</div>
				<!-- Modal -->
				<div class="modal fade" id="facilityModal" tabindex="-1"
					role="dialog" aria-labelledby="facilityModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>
								<h4 class="modal-title" id="facilityModalLabel">添加设施</h4>
							</div>
							<div class="modal-body">
								<form role="form"
									action="<%=basePath%>/admin/apartment/facility/add"
									method="post" id="facility-form">
									<div class="form-group">
										<label for="">设施描述</label> <input name="description"
											type="text" class="form-control" placeholder="输入设施">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary facility-submit">提交</button>
							</div>

						</div>
					</div>
				</div>
				<div class="card-body no-padding table-responsive">
					<table class="table card-table">
						<thead>
							<tr>
								<th class="right">内容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${facilities}" var="f">
								<tr>
									<td>${f.description}</td>
									<td><a class="facility-delete"
										id="<%=basePath%>/admin/apartment/facility/delete/${f.id}">删除</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	</my_body>
	<my_script>
		<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/system-settings.js"></script>
	</my_script>
</body>
</html>