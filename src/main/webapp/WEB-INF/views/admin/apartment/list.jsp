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
				

				
				
			<!-- 房屋列表 -->	
				<div style="overflow: hidden;width:100%;height:auto">
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
					    <div  class="col-md-2 col-xs-6">
					    	<a class="btn btn-success " href="<%=basePath%>/admin/apartment/add">添加新房源</a>
					    </div>
					 </div>   

				<div class="row" style="clear:both;padding:15px;" id="h-table">


<!-- 
					<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 hb-item">
						<div class="think thumbnail">
							<asdie ><input type = "checkbox"name = "chkItem" ></asdie>
							<div class="hb-img"><img src="..." alt=""></div>
							<div class="hb-info">
								<p>位置：城东福邸铭门</p>
								<p>房号：1315</p>
								<p>价格：398</p>
								<p>地址：幸福中路69号</p>
							</div>
							<div class="clear"></div>
							<ul>
								<li><a href="javascript:;" class="">查看详情</a></li>
								<li><a href="javascript:;" class="">查看详情</a></li>
								<li><a href="javascript:;" class="">查看详情</a></li>
								<li><a href="javascript:;" class="">查看详情</a></li>
								<li><a href="javascript:;" class="">查看详情</a></li>
								<li style="padding-top:5px;">
									<div class="paixu">
									<input type="text" placeholder="">
									<a class="stb">排序</a>
									</div>
								</li>
							</ul>
						</div>
					</div>
 -->


									
				</div>
				
				</div>
			<!-- 房屋列表 -->
			
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
				console.log('userType='+userType)
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
								_str+='<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 hb-item">';
								_str+='<div class="think thumbnail">';//<asdie ><input type = "checkbox"name = "chkItem" roomId='+data.content[j].id+'></asdie>
								_str+='<div class="hb-img"><img src="'+'<%=basePath%>/images/'+data.content[j].fang_jian_tu[0]+'"></div>';
								_str+='<div class="hb-info"><p>位置：'+data.content[j].position.xa_wei_zhi+'-'+data.content[j].position.xiao_qu+'</p><p>房号：'+data.content[j].position.men_pai+'</p><p>价格：'+data.content[j].basic_info.jia_ge+'</p><p>地址：'+data.content[j].position.bd_wei_zhi+'</p></div><div class="clear"></div>';
								_str+='<ul>';
								_str+='<li><a class="btn-success" title="编辑详情" href="/admin/apartment/update/'+data.content[j].id+'">编辑详情</a></li>'
/* 								if(data.content[j].show_home==true){
									_str+='<li><a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn-success" title="隐藏/显示" >首页隐藏</a></li>';
								}else{
									_str+='<li><a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn-success" title="隐藏/显示" >首页显示</a></li>';
								}
								_str+='<li><a href="javascript:;" title="删除" class="btn-danger" roomid='+data.content[j].id+'>删除房源</a></li>';
								_str+='<li><a href="/admin/leavemsglist"  title="查看评论" class="btn-info evalpinglun" roomid='+data.content[j].id+'>查看评论</a></li>'*/
								_str+='<li><a href="/admin/status/?id='+data.content[j].id+'" title="房态设置" class="btn-warning houseStatus" roomid='+data.content[j].id+'>价格房态</a></li>';
								/* _str+='<li style="padding-top:5px;"><div class="paixu"><input type="text" placeholder="'+data.content[j].sort+'"><a class="stb" roomid='+data.content[j].id+'>排序</a></div></li>'  */
								_str+='</ul></div></div>';
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
				
				$("#h-table").on('click','.evalpinglun,.houseStatus',function(){//查看评论
					var roomid=$(this).attr('roomid');
					window.sessionStorage.setItem('roomId',roomid);
				})
<!--
$("#h-table").on('click','tr .price_s',function(){
	var roomid=$(this).parent().parent().attr('roomid');
	window.sessionStorage.setItem('roomId',roomid);
})

-->
				
				//排序
				$("#h-table").on('click','.paixu .stb',function(){
					var roomid=$(this).attr('roomid');
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
				
				
					
					var roomIddel="";//删除房源
					$("#h-table").on('click','.btn-danger',function(){
						roomIddel=$(this).attr('roomid');
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
			                $("#h-table  input[name = chkItem]:checkbox").each(function () {
			                    if ($(this).is(":checked")) {
			                        result.push($(this).attr('roomId'));
			                    }
			             	});
			                roomids=result.join(",");
			    			console.log(roomids)
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
			                		location=location;
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
                $("#h-table  input[name = chkItem]:checkbox").each(function () {
                    if ($(this).is(":checked")) {
                        result.push($(this).attr('roomId'));
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
				 $.ajax({
					 	type:'POST',
						dataType:'json',
						data:{'wei':value},
						url:'/admin/user/getRoom/',
						success:function(data){
						//	console.log(data);
							if(data.statusCode==1){
								$("#h-table").html("");
								var _str="";
								for(var j=0;j<data.content.length;j++){
									_str+='<div class="col-lg-3 col-md-6 col-sm-6 col-xs-12 hb-item">';
									_str+='<div class="think thumbnail"><asdie ><input type = "checkbox"name = "chkItem" roomId='+data.content[j].id+'></asdie>';
									_str+='<div class="hb-img"><img src="'+'<%=basePath%>/images/'+data.content[j].fang_jian_tu[0]+'"></div>';
									_str+='<div class="hb-info"><p>位置：'+data.content[j].position.xa_wei_zhi+'</p><p>房号：'+data.content[j].position.men_pai+'</p><p>价格：'+data.content[j].basic_info.jia_ge+'</p><p>地址：'+data.content[j].position.bd_wei_zhi+'</p></div><div class="clear"></div>';
									_str+='<ul>';
									_str+='<li><a class="btn-success" title="编辑详情" href="/admin/apartment/update/'+data.content[j].id+'">编辑详情</a></li>'
									if(data.content[j].show_home==true){
										_str+='<li><a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn-success" title="隐藏/显示" >首页隐藏</a></li>';
									}else{
										_str+='<li><a href="/admin/apartment/showHome/'+data.content[j].id+'" class="btn-success" title="隐藏/显示" >首页显示</a></li>';
									}
									_str+='<li><a href="javascript:;" title="删除" class="btn-danger" roomid='+data.content[j].id+'>删除房源</a></li>';
									_str+='<li><a href="/admin/leavemsglist"  title="查看评论" class="btn-info evalpinglun" roomid='+data.content[j].id+'>查看评论</a></li>'
									_str+='<li><a href="/admin/status/?id='+data.content[j].id+'" title="房态设置" class="btn-warning houseStatus" roomid='+data.content[j].id+'>价格房态</a></li>';
									_str+='<li style="padding-top:5px;"><div class="paixu"><input type="text" placeholder="'+data.content[j].sort+'"><a class="stb" roomid='+data.content[j].id+'>排序</a></div></li>'
									_str+='</ul></div></div>';
								}
								if(data.content.length<=0){
									_str='<p style="width:100%;text-align:center;line-height:60px;">该地区暂时没有房源、点我<a style="color:#f18c0b" href="/admin/apartment/add">添加新房源</a>吧。</p>';
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