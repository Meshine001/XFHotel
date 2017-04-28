
$(document).ready(function(){
    addres();
//           页面1 点击筛选与排序弹出页面2
    $("#filterbtn").click(function(){
        //$(".sx_filter").hide();
        //$("#filter").show();
    });
//           页面2 {点击 返回按钮 and  确定按钮 返回页面1}；
    $(" #back_main,#confirm").click(function(){
        $("#filter").hide();
        $(".sx_filter").show();
    });

//           页面2 筛选条件 加选中样式；
    $(".px-list  li").click(function(){
        $(this).addClass("p_list_on").siblings().removeClass("p_list_on");
    });
    $(".r-list ul li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
//          页面2 点击 区域查询 弹出页面3 ；
    $(".quyu").click(function(){
        $("#filter").hide();
        $(".qy_filter ").show();
    });
//            页面3 点击返回,跳到页面2；
    $("#back_filter").click(function(){
        $(".qy_filter ").hide();
        $("#filter").show();
    });
    function addres(){
        //           区域选择；
        $(".qy_choose .q_right li").click(function(){
            var ensure=$(this).text();
            $(this).addClass("on").siblings().removeClass("on");
            $(".qy_filter").hide();
            $("#filter").show();
            $("#area").html( ensure+"&or;");
        });
        //            清空
        $("#filter .empty").click(function(){
            if($(".r-list ul li").hasClass("active")==true){
                $(".r-list ul li").removeClass("active");
            }
            $(".px-list li").removeClass("p_list_on");
            $("#area").html("全城&or;");
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
            str+='<li proID='+data.homeRooms[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data.homeRooms[i].pic3[1]+'"></a><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].community+'</a></h1><h2 class="text-ellipsis">'+data.homeRooms[i].balcony+"室"+data.homeRooms[i].bathroom+"厅"+
                data.homeRooms[i].bedroom+"卫"+"-"+data.homeRooms[i].direction+'</h2><p class="text-ellipsis address">'+data.homeRooms[i].location+data.homeRooms[i].address+'</p><p class="label-group"><i class="label-type1">'+data.homeRooms[i].apartmenttype+'</i></p><span class="label-price">'+data.homeRooms[i].dayPrice+'<small>/天</small></span></div></li>';
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