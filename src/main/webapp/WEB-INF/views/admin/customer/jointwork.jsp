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
	
	<div class="modallg lockhouse">
		<!-- Modal -->
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">他的房屋</h4>
		      </div>
		      <div class="modal-body">
		       	<ul id="houseList">
		    
		       	</ul>
		      </div>
		      <div class="modal-footer">
		      	   <button type="button" class="btn btn-primary closes">关闭</button>
		      </div>
		    </div>
		  </div>
	  </div>
	
	
	
	
	<!-- 删除 -->
	<div class="modallg removeIp">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				 <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title house-title">删除商户<i></i></h4>
			  </div>
			  <div class="modal-body" id="mmb">
				<p>您确定要删除这个用户吗？</p>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-danger Del" id="removeIp">OK</button>
			  </div>
			</div>
		  </div>
	</div>
	
	
	
	<!-- 新建 -->
	<div id="myalerts">
		<!-- Modal -->
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">创建商户管理ID</h4>
		      </div>
		      <div class="modal-body">
		       	 <div class="form-horizontal">
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录账号</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputzh" placeholder="Account" autocomplete="off">
							</div>
						 </div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录密码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputmm" placeholder="Password"  autocomplete="off">
								</div>
						 </div>		 			  
<!-- 
						<div class="form-group">
								<label  class="col-sm-2 control-label">所属角色</label>
								<div class="col-sm-10">
									<select class="form-control" id="setipmassage">
										<option ip="1">管理员</option>
										<option ip="0">超级管理员</option>
									</select>
								</div>
						 </div>
-->	
						<div class="form-group">
								<label  class="col-sm-2 control-label">公司名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputxm" placeholder="Name"  autocomplete="off">
								</div>
						</div>								  
						<div class="form-group">
								<label  class="col-sm-2 control-label">联系方式</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputdh" placeholder="Telephone number"  autocomplete="off">
								</div>
						 </div>
					</div>	
															  
		      </div>
		      <div class="modal-footer">
		      	   <button type="button" id="newIp" class="btn btn-primary closes">确定</button>
		      </div>
		    </div>
		  </div>
	</div>
	
	<!--修改 -->
	<div class="modallg setingip">
		
		<!-- Modal -->
		<div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">修改管理ID信息</h4>
		      </div>
		      <div class="modal-body">
		       	 <div class="form-horizontal">
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录账号</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="setinputzh" placeholder="Account" value="">
							</div>
						 </div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录密码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="setinputmm" placeholder="Password">
								</div>
						 </div>		 			  
<!-- 
						<div class="form-group">
								<label  class="col-sm-2 control-label">所属角色</label>
								<div class="col-sm-10">
									<select class="form-control" id="setipmassage">
										<option ip="1">管理员</option>
										<option ip="0">超级管理员</option>
									</select>
								</div>
						 </div>
-->				
						<div class="form-group">
								<label  class="col-sm-2 control-label">公司名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputxm" placeholder="Name">
								</div>
						</div>		
										  
						<div class="form-group">
								<label  class="col-sm-2 control-label">联系方式</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="setinputdh" placeholder="Telephone number">
								</div>
						 </div>
					</div>	
															  
		      </div>
		      <div class="modal-footer">
		      	   <button type="button" id="setadminIp" disabled="disabled" class="btn btn-primary closes">保存</button>
		      </div>
		    </div>
		  </div>
		
	</div>
	
	
	
	
	
	<div class="row animate" >
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>合作商户</h3>
					</div>
					<ul class="card-action navbar-right" id="app-new-house-btn" style="position:relative;top:14px;">
						<li><a class="btn btn-success " href="javascript:;" id="newip">创建商户ID</a>
						</li>
					</ul>
				</div>
			
					
			<div class="panel panel-default">
			 
			
			  <div style="overflow: auto;width:100%;height:auto">
				<table class="table table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th>ID</th>
							<th>账号</th>
							<th>密码</th>
							<th>公司名称</th>
							<th>注册时间</th>
							<th>电话号码</th>
							<th>操作</th>
						</tr>
					</thead>
					
					<tbody id="p-table">
						<c:forEach items="${orders}" var="order">
							<tr pid="${order.id}" name="${order.userName}"  tel="${order.tel}">
								<td>${order.id}</td>
								<td>${order.userName}</td>
								<td>${order.password}</td>
								<td>${order.tradeName}</td>
								<td>${order.time}</td>
								<td>${order.tel}</td>
								<td>
									<a href="javascript:;" title="删除用户" class="removeip">删除</a>
					 			</td>
							</tr>
						</c:forEach>
					</tbody>
					 
				</table>
			<script>

			</script>
				
				<ul id="pagecontroller" class="pagination">
					
				</ul>
				
				</div>
				
			  
		
			</div>	
			
			
			</div>
			
		</div>
	</div>
	<script type="text/javascript">
	   $(document).ready(function(){
		var persons="";//管理员id	
		
		$("tbody tr td").css('vertical-align','middle')
	    //1创建
		$("#newip").click(function(){
			$("#myalerts").fadeIn();
		})
		//2检查用户名是否重复
		$("#inputzh").blur(function(){
		//	 alert($(this).val())
		
		})
		//3确认创建
		$("#newIp").click(function(){
			var account=$("#inputzh").val();
			var password=$("#inputmm").val();
			//var ip=$("#ipmassage option:selected").attr('ip');
			var name=$("#inputxm").val();
			var tel=$("#inputdh").val();
			if(account==""||password==""||name==""||tel==""){
				alert('请填写上述完整信息')
			}else{
				var postdata={
						'userName':account,
						'password':password,
						'tradeName':name,
						'tel':tel
				}
				console.log(postdata);
				$.ajax({
					type:'POST',
					dataType:'json',
					data:postdata,
					url:'/admin/TenantAdd',
					success:function(data){
						console.log(data.content);
						location=location;
					}
				})
			}
			
		})
		
		$(".close,.closes").click(function(){
			$("#myalerts,.modallg").fadeOut();
		})
	    //1删除removeip
	    $("#p-table").on('click','tr td .removeip',function(){
			$(".removeIp").fadeIn();
			persons=$(this).parent().parent().attr('pid');
		})
		//2确认删除removeip
		$("#removeIp").click(function(){
			
		    if(persons==""||persons==null){
		    	alert('选择要删除的管理员之后再提交删除')
		    	return;
		    }
		    console.log(persons);
		    $(".modallg").fadeOut();
			var url = '../admin/TenantD';
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : persons,
			    },
				url : url,
				success : function(data) {
					console.log(data)
					alert(data.content)
	                $(".modallg").fadeOut();
					location=location;
				}
			});
			
		})
		
	   })
	</script>
	</my_body>
</body>
</html>