$(document).ready(function(){
	var _price;
	var _time;
	var ID=window.sessionStorage.getItem('roomId');
	
	$("#teshuDate").on('change',function(){
	    _time=$(this).val();
	})
	
//	$("#teshuSubmit").on('click',function(){
//		
//		_price=$("#teshuprice").val();
////		_time= (new Date(_time)).getTime()+ 1000 * 60 * 60 * 4;
//		// alert(new Date(_time)) 12:00
//		if(_time==null || _time==undefined){
//			alert('请您选择要设置特殊价的日期')
//			return;
//		}
//		if(_price == null || _price == undefined){
//			alert('请您选择要设置特殊价的价格')
//			return;
//		}
//		alert(_time+"-"+_price)
//		$.ajax({
//			type : 'post',
//			dataType : 'json',
//			url:'/admin/house',
//			data : {
//				'state' : _status,
//				'data':_time,
//				'apartmentId':ID
//			},
//			error:function(e){
//				alert('修改错误')
//			},
//			success:function(data){
//				console.log(data)
//				alert('修改成功')
//				location=location
//			}
//		})		
	
//	})
	

//	设置折扣
	var d3,d7,d30;
	$("#dateTime").click(function(){
		d3=$("#date3").val();
		d7=$("#date7").val();
		d30=$("#date30").val();
//		alert(d3+"-"+d7+"-"+d30+"-"+ID)
//		$.ajax({
//			type : 'post',
//			dataType : 'json',
//			url:'/admin/house',
//			data : {
//				'state' : _status,
//				'data':_time,
//				'apartmentId':ID
//			},
//			error:function(e){
//				alert('修改错误')
//			},
//			success:function(data){
//				console.log(data)
//				alert('修改成功')
				$("#_date3").val(d3);
				$("#_date7").val(d7);
				$("#_date30").val(d30);
//				location=location
//			}
//		})	
	})
	
})
