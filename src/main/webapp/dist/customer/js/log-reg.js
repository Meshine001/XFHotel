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
			url : "./reg",
			data : $('#register-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				alert(data.content);
				if (data.statusCode == 0) {

				} else {
					window.location.href = "./myOrder";
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
			url : "./login",
			data : $('#login-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					window.location.href = "./myOrder";
				}
			}
		});
	});

	/**
	 * 验证手机是否注册
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

	/**
	 * 显示图片验证码
	 * 
	 */
	$('#btn-validate').click(function() {
		/* 使用Javascript方法绑定在按钮上触发 */
		$('#btn-validate').modalTrigger({
			custom : '#imageVCode'
		});
		/* 使用触发器对象直接显示 */
		(new $.zui.ModalTrigger({
			custom : '#imageVCode'
		})).show();
	});

});
