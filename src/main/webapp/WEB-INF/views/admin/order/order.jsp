<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>订单管理-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<my_body>
	<!-- 订单详情 -->
	<div class="masking" style="display:none"></div>
	<div class="orderDetail">
		<div class="detailTitle">订单详情<span class="close">X</span></div>
		<div class="detailWraper">
			<table class="zfhouse">
			
				<!-- 
				<tr>
					<td>查看每日房价</td>
					<td style="clear:both;width:100%">
						<ul id="everyDateprice">
							<li><span>2017-06-20</span><span>￥200</span></li>
							<li><span>2017-06-20</span><span>￥200</span></li>
							<li><span>2017-06-20</span><span>￥200</span></li>
							<li><span>2017-06-20</span><span>￥200</span></li>
						</ul>
					</td>
				</tr>
				 -->
				
			
			</table>
			
			<table class="clean">
				<tr>
					<td style="width:100%;border-bottom:1px solid #ccc;font-size: 18px;">保洁订单</td>
				</tr>
				<tr class="zanwu" style="display:none;color:#dd4a4a"><td>暂时没有保洁服务订单</td></tr>
			</table>
			
		</div>	
	</div>
	
	
	
	
	
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>订单列表</h3>
					</div>
				</div>
				<div id="topscroll" style="width:100%;height:auto;overflow-x:auto">
				<table class="table table-bordered">

					<thead>
						<tr>
							<th>订单号</th>
							<th>下单时间</th>
							<th>订单状态</th>
							<th>下单人</th>
							<th>联系手机</th>
							<th>房间</th>
							<th>住房时间</th>
							<th>总天数</th>
							<th>单价</th>
							<th>总价</th>
							<th>优惠</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="list">
						
					</tbody>
				</table>
				
				
				<ul id="pagecontroller" class="pagination">
					
				</ul>
				
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("table th,table td").css('min-width','120px');
		})
	</script>
	</my_body>
	<my_script>
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/orderList.js"></script> 
	
	</my_script>
</body>
</html>