
$(document).ready(function () {
	 var _onesid=decodeURIComponent(fnBase.request("id"));
	 var _status = decodeURIComponent(fnBase.request("status"));
	 if(_onesid==""||_onesid=="null"||_onesid=="undefined"){
		 fnBase.keep(0,'status',1);
	 }else{
		 fnBase.keep(0,'userid',_onesid);
	     fnBase.keep(0,'status',_status);
		 console.log(_onesid);
	 };
	 
    // 新注册礼包  discounts
	 var discounts=decodeURIComponent(fnBase.request("discounts"));
	 if(discounts==0){
		 $(".newUser").show();
	 }else if(discounts==null||discounts=="undefined"){
		 $(".newUser").hide();
	 }

	 $(".newUser .nest").click(function(){
		 $(".newUser").remove();
			window.location.href='index.html'
	 });
	
		

	//使用微信头像
	 var _uid=fnBase.huoqu(0,"userid");
	 if(_uid==null || _uid=="undefined" || _uid==""){
	 	$(".header-mobile .link-btn img").attr("src",Constant.URL+"/images/face-90x90.png");
	 }else{
		 var frontURL=Constant.URL+'/mobile/detailsData';
	     var postData={id:_uid};
	     fnBase.commonAjax(frontURL,postData,function(data){
	         console.log(data);
	         if(data.details.avatar==""||data.details.avatar==null){
	             $(".header-mobile .link-btn img").attr("src",Constant.URL+"/images/face-90x90.png");
	         }else{
	             $(".header-mobile .link-btn img").attr("src",data.details.avatar);
	         }
	     });
	 };

	
	
	
    scrollNav();
    getData();
    alertSearch.info();
    

    
   
    
//  横向滚动begin
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
        	 var vio=data.homeRooms[i].position.bd_wei_zhi;
     		     vio=vio.slice(4,vio.length);
            str += '<li proID=' + data.homeRooms[i].id + '><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="' + Constant.URL+'/images/' + data.homeRooms[i].fang_jian_tu[0] + '"></a><div class="right-content"><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].position.xa_wei_zhi+"-" + data.homeRooms[i].position.xiao_qu + '</a></h1><h2 class="text-ellipsis">' + data.homeRooms[i].basic_info.shi + "室" + data.homeRooms[i].basic_info.ting + "厅" +
                data.homeRooms[i].basic_info.wei + "卫" + '</h2><p class="text-ellipsis address">'+vio+'-'+data.homeRooms[i].position.xiao_qu + '</p><p class="label-group"><span class="czs-label-info-l"></span>常住优惠</p><span class="label-price">' + data.homeRooms[i].basic_info.jia_ge + '<small>/天</small></span></div></div></li>';
           
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
                window.location.href = "house_particulars.html?id=" + encodeURIComponent(id) + "";
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




//青舍生活
function life() {
    var frontURL = Constant.URL + '/mobile/story';
    var postData = {"page": 1};
    fnBase.commonAjax(frontURL, postData, function (data) {
       
        
        $(".swiper-container-life .swiper-wrapper").html("");
        var ad_str = '';
        var ad_length = data.blogs.results.length;
        for (var i = 0; i < data.blogs.results.length; i++) {
            ad_str += '<div class="swiper-slide"><a pId="' + data.blogs.results[i].id + '"><img src="'+Constant.URL+ data.blogs.results[i].pic + '"><p class="mask">'+data.blogs.results[i].title+'</p><div class="content-mob"><p>'+data.blogs.results[i].abs_text+'</p><img src="'+Constant.URL+data.blogs.results[i].pic+'"></div></a></div>';
        }
        $(".swiper-container-life .swiper-wrapper").append(ad_str);

        var mySwiper = new Swiper('.swiper-container-life', {
            loop: true,
            autoplay: 2000,
            autoplayDisableOnInteraction: false
        });
    });
    $(".service-image a").live('click', function () {
        var _page = $(this).attr("pId");
        window.location.href = 'Life.html?id=' + encodeURIComponent(_page);
    });

}