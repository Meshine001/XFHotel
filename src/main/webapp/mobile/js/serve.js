

$(document).ready(function(){
    $(".vip_info #personMan div img").click(function(){
        window.location.href='editprofileinfo.html'
    });
    
    var _uid=fnBase.huoqu(0,"uid");
    if(_uid==null || _uid=="undefined" || _uid==""){
       $("#nickName,#Credit").hide();
       $("#_login").show();
    }else{
        $("#_login").hide();
        getmsg();
    }
    function getmsg(){
    var frontURL=Constant.URL+'/mobile/detailsData';
    var postData={id:_uid};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        fnBase.loadHide();
        if(data.details.nick==""||data.details.nick==null){
            $("#nickName").html(data.details.tel);
        }else{
            $("#nickName").html(data.details.nick);
        }
        if(data.details.avatar==""||data.details.avatar==null){
            $(".vip_info #personMan div img").attr("src",Constant.URL+"/images/face-90x90.png");
        }else{
            $(".vip_info #personMan div img").attr("src",Constant.URL+'/images/'+data.details.avatar);
        }

        //未支付的订单数
        var toPaid=fnBase.huoqu(0,"toPaid");
        if(toPaid=="0"){
            $(".vip_info_list li .per-order-status .tip-size").hide();
        }else{
            $(".vip_info_list li .per-order-status .tip-size").text(toPaid).show();
        }

    });
    }
    // 积分
//   $("#Credit").click(function(){
//   	 window.location.href='integral.html'
//   });
   


//  商品配送  餐饮配送 暂时没有做；
    $("#zanwu1,#zanwu2").click(
        function(){
            fnBase.myalert('正在开发,敬请期待')
        }
    );
    // 托管
    $("#zanwu3").click(function(){
    	fnBase.myalert('正在开发,敬请期待')
//    	window.location.href="platform.html"
    })
    
    $(".per-order-status ol dd").live('click',function(){
        if($(this).hasClass('_active')==false){
            $(this).addClass('_active')
        }else{
            console.log($(this).text());
            $(this).removeClass('_active')
        }
    });
    $(".time-status ol dd").live('click',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
    })






});
