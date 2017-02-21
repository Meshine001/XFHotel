var editor = new wangEditor('div_editor');

editor.config.uploadImgUrl = './upload_blog_pic';
editor.config.uploadParams = {
};
editor.config.uploadHeaders = {
    'Accept' : 'text/x-json'
};
editor.config.hideLinkImg = true;
editor.config.uploadImgFileName = 'myFileName';
	
editor.create();
$('#submit').click(function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {
			'title' : $('#title').val(),
			'content' : editor.$txt.html(),
			'txt' : editor.$txt.formatText()
		},
		url : "./publish",//请求的action路径
		error : function() {//请求失败处理函数
			alert("发布失败！");
		},
		success : function(data) {
			window.location.href='./edit?id='+data;
		}
	});
});