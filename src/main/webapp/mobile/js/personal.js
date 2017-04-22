
$(document).ready(function(){
   //var _uid=fnBase.huoqu(0,"id");
   // if(_uid==null || _uid=="undefined" || _uid==""){
   //     window.location.href="login.html";
   //     return;
   // }
    var frontURL=Constant.URL+'/mobile/detailsData?id=3';
    var postData={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);

        if(data.details.name==""||data.details.name==null){
            $("#nickname").text(data.details.tel);
        }else{
            $("#nickname").text(data.details.name);
        }
        if(data.details.avatar==""||data.details.avatar==null){
            $(".vip_info img").attr("src","http://192.168.1.109:8080/hotel/images/face-90x90.png");
        }else{
            $(".vip_info img").attr("src",'http://192.168.1.109:8080/hotel/images/'+data.details.avatar);
        }
        //未支付的订单数
        if(data.info.fkcount=="0"){
            $(".vip_info_list li .per-order-status .tip-size").hide();
        }else{
            $(".vip_info_list li .per-order-status .tip-size").text(data.info.fkcount).show();
        }

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
