$(document).ready(function(){
	
	var totalPrice=0;
	var selectProList=new Array();
	$(".account-login-width").click(function(){
		 var liListStr=selectProList.join(",");
        console.log(liListStr)
    })
	
	

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
    	$('.per-order-status .toll .toll-c').click(function(){ 
    		if($(this).hasClass('active')==true){
    			$(this).removeClass('active')
    		}else{
    			$(this).addClass('active')
    		}
    		countPrice();
    	})
    	
    	$(".vip_info_list li .per-order-status .toll .numContainer .riNum").click(function(){
    		//fnBase.myalert('增加')
    		var currentLi=$(this).parent().parent();
			var num=parseInt(currentLi.attr("num"),10);		
			num=num+1;
			currentLi.attr("num",num);
			currentLi.find('input').val(num);
			countPrice()
    	})
    	$(".vip_info_list li .per-order-status .toll .numContainer .leNum").click(function(){
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
			countPrice()
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
	
addfacilities.house();   
addfacilities.acquisition();        


    

});
