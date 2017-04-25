
$(document).ready(function(){



    ////�ֲ�


    var _uid = fnBase.huoqu(0, "uid");
    var _id = decodeURIComponent(fnBase.request("id"));
    var frontURL=Constant.URL+'/mobile/info';
    var postData={"roomId":_id};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        fnBase.keep(1,"community",data.apartment.community);
        fnBase.keep(1,"dayPrice",data.apartment.dayPrice);
        fnBase.keep(1,"roomType",data.apartment.apartmenttype);

        //if(data.success){
            //�ֲ�ͼ
            $(".swiper-container .swiper-wrapper").html("");
            var lunbo_str = '';
            var lunbo_length = data.apartment.pic3.length;
            for (var i = 0; i < lunbo_length; i++){
                lunbo_str += '<div class="swiper-slide"><img src="'+"http://192.168.1.109:8080/hotel/images/"+data.apartment.pic2[i]+'"/></div>';
            }
            $(".swiper-container .swiper-wrapper").append(lunbo_str);
            var mySwiper = new Swiper ('.swiper-container',{
                loop: true,
                autoplay: 1800,
                pagination: '.swiper-pagination',
                autoplayDisableOnInteraction: false
            });
            //��Դ�ſ�

            $(".information").html("");
            var priceStr ='<h1 class="ic_house">'+data.apartment.community+'</h1><p class="info_L24">'+data.apartment.balcony+"��"+data.apartment.bathroom+"��"+
                data.apartment.bedroom+"��"+"-"+data.room.direction+'</p>' +
                '<p class="info_L24 label-adders">'+data.apartment.location+data.apartment.address+'</p><p class="info_L24 label-group"><i class="label-type1">'
                +data.apartment.apartmenttype+'</i> <span class="label-yj">Ѻ��200</span></p><span class="label-price">'+data.apartment.dayPrice+'<small>/��</small></span>'
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
            var str='';
            for(var i=0;i<data.apartment.facilityEntity.length;i++){
                str+='<li><i>'+data.apartment.facilityEntity[i].description+'</i></li>';
            }
            $(".allocation .deploy ul").append(str);
        //    ��Դ����
            $(".describe #serviceIntro2").text(data.room.descriptionPersonal);

        //}else{
        //    fnBase.myalert(data.room.descriptionPersonal)
        //}
    });


    //    �����б���ʾ
    var frontURL=Constant.URL+'/mobile/comment';
    var postData={};
    fnBase.commonAjax(frontURL,postData,function(data){
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
    $(".navbar a").live('click',function() {
        $("#masking").show(10, function () {
            $(".alert-content").animate({bottom: 0}, 300);
        });
    });

    //�ж��з�û��
    $("#appDate2").on('change',function(){

        var checkIn= $("#appDate").val();
        var leave= $("#appDate2").val();
        if(checkIn>=leave){
            fnBase.myalert("������ѡ��ʱ��");
            $("#appDate").val("");
            $("#appDate2").val("");
            return;
        }else{
            var frontURL=Constant.URL+'/mobile/checkAvailable';
            var postData={"startTime":checkIn,"endTime":leave,"roomId":_id};
            fnBase.commonAjax(frontURL,postData,function(data){
                console.log(data);
                if(data.content.length>0){
                    $(".alert-content .hint").html("<i>!</i>����ѡʱ����û�пշ�,������ѡ������").css("color","red").show();
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

        })
    });
    //Click anywhere to close #masking;
    $("#masking").click(function(){
        $(this).hide(10,function(){
            $(".alert-content").animate({bottom:'-3.7rem'},300);
        });
    });

        //window.location.href="order.html";
        ////�ж�uid
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
