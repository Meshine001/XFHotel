$(document).ready(function(){
    var num='';
    var uname=$("#uname").val();
    var uphoneNamube=$("#uphoneNamube").val();
    var evaluate=$("#comment").val();
    var container=$('.price_info li');
    var stars=container.find('.is');
        stars.click(function(){
        stars.removeClass('hover');
        var index = $(this).index();
        $('.price_info li i:lt('+index+')').addClass('hover');
        num = $('.price_info li .hover').length;
        return num;
    });
    $(".navbar a").click(function(){
        var _uid=fnBase.huoqu(0,"uid");
        var frontURL=Constant.URL+'/api/user/login';
        var postData ={
            "uid":_uid,
            "uname":uname,
            "uphoneNamube":uphoneNamube,
            "evaluate":evaluate,
            "unm":num
        };
        postData=JSON.stringify(postData);
        fnBase.commonAjax(frontURL,{"param":postData},function(data){
            console.log(data);
            if(data.status=="1"){
                window.location.href="myorder.html";
            }else{
                fnBase.myalert(data.msg);
            }
        })
    })
});
