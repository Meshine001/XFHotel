﻿$(document).ready(function(){
	

	
    var _uid=fnBase.huoqu(0,"uid");
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
    // 1 查出要打扫的房间列表
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
        


    /*
    * ��������ύ����
    * demand  $('.per-order-status ._textarea').val();
    * oederId  _oederId
    * content _content
    * cleanTime _cleanTime
    *
    * */
    // 2 选择房间
    var _oederId='';
    $(".house-status ol dd").live('click',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
        if($(this).hasClass('_active')==true){
            _oederId=$(this).attr('hid');
        }
        return _oederId;
    });

    var _content='';

    $(".content-status ol dd").live('click',function(){
        var houseList=new Array();
        for(var i=0;i<$(".content-status ol dd").length;i++){
            if($(".content-status ol dd").eq(i).hasClass('_active')==true){
                houseList.push($(".content-status ol dd").eq(i).attr('paid'));
            }
        }
        _content=houseList.join(',');
        return _content;
    });

  




    //提交信息
    $(".account-login-width  a").click(function(){

       if(_oederId==''){
           fnBase.myalert('请选择要维修的房间');
           return;
       }
        if(_content==''){
            fnBase.myalert('请问你有哪些需要维修的项目呢');
            return;
        }
   
        var postData={
            oederId:_oederId,
            content1:_content,
            demand:$('.per-order-status ._textarea').val()
        };

        var frontURL=Constant.URL+'/mobile/cleanAdd';
//        $.ajax({
//            type:'post',
//            dataType:'json',
//            data:{
//                oederId:_oederId,
//                content1:_content,
//                demand:$('.per-order-status ._textarea').val()
//            },
//            url:frontURL,
//            success:function(data){
//                console.log(data);
//                fnBase.myalert('提交成功');
//                setTimeout(function(){
//                	window.location.href='serve.html';
//                },300)
//                
//            }
//        });
    });

});
