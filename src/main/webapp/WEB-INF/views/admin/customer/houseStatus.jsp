<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>订单管理-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<my_body>

	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<ul id="myTab" class="nav nav-tabs">
					<li class="active">
						<a href="#home" data-toggle="tab">房态设置</a>
					</li>
					<!-- <li><a href="#ios" data-toggle="tab">入住率分析</a></li> -->
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active row" id="home">
							<!--begin  -->
									<div class="warp">
										<table class="ul table table-bordered">
											<thead>
												<tr>
													<th>名称</th>
												</tr>
											</thead>
											<tbody id="h-name" style="text-align:left">
													
											</tbody>
										</table>
										<table class="info table table-bordered">
											<thead>
												<tr class="ymd">
												</tr>
											</thead>
											<tbody id="h-info">
												<tr>
												</tr>
											</tbody>
										</table>
										<div class="clear"></div>
									</div>
							<!--end  -->	
					</div>
					<div class="tab-pane fade" id="ios">
						<div class="pd">入住分析</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</my_body>
	<my_script>
 	<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/houseStatus.js"></script>  
	<script>
		$(document).ready(function(){
			$(function () {
				$('#myTab li:eq(0) a').tab('show');
			});
			$("#fl_hslist tr th,tr td").css({'min-width':'40px','text-align':'center'}) 
			$("#h-name td").css({'text-align':'left !important'}) 
			$("#h-name tr").find('td').eq(0).css('border-right','0px')
		})
	</script>
	</my_script>
</body>
</html>