



var _id;
$(document).ready(function(){
	 var clientIp = getIp();

	 var Constant = {
		        URL: baseUrl, 
		        CLIENT_IP:clientIp
		};
	function getIp() {
		 fnBase.loadShow();
		    var ip;
		    var ipInfoUrl = 'http://ipinfo.io/json';
		   	 $.ajax({
		        url:ipInfoUrl,
		        async:false,
		        dataType:'json',
		        success:function (data) {
		        	fnBase.loadHide();
		            ip = data.ip;
		        }
		    });
		    
		    return ip;
	}


    
    
    _id=fnBase.request('id');
    var frontURL=Constant.URL+'/mobile/getOrder';
    var postData={id:_id};
    var _totalprice='';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        
        fnBase.keep(1,'roomId',data[1].roomId)
        _totalprice=Number(data[1].totalPrice).toFixed(2);
        $(".p_msg li ._date").html(data[0]+"入住"+data[2]+"离开");
        $(".p_msg li ._cash").html("订单总额:<span class='money'>￥"+_totalprice+"</span>");
		$(".p_msg li ._number").html('共'+data[1].totalDay+'天')
		
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
    	};

    	
    	
    	if(data[1].status=='1'){
            var x = 10;
            var interval='';
            var d = new Date("1111/1/1,0:" + x + ":0");
    		interval = setInterval(function() {
    	        var m = d.getMinutes();
    	        var s = d.getSeconds();
    	        m = m < 10 ? "0" + m : m;
    	        s = s < 10 ? "0" + s : s;
    	        $(".p_msg li .daojs").html("剩余支付时间:"+ m + ":" + s);
    	        if (m == 0 && s == 0) {
    	            clearInterval(interval);
    	            $(".p_msg li .daojs").html("");
    	            return;
    	        }
    	        d.setSeconds(s - 1);
    	    }, 1000);
    	}
		

		
		
    });
   $.ajax({
	   type:'GET',
		dataType:'json',
		url:Constant.URL+'/mobile/info',
		data:{
	        'apartmentId':fnBase.huoqu(1,'roomId')
		},
		success:function(data){
			console.log(data)
			$(".p_msg li .addres").text(data.position.bd_wei_zhi+","+data.position.xiao_qu);
		}
   })


   $(".p_Settel li .wx_p").click(function (){

       if(Constant.CLIENT_IP == undefined){
           Constant.CLIENT_IP = getIp();
       }
       var url = Constant.URL + '/wx/pay/jsOrder';
       var data = {
           id:_id,//订单id
           ip:Constant.CLIENT_IP//客户端ip
       };
       console.log(data);
     fnBase.commonAjax(url,data,function (data) {
         console.log(data);
         if(data.status == 'success'){
             console.log(data);
             var payData = {
               appId: data.obj.appId,
                 timeStamp:  data.obj.timeStamp,
                 nonceStr: data.obj.nonceStr,
                 package:data.obj.package,
                 signType: data.obj.signType,
                 paySign:data.obj.paySign
             };
             callPay(payData);
         }else {
             fnBase.myalert('支付失败');
         }
     });
   });
   
});


/**
 * 调起支付
 * @param appId
 * @param timeStamp
 * @param nonceStr
 * @param package
 * @param signType
 * @param paySign
 */
function callPay(payData) {
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady(payData);
    }
}

var checkCount = 0;

function checkWechatPay() {
    var url = fnBase.URL + '/mobile/checkWechatPay';
    var data = {
        id:_id
    };
    fnBase.commonAjax(url,data,function (data) {
        if(data.statusCode == 1 ){
            window.location.href = fnBase.URL + '/wx/myorder.html';
        }else{
            //查询3次
            if (checkCount < 3){
                checkCount++;
                checkWechatPay();
            }
        }
    });

}

/**
 * jssdk
 */
function onBridgeReady(payData){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":payData.appId,
            "timeStamp":payData.timeStamp,
            "nonceStr":payData.nonceStr,
            "package":payData.package,
            "signType":payData.signType,
            "paySign":payData.paySign
        },
        function(res){
            WeixinJSBridge.log(res.err_msg);
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                // checkCount = 0;
                // //TODO 再次查询是否支付成功
                // checkWechatPay();
                // fnBase.myalert('支付失败');
                window.location.href = './myorder.html';

            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){//支付取消
                fnBase.myalert('取消支付');
            }else {//支付失败
                fnBase.myalert('支付失败');
            }
        }
    );
}
