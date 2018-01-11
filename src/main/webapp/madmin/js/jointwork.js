$(document).ready(function(){
	var id=fnBase.huoqu(0,'uid'); // 0:超管   1：普通管理员 
	var userType=fnBase.huoqu(0,'userType');
	var pd="";
	
	$(".layer-select a").click(function(){
		$(this).addClass('show').siblings().removeClass('show');
		var i=$(this).index();
		if(i==0){
			$(".content .houseList").eq(0).show();
			$(".content .houseList").eq(1).hide();
		}else{
			$(".content .houseList").eq(0).hide();
			$(".content .houseList").eq(1).show();
		}
	})


	// 商户列表
	masglistData();
	function masglistData(){
		$.ajax({
			type:'post',
			dataType:'json',
			data:{},
			async: false,
			url:'/admin/Jointwork1',
			success:function(data){
				console.log(data);
				$(".content .houseList:eq(0) ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><p>公司名称：'+data.content[i].tradeName+'</p><p>电话号码：'+data.content[i].tel+'</p><p>账号：'+data.content[i].userName+'</p><p>密码：'+data.content[i].password+'</p><p>注册时间：'+data.content[i].time+'</p><p class="cz-btn">操作：<span data-id="'+data.content[i].id+'">删除</span></p></li>';
				}
				$(".content .houseList:eq(0) ul").append(ai);
			}
		})
	};
	
	//删除商户

	$(".houseList ul").on('click',' li .cz-btn span',function(){
		console.log($(this).attr('data-id'))
		var url = '../admin/TenantD';
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : $(this).attr('data-id'),
		    },
			url : url,
			success : function(data) {
				console.log(data)
				if(data.statusCode == 1){
					fnBase.myalert(data.content);
					setTimeout(function(){location=location;},1550)
				}else{
					fnBase.myalert(data.content);
				}
			}
		});
	})

	//创建
	$(".layer-select .layer-nav.show").click(function(){
		$("#maskShow").show();
		$("#module-box").addClass('hover')
	})
	
	$("#newIp").click(function(){
		var account=$("#inputzh").val();
		var password=$("#inputmm").val();
		var name=$("#inputname").val();
		var tel=$("#inputtel").val();
		if(account==""||password==""||name==""||tel==""){
			alert('请填写上述完整信息')
		}else{
			var postdata={
					'userName':account,
					'password':password,
					'tradeName':name,
					'tel':tel
			}
			console.log(postdata);
			$.ajax({
				type:'POST',
				dataType:'json',
				data:postdata,
				url:'/admin/TenantAdd',
				success:function(data){
					if(data.statusCode == 1){
						fnBase.myalert(data.content);
						setTimeout(function(){location=location;},1550)
					}else{
						fnBase.myalert(data.content);
					}
				}
			})
		}
	})	
		
});
