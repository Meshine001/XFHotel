$(document).ready(function(){
	var _status,_time;
	var ID=window.sessionStorage.getItem('roomId');
	
	$(".status-time input").on('change',function(){
		_time=$(".status-time input").val();
		_time= (new Date(_time)).getTime()+ 1000 * 60 * 60 * 4;
	})
	$(".status-house a").click(function(){
		$(this).addClass('hat').siblings().removeClass('hat');
		_status=$(this).attr('stag')
	})
	
	$(".btn-primary").click(function(){
		
		if(_time==null || _time==undefined){
			alert('请您先选择日期')
			return;
		}
		if(_status==null || _status==undefined){
			alert('请您选择要设置的房屋状态')
			return;
		}
		console.log(_status+"&"+_time+"&"+ID);
		$.ajax({
			type : 'post',
			dataType : 'json',
			url:'/admin/house',
			data : {
				'state' : _status,
				'data':_time,
				'apartmentId':ID
			},
			error:function(e){
				alert('修改错误')
			},
			success:function(data){
				console.log(data)
				alert('修改成功')
			}
		})
	})
	
})
