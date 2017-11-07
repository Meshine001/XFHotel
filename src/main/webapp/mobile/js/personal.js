
$(document).ready(function(){
	fnBase.loadShow();
	$(".vip_info #personMan div img").click(function(){
		window.location.href='editprofileinfo.html'
	});
    var _uid=fnBase.huoqu(0,"userid");
    
    if(_uid==null || _uid=="undefined" || _uid==""){
    }else{
    	jifendata();
    }
    
    // 积分
    function jifendata(){
    	var frontURL=Constant.URL+'/mobile/getIntegral';
    	var postData={id:_uid};
    	 fnBase.commonAjax(frontURL,postData,function(data){
    		 console.log(data)
    		 $("#Credit i").text(data.integral);
    	 })
    }
    
    
    var frontURL=Constant.URL+'/mobile/detailsData';
    var postData={id:_uid};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        fnBase.loadHide();
        if(data.details.nick==""||data.details.nick==null){
            $("#nickName").text(data.details.tel);
        }else{
            $("#nickName").text(data.details.nick);
        }
        if(data.details.avatar==""||data.details.avatar==null){
            $(".vip_info #personMan div img").attr("src",Constant.URL+"/images/face-90x90.png");
        }else{
        	$(".vip_info #personMan div img").attr("src",data.details.avatar);
        }

//        //未支付的订单数
//        var toPaid=fnBase.huoqu(0,"toPaid");
//        if(toPaid=="0"){
//            $(".vip_info_list li .per-order-status .tip-size").hide();
//        }else{
//            $(".vip_info_list li .per-order-status .tip-size").text(toPaid).show();
//        }

    });
    $(".vip_info_list li").eq(0).click(function(){

    });

    $("#Credit").click(function(){
      	 window.location.href="integral.html?int="+encodeURIComponent($(this).find('i').text())+"&id="+encodeURIComponent(_uid)+"";
      });
    

//  商品配送  餐饮配送 暂时没有做；
    $("#zanwu1,#zanwu2,#zanwu3").click(
    		function(){
    	    	fnBase.myalert('正在开发,敬请期待')
    	    }	
    );
    
    $(".per-order-status ol").on('click','dd',function(){
    	if($(this).hasClass('_active')==true){
    		$(this).removeClass('_active');
    	}else{
    		$(this).addClass('_active');
    	}
    });
    $(".time-status ol dd").live('click',function(){
    	$(this).addClass('_active').siblings().removeClass('_active');
    })
    
    

    

    
});
