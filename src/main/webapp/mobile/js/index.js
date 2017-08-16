$(document).ready(function () {

    // 新注册礼包
	 $(".Masking-out,.newUser").hide();
    var _of=fnBase.huoqu(1,"newUser");
    console.log(_of)
	if(_of==1){
    	$(".Masking-out,.newUser").show();
    };
	 $(".newUser i").click(function(){
		fnBase.keep(1,"newUser",0);
		$(".Masking-out,.newUser").hide();
		console.log(fnBase.huoqu(1,"newUser"))
	})
	//====================2017/08/15========================
    var _id = decodeURIComponent(fnBase.request("id"));
	var _status = decodeURIComponent(fnBase.request("status"));
	console.log('此用户ID：'+_id+'状态：'+_status)
	//fnBase.keep( 0,"uid",_id);
    //微信用户完善信息
	if(_status==""||_status==null||_status=="undefined"||_status==1){
		$("#main_con,#masking").hide(); 
	}else if(_status==0){
		$("#main_con,#masking").show();
	}
	
	
	$("#black_houme").click(function(){
		_status=1;
		$("#masking").hide();
		$("#main_con").remove();
	});
	
	var resiger={
		    inputList:new Array(),
		    btnList:new Array(),
		    addEvent:function(){
		        resiger.inputList=[];
		        resiger.btnList=[];
		        resiger.inputList.push($("#tel"),$("#password"),$("#yzm"));
		        resiger.btnList.push($("#fetch-cmd"),$("#login_submit"));

		        //短信验证码
		        resiger.btnList[0].click(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            if(phoneNumber==""){
		                fnBase.myalert("请填写手机号码");
		                return;
		            }
		            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
		            if (!myreg.test(phoneNumber)) {
		                fnBase.myalert("手机号码有误！ 请输入11位数字");
		                return;
		            }
		            var frontURL=Constant.URL+"/mobile/sendVCode";
		            var postData={"tel":phoneNumber};
		            fnBase.commonAjax(frontURL,postData,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                    fnBase.myalert("短信发送成功");
		                }else{
		                    fnBase.myalert("短信发送失败")
		                }
		            });
		            resiger.timePrompt();
		        });


		        resiger.inputList[2].blur(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            var _yzm=resiger.inputList[2].val();
		            if(_yzm==""){
		                fnBase.myalert("请填写验证码");
		                return;
		            }
		            var frontURL=Constant.URL+"/mobile/checkVCode";
		            var postDatat={"vCode":_yzm,"tel": phoneNumber};
		            fnBase.commonAjax(frontURL,postDatat,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                    resiger.inputList[2].val(data.content);
		                }else{
		                    resiger.inputList[2].val(data.content);
		                }
		            });
		        });
		        //确认保存;
		        resiger.btnList[1].click(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            if(phoneNumber==""){
		                fnBase.myalert("请填写手机号码");
		                return;
		            }
		            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
		            if (!myreg.test(phoneNumber)) {
		                fnBase.myalert("手机号码有误！ 请输入11位数字");
		                return;
		            }
		            var _yzm=resiger.inputList[2].val();
		            if(_yzm==""){
		                fnBase.myalert("请填写验证码");
		                return;
		            }
		            var password=resiger.inputList[1].val();
		            if(password==""){
		                fnBase.myalert("请填写密码");
		                return;
		            }
		            if(password.length<6){
		                fnBase.myalert("密码至少是6位");
		                return;
		            }
		
		            var frontURL=Constant.URL+"/mobile/find1";
		            var postDatat={"tel":phoneNumber,"psd":password,'id':_id};
		            fnBase.commonAjax(frontURL,postDatat,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                	$("#main_con,#masking").hide();
		                	fnBase.myalert(data.content);
		                    sessionStorage.clear();
		                    localStorage.clear();
		                }else{
		                    fnBase.myalert(data.content)
		                }
		                window.location.href = "index.html";
		            })



		        })

		    },
		    timePrompt:function(){
		        var total=60;
		        var mytimecont;
		        resiger.btnList[0].text("60秒后重发");
		        clearInterval(mytimecont);
		        mytimecont=setInterval(function(){
		            total=total-1;
		            var str=total+"秒后重发";
		            resiger.btnList[0].text(str);
		            if(total<=0){
		                clearInterval(mytimecont);
		                resiger.sendCoded=false;
		                resiger.btnList[0].text("短信验证码");
		            }
		        },1000)
		    }
		};
	
	
	
    resiger.addEvent();
	//========================================================
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
       
        
        $(".swiper-container-life .swiper-wrapper").html("");
        var ad_str = '';
        var ad_length = data.blogs.results.length;
        for (var i = 0; i < data.blogs.results.length; i++) {
            ad_str += '<div class="swiper-slide"><a pId="' + data.blogs.results[i].id + '"><img src="'+Constant.URL+ data.blogs.results[i].pic + '"><p class="mask">'+data.blogs.results[i].title+'</p><div class="content-mob"><p>'+data.blogs.results[i].abs_text+'</p><img src="'+Constant.URL+data.blogs.results[i].pic+'"></div></a></div>';
        }
        $(".swiper-container-life .swiper-wrapper").append(ad_str);
    //    var mySwiper = new Swiper('.swiper-container-life');
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