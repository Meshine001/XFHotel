$(document).ready(function(){
   
	var _uid=fnBase.huoqu(0,"userid");
	var active="";
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
    var frontURL=Constant.URL+'/mobile/detailsData';
    var postData={id:_uid};
    fnBase.commonAjax(frontURL,postData,function(data){
    	console.log(data);
    	 //电话号码****代替
    	var _tel=data.tel;
        var tel= _tel.substr(0,3)+"****"+_tel.substr(7);
        var _nick=data.details.nick;
        if(_nick=="" || _nick==null || _nick==undefined){
        	$(".header .nick .nk").html(tel)
        }else{
        	$(".header .nick .nk").text(_nick)
        }
    })
    
    
    
   
    
    
    
    var postData={'id':_uid};
    var frontURL=Constant.URL+'/mobile/register';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.statusCode=='0'){
        	active=0;
        	$(".approve").html('<span class="czs-medal-l"></span>未认证');
        }else if(data.statusCode=='1'){
        	active=1;
        	fnBase.keep(0,'fadid',data.content);
        	$(".approve").html('<span class="czs-medal-l"></span>已认证');
        	$(".approve").addClass('okapp');
        
        }
    });
    
    $(".approve").click(function(){
    	if(active=='0'){
    		window.location.href='identification.html';
    	}else if(active=='1'){
    		fnBase.myalert('您已认证')
    	}
    })
    var _fadid=fnBase.huoqu(0,"fadid");
    console.log(_fadid)
    if(_fadid==null || _fadid=="undefined" || _fadid==""){
    	$("#housactive").html(' 您的房屋（0）')
        return;
    }

    var postData={'id':_fadid};
    var frontURL=Constant.URL+'/mobile/particulars/';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        $("#housactive").html(' 您的房屋（'+data.length+'）')
    })    
})