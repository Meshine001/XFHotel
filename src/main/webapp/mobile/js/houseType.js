
$(document).ready(function(){
    addres();
//           ҳ��1 ���ɸѡ�����򵯳�ҳ��2
    $("#filterbtn").click(function(){
        //$(".sx_filter").hide();
        //$("#filter").show();
    });
//           ҳ��2 {��� ���ذ�ť and  ȷ����ť ����ҳ��1}��
    $(" #back_main,#confirm").click(function(){
        $("#filter").hide();
        $(".sx_filter").show();
    });

//           ҳ��2 ɸѡ���� ��ѡ����ʽ��
    $(".px-list  li").click(function(){
        $(this).addClass("p_list_on").siblings().removeClass("p_list_on");
    });
    $(".r-list ul li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
//          ҳ��2 ��� �����ѯ ����ҳ��3 ��
    $(".quyu").click(function(){
        $("#filter").hide();
        $(".qy_filter ").show();
    });
//            ҳ��3 �������,����ҳ��2��
    $("#back_filter").click(function(){
        $(".qy_filter ").hide();
        $("#filter").show();
    });
    function addres(){
        //           ����ѡ��
        $(".qy_choose .q_right li").click(function(){
            var ensure=$(this).text();
            $(this).addClass("on").siblings().removeClass("on");
            $(".qy_filter").hide();
            $("#filter").show();
            $("#area").html( ensure+"&or;");
        });
        //            ���
        $("#filter .empty").click(function(){
            if($(".r-list ul li").hasClass("active")==true){
                $(".r-list ul li").removeClass("active");
            }
            $(".px-list li").removeClass("p_list_on");
            $("#area").html("ȫ��&or;");
        })
    }


    var frontURL=Constant.URL+'/mobile/home';
    var postData={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        var str='';
        var isoffers;
        $('.goodlist').html("");
        for(var i=0;i<data.homeRooms.length;i++){
            str+='<li proID='+data.homeRooms[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+"http://192.168.1.109:8080/hotel/images/"+data.homeRooms[i].pic3[1]+'"></a><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].community+'</a></h1><h2 class="text-ellipsis">'+data.homeRooms[i].balcony+"��"+data.homeRooms[i].bathroom+"��"+
                data.homeRooms[i].bedroom+"��"+"-"+data.homeRooms[i].direction+'</h2><p class="text-ellipsis address">'+data.homeRooms[i].location+data.homeRooms[i].address+'</p><p class="label-group"><i class="label-type1">'+data.homeRooms[i].apartmenttype+'</i></p><span class="label-price">'+data.homeRooms[i].dayPrice+'<small>/��</small></span></div></li>';
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
    })

});