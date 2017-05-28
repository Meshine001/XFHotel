
var menuBar=0;
$(document).ready(function(){

    var _status='';
    var _uid=fnBase.huoqu(0,"uid");
    var daty={
        currentdate:'',
        _startDate:''
    };
    //if(_uid==null||_uid=="undefined"||_uid==""){
    //    window.location.href="login.html";
    //    return;
    //}
    //
    //var menu=fnBase.request("menu");
    //if(menu==undefined){
    //    menu=0;
    //}
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
            	fnBase.loadHide();
                var _str='';
                $(".orderlist").html("");
                for(var i=0;i<data.content.length;i++){

                    if(data.content[i].status=="等待支付"){
                        _str+='<li roomid="'+data.content[i].roomId+'" orderId="'+data.content[i].id+'"><p class="p_title" style="color: red">'+data.content[i].status+'</p>';
                    }else{
                        _str+='<li roomid="'+data.content[i].roomId+'" orderId="'+data.content[i].id+'"><p class="p_title" style="color: #00b38a">'+data.content[i].status+'</p>';
                    }
                    _str+='<div class="pay_content"><p class="list_bigsize">'+data.content[i].description+'</p><p class="p_list">'+data.content[i].type+'<span>支付<i style="color: orange">'+data.content[i].totalPrice+'</i>元</span></p><p class="p_list">'+data.content[i].startTime+"--"+data.content[i].endTime+'<span>共'+data.content[i].totalDay+'天</span></p></div>';

                    if(data.content[i].status=="等待支付"){
                        _str+='<p class="total"><span class="fl payment">支付</span></p>';
                    }else if(data.content[i].status=="退款订单"){
                        _str+='<p class="total"><span class="fl evaluate">评价</span></p>'
                    }else if(data.content[i].status=="进行中"){
                        _str+='<p class="total"><span class="fl cancel check-out">退房</span><span class="fl cancel lockpassword">查看密码</span></p>'
                    }else if(data.content[i].status=="已完成"||data.content[i].status=="退租确认中"){
                        _str+='<p class="total"><span class="fl evaluate">评价</span></p>'
                    }
                    _str+='</li>';

                }
                $(".orderlist").append(_str);
                var s=$(".total .payment").length;
                fnBase.keep(0,"toPaid",s);
            }else{
                $(".orderlist").hide();
                fnBase.myalert(data.content)
            }
        })
    }





    $(".reserve").live('click',function(){
        window.location.href="order.html"
    });
    $(".payment").live('click',function(){
        var _orderID=$(this).parent().parent().attr('orderid');
        //var frontURL=Constant.URL+'/mobile/getOrder';
        //var postData={id:_orderID};
        //fnBase.commonAjax(frontURL,postData,function(data){
        //    console.log(data)
        //});
        window.location.href="payment.html?id="+_orderID;

    });
    $(".evaluate").live('click',function(){
        var _orderID=$(this).parent().parent().attr('orderid');
        var _roomID=$(this).parent().parent().attr('roomid');
        window.location.href="evaluate.html?orderId="+encodeURIComponent(_orderID)+"&roomId="+encodeURIComponent(_roomID);
    });


    $(".check-out").live('click',function(){
        var _orderID=$(this).parent().parent().attr('orderid');
        window.location.href="checkout.html?id="+encodeURIComponent(_orderID);
    });

    $(".lockpassword").live('click',function(){
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




