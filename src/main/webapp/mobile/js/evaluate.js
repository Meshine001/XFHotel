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

    });
    $(".navbar a").click(function(){
        var arr=[num,5,5,5,5,5];
        var oplist=arr.join(',');
        var _orderID=fnBase.request('orderId');
        var _roomID=fnBase.request('roomId');
        var _uid=fnBase.huoqu(0,"uid");
        var frontURL=Constant.URL+"/mobile/comment";
        var postData ={
            "roomId":_roomID,
            "orderId":_orderID,
            "pics":'null',
            "feel":$("#comment").val(),
            "c_score":oplist,
            "from":_uid,
            "to":0
        };

        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.statusCode=="1"){
                fnBase.myalert(data.content)
            }else{
                fnBase.myalert(data.content);
            }
        })
    })
});
