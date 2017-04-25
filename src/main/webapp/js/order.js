$(document).ready(function(){
    var cood='';
    var span='';
    var _uid = fnBase.huoqu(0, "uid");
    var _community=fnBase.huoqu(1,"community");
    var _startTime = fnBase.huoqu(1, "startTime");
    var _endTime=fnBase.huoqu(1,"endTime");
    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
    var _id = decodeURIComponent(fnBase.request("id"));
    var _price=fnBase.huoqu(1,"dayPrice");
    var _apartmenttype=fnBase.huoqu(1,"roomType");
    var _YJpic=fnBase.huoqu(1,"YJpic");

    $(".order_info .roomName").text(_community);
    $(".order_info .roomTime").html(_startTime+"入住"+_endTime+"离开"+"<i class='date'>共（"+_oTotalDay+"）天</i>");
    $(".order_info .roompYJ").html("押金:<span style='color: #666'>"+_YJpic+"</span>");
    $(".order_info .roompPic").html("订单总额:<span class='money'>￥"+_oTotalPrice+"</span>");

    $("#needpic i").click(function(){
       if($(this).hasClass('active')==true){
           $(this).removeClass('active');
           cood=$(this).attr('cod')
       } else{
           $(this).addClass('active').siblings().removeClass('active');
           cood=$(this).attr('cod')
       }
    });

//    提交订单
    $(".navbar a").click(function(){
        var _userName=$("#userName").val();
        var _tel=$("#tel").val();
        var _identity=$("#identity").val();
        var roonWz=fnBase.huoqu(1,'roonWz');
        var roomCX=fnBase.huoqu(1,'roomCX');
        var num_door=fnBase.huoqu(1,'num_door');
        var _description=_community+"-"+roonWz+"-"+roomCX+"-"+num_door+'';
        if(_userName==''){
            fnBase.myalert("请输入入住人姓名");
            return;
        }
        if(_tel==''){
            fnBase.myalert("请输入入住人电话号码");
            return;
        }
        if(_identity==''){
            fnBase.myalert("请输您的身份证号");
            return;
        }
        var frontURL=Constant.URL+'/mobile/modulePost';
        var postData={
            "roomId":_id,//房屋ID
            "cusId":_uid,//用户ID
            "startTime":_startTime,//入住时间
            "endTime":_endTime,//离开时间
            "totalDay":_oTotalDay,//总天数
            "totalPrice":_oTotalPrice,//总价格
            "cusName":_userName,//入住人姓名
            "cusTel":_tel,//入住人电话
            "cusIdCard":_identity,//入住人身份证号
            "price":_price,//当天单价
            "apartmentType":_apartmenttype,//房源类型
            "needFapiiao":cood,//需不需要发票
            "preferential":span,
            "personal":$("#presonal").val(),
            "description":_description
        };
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
        })
    })

});