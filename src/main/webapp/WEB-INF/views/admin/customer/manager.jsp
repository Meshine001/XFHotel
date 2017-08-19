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
				<h4 class="modal-title house-title">删除管理员<i></i></h4>
			  </div>
			  <div class="modal-body" id="mmb">
				<p>您确定要删除这个管理员吗？</p>
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
		        <h4 class="modal-title" id="myModalLabel">创建管理员角色</h4>
		      </div>
		      <div class="modal-body">
		       	 <div class="form-horizontal">
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录账号</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="inputzh" placeholder="Account">
							</div>
						 </div>
						<div class="form-group">
							<label  class="col-sm-2 control-label">登录密码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputmm" placeholder="Password">
								</div>
						 </div>		 			  
						<div class="form-group">
								<label  class="col-sm-2 control-label">所属角色</label>
								<div class="col-sm-10">
									<select class="form-control" id="ipmassage">
										<option ip="1">管理员</option>
									</select>
								</div>
						 </div>
<!-- 										 
						<div class="form-group">
								<label  class="col-sm-2 control-label">真实姓名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputxm" placeholder="Name">
								</div>
						</div>
 -->												  
						<div class="form-group">
								<label  class="col-sm-2 control-label">联系方式</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputdh" placeholder="Telephone number">
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
		        <h4 class="modal-title" id="myModalLabel">修改管理员信息</h4>
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
						<div class="form-group">
								<label  class="col-sm-2 control-label">所属角色</label>
								<div class="col-sm-10">
									<select class="form-control" id="setipmassage">
										<option ip="1">管理员</option>
									</select>
								</div>
						 </div>
<!-- 										 
						<div class="form-group">
								<label  class="col-sm-2 control-label">真实姓名</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputxm" placeholder="Name">
								</div>
						</div>
 -->												  
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
	
	
	
	
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>超级管理</h3>
					</div>
				</div>
			
					
			<div class="panel panel-default">
			 
			  <div class="panel-heading" style="background:#ffffff">
			 	 <div class="btn-group" role="group" aria-label="...">
					  <button type="button" class="btn btn-success" id="newip"><span></span>+ 创建管理员角色</button>
				 </div>
			  </div>
			  <div style="overflow: auto;width:100%;height:auto">
				<table class="table table-bordered table-striped table-condensed">
					<thead>
						<tr>
							<th>ID</th>
							<th>用户名</th>
							<th>角色</th>
							<th>电话号码</th>
							<th>上次登录时间</th>
							<th>是否已启用</th>
							<th>操作</th>
							<th>管理的房屋</th>
						</tr>
					</thead>
					
					<tbody id="p-table">
						<c:forEach items="${orders}" var="order">
							<tr pid="${order.id}" name="${order.username}" types="${order.authority}" tel="${order.contact}">
								<td>${order.id}</td>
								<td>${order.username}</td>
								<td>${order.authority}</td>
								<td>${order.contact}</td>
								<td>${order.time}</td>
								<td>${order.status}</td>
								<td>
								<c:if test="${order.status=='激活'}">
									<a href="javascript:;" class="setStatus" status="1" order-id="${order.id}">关闭</a>
								</c:if>
								<c:if test="${order.status=='冻结'}">
									<a href="javascript:;" class="setStatus" status="0" order-id="${order.id}">激活</a>
								</c:if>
					 			<a href="javascript:;" title="修改信息" class="setip">修改</a>
					 			<a href="javascript:;" title="删除用户" class="removeip">删除</a>
					 		</td>
								<td><a href="javascript:;" class="lock" order-id="${order.id}">查看</a></td>
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
		$("table th,table td").css({'min-width':'125px','text-align':'center'})
	    $("table th:first-child,table td:first-child").css({'width':'90px','min-width':'90px'});
		$("table th:first-child+th,table td:first-child+td").css({'width':'90px','min-width':'90px'})
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
			var ip=$("#ipmassage option:selected").attr('ip');
			var name=$("#inputxm").val();
			var tel=$("#inputdh").val();
			if(account==""||password==""||ip==""||name==""||tel==""){
				alert('请填写上述完整信息')
			}else{
				var postdata={
						'username':account,
						'psd':password,
						'authority':ip,
						'tel':tel
				}
				console.log(postdata);
				$.ajax({
					type:'POST',
					dataType:'json',
					data:postdata,
					url:'/admin/user/add',
					success:function(data){
						console.log(data);
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
			var url = '../admin/user/delete';
			$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id' : persons,
			    },
				url : url,
				success : function(data) {
					console.log(data)
					if(data.statusCode == 1){
						alert(data.content)
		                $(".modallg").fadeOut();
						location=location;
					}else{
						alert(data.content);
					}
				}
			});
			
		})
		
		//修改
		$('.setingip input').bind('input propertychange', function() {
			$("#setadminIp").removeAttr("disabled");//不修改的时候防止提交空数据；
		});
		$("#p-table").on('click','tr td .setip',function(){//获取现在账号信息
			persons=$(this).parent().parent().attr('pid');
		    var name=$(this).parent().parent().attr('name');
		    var tel=$(this).parent().parent().attr('tel');
		    $("#setinputzh").val(name);
		    $("#setinputdh").val(tel);
			$(".setingip").fadeIn();
			$("#setadminIp").attr('disabled','disabled');
		})
		$("#setadminIp").click(function(){//提交修改内容
			var _name=$("#setinputzh").val();
			var _password=$("#setinputmm").val();
			var _date=$("#setipmassage option:selected").attr("ip");
			var _tel=$("#setinputdh").val();
			var postData={
					'username':_name,
					'psd':_password,
					'authority':_date,
					'tel':_tel,
					'id':persons
			}
			console.log(postData);
			$.ajax({
				type:'post',
				dataType:'json',
				data:postData,
				url:'/admin/user/amend',
				success:function(data){
					console.log(data)
					alert(data.content);
					location=location;
				}
			})
		})
		
		
		// 激活、关闭用户
			
		$("#p-table").on('click','tr td .setStatus',function(){//获取现在账号信息
			var persons=$(this).attr('order-id');
			var status=$(this).attr('status');
		    $.ajax({
		    	type:'post',
		    	dataType:'json',
		    	data:{'id':persons,'status':status},
		    	url:'/admin/user/update',
		    	success:function(data){
		    		console.log(data);
		    		alert(data.content)
		    		location=location;
		    	}
		    })
		});
		//myalerts_house
		// 查看管理员管理的房屋
		
		$("#p-table").on('click','tr td .lock',function(){//获取现在账号信息
			var persons=$(this).attr('order-id');
		    $(".lockhouse").fadeIn();
		    $.ajax({
   		    	type:'post',
   		    	dataType:'json',
   		    	data:{'id':persons},
   		    	url:'/admin/user/steward',
   		    	success:function(data){
   		    		console.log(data);
   		    		var str="";
					$("#houseList").html("");
					for(var i=0;i<data.content.length;i++){
						str+='<li><p>名称：'+data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+'-'+data.content[i].position.men_pai+'</p><p>地址：'+data.content[i].position.bd_wei_zhi+'-'+data.content[i].position.jie_dao+'</p><p>价格：'+data.content[i].basic_info.jia_ge+'元/天</p></li>';
					}
					if(data.content.length<=0){
						str='<li><p>暂时没有房屋</p></li>'
					}
					$("#houseList").append(str);
   		    	}
   		    })
		            
		});
		
	   })
	</script>
	</my_body>
</body>
</html>