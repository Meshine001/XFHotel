$(document).ready(function() {

	$("#education-select").change(function() {
		console.log($(this).children('option:selected').val());
		$("#education").val($(this).children('option:selected').val());// 这就是selected的值
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

	var $uploadAvatar = $("#uploadAvatar");

	// 初始化裁剪
	var $image = $('#image');
	var $inputImage = $('#inputImage');
	var options = {
		aspectRatio : 1 / 1,
		preview : '.img-preview',
		crop : function(e) {

		}
	};
	$image.cropper(options);

	var URL = window.URL || window.webkitURL;
	var blobURL;
	// 选取图片
	$inputImage.change(function() {
		var files = this.files;
		var file;
		if (files && files.length) {
			file = files[0];
			if (/^image\/\w+$/.test(file.type)) {
				blobURL = URL.createObjectURL(file);
				$image.one('built.cropper', function() {
					// Revoke when load complete
					URL.revokeObjectURL(blobURL);
				}).cropper('reset').cropper('replace', blobURL);
				$inputImage.val('');
			} else {
				window.alert('Please choose an image file.');
			}
		}

	});

	// 上传头像
	$uploadAvatar.click(function() {
		$image.cropper('getCroppedCanvas', {
			width : 120,
			height : 120
		}).toBlob(function(blob) {
			var formData = new FormData($("#avatar-form"));
			formData.append('file', blob);
			 $.ajax('../file/upload', {
			 method : "POST",
			 data : formData,
			 processData : false,
			 contentType : false,
			 success : function(data) {
			 if (data.statusCode == 0) {
			 alert(data.content);
			 } else {
			 $("#avatar").val(data.content[0]);
			 $("#avatarUrl").attr("src", "../images/" + data.content);
			 $('#myModal').modal('toggle');
			 }
			 },
			 error : function(data) {
			 console.log(data);
			 // alert("连接异常！");
			 }
			 });
		});
	});

});


