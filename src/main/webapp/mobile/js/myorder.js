﻿
var menuBar=0;
$(document).ready(function(){

    var _status='';
    var _uid=fnBase.huoqu(0,"userid");
    var daty={
        currentdate:'',
        _startDate:''
    };
    if(_uid=='null'||_uid=="undefined"||_uid==""){
   		var redirect1 = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=index.html#wechat_redirect';
  	 	window.location.href = redirect1;
        return;
    }

    var menu=fnBase.request("menu");
    if(menu==undefined){
        menu=0;
    }
    $("#nav a").click(function(){
        menuBar=$(this).index();
        reloadPage($(this).index())
    });
    $("#nav a").eq(0).click();


    function reloadPage(n){
        var current=n;
        $("#nav a").removeClass("active");
        $("#nav a").eq(current).addClass("active");
        $(".orderlist").html("");
        if(current==0){
            _status=0;
        }else{
            _status=current;
        }
        getData(_status)
    }

    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
       var old_y=year-1;
        var old_m=month-1;
        currentdate = year + seperator1 + month + seperator1 + strDate;
        _startDate=(year-1)+seperator1+month+seperator1+strDate;
    }

    function getData(_status){
    	fnBase.loadShow();
        getNowFormatDate();
        var frontURL=Constant.URL+'/mobile/search';
        var postData={
            "cId":_uid,
            "category":_status,
            "type":0,
            "endDate":currentdate,
            "startDate":_startDate,
            "range":12
        };
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);



            if(data.statusCode=="1"){
                if(data.content.length=='0'){
                    $("#zanWu").show();
                }else{
                    $("#zanWu").hide();
                }
            	fnBase.loadHide();
                var _str='';
                $(".orderlist").html("");
                for(var i=0;i<data.content.length;i++){
                	var tui=data.content[i].description;
                    if(data.content[i].status=="等待支付"){
                        _str+='<li roomid="'+data.content[i].roomId+'" orderId="'+data.content[i].id+'"><p class="p_title" style="color: red">'+data.content[i].status+'</p>';
                    }else{
                        _str+='<li roomid="'+data.content[i].roomId+'" orderId="'+data.content[i].id+'"><p class="p_title" style="color: #00b38a">'+data.content[i].status+'</p>';
                    }
                    _str+='<div class="pay_content"><p class="list_bigsize">'+tui.replace(/-undefined-/,"-")+'</p><p class="p_list"><span>&nbsp; 合计：<i style="color: orange">'+data.content[i].totalPrice+'</i>元</span></p><p class="p_list">'+data.content[i].startTime+"~"+data.content[i].endTime+'<span>共'+data.content[i].totalDay+'天</span></p></div>';

                    if(data.content[i].status=="等待支付"){//1
                        _str+='<p class="total"><span class="fl payment">支付</span></p>';
                    }else if(data.content[i].status=="退款订单"){//6
                        _str+='<p class="total"></p>'
                    }else if(data.content[i].status=="进行中"){//2
                        _str+='<p class="total"><span class="fl cancel contenster" roomid="'+data.content[i].roomId+'">续租</span><span class="fl cancel lockpassword">查看密码</span><span class="fl cancel check-out">退房</span><span class="fl evaluate">评价</span></p>'
                    }else if(data.content[i].status=="已完成"||data.content[i].status=="退租确认中"){//3 or 8
                        _str+='<p class="total"><span class="fl cancel contenster" roomid="'+data.content[i].roomId+'">续租</span></p>'
                    }
                    _str+='</li>';

                }
                $(".orderlist").append(_str);
				var sz=$(".orderlist li");
				for(var j=0;j<sz.length;j++){
					if(sz.eq(j).find('.total .fl').length==0){
						$(".orderlist li").eq(j).find('.pay_content').css('border','none')
					}
				}
				
                var s=$(".total .payment").length;
                fnBase.keep(0,"toPaid",s);
            }else{
                $(".orderlist").hide();
                fnBase.myalert(data.content)
            }
        })
    }

    $(".orderlist").on('click','li',function(){
    	window.location.href="orderDetails.html?onumber="+$(this).attr("orderid")+"";
    })

    $(".orderlist").on('click','li .contenster',function(event){
    	event.stopPropagation();
    	 var id = $(this).attr('roomid');
         window.location.href = "house_details.html?id=" + encodeURIComponent(id) + "";
    });


    $(".reserve").live('click',function(){
        window.location.href="order.html"
    });
    $(".orderlist").on('click','li .payment',function(event){
    	event.stopPropagation();
        var _orderID=$(this).parent().parent().attr('orderid');
        window.location.href="payment.html?id="+_orderID;
    });
    $(".orderlist").on('click','li .evaluate',function(event){
    	event.stopPropagation();
        var _orderID=$(this).parent().parent().attr('orderid');
        var _roomID=$(this).parent().parent().attr('roomid');
        window.location.href="evaluate.html?orderId="+encodeURIComponent(_orderID)+"&roomId="+encodeURIComponent(_roomID);
    });


    $(".orderlist").on('click','li .check-out',function(event){
    	event.stopPropagation();
        var _orderID=$(this).parent().parent().attr('orderid');
        window.location.href="checkout.html?id="+encodeURIComponent(_orderID);
    });

    $(".orderlist").on('click','li .lockpassword',function(event){
    	event.stopPropagation();
        var Data=$(this).parent().parent().attr('orderid');
        var postData={"oId":Data};
        $.ajax({
            type:'POST',
            dataType:'json',
            data:postData,
            url:Constant.URL+'/mobile/viewpassword',
            success:function(data){
                console.log(data);
                $("#masking").show();
                $("#myArert").addClass('animate');
                $("#diaBody").text('房间密码：'+data.pwd_text);
            }
        })
    });
    $("#diaTitle em").click(function(){
        $("#myArert").removeClass('animate');
        $("#masking").hide();
    })
});




