
$(document).ready(function(){
   var _uid=fnBase.huoqu(0,"uid");
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
    var frontURL=Constant.URL+'api/personal?code=302';
    fnBase.commonAjax(frontURL,{"param":_uid},function(data){
        console.log(data);
        if(data.status=="1"){
            if(data.info.nackname==""){
                $("#nickname").text(data.info.phone);
            }else{
                $("#nickname").text(data.info._nackname);
            }
            if(data.info.user_pic==""){
                $(".vip_info img").attr("src","images/face-90x90.png");
            }else{
                $(".vip_info img").attr("src",data.info.user_pic_id);
            }
            //未支付的订单数
            if(data.info.fkcount=="0"){
                $(".vip_info_list li .per-order-status .tip-size").hide();
            }else{
                $(".vip_info_list li .per-order-status .tip-size").text(data.info.fkcount).show();
            }


        }
    })
});
