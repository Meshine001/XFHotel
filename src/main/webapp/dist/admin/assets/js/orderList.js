function getStatus(s) {
	var status = "";
	if (s == 1)
		status = "激活";
	if (s == 0)
		status = "冻结";
	return status;
};
function getOp(s) {
	var status = "";
	if (s == 0)
		status = "激活";
	if (s == 1)
		status = "冻结";
	return status;
};
function change(id, status,cp) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {
			'id' : id,
			'status' : status
		},
		url : "./change_status",// 请求的action路径
		error : function(e) {// 请求失败处理函数
			alert("更改失败！");
		},
		success : function(data) {
			list(cp);
		}
	});
};

function setItem(key,value) {
	window.sessionStorage.setItem(key,value);
};
function getItem(key) {
	return window.sessionStorage.getItem(key);
};





function list(page) {
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data : {
			'page' : page
		},
		url : "./order_page",
		error : function(e) {
			alert("嗷获取数据失败！");
		},
		success : function(data) {
	//		console.log(data)
			
			
			
//			分页
			$('#pagecontroller').html('');
			var a_f = $('<a></a>').attr('href','#').append('&laquo;').attr('onclick', 'list(1)').attr('href',
			'javascript:void(0);');
			var li_f = $('<li></li>').append(a_f);
			$('#pagecontroller').append(li_f);
			for (var i=data.sp;i<=data.ep;i++){
				var a_p = $('<a></a>').attr('href','#').append(i).attr('onclick', 'list(' + i + ')')
				.attr('href', 'javascript:void(0);');
				var li_p = $('<li></li>').append(a_p);
				if(i==data.currentPage)
					li_p.attr('class','active');
				$('#pagecontroller').append(li_p);
			}
			var a_l = $('<a></a>').attr('href','#').append('&raquo;').attr('onclick', 'list(' + data.pageCount + ')')
			.attr('href', 'javascript:void(0);');
			var li_l = $('<li></li>').append(a_l);
			$('#pagecontroller').append(li_l);
//			data
			$("#list").html('');
			var str='';
			
			for(var i=0;i<data.results.length;i++){
				var ted=data.results[i].description;
				str+='<tr data-id="'+data.results[i].id+'"><td>'+data.results[i].id+'</td><td>'+data.results[i].timeStr+'</td><td>'+data.results[i].status+
				'</td><td>'+data.results[i].cusName+'</td><td>'+data.results[i].cusTel+'</td><td>'+ted.replace(/-undefined-/,"-")+'</td><td>'+data.results[i].startTime+"至"+
				data.results[i].endTime+'</td><td>'+data.results[i].totalDay+'</td><td>'+data.results[i].price+'</td><td>'+data.results[i].totalPrice+'</td><td>'+data.results[i].preferential+'</td>'
				if(data.results[i].status=='确认中'){
					str+='<td><a href="javascript:;" class="btn comfirm-order" data-id="'+data.results[i].id+'">确认订单</a><a href="javascript:;" class="btn close-order" data-id="'+data.results[i].id+'">关闭订单</a></td>'
				}else if(data.results[i].status=='进行中'){
					str+='<td><a href="javascript:;" class="btn comfirm-sendpwd" data-id="'+data.results[i].id+'">发送密码</a></td>'
				}else if(data.results[i].status=='退租确认中'){
					str+='<td><a href="javascript:;" class="btn comfirmOutLease-order" data-id="'+data.results[i].id+'">确认退租</a></td>';
				}else{
					str+='<td></td>'
				}
				
				str+='</tr>'
					
			}
			$("#list").append(str)
		}
	});
};
	
//确认订单

$('#list').on('click','tr .comfirm-order',function(event){
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
//发送密码
$('#list').on('click','tr .comfirm-sendpwd',function(event){
	event.stopPropagation();
	var url = '../order/comfirmPw';
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


//关闭订单

$('#list').on('click','tr .close-order',function(event){
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

$('#list').on('click','tr .comfirmOutLease-order',function(event){
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



// 5.20分页存在问题；
//条件搜索；
$("#longtime").on('change',function(){
	 $.ajax({
			type:'POST',
			async : false,
			cache : false,
			dataType:'json',
			data:{'time':$("#longtime option:selected").attr('tid')},
			url: "./dsendlist",
			error:function(){
				alert('数据传输错误！')
			},
			success:function(data){
		//		console.log(data);
				var str='';
				var newTime=new Array();
				$("#list").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
					'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a>&nbsp;&nbsp;<a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
					
				}
				$("#list").append(str);
// OK	查看详细  状态显示			
				$(".lockit").on('click',function(){
					window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
				})

				if($("#longtime option:selected").attr('tid')=='3'){
					list(1)
				}
				
			}
		})
});
$("#monetary").on('change',function(){
//  getData($("#monetary option:selected").val())
	  $.ajax({
			type:'POST',
			async : false,
			cache : false,
			dataType:'json',
			data:{'money':$("#monetary option:selected").val()},
			url: "./dsendlist",
			error:function(){
				alert('数据传输错误！')
			},
			success:function(data){
		//		console.log(data);
				var str='';
				var newTime=new Array();
				$("#list").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
					'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a>&nbsp;&nbsp;<a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
					
				}
				$("#list").append(str);
// OK	查看详细  状态显示			
				$(".lockit").on('click',function(){
					window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
				})
				if($("#monetary option:selected").val()=='0'){
					list(1)
				}
				
				
			}
		})
	
});

$("#sex").on('change',function(){
//  getData($("#sex option:selected").val())
    $.ajax({
		type:'POST',
		async : false,
		cache : false,
		dataType:'json',
		data:{'sex':$("#sex option:selected").val()},
		url: "./dsendlist",
		error:function(){
			alert('数据传输错误！')
		},
		success:function(data){
		//	console.log(data);
			var str='';
			var newTime=new Array();
			$("#list").html('');
			for(var i=0;i<data.content.length;i++){
				var infotime=data.content[i].regTime;
				var j = new Date(infotime);
				var istime=j.toLocaleDateString()
				    newTime.push(istime);
				str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
				'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a>&nbsp;&nbsp;<a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
				
			}
			$("#list").append(str);
//OK	查看详细  状态显示			
			$(".lockit").on('click',function(){
				window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
			})
			if($("#sex option:selected").val()=='全部'){
				list(1)
			}
		}
	})
})


//订单详情
$(".orderDetail .close").click(
		function(){
			$('.masking,.orderDetail').hide();
			location=location;
		}
)

$("#list").on('click','tr',function(){
	
	/*住房订单*/
	var uid=$(this).attr('data-id');
	 $.ajax({
			type:'POST',
			dataType:'json',
			url:'/mobile/getOrder',
			data:{'id':uid},
			success:function(data){
			//	console.log(data);
				var pd="";
				var unixTimestamp = new Date( data[1].time ) ;
				commonTime = unixTimestamp.toLocaleString();
				 
				var dj=data[1].price.split('@');
				/*计算总价*/
				var total=eval(dj.join("+"));
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
			    
			    
			    
			    $(".detailWraper .zfhouse").html('');
			    var det=data[1].description
			    var kule='<tr><td style="width:100%;background:rgb(232, 229, 229);font-size: 18px;">住房订单</td><td>预订房屋：<span>'+det.replace(/-undefined-/,"-")+
			              '</span></td></tr><tr><td class="fl50">入住时间：<span>'+data[0]+'</span></td><td>离开时间：<span>'+data[2]+
			              '</span></td></tr><tr><td class="fl50">天数：<span>'+data[1].totalDay+'</span></td><td>订单状态：<span>'+pd+
			              '</span></td></tr><tr><td>下单时间：<span>'+commonTime+'</span></td></tr><tr><td>入住人：<span>'+data[1].cusName+
			              '</span></td></tr><tr><td>联系电话：<span>'+data[1].cusTel+
			              '</span></td></tr><tr><td class="fl50">总房费：<span>￥'+_price+'</span></td><td>押金：<span>￥'+jajin+
			              '</span></td><td class="fl50">优惠卷：<span>减'+yhj+
			              '元</span></td><td>合计：<span>￥'+Number(data[1].totalPrice).toFixed(0)+'</span></td></tr>';
			    
			    
			    $(".detailWraper .zfhouse").append(kule)
			    
			    
			}
			
	 })
	 
	/*保洁订单*/
	  $.ajax({
			type:'POST',
			dataType:'json',
			url:'/admin/getClean',
			data:{'oederId':uid},
			success:function(data){
			//	console.log(data);
				$(".detailWraper .clean ul").html('');
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
				
				$(".detailWraper .clean ul").append(sty);
				
			}
	  });
	 
	 /*用车订单*/
	  $.ajax({
			type:'POST',
			dataType:'json',
			url:'/mobile/getTripOrder',
			data:{'id':uid},
			success:function(data){
			//	console.log(data)
				$(".detailWraper .usercar ul").html('');
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
				$(".detailWraper .usercar ul").append(_Str);
			}
	  })	 
	 
	
	  	 /*维修订单*/
	  $.ajax({
			type:'POST',
			dataType:'json',
			url:'/mobile/getFault',
			data:{'id':uid},
			success:function(data){
		//		console.log(data)
				$(".detailWraper .getFault ul").html('');
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
				$(".detailWraper .getFault ul").html(_str);
				
			}
			
	  })
	  
			
		  	 /*添加订单*/
	  $.ajax({
			type:'POST',
			dataType:'json',
			url:'/mobile/getFacility',
			data:{'id':uid},
			success:function(data){
		//		console.log(data)
				$(".detailWraper .addsheshi ul").html('');
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
				$(".detailWraper .addsheshi ul").append(_str);
			}
			
	  })	
			
			
	  
	 $('.masking').show();
	 $(".orderDetail").addClass('hover');
})




$(document).ready(list(1));