$(document).ready(function(){
	getOrder()
	setopintern()
	function setopintern(){
		$('.layer-select .layer-nav').click(function(){
			var i=$(this).index();
			$(this).addClass('show').siblings('.layer-nav').removeClass('show')
			$('.layer-select .navbar-car').eq(i).slideToggle().siblings('.navbar-car').hide();
			if($('.layer-select .navbar-car').is(":hidden")==true){
				$('.layer-select .layer-nav').removeClass('show')
			}
		})
		
		$(".navbar-car .tab li").click(function(){
			 var i= $(this).index();
			 $(this).addClass('ac').siblings().removeClass('ac')
			 $(".addTab .scel").eq(i).show().siblings('.addTab .scel').hide();
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
							jslist+='<li id="'+data[i].id+'" price="'+data[i].price+'"><span>'+data[i].place+'</span><span>'+data[i].price+'</span><span class="btn-danger" rid="'+data[i].id+'">删除</span></li>';
						}else if(data[i].classify==1){
							bclist+='<li id="'+data[i].id+'" price="'+data[i].price+'"><span>'+data[i].place+'</span><span>'+data[i].price+'</span><span class="btn-danger" rid="'+data[i].id+'">删除</span></li>';
						}
						
					};
					$("#jiesongList").append(jslist);
					$("#baocheList").append(bclist);
				}
			});
	  	}
	    locadjiesong();
	    
	    
	  	//删除服务
		   $("#jiesongList,#baocheList").on('click','li .btn-danger',function(){
		    	var id=$(this).attr('rid');
		    	$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'id':id
					},
					url : '/site/deleteSite/',
					success : function(data) {
					//	console.log(data);
						if(data.statusCode == 1){
							fnBase.myalert(data.content);
							 setTimeout(function(){location=location;},1550)
						}else{
							fnBase.myalert(data.content);
						}
					}
				});
		    	
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
					//	console.log(data)
						if(data.statusCode == 1){
							fnBase.myalert(data.content);
							 setTimeout(function(){location=location;},1550)
						}else{
							fnBase.myalert(data.content);
						}
					}
				});
	    	}
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
					//	console.log(data)
						if(data.statusCode == 1){
							fnBase.myalert(data.content);
							 setTimeout(function(){location=location;},1550)
						}else{
							fnBase.myalert(data.content);
						}
					}
				});
	    		
	    	}
	    })
	    
	  
	    
	    
	    
	    
	}
	
	
	
	
	
	
	
	
	
	function getOrder(){
	    var frontURL=baseUrl+'/admin/DialogueCar1';
	    var postData={};
	    fnBase.commonAjax(frontURL,postData,function(data){
	    	console.log(data)
	    	var str="";
	    	$(".content .houseList ul").html('');
	    	for(var i=0;i<data.content.length;i++){
	    		str+='<li data-id="'+data.content[i].id+'"><p>房间：'+data.content[i].roomName+'</p><p>下单时间：'+data.content[i].time+'</p><div class="row"><span class="fl">类型：'+data.content[i].classify+'</span><span class="fl">状态：'+data.content[i].status+'</span></div><div class="row"><span class="fl">服务：'+data.content[i].tripId+'</span><span class="fl">车型：'+data.content[i].site+'</span></div><div class="row"><span>开始时间：'+data.content[i].startTime+'</span></div><div class="row"><span>结束时间：'+data.content[i].endTime+'</span></div><div class="row"><span>结束时间：'+data.content[i].price+'元</span></div><div class="row"><span>备注：'+data.content[i].demand+'</span></div>';
				if(data.content[i].status=='确认中'){
					str+='<p class="cz-btn">操作：<span class="comfirm-order" data-id="'+data.content[i].id+'">确认服务</span></p>'
				}else if(data.content[i].status=='进行中'){
					str+='<p class="cz-btn">操作：<span class="comfirm-success" data-id="'+data.content[i].id+'">确定完成</span></p>'
				}
	    		str+='</li>'
	    	}
	    	
	    	$(".content .houseList ul").append(str);
	    })
	    
		//确认订单
	$(".content .houseList ul").on('click','.comfirm-order',function(){
			var url = '../order/FacilityOrder';
			var id = $(this).attr('data-id');
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
						location=location
					}else{
						alert(data.content);
					}
				}
			});
		});
	    
		//关闭订单
	    $(".content .houseList ul").on('click','.comfirm-success',function(){
			var url = '../order/FacilityOrders';
			var id = $(this).attr('data-id');
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
						location=location
					}else{
						alert(data.content);
					}
					
				}
			});
		});
	    
	    
	    
	}
	
	
	
	
	
})