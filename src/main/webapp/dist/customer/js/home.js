function fg_big(k,index){
		$.colorbox({
			open:true,
			fixed:false,
			inline:true,
			width:1230,
			height:720,
			href:"#dt_div_"+k,
			overlayClose:true,
			onComplete:function(){ 
				//幻灯片
				window.mySwipe = $('#mySwipe_'+k).Swipe({
					startSlide: index-1,
					//auto: 3000,
					continuous: true,
					disableScroll: true,
					stopPropagation: true,
					callback: function(index, element) {},
					transitionEnd: function(index, element) {}
					}).data('Swipe');
			}
		});
		$(".pics_pre").attr('index',index);
		$(".pics_next").attr('index',index);
		$(".te_num").html(index+'/8');
	}
	var liulan = ',1';
	function on_fj(k){
		
		$(".fj_nav span").removeClass('exm_nav_on');
		$("#fj_on_"+k).addClass('exm_nav_on');
		$(".apt_ul").hide();
		$("#fj_"+k).css('display','');
		
		var kl = liulan.indexOf(','+k);
		if(kl==-1){
			liulan = liulan+','+k;
			if(k==3){
				var st = parseInt($(window).scrollTop())-1;
			}else{
				var st = parseInt($(window).scrollTop())+1;
			}			
			$('html,body').animate({ 'scrollTop': st }, 3);
		}		
	}
	function on_fg(k){
		$(".fg_nav span").removeClass('exm_nav_on');
		$("#fg_on_"+k).addClass('exm_nav_on');
		$(".exm_ul").hide();
		$("#fg_"+k).css('display','');
	}
	var imageLen = $('div.hr_img').length;
	var lastPic = imageLen - 1;
	var firstPic = 0;
	var timeInterval = 5000;
	var fadeInOutTime = 2000;
	var setInt;
	$(window).load(function() {
		setInt = setTimeout("next_pic()",timeInterval);   
		//$(".pics_pre").show();
		//$(".pics_next").show();
		
	})

	function pre_pic()
	{
		var currentImage = $('.banner_img');
		var currentIndex = $('div.hr_img').index($(currentImage));
		if(currentIndex > firstPic && currentIndex <= lastPic){
			 currentIndex--;
		}
		else {
			currentIndex  = lastPic;
		}
		clearInterval(setInt);
		currentImage.removeClass('banner_img').fadeOut(fadeInOutTime);
		$('div.hr_img').eq(currentIndex).addClass('banner_img').fadeIn(fadeInOutTime);
		setInt = setTimeout("next_pic()",timeInterval);   
	} 
	function next_pic()
	{
		var currentImage = $('.banner_img');
		var currentIndex = $('div.hr_img').index(currentImage);
		if(currentIndex >= firstPic && currentIndex < lastPic){
			currentIndex++;
		}
		else {
			currentIndex = firstPic;
		}
		clearInterval(setInt);
		currentImage.removeClass('banner_img').fadeOut(fadeInOutTime);
		$('div.hr_img').eq(currentIndex).addClass('banner_img').fadeIn(fadeInOutTime);
		setInt = setTimeout("next_pic()",timeInterval);   
	}	
	function showHomeMenu()
	{		
		$("#home-menu").css('display','');
	}
	function hideHomeMenu()
	{				
		$("#home-menu").hide();
	}
	function showMask(n,k)
	{		
		$("#mask_"+n+"_"+k).css('display','');
	}
	function hideMask(n,k)
	{				
		$("#mask_"+n+"_"+k).hide();
	}
	function goTop() {
		$('html,body').animate({ 'scrollTop': 0 }, 300);
	}
	$(document).ready(function(){
		$('.colse').live("click",function(){    
			$.colorbox.close();	
		});	
		$("img.readyload").lazyload({
			threshold: 300,
			effect: "fadeIn"
		});
		$(".weixin").live('mouseover',function(){    
			$(".em").show();
		});
		$(".weixin").live('mouseout',function(){    
			$(".em").hide();
		});

		$(".wx").live('mouseover',function(){    
			$(".btm_em").show();
		});
		$(".wx").live('mouseout',function(){    
			$(".btm_em").hide();
		});

		$(".customer2").live('click',function(){			
			$("#nb_icon_wrap").click();			
		});
		$(".pics_pre").click(function(){
			var index = $(".pics_pre").attr('index');			
			if(index==1){
				index = 8;
			}else{
				index = parseInt(index)-1;
			}
			$(".te_num").html(index+'/8');		
			$(".pics_pre").attr('index',index);
			$(".pics_next").attr('index',index);
		});	
		$(".pics_next").click(function(){
			var index = $(".pics_next").attr('index');			
			if(index==8){
				index = 1;				
			}else{
				index = parseInt(index)+1;				
			}
			$(".te_num").html(index+'/8');
			$(".pics_pre").attr('index',index);
			$(".pics_next").attr('index',index);
		});		
	});	
	
	$(function() {
		var jx_sWidth = $("#jxfocus").width(); //获取焦点图的宽度（显示面积）
		var jx_len = $("#jxfocus dl").length; //获取焦点图个数
		var jx_index = 0;
		var on_index = 0;
		var jx_picTimer;

		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn = "";
		for(var i=0; i < jx_len; i++) {
			btn += "<li></li>";
		}		
		$(".hai_ul").append(btn);
			
		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$(".hai_ul li").mouseenter(function() {
			index = $(".hai_ul li").index(this);	
			jx_index = index;	
			jx_showPics(index);
		}).eq(0).trigger("mouseenter");		

		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$(".hua_d").css("width",jx_sWidth * (jx_len));
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$("#jx_div").hover(function() {
			clearInterval(jx_picTimer);
		},function() {
			jx_picTimer = setInterval(function() {
				jx_showPics(jx_index);
				//alert(jx_index);
				on_index = jx_index;
				jx_index++;
				if(jx_index == jx_len) {jx_index = 0;}
			},3000); //此4000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");
		
		//显示图片函数，根据接收的index值显示相应的内容
		function jx_showPics(jx_index) { //普通切换
			var jx_nowLeft = -jx_index*jx_sWidth; //根据index值计算ul元素的left值
			$(".hai_ul li").removeClass("hai_on").eq(jx_index).addClass("hai_on");
			$(".hua_d").stop(true,false).animate({"left":jx_nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		}
	});	
	$(function() {
		var qy_sWidth = $(".cut_list li").width(); //获取焦点图的宽度（显示面积）
		var qy_len = $(".cut_list li").length; //获取焦点图个数
		var qy_index = 0;
		var qy_on_index = 0;
		var qy_picTimer;

		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn_qy = "";
		for(var i=0; i < (qy_len-4); i++) {
			btn_qy += "<li></li>";
		}		
		$(".cut_ul").append(btn_qy);
			
		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$(".cut_ul li").mouseenter(function() {
			index = $(".cut_ul li").index(this);	
			qy_index = index;	
			qy_showPics(index);
		}).eq(0).trigger("mouseenter");		

		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$(".cut_list").css("width",qy_sWidth * (qy_len));
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$(".qy_div").hover(function() {
			clearInterval(qy_picTimer);
		},function() {
			qy_picTimer = setInterval(function() {
				qy_showPics(qy_index);				
				qy_on_index = qy_index;
				qy_index++;
				if(qy_index == (qy_len-4)) {qy_index = 0;}
			},3000); //此4000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");
		
		//显示图片函数，根据接收的index值显示相应的内容
		function qy_showPics(qy_index) { //普通切换	
			var qy_nowLeft = -qy_index*qy_sWidth;				
			$(".cut_ul li").removeClass("cut_on").eq(qy_index).addClass("cut_on");
			$(".cut_list").stop(true,false).animate({"left":qy_nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
		}
	});	
	
	$(document).on('click',function(e){
		
	});
	

	

	