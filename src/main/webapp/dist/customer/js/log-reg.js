



$(function() {
	

	$('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	/**
	 * 注册
	 */
	$("#register-submit").click(function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "./customer/reg",
			data : $('#register-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				alert(data.content);
				if (data.statusCode == 0) {

				} else {
					window.location.href = "./customer/reservation";
				}

			}
		});
	});

	/**
	 * 登录
	 */
	$("#login-submit").click(function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "./customer/login",
			data : $('#login-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					window.location.href = "./customer/reservation";
				}
			}
		});
	});

	/**
	 * 验证手机
	 */
	$("#tel").blur(function() {
		var tel = $("#tel").val();
		alert("11");
		if (tel == '')
			alert("手机号不能为空！");
		var reg = /^1[3|4|5|7|8][0-9]{9}$/; // 验证规则
		if (!reg.test(tel)) {

		} else {

		}

	});

});
