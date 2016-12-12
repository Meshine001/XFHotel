var curWwwPath = window.document.location.href;
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var basePath = curWwwPath.substring(0, pos) + "/hotel";

$(document).ready(function() {

	$("#back-home").click(function() {
		window.location.href = basePath + "/";
	});

	$("#reg").click(function() {
		window.location.href = basePath + "/reg";
	});

	$("#login").click(function() {
		window.location.href = basePath + "/login";
	});

	/**
	 * 验证手机
	 */
	$("#tel").blur(function() {
		console.log("asds");
		$("#tel-v").text("");
		var tel = $("#tel").val();
		var reg = /^1[3|4|5|7|8][0-9]{9}$/; // 验证规则
		if (!reg.test(tel)) {
			$("#tel-v").text("手机格式不正确");
		} else {
			$.get('customer/check_tel?tel=' + tel, function(result) {
				if (result == true) {
					$("#tel-v").text("手机号已使用");
				} else {
					$("#tel-v").text("手机号可用");
					$("#reg-submit").removeAttr("disabled");
				}
			});
		}

	});

	
	$("#volidate-code").focus(function() {

	});

});
