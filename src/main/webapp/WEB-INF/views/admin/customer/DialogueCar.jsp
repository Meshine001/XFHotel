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
				<h4 class="modal-title house-title">汽车服务设置<i></i></h4>
			  </div>
			  <div class="modal-body">
				 <div  id="mytable"> 
				 	<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#free" aria-controls="free" role="tab" data-toggle="tab">接送站服务</a></li>
						<li role="presentation"><a href="#etc" aria-controls="etc" role="tab" data-toggle="tab">包车服务</a></li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="free">
							<div class="row feature">
								<div class="col-md-6">
                        			<div class="input-group">
									  <span class="input-group-addon">名称</span>
									  <input type="text" class="form-control" id="jiesongName" placeholder="接送站名称" aria-describedby="basic-addon1">
									</div>
				 				</div>
				 				<div class="col-md-6">
								 		<div class="input-group">
								 		  <span class="input-group-addon">价格</span>
										  <input type="text" class="form-control" placeholder="价格" id="jiesongPrice">
										</div>
								 </div>	
							</div>
							<div>
								<button type="button" class="btn btn-success affirm" id="jiesongBtn">OK</button>
							</div>
					    </div>
					    <div role="tabpanel" class="tab-pane" id="etc">
							<div class="row feature">
									<div class="col-md-6">
								 		<div class="input-group">
										  <span class="input-group-addon">名称</span>
										  <input type="text" class="form-control" placeholder="包车路线" id="baocheName">
										</div>
								 	</div>
								 	<div class="col-md-6">
								 		<div class="input-group">
								 		  <span class="input-group-addon">价格</span>
										  <input type="text" class="form-control" placeholder="价格" id="baochePrice">
										</div>
								 	</div>	
							</div>
							<div>
								<button type="button" class="btn btn-success affirm" id="baocheBtn">OK</button>
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
						<h3>叫车服务</h3>
					</div>
					<ul class="card-action" id="app-car-set" style="position:relative;top:14px;display:none;">
						<li><a class="btn btn-success" id="addfac">设置服务</a>
						</li>
					</ul>
				</div>
<!--  -->	
				
			<div class="row" id="app-car-con" style="display:none">
				<div class="col-md-4">
					<div class="card card-mini">
						<div class="card-header">
							<div class="card-title">接送站服务</div>
							<ul class="card-action">
								<li><a class="btn  btn-link  btn-xs add-lock" style="color:#29c75f" id="add-lock">查看</a></li>
							</ul>
						</div>
						<div class="card-body no-padding table-responsive addlist">
							<table class="table card-table" id="jiesongList">
								
								<tr id="1">
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="汽车北站" readonly>
									</td>
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="200" readonly>
									</td>
									<td style="padding:0 15px">
									    <span class="input-group-btn" style="text-align: right;">
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
							<div class="card-title">包车服务</div>
							<ul class="card-action">
								<li><a class="btn  btn-link  btn-xs add-lock" style="color:#29c75f" id="add-lock">查看</a></li>
							</ul>
						</div>
						<div class="card-body no-padding table-responsive addlist">
							<table class="table card-table" id="baocheList">
								<tr>
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="汽车北站" readonly>
									</td>
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="200" readonly>
									</td>
									<td style="padding:0 15px">
									    <span class="input-group-btn" style="text-align: right;">
										   <button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
								<tr>
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="汽车北站" readonly>
									</td>
									<td style="padding:10px 15px">
									    <input type="text" style="margin:0" class="form-control" value="200" readonly>
									</td>
									<td style="padding:0 15px">
									    <span class="input-group-btn" style="text-align: right;">
										   <button class="btn btn-danger btn-sm" type="button">删除</button>
										</span>
									</td>
								</tr>
								
							</table>
						</div>
					</div>
				</div>
			
			
			</div>
	
	
<!--  -->	
			<div class="panel panel-default" style="margin-top:20px;">
				<div style="overflow: auto;width:100%;height:auto;">
				<table class="table table-bordered table-hover">

					<thead>
						<tr>
							<th>id</th>
							<th>起止时间</th>
							<th>房间</th>
							<th>服务名称</th>
							<th>车辆</th>
							<th>服务内容</th>
							<th>联系电话</th>
							<th>价格</th>
							<th>支付平台</th>
							<th>订单状态</th>
							<th>其他需求</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders}" var="order">
							<tr order-id="${order.id}">
								<td>${order.id}</td>
								<td>${order.startTime}<br>${order.endTime}</td>
								<td>${order.roomName}</td>
								<td>${order.classify}</td>
								<td>${order.site}</td>
								<td>${order.tripId}</td>
								<td>${order.tel}</td>
								<td>${order.price}</td>
								<td>${order.payPlatform}</td>
								<td>${order.status}</td>
								<td>${order.demand}</td>
								<td>
									
									<c:if test="${order.status=='确认中'}">
									<a href="javascript:;" class=" btn  comfirm-order" order-id="${order.id}" style="display:block;width:120px;">确定服务</a>
									</c:if>
									<c:if test="${order.status=='进行中'}">
									<a href="javascript:;" class="success-order btn  " order-id="${order.id}" style="display:block;width:120px;">确定完成</a>
									</c:if>
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
		var userType=window.localStorage.getItem('userType');//0:超级管理员 1:管理员
		if(userType==0){
			$("#app-car-set,#app-car-con").show();
		}
	
	$(".table th,.table td").css({'min-width':'120px'})
	$(".table th:first-child,.table td:first-child").css({'width':'90px','min-width':'90px'});
	$(".table th:first-child+th,.table td:first-child+td").css({'width':'240px','min-width':'240px'});
	$(".table tr td").css('vertical-align','middle')	
	    $("#addfac").click(function(){
			$("#myalerts").fadeIn(100);
		})
		$(".close").click(function(){
			$("#myalerts").fadeOut(100);
		})
	    //查看汽车服务
	  	$(".add-lock").click(function(){
	  		$(this).parent().parent().parent().parent().find('.addlist').slideToggle();
	  	})
	    //添加接送站服务
	    $("#jiesongBtn").click(function(){
	      	var jiesongName=$("#jiesongName").val();
	      	var jiesongPrice=$("#jiesongPrice").val();
	    	if(jiesongName==""||jiesongPrice==""){
	    		alert("请填写正确信息")
	    	}else{
	    		$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'classify':0,
						'place':jiesongName,
						'price':jiesongPrice
					},
					url : '/site/addSite/',
					success : function(data) {
						console.log(data)
						if(data.statusCode==1){
							alert(data.content);
						}
						location=location;
					}
				});
	    		
	    	}
	    })
	   
	  //添加包车服务
	    $("#baocheBtn").click(function(){
	      	var baocheName=$("#baocheName").val();
	      	var baochePrice=$("#baochePrice").val();
	    	if(baocheName==""||baochePrice==""){
	    		alert("请填写正确信息")
	    	}else{
	    		$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'classify':1,
						'place':baocheName,
						'price':baochePrice
					},
					url : '/site/addSite/',
					success : function(data) {
						console.log(data)
						if(data.statusCode==1){
							alert(data.content);
						}
						location=location
					}
				});
	    	}
	    })
	   //填写用车服务选项=>接送站
	      var locadjiesong=function(){
	  		$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {},
				url : '/site/getSite/',
				success : function(data) {
					console.log(data)
					var jslist="",bclist="";
					$("#jiesongList,#baocheList").html("");
					for(var i=0;i<data.length;i++){
						
						if(data[i].classify==0){
							jslist+='<tr id="'+data[i].id+'" price="'+data[i].price+'"><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].place+'" readonly></td><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].price+'" readonly></td><td  style="padding:0 15px"><span class="input-group-btn" style="text-align: right;"><button class="btn btn-danger btn-sm" type="button">删除</button></span></td></tr>';
						}else if(data[i].classify==1){
							bclist+='<tr id="'+data[i].id+'" price="'+data[i].price+'"><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].place+'" readonly></td><td style="padding:10px 15px"><input type="text" style="margin:0" class="form-control" value="'+data[i].price+'" readonly></td><td  style="padding:0 15px"><span class="input-group-btn" style="text-align: right;"><button class="btn btn-danger btn-sm" type="button">删除</button></span></td></tr>';
						}
						
					};
					$("#jiesongList").append(jslist);
					$("#baocheList").append(bclist);
				}
			});
	  	}
	    locadjiesong();
	    
	    
	    
	    
	    
	  	//删除服务
	   $("#jiesongList,#baocheList").on('click','tr td .btn-danger',function(){
	    	var id=$(this).parent().parent().parent().attr('id');
	    	$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id':id
				},
				url : '/site/deleteSite/',
				success : function(data) {
					console.log(data);
					if(data.statusCode==1){
						alert(data.content);
					}
					location=location
				}
			});
	    	
	    })
	  	
	  	
	  	
	  	
		//确认订单
		$('.comfirm-order').click(function(){
			var url = '../triporder/tipOrders';
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
						location=location;
					}else{
						alert(data.content);
					}
				}
			});
		});
		
		//确认完成
		$('.success-order').click(function(){
			var url = '../triporder/tipOrder';
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

					if(data.statusCode == 1){
						location=location;
					}else{
						alert(data.content);
					}
					
				}
			});
		});
	});	
	</script>
	</my_body>
</body>
</html>