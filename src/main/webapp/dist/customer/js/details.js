$(document).ready(function() {
	//修改详细信息
	$('#modify').click(function(){
		$('#education').val($('#education-select').val());
		console.log($('#education').val());
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
				if (data.statusCode == 0) {
					alert(data.content);
				} else {
					alert("修改成功");
					location.reload();
				}
			}
		});
	});
	
	
	
	
	//图片裁剪
	'use strict';
	var $avatarView = $('.avatar-view');
	var $avatarImg = $('.avatar-view img');
	
	var $avatar = $(".avatar-wrapper").find('img')
	var $avatarModal = $('#avatar-modal');
	var $avatarPreview = $avatarModal.find('.avatar-preview');
	
	var $formAvatar = $('#avatar');
	
	var $avatarForm = $('.avatar-form');
	var $avatarInput = $avatarForm.find('.avatar-input');
	var $avatarData = $avatarForm.find('.avatar-data');
	var $avatarSave = $avatarForm.find('.avatar-save');
	
	var options = {
		        aspectRatio: 1 / 1,
		        preview: $avatarPreview,
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
	        	if(data.statusCode == 1){
	        		$avatarImg.attr('src','../images/'+data.content);
	        		$formAvatar.val(data.content);
	        		$avatarModal.modal('toggle');
	        	}
	        	else{
	        		alert(data.content);
	        	}
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
