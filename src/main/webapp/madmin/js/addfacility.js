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
						srt+='<li id="'+data[i].id+'" price="'+data[i].price+'"><span>'+data[i].name+'</span><span>免费</span><span class="removeid" rid="'+data[i].id+'">删除</span></li>';
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
						srt+='<li id="'+data[i].id+'" price="'+data[i].price+'"><span>'+data[i].name+'</span><span>'+data[i].price+'</span><span class="removeid" rid="'+data[i].id+'">删除</span></li>';
					};
					$("#shoufeiList").append(srt);
				}
			});
	  	}
	    
	    locadShoufeiSheshi();
	    
	    
    //删除收费
	    
	    $("#shoufeiList").on('click','li .removeid',function(){
	    	var id=$(this).attr('rid');
	    	$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id':id
				},
				url : '/facility/deleteFacility/',
				success : function(data) {
					//console.log(data)
					fnBase.myalert(data.content)
					 setTimeout(function(){location=location;},1550)
					
				}
			});
	    	
	    })
	    
	    
	    //删除免费
	    
	    $("#mianfeiList").on('click','li .removeid',function(){
	    	var id=$(this).attr('rid');
	    	$.ajax({
				type : 'POST',
				dataType : 'json',
				data : {
					'id':id
				},
				url : '/facility/deleteFacility/',
				success : function(data) {
					//console.log(data)
					fnBase.myalert(data.content)
					 setTimeout(function(){location=location;},1550)
				}
			});
	    	
	    })	    
	    
	   //免费
	    $("#mianfeiBtn").click(function(){
	    	var mianfeiName=$("#mianfeiName").val();
	    	if(mianfeiName==""){
	    		fnBase.myalert("请提交正确信息")
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
							fnBase.myalert(data.content);
							 setTimeout(function(){location=location;},1550)
						}else{
							fnBase.myalert(data.content);
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
	    var frontURL=baseUrl+'/admin/addfacility1';
	    var postData={};
	    fnBase.commonAjax(frontURL,postData,function(data){
	    	console.log(data)
	    	var str="";
	    	$(".content .houseList ul").html('');
	    	for(var i=0;i<data.content.length;i++){
	    		str+='<li data-id="'+data.content[i].id+'"><p>房间：'+data.content[i].roomId+'</p><p>下单时间：'+data.content[i].time+'</p><div class="row"><span class="fl">类型：'+data.content[i].classify+'</span><span class="fl">状态：'+data.content[i].status+'</span></div><div class="row"><span class="fl">设施：'+data.content[i].Facility+'</span><span class="fl">数天：'+data.content[i].fate+'天</span></div><div class="row"><span class="fl">价格：'+data.content[i].price+'元</span><span class="fl">添加时间：'+data.content[i].addTime+'</span></div>';
				if(data.content[i].status=='等待管理员确认'){
					str+='<p class="cz-btn">操作：<span class="comfirm-order" data-id="'+data.content[i].id+'">确认服务</span></p>'
				}else if(data.content[i].status=='正在路上'){
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