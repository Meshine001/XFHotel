var _uid="", _oTotalPrice="",_getYHJprice="";
$(document).ready(function(){
	_uid=fnBase.huoqu(0,'userid');
	showUserInfo()//默认显示用户信息
	orderShowinfo()//获取订单信息
	myjmessage()//免押金说明
	yhjtoggleshow()//点击优惠卷
	
	
	
	
})
	function showUserInfo(){//默认显示用户信息
		
        var frontURL=Constant.URL+'/mobile/detailsData?id='+_uid;
        var postData={};
        fnBase.commonAjax(frontURL,postData,function(data){
           // console.log(data);
            fnBase.keep(1,"uname",data.details.nick);
            fnBase.keep(1,"usfz",data.details.idCard);
            $("#tel").attr('value',data.tel);
            $("#identity").attr('value',data.details.idCard);
        });
	}

	function orderShowinfo(){//获取订单信息
		var _community=fnBase.huoqu(1,"community");
	    var _startTime = fnBase.huoqu(1, "startTime");
	    var _endTime=fnBase.huoqu(1,"endTime");
	    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
	    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
	    var _id = decodeURIComponent(fnBase.request("id"));
	    var _price=fnBase.huoqu(1,"dayPrice");
	    var _apartmenttype=fnBase.huoqu(1,"roomType");
	    var _YJpic=fnBase.huoqu(1,"YJpic");
		var _getYHJprice=fnBase.huoqu(1,"_price");
//	    console.log(_community)
//	    console.log(_startTime)
//	    console.log(_endTime)
//	    console.log(_oTotalDay)
//	    console.log(_oTotalPrice)
//	    console.log(Number(_oTotalPrice).toFixed(0))
	    $(".roomName").text(_community);
    	$(".roomTime").html(_startTime+"入住"+_endTime+"离开"+"<i class='date'>共（"+_oTotalDay+"）天</i>");
	    $(".roompYJ .money").text('￥'+_YJpic+"元");
	    $(".roomDJ .money").text('￥'+_price+"元");
	    
	    if(_oTotalDay<3){
	    	$(".roomZK").hide();
	    }else if(_oTotalDay>=3 && _oTotalDay<7){
	    	$(".roomZK").html('<i class="zk">9.5</i>折优惠：<span class="money">'+Number(_oTotalPrice).toFixed(0)+'</span>');
	    }else if(_oTotalDay>=7 && _oTotalDay<30){
	    	$(".roomZK").html('<i class="zk">9</i>折优惠：<span class="money">'+Number(_oTotalPrice).toFixed(0)+'</span>');
	    }else if(_oTotalDay>=30){
	    	$(".roomZK").html('<i class="zk">8</i>折优惠：<span class="money">'+Number(_oTotalPrice).toFixed(0)+'</span>');
	    }
	    
	    $(".roomPic").html("订单总额:<span class='money'>"+Number(_oTotalPrice).toFixed(0)+"元</span><a>优惠劵</a>");
	    
	}
	
	function myjmessage(){// 免押金说明
	    $("#masking").hide();
		$(".roomYJ .free").click(function(){	
			 $(".gratis").addClass('scale');
			 $("#masking").show();
		});
		$(".gratis h1 span").click(function(){
			 $(".gratis").removeClass('scale');
			 $("#masking").hide();		
	    });	
	}

function yhjtoggleshow(){
		_oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
		_getYHJprice=fnBase.huoqu(1,"_price");
		
		$(".roomPic a").click(function(){
	       $("#masking").show(10,function(){
	           $("#usecoupon").animate({bottom:'0rem'},300);
	       });
	        var frontURL=Constant.URL+'/mobile/getMyCoupons';
	        var postData={
	    			'uId':_uid,
	    			'price':_getYHJprice
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
	 	$("#usecoupon ._black").live('click',function(){
	        $("#masking").hide(10,function(){
	            $("#usecoupon").animate({bottom:'-5.7rem'},300);
	        });
	    });
    	//    选取优惠劵
	    var couponid='';
	    var used='';
	    var yhjid='';
	    $("#usecoupon header ._confirm").on('click',function(){
	        $("#masking").hide(10,function(){
	            $("#usecoupon").animate({bottom:'-5.7rem'},300);
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
	            $(".roomYHJ .money").text(couponid+"元");
	        }else{
	        	$(".roomYHJ .money").text("-"+couponid+"元");
	        }
	        $(".roomYHJ").show();
	        used=_oTotalPrice-couponid;
	        used=Number(used).toFixed(0);
	        console.log(used)
	        $(".roomPic .money").html(used+"元");
	        return yhjid;
	    });
	
	    $("#usecoupon ul li").live('click',function(){
	        if($(this).find('.ifrader').hasClass('hat')==false){
	            $(this).find('.ifrader').addClass('hat');
	            $(this).siblings().find('.ifrader').removeClass('hat')
	        }else{
	            $(this).find('.ifrader').removeClass('hat')
	        }
	    });
    	//提交订单==========================
    	 var _community=fnBase.huoqu(1,"community");
	    var _startTime = fnBase.huoqu(1, "startTime");
	    var _endTime=fnBase.huoqu(1,"endTime");
	    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
	    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
	    var _id = decodeURIComponent(fnBase.request("id"));
	    var _price=fnBase.huoqu(1,"dayPrice");
	    var _apartmenttype=fnBase.huoqu(1,"roomType");
	    var _YJpic=fnBase.huoqu(1,"YJpic");
		var _getYHJprice=fnBase.huoqu(1,"_price");
		var cood="";
		var _span='';
    $("#bottomMenu a").click(function(){
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
        var num_door=fnBase.huoqu(1,'num_door');
        var _description=_community+"-"+roonWz+"-"+num_door+'';
        if(_userName==''){
            fnBase.myalert("请输入您的姓名");
            return;
        }
        if(_tel==''){
            fnBase.myalert("请输入您的电话号码");
            return;
        }
        var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
        if (!myreg.test(_tel)) {
            fnBase.myalert("手机号码有误！ 请输入11位数字");
            return;
        }
        if(_identity==''){
            fnBase.myalert("请输您的身份证号");
            return;
        }
        var isIDCard0=/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
        if(!isIDCard0.test(_identity)){
        	fnBase.myalert("请输正确的身份证号");
            return;
        }
        
        if($("#contract span").hasClass('czs-circle-o')!=true){
        	 fnBase.myalert("同意住房协议之后才可以提交");
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
                window.location.href="payment.html?id="+encodeURIComponent(data.order.id);
            }
        })
    })
    	
    	
    	
    	//提交订单end======================
}

	