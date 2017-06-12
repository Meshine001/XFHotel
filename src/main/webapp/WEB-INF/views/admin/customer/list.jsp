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
						<h3>用户列表</h3>
					</div>
				</div>
				<div style="overflow: scroll;width:100%;height:auto">
				<table class="table">
				<thead>
						<th>
							注册时间
								<select id="longtime">
									<option tid="3">全部</option>
									<option tid="0">新用户注册</option>
									<option tid="1">注册满一年</option>
								</select>
						</th>
						<th>
							消费金额筛选
								<select id="monetary">
									<option value="0">全部</option>
									<option value="200">满200</option>
									<option value="400">满400</option>
									<option value="600">满600</option>
								</select>
						</th>
						<th> 性别
								<select id="sex">
								    <option>全部</option>
									<option>男</option>
									<option>女</option>
								</select>       
						</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
					</thead>
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
				</div>
				<ul id="pagecontroller" class="pagination">
				</ul>
			</div>
		</div>
	</div>
	</my_body>
	<my_script>
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/list-customer.js"></script> 
	
	</my_script>
		
</body>
</html>