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
				<h4 class="modal-title house-title" id="app-title-msg">房东列表<i></i></h4>
			  </div>
			  <div class="modal-body row" id="fangdongList">
			  <!-- 
				  <div class="col-xs-6 col-sm-4">
				  	<div class="input-group">
							<span class="input-group-addon">
								<input type="radio" value="'+data.content[i].id+'" name="radio">
							</span>
							<input type="text" class="form-control" placeholder="'+data.content[i].username+'" readonly>
						</div>
				  </div> -->
			  </div>
			  <div class="modal-footer">
			    <button type="button" class="btn btn-danger Del" id="present" style="display:none">确定</button>
				<button type="button" class="btn btn-danger Del" id="payout" style="display:none">OK</button>
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
					<ul class="card-action navbar-right" id="app-new-house-btn" style="position:relative;top:14px;display:none">
						<li><a class="btn btn-success " href="<%=basePath%>/admin/apartment/add">添加新房源</a>
						</li>
					</ul>

				</div>
				
				
				
				<div style="overflow: hidden;width:100%;height:auto;overflow-x:auto;">
					<div class="col-md-12 statistics" style="display:none">
					    <div class="col-md-3">
					       		<label class="col-md-3"  style="padding:0;line-height:36px;">地区筛选</label>
					       		<div class="col-md-6" style="padding:0">
									<select id="weizhiselect"  class="form-control" style="margin:0;">
										<option tid="5">全部</option>
										<option tid="0">城东</option>
										<option tid="1">城南</option>
										<option tid="2">城西</option>
										<option tid="3">城北</option>
										<option tid="4">城中</option>
									</select>
								</div>	
					    </div>
					    <div class="col-md-2 col-xs-6">
					    	<a class="btn btn-success" id="appendto">选择管理员</a>
					    </div>
					    <div  class="col-md-2 col-xs-6">
					    	<a class="btn btn-success" id="add-landlord">选择房东</a>
					    </div>
					 </div>   
				<table class="table table-bordered table-hover">
					<thead id="h-table-nav">
						<tr>
							<th>选择</th>
							<th>房屋id</th>
							<th>房间图</th>
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
				var userType=window.localStorage.getItem('userType');//0:超级管理员 1:管理员
				var uid=window.localStorage.getItem('uid');
				if(userType==1){
					$.ajax({
						type:'post',
						dataType:'json',
						data:{'id':uid},
						url:'/admin/user/steward',
						success:function(data){
							console.log(data);
							$("#h-table").html("");
							var _str="";
							for(var j=0;j<data.content.length;j++){
								_str+='<tr roomId="'+data.content[j].id+'"><td><input type="checkbox"name="chkItem"></td><td>'+data.content[j].id+'</td><td>'+data.content[j].position.men_pai+'</td><td>'+data.content[j].position.jie_dao+'</td><td>'+data.content[j].position.xa_wei_zhi+'</td><td>'+data.content[j].position.xiao_qu+'</td><td>'+data.content[j].position.lou_hao+'</td><td>'+data.content[j].position.lou_ceng+'/'+data.content[j].position.zong_lou_ceng+'</td><td>'+data.content[j].basic_info.mian_ji+'</td>';
								_str+='</tr>';
							}
							if(data.content.length<=0){
								_str='<tr><td colspan="10"><p>该地区暂时没有房源、点我<a style="color:#f18c0b" href="/admin/apartment/add">添加新房源</a>吧。</p></td><tr>';
							}
							$("#h-table").append(_str);
							$("#h-table tr td:first-child").hide();
							$("#h-table").parent().find('thead th').eq(0).hide();
							$("#h-table").parent().find('thead th:last-child').hide();
						}
					})
				}else if(userType==0){
					$(".statistics,#app-new-house-btn").show();
					selectWeizhi(5);
				}
				
				//全选
//				$("#inputbox").on('click',function(){
//					if ($(this).is(":checked")) {
//						$("#h-table tr td input[name = chkItem]").attr('checked', true);
//                    }else{
//                    	$("#h-table tr td input[name = chkItem]").attr('checked',false);
//                    }
//				})
				
				
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
							$("#app-title-msg").text('房东列表');
							$("#payout").show();
							$("#present").hide();
							var result = new Array();
			                $("#h-table tr td input[name = chkItem]:checkbox").each(function () {
			                    if ($(this).is(":checked")) {
			                        result.push($(this).parent().parent().attr('roomId'));
			                    }
			             	});
			                roomids=result.join(",");
			    			
			                //获取房东
			                $.ajax({
			                	type:'POST',
			                	dataType:'json',
			                	url:'/landlord/collocation/',
			                	success:function(data){
			                		console.log(data)
			                		$("#fangdongList").html("");
			                		var sty="";
			                		for(var i=0;i<data.length;i++){
			                			sty+='<div class="col-xs-6 col-sm-4"><div class="input-group"><span class="input-group-addon"><input type="radio"value="'+data[i].id+'"name="radio"></span><input type="text"class="form-control"placeholder="'+data[i].name+'"readonly></div></div>';
			                		}
			                		if(data.length<=0){
			                			sty='<p>暂时没有房东</p>'
			                		}
			                		$("#fangdongList").append(sty);
			                	}
			                })
			       
					})
					
					$(".close").click(function(){
						$(".modallg").fadeOut();
					})
					
			//给选中的房东添加房屋
					$("#payout").click(function(){
						
						 $("#fangdongList input[name = radio]").each(function () {
			                    if ($(this).is(":checked")) {
			                    	fangdongid=$(this).val();
			                    }
			             });
						 console.log(fangdongid)
						 console.log(roomids)
						 if(roomids==""||roomids==null||fangdongid==""||fangdongid==null){
							 alert('操作有误、不能提交！');
							 return
						 }
				  	     $.ajax({
			                	type:'POST',
			                	dataType:'json',
			                	url:'/landlord/allocation/',
			                	data:{'id': fangdongid,'roomId':roomids},
			                	success:function(data){
			                		console.log(data)
			                		alert(data.content)
			                	}
			               })
						 
						$(".modallg").fadeOut();
					})
					
				
			})
			
			// 选择管理员视口;
			$("#appendto").click(function(){
				$(".modallg").fadeIn();
				$("#app-title-msg").text('管理员列表');
				$("#present").show();
				$("#payout").hide();
				var result = new Array();
                $("#h-table tr td input[name = chkItem]:checkbox").each(function () {
                    if ($(this).is(":checked")) {
                        result.push($(this).parent().parent().attr('roomId'));
                    }
             	});
                roomids=result.join(",");
                console.log(roomids);
                $.ajax({
                	type:'post',
                	dataType:'json',
                	data:{},
                	url:'/admin/user/all/',
                	success:function(data){
                		console.log(data)
                		if(data.statusCode==1){
                			$("#fangdongList").html("");
                			var str="";
                			for(var i=0;i<data.content.length;i++){
                				str+='<div class="col-xs-6 col-sm-4"><div class="input-group"><span class="input-group-addon"><input type="radio"value="'+data.content[i].id+'"name="radio"></span><input type="text"class="form-control"placeholder="'+data.content[i].username+'"readonly></div></div>';
                			}
                			$("#fangdongList").append(str);
                		}
                	}
                })
                
			})
			
			//选择管理员并分配房屋；
			$("#present").click(function(){
						
						 $("#fangdongList input[name = radio]").each(function () {
			                    if ($(this).is(":checked")) {
			                    	fangdongid=$(this).val();
			                    }
			             });
	
						 if(roomids==""||roomids==null||fangdongid==""||fangdongid==null){
							 alert('操作有误、不能提交！');
							 return
						 }
				  	     $.ajax({
			                	type:'POST',
			                	dataType:'json',
			                	url:'/admin/user/allocation/',
			                	data:{'id': fangdongid,'roomId':roomids},
			                	success:function(data){
			                		console.log(data)
			                		alert(data.content)
			                		location=location;
			                	}
			               })
						 
						$(".modallg").fadeOut();
			})
			
			function selectWeizhi(value){
				console.log(value)
				 $.ajax({
					 	type:'POST',
						dataType:'json',
						data:{'wei':value},
						url:'/admin/user/getRoom/',
						success:function(data){
							console.log(data);
							if(data.statusCode==1){
								$("#h-table").html("");
								var _str="";
								for(var j=0;j<data.content.length;j++){
									_str+='<tr roomId="'+data.content[j].id+'"><td><input type="checkbox"name="chkItem"></td><td>'+data.content[j].id+'</td><td><img style="width:120px" src="'+'<%=basePath%>/images/'+data.content[j].fang_jian_tu[0]+'"></td><td>'+data.content[j].position.men_pai+'</td><td>'+data.content[j].position.jie_dao+'</td><td>'+data.content[j].position.xa_wei_zhi+'</td><td>'+data.content[j].position.xiao_qu+'</td><td>'+data.content[j].position.lou_hao+'</td><td>'+data.content[j].position.lou_ceng+'/'+data.content[j].position.zong_lou_ceng+'</td><td>'+data.content[j].basic_info.mian_ji+'</td>';
									_str+='<th><a class="btn btn-success"   data-toggle="tooltip" data-placement="left" title="编辑详情" href="/admin/apartment/update/'+data.content[j].id+'">编辑详情</a><br>';
									if(data.content[j].show_home==true){
										_str+='<a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn btn-success" data-toggle="tooltip" data-placement="left" title="隐藏/显示" >首页隐藏</a><br>';
									}else{
										_str+='<a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn btn-success" data-toggle="tooltip" data-placement="left" title="隐藏/显示" >首页显示</a><br>';
									}
									_str+='<a  data-toggle="tooltip" data-placement="left" title="删除" class="btn btn-danger">删除</a><br>'
									_str+='<a href="/admin/leavemsglist"  data-toggle="tooltip" data-placement="left" title="查看评论" class="btn btn-info evalpinglun">查看评论</a><br>'
									_str+='<a href="/admin/status/?id='+data.content[j].id+'"  data-toggle="tooltip" data-placement="left" title="房态设置" class="btn btn-warning houseStatus">价格房态</a><br>'
									_str+='<div class="paixu"><input type="text" placeholder="'+data.content[j].sort+'"><a class="stb">排序</a></div>'
									_str+='</th></tr>';
								}
								if(data.content.length<=0){
									_str='<tr><td colspan="10"><p>该地区暂时没有房源、点我<a style="color:#f18c0b" href="/admin/apartment/add">添加新房源</a>吧。</p></td><tr>';
								}
								$("#h-table").append(_str);
							}
							
						}
				 })
			}
			
			//位置筛选
			$("#weizhiselect").on('change',function(){
				selectWeizhi($("#weizhiselect option:selected").attr('tid'));
			})
			

			$("#h-table tr th [data-toggle='tooltip']").tooltip();  
			$("table th,table td").css('text-align','center')
			$("table th,table td").css({'width':'120px','min-width':'90px'});
	    </script>
	</my_script>
</body>
</html>