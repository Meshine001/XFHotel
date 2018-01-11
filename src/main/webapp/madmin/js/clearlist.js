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
			data:{},
			async: false,
			url:'/admin/Clean',
			success:function(data){
				console.log(data);
			
				$(".content .houseList ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><div class="row"><span>房间：'+data.content[i].roomId+'</span></div><p>服务内容：'+data.content[i].content+'</p><p>打扫时间：'+data.content[i].cleanTime+'</p><p>下单时间：'+data.content[i].time+'</p><p>订单状态：'+data.content[i].status+'</p><p>备注：'+data.content[i].demand+'</p>';
					if(data.content[i].status=='等待管理员呼叫保洁'){
						ai+='<p class="cz-btn">操作：<span class="comfirm-order" data-id="'+data.content[i].id+'">确认服务</span></p>'
					}else if(data.content[i].status=='正在清扫'){
						ai+='<p class="cz-btn">操作：<span class="comfirm-success" data-id="'+data.content[i].id+'">确定完成</span></p>'
					}
					ai+='</li>';
				}
				$(".content .houseList ul").append(ai);
			}
		})
	};
	

	//确认订单
	$(".houseList ul").on('click','li .cz-btn .comfirm-order',function(){
		var url = '../order/cleanOrder';
		var id = $(this).attr('data-id');
		console.log(id)
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
	$(".houseList ul").on('click','li .cz-btn .comfirm-success',function(){
		var url = '../order/cleanOrders';
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
	
	

	
	

	
	
    
});
