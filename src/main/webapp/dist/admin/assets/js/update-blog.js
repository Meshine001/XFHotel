$(document).ready(function() {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {'id' : $('#id').val()},
		url : "./load_blog",// 请求的action路径
		error : function(error) {// 请求失败处理函数
			alert("加载失败！");
			console.log(error);
		},
		success : function(data) {
			var editor = new wangEditor('div_editor');
			editor.create();
			$('#submit').click(function() {
				$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					dataType : 'json',
					data : {
						'id' : data.id,
						'title' : $('#title').val(),
						'content' : editor.$txt.html()
					},
					url : "./update",// 请求的action路径
					error : function() {// 请求失败处理函数
						alert("发布失败！");
					},
					success : function(data) {
					}
				});
			});
			$('#title').val(data.title);
			$('#date').append(data.date);
			editor.$txt.html(data.content);
		}
	});
});