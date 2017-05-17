
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
    	}
    	var frontURL=Constant.URL+'/mobile/list';
    	fnBase.commonAjax(frontURL,postData,function(data){
    		console.log(data)
    		$('.goodlist').html("");
    		var str='';
    		for(var i=0;i<data.page.results.length;i++){
    			 str+='<li proID='+data.page.results[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data.page.results[i].pic3[1]+'"></a><h1 class="text-ellipsis"><a href="">'+data.page.results[i].community+'</a></h1><h2 class="text-ellipsis">'+data.page.results[i].bedroom+"室"+data.page.results[i].balcony+"厅"+
    			 data.page.results[i].bathroom+"卫"+"-"+data.page.results[i].direction+'</h2><p class="text-ellipsis address">'+data.page.results[i].location+data.page.results[i].address+'</p><p class="label-group"><i class="label-type1">'+data.page.results[i].apartmenttype+'</i></p><span class="label-price">'+data.page.results[i].dayPrice+'<small>/天</small></span></div></li>';
             }
    		 $('.goodlist').append(str);
    		 $(".sx_filter").show();
    		 $("#filter").hide();
    		 
    		  var len = $('.goodlist li').length;
    	        if(len==0){
    	            $('#zanwu').show();
    	            $("#Myscroll-body").css('background','#FFF')
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
    
    
    
    
    
   function uploadData(){
	   var frontURL=Constant.URL+'/mobile/home';
	    var postData={};
	    fnBase.commonAjax(frontURL,postData,function(data){
	        console.log(data);
	        var str='';
	        var isoffers;
	        $('.goodlist').html("");
	        for(var i=0;i<data.homeRooms.length;i++){
	            str+='<li proID='+data.homeRooms[i].id+'><div class="item-room"><a href="javascript:;"class="img-wrapper"><img src="'+Constant.URL+"/images/"+data.homeRooms[i].pic3[1]+'"></a><h1 class="text-ellipsis"><a href="">'+data.homeRooms[i].community+'</a></h1><h2 class="text-ellipsis">'+data.homeRooms[i].bedroom+"室"+data.homeRooms[i].balcony+"厅"+
	                data.homeRooms[i].bathroom+"卫"+"-"+data.homeRooms[i].direction+'</h2><p class="text-ellipsis address">'+data.homeRooms[i].location+data.homeRooms[i].address+'</p><p class="label-group"><i class="label-type1">'+data.homeRooms[i].apartmenttype+'</i></p><span class="label-price">'+data.homeRooms[i].dayPrice+'<small>/天</small></span></div></li>';
	        }
	        $('.goodlist').append(str);


	        var len = $('.goodlist li').length;
	        if(len==0){
	            $('#zanwu').show();
	            $("#Myscroll-body").css('background','#FFF')
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
   }

});