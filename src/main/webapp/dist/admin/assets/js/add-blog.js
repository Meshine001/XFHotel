var editor = new wangEditor('div_editor');
/**
 * 获取根目录
 * @returns {string}
 */
function getRootPath() {
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(localhostPath + projectName);
}

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