$(document).ready(function(){
	var _status,_statime,_endtime;
	var ID=window.sessionStorage.getItem('roomId');
	
	$(".status-time input").on('change',function(){
		_statime=$(".status-time input").val();
		_statime= (new Date(_statime)).getTime()+ 1000 * 60 * 60 * 4;
	});
	$(".status-endtime input").on('change',function(){
		_endtime=$(".status-endtime input").val();
		_endtime=(new Date(_endtime)).getTime()+ 1000 * 60 * 60 * 4;
	});
	
	$(".status-house a").click(function(){
		$(this).addClass('hat').siblings().removeClass('hat');
		_status=$(this).attr('stag')
	})
	
	$("#btnyesDate").click(function(){
	
		if(_statime==null || _statime==undefined || _endtime==null || _endtime==undefined){
			alert('请您先选择日期')
			return;
		}
		if(_status==null || _status==undefined){
			alert('请您选择要设置的房屋状态')
			return;
		}
		console.log(_status+"&"+_statime+"&"+ID+"&"+_endtime);
		$.ajax({
			type : 'post',
			dataType : 'json',
			url:'/admin/house',
			data : {
				'state' : _status,
				'startDate':_statime,
				'endDate':_endtime,
				'apartmentId':ID
			},
			error:function(e){
				alert('修改错误')
			},
			success:function(data){
				console.log(data)
				alert('修改成功')
				location=location
			}
		})
	})
	
	
	//其他平台订单信息统计
	$("#otherOrder").click(function(){
		var add=$("#otherAddress").val();
		if( add==null || add==undefined ||add==""){
			alert('请您填写订单来源后再提交')
			return;
		}
		var value=$("#otherSave").val();
		if( value==null || value==undefined ||value==""){
			alert('请您填写订单价格后再提交')
			return;
		}
		var name=$("#otherName").val();
		if( name==null || name==undefined ||name==""){
			alert('请您填写入住人姓名后再提交')
			return;
		}
		var chickin=$("#otherCheck-in").val();
		if( chickin==null || chickin==undefined ||chickin==""){
			alert('请您填写入住时间后再提交')
			return;
		}
		var chickout=$("#otherCheck-out").val();
		if( chickout==null || chickout==undefined ||chickout==""){
			alert('请您填写离开时间后再提交')
			return;
		}
		var otherdate=$("#otherdate").val();
		if( otherdate==null || otherdate==undefined ||otherdate==""){
			alert('请您填写入住总天数后再提交')
			return;
		}
		var othertel=$("#otherTel").val();
		if( othertel==null || othertel==undefined ||othertel==""){
			alert('请您填写入住人电话号码后再提交')
			return;
		}

		$.ajax({
			type:'post',
			dataType:'json',
			url:'/admin/Rests',
			data:{
				'source': add,
				'startTime' : chickin,
				'endTime':chickout,
				'sum':value,
				'apId':ID,
				'name':name,
				'fate':otherdate,
				'tel':othertel
			},
			success:function(data){
				console.log(data)
				if(data.statusCode==1){
					alert('信息成交成功，请及时修改房态。')
					location=location;
				}else{
					alert(data.content)
				}
				
			}
		})
		
	})
	
	
})
