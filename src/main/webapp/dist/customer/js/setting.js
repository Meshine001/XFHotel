$(document).ready(function() {
	$('#change-psd-submit').click(function() {
		var psd1 = $('#password').val();
		var psd2 = $('#password1').val();
		if(psd1 == ''){
			alert("新密码不能为空");
			return;
		}
		
		if (psd1 != psd2) {
			alert("两次新密码不一致");
			return;
		}

		$.ajax({
			cache : true,
			type : "POST",
			url : "./changePsd",
			data : $('#psd-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				
				if(data.statusCode == 0){
					alert(data.content);
				}else{
					alert(data.content);
					window.location.href = "../customer?forword=login";
				}
				$('#psd-form')[0].reset();
			}
		});
	});
});