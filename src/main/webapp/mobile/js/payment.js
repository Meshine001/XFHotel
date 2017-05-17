var _id;
$(document).ready(function(){
    var _uid = fnBase.huoqu(0, "uid");
    var _community=fnBase.huoqu(1,"community");
    var _startTime = fnBase.huoqu(1, "startTime");
    var _endTime=fnBase.huoqu(1,"endTime");
    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrices");
    _id = decodeURIComponent(fnBase.request("id"));
    var _price=fnBase.huoqu(1,"dayPrice");
    var _apartmenttype=fnBase.huoqu(1,"roomType");
    var _YJpic=fnBase.huoqu(1,"YJpic");

    $(".p_msg li .addres").text(_community);
    $(".p_msg li ._date").html(_startTime+"入住"+_endTime+"离开"+"<i class='date'>共（"+_oTotalDay+"）天</i>");
    $(".p_msg li ._cash").html("押金:<span style='color: #666'>"+_YJpic+"</span>");
    $(".p_msg li .toal").html("订单总额:<span class='money'>￥"+_oTotalPrice+"</span>");

    payment.Entry();
});
var payment={
    Entry:function(){
        //微信支付
        $(".p_Settel li .wx_p").click(function () {
            //fnBase.myalert('支付系统未开启！')
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
