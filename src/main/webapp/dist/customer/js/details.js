$(document).ready(function() {
	
	$("#education-select").change(function() {
		console.log($(this).children('option:selected').val() );
		$("#education").val($(this).children('option:selected').val());//这就是selected的值
	});
	$("#declaration-area").change(function() {
		$("#declaration").val($("#declaration-area").val());
	});
	$("#hobby-area").change(function() {
		$("#hobby").val($("#hobby-area").val());
	});
	
	/**
	 * 修改个人信息
	 */
	$("#modify").click(function() {
		$.ajax({
			cache : true,
			type : "POST",
			url : "./modify",
			data : $('#details-form').serialize(),
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				alert(data.content);
				if (data.statusCode == 0) {
					
				} else {
					location.reload()
				}

			}
		});
	});
	/**
	 * 上传头像
	 */
	$("#uploadAvatar").click(function() {
		$.ajaxFileUpload({
			url : "../file/upload", // submit to UploadFileServlet
			dataType: 'json',//返回数据的类型  
			secureuri : false,
			fileElementId : 'file',
			success : function(data, status) {
				console.log(data);
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					$("#avatar").val(data.content);
					$("#avatarUrl").attr("src", "../images/" + data.content);
					$('#myModal').modal('toggle');
				}
			},
			error : function(data, status, e) {
				alert("连接异常！");
			}
		});
	});


	/**
	 * 预览头像
	 */
	$("#file").change(function() {
		// 判断是否支持FileReader
		if (window.FileReader) {
			var reader = new FileReader();
		} else {
			alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
		}
		console.log($(this));
		// 获取文件
		var file = $(this).prop("files")[0];
		var imageType = /^image\//;
		// 是否是图片
		if (!imageType.test(file.type)) {
			alert("请选择图片！");
			return;
		}
		// 读取完成
		reader.onload = function(e) {
			// 获取图片dom
			var img = document.getElementById("preAvatar");
			// 图片路径设置为读取的图片
			img.src = e.target.result;
		};
		reader.readAsDataURL(file);
	});

})
