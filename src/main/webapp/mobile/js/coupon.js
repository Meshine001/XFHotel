
$(document).ready(function(){
    if($(".goodlist li").length==0){
        $("#zanwu").show();
    }
//    var uid=fnBase.huoqu('0',uid);
    // 暂时用uid=2;
    var postdata={'uId':2}
    var frontURL=Constant.URL+"/mobile/getCoupons";
    fnBase.commonAjax(frontURL,postdata ,function(data) {
    	console.log(data)
    	var str='';
    	$(".goodlist").html('');
    	for(var i=0;i<data.length;i++){
    		str+='<li cuid="'+data[i].id+'"><h1>'+data[i].type+'</h1><p>消费满：<span>￥'+data[i].rule+'</span>可用</p><p>有效期：<span>'+data[i].startTime+"-"+data[i].endTime+'</span></p><i class="_pic">￥'+data[i].cValue+'</i></li>'
    	}
    	$(".goodlist").append(str);
    })
});
