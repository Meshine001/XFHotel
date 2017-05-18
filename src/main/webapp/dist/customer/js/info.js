 //=============================================================================>
	 //计算天数差的函数，通用  
	   function  DateDiff(sDate1,  sDate2){    // sDate1和sDate2是2006-12-18格式
	       var  aDate,  oDate1,  oDate2,  iDays  
	       aDate  =  sDate1.split("-")  
	       oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    // 转换为12-18-2006格式
	       aDate  =  sDate2.split("-")  
	       oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
	       iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    // 把相差的毫秒数转换为天数
	       return  iDays  
	   }    
	 
	   function scrollPage(obj)
	   {
	       var windowHeight = $(window).height();
	       var offsetTop = parseInt(obj.offset().top);
	       var scrollTop = $(document).scrollTop();
	       var suggestionHeight = obj.height();
	       if( (windowHeight - (offsetTop - scrollTop)) - suggestionHeight < 0 ) {
	           var newTopHeight = scrollTop + (suggestionHeight + 10 ) - (windowHeight - (offsetTop - scrollTop)); 
	           $('html,body').animate({ scrollTop: newTopHeight }, 300);
	       }

	   }
	    function dateDiff(strInterval, dtStart, dtEnd) {   
	       if (typeof dtEnd == 'string' )// 如果是字符串转换为日期型
	       {   
	           dtEnd = StringToDate(dtEnd);  
	       }  
	       switch (strInterval) {   
	           case 's' :return parseInt((dtEnd - dtStart) / 1000);  
	           case 'n' :return parseInt((dtEnd - dtStart) / 60000);  
	           case 'h' :return parseInt((dtEnd - dtStart) / 3600000);  
	           case 'd' :return parseInt((dtEnd - dtStart) / 86400000);  
	           case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));  
	           case 'm' :return ((dtEnd.getMonth()+1)+(dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);  
	           case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();  
	       }  
	   }  

	   function calTotalPrice(){
	       // TODO
		   var s = $('#startdate').val();
           var e = $('#enddate').val();
           var url = '../price?id='+$('#apartmentId').val()+'&start='+s+'&end='+e;
           $.ajax({ 
        	   url: url,
        	   dataType:'json',
        	   success: function(data){
        		   
        		   console.log(data);
        		   $('#sameRoomNum').val(data['totalDay']+'日');
        		   var h = '../order/module?startTime='
        			   +data['start']+"&endTime="+data['end']
        		   +'&totalDay='+data['totalDay']+'&totalPrice='+data['totalPrice']
        		   +'&price='+data['price'];
        		   $('#day_yuding').attr('href',h);
        	   }
           });
	   }
	   
	   /**
		 * 
		 * @param target
		 * @returns
		 */
	   function changePic(target) {
		    var lastPicIndex = $('.w00 ul').children('li:last-child').attr('index');
	    	var curIndex = $('#datu').attr('index');
	    	var changeToWhere = parseInt(curIndex);
	    	if(target == 'pre'){
	    		if(curIndex==0){
	    			changeToWhere = lastPicIndex;
	    		}else{
	    			changeToWhere = changeToWhere-1;
	    		}
	    	}else{
	    		if(curIndex==lastPicIndex){
	    			changeToWhere = 0;
	    		}else{
	    			changeToWhere = changeToWhere+1;
	    		}
	    	}
	    	var curPic;
	    	$('.w00 ul li').each(function(index,li){
	    		if($(li).attr('index')==changeToWhere){
	    			curPic = $(li).find('img').attr('src');
	    		}
	    	});
	    	var aniWidth = (changeToWhere-curIndex)*138;
	    	var nowPosition = $('.w00 ul').css('margin-left');
	    	$('.w00 ul').animate({marginLeft:(nowPosition+aniWidth)+'px'},200,function(){
	    		$('#datu').attr('index',changeToWhere).attr('src',curPic);
	    	});
	   }
	   
	   //获取当前时间，格式YYYY-MM-DD
	    function getNowFormatDate() {
	        var date = new Date();
	        var seperator1 = "-";
	        var year = date.getFullYear();
	        var month = date.getMonth() + 1;
	        var strDate = date.getDate();
	        if (month >= 1 && month <= 9) {
	            month = "0" + month;
	        }
	        if (strDate >= 0 && strDate <= 9) {
	            strDate = "0" + strDate;
	        }
	        var currentdate = year + seperator1 + month + seperator1 + strDate;
	        return currentdate;
	    }
	   
	   function createPriceCalendar(){
		   var config = {
			        modules: {
			            'price-calendar': {
			                fullpath: '../dist/zui/lib/price-calendar/js/price-calendar.js', //根据项目路径调整
			                type    : 'js',
			                requires: ['price-calendar-css']
			            },
			            'price-calendar-css': {
			                fullpath: '../dist/zui/lib/price-calendar/css/price-calendar.css', //根据项目路径调整
			                type    : 'css'
			            }
			        }
			    };
		   		
		   //信息格式
			    var data = {
			        "2017-03-13": {
			            "price"  : "100",
			            "roomNum": "1"
			        },
			        "2017-03-14": {
			            "price"  : "120",
			            "roomNum": "1"
			        },
			        "2017-03-15": {
			            "price"  : "150",
			            "roomNum": "1"
			        }  
			    };

			    YUI(config).use('price-calendar', function(Y) {
			    var oCalendar = new Y.PriceCalendar();    
			    //初始化日历
			    //点击确定按钮
			    oCalendar.on('confirm',function(){
			    	 var s = this.get('depDate');
				     var d = this.get('endDate');
			       // alert('入住时间：'+this.get('depDate')+',离店时间:  '+this.get('endDate'));
			        $('#startenddate').val(s+'至'+d);
			        $('#sameRoomNum').val(DateDiff(s, d)+'日');
			        
			        
		
			        var param = $('#day_yuding').attr('data-baseurl')+'?'
			        +'startTime='+s	+'&endTime='+d+'&apartmentId='+$('#apartmentId').val();
			        
			        $('#day_yuding').attr('data-remote',param);
			        
			    	$('#startdate').val(this.get('depDate'));
			        $('#enddate').val(this.get('endDate'));
			        $('.price-calendar-bounding-box').remove();
			        
			    });
			    //点击取消按钮
			    oCalendar.on('cancel',function(){
			        this.set('depDate','').set('endDate','').render();
			        $('.price-calendar-bounding-box').remove();
			    });
			    
			    oCalendar.set('data',null)
			            .set('depDate','')
			            .set('endDate','')
			            .set('minDate','')
			            .set('afterDays','');
			    //设置从今天算起
			    var today = new Date();
			    oCalendar.set('date',today)
			    .set('minDate',today);
			    
			    
			    //获取数据
			    var aId = $('#apartmentId').val();
			    var startDate = getNowFormatDate();
			    var url = '../price/'+aId+'/'+startDate+"?ss="+new Date();
			    console.log(url);
			    $.ajax({
			    	type:'GET',
			    	url:url,
			    	dataType:'json',
			    	success:function(data){
			    		var pData = new Array();
			    		$.each(data,function(index,item){
			    			pData[index] = item;
			    		});
			    		console.log(data);
			    		oCalendar.set('data',data);
			    		oCalendar.render();
			    	},
			    	error:function(){
			    		
			    	}
			    });
			    oCalendar.render();
			    });
	   }
$(document).ready(function(){	
	var i=0;
	var size=$('.f_img_list ul li').size();
    $(document).click(function(ev){
        ev = ev || window.event || arguments.callee.caller.arguments[0]; 
        var target = ev.target || ev.srcElement;
    });

    $('#startenddate').click(function(){
         createPriceCalendar();      
         setTimeout(() => {
        	 $('.price-calendar-bounding-box').attr('style','');
		}, 800);
        
    });
    

    $('#pre').click(function(){
    	
    	i++;
    	move();
    	changePic('pre')
    });
    $('#next').click(function(){
    	
    	i--;
    	move();
    	changePic('next');
    });
    
    $(".f_img_list ul li").mouseover(function(){
    	 var index = $(this).find('img').attr('src');
         $('.f_img img').attr('src',index)
    })
    
    function move(){
    	 if (i == size) {
             $(".f_img_list ul").css({left: 0})
             i = 1;
         }
         if (i == -1) {
             $(".f_img_list ul").css({left: -(size - 1) * 140})
             i = size - 1;
         }

         $(".f_img_list ul").stop().animate({left: -i * 140}, 500)
    }
    
    $("#price-calendar-1493797734364").width();
});