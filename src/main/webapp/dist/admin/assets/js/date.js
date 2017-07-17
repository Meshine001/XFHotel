 window.onload=function(){
	
	 
    var mydate=new Date();
    var thisyear=mydate.getFullYear();
    var thismonth=mydate.getMonth()+1;
    var thisday=mydate.getDate();
    var mydate1=new Date();
    var thisyear1=mydate1.getFullYear();
    var thismonth1=mydate1.getMonth()+1;
    var thisday1=mydate1.getDate();

    var selectday=thisday;
   
    var datetxt="datetoday";
    var datefirst;
    var datesecond;
    function initdata(){
     //日期初始填充
     var tdheight=$(".data_table tbody tr").eq(0).find("td").height();
     $(".data_table tbody td").css("height",tdheight);
     //只获取两个月的数据
     if(thismonth<thismonth1 || thisyear<thisyear1){
    	 return
     }else if(thismonth>thismonth1+1){
    	 return
     }
     $(".selectdate").val(thisyear+"年"+thismonth+"月");
     var days=getdaysinonemonth(thisyear,thismonth); //获得这个月的总天数
     var weekday=getfirstday(thisyear,thismonth); // 获得这个月1号，是星期几
     setcalender(days,weekday);
     
    }
    initdata();
    $(".datetoday").val(thisyear+"-"+thismonth+"-"+thisday);
    $(".dateendday").val(thisyear+"-"+thismonth+"-"+(thisday+1));
    
  
    
    function getdaysinonemonth(year,month){
        //算某个月的总天数
        month=parseInt(month,10);
        var d=new Date(year,month,0);
        return d.getDate();
    }
    function getfirstday(year,month){
        //算某个月的第一天是星期几
        month=month-1;
        var d=new Date(year,month,1);
        return d.getDay();
    }
    
    
    
    
    
    
    //价格状态
    function setcalender(days,weekday){
    	var _id = window.sessionStorage.getItem('roomId');
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var startDate=year+"-"+month+"-"+day;
        var dateList;
        var gg=new Array();
        var postData={
          id:_id,
          startDate:startDate
        };
    	$.ajax({
    		type : 'POST',
			dataType : 'json',
        	url:'/mobile/price/'+_id+'/'+startDate,
        	data:postData,
        	success:function(data){
        		dateList=data
        		jQuery.each(dateList, function(key, val){
                    gg[key] = val;
                  });
                function p(s) {
                    return s < 10 ? '0' + s: s;
                }
                //往日历中填入日期
                var a=1;
                
                for(var j=0;j<6;j++){
                 for(var i=0;i<7;i++){
                  if(j==0&&i<weekday){ //空白填充
                   $(".data_table tbody tr").eq(0).find("td").eq(i).html("");
                  }else{
                	  var dates =  thisyear + '-' + p(thismonth) + '-' + p(a);
		                   if(a<=days){ //填日期
		                    $(".data_table tbody tr").eq(j).find("td").eq(i).attr('date-date',dates);
		                   
		                    if(gg[dates].roomNum=='0'){
		                    	 $(".data_table tbody tr").eq(j).find("td").eq(i).html(a+'<br>'+"<i style='color:red'>满房</i>");
		                    	 $(".data_table tbody tr").eq(j).find("td").eq(i).css('background','#f0f0f0');
		                    }else if(gg[dates].roomNum=='1'){
		                    	 $(".data_table tbody tr").eq(j).find("td").eq(i).html(a+'<br>'+gg[dates].price);
		                    	 $(".data_table tbody tr").eq(j).find("td").eq(i).css('background','#fff');
		                    }
		                    
		                    a++;
		                   }else{ // 超过总天数的留空白
		                    $(".data_table tbody tr").eq(j).find("td").eq(i).html("");
		                    a=days+1;
		                   }
                  }
                 }
                }
                
                
        	}
    	})
    	
    	
    
       }
    
    
   
    
    
 

    $(".lastmonth").click(function(){ //上一个月
	    var dates=new Date();
	    var thisYears=dates.getFullYear();
	    var thisMoths=dates.getMonth()+1;
	     if(thismonth<=thisMoths || thisyear<thisYears){
	    	 return 
	     }
	     thismonth--;
	     if(thismonth==0){
	      thismonth=12;
	      thisyear--;
	     }
	     initdata();
    });
    
    $(".nextmonth").click(function(){ //上一个月
	     var dates=new Date();
	     var thisYears=dates.getFullYear();
	     var thisMoths=dates.getMonth()+1;
	     if(thismonth>=thisMoths+1){
	    	 return
	     }
	     thismonth++;
	     if(thismonth==13){
	      thismonth=1;
	      thisyear++;
	     }
	     initdata();
    });

   }