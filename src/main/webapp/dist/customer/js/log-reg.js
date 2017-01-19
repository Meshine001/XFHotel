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
					window.location.href = "./customer/myOrder";
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
	$("#reg-tel").blur(function() {
		var tel = $("#reg-tel").val();

		$.ajax({
			cache : true,
			type : "GET",
			url : "./customer/checkTel?tel=" + tel,
			async : false,
			error : function(request) {

			},
			success : function(data) {
				if (data) {
					
				} else {
					
				}
			}
		});

	});

});
