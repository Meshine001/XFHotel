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
					<li><a href="#ios" data-toggle="tab">入住率分析</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active row" id="home">
						<div class="houselist col-md-2 pd">

						</div>
						<div class="statusDate col-md-5 pd">
							<!--begin  -->
								<div class="choosecal">
									 <div class="calender">
									  <div class="selectmouth">
									  <p style="text-align:right" class="lastmonth"><</p>
									  <p><input type="text" class="selectdate" value="2014年2月" readonly=readonly /></p>
									  <p class="nextmonth">></p>
									  </div>
									  <table class="data_table" cellspacing="0px">
									  <thead>
									   <tr>
									   <td>日</td><td>一</td><td>二</td><td>三</td><td>四</td><td>五</td><td>六</td>
									   </tr>
									  </thead>
									  <tbody>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   <tr>
									   <td>1</td><td></td><td></td><td></td><td></td><td></td><td></td>
									   </tr>
									   </tbody>
									  </table>
									 </div>
								</div>
							
							<!--end  -->
						</div>
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
 		//	$("#fl_hslist tr th:first-child,tr td:first-child").css({'min-width':'200px','text-align':'left'}) 
		})
	</script>
	</my_script>
</body>
</html>