$(document).ready(function(){
	var id=fnBase.huoqu(0,'uid'); // 0:超管   1：普通管理员 
	var userType=fnBase.huoqu(0,'userType');
	var pd="";
	
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

	
	function ymd(str){
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ oMonth +'-'+oDay;
        return oTime; 
	}
	
	// 房东列表
	masglistData();
	function masglistData(){
		$.ajax({
			type:'post',
			dataType:'json',
			data:{'id':id},
			async: false,
			url:'/admin/Collocation2',
			success:function(data){
				console.log(data);
				$(".content .houseList:eq(0) ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><p>姓名：'+data.content[i].name+'</p><p>电话号码：'+data.content[i].phone+'</p><p>收款账号：'+data.content[i].number+'</p><p class="cz-btn">操作：<span>查看房屋</span><span>删除</span></p></li>';
				}
				$(".content .houseList:eq(0) ul").append(ai);
			}
		})
	};
	


	
    //订单关联相关服务订单统计
    
//    $(".content .houseList:eq(0) ul").on('click','li',function(event){
//		event.stopPropagation();
//		$('#maskShow').show()
//		$('#module-box').addClass('hover')
//		getinfo($(this).attr('data-id'))//住房订单
//		getClean($(this).attr('data-id'))//保洁订单
//		getcar($(this).attr('data-id'))//叫车订单
//		getfault($(this).attr('data-id'))//维修订单
//		addsheshi($(this).attr('data-id'))//添加设施订单
//	});
	
	
	//查看房东的房子
	$(".content .houseList:eq(0) ul").on('click','li .cz-btn span:first-child',function(event){
		event.stopPropagation();
		$('#maskShow').show()
		$('#module-box').addClass('hover')
		getinfo($(this).parent().parent().attr('data-id'))
	});
	
	function getinfo(uid){
		var url = '../mobile/particulars/';
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : uid
			},
			url : url,
			success : function(data) {
				console.log(data)
				var str="";
				$(".module-con ul").html("");
				for(var i=0;i<data.length;i++){
					str+='<li><p>名称：'+data[i].position.xa_wei_zhi+'-'+data[i].position.xiao_qu+'-'+data[i].position.men_pai+'</p><p>地址：'+data[i].position.bd_wei_zhi+'-'+data[i].position.jie_dao+'</p><p>价格：'+data[i].basic_info.jia_ge+'元/天</p></li>';
				}
				if(data.length<=0){
					str='<li><p>暂时没有房屋</p></li>'
				}
				$(".module-con ul").append(str);
			}
		});
	}
	
	//删除房东
	$(".content .houseList:eq(0) ul").on('click','li .cz-btn span:last-child',function(event){
		event.stopPropagation();
		var url = '../landlord/deleteLandlord';
		var id = $(this).parent().parent().attr('data-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : id
			},
			url : url,
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
		
	});
	
	
	//房东申请
	masglistData1()
	function masglistData1(){
		$.ajax({
			type:'post',
			dataType:'json',
			data:{},
			async: false,
			url:'/admin/Collocation1',
			success:function(data){
				console.log(data);
				$(".content .houseList:eq(1) ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><p>姓名：'+data.content[i].name+'</p><p>电话号码：'+data.content[i].tel+'</p><p>房屋地址：'+data.content[i].site+'</p><p>申请时间：'+data.content[i].time+'</p><p>状态：'+data.content[i].state+'</p>';
					if(data.content[i].state=='等待处理'){
						ai+='<p class="cz-btn">操作：<span order-id="'+data.content[i].id+'" class="comfirm-order">进行审核</span></p>'
					}else if(data.content[i].state=='审核中'){
						ai+='<p class="cz-btn">操作：<span  order-id="'+data.content[i].id+'"  class="success-order">成功</span  order-id="'+data.content[i].id+'"  class="success-order"><span>失败</span></p>'
					}
					ai+='</li>'
				}
				$(".content .houseList:eq(1) ul").append(ai);
			}
		})
	};
	
	//确认审核
	$(".content .houseList:eq(1) ul").on('click','li .comfirm-order',function(){
		var url = '../landlord/ApplyOrder';
		var id = $(this).attr('order-id');
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
			//	console.log(data)
				if(data.statusCode == 1){
					fnBase.myalert(data.content);
					setTimeout(function(){location=location;},1550)
				}else{
					fnBase.myalert(data.content);
				}
			}
		});
	});
    
	//审核成功
	$(".content .houseList:eq(1) ul").on('click','li .success-order',function(){
		var url = '../landlord/FacilityOrders';
		var id = $(this).attr('order-id');
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : id,
				'judge':$(this).attr('type')
			},
			url : url,
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
	});
	
	
	
});
