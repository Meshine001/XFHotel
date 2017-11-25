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
					<li><a href="#ios" data-toggle="tab">入住率汇总</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade" id="ios" style="overflow:auto;width:100%;">
						<div id="clear"></div>
						<div class="pd" id="rzoom">
							
						</div>
					</div>
				</div>
				<ul id="houseTab" class="nav nav-tabs">
					<li><a href="#house" data-toggle="tab">收入统计
						<select id="yearDate">
							<option>2017</option>
						</select>
<!-- 						&nbsp;&nbsp;位置
						<select id="address">
							<option aid="5">全部</option>
							<option aid="0">城东</option>
							<option aid="1">城南</option>
							<option aid="2">城西</option>
							<option aid="3">城北</option>
							<option aid="4">城内</option>
						</select> -->
					</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane fade" id="house">
						<div class="pd" id="everydata" style="overflow:auto;width:100%">
							<ul class="houseListcon">
							</ul>
							<div id="post_detail"></div>
							<div style="clear:both"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</my_body>
	<my_script>
 	<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/houseStatus.js"></script>
 	<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/echarts.min.js"></script>
 	<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/statistics.js"></script>   
	<script>
		$(document).ready(function(){
			$(".post_detail").css( 'width', $(".post_detail").width() );
			$(function () {
				$('#myTab li:eq(0) a').tab('show');
				$('#houseTab li:eq(0) a').tab('show');
			});
			$("#fl_hslist tr th,tr td").css({'min-width':'40px','text-align':'center'}) 
			$("#h-name tr").find('td').eq(0).css('border-right','0px')
			


			
			
			
			
			
			
			
			
			
			
			
		})
	</script>
	</my_script>
</body>
</html>