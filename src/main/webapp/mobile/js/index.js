$(document).ready(function(){
    getData();
    alertSearch.info();
    var mySwiper = new Swiper ('.swiper-container',{
        loop: true,
        autoplay: 1800,
        pagination: '.swiper-pagination',
        autoplayDisableOnInteraction: false
    });
});

var alertSearch={
    info:function(){
        var openDoc=$(".Address-search");
        $(".group-search").click(function(){
            openDoc.show();
            $(".Address-search .searInput").find(".serchText").val("").focus();
        });

        $(".header-mobile .link-btn").click(function(){
            window.location.href="login.html";
        })
    }

};


function getData(){
    var frontURL=Constant.URL+'/mobile/home';
    var postData ={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.status==1){
        //   轮播
            var lunboStr='';
            var lunbo_length=data.info.lunbo.length;
            $(".swiper-wrapper").html("");
            if(data.data.adv.length>0){
                for (var i = 0; i < data.data.adv.length; i++) {
                    lunboStr += '<div class="swiper-slide"> <a href="javascript:void(0)"> <img src="' + data.info.lunbo[i].attach_ids + '" /> </a> </div>';
                }
                $(".swiper-wrapper").append(lunboStr);
                var mySwiper = new Swiper ('.swiper-container', {
                    loop: true,
                    autoplay: 1800,
                    pagination: '.swiper-pagination',
                    autoplayDisableOnInteraction: false
                });
            }
        }else{
            fnBase.myalert(data.msg)
        }
     //房间分类
     //   $(".hot-issue").html("");
        var category_str='';
        //var categoryLength=data.info.category.length;
        if(categoryLength>4){
            categoryLength=4;
        }
        for(var i=0;i<categoryLength;i++){
            category_str+='<a href="house_particulars.html?cate='+data.info.category[i].id+'"><div class="img-wrapper"><img src="'+data.info.category[i].attach+'"/></div><div>'+data.info.category[i].title+'</div></a>';
        }
        $(".hot-issue").append(category_str);

    // 热门房源
        var str='';
        var isoffers;
        for(var i=0;i<data.info.length;i++){
            str+='<li proID="'+data.info[i].id+'"offer="'+data.info[i].isoffers+'"><div class="item-room"><a class="subpic"><img src="'+data.info[i].attach_ids[0]+'"/></a><h1 class="text-ellipsis"><a href="javascript:void(0)">'+data.info[i].name+'</a></h1><h2 class="text-ellipsis">'+data.info[i].type_Name+"-"+data.info[i].type_aspect+"-"+data.info[i].type_area+"㎡"+'</h2><p class="text-ellipsis address">'+data.info[i].nearby+'</p><p class="label-group"><i class="label-type2">'+data.info[i].mold+'</i></p><span class="label-price">'+data.info[i].date+'<small>/天</small></span></div></li>';
        }
        $('.goodlist').append(str);
        var len = $('.goodlist li').length;
        if(len==0){
            $('#zanwu').show();
            $('body').addClass('wingBg');
        }
        $('.goodlist li').unbind('click').click(
            function(){
                var id=$(this).attr('proID');
                var isoff= $(this).attr('offer');
                window.location.href="house_particulars.html?id="+encodeURIComponent(id)+"&offer="+encodeURIComponent(isoff);
            }
        );
       //青舍生活
        $(".service-image").html("");
        var ad_str='';
        var ad_length=data.info.indexPush.length;
        for(var i=0;i<ad_length;i++){
            ad_str+='<div class="service service-image"><h2 class="title-serce">青舍生活<a href="">更多</a></h2><a href="+data.info.indexPush[i].url+"><img src="'+data.info.indexPush[i].attach+'"></a></div>';
        }
        $(".service-image").append(ad_str);

        $('.title-serce').unbind('click').click(
            function(){
                var id=$(this).attr('proID');
                var isoff= $(this).attr('offer');
                window.location.href="houseType.html";
            }
        );

    });

    $('.header-mobile .input-search').focus(function(){
        fnBase.keep(1,"mode",0);
        window.location.href="search.html";
    });

}