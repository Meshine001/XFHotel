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
	
	$(".btn-primary").click(function(){
		
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
	
})
