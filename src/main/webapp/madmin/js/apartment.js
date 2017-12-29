  (function(){
	var userType=window.localStorage.getItem('userType');//1:管理员 ,0:超级管理员
	var uid=window.localStorage.getItem('uid');



	$(".houseList ul").on('click','li .hbtn .selected',function(){
		if($(this).hasClass('show')==false){
			$(this).addClass('show')
		}else{
			$(this).removeClass('show')
		}
	})

	
	if(userType==1){
		$.ajax({
			type:"post",
			dataType:"json",
			url:baseUrl+'/admin/user/steward',
			data:{'id':uid},
			success:function(data){
				console.log(data)
				
			}
		});
	}else if(userType==0){ 
		selectWeizhi(5)
	}
	

			function selectWeizhi(value){
				 $.ajax({
					 	type:'POST',
						dataType:'json',
						data:{'wei':value},
						url:baseUrl+'/admin/user/getRoom/',
						success:function(data){
							console.log(data);
								
							$(".houseList ul").html('');
							var str="";
							for(var i=0;i<data.content.length;i++){
					        	var vio=data.content[i].position.bd_wei_zhi;
			 			     	vio=vio.slice(4,vio.length);
			 			     	str+='<li proID='+data.content[i].id+'><div class="hinfo"><div class="infoimg"><img src="'+baseUrl+"/images/"+data.content[i].fang_jian_tu[0]+'"></div><div class="infocon"><p>位置：'+data.content[i].position.xa_wei_zhi+'</p><p>小区：'+data.content[i].position.xiao_qu+'</p><p>价格：<i style="color:red">'+data.content[i].basic_info.jia_ge+'/晚</i></p><p>地址：'+vio+'</p></div></div>';
			 			     	if(userType=='0'){

			 			     		if(data.content[i].show_home==true){
			 			     			str+='<div class="hbtn"><a class="fn-edit">编辑详情</a><a class="fn-ifshow"  roomid='+data.content[i].id+'>首页隐藏</a><a class="fn-remove" roomid='+data.content[i].id+'>删除房屋</a><a class="fn-look">查看评论</a><a class="fn-price" href="Roomstatus.html?id='+data.content[i].id+'">价格房态</a><a><input type="text"class="paixu" placeholder="'+data.content[i].sort+'"><input type="button"value="排序" class="btn-px" roomid='+data.content[i].id+'></a><a class="selected">选择<i></i></a></div>'
									}else{
										str+='<div class="hbtn"><a class="fn-edit">编辑详情</a><a class="fn-ifshow"  roomid='+data.content[i].id+'>首页显示</a><a class="fn-remove" roomid='+data.content[i].id+'>删除房屋</a><a class="fn-look">查看评论</a><a class="fn-price" href="Roomstatus.html?id='+data.content[i].id+'">价格房态</a><a><input type="text"class="paixu" placeholder="'+data.content[i].sort+'"><input type="button"value="排序" class="btn-px" roomid='+data.content[i].id+'></a><a class="selected">选择<i></i></a></div>'
									}
			 			     		
			 			     	}else if(userType=='1'){
			 			     		str+='<div class="hbtn"><a class="fn-edit">编辑详情</a><a class="fn-price" href="Roomstatus.html?id='+data.content[i].id+'">价格房态</a></div>';
			 			     	}
			 			     	str+='</li>';
							}
							if(data.content.length<=0){
								str='<p style="font-size:0.14rem">该地区没有房屋</p>'
							}
							$(".houseList ul").append(str);
						}
				 })
			}

			//位置筛选
			$("#weizhiselect").on('change',function(){
				selectWeizhi($("#weizhiselect option:selected").attr('tid'));
			})		
				
				//排序
			$(".houseList ul").on('click','li .hbtn a .btn-px',function(){
					var roomid=$(this).attr('roomid');
					var sort=$(this).parent().find('.paixu').val();
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
			
			//删除房屋
			$(".houseList ul").on('click',' li .hbtn .fn-remove',function(){
				var roomId=$(this).attr('roomid');
				$.ajax({
					type:'get',
					dataType:'json',
					url:'/admin/apartment/delete/'+roomId,
					success:function(data){
						location=location
					}
				})
			}) 

			//隐藏显示
			$(".houseList ul").on('click',' li .hbtn .fn-ifshow',function(){
				var roomId=$(this).attr('roomid');
				$.ajax({
					type:'get',
					dataType:'json',
					url:'/admin/apartment/showHomell/'+roomId
				})
				location=location;
			}) 
			
  })()