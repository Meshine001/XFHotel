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
    	if(uname==''){
    		fnBase.myalert('请填写手机号码')
    		return
    	}
  
    	if($("#comment").val()==''){
    		fnBase.myalert('请留言后再提交')
    		return;
    	}
    	
        var arr=[num,5,5,5,5,5];
        var oplist=arr.join(',');
        var _orderID=fnBase.request('orderId');
        var _roomID=fnBase.request('roomId');
        var _uid=fnBase.huoqu(0,"userid");
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
                fnBase.myalert(data.content);
                window.location.href = 'index.html';
            }else{
                fnBase.myalert(data.content);
            }
        })
    })
});
