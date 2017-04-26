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
	<h1 class="e_tit">用户评价</h1>
	<div class="summary">
		订单号：${order.id}
		<ul class="media-list">
			<li class="media">
				<div class="media-body">
					<div class="media">
						<a class="pull-left"> <img class="media-object img-thumbnail"
							width="140px" height="40px"
							src="<%=basePath%>/images/${apartment.pic2[0]}"
							style="height: 40px; width: 60px">
						</a>
						<div class="media-body">
							${order.description}<br> <small class="text-muted">${order.startTime}至${order.endTime}</small>
							<hr>
						</div>
					</div>
				</div>
			</li>
		</ul>

	</div>
	<form action="<%=basePath%>/order/comment/post" id="comment-form"
		method="post">
		<input type="hidden" name = "orderId" value="${order.id}"/>
		<input type="hidden" name="roomId" value="${room.id}">
		<!-- 默认三星 -->
		<input type="hidden" name="c_score" value="3" id="scoreWeiSheng">
		<input type="hidden" name="c_score" value="3" id="scoreMiaoSu">
		<input type="hidden" name="c_score" value="3" id="scoreJiaoTong">
		<input type="hidden" name="c_score" value="3" id="scoreAnQuan">
		<input type="hidden" name="c_score" value="3" id="scoreXinJiaBi">
		<input type="hidden" name="from" value="${order.cusId}" id="from">
		<input type="hidden" name="to" value="0">
		<div class="stars">
			<span>卫生：</span>
			<div id="starsWeiSheng" class="block"></div>
			<p id="starHintWeiSheng"></p>
			<script type="text/javascript">
				$('#starsWeiSheng').raty({
					hints : [ '一般', '还行', '好', '很好', '非常好' ],
					score : 3,
					target : '#starHintWeiSheng',
					targetKeep : true,
					click : function(score, evt) {
						$('#scoreWeiSheng').val(score);
						//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
					}
				});
			</script>
		</div>
		<div class="stars">
			<span>描述：</span>
			<div id="starsMiaoSu" class="block"></div>
			<p id="starHintMiaoSu"></p>
			<script type="text/javascript">
				$('#starsMiaoSu').raty({
					hints : [ '一般', '还行', '好', '很好', '非常好' ],
					score : 3,
					target : '#starHintMiaoSu',
					targetKeep : true,
					click : function(score, evt) {
						$('#scoreMiaoSu').val(score);
						//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
					}
				});
			</script>
		</div>
		<div class="stars">
			<span>交通：</span>
			<div id="starsJiaoTong" class="block"></div>
			<p id="starHintJiaoTong"></p>
			<script type="text/javascript">
				$('#starsJiaoTong').raty({
					hints : [ '一般', '还行', '好', '很好', '非常好' ],
					score : 3,
					target : '#starHintJiaoTong',
					targetKeep : true,
					click : function(score, evt) {
						$('#scoreJiaoTong').val(score);
						//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
					}
				});
			</script>
		</div>
		<div class="stars">
			<span>安全：</span>
			<div id="starsAnQuan" class="block"></div>
			<p id="starHintAnQuan"></p>
			<script type="text/javascript">
				$('#starsAnQuan').raty({
					hints : [ '一般', '还行', '好', '很好', '非常好' ],
					score : 3,
					target : '#starHintAnQuan',
					targetKeep : true,
					click : function(score, evt) {
						$('#scoreAnQuan').val(score);
						//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
					}
				});
			</script>
		</div>
		<div class="stars">
			<span>性价比：</span>
			<div id="starsXinJiaBi" class="block"></div>
			<p id="starHintXinJiaBi"></p>
			<script type="text/javascript">
				$('#starsXinJiaBi').raty({
					hints : [ '一般', '还行', '好', '很好', '非常好' ],
					score : 3,
					target : '#starHintXinJiaBi',
					targetKeep : true,
					click : function(score, evt) {
						$('#scoreXinJiaBi').val(score);
						//alert('ID: ' + $(this).attr('id') + "\nscore: " + score + "\nevent: " + evt);
					}
				});
			</script>
		</div>
		<div>入住时间:${order.startTime}</div>
		<div>
			入住感受:
			<textarea rows="5" cols="20" name="feel"></textarea>
		</div>
	</form>


	<div class="details">
		<span class="btn btn-success fileinput-button"> <i
			class="glyphicon glyphicon-plus"></i> <span>添加图片</span> <!-- The file input field used as target for the file upload widget -->
			<input id="fileupload" type="file" name="files[]" multiple="multiple"
			data-url='<%=basePath%>/file/uploadFile'>
		</span>
		<div id="dropzone" style="width: 100px; height: 100px; border: thick;">拖动图片到这</div>

		<div id="progress">
			<div style="width: 0%;"></div>
		</div>

		<table>
			<tr id="uploaded-files"></tr>
		</table>
		<script type="text/javascript">
			var url = $('#fileupload').attr('data-url');
			$('#fileupload')
					.fileupload(
							{
								url : url,
								dataType : 'json',
								done : function(e, data) {
									$
											.each(
													data.result,
													function(index, file) {
														$("#uploaded-files")
																.append(
																		$(
																				'<td/>')
																				.html(
																						"<img class='img-thumbnail' width='60px' src='../../images/"+file.fileName+"'/>"));
														$('#comment-form')
																.append(
																		$(
																				'<input/>')
																				.attr(
																						'type',
																						'hidden')
																				.attr(
																						'name',
																						'pics')
																				.attr(
																						'value',
																						file.fileName));
													});
								},
								progressall : function(e, data) {
									var progress = parseInt(data.loaded
											/ data.total * 100, 10);
									$('#progress .bar').css('width',
											progress + '%');
								},

								dropZone : $('#dropzone')
							});
		</script>
	</div>
	<div class="row">
		<button type="button" class="btn btn-primary" id="comment-submit">确认提交</button>
	</div>
	<script type="text/javascript">
		$('#comment-submit').click(function() {
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
	</script> </my_body>
	<my_script> </my_script>
</body>
</html>