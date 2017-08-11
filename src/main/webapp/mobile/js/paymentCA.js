var _id;
$(document).ready(function(){

    _id=decodeURIComponent(fnBase.request('pid'));
    var url = Constant.URL + '/mobile/getTrip/';
    var postData={id:_id};
    fnBase.commonAjax(url,postData,function (data){
    	console.log(data); 
    	if(data.classify=="接送"){
    		var str='<p>房屋地址：'+data.roomName+'</p><p>车辆选择：'+data.site+'</p><p>接送站：'+data.tripId+'</p><p>接送站时间：'+(new Date(data.startTime)).toLocaleDateString()+'</p><p>手机号：'+data.tel+'</p>';
    	}else if(data.classify=="包车"){
    		var str='<p>房屋地址：'+data.roomName+'</p><p>车辆选择：'+data.site+'</p><p>包车路线：'+data.tripId+'</p><p>开始时间：'+(new Date(data.startTime)).toLocaleDateString()+'</p><p>结束时间：'+(new Date(data.endTime)).toLocaleDateString()+'</p><p>手机号：'+data.tel+'</p>';
    	}
    	$(".p_msg .massage").append(str);
    	$(".p_msg li .toal").html('订单总额：<i style="color:red">￥'+data.price+'</i>');
    })


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
            var url = Constant.URL + '/wx/pay/jsTrip';
            var data = {
                id:_id,//订单id
                ip:Constant.CLIENT_IP//客户端ip
            };
            console.log(data);
            fnBase.commonAjax(url,data,function (data){
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
