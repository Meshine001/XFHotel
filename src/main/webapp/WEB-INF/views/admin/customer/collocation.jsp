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
		<!-- Modal -->
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">他的房屋</h4>
		      </div>
		      <div class="modal-body">
		       	<ul id="houseList">
		       		<li>
		       			<p>名称：福邸铭门2205</p>
		       			<p>地址：西安市, 新城区, 幸福中路 幸福中路69号</p>
		       			<p>价格：350元/天</p>
		       			<p>入住率：80%</p>
		       		</li>
		       		<li>
		       			<p>名称：福邸铭门2205</p>
		       			<p>地址：西安市, 新城区, 幸福中路 幸福中路69号</p>
		       			<p>价格：350元/天</p>
		       			<p>入住率：80%</p>
		       		</li>
		       		<li>
		       			<p>名称：福邸铭门2205</p>
		       			<p>地址：西安市, 新城区, 幸福中路 幸福中路69号</p>
		       			<p>价格：350元/天</p>
		       			<p>入住率：80%</p>
		       		</li>
		       	</ul>
		      </div>
		      <div class="modal-footer">
		      	   <button type="button" class="btn btn-primary closes">关闭</button>
		      </div>
		    </div>
		  </div>
	</div>
	
	
	
	
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="modal-body" id="mytable">
				  <!-- Nav tabs -->
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">房东信息</a></li>
				    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">房源申请</a></li>
				  </ul>
				
				  <!-- Tab panes -->
				  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="home">
		<!-- 房东信息 begin-->
				    	<div class="card card-mini">
							<div style="overflow: auto;width:100%;height:auto">
							<table class="table table-striped table-condensed">
			
								<thead>
									<tr>
										<th>id</th>
										<th>手机号码</th>
										<th>姓名</th>
										<th>收款账号</th>
										<th>上线房屋</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td>${order.id}</td>
											<td>${order.phone}</td>
											<td>${order.name}</td>
											<td>${order.number}</td>
											<td>查看</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							
							<ul id="pagecontroller" class="pagination">
								
							</ul>
							
							</div>
						</div>
		<!-- 房东信息end -->
				    </div>
				    <div role="tabpanel" class="tab-pane" id="profile">
		<!-- 房东申请记录begin -->
					
					<div class="card card-mini">
							<div style="overflow: auto;width:100%;height:auto">
							<table class="table table-striped table-condensed">
			
								<thead>
									<tr>
										<th style="min-width:80px">id</th>
										<th>房东姓名</th>
										<th>联系方式</th>
										<th>房屋地址</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ordersd}" var="order">
										<tr>
											<td style="min-width:80px">${order.id}</td>
											<td>${order.name}</td>
											<td>${order.tel}</td>
											<td>${order.site}</td>
											<td>${order.state}</td>
											<td>${order.state}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							
							
							<ul id="pagecontroller" class="pagination">
								
							</ul>
							
							</div>
						</div>	
					
		<!-- 房东申请记录end -->		    
				    </div>
				  </div>
				
				</div>		
			</div>
		</div>
		
		
		
	</div>
	<script type="text/javascript">
	   
	    $(".table th,.table td").css('min-width','180px');
		$("#home .table th:eq(0),#home .table td:eq(0)").css('min-width','80px');
	    $("#profile .table th:eq(0),#profile .table td:eq(0)").css('min-width','80px');
	    
	
		$("#home tbody").on('click','tr',function(){
			$("#myalerts").fadeIn();
		})
		$(".close,.closes").click(function(){
			$("#myalerts").fadeOut();
		})
	    
	    
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
	</script>
	</my_body>
</body>
</html>