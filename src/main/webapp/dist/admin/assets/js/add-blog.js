var editor = new wangEditor('div_editor');
editor.create();
$('#submit').click(function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {
			'title' : $('#title').val(),
			'content' : editor.$txt.html()
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