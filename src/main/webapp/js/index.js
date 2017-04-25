

$(document).ready(function(){
    scrollNav();
    getData();
    alertSearch.info();

    function scrollNav(){
        $(window).scroll(function(){
            var stop=$(window).scrollTop();
            console.log(stop)
            if(stop>40){
                $(".header-mobile").fadeIn().addClass("scro")
            }else{
                $(".header-mobile").removeClass("scro");
            }
        })
    }
});

var alertSearch={
    info:function(){
        var _uid = fnBase.huoqu(0, "uid");
        var openDoc=$(".Address-search");
        $(".group-search").click(function(){
            openDoc.show();
            $(".Address-search .searInput").find(".serchText").val("").focus();
        });

        $(".header-mobile .link-btn").click(function(){
            if (_uid == null || _uid == "undefined" || _uid == "") {
                window.location.href = "login.html";
                return;
            }

        })
    }

};


function getData(){
    var frontURL=Constant.URL+'/mobile/home';
    var postData ={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        //if(data.status==1){
        //   轮播

            var lunboStr='';
            var lunbo_length=data.homeRooms[0].pic3.length;

            $(".swiper-container .swiper-wrapper").html("");
            if(lunbo_length>0){
                for (var i = 0; i < lunbo_length; i++) {
                    lunboStr += '<div class="swiper-slide"> <a href="javascript:void(0)"><img src="'+"http://192.168.1.109:8080/hotel/images/"+data.homeRooms[0].pic3[i]+'"/></a></div>';
                }
                $(".swiper-container .swiper-wrapper").append(lunboStr);
                var mySwiper = new Swiper ('.swiper-container',{
                    loop: true,
                    autoplay: 1800,
                    pagination: '.swiper-pagination',
                    autoplayDisableOnInteraction: false
                });
            }
        //}else{
        //    fnBase.myalert(data.msg)
        //}
     //房间分类
     //   $(".hot-issue").html("");
        var category_str='';
        //var categoryLength=data.info.category.length;
        //if(categoryLength>4){
        //    categoryLength=4;
        //}
        //for(var i=0;i<categoryLength;i++){
        //    category_str+='<a href="house_particulars.html?cate='+data.info.category[i].id+'"><div class="img-wrapper"><img src="'+data.info.category[i].attach+'"/></div><div>'+data.info.category[i].title+'</div></a>';
        //}
        //$(".hot-issue").append(category_str);
        $('.goodlist li').unbind('click').click(
            function(){
                var id=$(this).attr('proID');
                var isoff= $(this).attr('offer');
                window.location.href="house_particulars.html?id="+encodeURIComponent(id)+"&offer="+encodeURIComponent(isoff);
            }
        );


    // 热门房源
        var str='';
        var isoffers;
        $('.plCon .goodlist').html("");
        for(var i=0;i<data.homeRooms.length;i++){
            str+='<li proID='+data.homeRooms[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+"http://192.168.1.109:8080/hotel/images/"+data.homeRooms[i].pic3[1]+'"></a><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].community+'</a></h1><h2 class="text-ellipsis">'+data.homeRooms[i].balcony+"室"+data.homeRooms[i].bathroom+"厅"+
                data.homeRooms[i].bedroom+"卫"+"-"+data.homeRooms[i].direction+'</h2><p class="text-ellipsis address">'+data.homeRooms[i].location+data.homeRooms[i].address+'</p><p class="label-group"><i class="label-type1">'+data.homeRooms[i].apartmenttype+'</i></p><span class="label-price">'+data.homeRooms[i].dayPrice+'<small>/天</small></span></div></li>';
        }
        $('.plCon .goodlist').append(str);


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
        $('.title-serce').unbind('click').click(
            function(){
                window.location.href="houseType.html";
            }
        );

       //青舍生活
       // $(".service-image").html("");
       // var ad_str='';
       // var ad_length=data.info.indexPush.length;
       // for(var i=0;i<ad_length;i++){
       //     ad_str+='<div class="service service-image"><h2 class="title-serce">青舍生活<a href="">更多</a></h2><a href="+data.info.indexPush[i].url+"><img src="'+data.info.indexPush[i].attach+'"></a></div>';
       // }
       // $(".service-image").append(ad_str);

        $('.service-image .title-serce').unbind('click').click(
            function(){
                //var id=$(this).attr('proID');
                window.location.href="Life.html";
            }
        );

    });

    $('.header-mobile .input-search').focus(function(){
        fnBase.keep(1,"mode",0);
        window.location.href="search.html";
    });

}