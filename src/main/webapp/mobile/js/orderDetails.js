$(document).ready(function(){
	var orderId=decodeURIComponent(fnBase.request("onumber"));
	//获得年月日      得到日期oTime  
    function getMyDate(str){  
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oHour = oDate.getHours(),  
        oMin = oDate.getMinutes(),  
        oSen = oDate.getSeconds(),  
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);
        return oTime;  
    };  
    //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }  

	var orderlist={
			house:function(){
				 var frontURL=Constant.URL+'/mobile/getOrder';
			     var postData={'id':orderId};
			        fnBase.commonAjax(frontURL,postData,function(data){
			        //	console.log(data)
			        	$(".content-fixed").html('');

			        	$(".msginfo #infoname").html('下单人：'+data[1].cusName+'&nbsp;'+data[1].cusTel+'');
			        	$(".msginfo #infoadd").html('房屋地址：'+data[1].description+'');
			        	var _str,pd,st="";
			        	if(data[1].status=='2'){
			        		pd='进行中'
			        			st='<a href="javascript:;" class="check-out" orderid="'+data[1].id+'">退房</a><a href="javascript:;" orderid="'+data[1].id+'" class="look">查看密码</a>'	
			        	}else if(data[1].status=='1'){
			        		pd='等待支付'
			        			st='<a href="javascript:;" class="payment" orderid="'+data[1].id+'">支付</a>'		
			        	}else if(data[1].status=='3'){
			        		pd='已完成'
			        			st='<a href="javascript:;" class="evaluate" orderid="'+data[1].id+'" roomid="'+data[1].roomId+'">评论</a>'			
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
			        	
			        	_str='<div class="section houseinfo"><ul class="shop_item"><li>住房订单<span>'+pd+'</span></li><li>下单时间：<span>'+getMyDate(data[1].time)+'</span></li><li>入住时间：<span>'+data[0]+'</span></li><li>离开时间：<span>'+data[2]+'</span></li><li><span>共'+data[1].totalDay+'晚合计：'+data[1].totalPrice+'</span></li></ul><p style="clear:both"></p><div class="action">'+st+'</div></div>'
			        	
			        	$(".content-fixed").html(_str);
			        });

			        
			        //支付：
			        $(".houseinfo .action").on('click','.payment',function(){
			            var _orderID=$(this).attr('orderid');
			            window.location.href="payment.html?id="+_orderID;
			        })
			        //退房
			        $(".check-out").live('click',function(){
			            var _orderID=$(this).attr('orderid');
			            window.location.href="checkout.html?id="+encodeURIComponent(_orderID);
			        });
			        //评论
			        $(".evaluate").live('click',function(){
			            var _orderID=$(this).attr('orderid');
			            var _roomID=$(this).attr('roomid');
			            window.location.href="evaluate.html?orderId="+encodeURIComponent(_orderID)+"&roomId="+encodeURIComponent(_roomID);
			        });
			        //查看密码
			        $(".look").live('click',function(){
			            var Data=$(this).attr('orderid');
			            var postData={"oId":Data};
			            $.ajax({
			                type:'POST',
			                dataType:'json',
			                data:postData,
			                url:Constant.URL+'/mobile/viewpassword',
			                success:function(data){
			                    console.log(data);
			                   fnBase.myalert('房间密码：'+data.pwd_text);
			                }
			            })
			        });
			},
			UsedCars:function(){
				 var frontURL=Constant.URL+'/mobile/getTripOrder';
			     var postData={'id':orderId};
			        fnBase.commonAjax(frontURL,postData,function(data){
			        	console.log(data)
			        	$(".content-fixed").html("");
			        	var _ui="",st,pt="";
			        	for(var i=0;i<data.length;i++){
			        		if(data[i].status=='0'){
			        			st='未支付'
			        				pt='<a href="javascript:;" class="paymentcar" orderid="'+data[i].id+'">支付</a>'	
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
			        		_ui+='<div class="section"id="'+data[i].id+'"><ul class="shop_item"><li>用车订单<span>'+st+'</span></li><li>下单时间：<span>'+getMyDate(data[i].time)+'</span></li><li>服务类容：<span>'+data[i].classify+'</span></li><li>汽车类型：<span>'+data[i].site+'</span></li><li>使用地址：<span>'+data[i].tripId+'</span></li><li>开始时间：<span>'+getMyDate(data[i].startTime)+'</span></li><li>离开时间：<span>'+getMyDate(data[i].endTime)+'</span></li><li><span>合计：￥'+data[i].price+'</span></li></ul><p style="clear:both"></p><div class="action">'+pt+'</div></div>'
			        	}
			        	if(data.length==0){
			        		_ui='<p style="font-size:0.14rem;text-align:center;">暂无订单</p>';
			        	}
			        	$(".content-fixed").append(_ui);
			        	
			        })
			        
			        $(".content-fixed").on('click','.section .paymentcar',function(){
			        	 window.location.href="paymentCA.html?pid="+$(this).attr('orderid')+"";
			        })
			},
			Cleaning:function(){
				 var frontURL=Constant.URL+'/mobile/getClean';
			     var postData={'id':orderId};
			        fnBase.commonAjax(frontURL,postData,function(data){
			        	console.log(data);
			        	$(".content-fixed").html("");
			        	var _sui="",st;
			        	for(var j=0;j<data.length;j++){
			        		if(data[j].status=='0'){
			        			st='等待管理员呼叫保洁'	
				        	}else if(data[j].status=='1'){
				        		st='正在打扫'		
				        	}else if(data[j].status=='2'){
				        		st='已完成'		
				        	}
			        		_sui+='<div class="section" id="'+data[j].id+'"><ul class="shop_item"><li>保洁订单<span>'+st+'</span></li><li>下单时间：<span>'+getMyDate(data[j].time)+'</span></li><li>服务类容：<span>'+data[j].content+'</span></li><li>打扫时间：<span>'+data[j].cleanTime+'</span></li></ul><p style="clear:both"></p></div>';
			        	}
			        	if(data.length==0){
			        		_sui='<p style="font-size:0.14rem;text-align:center;">暂无订单</p>';
			        	}
			        	$(".content-fixed").append(_sui);
			        })
			},
			maintenance:function(){
				var frontURL=Constant.URL+'/mobile/getFault';
			    var postData={'id':orderId};
			        fnBase.commonAjax(frontURL,postData,function(data){
			        	console.log(data);
			        	$(".content-fixed").html("");
			        	var su="",st;
			        	for(var j=0;j<data.length;j++){
			        		if(data[j].status=='0'){
			        			st='等待管理员呼叫维修'	
				        	}else if(data[j].status=='1'){
				        		st='正在维修'		
				        	}else if(data[j].status=='2'){
				        		st='已完成'		
				        	}
			        		su+='<div class="section" id="'+data[j].id+'"><ul class="shop_item"><li>维修订单<span>'+st+'</span></li><li>下单时间：<span>'+getMyDate(data[j].time)+'</span></li><li>服务类容：<span>'+data[j].faultItem+'</span></li><li>维修时间：<span>'+data[j].maintainTime+'</span></li></ul><p style="clear:both"></p></div>';
			        	}
			        	if(data.length==0){
			        		su='<p style="font-size:0.14rem;text-align:center;">暂无订单</p>';
			        	}
			        	$(".content-fixed").append(su);
			        })
			},
			addsheshi:function(){
				var frontURL=Constant.URL+'/mobile/getFacility';
			    var postData={'id':orderId};
			        fnBase.commonAjax(frontURL,postData,function(data){
			        	console.log(data);
			        	$(".content-fixed").html("");
			        	var su="",st;
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
			        		su+='<div class="section" id="'+data[i].id+'"><ul class="shop_item"><li>添加设施订单<span>'+st+'</span></li><li>下单时间：<span>'+getMyDate(data[i].time)+'</span></li><li>时间：<span>'+data[i].addTime+'</span></li><li>名称：<span>'+data[i].facility+'</span></li><li>天数：<span>'+data[i].fate+'天</span></li><li><span>合计：￥'+data[i].price+'</span></li></ul><p style="clear:both"></p></div>';
			        	}
			        	if(data.length==0){
			        		su='<p style="font-size:0.14rem;text-align:center;">暂无订单</p>';
			        	}
			        	$(".content-fixed").append(su);
			        });
			        
			        
			},
	}
	$(".find_nav .item").click(function(){
		$(this).addClass('show').siblings().removeClass('show');
	})
	$(".find_nav .item").eq(0).click(function(){
		orderlist.house();//住房订单详情
	})
	$(".find_nav .item").eq(1).click(function(){
		orderlist.UsedCars();//用车订单
	})
	$(".find_nav .item").eq(2).click(function(){
		orderlist.Cleaning();//保洁订单
	})
	$(".find_nav .item").eq(3).click(function(){
		orderlist.maintenance();//维修订单
	})
	$(".find_nav .item").eq(4).click(function(){
		orderlist.addsheshi();//添加设施订单
	})
	
	
	orderlist.house();
})