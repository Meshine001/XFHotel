$(document).ready(function(){
    $(".link-home").attr("href","javascript:void(0);").click(function(){
        if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
            window.location.href = window.document.referrer;
        } else { window.history.go("-1"); }
    });

    addpartenr();
    var sexVal='';
    var couponid='';
    var yhjid='';
    $("#addfriend").live('click',function(){
        $("#masking").show(10,function(){
            $(".alert-content").animate({bottom:0},300);
        });
    });
    $(".alert-content ._black,#usecoupon ._black").live('click',function(){
        $("#masking").hide(10,function(){
            $(".alert-content, #usecoupon").animate({bottom:'-5.7rem'},300);
        });
    });
    
function addpartenr(){
    var isName='';
    var isID='';
    $("ins").toggle(function(){
        $(this).addClass('rotate');
        $(".news-msg").animate({bottom:0},260);
    },function(){
        $("#pName").val('');
        $("#pId").val('');
        $(this).removeClass('rotate');
        $(".news-msg").animate({bottom:'-4.7rem'},200);
    });
    $("#keep").click(function(){//填写信息
        isName=$("#pName").val();
        isID=$("#pId").val();
        //身份证验证
        var isIDCard2=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
        if(isIDCard2.test(isID)){
           fnBase.myalert('OK')
        }else{
            fnBase.myalert('身份证号码输入有误');
            return false;
        }

        var str='';
        if(isName!=''&& isID!=''){
            str+='<li><i class="this"></i><span class="_pName">'+isName+'</span><span class="numbId">'+isID+'</span><i class="_remove"></i></li>'
            $(".alert-content .oldpartenr").append(str);
            
        }
        $("ins").removeClass('rotate');
        $(".news-msg").animate({bottom:'-4.7rem'},200);
    });
    $(".alert-content .oldpartenr li").live('click',function(){
        if($(this).hasClass('selected')==false){
            $(this).addClass('selected')
        }else{
            $(this).removeClass('selected')
        }
    });
    //删除同居人信息
    $(".alert-content .oldpartenr li ._remove").live('click',function(){
        $(this).parent().remove();
    })
   //确定同居人
    $(".alert-content ._confirm").click(function(){
        var opifo='';
        var strList=new Array();
        var pidlist=new Array();
        var obj=$(".alert-content .oldpartenr li");
        for(var i=0;i<obj.length;i++){
            if(obj.eq(i).hasClass("selected")){
                strList.push(obj.eq(i).find('._pName').text());
                pidlist.push(obj.eq(i).find('.numbId').text());
            }
        }
        console.log(strList)
        $(".addpartenr").html('');
        for(var j=0;j<strList.length;j++){
            opifo+='<li pid="'+pidlist[j]+'"><span>'+strList[j]+'</span><i>X</i></li>'
        }
        $(".addpartenr").append(opifo);
        $(".alert-content").animate({bottom:'-5.6rem'},200);
        $("#masking").hide();
    });
    //取消好友
    $(".addpartenr li i").live('click',function(){
        $(this).parent().remove()
    });
  
   
    

    
}



    var cood='';
    var _span='';
    var _uid = fnBase.huoqu(0, "uid");
    if (_uid == null || _uid == "undefined" || _uid == "") {
        window.location.href = "login.html";
        return;
    }
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
    $(".order_info .roompPic").html("订单总额:<span class='money'>￥"+Number(_oTotalPrice).toFixed(2)+"</span><a>优惠劵</a>");
    
    $("#needpic i").click(function(){
       if($(this).hasClass('active')==true){
           $(this).removeClass('active');
           cood=$(this).attr('cod')
       } else{
           $(this).addClass('active').siblings().removeClass('active');
           cood=$(this).attr('cod')
       }
       
    });
 






    /**
	 * 获取可用优惠卷
	 * @param uId  _uid
	 * @param totalPrice
	 * @return 
	 */
    function get(){

    }
   $(".order_info .roompPic a").click(function(){
       console.log('我要用优惠卷')
        $("#masking").show(10,function(){
            $("#usecoupon").animate({bottom:0},300);
        });

        var frontURL=Constant.URL+'/mobile/getMyCoupons';
        var postData={
    			'uId':_uid,
    			'totalPrice':_oTotalPrice
        };
        console.log(postData);
    	 fnBase.commonAjax(frontURL,postData,function(data){
    		 console.log(data);
    		 if(data.length=='0'||data.length==''){
    			 $('#zanwu').show();
    		 }else{
    			 var _data='';
        		 $('#usecoupon ul').html('');
        		 for(var j=0;j<data.length;j++){
        			 _data+='<li usid="'+data[j].id+'"  used="'+data[j].cValue+'"><div class="usemsg"><h1>'+data[j].type+'</h1><p>消费满：<span>'+data[j].rule+'</span></p><p>有效期：<span>'+data[j].startTime+"-"+data[j].endTime+'</span></p><span class="ifrader"><i></i></span></div><i class="_pic">￥'+data[j].cValue+'</i></li>'
        		 }
        		 $('#usecoupon ul').append(_data)
    		 }
    	 })
    });
    $("#usecoupon ul li").live('click',function(){
        if($(this).find('.ifrader').hasClass('hat')==false){
            $(this).find('.ifrader').addClass('hat');
            $(this).siblings().find('.ifrader').removeClass('hat')
        }else{
            $(this).find('.ifrader').removeClass('hat')
        }
    });



//    选取优惠劵11
    var used='';
    $("#usecoupon header ._confirm").live('click',function(){
        $("#masking").hide(10,function(){
            $(".alert-content, #usecoupon").animate({bottom:'-5.7rem'},300);
        });
        var obj=$(this).parent().parent().find('li');
        for(var i=0;i<obj.length;i++){
            if(obj.eq(i).find('.usemsg .ifrader').hasClass("hat")){
                couponid=obj.eq(i).attr('used');
                yhjid=obj.eq(i).attr('usid')
            }
        }
        if(couponid==""){
            couponid=0;
        }
        used=_oTotalPrice-couponid;
        used=Number(used).toFixed(0);
        $(".order_info .roompPic").html("订单总额:<span class='money'>￥"+used+"</span><a>优惠劵</a>");
        return yhjid;
    });

    //登录用户；默认显示信息；
    getData();
    function getData(){
        var _uid = fnBase.huoqu(0, "uid");
        if (_uid == null || _uid == "undefined" || _uid == "") {
            window.location.href = "login.html";
            return;
        }
        var frontURL=Constant.URL+'/mobile/detailsData?id='+_uid;
        var postData={};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            $("#userName").attr('value',data.details.nick);
            $("#tel").attr('value',data.details.tel);
            $("#identity").attr('value',data.details.idCard);
        });

    }

    //    提交订单
    $(".navbar a").click(function(){
        var otherCusName=new Array();
        var otherCusIdCard=new Array();
        for(var i=0;i<$('.addpartenr li').length;i++){
            otherCusName.push($('.addpartenr li').eq(i).find('span').text());
            otherCusIdCard.push($('.addpartenr li').eq(i).attr('pid'))
        }
        var _otherCusName=otherCusName.join("，");
        var _otherCusIdCard=otherCusIdCard.join(",");

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
            "preferential":_span,//优惠
            "personal":$("#presonal").val(),
            "description":_description,
            "id":yhjid,
            "otherCusName":_otherCusName,
        	"otherCusIdCard":_otherCusIdCard
        };
        console.log(postData);
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            console.log(data.order.id);
            if(data.order.id != '' && data.order.id != undefined){
                //location.href = Constant.URL + '/wx/payment.html?id='+encodeURIComponent(data.order.id);
                window.location.href="payment.html?id="+encodeURIComponent(data.order.id);
            }else {
                window.location.href = 'login.html';
            }
        })
    })
    
    
});