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
	console.log(_fadid)
	if(_fadid==null || _fadid=="undefined" || _fadid==""){
	        window.location.href="identification.html";
	        return;
	}

	var postData={'id':_fadid};
    var frontURL=Constant.URL+'/mobile/particulars/';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
       var str=""; 
       $(".houselist").html('')
	   for(var i=0;i<data.length;i++){
		   str+='<li id="'+data[i].id+'"><div class="house-pic"><img src="'+Constant.URL+'/images/'+data[i].fang_jian_tu[0]+'" class="img"><span>'+data[i].position.xa_wei_zhi+'-'+data[i].position.xiao_qu+'-'+data[i].position.men_pai+
		   '</span></div><div class="list_intro"><p>价格：'+data[i].basic_info.jia_ge+'/天</p><p><a href="javascript:;" class="order">查看订单</a></p></div></li>'
	   }
       $(".houselist").append(str);
       if(data.length=='0'||data==null||data==""){
    	   $("#zanwu").show();
       }
    });
    //查看详情
    $(".houselist").on('click','li .house-pic',function(){
    	var id=$(this).parent().attr('id');
    	 window.location.href="house_particulars.html?id="+encodeURIComponent(id)+"";
    })
    //查看订单
    $(".houselist").on('click','li .list_intro .order',function(){
    	var id=$(this).parent().parent().parent().attr('id');
    	 window.location.href="mybill.html?id="+encodeURIComponent(id)+"";
    })
});
