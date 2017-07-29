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
									  <input type="text" class="form-control" id="mianfeiName" placeholder="免费设施名称" aria-describedby="basic-addon1">
									</div>
				 				</div>
							</div>
							<div>
								<button type="button" class="btn btn-success affirm" id="mianfeiBtn">OK</button>
							</div>
					    </div>
					    <div role="tabpanel" class="tab-pane" id="etc">
							<div class="row feature">
									<div class="col-md-6">
								 		<div class="input-group">
										  <span class="input-group-addon">名称</span>
										  <input type="text" class="form-control" placeholder="收费设施名称" id="shoufeiName">
										</div>
								 	</div>
								 	<div class="col-md-6">
								 		<div class="input-group">
								 		  <span class="input-group-addon">价格</span>
										  <input type="text" class="form-control" placeholder="价格" id="shoufeiPrice">
										</div>
								 	</div>	
							</div>
							<div>
								<button type="button" class="btn btn-success affirm" id="shoufeiBtn">OK</button>
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
							<table class="table card-table" id="mianfeiList">
								
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
							<table class="table card-table" id="shoufeiList">
								
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
							<th>房间</th>
							<th>类型</th>
							<th>设施</th>
							<th>天数</th>
							<th>价格</th>
							<th>添加时间</th>
							<th>当前状态</th>
							<th>需求</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr>
								<td>${order.id}</td>
								<td>${order.time}</td>
								<td>${order.roomId}</td>
								<td>${order.classify}</td>
								<td>${order.Facility}</td>
								<td>${order.fate}</td>
								<td>${order.price}</td>
								<td>${order.addTime}</td>
								<td>${order.status}</td>
								<td>${order.demand}</td>
								<td>
								<c:if test="${order.status=='等待管理员确认'}">
									<a href="javascript:;" class="comfirm-order" order-id="${order.id}" style="display:block">确定服务</a>
									</c:if>
									<c:if test="${order.status=='正在路上'}">
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
	 	//查询免费
	    var locadMianfeiSheshi=function(){
	  		$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'classify':0
				},
				url : '/facility/getFacilityForm',
				success : function(data) {
					console.log(data)
					var srt="";
					$("#mianfeiList").html("");
					for(var i=0;i<data.length;i++){
						srt+='<tr id="'+data[i].id+'" price="'+data[i].price+'"><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].name+'" readonly></td><td  style="padding:0 15px"><span class="input-group-btn" style="text-align: right;"><button class="btn btn-danger btn-sm" type="button">删除</button></span></td></tr>';
					};
					$("#mianfeiList").append(srt);
				}
			});
	  	}
	    
	    locadMianfeiSheshi();
	  //查询收费
	    var locadShoufeiSheshi=function(){
	  		$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'classify':1
				},
				url : '/facility/getFacilityForm',
				success : function(data) {
					console.log(data)
					var srt="";
					$("#shoufeiList").html("");
					for(var i=0;i<data.length;i++){
						srt+='<tr id="'+data[i].id+'" price="'+data[i].price+'"><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].name+'" readonly></td><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].price+'" readonly></td><td  style="padding:0 15px"><span class="input-group-btn" style="text-align: right;"><button class="btn btn-danger btn-sm" type="button">删除</button></span></td></tr>';
					};
					$("#shoufeiList").append(srt);
				}
			});
	  	}
	    
	    locadShoufeiSheshi();
	    
    //删除收费
	    
	    $("#shoufeiList").on('click','tr td .btn-danger',function(){
	    	var id=$(this).parent().parent().parent().attr('id');
	    	$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id':id
				},
				url : '/facility/deleteFacility/',
				success : function(data) {
					console.log(data)
					location=location
				}
			});
	    	
	    })
	    
	    
	    //删除免费
	    
	    $("#mianfeiList").on('click','tr td .btn-danger',function(){
	    	var id=$(this).parent().parent().parent().attr('id');
	    	$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id':id
				},
				url : '/facility/deleteFacility/',
				success : function(data) {
					console.log(data)
					location=location
				}
			});
	    	
	    })
	    
	    //免费
	    $("#mianfeiBtn").click(function(){
	    	var mianfeiName=$("#mianfeiName").val();
	    	if(mianfeiName==""){
	    		alert("请提交正确信息")
	    	}else{
	    		$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'name' : mianfeiName,
						'price':0,
						'classify':0
					},
					url : '/facility/addFacility/',
					success : function(data) {
						console.log(data)
						if(data.statusCode == 1){
							alert(data.content);
							location=location
						}else{
							alert(data.content);
						}
					}
				});
	    	}
	    })
	  	 //收费
	    $("#shoufeiBtn").click(function(){
	    	var shoufeiName=$("#shoufeiName").val();
	    	var shoufeiPrice=$("#shoufeiPrice").val();
	    	if(shoufeiName=="" || shoufeiPrice==""){
	    		alert("请提交正确信息")
	    	}else{
	    		$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'name' : shoufeiName,
						'price':shoufeiPrice,
						'classify':1
					},
					url : '/facility/addFacility/',
					success : function(data) {
						console.log(data)
						if(data.statusCode == 1){
							alert(data.content);
							location=location
						}else{
							alert(data.content);
						}
					}
				});
	    	}
	    })
	  	
	 
	    
	  	
	    
		//确认订单
		$('.comfirm-order').click(function(){
			var url = '../order/FacilityOrder';
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
						window.location.href = '../admin/customer_addfacility';
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//关闭订单
		$('.success-order').click(function(){
			var url = '../order/FacilityOrders';
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
						window.location.href = '../admin/customer_addfacility';
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