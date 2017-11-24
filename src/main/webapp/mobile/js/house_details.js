$(document).ready(function(){
				$(".fn-loca-nav a").click(function(){
					$(this).addClass('hat').siblings().removeClass('hat');
				});
				//房间描述查看
				$(".hs-content").on('click','.info-lock',function(){
					if($(this).parent().find('.hs-info').hasClass('showtime')==true){
						$(this).parent().find('.hs-info').removeClass('showtime');
						$(this).html('收起<span class="czs-arrow-up-l"></span>')
					}else{
						$(this).parent().find('.hs-info').addClass('showtime');
						$(this).html('展开全部<span class="czs-arrow-down-l"></span>')
					}
					
				})
				//设施服务查看
				var falg=1;
				$(".dp .deploy li").hide();
	        	$(".dp .deploy li:eq(0)").show();
	            $(".dp .deploy li:eq(0)").find('.fc-name').hide();
	            
				$(".dp .info-lock").click(function(){
					if(falg=='1'){
	                    $(".dp .deploy li:eq(0)").find('.fc-name').show();
	                    $(".dp .deploy li").show();
						$(this).html('收起<span class="czs-arrow-up-l"></span>');
	                    falg=0;
	                }else if(falg=='0'){
	                    $(".dp .deploy li").hide();
	                    $(".dp .deploy li:eq(0)").show();
	                    $(".dp .deploy li:eq(0)").find('.fc-name').hide();
						$(this).html('展开全部<span class="czs-arrow-down-l"></span>');
	                    falg=1
	                }
				})
				//评价
				$(".appraise li").eq(0).addClass('showtime');
            	$(".pingjia .info-lock").toggle(function(){
            		$(".appraise li").addClass('showtime');
            		$(this).html('收起<span class="czs-arrow-up-l"></span>');
            	},function(){
            		$(".appraise li").removeClass('showtime');
			        $(".appraise li").eq(0).addClass('showtime');
			        $(this).html('展开全部<span class="czs-arrow-down-l"></span>');
            	})
          
				
				
				
				//置顶
			   $(window).scroll(function(event){
					 var st=$(window).scrollTop();
					 	if(st>200){
		                	$(".fn-loca-nav").addClass("stp");
			            }
			            else{
			                $(".fn-loca-nav").removeClass("stp");
			            }
	    		});
	            //滚动
	         
				$('.fn-loca-nav a:first-child').click(function(){
						$('html,body').animate({scrollTop:$('#page1').offset().top-45}, 200);}
				);
				$('.fn-loca-nav a:first-child+a').click(function(){
						$('html,body').animate({scrollTop:$('#page2').offset().top-45}, 200);}
				);
				$('.fn-loca-nav a:first-child+a+a').click(function(){
						$('html,body').animate({scrollTop:$('#page3').offset().top-45}, 200);}
				);
				$('.fn-loca-nav a:first-child+a+a+a').click(function(){
						$('html,body').animate({scrollTop:$('#page4').offset().top-45}, 200);}
				);
				
	            
	            
	//房子id            
	var _id = decodeURIComponent(fnBase.request("id"));
	console.log(_id)
//======房子信息begin===========	
	$.ajax({
		type:'get',
		dataType:'json',
		url:Constant.URL+'/mobile/info',
		data:{
            'apartmentId':_id,
		},
		success:function(data){
			console.log(data);
			fnBase.keep(1,"community",data.position.xiao_qu);
            fnBase.keep(1,"dayPrice",data.basic_info.jia_ge);
            fnBase.keep(1,"roomType",data.basic_info.lei_xing);
            fnBase.keep(1,"roonWz",data.position.dan_yuan);
            fnBase.keep(1,"roomCX",data.basic_info.cao_xiang);
            fnBase.keep(1,"num_door",data.position.men_pai);
			
			
			var vio=data.position.bd_wei_zhi;
        	vio=vio.slice(4,vio.length);
			$(".submitbtn a").html('￥<span>'+data.basic_info.jia_ge+'</span>/晚<i style="">订</i>');
			$(".map .hs-title").html('<span class="czs-location-l"></span>'+vio+data.position.jie_dao+data.position.xiao_qu)
			
			$(".swiper-container .swiper-wrapper").html("");
            var lunbo_str = '';
            var lunbo_length = data.pic_show.length;
            for (var i = 0; i < lunbo_length; i++){
                lunbo_str += '<div class="swiper-slide"><img src="'+Constant.URL+"/images/"+data.pic_show[i]+'"/></div>';
            }
            $(".swiper-container .swiper-wrapper").append(lunbo_str);
            
            var mySwiper = new Swiper ('.swiper-container',{
                loop: true,
                autoplay: 3000,
                pagination: '.swiper-pagination',
                autoplayDisableOnInteraction: false
            });
            	if(data.panorama==null || data.panorama==undefined || data.panorama==""){
            		
            	}else{
            		$(".swiper-container").append('<i class="videovr"><span>VR实景</span></i>');
            	};
            	
            	$(".swiper-container .videovr").on('click',function(){
            		window.location.href="VR.html?VR_di_zhi="+encodeURIComponent(data.panorama)+""; //可以分享朋友圈
        		})
            
            $(".information").html("");
                 var priceStr ='<p>'+data.position.xa_wei_zhi+"-" +data.position.xiao_qu+'</p><ul class="hs-info-one"><li><span>户型：'+data.basic_info.shi+"室"+data.basic_info.ting+"厅"+data.basic_info.wei+"卫"+'</span><span>可住：'+data.basic_info.reng_shu+'人</span></li><li><span>面积：'+data.basic_info.mian_ji+'m</span><span>床位：'+data.basic_info.chuang+'</span></li></ul><p>入住诱惑：满3天9.5折、满7天9折、满30天8折</p>';
            $(".information").append(priceStr);
            
            $(".infohouse .hs-info").text(data.description);
           //特色 
            $(".deploy li:eq(0)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.te_se.length;i++){
                str+='<i class="'+data.te_se_class[i]+'"><em></em>'+data.te_se[i]+'</i>';
                if(data.te_se.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy li:eq(0)").find('.facility-list').append(str);
            //居家
             //2---居家
            $(".deploy li:eq(1)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.jia_ju.length;i++){
                str+='<i class="'+data.jia_ju_class[i]+'"><em></em>'+data.jia_ju[i]+'</i>';
                if(data.jia_ju.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy li:eq(1)").find('.facility-list').append(str);
            //卫浴
             $(".deploy li:eq(2)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.wei_yu.length;i++){
                str+='<i class="'+data.wei_yu_class[i]+'"><em></em>'+data.wei_yu[i]+'</i>';
                if(data.wei_yu.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy li:eq(2)").find('.facility-list').append(str);
            //厨房
             $(".deploy  li:eq(3)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.can_chu.length;i++){
                str+='<i class="'+data.can_chu_class[i]+'"><em></em>'+data.can_chu[i]+'</i>';
                if(data.can_chu.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy li:eq(3)").find('.facility-list').append(str);
            //周边
            $(".deploy  li:eq(4)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.zou_bian.length;i++){
                str+='<i class="'+data.zou_bian_class[i]+'"><em></em>'+data.zou_bian[i]+'</i>';
                if(data.zou_bian.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy  li:eq(4)").find('.facility-list').append(str);
            //配套
             $(".deploy li:eq(5)").find('.facility-list').html('');
            var str='';
            for(var i=0;i<data.pei_tao.length;i++){
                str+='<i class="'+data.pei_tao_class[i]+'"><em></em>'+data.pei_tao[i]+'</i>';
                if(data.pei_tao.length<=1){
                	str='<i><em></em>暂无</i>'
                }
            }
            $(".deploy  li:eq(5)").find('.facility-list').append(str);
            //其他
            $(".deploy li:eq(6)").find('.facility-list').html('');
            var str='';
        	for(var i=0;i<data.qi_ta.length;i++){
                	str+='<i class="'+data.qi_ta_class[i]+'"><em></em>'+data.qi_ta[i]+'</i>';
                	if(data.qi_ta.length<=1){
                		str='<i><em></em>暂无</i>'
                	}
           	}
            $(".deploy  li:eq(6)").find('.facility-list').append(str);
            
            $(".map").attr('jd',data.position.jing_du);
            $(".map").attr('wd',data.position.wei_du);
            $(".map").on('click',function(){
            	window.location.href="addresMap.html?jd="+encodeURIComponent(data.position.jing_du)+"&wd="+encodeURIComponent(data.position.wei_du)+"&address="+encodeURIComponent(vio+data.position.jie_dao+data.position.xiao_qu)+"&price="+encodeURIComponent(data.basic_info.jia_ge)
            })
            
            //weizhiMap
                        //创建和初始化地图函数：
            function initMap(){
                createMap();//创建地图
                setMapEvent();//设置地图事件
//                addMapControl();//向地图添加控件
                addMarker();//向地图中添加marker
            }

            //创建地图函数：
            function createMap(){
                var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
                var point = new BMap.Point(109.014721,34.276352);//定义一个中心点坐标
                map.centerAndZoom(point,11);//设定地图的中心点和坐标并将地图显示在地图容器中
                window.map = map;//将map变量存储在全局
            }

            //地图事件设置函数：
            function setMapEvent(){
                map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
//                map.enableScrollWheelZoom();//启用地图滚轮放大缩小
//                map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
//                map.enableKeyboard();//启用键盘上下左右键移动地图
                map.disableDragging();
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
//                        _iw.addEventListener("open",function(){
                            _marker.getLabel().hide();
//                        });
//                        _iw.addEventListener("close",function(){
//                            _marker.getLabel().show();
//                        });
                        label.addEventListener("click",function(){
                            _marker.openInfoWindow(_iw);
                        });
                        if(!!json.isOpen){
                            label.hide();
//                            _marker.openInfoWindow(_iw);
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
        
                var icon = new BMap.Icon("images/smiley.png",new BMap.Size(30,30));
                return icon;
                
            }

            
            initMap();//创建和初始化地图
            
		}
	})
//======房子信息end===========	
//=====评论begin==============
	var frontURL=Constant.URL+'/mobile/get';
    var postData={"roomId":_id};
    fnBase.commonAjax(frontURL,postData,function(data){
       // console.log(data);
        $(".pingjia .shuliang i").text("("+data.length+"条评论"+")");
        if(data.length>0){

        }else{
            $("#pingjia").text("暂无评论");
            $(".pingjia .info-lock").hide();
        }

            $(".appraise").html("");
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
                var dates=year+month;
                var _date=new Array();
                for(var u=0;u<dates.length;u++){
                    _date.push(dates)
                }
                plStr+='<li>';
          
                if(data[i].Avatar==''){
                	plStr+='<div class="customer"><img src="/images/face-90x90.png" class="pic"><p class="user">用户：'+str2+'<span style="float:right">评价：'+data[i].comment.score[0]+'分</span></p><p class="time">'+_date[i]+' 入住</p></div>'
                }else{
                	plStr+='<div class="customer"><img src="'+data[i].Avatar+'" class="pic"><p class="user">用户：'+str2+'<span style="float:right">评价：'+data[i].comment.score[0]+'分</span></p><p class="time">'+_date[i]+' 入住</p></div>'
                }
                plStr+='<p class="text">'+data[i].comment.feel+'</p>';
                if(data[i].comment.reply != null){
            	   plStr+='<div class="huifu"><span class="sro"></span><p style="font-size:0.12rem;line-height: 0.2rem;color:#484848"><i style="color:#000;">管家回复：</i>'+data[i].comment.reply+'</p></div>'
               }
              
                plStr+='</li>';

            }
            $(".appraise").append(plStr);
        $(".appraise li").eq(0).addClass('showtime');

    });
//=======评论end===========
	
	$(".xuanTime").click(function(){
		$("#containerArea").hide();
		$("#bottomMenu").hide();
	})



	$(".submitbtn a").on('click',function(){
		hasshouse();
	})
		function hasshouse(){
					        	   //判断有房没房
	            var _id = decodeURIComponent(fnBase.request("id"));
	            var checkIn= $(".input-enter").val();
	            var leave= $(".input-leave").val();
	            console.log(checkIn+'-'+leave)
	            var frontURL=Constant.URL+'/mobile/checkAvailable';
	            var postData={"startTime":checkIn,"endTime":leave,"roomId":_id};
	            fnBase.commonAjax(frontURL,postData,function(data){
	                if(data.content.length>0){
	                    fnBase.myalert('您所选时间段内没有空房，请重新选择');
	                    return;
	                }else{
	                  msgdata()
	                }
	            })
		}

	    function msgdata(){
          var checkIn= $(".input-enter").val();
          var leave= $(".input-leave").val();

          var _id = decodeURIComponent(fnBase.request("id"));
          var frontURL=Constant.URL+'/mobile/module';
          var postData={"startTime":checkIn,"endTime":leave,"apartmentId":_id};
          fnBase.commonAjax(frontURL,postData,function(data){
              fnBase.keep(1,'startTime',data.oStart);
              fnBase.keep(1,'endTime',data.oEnd);
              fnBase.keep(1,'oTotalDay',data.oTotalDay);
              fnBase.keep(1,'oTotalPrice',data.oTotalPrice);
              fnBase.keep(1,"YJpic",data.oCashPledge);
  			  fnBase.keep(1,"_price",data.price);
 			  window.location.href="order.html?id="+encodeURIComponent(_id);
          })
        }

	    
	    $(".swiper-container").on('click','.swiper-slide',function(){
	    	$("#masking").show();
	    	$(".gratis").addClass('scale');
	    	
	    })
	    
	    $("#masking,.gratis").click(function(){
	    	$(".gratis").removeClass('scale')
	    	$("#masking").hide();
	    })
});