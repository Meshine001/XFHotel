
$(document).ready(function(){
    ////轮播
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
            //轮播图
            //$(".swiper-container .swiper-wrapper").html("");
            //var lunbo_str = '';
            //var lunbo_length = data.length;
            //console.log(lunbo_length);
            //for (var i = 0; i < lunbo_length; i++){
            //    lunbo_str += '<div class="swiper-slide"><img src="' + data.apartment.pic3[i] + '"/></div>';
            //}
            //$(".swiper-container .swiper-wrapper").append(lunbo_str);
            //房源概况

            $(".information").html("");
            var priceStr ='<h1 class="ic_house">'+data.apartment.community+'</h1><p class="info_L24">'+data.apartment.balcony+"室"+data.apartment.bathroom+"厅"+
                data.apartment.bedroom+"卫"+"-"+data.room.direction+'</p>' +
                '<p class="info_L24 label-adders">'+data.apartment.location+data.apartment.address+'</p><p class="info_L24 label-group"><i class="label-type1">'
                +data.apartment.apartmenttype+'</i></p><span class="label-price">'+data.apartment.dayPrice+'<small>/天</small></span>'
            $(".information").append(priceStr);
            //房源信息
            $(".housing .i_inf ul").html("");
            var massage='<li><span class="inf_sp">小区</span><span>'+data.apartment.community+'</span></li><li><span class="inf_sp">单元</span><span>'+data.apartment.num_building
                +'</span></li><li><span class="inf_sp">面积</span><span>'+data.apartment.square+"㎡"+'</span></li><li><span class="inf_sp">楼层</span><span>'+data.apartment.totalfloor
                +'</span></li><li><span class="inf_sp">可住</span><span>'+data.apartment.capacity+"人"+'</span></li><li><span class="inf_sp">户型</span><span>'+data.apartment.balcony+"室"+data.apartment.bathroom+"厅"+
                data.apartment.bedroom+"卫"+'</span></li>';
            $(".housing .i_inf ul").append(massage);
        //    房源配置
            $(".allocation .deploy ul").html("");
            var facility=data.info.facilities;
            for(var i=0;i<facility.length;i++){
                var str='<li><i>'+data.info.facilities[i]+'</i></li>';
            }
            $(".allocation .deploy ul").append(str);
        //    房源描述
            $(".describe #serviceIntro2").text('+data.info.description+');
        //}else{
        //    fnBase.myalert(data.room.descriptionPersonal)
        //}
    });


    //    评价
    var frontURL='';
    fnBase.commonAjax(frontURL,{'param':93},function(data){
        console.log(data);
        if(data.status=="1"){
            $(".criticism ul").html("");
            var plStr = '';
            for(var i=0;i<data.info.length;i++){
                plStr+='<li><p class="appInfo"><span class="appName">'+data.info[i].user_nickname+'</span><i>5星</i></p><p class="appText">' + data.info[i].content + '</p><p class="appDate">'+data.info[i].ctime+'</p></li>'
            }
            $(".criticism ul").append(plStr)
        }
    });
    //    评价

   //  立即预约
    var _uid='';
    $(".navbar a").live('click',function(){
        ////判断uid
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
