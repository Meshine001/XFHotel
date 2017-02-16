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
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>公寓${apartment.id}-${apartment.community}-${apartment.num_building}-${apartment.floor}/${apartment.totalfloor}-价格表</h3>
					</div>
				</div>
				<div class="card-body">
					<form action="" class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">日常价</label>
							<div class="col-md-3">
								<input type="text" class="form-control" placeholder=""
									name="oldPrice" value="${apartment.dayPrice}">
							</div>
						</div>
						<c:forEach items="${spPrices}" var="sp">
							<div class="form-group">
								<label class="col-md-3 control-label">${sp.date}</label>
								<div class="col-md-3">
									<input type="text" class="form-control" placeholder=""
										name="oldPrice" value="${sp.price}">
								</div>
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>特殊价格设置</h3>
					</div>
				</div>
				<div class="card-body">
					<form action="<%=basePath%>/admin/apartment/price/set"
						class="form form-horizontal" method="post">
						<input type="hidden" name="apartmentId" value="${apartment.id}">
						<div class="form-group">
							<label class="col-md-3 control-label">选择日期</label>
							<div class="col-md-3">
								<input type="date" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">当日价格</label>
							<div class="col-md-3">
								<input type="text" class="form-control" placeholder=""
									name="price" value="">
							</div>
						</div>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</my_body>
</body>
</html>