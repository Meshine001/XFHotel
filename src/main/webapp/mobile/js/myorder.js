
    var menuBar=0;
$(document).ready(function(){
    var _status='';//¶©µ¥×´Ì¬

    $(".reserve").live('click',function(){
        window.location.href="order.html"
    });
    $(".payment").live('click',function(){
        window.location.href="payment.html";
    });
    $(".evaluate").live('click',function(){
        window.location.href="evaluate.html";
    });
    $(".cancel").live('click',function(){
        window.location.href="checkout.html";
    });
    var menu=fnBase.request("menu");
    if(menu==undefined){
        menu=0;
    }
    $("#nav a").click(function(){
        menuBar=$(this).index();
        reloadPage($(this).index())
    })

});

    function reloadPage(n){
        var _uid=fnBase.huoqu(0,"uid");
        //if(_uid==null||_uid=="undefined"||_uid==""){
        //    window.location.href="login.html";
        //    return;
        //}
        var current=n;
        $("#nav a").removeClass("active");
        $("#nav a").eq(current).addClass("active");
        $(".orderlist").html("");
        if(current==0){
            _status='';
        }else{
            _status=current;
        }
        getData(_uid,_status)
    }

    function getData(){
        var frontURL=Constant.URL+'/mobile/search';
        var postData={"uid":_uid ,"status":_status};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.status=="1"){

            }else if(data.status=="2"){

            }else{
                fnBase.myalert(data.msg)
            }
        })
    }
