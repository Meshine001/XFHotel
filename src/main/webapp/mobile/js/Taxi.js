$(document).ready(function(){
	var currentObj="";
	var dateSelect=""; 
    var _uid=fnBase.huoqu(0,"uid");
    if(_uid==null || _uid=="undefined" || _uid==""){
        window.location.href="login.html";
        return;
    }
	//植入汽车服务$("#jiesongWarp")
    var Service={
    		jiesong:function(){
    			 var frontURL=Constant.URL+'/site/getSiteyForm/';
    			     postData={classify:0};
    			     var str="";
    			 fnBase.commonAjax(frontURL,postData,function(data){
    				 console.log(data);
    				 for(var i=0;i<data.length;i++){
    					 str+='<dd cat="'+data[i].id+'" price="'+data[i].price+'"><i>'+data[i].place+'</i><i>'+data[i].price+'/元</i></dd>'
    				 }
    				 $("#jiesongWarp").append(str);
    			 })
    		},
    		baoche:function(){
    			 var frontURL=Constant.URL+'/site/getSiteyForm/';
			     	 postData={classify:1};
			         var str="";
    			 fnBase.commonAjax(frontURL,postData,function(data){
    				 console.log(data);
    				 for(var i=0;i<data.length;i++){
    					 str+='<dd cat="'+data[i].id+'" price="'+data[i].price+'"><i>'+data[i].place+'</i><i>'+data[i].price+'/元</i></dd>'
    				 }
    				 $("#baocheWarp").append(str);
    			 })
    		}
    }
    Service.jiesong();//接送
    Service.baoche();//包车
    
    
 
	//选择房间id
	var _oederId='';
    $(".house-status ol").on('click','dd',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
        if($(this).hasClass('_active')==true){
            _oederId=$(this).attr('hid');
        }
        return _oederId;
    });
	//选择车 car-status
    var _carid='';
    $(".car-status ol").on('click','dd',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
        if($(this).hasClass('_active')==true){
        	_carid=$(this).attr('ct_name');
        }
        return _carid;
    });
    
	//用车服务、单次提交
	$(".per-gap-conter labler").click(function(){
		$(".bottomContainer span").text('0');
		$(this).parent().find('ol dd').removeClass('_active');
		$(".per-gap-conter ol,.per-gap-conter .time").hide();
		$(this).find('span').addClass('czs-circle-o');
		$(this).parent().siblings().find('span').removeClass('czs-circle-o');
		if($(this).parent().find('span').hasClass('czs-circle-o')==true){
			currentObj=$(this).parent().find('ol,.time');
			displayobj(1)
		}else{
			return;
		}
		if($(".per-gap-conter").eq(0).find('labler span').hasClass('czs-circle-o')==true){
			dateSelect=1;
		}else{
			dateSelect=2;
		}
	})
	 function displayobj(flag){
        if(flag==1){
            currentObj.show();
        }else{
            currentObj.hide();
        }
    };
    

    
   
    
  
    
    var postData={uId:_uid,type:0 };
    var frontURL=Constant.URL+'/mobile/Clean';
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        if(data.content.length==0){
            $("#atl").show();
        }
        var str='';
        $(".house-status ol").html('');
        for(var i=0;i<data.content.length;i++){
            str+='<dd hid="'+data.content[i].id+'">'+data.content[i].description+'</dd>'
        }
        $(".house-status ol").append(str);
    });

   
    var _oederId='';
    $(".house-status ol dd").live('click',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
        if($(this).hasClass('_active')==true){
            _oederId=$(this).attr('hid');
        }
        return _oederId;
    });

    var _content='';

    $(".content-status ol dd").live('click',function(){
        if($(this).hasClass('_active')==false){
            $(this).addClass('3213213');
        }else{
            $(this).removeClass('12312312');
        }
        var houseList=new Array();
        for(var i=0;i<$(".content-status ol dd").length;i++){
            if($(".content-status ol dd").eq(i).hasClass('_active')==true){
                houseList.push($(".content-status ol dd").eq(i).attr('paid'));
            }
        }
        _content=houseList.join(',');
        return _content;
    });

    //计算时间差、价格
    function DateDiff(sDate1, sDate2) {  


        var aDate, oDate1, oDate2, iDays;

        aDate = sDate1.split("-");

        oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);  

        aDate = sDate2.split("-");

        oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0]);

        iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24); 

        iDays=iDays+1;
        var topic=_price*iDays;
        $(".bottomContainer span").text(topic)
        return topic;  

    }
	//选择一次一个
    var _carip="";
    var _price="";
	$(".per-gap-conter ol").on('click','dd',function(){
		$(this).addClass('_active').siblings().removeClass('_active');
		_carip=$(this).attr('cat');
		if(dateSelect==2){
			_price=$(this).attr('price');
			if($("#endDate2").val()!="" &&  $("#stateDate2").val()!=""){
				DateDiff($("#endDate2").val(),$("#stateDate2").val());
			}
		}else if(dateSelect==1){
			$(".bottomContainer span").text($(this).attr('price'));
		}
		
	})
	   $("#endDate2").bind('input propertychange', function() {
		   if($("#stateDate2").val()==""){
				 return
			  }else{
				 DateDiff($("#endDate2").val(),$("#stateDate2").val());  
		   }
	   });
	  $("#stateDate2").bind('input propertychange', function() {
		  if($("#endDate2").val()==""){
			 return
		  }else{
			 DateDiff($("#endDate2").val(),$("#stateDate2").val());  
		  }
	   });
	 
	


    //提交
    $(".account-login-width  a").click(function(){
    	if(_oederId==""){fnBase.myalert('请先选择房间');return;};
    	if(_carid==""){fnBase.myalert('请先选择合适的车辆');return;};
    	if(_carip==""){fnBase.myalert('请先选择需要的出行');return;};
    	if($("#tel").val()==""){fnBase.myalert('请填写您的电话号码');return;};
    	if(dateSelect==1){
    		   var sta=$("#jiesongDate").val();
    	       var starttimeHaoMiao = (new Date(sta)).getTime(); //接送站的具体时间
    	       if(sta==""){fnBase.myalert('请选择接送时间');return;};
        	
	        	 var postData={
	     	    		OrderId:_oederId,
	     	    		site1:_carid,
	     	            tripId:_carip,
	     	            startTime:starttimeHaoMiao,
	     	            endTime:starttimeHaoMiao,
	     	            tel:$("#tel").val(),
	     	            price:$(".bottomContainer span").text(),
	     	            demand:$('.per-order-status ._textarea').val()
	     	        };
	     	        console.log('接送站');
	     	        console.log(postData);
	     	        var postUrl=Constant.URL+"/mobile/tripOrderAdd";
	     	        fnBase.commonAjax(postUrl,postData,function(data){
	    				 console.log(data);
	    				 if(data.statusCode==1){
	    					 fnBase.myalert('提交成功');
	    					 window.location.href="paymentCA.html?pid="+encodeURIComponent(data.content.id)+"";
	    				 }else{
	    					 fnBase.myalert(data.content)
	    				 }
	    			 })
    	      
    	        
    	}else if(dateSelect==2){
    		   if($("#stateDate2").val()==""){fnBase.myalert('请选择开始时间');return;};
    		   if($("#endDate2").val()==""){fnBase.myalert('请选择结束时间');return;};
    		   var postData={
     	            OrderId:_oederId,
     	            site1:_carid,
    	            tripId:_carip,
    	            startTime:(new Date($("#stateDate2").val())).getTime(),
    	            endTime:(new Date($("#endDate2").val())).getTime(),
    	            tel:$("#tel").val(),
    	            price:$(".bottomContainer span").text(),
    	            demand:$('.per-order-status ._textarea').val()
     	        };
    		    console.log('包车')
     	        console.log(postData)
     	        var postUrl=Constant.URL+"/mobile/tripOrderAdd";
	     	    fnBase.commonAjax(postUrl,postData,function(data){
	    				 console.log(data);
	    				 if(data.statusCode==1){
	    					 fnBase.myalert('提交成功');
	    					 window.location.href="paymentCA.html?pid="+encodeURIComponent(data.content.id)+"";
	    				 }else{
	    					 fnBase.myalert(data.content);
	    				 }
	    		})
    	      
    		   
    	}

    	
    	
    	
    });
//  setTimeout(function(){
//	window.location.href='serve.html';
//},300)
});
