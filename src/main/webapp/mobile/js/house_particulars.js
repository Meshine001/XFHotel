
$(document).ready(function(){
	fnBase.loadShow();
	var _uid = fnBase.huoqu(0, "uid");
	var _id = decodeURIComponent(fnBase.request("id"));

	$.ajax({
		type:'GET',
		dataType:'json',
		url:Constant.URL+'/mobile/info',
		data:{
            'apartmentId':_id
		},
		success:function(data){
			fnBase.loadHide();
			console.log(data);
            fnBase.keep(1,"community",data.position.xiao_qu);
            fnBase.keep(1,"dayPrice",data.basic_info.jia_ge);
            fnBase.keep(1,"roomType",data.basic_info.lei_xing);
            fnBase.keep(1,"roonWz",data.position.dan_yuan);
            fnBase.keep(1,"roomCX",data.basic_info.cao_xiang);
            fnBase.keep(1,"num_door",data.position.men_pai);
			//房屋图片展示
            $(".swiper-container .swiper-wrapper").html("");
            var lunbo_str = '';
            var lunbo_length = data.pic_show.length;
            for (var i = 0; i < lunbo_length; i++){
                lunbo_str += '<div class="swiper-slide"><img src="'+Constant.URL+"/images/"+data.pic_show[i]+'"/></div>';
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
            var priceStr ='<h1 class="ic_house">'+data.position.xa_wei_zhi+"-" +data.position.xiao_qu+'</h1><p class="info_L24">'+data.basic_info.shi+"室"+data.basic_info.ting+"厅"+
            data.basic_info.wei+"卫"+"-"+data.basic_info.cao_xiang+'</p>' +
                '<p class="info_L24 label-adders">'+data.position.bd_wei_zhi+"-"+data.position.jie_dao+data.position.xiao_qu+'</p><p class="info_L24 label-group"><i class="label-type1">'
                +data.basic_info.lei_xing+'</i></p><span class="label-price">'+data.basic_info.jia_ge+'<small>/天</small></span>'
            $(".information").append(priceStr);
            //房源信息
            $(".housing .i_inf ul").html("");
            var massage='<li><span class="inf_sp">面积</span><span>'+data.basic_info.mian_ji+"㎡"+'' +
                '</span></li><li><span class="inf_sp">可住</span><span>'+data.basic_info.reng_shu+"人"+'</span></li><li><span class="inf_sp">户型</span><span>'+data.basic_info.shi+"室"+data.basic_info.ting+"厅"+
                data.basic_info.wei+"卫"+'</span></li><li><span class="inf_sp">床位</span><span>'+data.basic_info.chuang+'</span></li>';
            $(".housing .i_inf ul").append(massage);
            //房源设施

            //   查看更多
            var falg=1;
            $(".deploy .facility li").hide();
            $(".deploy .facility li:eq(0)").show();
            $(".deploy .facility li:eq(0)").find('.pNames').hide();
            $("#genduo").click(function(){
                if(falg=='1'){
                    $(".deploy .facility li:eq(0)").find('.pNames').show();
                    $(".deploy .facility li").show();
                    $(this).text('收起查看');
                    falg=0;
                }else if(falg=='0'){
                    $(".deploy .facility li").hide();
                    $(".deploy .facility li:eq(0)").show();
                    $(".deploy .facility li:eq(0)").find('.pNames').hide();
                    $(this).html('<img src="images/5-121204194112-52.gif">查看更多');
                    falg=1
                }

            });

            //1---特色
            $(".deploy .facility li:eq(0)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.te_se.length;i++){
                str+='<i class="'+data.te_se_class[i]+'"><em></em>'+data.te_se[i]+'</i>';
            }
            $(".deploy .facility li:eq(0)").find('.facility-list').append(str);
            //2---居家
            $(".deploy .facility li:eq(1)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.jia_ju.length;i++){
                str+='<i class="'+data.jia_ju_class[i]+'"><em></em>'+data.jia_ju[i]+'</i>';
            }
            $(".deploy .facility li:eq(1)").find('.facility-list').append(str);
            //3---卫浴
            $(".deploy .facility li:eq(2)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.wei_yu.length;i++){
                str+='<i class="'+data.wei_yu_class[i]+'"><em></em>'+data.wei_yu[i]+'</i>';
            }
            $(".deploy .facility li:eq(2)").find('.facility-list').append(str);
            //4---厨房
            $(".deploy .facility li:eq(3)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.can_chu.length;i++){
                str+='<i class="'+data.can_chu_class[i]+'"><em></em>'+data.can_chu[i]+'</i>';
            }
            $(".deploy .facility li:eq(3)").find('.facility-list').append(str);
            //5---周边
            $(".deploy .facility li:eq(4)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.zou_bian.length;i++){
                str+='<i class="'+data.zou_bian_class[i]+'"><em></em>'+data.zou_bian[i]+'</i>';
            }
            $(".deploy .facility li:eq(4)").find('.facility-list').append(str);
            //6---配套
            $(".deploy .facility li:eq(5)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.pei_tao.length;i++){
                str+='<i class="'+data.pei_tao_class[i]+'"><em></em>'+data.pei_tao[i]+'</i>';
            }
            $(".deploy .facility li:eq(5)").find('.facility-list').append(str);
            //7---其他
            $(".deploy .facility li:eq(6)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.qi_ta.length;i++){
                str+='<i class="'+data.qi_ta_class[i]+'"><em></em>'+data.qi_ta[i]+'</i>';
            }

            $(".deploy .facility li:eq(6)").find('.facility-list').append(str);
            //    房源描述
            $(".describe #serviceIntro2").text(data.description);
            //    户型图
//            $(".describe .hu_xing_tu img").attr('src',Constant.URL+'/images/'+data.hu_xing_tu);

        // 房屋位置；
            //创建和初始化地图函数：
            function initMap(){
                createMap();//创建地图
                setMapEvent();//设置地图事件
                addMapControl();//向地图添加控件
                addMarker();//向地图中添加marker
            }

            //创建地图函数：
            function createMap(){
                var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
                var point = new BMap.Point(109.014721,34.276352);//定义一个中心点坐标
                map.centerAndZoom(point,12);//设定地图的中心点和坐标并将地图显示在地图容器中
                window.map = map;//将map变量存储在全局
            }

            //地图事件设置函数：
            function setMapEvent(){
                map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
                map.enableScrollWheelZoom();//启用地图滚轮放大缩小
                map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
                map.enableKeyboard();//启用键盘上下左右键移动地图
            }

            //地图控件添加函数：
            function addMapControl(){
                //向地图中添加缩放控件
                var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,type:BMAP_NAVIGATION_CONTROL_PAN});
                map.addControl(ctrl_nav);
                //向地图中添加缩略图控件
                var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:0});
                map.addControl(ctrl_ove);
                //向地图中添加比例尺控件
                var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
                map.addControl(ctrl_sca);
            }
            var markerArr='';
            var XXX=data.position.jing_du;
            var YYY=data.position.wei_du;
            //标注点数组
            markerArr = [{
                title:"青舍都市公寓",
                content:"亲.我在这里等你",
                point:XXX+'|'+YYY,
                isOpen:1,
                icon:{w:23,h:25,l:46,t:21,x:9,lb:12}
            }];
            //创建marker
            function addMarker(){
                for(var i=0;i<markerArr.length;i++){
                    var json = markerArr[i];
                    var p0 = json.point.split("|")[0];
                    var p1 = json.point.split("|")[1];
                    var point = new BMap.Point(p0,p1);
                    var iconImg = createIcon(json.icon);
                    var marker = new BMap.Marker(point,{icon:iconImg});
                    var iw = createInfoWindow(i);
                    var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
                    marker.setLabel(label);
                    map.addOverlay(marker);
                    label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
                    });

                    (function(){
                        var index = i;
                        var _iw = createInfoWindow(i);
                        var _marker = marker;
                        _marker.addEventListener("click",function(){
                            this.openInfoWindow(_iw);
                        });
                        _iw.addEventListener("open",function(){
                            _marker.getLabel().hide();
                        });
                        _iw.addEventListener("close",function(){
                            _marker.getLabel().show();
                        });
                        label.addEventListener("click",function(){
                            _marker.openInfoWindow(_iw);
                        });
                        if(!!json.isOpen){
                            label.hide();
                            _marker.openInfoWindow(_iw);
                        }
                    })()
                }
            }
            //创建InfoWindow
            function createInfoWindow(i){
                var json = markerArr[i];
                var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
                return iw;
            }
            //创建一个Icon
            function createIcon(json){
                var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
                return icon;
            }

            initMap();//创建和初始化地图
		}

	});


    
    var frontURL=Constant.URL+'/mobile/get';
    var postData={"roomId":_id};
    console.log(postData);
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        $(".criticism .ic_house em").text("("+data.length+"条评论"+")");
        if(data.length>0){

        }else{
            $("#pingjia").text("暂无评论");
            $(".panel .button-orange").hide();
        }

            $(".criticism ul").html("");
            var plStr = '';
            for(var i=0;i<data.length;i++){
                //电话号码****代替
                var str=data[i].tel;
                var str2 = str.substr(0,3)+"****"+str.substr(7);
                // 日期转换
                var da = data[i].comment.time;
                da = new Date(da);
                var year = da.getFullYear()+'年';
                var month = da.getMonth()+1+'月';
                var date = da.getDate()+'日';
                var dates=year+month+date;
                var _date=new Array();
                for(var u=0;u<dates.length;u++){
                    _date.push(dates)
                }
                plStr+='<li><div class="customer"><img src="'+Constant.URL+'/images/'+data[i].Avatar+'" class="pic"><p class="user">【'+str2+'】<span>'+data[i].comment.score[0]+'分</span></p><p class="time">'+_date[i]+' 点评</p></div><p class="text">'+data[i].comment.feel+'</p>';
                plStr+='</li>';

            }
            $(".criticism ul").append(plStr);
        $(".criticism ul li").eq(0).addClass('showtime');

    });
   // 查看全部点评
    $("#commentsInfo").toggle(function(){
        $(".criticism ul li").addClass('showtime')
    },function(){
        $(".criticism ul li").removeClass('showtime');
        $(".criticism ul li").eq(0).addClass('showtime');
    });

   //  立即预约
    $(".navbar a").live('click',function(){
        $(".select-time").click();
    });

//  选好日期进行下一步操作：
    $(".alert-content .but-success").click(function(){
        var _uid = fnBase.huoqu(0, "uid");
        if (_uid == null || _uid == "undefined" || _uid == "") {
            window.location.href = "login.html";
            return;
        }
        $("#masking").hide(10,function(){
            $(".alert-content").animate({bottom:'-3.7rem'},300);
        });
        var checkIn= $(".input-enter").val();
        var leave= $(".input-leave").val();

        var _id = decodeURIComponent(fnBase.request("id"));
        var frontURL=Constant.URL+'/mobile/module';
        var postData={"startTime":checkIn,"endTime":leave,"apartmentId":_id};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            fnBase.keep(1,'startTime',data.oStart);
            fnBase.keep(1,'endTime',data.oEnd);
            fnBase.keep(1,'oTotalDay',data.oTotalDay);
            fnBase.keep(1,'oTotalPrice',data.oTotalPrice);
            fnBase.keep(1,"YJpic",data.oCashPledge);
			fnBase.keep(1,"_price",data.price);
			window.location.href="order.html?id="+encodeURIComponent(_id);
        })
    });
    //Click anywhere to close #masking;
    $("#masking").click(function(){
        $(this).hide(10,function(){
            $(".alert-content").animate({bottom:'-3.7rem'},300);
        });
    });

});
