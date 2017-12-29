$(document).ready(function(){
			var userType=window.localStorage.getItem('userType');//1:管理员 ,0:超级管理员
			var uid=window.localStorage.getItem('uid');
		
			
			if(userType==1){
				$("#app-moble-car,#app-moble-wx,#app-moble-clean,#app-moble-order,#app-moble-house,#app-moble-add").show();
				getmsghouseNum();
			}else{
				$(".app-ng").show();
				getData()
			}

			function getData(){
	 			$.ajax({
					type:'post',
					dataType:'json',
					data:{},
					url:'/admin/getData',
					success:function(data){
						console.log(data);
						$("#app-moble-house .value").html('<span class="sign"></span>'+data.content.room+'');
						$("#app-moble-order .value").html('<span class="sign"></span>'+data.content.order+'');
						$("#app-moble-clean .value").html('<span class="sign"></span>'+data.content.clean+'');
						$("#app-moble-wx .value").html('<span class="sign"></span>'+data.content.facility+'');
						$("#app-moble-car .value").html('<span class="sign"></span>'+data.content.trip+'');
						$("#app-moble-add .value").html('<span class="sign"></span>'+data.content.facility+'')
						$("#app-moble-power .value").html('<span class="sign"></span>'+data.content.user+'')
						$("#app-moble-app .value").html('<span class="sign"></span>'+data.content.landlord+'')
						$("#app-moble-user .value").html('<span class="sign"></span>'+data.content.customer+'')
						$("#app-moble-yhj .value").html('<span class="sign"></span>'+data.content.coupon+'')
						$("#app-moble-life .value").html('<span class="sign"></span>'+data.content.blog+'');
						$("#app-moble-coopler .value").html('<span class="sign"></span>'+data.content.tenant+'')
						
						
					}
			    }); 
			}
		    
		    
		    
		    function getmsghouseNum(){
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/steward',//统计用户有多少管理的房屋
					success:function(data){
						$("#app-moble-house .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardO',//统计住房订单
					success:function(data){
						$("#app-moble-order .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
/* 				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardC',//统计保洁订单
					success:function(data){
						$("#app-moble-clean .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardF',//统计维修订单
					success:function(data){
						$("#app-moble-wx .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardFO',//统计添加设置订单
					success:function(data){
						$("#app-moble-add .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				});
				$.ajax({
					type:'post',
					dataType:'json',
					data:{'id':uid},
					url:'/admin/user/stewardT',//统计轿车订单
					success:function(data){
						$("#app-moble-car .value").html('<span class="sign"></span>'+data.content.length+'')
					}
				}); */
				
				
		    }
		    
		    
})
