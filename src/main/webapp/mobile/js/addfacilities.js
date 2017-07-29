$(document).ready(function(){
	  var _oederId='';
	  var facilId="";
	  var sp="";
	  var _addtime="";
	  var dayList=new Array();
	  var lastList=new Array();
var addfacilities={
    house:function(){//判断房间
    	var _uid=fnBase.huoqu(0,"uid");
        if(_uid==null || _uid=="undefined" || _uid==""){
            window.location.href="login.html";
            return;
        }
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
    },
    acquisition:function(){ //添加物品
    	
    	
    	$('.shoufeiList').on('click',' .toll .toll-c',function(){ 
    		if($(this).find('.toll-c').hasClass('active')==true){
    			$(this).find('.toll-c').removeClass('active')
    		}else{
    			$(this).find('.toll-c').addClass('active')
    		}
    		countPrice();
    	})

    	$(".per-order-status .list").on('click','.toll .numContainer .riNum',function(){
    		//fnBase.myalert('增加')
    		var currentLi=$(this).parent().parent();
			var num=parseInt(currentLi.attr("num"),10);		
			num=num+1;
			currentLi.find('input').attr('value',num);
			currentLi.attr("num",num);
			countPrice();
    	})
    	
    	$(".per-order-status .list").on('click','.toll .numContainer  .ceText input',function(){
    		
    	})
   
    	
		
    	$(".vip_info_list li .per-order-status").on('click','.toll .numContainer .leNum',function(){
    		//fnBase.myalert('减少')
    		var currentLi=$(this).parent().parent();
			var num=parseInt(currentLi.attr("num"),10);		
			if(num<=1){
				  num=1;
				  fnBase.myalert("数量不能小于1");
				  return;
				}
			num=num-1;
			currentLi.attr("num",num);
			currentLi.find('input').val(num);
			countPrice();
    	})
    	//计算价格
		function countPrice(){
		    totalPrice=0;
		    var liLength=$(".vip_info_list li .per-order-status .toll").length;
			var myts=0;
			selectProList=[];
		    for(var i=0;i<liLength;i++){
				var danjia=$(".vip_info_list li .per-order-status .toll").eq(i).attr("price");
				var shuliang=$(".vip_info_list li .per-order-status .toll").eq(i).attr("num");
				if($(".vip_info_list li .per-order-status .toll").eq(i).find('.toll-c').hasClass('active')==true){
				  totalPrice=fnBase.accAdd(totalPrice, fnBase.accMul(danjia, shuliang));
				  selectProList.push($(".vip_info_list li .per-order-status .toll").eq(i).attr("cartid"));
				}
		   }
		    $(".bottomContainer span").text(totalPrice);
		  
		}
    	
    	
    	
    }


}
	
	//查询免费
    var locadMianfeiSheshi=function(){
  		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'classify':0
			},
			url : Constant.URL+'/facility/getFacilityForm',
			success : function(data) {
				console.log(data)
				var srt="";
				$(".mianfeiList .list").html("");
				for(var i=0;i<data.length;i++){
					srt+='<div class="toll" id="'+data[i].id+'" price="'+data[i].price+'" num="1"><div class="toll-c"><i>'+data[i].name+'</i></div><div class="numContainer"><div class="fl leNum"></div><div class="fl ceText"><input type="type"  disabled placeholder="1">天</div><div class="fr riNum"></div></div></div>';
				};
				$(".mianfeiList .list").append(srt);
			}
		});
  	}
    
	//查询免费
    var locadShoufeiSheshi=function(){
  		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'classify':1
			},
			url : Constant.URL+'/facility/getFacilityForm',
			success : function(data) {
				console.log(data)
				var srt="";
				$(".shoufeiList .list").html("");
				for(var i=0;i<data.length;i++){
					srt+='<div class="toll" id="'+data[i].id+'" price="'+data[i].price+'" num="1"><div class="toll-c"><i>'+data[i].name+'</i><i style="color:red">￥'+data[i].price+'</i><i>/天</i></div><div class="numContainer"><div class="fl leNum"></div><div class="fl ceText"><input type="type" disabled placeholder="1">天</div><div class="fr riNum"></div></div></div>';
				};
				$(".shoufeiList .list").append(srt);
			}
		});
  	}
    
  
    $(".house-status ol dd").live('click',function(){
        $(this).addClass('_active').siblings().removeClass('_active');
        if($(this).hasClass('_active')==true){
            _oederId=$(this).attr('hid');
        }
        return _oederId; //住房订单id
    });

	  $(".per-order-status ol").on('click','dd',function(){
	    	if($(this).hasClass('_active')==true){
	    		$(this).removeClass('_active');
	    		_addtime=$(this).attr('tid'); //添加时间
	    	}else{
	    		$(this).addClass('_active');
	    	}
	    });
    
    $(".per-order-status .list").on('click','.toll .toll-c',function(){
    	if($(this).hasClass('active')==true){
    		$(this).removeClass('active')
		}else{
			$(this).addClass('active');
			facilId=$(this).parent().attr('id'); //物品id
		}
    	var houseList=new Array();
        for(var i=0;i<$(".per-order-status .list .toll").length;i++){
            if($(".per-order-status .list .toll").eq(i).find('.toll-c').hasClass('active')==true){
                houseList.push($(".per-order-status .list .toll").eq(i).attr('id'));
                
            }
        }
        facilId=houseList.join(',');//获取物品id；

    })
    
 
  
	
    
addfacilities.house();   
addfacilities.acquisition(); 

//提交申请  
$(".account-login-width a").click(function(){
	
	for(var i=0;i<$(".per-order-status .list .toll").length;i++){
        if($(".per-order-status .list .toll").eq(i).find('.toll-c').hasClass('active')==true){
        	lastList.push($(".per-order-status .list .toll").eq(i).attr('num')); 
        }
    }
	sp=lastList.join(',');//获取物品使用时间；
	if(facilId=="" || facilId==null){
		fnBase.myalert('请您选择需要添加的物品再提交！')
		return
	}
	if(_addtime=="" || _addtime==null){
		fnBase.myalert('请您选择合适的时间')
		return
	}
	var frontURL=Constant.URL+'/mobile/FacilityOrderAdd/';
    var postData={
    		'oederId':_oederId,
    		'facility':facilId,
    		'fate':sp,
    		'addTime':_addtime,
    		'demand':$(".per-order-status textarea").val()
    };
    console.log(postData);
    fnBase.commonAjax(frontURL,postData,function(data){
    	console.log(data);
    	fnBase.myalert('提交成功');
        setTimeout(function(){
        	window.location.href='serve.html';
        },300)
    })
	
}) 
       
locadMianfeiSheshi();	
locadShoufeiSheshi();


});
