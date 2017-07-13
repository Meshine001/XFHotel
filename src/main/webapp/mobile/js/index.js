$(document).ready(function () {
    scrollNav();
    getData();
    alertSearch.info();
    
    // 托管
    $(".hot-issue .tuoguan").click(function(){
    	fnBase.myalert('正在开发中...')
    })
    
    
    // 新注册礼包
    $(".Masking-out,.newUser").hide();
    
    if(fnBase.huoqu(0, "newUser")==1){
    	$(".Masking-out,.newUser").show();
    }
    $(".newUser i").click(function(){
    	$(".Masking-out,.newUser").hide();
    })
    
//    横向滚动begin
    var myScroll;
	 myScroll = new IScroll('#proSort', { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
    myScroll.refresh(); 
//  横向滚动end

    $('.header-mobile .input-search').focus(function () {
        fnBase.keep(1, "mode", 0);
        window.location.href="houseType.html";
    });

    life();
    function scrollNav() {
        $(window).scroll(function () {
            var stop = $(window).scrollTop();
            if (stop > 40) {
                $(".header-mobile").fadeIn().addClass("scro")
            } else {
                $(".header-mobile").removeClass("scro");
            }
        })
    }

});

var alertSearch = {
    info: function () {
        var _uid = fnBase.huoqu(0, "uid");
        var openDoc = $(".Address-search");
        $(".group-search").click(function () {
            $(".Address-search .searInput").find(".serchText").val("").focus();
        });

        $(".header-mobile .link-btn").click(function () {
            if (_uid == null || _uid == "undefined" || _uid == "") {
                window.location.href = "login.html";
                return;
            }

        })
    }

};


function getData() {
	fnBase.loadShow();
    var frontURL = Constant.URL + '/mobile/home';
    var postData = {};
    fnBase.commonAjax(frontURL, postData, function (data) {
    	fnBase.loadHide();
        console.log(data);
        var mySwiper = new Swiper('.swiper-container', {
            loop: true,
            autoplay: 1800,
            pagination: '.swiper-pagination',
            autoplayDisableOnInteraction: false
        });

        var category_str = '';
        $('.goodlist li').unbind('click').click(
            function () {
                var id = $(this).attr('proID');
                var isoff = $(this).attr('offer');
                window.location.href = "house_particulars.html?id=" + encodeURIComponent(id) + "&offer=" + encodeURIComponent(isoff);
            }
        );
        // 热门房源
        var str = '';
        var isoffers;
        $('.plCon .goodlist').html("");
        for (var i = 0; i < data.homeRooms.length; i++) {
            str += '<li proID=' + data.homeRooms[i].id + '><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="' + Constant.URL+'/images/' + data.homeRooms[i].fang_jian_tu[0] + '"></a><div class="right-content"><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].position.xa_wei_zhi+"-" + data.homeRooms[i].position.xiao_qu + '</a></h1><h2 class="text-ellipsis">' + data.homeRooms[i].basic_info.shi + "室" + data.homeRooms[i].basic_info.ting + "厅" +
                data.homeRooms[i].basic_info.wei + "卫" + '</h2><p class="text-ellipsis address">'+data.homeRooms[i].position.bd_wei_zhi+'-'+data.homeRooms[i].position.xiao_qu + '</p><p class="label-group"><i class="label-type1">' + data.homeRooms[i].basic_info.lei_xing + '</i></p><span class="label-price">' + data.homeRooms[i].basic_info.jia_ge + '<small>/天</small></span></div></div></li>';
           
        }
        $('.plCon .goodlist').append(str);


        var len = $('.goodlist li').length;
        if (len == 0) {
            $('#zanwu').show();
            $('body').addClass('wingBg');
        }
        $('.goodlist li').unbind('click').click(
            function () {
                var id = $(this).attr('proID');
                var isoff = $(this).attr('offer');
                window.location.href = "house_particulars.html?id=" + encodeURIComponent(id) + "&offer=" + encodeURIComponent(isoff);
            }
        );
        $('.title-serce ').unbind('click').click(
            function () {
                window.location.href = "houseType.html";
            }
        );


        $('.service-image .title-serce').unbind('click').click(
            function () {
                window.location.href = "Lifelist.html";
            }
        );

    });


}

var _uid=fnBase.huoqu(0,"uid");
if(_uid==null || _uid=="undefined" || _uid==""){
    $(".header-mobile .link-btn img").attr('src',"/images/my-index.png");
}else{
    getmsg();
}

function getmsg(){
    var frontURL=Constant.URL+'/mobile/detailsData';
    var postData={id:_uid};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.details.avatar==""||data.details.avatar==null){
            $(".header-mobile .link-btn img").attr("src","/images/my-index.png");
        }else{
            $(".header-mobile .link-btn img").attr("src",Constant.URL+'/images/'+data.details.avatar);
        }
    });
}


//青舍生活
function life() {
    var frontURL = Constant.URL + '/mobile/story';
    var postData = {"page": 1};
    fnBase.commonAjax(frontURL, postData, function (data) {
        console.log(data);
        //$(".service-image").html("");
        var ad_str = '';
        var ad_length = data.blogs.results.length;
        for (var i = 0; i < 2; i++) {
            ad_str += '<a pId="' + data.blogs.results[i].id + '"><img src="'+Constant.URL+ data.blogs.results[i].pic + '"><p class="mask">'+data.blogs.results[i].title+'</p><div class="content-mob"><p>'+data.blogs.results[i].abs_text+'</p><img src="'+Constant.URL+data.blogs.results[i].pic+'"></div></a>';
        }
        $(".service-image").append(ad_str);
    });
    $(".service-image a").live('click', function () {
        var _page = $(this).attr("pId");
        window.location.href = 'Life.html?id=' + encodeURIComponent(_page);
    });

}