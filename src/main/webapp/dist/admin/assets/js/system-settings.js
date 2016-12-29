$(document).ready(function() {

	
	
	
	//==========================================>
	//删除设施
	$('.facility-delete').click(function() {
		var url = $(this).attr('id');
		$.ajax({
			cache : true,
			type : "post",
			url : url,
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					alert(data.content);
					window.location.reload();
				}

			}
		});
	});

	// ==================================================>
	// 添加设施
	$('.facility-submit').click(function() {
		var url = $('#facility-form').attr('action');
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : $('#facility-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {

				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					window.location.reload();
				}

			}
		});
	});
	// =========================================>
	// 删除特色
	$('.features-delete').click(function() {
		var url = $(this).attr('id');
		$.ajax({
			cache : true,
			type : "post",
			url : url,
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					alert(data.content);
					window.location.reload();
				}

			}
		});
	});

	// ==================================================>
	// 添加特色
	$('.features-submit').click(function() {
		var url = $('#features-form').attr('action');
		$.ajax({
			cache : true,
			type : "POST",
			url : url,
			data : $('#features-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {

				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					window.location.reload();
				}

			}
		});
	});
});