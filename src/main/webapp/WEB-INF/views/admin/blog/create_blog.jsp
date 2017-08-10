<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<my_header>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/blog/css/wangEditor.min.css">
<style type="text/css">
#div_editor {
	margin: 10px;
	width: 100%;
	height: 500px;
}
</style>
</my_header>
</head>
<body>
	<my_body>
	<div class="row">
		<div class="input-group">
			<input type="hidden" id="id" value="${id}"> <input class="form-control"
				style="width: 100%; font-size: 22px;background:#fff" type="text" id="title"
				value="请输入标题">
		</div>
	
		<div id="div_editor">
			<p>请输入内容</p>
		</div>
		<br/>
		<button type="button" class="btn btn-success" id="submit">发布</button>
		
	</div>
	<script src="<%=basePath%>/dist/blog/js/lib/jquery-1.10.2.min.js"
		type="text/javascript"></script> <script type="text/javascript"
		src="<%=basePath%>/dist/blog/js/wangEditor.min.js"></script> </my_body>

	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/admin/assets/js/add-blog.js"></script> </my_script>
</body>