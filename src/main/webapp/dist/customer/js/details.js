$(document).ready(function() {

	'use strict';
	var $avatarView = $('.avatar-view');
	var $avatar = $(".avatar-wrapper").find('img')
	var $avatarModal = $('#avatar-modal');
	var $avatarPreview = $avatarModal.find('.avatar-preview');
	
	var $avatarForm = $('.avatar-form');
	var $avatarInput = $avatarForm.find('.avatar-input');
	var $avatarData = $avatarForm.find('.avatar-data');
	var $avatarSave = $avatarForm.find('.avatar-save');
	
	 var options = {
		        aspectRatio: 1 / 1,
		        preview: $avatarPreview,
		        ready: function (e) {
		          console.log(e.type);
		        },
		        cropstart: function (e) {
		          console.log(e.type, e.detail.action);
		        },
		        cropmove: function (e) {
		          console.log(e.type, e.detail.action);
		        },
		        cropend: function (e) {
		          console.log(e.type, e.detail.action);
		        },
		        crop: function (data) {

		          var json = [
	                  '{"x":' + data.x,
	                  '"y":' + data.y,
	                  '"height":' + data.height,
	                  '"width":' + data.width,
	                  '"rotate":' + data.rotate + '}'
	                ].join();
		          $avatarData.val(json);
		        },
		        zoom: function (e) {
		          console.log(e.type, e.detail.ratio);
		        }
		      };
	 
	 var cropper = $avatar.cropper(options);
	 
	$avatarView.tooltip({
		placement : 'bottom'
	});

	$avatarModal.modal({
		show : false
	});
	
	$avatarSave.click(function() {
		var url = $avatarForm.attr('action');
		var data = new FormData($avatarForm[0]);
		$.ajax(url,{
			headers: {'X-XSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')}, 
	        type: 'post',
	        data: data,
	        dataType: 'json',
	        processData: false,
	        contentType: false,
	        success:function(data){
	        	console.log(data);
	        },
	        error:function(data){
	        	alert('连接异常');
	        }
		});
	});

	// 点击头像触发
	$avatarView.click(function() {
		$avatarModal.modal('show');
		initPreview();
	});

	// 选择图片触发
	$avatarInput.change(function() {
		var files = $avatarInput.prop('files'), file;
		if (files.length > 0) {
			file = files[0];
			if (/^image\/\w+/.test(file.type)) {
				$avatar.attr('src',URL.createObjectURL(file));
				 $avatar.cropper('destroy');
				cropper = $avatar.cropper(options);
				initPreview();				
			}else{
				alert("请选择图片！");
			}
		}
	});
	
	
	/**
	 * 初始化预览
	 * 
	 * @returns
	 */
	function initPreview() {
		var url = $avatar.attr('src');
		$avatarPreview.empty().html('<img src="' + url + '">');
	}

});
