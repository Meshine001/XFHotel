$(document).ready(function(){
    payment.Entry();
    var _uid = fnBase.huoqu(0, "uid");
    var _community=fnBase.huoqu(1,"community");
    var _startTime = fnBase.huoqu(1, "startTime");
    var _endTime=fnBase.huoqu(1,"endTime");
    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
    var _id = decodeURIComponent(fnBase.request("id"));
    var _price=fnBase.huoqu(1,"dayPrice");
    var _apartmenttype=fnBase.huoqu(1,"roomType");
    var _YJpic=fnBase.huoqu(1,"YJpic");

    $(".p_msg li .addres").text(_community);
    $(".p_msg li ._date").html(_startTime+"入住"+_endTime+"离开"+"<i class='date'>共（"+_oTotalDay+"）天</i>");
    $(".p_msg li ._cash").html("押金:<span style='color: #666'>"+_YJpic+"</span>");
    $(".p_msg li .toal").html("订单总额:<span class='money'>￥"+_oTotalPrice+"</span>");
});
var payment={
    Entry:function(){
        //微信支付
        $(".p_Settel li .wx_p").click(function () {
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
