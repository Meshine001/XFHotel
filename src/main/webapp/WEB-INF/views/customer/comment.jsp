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
	src="<%=basePath%>/dist/commons/jquery/jquery-1.9.1.min.js"></script> <script
	src="<%=basePath%>/dist/commons/raty/jquery.raty.js"></script>
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
</my_header>
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
			<form action="<%=basePath%>/order/comment/post" id="comment-form"
				method="post">
				<input type="hidden" name="roomId" value="${room.id}"> 
					<!-- 默认三星 -->
					<input
						type="hidden" name="c_score" value="3" id="score"> <input
						type="hidden" name="from" value="${order.cusId}" id="from"> <input
						type="hidden" name="to" value="0"> 
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
								$('#score').val(score);
								//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
							}
						});
					</script>
				</div>
				<div class="row">入住时间:${order.startTime}</div>
				<div class="row">
					入住感受:
					<textarea rows="5" cols="20" name="feel"></textarea>
				</div>

			</form>
			<div class="row">
				<span class="btn btn-success fileinput-button"> <i
					class="glyphicon glyphicon-plus"></i> <span>添加图片</span> <!-- The file input field used as target for the file upload widget -->
					<input id="fileupload" type="file" name="files[]"
					multiple="multiple" data-url='<%=basePath%>/file/uploadFile'>
				</span>
				<div id="dropzone"
					style="width: 100px; height: 100px; border: thick;">拖动图片到这</div>

				<div id="progress">
					<div style="width: 0%;"></div>
				</div>

				<table>
					<tr id="uploaded-files"></tr>
				</table>
				<script type="text/javascript">
				var url = $('#fileupload').attr('data-url');
				$('#fileupload').fileupload({
			    	url:url,
			    	dataType: 'json',
			        done: function (e, data) {
			            $.each(data.result, function (index, file) {
			                $("#uploaded-files").append($('<td/>').html("<img class='img-thumbnail' width='60px' src='../../images/"+file.fileName+"'/>"));
			                $('#comment-form').append($('<input/>').attr('type','hidden').attr('name','pics').attr('value',file.fileName));
			            }); 
			            },
				  progressall: function (e, data) {
				        var progress = parseInt(data.loaded / data.total * 100, 10);
				        $('#progress .bar').css(
				            'width',
				            progress + '%'
				        );
			   		},

					dropZone: $('#dropzone')
			    });	
				</script>
			</div>
			<div class="row">
				<button type="button" class="btn btn-primary" id="comment-submit">确认提交</button>
			</div>
			<script type="text/javascript">
				$('#comment-submit').click(function(){
					$.ajax({
						cache : true,
						type : "POST",
						url : $('#comment-form').attr('action'),
						data : $('#comment-form').serialize(),
						async : false,
						error : function(request) {
							alert("连接异常！");
						},
						success : function(data) {
							alert(data.content);
							if (data.statusCode == 0) {

							} else {
								history.go(-1);
							}

						}
					});
				});
			</script>
		</div>
	</div>
	</my_body>
	<my_script> </my_script>
</body>
</html>