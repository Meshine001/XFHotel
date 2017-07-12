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

</head>
<body>
	<my_body>
	<div class="row">
		<!-- 
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>房屋状态</h3>
					</div>
				</div>
				<div class="card-body" id="statusDate" style="position:relative;height:356px;"> 
					 <div id="time" style="display:none"></div>
					
				</div>
			</div>
		</div>
		 -->
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>房屋状态设置</h3>
					</div>
				</div>
				<div class="card-body">
					
						<div class="form-group">
							<label class="col-md-3 control-label">选择日期</label>
							<div class="col-md-3 status-time">
								<input type="date" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">状态选择</label>
							<div class="col-md-3 status-house">
								<a href="javascript:;" stag="0">无房</a>
								<a href="javascript:;" stag="1">有房</a>
							</div>
						</div>
						<div class="form-group">
								<div class="col-md-9 ">
									<button type="submit" class="btn btn-primary">确定修改</button>
								</div>
						</div>
						<div class="form-group">	
						</div>
					
				</div>
			</div>
		</div>
		
		
		
	</my_body>
	
	<my_script>
	    <!-- 
	     <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/jquery-1.7.1.min.js"></script>  
			 <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/status.js"></script> 
		 -->
	
		 <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/date.js"></script> 


		 <script>
		 	$('#time').hotelDate();
			$('#time').click();
  		 </script>
	</my_script>
	
</body>
</html>