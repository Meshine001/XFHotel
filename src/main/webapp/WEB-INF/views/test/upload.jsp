<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>智能公寓平台-西安租房_西安合租</title>

<!-- Bootstrap core CSS -->
<link href="<%=basePath%>/dist/commons/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="all">

<script src="<%=basePath%>/dist/commons/jquery/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>/dist/commons/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="<%=basePath%>/dist/commons/jquery-file-upload/css/jquery.fileupload.css">
<script
	src="<%=basePath%>/dist/commons/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script
	src="<%=basePath%>/dist/commons/jquery-file-upload/js/jquery.iframe-transport.js"></script>
<script
	src="<%=basePath%>/dist/commons/jquery-file-upload/js/jquery.fileupload.js"></script>
<!-- we code these -->
<link href="<%=basePath%>/dist/test/css/dropzone.css" type="text/css"
	rel="stylesheet" />
<script src="<%=basePath%>/dist/test/js/myuploadfunction.js"></script>
</head>
<body>
	<h1>Spring MVC - jQuery File Upload</h1>
	<div style="width: 500px; padding: 20px">
		<span class="btn btn-success fileinput-button"> <i
			class="glyphicon glyphicon-plus"></i> <span>Add files...</span> <!-- The file input field used as target for the file upload widget -->
			<input id="fileupload" type="file" name="files[]" multiple="multiple">
		</span>
		<div id="dropzone">Drop files here</div>

		<div id="progress">
			<div style="width: 0%;"></div>
		</div>

		<table id="uploaded-files">
			<tr>
				<th>File Name</th>
				<th>File Size</th>
				<th>File Type</th>
				<th></th>
			</tr>
		</table>

	</div>
</body>
</html>