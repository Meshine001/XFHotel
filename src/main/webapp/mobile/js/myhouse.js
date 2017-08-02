'use strict';

$(document).ready(function(){
	$(".top-nav-wrap a").click(function(){
		$(this).addClass('atl').siblings().removeClass('atl');
	})
	$(".top-nav-wrap a:eq(0)").click(function(){
		$(".houselist").show();
		$(".msg-house-list").hide();
	})
	$(".top-nav-wrap a:gt(0)").click(function(){
		$(".houselist").hide();
		$(".msg-house-list").show();
	})
	
	var _fadid=fnBase.huoqu(0,"fadid");
	
	
	
	var postData={'id':_fadid};
    var frontURL=Constant.URL+'/mobile/particulars/';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.statusCode=='0'){
        	$(".approve").html('<span class="czs-medal-l"></span>未认证');
        }else if(data.statusCode=='1'){
        	$(".approve").html('<span class="czs-medal-l"></span>已认证');
        	$(".approve").addClass('okapp');
        
        }
    });
});
