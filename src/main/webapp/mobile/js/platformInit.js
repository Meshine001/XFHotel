$(document).ready(function(){
   
	var _uid=fnBase.huoqu(0,"uid");
	var _nick=fnBase.huoqu(1,"nick");
	var _tel=fnBase.huoqu(1,"tel");
	
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
    //电话号码****代替
    var tel= _tel.substr(0,3)+"****"+_tel.substr(7);
    if(_nick=="" || _nick==null || _nick==undefined){
    	$(".header .nick .nk").html(tel)
    }else{
    	$(".header .nick .nk").text(_nick)
    }
    
    
    
    var postData={'id':_uid};
    var frontURL=Constant.URL+'/mobile/register';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.statusCode=='0'){
        	$(".approve").html('<span class="czs-medal-l"></span>未认证');
        }else if(data.statusCode=='1'){
        	$(".approve").html('<span class="czs-medal-l"></span>已认证');
        	$(".approve").addClass('okapp');
        
        }
    });
   
})