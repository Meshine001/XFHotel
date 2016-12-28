$(document).ready(function(){
	
	var searchType = $('#search-type').text();
	var hotelType = searchType == '酒店型公寓'?0:1;//0为酒店型,1为短租型
	
	//============================================================================>
	//根据类型不同加载不同UI
	if(hotelType == 0){
		$('.paymethod').hide();
		$('.my-apartment-friend').hide();
		$('.type-hotel-price').show();
		$('.type-apartment-price').hide();
	}else{
		$('.paymethod').show();
		$('.my-apartment-friend').show();
		$('.type-hotel-price').hide();
		$('.type-apartment-price').show();
	}
	
	
	 //=============================================================================>
	 //计算天数差的函数，通用  
	   function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
	       var  aDate,  oDate1,  oDate2,  iDays  
	       aDate  =  sDate1.split("-")  
	       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
	       aDate  =  sDate2.split("-")  
	       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
	       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
	       return  iDays  
	   }    
		
	   //===============================================================>
	   //日期选择
	 	$('.order-date').change(function(){
	 		var start = $("#order-start").val();
	 		var end = $('#order-end').val();
			if(start == '' || end == '')return;
			
	 		var startNum = parseInt(start.replace(/-/g,''),10); 
	 		var endNum = parseInt(end.replace(/-/g,''),10); 
	 		
	 		if(startNum>endNum){ 
	 			alert("结束时间不能在开始时间之前！"); 
	 			$('#order-total-day').val('');
	 			return false; 
	 		}
	 		
	 		var days = DateDiff(start,end);
	 		$('#order-total-day').val(days);
	 		var price = $('#order-price').val();
	 		var totalPrice = price*days;
	 		$('#order-total-price').val(totalPrice);
	 	});
	
	 //====================================================================>
	 	//提交酒店型订单
		$('#order-submit').click(function(){
			$('#order-description').val($('#order-description-title').text());
			$('#order-form').submit();
		});
	
	
	//==============================================================>
	//房间图片画廊
	$('#gallery').galleryView({
		 panel_width: 700,
         panel_height: 300,
         frame_width: 100,
         frame_height: 50,
         easing: 'swing',
         pause_on_hover: true,
         nav_theme: 'light',
         overlay_opacity: 0.5,
         overlay_height: 10
	});
});