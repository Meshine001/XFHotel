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
        	margin:10px;
            width: 100%;
            height: 500px;
        }
    </style>
</my_header>
</head>
<body>
	<my_body>
		<input type="text" id="title" value="请输入标题">
		<br><br>
		<div id="div_editor">
			<p>请输入内容</p>
		</div>
		<button type="button" id="submit">发布</button>
		<script src="<%=basePath%>/dist/blog/js/lib/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=basePath%>/dist/blog/js/wangEditor.min.js"></script> 
		<script type="text/javascript">
				var editor = new wangEditor('div_editor');
				editor.create();
				$('#submit').click(function(){
					alert('tttttttttttt');
					$.ajax({
						async : false,
						cache : false,
						type : 'POST',
						dataType : 'json',
						data : {'title':$('#title').val(),'content':editor.$txt.html()},
						url : "./publish",//请求的action路径
						error : function() {//请求失败处理函数
							alert("发布失败！");
						},
						success : function(data) {
							console.log(data);
						}
					});
				});
		</script> 
	</my_body>
</body>