<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>title-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<my_header> <script
	src="<%=basePath%>/dist/commons/raty/jquery.raty.js"></script>
<link
	href="<%=basePath%>/dist/commons/jquery-filer/css/jquery.filer.css"
	rel="stylesheet">
<link
	href="<%=basePath%>/dist/commons/jquery-filer/css/themes/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<script
	src="<%=basePath%>/dist/commons/jquery-filer/js/jquery.filer.min.js"
	type="text/javascript"></script> </my_header>
</head>
<body>
	<my_body>
	<div class="row">
		<div class="col-md-12">
			<h3>用户评价</h3>
			<ul class="media-list">
				<li class="media">
					<div class="media-body">
						<div class="media">
							<a class="pull-left"> <img class="media-object img-thumbnail"
								width="140px" src="<%=basePath%>/images/${apartment.pic2[0]}">
							</a>
							<div class="media-body">
								${order.description}<br> <small class="text-muted">Alex
									Deo | 23rd June at 5:00pm</small>
								<hr>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<span>整体评价：</span>
				<div id="stars" class="block"></div>
				<p id="starHint"></p>
				<script type="text/javascript">
					$('#stars').raty({
						hints : [ '一般', '还行', '好', '很好', '非常好' ],
						score : 3,
						target : '#starHint',
						targetKeep : true,
						click : function(score, evt) {

							//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);

						}
					});
				</script>
			</div>
			<div class="row">入住时间:${order.startTime}</div>
			<div class="row">
				入住感受:
				<textarea rows="5" cols="20"></textarea>
			</div>
			<div class="row">
				<form action="../../file/multiUpload" method="post"
					enctype="multipart/form-data">
					<input id="filer-input" type="file" name="files"
						multiple="multiple">
				</form>
			</div>
			<div class="row">
				<button>确认提交</button>
			</div>
		</div>
	</div>
	</my_body>
	<my_script> <script
		src="<%=basePath%>/dist/commons/jquery-filer/js/custom.js"
		type="text/javascript"></script> </my_script>
</body>
</html>