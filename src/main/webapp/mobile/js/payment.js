$(document).ready(function(){
    payment.Entry();
});
var payment={
    Entry:function(){
        //微信支付
        $(".wx_p").click(function () {

            var url = Constant.URL + '/wechat/pay/jsOrder';
            var data = {
                id:'11',//订单id
                ip:Constant.CLIENT_IP//客户端ip
            }
            fnBase.commonAjax(url,data,function (data) {
                if(data.status == 'success'){
                    callPay(
                        data.obj.appId,
                        data.obj.timeStamp,
                        data.obj.nonceStr,
                        data.obj.package,
                        data.obj.signType,
                        data.obj.paySign);
                }
            });
        });
    }
};

/**
 * 调起支付
 * @param appId
 * @param timeStamp
 * @param nonceStr
 * @param package
 * @param signType
 * @param paySign
 */
function callPay(appId,timeStamp,nonceStr,package,signType,paySign) {
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
}

/**
 * jssdk
 */
function onBridgeReady(appId,timeStamp,nonceStr,package,signType,paySign){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":appId,
            "timeStamp":timeStamp,
            "nonceStr":nonceStr,
            "package":package,
            "signType":signType,
            "paySign":paySign
        },
        function(res){
            WeixinJSBridge.log(res.err_msg);
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                console.log('支付成功');
                //TODO 跳转支付成功页面

            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){//支付取消

            }else {//支付失败

            }
        }
    );
}
