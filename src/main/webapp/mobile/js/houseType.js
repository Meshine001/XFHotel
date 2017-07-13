
$(document).ready(function(){
	uploadData()
    addres();
    var _addres='';
    var _layou='';
    var _features='';
//  页面1 点击筛选与排序弹出页面2
    $("#filterbtn").click(function(){
        $(".sx_filter").hide();
        $("#filter").show();
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
            _addres=$(this).attr('tokon');
            
 // _addres           
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
            $("#area").html("全部");
        })
    }
    function lock(){
   	 $('.goodlist').on('click','li',function(){
	        	var id=$(this).attr('proID');
             var isoff= $(this).attr('offer');
             window.location.href="house_particulars.html?id="+encodeURIComponent(id)+"";
	        })
   }
//查询；
    $("#confirm").click(function(){

    	_layou=$("#house-layou ul .active").attr('la');
    	_features=$("#house-features ul .active").attr('fa');
    	if($("#area").html()=='全城'){
        	_addres=0;
        }
    	var postData={
    			'area':_addres,
    			'layout':_layou,
    			'features':_features,
    			'currentPage':1,
    			'moreStr':0,
    			'startTime':0,
    			'endTime':0,
    			'priceRange':0,
    			'enterTime':0,
    			'leaseType':0,
    			'sortType':0
    	};
    	var frontURL=Constant.URL+'/mobile/list';
    	fnBase.commonAjax(frontURL,postData,function(data){
    		console.log(data);

    		$('.goodlist').html("");
    		var str='';
    		for(var i=0;i<data.page.results.length;i++){
    			 str+='<li proID='+data.page.results[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data.page.results[i].fang_jian_tu[1]+'"></a><div class="right-content"><h1 class="text-ellipsis"><a href="javascript:;">'+data.page.results[i].position.xiao_qu+'</a></h1><h2 class="text-ellipsis">'+data.page.results[i].basic_info.shi+"室"+data.page.results[i].basic_info.ting+"厅"+
    			 data.page.results[i].basic_info.wei+"卫"+" "+'</h2><p class="text-ellipsis address">'+data.page.results[i].position.bd_wei_zhi+'</p><p class="label-group"><i class="label-type1">'+data.page.results[i].basic_info.lei_xing+'</i></p><span class="label-price">'+data.page.results[i].basic_info.jia_ge+'<small>/天</small></span></div></div></li>';
             }
    		 $('.goodlist').append(str);
    		 $(".sx_filter").show();
    		 $("#filter").hide();

    		  var len = $('.goodlist li').length;
    	        if(len==0){
    	            $('#zanwu').show();
    	            $("#Myscroll-body").css('background','#FFF');
    	            $('body').addClass('wingBg');
    	        }
    	      lock();
    	})
    });
    
    
    
    /*搜索*/
    $(".header-mobile .link-btn").click(function(){
    //暂时用刷新;
    	location=location
    	
    	var frontURL=Constant.URL+'/';
	    var postData={'key':$('.header-mobile .input-search').val()};
//	    fnBase.commonAjax(frontURL,postData,function(data){
//	    	console.log(data);
//	    	getdate(data);
//	    })
    })
    
    
   function uploadData(){
	    var frontURL=Constant.URL+'/mobile/homeTherefore';
	    var postData={};
	    fnBase.commonAjax(frontURL,postData,function(data){
	        console.log(data);

	    	getdate(data);
	    })
   }

    function getdate(data){
    	    var str='';
	        $('.goodlist').html("");
	        for(var i=0;i<data.homeRooms.length;i++){
	        	
	            str+='<li proID='+data.homeRooms[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data.homeRooms[i].fang_jian_tu[0]+'"></a><div class="right-content"><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].position.xiao_qu+'</a></h1><h2 class="text-ellipsis">'+data.homeRooms[i].basic_info.shi+"室"+data.homeRooms[i].basic_info.ting+"厅"+
	                data.homeRooms[i].basic_info.wei+"卫"+"-"+data.homeRooms[i].basic_info.cao_xiang+'</h2><p class="text-ellipsis address">'+data.homeRooms[i].position.bd_wei_zhi+' '+data.homeRooms[i].position.jie_dao+'</p><p class="label-group"><i class="label-type1">'+data.homeRooms[i].basic_info.lei_xing+'</i></p><span class="label-price">'+data.homeRooms[i].basic_info.jia_ge+'<small>/天</small></span></div></div></li>';
	        }
	        $('.goodlist').append(str);
	        var len = $('.goodlist li').length;
	        if(len==0){
	            $('#zanwu').show();
	            $("#Myscroll-body").css('background','#FFF');
	            $('body').addClass('wingBg');
	        }
	        lock();
    }
    
    /*定位附近的*/
    $("#nearby").on('click',function(){
    	if($("#filisted").hasClass('show')==true){
    		$("#filisted").removeClass('show')
    	}else{
    		$("#filisted").addClass('show')
    	}
    })
    
    
    
    
    $("#filisted ul li").click(function(){
    	var mi=$(this).attr('add');
    	if ((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
    		   getLocation();
    		}else{
    		   fnBase.myalert('请在手机或平板等移动设备上使用该操作')
    		}
    	
    	// 检测浏览器是否支持HTML5
        function supportsGeoLocation(){
           return !!navigator.geolocation;
       }  
        // 单次位置请求执行的函数             
        function getLocation(){
           fnBase.loadShow();
           navigator.geolocation.getCurrentPosition(mapIt,locationError);
        }
    	$("#filisted").removeClass('show')
    	mapIt();
        function mapIt(position){ 
        	var lon=position.coords.longitude;
        	var lat=position.coords.latitude;  
        	fnBase.loadHide();
        	var frontURL=Constant.URL+'/mobile/distance';
        	var postdata={
        			'lng1':lon,
        			'lat1':lat,
        			'mi':mi
//        			'lng1':109.022892,
//        			'lat1':34.264563
        	};
        	
        	console.log(postdata)
        	fnBase.commonAjax(frontURL,postdata,function(data){
        		console.log(data);
        		var dl='符合您要求的房屋有 '+data.length+' 个';
        		var str='';
        		var mi,km;
        		$('.goodlist').html("");
     	        for(var i=0;i<data.length;i++){
     	        	mi=data[i].distance;
     	        	km=mi/1000;
     	            str+='<li proID='+data[i].apartment.id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data[i].apartment.fang_jian_tu[0]+'"></a><div class="right-content"><h1 class="text-ellipsis"><a href="">'+data[i].apartment.position.xiao_qu+'</a></h1><h2 class="text-ellipsis">'+data[i].apartment.basic_info.shi+"室"+data[i].apartment.basic_info.ting+"厅"+
     	            data[i].apartment.basic_info.wei+"卫"+"-"+data[i].apartment.basic_info.cao_xiang+'</h2><p class="text-ellipsis address">'+data[i].apartment.position.bd_wei_zhi+' '+data[i].apartment.position.jie_dao+'</p><p class="label-group"><i class="label-type1">'+data[i].apartment.basic_info.lei_xing+'</i></p><span class="label-price">'+data[i].apartment.basic_info.jia_ge+
     	            '<small>/天</small></span><span class="myaddres"><'+km.toFixed(2)+'km</span></div></div></li>';
     	        }
     	        $('.goodlist').append(str);
     	       fnBase.myalert(dl);
     	     
        	})
        	 lock();
        };
       
        
        
        function locationError(error){
        	fnBase.loadHide();
	        switch(error.code){
	          case error.PERMISSION_DENIED:
	        	  alert('用户拒绝获取地理位置');
	            break;
	          case error.POSITION_UNAVAILABLE:
	        	  alert('无法获取当前位置');
	            break;
	          case error.TIMEOUT:
	        	  alert('操作超时');
	            break;
	          case error.UNKNOWN_ERROR:
	        	  alert('定位失败,定位系统失效');
	            break;
	          }
        };
        
       
        
        
    })
});