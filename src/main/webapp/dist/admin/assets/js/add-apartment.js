$(document).ready(function() {

	var $layoutImageInput = $('#layout-image-input');
	$layoutImageInput.change(function() {
		var files = $layoutImageInput.prop('files'), file;
		if (files.length > 0) {
			file = files[0];
			if (/^image\/\w+/.test(file.type)) {
				$('#layout-image').attr('src', URL.createObjectURL(file));
				$imageInput.val('');
			} else {
				alert("请选择图片！");
			}
		}
	});

});
