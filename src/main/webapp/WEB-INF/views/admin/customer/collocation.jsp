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
		   <!--   	<li>
		       			<p>名称：'+data[i].position.xa_wei_zhi+'-'+data[i].position.xiao_qu+'-'+data[i].position.men_pai+'</p>
		       			<p>地址：'+data[i].position.bd_wei_zhi+'-'+data[i].position.jie_dao+'</p>
		       			<p>价格：'+data[i].basic_info.jia_ge+'元/天</p>
		       		</li> -->  
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
							<table class="table table-striped table-condensed table-bordered">
			
								<thead>
									<tr>
										<th>id</th>
										<th>手机号码</th>
										<th>姓名</th>
										<th>收款账号</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="order">
										<tr>
											<td>${order.id}</td>
											<td>${order.phone}</td>
											<td>${order.name}</td>
											<td>${order.number}</td>
											<td><a faid="${order.id}" class="hbtn btn-sm">查看房屋</a><a faid="${order.id}" class="hbtn btn-sm btn-sm-sc">删除</a></td>
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
							<table class="table table-striped table-condensed table-bordered">
			
								<thead>
									<tr>
										<th style="min-width:80px;width:80px">id</th>
										<th>姓名</th>
										<th>联系方式</th>
										<th>房屋地址</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${ordersd}" var="order">
										<tr>
											<td style="min-width:80px;width:80px">${order.id}</td>
											<td>${order.name}</td>
											<td>${order.tel}</td>
											<td>${order.site}</td>
											<td>${order.state}</td>
											<td>
												<c:if test="${order.state=='等待处理'}">
													<a href="javascript:;" class="comfirm-order" order-id="${order.id}" style="display:block">进行审核</a>
												</c:if>
												<c:if test="${order.state=='审核中'}">
													<a href="javascript:;" class="success-order" type="0" order-id="${order.id}" style="display:block">成功</a>
													<a href="javascript:;" class="success-order" type="1" order-id="${order.id}" style="display:block">失败</a>
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
					
		<!-- 房东申请记录end -->		    
				    </div>
				  </div>
				
				</div>		
			</div>
		</div>
		
		
		
	</div>
	<script type="text/javascript">
	   
	$("table th,table td").css({'min-width':'125px','text-align':'center'})
    $("table th:first-child,table td:first-child").css({'width':'90px','min-width':'90px'});
	$("table th:first-child+th,table td:first-child+td").css({'width':'90px','min-width':'90px'})
	$("tbody tr td").css('vertical-align','middle')
	    
	
	//	$("#home tbody").on('click','tr',function(){
	//		$("#myalerts").fadeIn();visible
	//	})
		$(".close,.closes").click(function(){
			$("#myalerts").fadeOut();
		})
	    
	    
		//确认审核
		$('.comfirm-order').click(function(){
			var url = '../landlord/ApplyOrder';
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
		
		//审核成功
		$('.success-order').click(function(){
			var url = '../landlord/FacilityOrders';
			var id = $(this).attr('order-id');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id,
					'judge':$(this).attr('type')
				},
				url : url,
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
		
		//查看房东的房屋
		$("#home tbody").on('click','tr td .hbtn',function(){
			$("#myalerts").fadeIn();
			var url = '../mobile/particulars/';
			var id = $(this).attr('faid');
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : id
				},
				url : url,
				success : function(data) {
					console.log(data)
					var str="";
					$("#houseList").html("");
					for(var i=0;i<data.length;i++){
						str+='<li><p>名称：'+data[i].position.xa_wei_zhi+'-'+data[i].position.xiao_qu+'-'+data[i].position.men_pai+'</p><p>地址：'+data[i].position.bd_wei_zhi+'-'+data[i].position.jie_dao+'</p><p>价格：'+data[i].basic_info.jia_ge+'元/天</p></li>';
					}
					if(data.length<=0){
						str='<li><p>暂时没有房屋</p></li>'
					}
					$("#houseList").append(str);
				}
			});
		})
	</script>
	</my_body>
</body>
</html>