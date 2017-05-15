
$(document).ready(function(){



    ////轮播


    var _uid = fnBase.huoqu(0, "uid");
    var _id = decodeURIComponent(fnBase.request("id"));
    var frontURL=Constant.URL+'/mobile/info';
    var postData={"roomId":_id};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        fnBase.keep(1,"community",data.apartment.community);
        fnBase.keep(1,"dayPrice",data.apartment.dayPrice);
        fnBase.keep(1,"roomType",data.apartment.apartmenttype);
        fnBase.keep(1,"roonWz",data.apartment.num_building);
        fnBase.keep(1,"roomCX",data.room.direction);
        fnBase.keep(1,"num_door",data.apartment.num_door);
        //if(data.success){
            //轮播图
            $(".swiper-container .swiper-wrapper").html("");
            var lunbo_str = '';
            var lunbo_length = data.apartment.pic3.length;
            for (var i = 0; i < lunbo_length; i++){
                lunbo_str += '<div class="swiper-slide"><img src="'+Constant.URL+"/images/"+data.apartment.pic2[i]+'"/></div>';
            }
            $(".swiper-container .swiper-wrapper").append(lunbo_str);
            var mySwiper = new Swiper ('.swiper-container',{
                loop: true,
                autoplay: 1800,
                pagination: '.swiper-pagination',
                autoplayDisableOnInteraction: false
            });
            //房源概况

            $(".information").html("");
            var priceStr ='<h1 class="ic_house">'+data.apartment.address+"-" +data.apartment.community+'</h1><p class="info_L24">'+data.apartment.balcony+"室"+data.apartment.bathroom+"厅"+
                data.apartment.bedroom+"卫"+"-"+data.room.direction+'</p>' +
                '<p class="info_L24 label-adders">'+data.apartment.location+data.apartment.address+'</p><p class="info_L24 label-group"><i class="label-type1">'
                +data.apartment.apartmenttype+'</i></p><span class="label-price">'+data.apartment.dayPrice+'<small>/天</small></span>'
            $(".information").append(priceStr);

            //房源信息
            $(".housing .i_inf ul").html("");
            var massage='<li><span class="inf_sp">小区</span><span>'+data.apartment.community+'</span></li><li><span class="inf_sp">面积</span><span>'+data.apartment.square+"㎡"+'' +
                '</span></li><li><span class="inf_sp">可住</span><span>'+data.apartment.capacity+"人"+'</span></li><li><span class="inf_sp">户型</span><span>'+data.apartment.balcony+"室"+data.apartment.bathroom+"厅"+
                data.apartment.bedroom+"卫"+'</span></li>';
            $(".housing .i_inf ul").append(massage);

        //    房源配置
            $(".allocation .deploy ul").html("");
            var str='';
            for(var i=0;i<data.apartment.facilityEntity.length;i++){
                str+='<li><i>'+data.apartment.facilityEntity[i].description+'</i></li>';
            }
            $(".allocation .deploy ul").append(str);
        //    房源描述
            $(".describe #serviceIntro2").text(data.room.descriptionPersonal);
        //    房源特色
        $(".describe .feature  ul").html("");
            var des='';
            for(var i=0;i<data.apartment.featureEntity.length;i++){
                des+='<li><i>'+data.apartment.featureEntity[i].description+'</i></li>';
            }
        $(".describe .feature ul").append(des);

    });
    

    //    评价列表显示
    var frontURL=Constant.URL+'/mobile/getRoomRates';
    var postData={"roomId":_id};
    fnBase.commonAjax(frontURL,postData,function(data){
    	$("#pingjia span").text(data.pingjun+"星")
    })
    
    var frontURL=Constant.URL+'/mobile/get';
    var postData={"roomId":_id,"page":1};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        
            $(".criticism ul").html("");
            var plStr = '';
            for(var i=0;i<data.results.length;i++){
                plStr+='<li><p class="appInfo"><span class="appName">'+data.results[i].fromWho+'</span><i>入住时间：'+data.results[i].time+'</i></p><p class="appText">'+data.results[i].feel+'</p><p class="pl-pic">';
                if(data.results[i].pics.length>0){
                	$(".panel .pl-pic").show();
                	for(var j=0;j<data.results[i].pics.length;j++){
                		plStr+='<img src="/images/' + data.results[i].pics[0] +'">'
                	}
                }
                if(data.results[i].reply==null){
                	$(".panel .appDate").hide();
                }else{
                	$(".panel .appDate").show();
                	plStr+='</p><p class="appDate" ><span style="font-weight: bold;">房东回复：</span><span>'+data.results[i].reply+'</span></p>'
                }
                plStr+='</li>';
            }
            $(".criticism ul").append(plStr)
       
    });
    //    评价

   //  立即预约
    $(".navbar a").live('click',function() {
        $("#masking").show(10, function () {
            $(".alert-content").animate({bottom: 0}, 300);
        });
    });

    //判断有房没房
    $("#appDate2").on('change',function(){

        var checkIn= $("#appDate").val();
        var leave= $("#appDate2").val();
        if(checkIn>=leave){
            fnBase.myalert("请重新选择时间");
            $("#appDate").val("");
            $("#appDate2").val("");
            return;
        }else{
            var frontURL=Constant.URL+'/mobile/checkAvailable';
            var postData={"startTime":checkIn,"endTime":leave,"roomId":_id};
            fnBase.commonAjax(frontURL,postData,function(data){
                console.log(data);
                if(data.content.length>0){
                    $(".alert-content .hint").html("<i>!</i>您所选时间内没有空房,请重新选择日期").css("color","red").show();
                    $(".but-success").hide();
                }else{
                    $(".alert-content .hint").html("")
                    $(".but-success").show();
                }
            })
        }

    });


    $(".alert-content .but-success").click(function(){
        $("#masking").hide(10,function(){
            $(".alert-content").animate({bottom:'-3.7rem'},300);
        });
        var checkIn= $("#appDate").val();
        var leave= $("#appDate2").val();
        var _id = decodeURIComponent(fnBase.request("id"));
        var frontURL=Constant.URL+'/mobile/module';
        var postData={"startTime":checkIn,"endTime":leave,"apartmentId":_id};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            fnBase.keep(1,'startTime',data.oStart);
            fnBase.keep(1,'endTime',data.oEnd);
            fnBase.keep(1,'oTotalDay',data.oTotalDay);
            fnBase.keep(1,'oTotalPrice',data.oTotalPrice);
            window.location.href="order.html?id="+encodeURIComponent(_id);
            fnBase.keep(1,"YJpic",data.oCashPledge);
        })
    });
    //Click anywhere to close #masking;
    $("#masking").click(function(){
        $(this).hide(10,function(){
            $(".alert-content").animate({bottom:'-3.7rem'},300);
        });
    });

        //window.location.href="order.html";
        ////判断uid
        //if (_uid == null || _uid == "undefined" || _uid == "") {
        //    window.location.href = "login.html";
        //    return;
        //}
        //var postData = {
        //    "roomId": _id
        //    //"uid": _uid
        //};
       //var frontURL=Constant.URL+"/";
       // fnBase.commonAjax(frontURL,postData,function(data){
       //     console.log(data);
       //     window.location.href="order.html"
       // })






});
