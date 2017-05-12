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
</head>
<body>
	<my_body>

	<div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="card-header">编辑房间</div>
				<div class="card-body">
					<form
						action="<%=request.getContextPath()%>/admin/apartment/room/update/${room.id}"
						method="POST" class="form form-horizontal">
						<input type="hidden" id="ltype" name="ltype" value="${room.ltype}">
						<div class="form-group">
							<label class="col-md-3 control-label">房间名</label>

							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="description" value="${room.description}">
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">类型</label>
							<div class="col-md-9">
								<select name="type" id="type">
									<option value="-1">请选择</option>
									<option value="主卧">主卧</option>
									<option value="次卧">次卧</option>
								</select>
							</div>
							<script type="text/javascript">
									$('#type option').each(function(){
										var op = $(this).val();
										if(op == '${room.type}'){
											$(this).attr('selected','selected');
										}
									});
									
								</script>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="direction" id="direction">
									<option value="-1">请选择</option>
									<option value="东">东</option>
									<option value="南">南</option>
									<option value="西">西</option>
									<option value="北">北</option>
								</select>
							</div>
							<script type="text/javascript">
									$('#direction option').each(function(){
										if($(this).val() == '${room.direction}'){
											$(this).attr('selected','selected');
										}
									});
									
							</script>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">面积</label>

							<div class="col-md-9">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="square" value="${room.square}"><span
										class="input-group-addon">m<sup>2</sup></span>
								</div>
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设施</label>
							<div class="col-md-9" id="facilities">
								<c:forEach items="${l_facility}" var="facility" varStatus="p">
									<div class="checkbox checkbox-inline">
										<input type="checkbox" id="fa-${facility.id}" name="facility"
											value="${facility.id}"> <label
											for="fa-${facility.id}">${facility.description }</label>
									</div>
									<c:forEach items="${room.facilities}" var="f">
										<c:if test="${f == facility.id}">
											<script type="text/javascript">
												$('#fa-'+${facility.id}).attr('checked','checked');
											</script>
										</c:if>
									</c:forEach>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">图片</label>
							<div class="col-md-9">
								<table>
									<tbody>
										<tr>

											<td>
												<ul id="img-list">
													<c:forEach items="${room.pics}" var="pic">
														<li><img alt="" src="../../images/${pic}"
															class="img-thumbnail" width="120px" height="80px">
														</li>
													</c:forEach>
												</ul>

											</td>
											<td><button type="button" class="btn btn-primary btn-lg"
													data-toggle="modal" data-target="#edit-img-modal">编辑</button></td>

										</tr>
									</tbody>
								</table>
							</div>

						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">价格</label>
							<div id="lease-price" class="col-md-9">
								<div class="input-group day">
									<span class="input-group-addon">天</span> <input type="text"
										name="prices" class="price-day" value="${room.prices[0]}"> <span
										class="input-group-addon">元</span>
								</div>
								<!--  
								<div class="input-group week">
									<span class="input-group-addon">周</span> <input type="text"
										name="prices" class="price-week" value="${room.prices[1]}"> <span
										class="input-group-addon">元</span>
								</div>
								<div class="input-group month">
									<span class="input-group-addon">月</span> <input type="text"
										name="prices" class="price-month" value="${room.prices[2]}"> <span
										class="input-group-addon">元</span>
								</div>
								<div class="input-group year">
									<span class="input-group-addon">年</span> <input type="text"
										name="prices" class="price-year" value="${room.prices[3]}"> <span
										class="input-group-addon">元</span>
								</div>
								-->
							</div>
						</div>

						<br>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" class="btn btn-primary">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="edit-img-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑图片</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<form action="./room/pic/update/${room.id}" id="update-img-form">
								<c:forEach items="${room.pics}" var="pic" varStatus="p">
									<input type="hidden" name="pics" value="${pic}" id="pic-${p.index}">
								</c:forEach>
							</form>
							<ul>
								<c:forEach items="${room.pics}" var="pic" varStatus="p">
									<li> <img alt="" src="../../images/${pic}" 
										class="img-thumbnail edit-image-preview" width="120px" height="80px" id="img-${p.index}"><label  class="edit-image-label" id="${p.index}" for="img-input">更改</label><a>删除</a></li>
								</c:forEach>
							</ul>
							<script type="text/javascript">
								var picPosition;
								$('.edit-image-label').click(function(){
									picPosition = $(this).attr('id');
								});
						
							</script>
							<form action="<%=basePath%>/file/upload" id="upload-img-form"
								method="post">
								<input type="file" id="img-input" name="file">
								 <br>
								<img alt="" src="" class="img-responsive" id="img-input-preview">
							</form>
						</div>
					</div>
					<script type="text/javascript">
							$('#img-input').change(function(){
								var url = $('#upload-img-form').attr('action');
								var data = new FormData($('#upload-img-form')[0]);
								$.ajax(url,{
									headers: {'X-XSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')}, 
							        type: 'post',
							        data: data,
							        dataType: 'json',
							        processData: false,
							        contentType: false,
							        success:function(data){
							        	if(data.statusCode == 1){
							        		$('#img-'+picPosition).attr('src','../../images/'+data.content);
							        		$('#pic-'+picPosition).val(data.content);
							        	}
							        	else{
							        		alert(data.content);
							        	}
							        },
							        error:function(data){
							        	alert('连接异常');
							        }
								});
							});
						</script>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="edit-img-submit">保存</button>
					<script type="text/javascript">
							$('#edit-img-submit').click(function(){
								var url = $('#update-img-form').attr('action');
								$.ajax({
									cache : true,
									type : "POST",
									url : url,
									data : $('#update-img-form').serialize(),
									async : false,
									error : function(request) {
										alert("连接异常！");
									},
									success : function(data) {
										alert(data.content);
										if (data.statusCode == 0) {

										} else {
											window.location.reload();
										}

									}
								});
							})
						</script>
				</div>

			</div>
		</div>
	</div>
	</my_body>
</body>
</html>