$(document).ready(function(){
	var id=fnBase.huoqu(0,'uid'); // 0:超管   1：普通管理员 
	var userType=fnBase.huoqu(0,'userType');
	var pd="";
//	console.log(id+'_'+userType)
	
	$(".layer-select a").click(function(){
		$(this).addClass('show').siblings().removeClass('show');
		var i=$(this).index();
		if(i==0){
			$(".content .houseList").eq(0).show();
			$(".content .houseList").eq(1).hide();
		}else{
			$(".content .houseList").eq(0).hide();
			$(".content .houseList").eq(1).show();
		}
	})

	
	// userType = 0       ==> 超管总订单
	// userType = 1       ==>  根据用户属性查他的订单
	
	function orderStatus(cp){
	    if(cp=='2'){
    		pd='进行中'
    	}else if(cp=='1'){
    		pd='等待支付'
    	}else if(cp=='3'){
    		pd='已完成'
    	}else if(cp=='5'){
    		pd='超时'
    	}else if(cp=='4'){
    		pd='删除订单'
    	}else if(cp=='6'){
    		pd='退款'
    	}else if(cp=='7'){
    		pd='需要管理员确认'
    	}else if(cp=='8'){
    		pd='退房等待管理员确认'
    	}
	    return pd;
	}
	
	function ymd(str){
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ oMonth +'-'+oDay;
        return oTime; 
	}
	
	masglistData();
	function masglistData(){
		$.ajax({
			type:'post',
			dataType:'json',
			data:{'id':id},
			async: false,
			url:'/admin/user/stewardO',
			success:function(data){
				console.log(data);
				$(".layer-select a:eq(0) i").html('('+data.content.length+'/条)');
				$(".content .houseList:eq(0) ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><div class="row"><span class="fl">订单号：'+data.content[i].id+'</span><span class="fl">下单人：'+data.content[i].cusName+'</span></div><p>房间：'+data.content[i].description+'</p><p>时间：'+ymd(data.content[i].startTime)+'至'+ymd(data.content[i].endTime)+'</p><p>状态：'+orderStatus(data.content[i].status)+'</p>';
					if(data.content[i].status==7){
						ai+='<p class="cz-btn">操作：<span class="comfirm-order" data-id="'+data.content[i].id+'">确认订单</span><span class="close-order" data-id="'+data.content[i].id+'">关闭订单</span></p>'
					}else if(data.content[i].status==1){
						ai+='<p class="cz-btn">操作：<span class="comfirm-price" data-id="'+data.content[i].id+'">修改价格</span></p>'
					}else if(data.content[i].status==2){
						ai+='<p class="cz-btn">操作：<span class="comfirm-sendpwd" data-id="'+data.content[i].id+'">发送密码</span></p>'
					}else if(data.content[i].status==8){
						ai+='<p class="cz-btn">操作：<span class="comfirmOutLease-order" data-id="'+data.content[i].id+'">确认退租</span></p>'
					}
					ai+='</li>';
				}
				$(".content .houseList:eq(0) ul").append(ai);
			}
		})
	};
	
	
	//发送密码
	$(".content .houseList:eq(0) ul").on('click','li .comfirm-sendpwd',function(event){
		event.stopPropagation();
		var url = '/order/comfirmPw';
		var id = $(this).attr('data-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : id,
			},
			url : url,
			success : function(data) {
				console.log(data)
				alert(data.content);
			}
		});
	});
	
	
	//确认订单

	$(".content .houseList:eq(0) ul").on('click','li .comfirm-order',function(event){
		event.stopPropagation();
		var url = '../order/comfirm';
		var id = $(this).attr('data-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : id,
			},
			url : url,
			success : function(data) {
			//	console.log(data)
				if(data.statusCode == 1){
					window.location.href = '../admin/order';
				}else{
					alert(data.content);
				}
			}
		});
	});
	
	

	//关闭订单

	$(".content .houseList:eq(0) ul").on('click','li .close-order',function(event){
		event.stopPropagation();
		var url = '../order/close';
		var id = $(this).attr('data-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : id,
			},
			url : url,
			success : function(data) {
				if(data.statusCode == 1){
					window.location.href = '../admin/order';
				}else{
					alert(data.content);
				}
			}
		});
	});
	
	
	//退租确认订单

	$(".content .houseList:eq(0) ul").on('click','li .comfirmOutLease-order',function(event){
		event.stopPropagation();
		var url = '../order/comfirmOutLease';
		var id = $(this).attr('data-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {'id' : id},
			url : url,
			success : function(data) {
			//	console.log(data)
				if(data.statusCode == 1){
					window.location.href = '../admin/order';
				}else{
					alert(data.content);
				}
			}
		});
	});
	
	//第三方订单
	var page=1,totalPage="";
	otherorder(page);
	function otherorder(page){
    	$.ajax({
    		type:'POST',
    		dataType:'JSON',
    		url:'/admin/getListRests',
    		data:{
    			'page':page
    		},
    		success:function(data){
    			console.log(data)
    			totalPage=data.content.pageCount;
    			if(totalPage>=2){
    				$(".content .houseList:eq(1) .page").show();
    			}
    			
    			var $src="";
    			$(".content .houseList:eq(1) ul").html('');
    			for(var i=0;i<data.content.results.length;i++){
    				$src+='<li><div class="row"><span class="fl">来源：'+data.content.results[i].source+'</span><span class="fl">收入：'+data.content.results[i].sum+'元</span></div><p>房间：'+data.content.results[i].roomName+'</p><div class="row"><span class="fl">入住：'+data.content.results[i].startTime+'</span><span class="fl">离开：'+data.content.results[i].endTime+'</span></div><div class="row"><span class="fl">姓名：'+data.content.results[i].name+'</span><span class="fl">电话：'+data.content.results[i].tel+'</span></div><p class="cz-btn">操作：<span oid="'+data.content.results[i].id+'" class="removeOrder" style="cursor: pointer">删除</span></p></li>';
					if(data.content.results.length<=0){
						$src='<p style="text-center;padding-top:0.35rem">暂无订单</p>'
					}
    				
    			}
    			$(".content .houseList:eq(1) ul").append($src);
    		}
    	})
		
	}
	
	
	$(".content .houseList:eq(1) .page a:eq(0)").click(function(){
    	if(page>1){
        	page--;
        	otherorder(page);
    	}
    })
    
   $(".content .houseList:eq(1) .page a:eq(1)").click(function(){
    	if(page<totalPage){
        	page++;
        	otherorder(page);
    	}
    })
	
    //删除第三方订单
    $(".content .houseList:eq(1) ul").on('click','li .removeOrder',function(){
    	$.ajax({
    		type:'POST',
    		dataType:'json',
    		url:'/admin/deleteRests',
    		data:{
    			'id':$(this).attr('oid')
    		},
    		success:function(data){
    			if(data.statusCode==1){
    				alert(data.content)
    			}else{
    				alert(data.content)
    			}
    			location=location;
    		}
    	})
    })
	
    //订单关联相关服务订单统计
    
    $(".content .houseList:eq(0) ul").on('click','li',function(event){
		event.stopPropagation();
		$('#maskShow').show()
		$('#module-box').addClass('hover')
		getinfo($(this).attr('data-id'))//住房订单
		getClean($(this).attr('data-id'))//保洁订单
		getcar($(this).attr('data-id'))//叫车订单
		getfault($(this).attr('data-id'))//维修订单
		addsheshi($(this).attr('data-id'))//添加设施订单
	});
	
	
	//住房订单详情
	function getinfo(uid){
		 $.ajax({
				type:'POST',
				dataType:'json',
				url:'/mobile/getOrder',
				data:{'id':uid},
				success:function(data){
					console.log(data);
					var pd="";
					var unixTimestamp = new Date( data[1].time ) ;
					commonTime = unixTimestamp.toLocaleString();
					 
					var dj=data[1].price.split('@');
					console.log(dj)
					/*计算总价*/
					var total=eval(dj.join("+"));
					console.log(total)
					/*押金*/
				    var jajin=Number(dj[dj.length-1]);
				   
				    /*总房费 */
					var _price=total-jajin;
				    /*优惠卷*/
				    var yhj=total-Number(data[1].totalPrice);
				    yhj=Number(yhj).toFixed(0);
				    if(yhj != 0){
				    	yhj==yhj;
				    }else{
				    	yhj=0;
				    }
				 
				    if(data[1].status=='2'){
		        		pd='进行中'
		        	}else if(data[1].status=='1'){
		        		pd='等待支付'
		        	}else if(data[1].status=='3'){
		        		pd='已完成'
		        	}else if(data[1].status=='5'){
		        		pd='超时'
		        	}else if(data[1].status=='4'){
		        		pd='删除订单'
		        	}else if(data[1].status=='6'){
		        		pd='退款'
		        	}else if(data[1].status=='7'){
		        		pd='需要管理员确认'
		        	}else if(data[1].status=='8'){
		        		pd='退房等待管理员确认'
		        	}
				    
				    
				    
				    $("#zfhouse").html('');
				    var det=data[1].description
				    var kule='<tr><td>预订房屋：<span>'+det.replace(/-undefined-/,"-")+
				              '</span></td></tr><tr><td class="fl50">入住时间：<span>'+data[0]+'</span></td><td>离开时间：<span>'+data[2]+
				              '</span></td></tr><tr><td class="fl50">天数：<span>'+data[1].totalDay+'</span></td><td>订单状态：<span>'+pd+
				              '</span></td></tr><tr><td>下单时间：<span>'+ymd(data[1].time)+'</span></td></tr><tr><td>入住人：<span>'+data[1].cusName+
				              '</span></td><tr><td>备注：<span>'+data[1].personal+'</span></td></tr></tr><tr><td class="fl50">联系电话：<span>'+data[1].cusTel+
				              '</span></td><td>单价：<span>￥'+dj[0]+'</span></td></tr><tr><td class="fl50">总房费：<span>￥'+Number(data[1].totalPrice).toFixed(0)+'</span></td><td>押金：<span>￥'+jajin+
				              '</span></td></tr><tr><td class="fl50">优惠卷：<span>减'+data[1].preferential+
				              '元</span></td><td>合计：<span>￥'+Number(data[1].totalPrice).toFixed(0)+'</span></td></tr>';
				    
				    
				    $("#zfhouse").append(kule)
				    
				    
				}
				
		 })
	}
	
	
	
	//保洁订单
	
	function getClean(uid){
		  $.ajax({
				type:'POST',
				dataType:'json',
				url:'/admin/getClean',
				data:{'oederId':uid},
				success:function(data){
				//	console.log(data);
					$("#clean").html('');
				    var sty="",stuse="";
					for(var i=0;i<data.content.length;i++){
						new Date( data.content[i].time ).toLocaleString();
						
						if(data.content[i].status==0){
							stuse='等待保洁';
						}else if(data.content[i].status==1){
							stuse='正在打扫';
						}else if(data.content[i].status==2){
							stuse='完成';
						}
						
						sty+='<li id="'+data.content[i].id+'"><table><tr><td style="width:100%">下单时间：<span>'+new Date( data.content[i].time ).toLocaleString()+'</span></td></tr><tr><td class="fl50">订单状态：<span>'+stuse+'</span></td><td class="fl50">打扫时间：<span>'+data.content[i].cleanTime+'</span></td></tr><tr><td style="width:100%">服务内容：<span>'+data.content[i].content+'</span></td></tr></table></li>'
						
					}
					if(data.content.length<=0){
						sty='<p style="color:red">暂无订单</p>'
					}
					$("#clean").append(sty);
					
				}
		  });
	}
	
	
	
	//叫车订单
	function getcar(uid){
		  $.ajax({
				type:'POST',
				dataType:'json',
				url:'/mobile/getTripOrder',
				data:{'id':uid},
				success:function(data){
				//	console.log(data)
					$("#usercar").html('');
					var _Str="",st="";
					for(var i=0;i<data.length;i++){
				 		if(data[i].status=='0'){
		        			st='未支付'
			        	}else if(data[i].status=='1'){
			        		st='进行中'		
			        	}else if(data[i].status=='2'){
			        		st='完成订单'		
			        	}else if(data[i].status=='3'){
			        		st='超时'
			        	}else if(data[i].status=='4'){
			        		st='退款'
			        	}else if(data[i].status=='5'){
			        		st='需要管理员确认'
			        	}
						_Str+='<li id="'+data[i].id+'"><table><tr><td style="width:100%">下单时间：<span>'+new Date( data[i].time ).toLocaleString()+'</span></td></tr><tr><td class="fl50">服务类型：<span>'+data[i].classify+'</span></td><td class="fl50">服务内容：<span>'+data[i].tripId+'</span></td></tr><tr><td style="width:100%">开始时间：<span>'+new Date( data[i].startTime ).toLocaleString()+'</span></td><td style="width:100%">结束时间：<span>'+new Date( data[i].endTime ).toLocaleString()+'</span></td></tr><tr><td class="fl50">车型：<span>'+data[i].site+'</span></td><td class="fl50">价格：<span>'+data[i].price+'</span></td></tr><tr><td class="fl50">订单状态：<span>'+st+'</span></td><td class="fl50">联系电话：<span>'+data[i].tel+'</span></td></tr></table></li>'
					}
					if(data.length<=0){
						_Str='<p style="color:red">暂无订单</p>'
					}
					$("#usercar").append(_Str);
				}
		  })
	}
	
	
	
	
	function getfault(uid){
	  	 /*维修订单*/
		  $.ajax({
				type:'POST',
				dataType:'json',
				url:'/mobile/getFault',
				data:{'id':uid},
				success:function(data){
			//		console.log(data)
					$("#getFault").html('');
					var stuse="",_str="";
			
					for(var i=0;i<data.length;i++){
						if(data[i].status=='0'){
							stuse='等待管理员呼叫维修'	
			        	}else if(data[i].status=='1'){
			        		stuse='正在维修'		
			        	}else if(data[i].status=='2'){
			        		stuse='已完成'		
			        	}
						_str+='<li id="'+data[i].id+'"><table><tr><td style="width:100%">下单时间：<span>'+new Date( data[i].time ).toLocaleString()+'</span></td></tr><tr><td style="width:100%">房间地址：<span>'+data[i].roomId+'</span></td></tr><tr><td style="width:100%">订单状态：<span>'+stuse+'</span></td><td>维修时间：<span>'+data[i].maintainTime+'</span></td></tr><tr><td style="width:100%">服务内容：<span>'+data[i].faultItem+'</span></td></tr></table></li>'
					}
					if(data.length<=0){
						_str='<p style="color:red">暂无订单</p>'
					}
					$("#getFault").html(_str);
					
				}
				
		  })
	}
	
	
	
	function addsheshi(uid){
		
	  	 /*添加订单*/
		 $.ajax({
				type:'POST',
				dataType:'json',
				url:'/mobile/getFacility',
				data:{'id':uid},
				success:function(data){
			//		console.log(data)
					$("#addsheshi").html('');
					var st="",_str="";
					for(var i=0;i<data.length;i++){
		       		if(data[i].status=='0'){
		       			st='等待管理员添加'	
			        	}else if(data[i].status=='1'){
			        		st='正在路上'		
			        	}else if(data[i].status=='2'){
			        		st='已完成'		
			        	}else if(data[i].status=='3'){
			        		st='等待支付';
			        	}else if(data[i].status=='4'){
			        		st='超时'		
			        	}
		       		_str+='<li id="'+data[i].id+'"><table><tr><td style="width:100%">下单时间：<span>'+new Date( data[i].time ).toLocaleString()+'</span></td></tr><tr><td style="width:100%">房间地址：<span>'+data[i].roomId+'</span></td></tr><tr><td class="fl50">订单状态：<span>'+st+'</span></td><td class="fl50">价格：<span>'+data[i].price+'</span></td></tr><tr><td class="fl50">物品名称：<span>'+data[i].facility+'</span></td><td class="fl50">添加时间：<span>'+data[i].addTime+'</span></td></tr></table></li>'
		       	}
					if(data.length<=0){
						_str='<p style="color:red">暂无订单</p>'
					}
					$("#addsheshi").append(_str);
				}
				
		 })
	}
	
	
    
});
