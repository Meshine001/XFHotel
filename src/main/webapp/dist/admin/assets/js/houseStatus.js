



window.onload=function(){
	var mydate=new Date();
    var thisyear=mydate.getFullYear();
    var thismonth=mydate.getMonth()+1;
    var thisday=mydate.getDate();
	var days=getDaysInOneMonth(thisyear,thismonth); 
	var dateitem=[];

   
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
     	
		function GetDateStr(AddDayCount) {   
			   var dd = new Date();  
			   dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期  
			   var y = dd.getFullYear();   
			   var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0  
			   var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate();//获取当前几号，不足10补0  
			   return y+"-"+m+"-"+d;   
		}  
     	
	Date.prototype.format = function() {  
	      var s = '';  
	      var mouth = (this.getMonth() + 1)>=10?(this.getMonth() + 1):('0'+(this.getMonth() + 1));  
	      var day = this.getDate()>=10?this.getDate():('0'+this.getDate());  
	      s += this.getFullYear() + '-'; // 获取年份。  
	      s += mouth + "-"; // 获取月份。  
	      s += day; // 获取日。  
	      return (s); // 返回日期。  
	  }; 
	  function getAll(begin, end) {  
	      var ab = begin.split("-");  
	      var ae = end.split("-");  
	      var db = new Date();  
	      db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);  
	      var de = new Date();  
	      de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);  
	      var unixDb = db.getTime();  
	      var unixDe = de.getTime();  
	      for (var k = unixDb; k <= unixDe;) {  
	          dateitem.push((new Date(parseInt(k))).format());  
	          k = k + 24 * 60 * 60 * 1000;  
	      }  
	  }  
	  
	    getAll(startDate, GetDateStr(14))		 
				  	
     	$(".info .ymd").html('')
     	for(a=0;a<dateitem.length;a++){
				    e_date+='<th data="'+dateitem[a]+'">'+dateitem[a]+'</th>';	
     	}
		$(".info .ymd").append(e_date)
		$(".info .ymd th").css({'min-width':'90px'})
		$(".info .ymd th:first-child").html('今天')		
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

                for(a=0;a<dateitem.length;a++){

						if(gg[dateitem[a]].roomNum=='0'){
	 						info_date+='<td data="'+dateitem[a]+'" _hid="'+id+'" style="background-color:orangered;color:#fff">￥'+gg[dateitem[a]].price+'</td>';
	 					}else{
	 						info_date+='<td data="'+dateitem[a]+'"  _hid="'+id+'">￥'+gg[dateitem[a]].price+'</td>';
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
			//	console.log(data)
				var str="";
				var _tr="";
				$("#h-info,#h-name").html('');
				for(var i=0;i<data.content.length;i++){
					
					str+='<tr hid="'+data.content[i].id+'"><td style="text-align:left">'+data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+'-'+data.content[i].position.men_pai+'</td></tr>'
									
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
