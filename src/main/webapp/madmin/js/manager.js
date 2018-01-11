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

	
	function ymd(str){
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),
        oTime = oYear +'-'+ oMonth +'-'+oDay;
        return oTime; 
	}
	
	// 成员列表
	masglistData();
	function masglistData(){
		$.ajax({
			type:'post',
			dataType:'json',
			data:{},
			async: false,
			url:'/admin/Manager1',
			success:function(data){
				console.log(data);
				$(".content .houseList:eq(0) ul").html('');
				var ai="";
				for(var i=0;i<data.content.length;i++){
					ai+='<li data-id="'+data.content[i].id+'"><p>姓名：'+data.content[i].username+'</p><p>电话号码：'+data.content[i].contact+'</p><p>权限：'+data.content[i].authority+'</p><p>上次登录时间：'+data.content[i].time+'</p><p class="cz-btn">操作：<span data-id="'+data.content[i].id+'">查看</span><span data-id="'+data.content[i].id+'" tel="'+data.content[i].contact+'" name="'+data.content[i].username+'" authority="'+data.content[i].authority+'" class="setip">修改</span><span data-id="'+data.content[i].id+'">删除</span></p></li>';
				}
				$(".content .houseList:eq(0) ul").append(ai);
			}
		})
	};
	
	//删除成员

	$(".houseList ul").on('click',' li .cz-btn span:last-child',function(){
		console.log($(this).attr('data-id'))
		var url = '../admin/user/delete';
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

	
	//查看房东的房子
	$(".content .houseList:eq(0) ul").on('click','li .cz-btn span:first-child',function(event){
		event.stopPropagation();
		$(".layer-logo").html('<img src="images/computer.png">管理的房屋')
		$("#newIp,.setadminIp").hide();
		$('#maskShow').show()
		$('#module-box').addClass('hover')
		getinfo($(this).parent().parent().attr('data-id'))
	});
	
	function getinfo(uid){
		 fnBase.loadShow();
		var url = '/admin/user/steward';
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'id' : uid
			},
			url : url,
			success : function(data) {
				 fnBase.loadHide();
				console.log(data)
				var str="";
				$(".module-con ul").html("");
				for(var i=0;i<data.content.length;i++){
					str+='<li><p>名称：'+data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+'-'+data.content[i].position.men_pai+'</p><p>地址：'+data.content[i].position.bd_wei_zhi+'-'+data.content[i].position.jie_dao+'</p><p>价格：'+data.content[i].basic_info.jia_ge+'元/天</p></li>';
				}
				if(data.length<=0){
					str='<li><p>暂时没有房屋</p></li>'
				}
				$(".module-con ul").append(str);
			}
		});
	}
	

	$(".layer-select .layer-nav.show").click(function(){
		$("#maskShow").show();
		$("#module-box").addClass('hover')
		$(".setadminIp").hide();
	})
	
	//创建IP
	
			//3确认创建
		$("#newIp").click(function(){
			var account=$("#inputzh").val();
			var password=$("#inputmm").val();
			var ip=$("#ipmassage option:selected").attr('ip');
			var tel=$("#inputdh").val();
			if(account==""||password==""||ip==""||tel==""){
				alert('请填写上述完整信息')
			}else{
				var postdata={
						'username':account,
						'psd':password,
						'authority':ip,
						'tel':tel
				}
				console.log(postdata);
				$.ajax({
					type:'POST',
					dataType:'json',
					data:postdata,
					url:'/admin/user/add',
					success:function(data){
						console.log(data);
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
	
	var persons2="";
		//设置修改 信息
		$(".houseList ul").on('click',' li .cz-btn .setip',function(){
			$(".layer-logo").html('<img src="images/computer.png">修改信息')
			$('#maskShow').show()
			$('#module-box').addClass('hover')
			$("#inputzh").val($(this).attr('name'));
			$("#inputdh").val($(this).attr('tel'));
			$("#ipmassage").val($(this).attr('authority'));
			$("#newIp").hide();
			persons2=$(this).attr('data-id')
		})
		
		$(".setadminIp").click(function(){//提交修改内容
			var _name=$("#inputzh").val();
			var _password=$("#inputmm").val();
			var _date=$("#ipmassage option:selected").attr("ip");
			var _tel=$("#inputdh").val();
			var postData={
					'username':_name,
					'psd':_password,
					'authority':$("#ipmassage option:selected").attr("ip"),
					'tel':_tel,
					'id':persons2
			}
			if(_name==""||_password==""||_date==""||_tel==""){
				alert('请填写上述完整信息')
			}else{
				$.ajax({
					type:'post',
					dataType:'json',
					data:postData,
					url:'/admin/user/amend',
					success:function(data){
						console.log(data)
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
