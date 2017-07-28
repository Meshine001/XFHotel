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
	
	<div id="myalerts">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				 <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title house-title">添加设施<i></i></h4>
			  </div>
			  <div class="modal-body">
				 <div  id="mytable"> 
				 	<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#free" aria-controls="free" role="tab" data-toggle="tab">免费设施添加</a></li>
						<li role="presentation"><a href="#etc" aria-controls="etc" role="tab" data-toggle="tab">收费设施添加</a></li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="free">
							<div class="row feature">
								<div class="col-md-6">
                        			<div class="input-group">
									  <span class="input-group-addon">名称</span>
									  <input type="text" class="form-control" placeholder="免费设施名称" aria-describedby="basic-addon1">
									</div>
				 				</div>
							</div>
							<div>
								<button type="button" class="btn btn-success affirm">OK</button>
							</div>
					    </div>
					    <div role="tabpanel" class="tab-pane" id="etc">
							<div class="row feature">
									<div class="col-md-6">
								 		<div class="input-group">
										  <span class="input-group-addon">名称</span>
										  <input type="text" class="form-control" placeholder="收费设施名称">
										</div>
								 	</div>
								 	<div class="col-md-6">
								 		<div class="input-group">
								 		  <span class="input-group-addon">价格</span>
										  <input type="text" class="form-control" placeholder="价格">
										</div>
								 	</div>	
							</div>
							<div>
								<button type="button" class="btn btn-success affirm">OK</button>
							</div>
					    </div>
					</div>
				 </div>
			  </div>
			  
			</div>
		  </div>
	</div>
	
	
	
	
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>添加设施</h3>
					</div>
					<ul class="card-action" style="position:relative;top:14px;">
						<li><a class="btn btn-success" id="addfac">添加</a>
						</li>
					</ul>
				</div>
				
			<div class="row">
				<div class="col-md-4">
					<div class="card card-mini">
						<div class="card-header">
							<div class="card-title">免费设施管理</div>
							<ul class="card-action">
								<li><a class="btn  btn-link  btn-xs add-lock" style="color:#29c75f" id="add-lock">查看</a></li>
							</ul>
						</div>
						<div class="card-body no-padding table-responsive addlist">
							<table class="table card-table">
								<tr>
									<td><input type="text"  class="form-control" readonly></td>
									<td>
										<span class="input-group-btn" style="text-align: right;">
											<button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
								<tr>
									<td><input type="text" class="form-control" readonly></td>
									<td>
										<span class="input-group-btn"  style="text-align: right;">
											<button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				
			<div class="col-md-4">
					<div class="card card-mini">
						<div class="card-header">
							<div class="card-title">收费设施管理</div>
							<ul class="card-action">
								<li><a class="btn  btn-link  btn-xs add-lock" style="color:#29c75f" id="add-lock">查看</a></li>
							</ul>
						</div>
						<div class="card-body no-padding table-responsive addlist">
							<table class="table card-table">
								<tr>
									<td><input type="text" class="form-control" placeholder="" readonly></td>
									<td><input type="text" class="form-control" placeholder="" readonly></td>
									<td>
										<span class="input-group-btn">
											<button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
								<tr>
									<td><input type="text" class="form-control" placeholder="" readonly></td>
									<td><input type="text" class="form-control" placeholder="" readonly></td>
									<td>
										<span class="input-group-btn">
											<button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			
			
			</div>
				
				
				
			<div class="panel panel-default">
			 
			  <div class="panel-heading" style="background:#ffffff">订单列表</div>
			  <div style="overflow: auto;width:100%;height:auto">
				<table class="table table-striped table-condensed">
					<thead>
						<tr>
							<th>id</th>
							<th>下单时间</th>
							<th>订单ID</th>
							<th>房间</th>
							<th>状态</th>
							<th>维修内容</th>
							<th>需求</th>
							<th>维修时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
								<td>${order.time}</td>
								<td>${order.status}</td>
								<td>${order.content}</td>
								<td>${order.roomId}</td>
								<td>${order.cleanTime}</td>
								<td>${order.oederId}</td>
								<td>${order.demand}</td>
								<td>
								<c:if test="${order.status=='等待管理员呼叫保洁'}">
									<a href="javascript:;" class="comfirm-order" order-id="${order.id}" style="display:block">确定服务</a>
									</c:if>
									<c:if test="${order.status=='正在清扫'}">
									<a href="javascript:;" class="success-order" order-id="${order.id}" style="display:block">确定完成</a>
									</c:if>
									<!-- 
									<c:if test="${order.status=='确认中'}">
									<a href="javascript:;" class="btn comfirm-order" data-id="${order.id}">确认订单</a><br>
									<a href="javascript:;" class="btn close-order" data-id="${order.id}">关闭订单</a>
									</c:if>
									 -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				
				<ul id="pagecontroller" class="pagination">
					
				</ul>
				
				</div>
				
			  
		
			</div>	
							
				
				
				
					
				
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
	  	$("table th,table td").css('min-width','120px')
		$("table th:first-child,table td").css('min-width','60px')
		$("tbody tr").css('vertical-align','middle')
		//设施查看
	  	$(".add-lock").click(function(){
	  		$(this).parent().parent().parent().parent().find('.addlist').slideToggle();
	  	})
	    //添加设施
	    
	  	
	  	
	  	
	  	
	    
		//确认订单
		$('.comfirm-order').click(function(){
			var url = '../order/cleanOrder';
			var id = $(this).attr('order-id');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id,
				},
				url : url,
				error : function(data) {
					
				},
				success : function(data) {
					console.log(data)
					if(data.statusCode == 1){
						window.location.href = '../admin/customer_baojie';
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//关闭订单
		$('.success-order').click(function(){
			var url = '../order/cleanOrders';
			var id = $(this).attr('order-id');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id,
				},
				url : url,
				error : function(data) {
					
				},
				success : function(data) {
					console.log(data)
				
					
					
					if(data.statusCode == 1){
						window.location.href = '../admin/customer_baojie';
					}else{
						alert(data.content);
					}
					
				}
			});
		});
		
		$("#addfac").click(function(){
			$("#myalerts").fadeIn();
		})
		$(".close").click(function(){
			$("#myalerts").fadeOut();
		})
	})
	</script>
	</my_body>
</body>
</html>