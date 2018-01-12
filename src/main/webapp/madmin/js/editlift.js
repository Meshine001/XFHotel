$(document).ready(function(){
	var _id = decodeURIComponent(fnBase.request("id"));
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {'id' : _id},
		url : baseUrl+"/admin/blog/load_blog",
		error : function(error) {
			fnBase.myalert("加载失败！");
		},
		success : function(data) {
			var editor = new wangEditor('div_editor');
			
			editor.config.uploadImgUrl = baseUrl+"/admin/blog/upload_blog_pic";
			editor.config.uploadParams = {
			};
			editor.config.uploadHeaders = {
			    'Accept' : 'text/x-json'
			};
			editor.config.hideLinkImg = true;
			editor.config.uploadImgFileName = 'myFileName';
			
			editor.create();
			$('#submit').click(function() {
				fnBase.myalert('发布成功')
				setTimeout(function(){
					window.location.href="lift.html"
				},1550)
				$.ajax({
					type : 'POST',
					dataType : 'json',
					data : {
						'id' : data.id,
						'title' : $('#title').val(),
						'content' : editor.$txt.html(),
						'txt' : editor.$txt.formatText()
					},
					url : baseUrl+"/admin/blog/update",
					error : function(e) {
						fnBase.myalert("发布失败！");
					},
					success : function(data) {
					}
				});
			});
			$('#title').val(data.title);
			$('#date i').html(data.date);
			editor.$txt.html(data.content);
		}
	});
	
})