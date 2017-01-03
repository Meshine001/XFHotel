$(document).ready(function() {

	//获取当前网址，如： http://localhost:8080/Tmall/index.jsp 
	var curWwwPath=window.document.location.href; 

	//获取主机地址之后的目录如：/Tmall/index.jsp 
	var pathName=window.document.location.pathname; 
	var pos=curWwwPath.indexOf(pathName); 

	//获取主机地址，如： http://localhost:8080 
	var localhostPaht=curWwwPath.substring(0,pos); 

	//获取带"/"的项目名，如：/Tmall 
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	
	// 上传图片
	// ======================================================>
	var uploadForm = $('#upload-image-form');
	var uploadUrl = localhostPaht+'/'+projectName+'/file/upload';

	function uploadFile(data) {
		var result;
		$.ajax(uploadUrl, {
			headers : {
				'X-XSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
			},
			type : 'post',
			data : data,
			async : false,
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(data) {
				console.log(data);
				if (data.statusCode == 1) {
					result = data.content;
				} else {
					alert(data.content);
					result = false;
				}
			},
			error : function(data) {
				alert('连接异常');
				result = false;
			}
		});
		return result;
	}

	var uploadId;
	var uploadPre;
	$('.upload-image').click(function() {
		var uploadType = $(this).attr('id');
		uploadId = uploadType + '-input';
		uploadPre = uploadType + '-img';
		$('#upload-image-input').click();
	});

	$('#upload-image-input').change(function() {
		var data = new FormData(uploadForm[0]);
		var imgUrl = uploadFile(data);
		if (imgUrl != false) {
			$('#' + uploadId).val(imgUrl);
			$('#' + uploadPre).attr('src', localhostPaht+'/'+projectName+'/images/' + imgUrl);
		}
	});

});
