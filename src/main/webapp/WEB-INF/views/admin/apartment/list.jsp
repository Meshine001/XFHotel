<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>公寓列表-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">

</head>
<body>
	<my_body>

	<div id="myalerts">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				 <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title house-title">删除房源<i></i></h4>
			  </div>
			  <div class="modal-body" id="mmb">
				<p>您确定要删除这个房源吗？确定后此房源的所有信息将不存在。</p>
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-danger Del">OK</button>
			  </div>
			</div>
		  </div>
	</div>
	
	<div class="modallg">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				 <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title house-title">房东列表<i></i></h4>
			  </div>
			  <div class="modal-body row" id="fangdongList">
				
				 
				  
				  <div class="col-xs-6 col-sm-4">
				  	<div class="input-group">
							<span class="input-group-addon">
								<input type="checkbox" value="1">
							</span>
							<input type="text" class="form-control" placeholder="张三" readonly>
						</div>
				  </div>
				  <div class="col-xs-6 col-sm-4">
				  	<div class="input-group">
							<span class="input-group-addon">
								<input type="checkbox"  value="0">
							</span>
							<input type="text" class="form-control" placeholder="张三" readonly>
						</div>
				  </div>
				  <div class="col-xs-6 col-sm-4">
				  	<div class="input-group">
							<span class="input-group-addon">
								<input type="checkbox" value="">
							</span>
							<input type="text" class="form-control" placeholder="张三" readonly>
						</div>
				  </div>
				
				 
				  
				   
			  </div>
			  <div class="modal-footer">
				<button type="button" class="btn btn-danger Del" id="payout">OK</button>
			  </div>
			</div>
		  </div>
	</div>
	
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				
				<div class="card-header ">
					<div class="card-title">
						<h3>公寓列表</h3>
					</div>
					<ul class="card-action navbar-right" style="position:relative;top:14px;">
						<li><a class="btn btn-success" id="add-landlord">选择房东</a>
						</li>
						<li><a class="btn btn-success " href="<%=basePath%>/admin/apartment/add">添加</a>
						</li>
					</ul>

				</div>
				
				
				
				<div style="overflow: hidden;width:100%;height:auto;overflow-x:auto;">
				<table class="table">
					<thead>
						<tr>
							<th>房屋归属</th>
							<th>房屋id</th>
							<!--<th>类型</th>  -->
							<th>房号</th>
							<th>地址</th>
							<th>位置</th>
							<th>小区</th>
							<th>楼号</th>
							<th>楼层</th>
							<!--<th>朝向</th>-->
							<th>面积</th>
							<th>操作</th>
						</tr>
					</thead>

					<tbody id="h-table">
						<c:forEach items="${apartments}" var="apartment">
							<!-- Modal -->
		
	
							<tr roomId="${apartment.id}">
								<td><input type="checkbox" name="chkItem"></td>
								<td>${apartment.id}</td>
						   <!-- <td>${apartment.basic_info.lei_xing}</td> -->		
								<td>${apartment.position.men_pai}</td>
								<td>${apartment.position.jie_dao}</td>
								<td>${apartment.position.xa_wei_zhi}</td>
								<td>${apartment.position.xiao_qu}</td>
								<td>${apartment.position.lou_hao}</td>
								<td>${apartment.position.lou_ceng}/${apartment.position.zong_lou_ceng}</td>
							<!--	<td>${apartment.basic_info.cao_xiang}</td>-->	
								<td>${apartment.basic_info.mian_ji}</td>
								<th><a class="btn btn-success"   data-toggle="tooltip" data-placement="left" title="编辑详情"
									href="<%=basePath %>/admin/apartment/update/${apartment.id}">编辑详情</a><br>
									<a class="btn btn-success price_s" data-toggle="tooltip" data-placement="left" title="编辑价格"
									href="<%=basePath%>/admin/apartment/price/${apartment.id}">编辑价格</a><br>
									
									<a href="<%=basePath %>/admin/apartment/showHome/${apartment.id}" class="btn btn-success" data-toggle="tooltip" data-placement="left" title="隐藏/显示" >
										<c:if test="${apartment.show_home == true }">首页隐藏</c:if>
										<c:if test="${apartment.show_home == false }">首页显示</c:if>
									</a><br>
									<a  data-toggle="tooltip" data-placement="left" title="删除" class="btn btn-danger">删除</a>
							
								<!-- 房客留言操作begin -->	
									<br><a href="<%=basePath%>/admin/leavemsglist"  data-toggle="tooltip" data-placement="left" title="查看评论" class="btn btn-info evalpinglun">查看评论</a>
								<!-- 房客留言操作end -->	
								
								<!-- 房态修改begin -->	
									<br><a href="<%=basePath%>/admin/status"  data-toggle="tooltip" data-placement="left" title="房态设置" class="btn btn-warning houseStatus">房态设置</a>
								<!-- 房态修改end -->	
								    
								<!-- 排序begin -->
									<br><div class="paixu"><input type="text" placeholder="${apartment.sort}"><a class="stb">排序</a></div>
								<!-- 排序begin -->
								
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
	</my_body>
	<my_script>
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
		<script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/vendor.js"></script>
		
	    <script>
		    
			$(document).ready(function(){
				$("[data-toggle='tooltip']").tooltip();  
				$("table th,table td").css('text-align','center')
				$("table th,table td").css('min-width','80px')
				$("#h-table .paixu input").focus(function(){
					$(this).parent().css('border-color','#29c75f');
					$(this).parent().find('.stb').css('bakcground','#29c75f');
				})
				$("#h-table .paixu input").blur(function(){
					$(this).parent().css('border-color','#ccc');
					$(this).parent().find('.stb').css('bakcground','#ccc');
				})
				
				$("#h-table").on('click','tr .evalpinglun,tr .houseStatus',function(){
					var roomid=$(this).parent().parent().attr('roomid');
					window.sessionStorage.setItem('roomId',roomid);
				})
				$("#h-table").on('click','tr .price_s',function(){
					var roomid=$(this).parent().parent().attr('roomid');
					window.sessionStorage.setItem('roomId',roomid);
				})
				
				
				$("#h-table").on('click','tr .paixu .stb',function(){
					var roomid=$(this).parent().parent().parent().attr('roomid');
					var sort=$(this).parent().find('input').val();
					console.log(roomid+'-'+sort);
					$.ajax({
						type : 'post',
						dataType : 'json',
						url:'/admin/roomSort',
						data : {
							'roomId' : roomid,
							'sort':sort
						},
						success:function(data){
							console.log(data);
							alert(data.content)
						}
					})
					
				})
				
				
			
					var roomIddel="";
					$("#h-table").on('click','tr th .btn-danger',function(){
						roomIddel=$(this).parent().parent().attr('roomid');
						$("#myalerts").fadeIn();
					})
					$("#myalerts .Del").click(function(){
						$.ajax({
							type:'get',
							dataType:'json',
							url:'/admin/apartment/delete/'+roomIddel,
						})
						setTimeout(function(){
							location=location
						},500)	
					})
					$("#myalerts .close").click(function(){
						$("#myalerts").fadeOut()
					})
					
					
					var roomids="";//获取选择的房子id
					var fangdongid=""//获取选择的房东id
					$("#add-landlord").click(function(){
							$(".modallg").fadeIn();
							var result = new Array();
			                $("#h-table tr td input[name = chkItem]:checkbox").each(function () {
			                    if ($(this).is(":checked")) {
			                        result.push($(this).parent().parent().attr('roomId'));
			                    }
			             	});
			                roomids=result.join(",");
			    			console.log(roomids)
			                //获取房东
			      //          $.ajax({
			      //          	type:'POST',
			      //          	dataType:'json',
			      //          	url:'',
			      //          	data:{},
			      //          	success:function(data){
			      //          		console.log(data)
			                		
			      //          	}
			       //         })
					})
					
					$(".close").click(function(){
						$(".modallg").fadeOut();
					})
					
				//	$("#fangdongList").on('click','.input-group-addon input',function(){
				//		$(this).is(":checked");
				//		$(this).parent().parent().parent().siblings().find('.input-group-addon input')
				//	});
					
					//给选中的房东添加房屋
					$("#payout").click(function(){
						
						$(".modallg").fadeOut();
					})
					
				
			})
	    </script>
	</my_script>
</body>
</html>