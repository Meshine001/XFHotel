

$(document).ready(function(){
	
	
	 var userid,wechatOpenId="",_status='0',ss="0",uid="";
	 var redirect = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=serve.html#wechat_redirect';
	    
   	 uid=fnBase.request("id");
   	 userid=fnBase.huoqu(0,'userid');

	 if(userid=="undefined" || userid==undefined || userid==null){
		 if(uid=="undefined" || uid==undefined ||uid==null){
			  window.location.href = redirect; 	
			  return; 
		 }else{
			 uid=fnBase.request("id");
			 userid=fnBase.keep(0,'userid',uid);
			 userid=fnBase.huoqu(0,'userid');
		 }
	 };
	 

	 
	
	
	
	
	
	
	
	
	
	
	
    $(".vip_info #personMan div img").click(function(){
        window.location.href='editprofileinfo.html'
    });
      
    var _uid=fnBase.huoqu(0,"userid");
    console.log(_uid)
    if(_uid=='null' || _uid=="undefined" || _uid==""){
       $("#nickName,#Credit").hide();
   		var redirect1 = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=index.html#wechat_redirect';
  	 	window.location.href = redirect1;
    }else{
        $("#_login").hide();
        getmsg();
        jifendata();
    }
    function getmsg(){
    var frontURL=Constant.URL+'/mobile/detailsData';
    var postData={id:_uid};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        fnBase.loadHide();
        fnBase.keep( 1 ,"nick",data.details.nick);
        fnBase.keep( 1 ,"tel",data.tel);
        
        if(data.details.nick==""||data.details.nick==null){
            $("#nickName").html(data.details.tel);
        }else{
            $("#nickName").html(data.details.nick);
        }
        if(data.details.avatar==""||data.details.avatar==null){
            $(".vip_info #personMan div img").attr("src",Constant.URL+"/images/face-90x90.png");
        }else{
            $(".vip_info #personMan div img").attr("src",data.details.avatar);
        }



    });
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
    
    
   $("#Credit").click(function(){
   	 window.location.href="integral.html?int="+encodeURIComponent($(this).find('i').text())+"&id="+encodeURIComponent(_uid)+"";
   });
   


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
