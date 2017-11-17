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


	
<!-- ZUI 标准版压缩后的 CSS 文件 -->
<link rel="stylesheet" href="<%=basePath%>/dist/zui/css/zui.min.css">
<!-- ZUI Javascript 依赖 jQuery -->
<script src="<%=basePath%>/dist/zui/lib/jquery/jquery.js"></script>
<!-- ZUI 标准版压缩后的 JavaScript 文件 -->
<script src="<%=basePath%>/dist/zui/js/zui.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/v1/css/base.css?r=3">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/public/css/layout.css">

<sitemesh:write property='my_header' />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body style="background-color: rgb(244, 244, 245);">
	<jsp:include page="home-header.jsp"></jsp:include>
	<sitemesh:write property='my_banner' />
	<div class="main">
		<sitemesh:write property='my_body' />
	</div>
	<jsp:include page="home-footer.jsp"></jsp:include>
	<c:if test="${c == null}">
		<jsp:include page="login-dialog.jsp"></jsp:include>
	</c:if>
	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.timers-1.2.js"></script>
	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.easing.1.3.js"></script>
	<script
		src="<%=basePath%>/dist/commons/gallery/js/jquery.galleryview-3.0-dev.js"></script>
	<sitemesh:write property='my_script' />
</body>
</html>