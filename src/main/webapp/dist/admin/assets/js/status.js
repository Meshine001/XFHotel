$(document).ready(function(){

	var ID=window.sessionStorage.getItem('roomId');

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
			//	console.log(data)
				if(data.statusCode==1){
					 status();
				}else{
					alert(data.content)
				}
				
			}
		})
		
		
		function status(){
			var _startDate=$("#otherCheck-in").val();
			var _endDate=$("#otherCheck-out").val();
			
			_startDate=(new Date(_startDate)).getTime()+ 1000 * 60 * 60 * 4;
			_endDate=(new Date(_endDate)).getTime()+ 1000 * 60 * 60 * 4;
		//	console.log(_startDate+'&&&&'+_endDate+'&&&'+ID)
			$.ajax({
				type : 'post',
				dataType : 'json',
				url:'/admin/house1',
				data : {
					'state' : 0,
					'startDate':_startDate,
					'endDate':_endDate,
					'apartmentId':ID
				},
				error:function(e){
					alert('修改错误')
				},
				success:function(data){
					console.log(data)
					alert(data.content)
					location=location
				}
			});
		}
		
		
	})
	
	
})
