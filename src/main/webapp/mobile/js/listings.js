$(document).ready(function(){
  
	
	 var userid,wechatOpenId="",_status='0',ss="0",uid="";
	 var redirect = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=listings.html#wechat_redirect';
	    
  	 uid=fnBase.request("id");
  	 userid=fnBase.huoqu(0,'userid');
	 
	 if(userid==null || userid==undefined || userid==""){
		 if(uid==null || uid==undefined ||uid==''){
			  window.location.href = redirect; 	 
			  return; 
		 }else{
			 userid=fnBase.request("id")
			 userid=fnBase.keep(0,'userid',userid);
		 }
	 };
    
    
    
    
    
    
	
	//置顶导航

				$(".selectedNav ul li").click(function(){
					var i=$(this).index();
					$(".selectedNav ul li").removeClass('active');
					$(this).addClass('active').siblings().removeClass('active');

					if($(".shaixuan .shaixuanList").eq(i).is(':visible')==false){
						$(".shaixuan .shaixuanList").hide();
						$(".shaixuan .shaixuanList").eq(i).addClass('show');
						$(".shaixuan .shaixuanList").eq(i).slideDown();
					}else{
						$(".shaixuan .shaixuanList").eq(i).removeClass('show');
						$(".shaixuan .shaixuanList").eq(i).slideUp();
					}
					
				})
				
			   $(window).scroll(function(event){
					 var st=$(window).scrollTop();
					 	if(st>200){
		                	$(".selectedNav").addClass("scrollTop");
		                	$(".shaixuan").addClass('sctop')
			            }
			            else{
			                $(".selectedNav").removeClass("scrollTop");
			                $(".shaixuan").removeClass('sctop')
			            }
	    		});
var _wzid = decodeURIComponent(fnBase.request("ofo"));					
	if(_wzid==null||_wzid==""||_wzid=='undefined'){
		 weizhishaixuan(5)
	}else{
		weizhishaixuan(_wzid)
	}			
				
	//获取所有房源/admin/user/getRoom/            
	
	 function weizhishaixuan(value){
	 	var url=Constant.URL+'/admin/user/getRoom/';
	 	var postdata={'wei':value}
	 	fnBase.commonAjax(url,postdata,function(data){
	 		console.log(data);
	 		var number='该地区有<i id="hnumber">'+data.content.length+'</i>套房子';
	 		fnBase.myalert(number)
			    if(data.content.length<=0){
 			    	$('#zanwu').show();
 			    	$(".indexhouseList .more").hide();
 			    }else{
 			    	$('#zanwu').hide();
 			    }
	 		
	 		$(".indexhouseList ul").html('');
	 		var str='';
	 		for(var i=0;i<data.content.length;i++){
	        	var vio=data.content[i].position.bd_wei_zhi;
 			     	vio=vio.slice(4,vio.length);
 			     	str+='<li proID='+data.content[i].id+'><a href="javascript:;"><div class="fn-img"><img src="'+Constant.URL+"/images/"+data.content[i].fang_jian_tu[0]+'"><span class="price">￥'+data.content[i].basic_info.jia_ge+'</span></div><div class="fn-txt"><p>'+data.content[i].position.xa_wei_zhi+"-"+data.content[i].position.xiao_qu+'</p><p class="hu-c">'+data.content[i].basic_info.shi+"室"+data.content[i].basic_info.ting+"厅"+data.content[i].basic_info.wei+"卫"+'<span>宜住：'+data.content[i].basic_info.reng_shu+'人</span><span>常住优惠</span></p></div></a></li>';

	 		}
	 		$(".indexhouseList ul").append(str);

	 	})
	 	
	 	
	 }
	 function shulianghouseNumber(val){
		 	var url=Constant.URL+'/admin/user/getRoom/';
		 	var postdata={'wei':val}
		 	fnBase.commonAjax(url,postdata,function(data){
		 		console.log(data)

		 		if(val==0){//东
		 			$(".shaixuan .shaixuanList:eq(0) li:eq(1)").find('span:eq(1) i').text(data.content.length);
		 		}else if(val==1){//南
		 			$(".shaixuan .shaixuanList:eq(0) li:eq(2)").find('span:eq(1) i').text(data.content.length);
		 		}else if(val==2){//西
		 			$(".shaixuan .shaixuanList:eq(0) li:eq(3)").find('span:eq(1) i').text(data.content.length);
		 		}else if(val==3){//北
		 			$(".shaixuan .shaixuanList:eq(0) li:eq(4)").find('span:eq(1) i').text(data.content.length);
		 		}else if(val==4){//中
		 			$(".shaixuan .shaixuanList:eq(0) li:eq(5)").find('span:eq(1) i').text(data.content.length);
		 		}//全部5
		 	})
	 }
	 //筛选统计数量
	 for(var i=0;i<5;i++){
		 shulianghouseNumber(i)
	 }
	 
	 //筛选
	 $(".shaixuan .shaixuanList li").click(function(){
		 $(this).find('span:last-child').addClass('czs-circle-o');
		 $(this).siblings().find('span:last-child').removeClass('czs-circle-o');
		 $(".shaixuan .shaixuanList").hide();
	 })

	 
	 $(".shaixuan .shaixuanList li").click(function(){
		    var h = $('.indexTopCon').offset().top;
		    $('html,body').animate({'scrollTop':h});
	 });
	 
	 $(".shaixuan .shaixuanList:eq(0) li:first-child").click(function(){
		 weizhishaixuan(5)
	 });
	 $(".shaixuan .shaixuanList:eq(0) li:nth-child(2)").click(function(){
		 weizhishaixuan(0)
	 });
	 $(".shaixuan .shaixuanList:eq(0) li:nth-child(3)").click(function(){
		 weizhishaixuan(1)
	 });
	 $(".shaixuan .shaixuanList:eq(0) li:nth-child(4)").click(function(){
		 weizhishaixuan(2)
	 });
	 $(".shaixuan .shaixuanList:eq(0) li:nth-child(5)").click(function(){
		 weizhishaixuan(3)
	 });
	 $(".shaixuan .shaixuanList:eq(0) li:nth-child(6)").click(function(){
		 weizhishaixuan(4)
	 });
	 
	 //附近
	 $("#nearby").on('click',function(){
	        if((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i))) {
	    		 getLocation();
	    	}else{
	    		 fnBase.myalert('请在手机或平板等移动设备上使用该操作')
	        };

	        function supportsGeoLocation(){
	           return !!navigator.geolocation;
	        };        
	        function getLocation(){
	           navigator.geolocation.getCurrentPosition(mapIt,locationError);
	        };
	        function mapIt(position){
	        	var lon=position.coords.longitude;
	        	var lat=position.coords.latitude;  
	        	var frontURL=Constant.URL+'/mobile/distance';
	        	var postdata={
		        			'lng1':lon,
		        			'lat1':lat,
		        			'mi':10000,
//		        			'lng1':109.022892,
//		        			'lat1':34.264563
	        			     };
	        	fnBase.commonAjax(frontURL,postdata,function(data){
	        		console.log(data);
	        		var dl='您附近有 <i id="hnumber_s">'+data.length+'</i> 套公寓';
	        		var str='';
	        		var mi,km;
				    if(data.length<=0){
	 			    	$('#zanwu').show();
	 			    	$(".indexhouseList .more").hide();
	 			    }else{
	 			    	$('#zanwu').hide();
	 			    };
	 		 		$(".indexhouseList ul").html('');
	        		for(var i=0; i < data.length; i++){
	        			str+='<li proID='+data[i].apartment.id+'><a href="javascript:;"><div class="fn-img"><img src="'+Constant.URL+"/images/"+data[i].apartment.fang_jian_tu[0]+'"><span class="price">￥'+data[i].apartment.basic_info.jia_ge+'</span></div><div class="fn-txt"><p>'+data[i].apartment.position.xa_wei_zhi+"-"+data[i].apartment.position.xiao_qu+'</p><p class="hu-c">'+data[i].apartment.basic_info.shi+"室"+data[i].apartment.basic_info.ting+"厅"+data[i].apartment.basic_info.wei+"卫"+'<span>宜住：'+data[i].apartment.basic_info.reng_shu+'人</span><span>常住优惠</span></p></div></a></li>'
	        		};
	        		$(".indexhouseList ul").append(str);
	        		
	     	        fnBase.myalert(dl);
	        	})
	        	
	        	

	        }


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
     
	 });
	 
	 
     $(".indexhouseList ul").on('click','li',function(){
       	 var id = $(this).attr('proID');
         window.location.href = "house_details.html?id=" + encodeURIComponent(id) + "";
     });  
	 
	 
});