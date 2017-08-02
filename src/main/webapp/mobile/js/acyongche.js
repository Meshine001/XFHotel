$(document).ready(function(){
   var house=function(){//判断房间
    	var _uid=fnBase.huoqu(0,"uid");
        if(_uid==null || _uid=="undefined" || _uid==""){
            window.location.href="login.html";
            return;
        }
        var postData={uId:_uid,type:0 };
        var frontURL=Constant.URL+'/mobile/Clean';
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.content.length==0){
                $("#atl").show();
            }
            var str='';
            $(".house-status ol").html('');
            for(var i=0;i<data.content.length;i++){
                str+='<dd hid="'+data.content[i].id+'">'+data.content[i].description+'</dd>'
            }
            $(".house-status ol").append(str);
        });
    }
   house();
   
})