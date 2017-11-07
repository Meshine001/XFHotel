var _id;
$(document).ready(function(){
    
	 var clientIp = getIp();

	 var Constant = {
		        URL: baseUrl, 
		        CLIENT_IP:clientIp
		};
	function getIp() {
		    var ip;
		    var ipInfoUrl = 'http://ipinfo.io/json';
		   	 $.ajax({
		        url:ipInfoUrl,
		        async:false,
		        dataType:'json',
		        success:function (data) {
		            ip = data.ip;
		           
		        }
		    });
		    
		    return ip;
	}
	
	
    _id=fnBase.request('pid');
    var jiage=fnBase.request('topic');
 
    $(".p_msg li .toal").html('订单总额：<i style="color:red">￥'+jiage+'</i>');
    

    $(".p_Settel li .wx_p").click(function () {
        //fnBase.myalert('支付系统未开启！')
        if(Constant.CLIENT_IP == undefined){
            Constant.CLIENT_IP = getIp();
        }
        var url = Constant.URL + '/wx/pay/jsAdd';
        var data = {
            id1:_id,//订单id
            ip:Constant.CLIENT_IP//客户端ip
        };
        console.log(data);
        fnBase.commonAjax(url,data,function (data){
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
    var url = fnBase.URL + '/mobile/faciletyWechatOrder';
    var data = {
        id:_id
    };
    fnBase.commonAjax(url,data,function (data) {
        if(data.statusCode == 1 ){
            window.location.href = fnBase.URL + '/wx/serve.html';
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
                window.location.href = './serve.html';

            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){//支付取消
                fnBase.myalert('取消支付');
            }else {//支付失败
                fnBase.myalert('支付失败');
            }
        }
    );
}
