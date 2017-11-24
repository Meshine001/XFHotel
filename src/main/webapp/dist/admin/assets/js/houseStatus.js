



window.onload=function(){
	var mydate=new Date();
    var thisyear=mydate.getFullYear();
    var thismonth=mydate.getMonth()+1;
    var thisday=mydate.getDate();
	var days=getDaysInOneMonth(thisyear,thismonth); 


   
    function initdata(){
   
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var startDate=year+"-"+month+"-"+day;
        function p(s) {
                 return s < 10 ? '0' + s: s;
        }
     	var a=1,e_date="";
     	$(".info .ymd").html('')
     	for(a=1;a<=days;a++){
				    dates =  thisyear + '-' + p(thismonth) + '-' + p(a);
				    if(dates==startDate){
				    	e_date+='<th data="'+dates+'" style="color:#29c75f">今天</th>';
				    }else if(dates<startDate){
				    	e_date+=''
				    }else{
				    	e_date+='<th data="'+dates+'">'+p(thismonth) + '-' + p(a)+'</th>';
				    }
 					
     	}
		$(".info .ymd").append(e_date)
				
				
    }
    initdata();
 
 	function getDaysInOneMonth(year, month){
	    month = parseInt(month, 10);
	    var day= new Date(year, month, 0);  
	    return day.getDate();  
	}
    function getfirstday(year,month){ 
        month=month-1;
        var d=new Date(year,month,1);
        return d.getDay();
    }
 	
 	function setcalender(days,id){
 	//console.log(id)
   		var info_date="";	 
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDate();
        var startDate=year+"-"+month+"-"+day;
        var dateList="";
        var gg=new Array();		
		


		
	

    	$.ajax({
    		type : 'POST',
			dataType : 'json',
			async:false,
        	url:'http://www.yiyunzn.xyz/mobile/price/'+id+'/'+startDate,
        	data:{'id':id,'startDate':startDate},
        	success:function(data){
        		dateList=data
        		jQuery.each(dateList, function(key, val){
                    gg[key] = val;
                  });
                function p(s) {
                    return s < 10 ? '0' + s: s;
                }
				var dates=""; 
                var hm=(new Date(dates)).getTime()+4*60*60*1000;
                var newTime = new Date(hm);  

				for(var a=1;a<=days;a++){
				    dates =  thisyear + '-' + p(thismonth) + '-' + p(a);
					if(dates<startDate){
						info_date=""
					}else{
						if(gg[dates].roomNum=='0'){
	 						info_date+='<td data="'+dates+'" _hid="'+id+'" style="background-color:orangered;color:#fff">￥'+gg[dates].price+'</td>';
	 					}else{
	 						info_date+='<td data="'+dates+'"  _hid="'+id+'">￥'+gg[dates].price+'</td>';
	 					}
					}
				}  

				 var zs='<tr id="'+id+'">'+info_date+'</tr>';
				
				$(".info tbody").append(zs)
				
        	}
    	})
    	
    	
    
    }


	var uid=window.localStorage.getItem('uid');
 	function gethouse(){
 				
 		    $.ajax({
 				type:'post',
 				dataType:'json',
 				data:{'id':uid},
 				url:'/admin/user/steward',
        	success:function(data){
				console.log(data)
				var str="";
				var _tr="";
				$("#h-info,#h-name").html('');
				for(var i=0;i<data.content.length;i++){
					
					str+='<tr hid="'+data.content[i].id+'"><td>'+data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+'-'+data.content[i].position.men_pai+'</td></tr>'
									
					setcalender(days,data.content[i].id);
				}
				
				$("#h-name").append(str);			
        	}
    	})
 	}
 
 	gethouse();
 	
 	$("#h-name").on('click','tr',function(){
 		var isId=$(this).attr('hid');
 		window.location.href='../admin/status/?id='+isId;
 	})

 	
}
