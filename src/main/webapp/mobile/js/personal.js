
$(document).ready(function(){
    //$(".vip_info_list li .per-order-status .tip-size").css("display","none");
    var _uid=fnBase.huoqu(0,"uid");
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
    var frontURL=Constant.URL+'/mobile/detailsData?id='+_uid;
    var postData={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);

        if(data.details.nick==""||data.details.nick==null){
            $("#nickname").text(data.details.tel);
        }else{
            $("#nickname").text(data.details.nick);
        }
        if(data.details.avatar==""||data.details.avatar==null){
            $(".vip_info img").attr("src",Constant.URL+"/images/face-90x90.png");
        }else{
            $(".vip_info img").attr("src",Constant.URL+'/images/'+data.details.avatar);
        }
        //未支付的订单数
        var toPaid=fnBase.huoqu(0,"toPaid");
        if(toPaid=="0"){
            $(".vip_info_list li .per-order-status .tip-size").hide();
        }else{
            $(".vip_info_list li .per-order-status .tip-size").text(toPaid).show();
        }

    });
    $(".vip_info_list li").eq(0).click(function(){

    });




    //退出登录
    $(".logout").click(
        function(){
            sessionStorage.clear();
            localStorage.clear();
            setTimeout(function(){
                window.location.href="login.html";
            },300)

        }
    );
});
