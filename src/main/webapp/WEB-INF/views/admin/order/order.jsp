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
							
			</table>
			
			<div class="clean">
				<table>
					<tr><td style="width: 100%;font-size: 18px;background:rgb(232, 229, 229)">保洁订单</td></tr>
				</table>
							
				<ul>
					<!-- 
					<li id="'+data.content[i].id+'">
						<table>
							<tr>
								<td style="width:100%">下单时间：<span>'+new Date( data.content[i].time ).toLocaleString()+'</span></td>
							</tr>
							<tr>
								<td class="fl50">订单状态：<span>'+stuse+'</span></td>
							    <td class="fl50">打扫时间：<span>'+data.content[i].cleanTime+'</span></td>
							</tr>
							<tr>
								<td style="width:100%">服务内容：<span>'+data.content[i].content+'</span></td>
							</tr>
						</table>
					</li>
					 -->
				</ul>
			</div>
			
			<div class="usercar">
				<table>
					<tr><td style="width: 100%;font-size: 18px;background:rgb(232, 229, 229)">叫车订单</td></tr>
				</table>
				<ul>

				</ul>
				
			</div>
			
			<div class="getFault">
				<table>
					<tr><td style="width: 100%;font-size: 18px;background:rgb(232, 229, 229)">维修订单</td></tr>
				</table>
							
				<ul>
				
				</ul>
			</div>
			
			<div class="addsheshi">
				<table>
					<tr><td style="width: 100%;font-size: 18px;background:rgb(232, 229, 229)">添加设施订单</td></tr>
				</table>
							
				<ul>
					
				</ul>
			</div>
			
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