
$(document).ready(function(){
	 var userid,wechatOpenId="",_status='0',ss="0",uid="";
	 var redirect = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=index.html#wechat_redirect';
	    
   	 uid=fnBase.request("id");
   	 userid=fnBase.huoqu(0,'userid');

	 if(userid=="undefined" || userid==undefined || userid==null){
		 if(uid=="undefined" || uid==undefined ||uid==null){
			  window.location.href = redirect; 	
			  return; 
		 }else{
			 uid=fnBase.request("id");
			 userid=fnBase.keep(0,'userid',uid);
			 userid=fnBase.huoqu(0,'userid');
		 }
	 };
	 
 
	 
	 
   //使用微信头像

	var frontURL=Constant.URL+'/mobile/detailsData';
	var postData={id:userid};
	fnBase.commonAjax(frontURL,postData,function(data){
	     console.log(data);
		 $(".topInnerNav .myimg img").attr("src",data.details.avatar);       
	});     
	
	var mySwiper = new Swiper ('.indexCenterCon .swiper-container',{
        loop: true,
        autoplay: 1800,
        pagination: '.swiper-pagination',
        autoplayDisableOnInteraction: false
    })
   
   
   
   //获取房源
   getData();
   function getData(){
   	  var frontURL = Constant.URL + '/mobile/home';
    var postData = {};
    fnBase.commonAjax(frontURL, postData, function (data) {
    	console.log(data);
    	var str = '';
        var isoffers;
        $('.hatHouse ul').html("");
        for (var i = 0; i < data.homeRooms.length; i++) {
        	 var vio=data.homeRooms[i].position.bd_wei_zhi;
     		     vio=vio.slice(4,vio.length);
            str += '<li proID=' + data.homeRooms[i].id + '><a href="javascript:;"><div class="fn-img"><img src="' + Constant.URL+'/images/' + data.homeRooms[i].fang_jian_tu[0] + '"/><span class="price">￥' + data.homeRooms[i].basic_info.jia_ge + '</span></div><div class="fn-txt"><p>'+data.homeRooms[i].position.xa_wei_zhi+"-" + data.homeRooms[i].position.xiao_qu + '</p><p class="hu-c">' + data.homeRooms[i].basic_info.shi + "室" + data.homeRooms[i].basic_info.ting + "厅" +data.homeRooms[i].basic_info.wei + "卫" + '<span>宜住'+data.homeRooms[i].basic_info.reng_shu+'人</span><span>常住优惠</span></p></div></a></li>';
           
        }
        $('.hatHouse ul').append(str);
        
        $('.hatHouse ul').on('click','li',function(){
        	 var id = $(this).attr('proID');
             window.location.href = "house_details.html?id=" + encodeURIComponent(id) + "";
        })
    })
   };
   
   //方位筛选
   $(".swiper-container .swiper-slide").on('click',function(){
	   var data=$(this).attr('selectweizhiid');
	   window.location.href = "listings.html?ofo=" + encodeURIComponent(data) + "";
   })
   
   //secash
   $(".subparent").on('click',function(){
	   window.location.href = "listings.html";
   })
   
   //青舍生活
   life();
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
            autoplay: 1800,
            autoplayDisableOnInteraction: false
        });
    });
    $(".service-image a").live('click', function () {
        var _page = $(this).attr("pId");
        window.location.href = 'Life.html?id=' + encodeURIComponent(_page);
    });

}

   
})