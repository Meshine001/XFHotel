
$(document).ready(function(){
    ////�ֲ�
    var mySwiper = new Swiper ('.swiper-container',{
        loop: true,
        autoplay: 1800,
        pagination: '.swiper-pagination',
        autoplayDisableOnInteraction: false
    });

    var _uid = fnBase.huoqu(0, "uid");
    //var _id = decodeURIComponent(fnBase.request("roomId"));
    var frontURL="http://192.168.1.109:8080/hotel/mobile/info";
    var postData={"roomId":1};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        //if(data.success){
            //�ֲ�ͼ
            //$(".swiper-container .swiper-wrapper").html("");
            //var lunbo_str = '';
            //var lunbo_length = data.length;
            //console.log(lunbo_length);
            //for (var i = 0; i < lunbo_length; i++){
            //    lunbo_str += '<div class="swiper-slide"><img src="' + data.apartment.pic3[i] + '"/></div>';
            //}
            //$(".swiper-container .swiper-wrapper").append(lunbo_str);
            //��Դ�ſ�

            $(".information").html("");
            var priceStr ='<h1 class="ic_house">'+data.apartment.community+'</h1><p class="info_L24">'+data.apartment.balcony+"��"+data.apartment.bathroom+"��"+
                data.apartment.bedroom+"��"+"-"+data.room.direction+'</p>' +
                '<p class="info_L24 label-adders">'+data.apartment.location+data.apartment.address+'</p><p class="info_L24 label-group"><i class="label-type1">'
                +data.apartment.apartmenttype+'</i></p><span class="label-price">'+data.apartment.dayPrice+'<small>/��</small></span>'
            $(".information").append(priceStr);
            //��Դ��Ϣ
            $(".housing .i_inf ul").html("");
            var massage='<li><span class="inf_sp">С��</span><span>'+data.apartment.community+'</span></li><li><span class="inf_sp">��Ԫ</span><span>'+data.apartment.num_building
                +'</span></li><li><span class="inf_sp">���</span><span>'+data.apartment.square+"�O"+'</span></li><li><span class="inf_sp">¥��</span><span>'+data.apartment.totalfloor
                +'</span></li><li><span class="inf_sp">��ס</span><span>'+data.apartment.capacity+"��"+'</span></li><li><span class="inf_sp">����</span><span>'+data.apartment.balcony+"��"+data.apartment.bathroom+"��"+
                data.apartment.bedroom+"��"+'</span></li>';
            $(".housing .i_inf ul").append(massage);
        //    ��Դ����
            $(".allocation .deploy ul").html("");
            var facility=data.info.facilities;
            for(var i=0;i<facility.length;i++){
                var str='<li><i>'+data.info.facilities[i]+'</i></li>';
            }
            $(".allocation .deploy ul").append(str);
        //    ��Դ����
            $(".describe #serviceIntro2").text('+data.info.description+');
        //}else{
        //    fnBase.myalert(data.room.descriptionPersonal)
        //}
    });


    //    ����
    var frontURL='';
    fnBase.commonAjax(frontURL,{'param':93},function(data){
        console.log(data);
        if(data.status=="1"){
            $(".criticism ul").html("");
            var plStr = '';
            for(var i=0;i<data.info.length;i++){
                plStr+='<li><p class="appInfo"><span class="appName">'+data.info[i].user_nickname+'</span><i>5��</i></p><p class="appText">' + data.info[i].content + '</p><p class="appDate">'+data.info[i].ctime+'</p></li>'
            }
            $(".criticism ul").append(plStr)
        }
    });
    //    ����

   //  ����ԤԼ
    var _uid='';
    $(".navbar a").live('click',function(){
        ////�ж�uid
        if (_uid == null || _uid == "undefined" || _uid == "") {
            window.location.href = "login.html";
            return;
        }
        var attr_Str = attrList.join(",");
        var postData = {
            "sid": _id,
            "uid": _uid,
            "attr": attr_Str,
            "shopid": _shopid
        };
       var frontURL=Constant.URL+"/";
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.status=="1"){
                window.location.href=""
            }
        })



    })


});
