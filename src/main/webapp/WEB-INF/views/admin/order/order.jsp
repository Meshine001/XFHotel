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
					<tr><td style="width: 100%;font-size: 18px;">保洁订单</td></tr>
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
					<tr><td style="width: 100%;font-size: 18px;">叫车订单</td></tr>
				</table>
				<ul>

				</ul>
				
			</div>
			
			<div class="getFault">
				<table>
					<tr><td style="width: 100%;font-size: 18px;">维修订单</td></tr>
				</table>
							
				<ul>
				
				</ul>
			</div>
			
			<div class="addsheshi">
				<table>
					<tr><td style="width: 100%;font-size: 18px;">添加设施订单</td></tr>
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
					<div class="card-title navs">
						<a class="active">青舍订单统计<i></i></a>
						<a>第三方订单统计<i></i></a>
					</div>
				</div>
				<div id="topscroll" style="width:100%;height:auto;overflow-x:auto">
					<table class="table table-bordered">
						<thead id="list-nav">
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
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="list">
							
						</tbody>
					</table>
					<ul id="pagecontroller" class="pagination">
						
					</ul>
				</div>
	<!-- 外来订单列表begin -->			
				<div id="otherOrderlist" style="width:100%;height:auto;overflow-x:auto;display:none">
					<table class="table table-bordered">
						<thead id="otherlist-nav">
							<tr>
								<th>订单来源/平台</th>
								<th>总收入/元</th>
								<th>房间/小区-门牌号</th>
								<th>入住时间</th>
								<th>离开时间</th>
								<th>姓名</th>
								<th>电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="otherlist">
							
						</tbody>
					</table>
					<ul id="otherpagecontroller" class="pagination" style="display:none">
						<li><a>上一页</a></li>
						<li><a>下一页</a></li>
						<li><span></span></li>
					</ul>
				</div>
	<!-- 外来订单列表end -->					
			</div>
		</div>
	</div>

	</my_body>
	<my_script>
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/orderList.js"></script> 
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/otherOrder.js"></script> 
	</my_script>
</body>
</html>