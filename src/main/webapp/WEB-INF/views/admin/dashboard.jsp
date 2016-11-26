<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>控制面板</title>
</head>
<body>
	管理员：${admin.username}
	<form action="../file/upload" method="post" enctype="multipart/form-data">
		选择图片：<input type = "file" name = "file">
		<input type="submit" value="上传">
	</form>
</body>
</html>