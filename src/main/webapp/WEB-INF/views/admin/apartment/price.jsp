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
			<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>价格日历</h3>
					</div>
				</div>
				<div class="card-body" id="statusDate" style="position:relative">
				<!-- 
					<form action="" class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">日常价</label>
							<div class="col-md-3">
								<input type="text" class="form-control" placeholder=""
									name="oldPrice" value="${apartment.basic_info.jia_ge}">
							</div>
						</div>
						<c:forEach items="${spPrices}" var="sp">
							<div class="form-group">
								<label class="col-md-3 control-label">${sp.date}</label>
								<div class="col-md-3">
									<input type="text" class="form-control" placeholder=""
										name="oldPrice" value="${sp.price}">
								</div>
							</div>
						</c:forEach>
					</form>
					 -->
					 <!-- 2017-7-19日历 --> 
					
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
											
				
			<!-- 2017-7-19日历 --> 
					 
					 
				</div>
			</div>
		</div>
	
	
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>特殊价格设置</h3>
					</div>
				</div>
				<div class="card-body">
				
				<form action="<%=basePath%>/admin/apartment/price/set"
						class="form form-horizontal" method="post">
						<input type="hidden" name="apartmentId" value="${apartment.id}">
						<div class="form-group">
							<label class="col-md-3 control-label">选择日期</label>
							<div class="col-md-3">
								<input type="date" id="teshuDate" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">当日价格</label>
							<div class="col-md-3">
								<input type="text" id="teshuPrice" class="form-control" placeholder=""
									name="price" value="">
							</div>
						</div>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" id="teshuSubmit" class="btn btn-success">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</form>
				
				
				
				 <!-- 
					<div  class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">选择日期</label>
							<div class="col-md-3">
								<input type="date"  id="teshuDate" class="form-control" placeholder=""
									name="date">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">当日价格</label>
							<div class="col-md-3">
								<input type="number" min="0" id="teshuprice" class="form-control" placeholder=""
									name="price">
							</div>
						</div>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" id="teshuSubmit" class="btn btn-success">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</div>
				-->
				</div>
				
			</div>
		</div>
	
	
	
	</div>
		
	<div class="row">
			
			
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>折扣价格</h3>
					</div>
				</div>
				<div class="card-body">
					<form action="" class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">入住满3天</label>
							<div class="col-md-3">
								<input type="text" id="_date3" autocomplete="off" class="form-control" placeholder=""
									name="price" readonly >
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">入住满7天</label>
							<div class="col-md-3">
								<input type="text" id="_date7" autocomplete="off" class="form-control" placeholder=""
									name="price" readonly >
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">入住满30天</label>
							<div class="col-md-3">
								<input type="text" id="_date30" autocomplete="off" class="form-control" placeholder=""
									name="price" readonly >
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
			
		<div class="col-md-6">
				<div class="card card-mini">
					<div class="card-header">
					<div class="card-title">
						<h3>折扣价设置</h3>
					</div>
				</div>
				<div class="card-body">
					<div class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">入住满3天</label>
							<div class="col-md-3">
								<input type="text" id="date3" autocomplete="off" class="form-control" placeholder="最大1折"
									name="price" >
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">入住满7天</label>
							<div class="col-md-3">
								<input type="text" id="date7"  autocomplete="off" class="form-control" placeholder="最大1折"
									name="price" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">入住满30天</label>
							<div class="col-md-3">
								<input type="text"  id="date30"  autocomplete="off" class="form-control" placeholder="最大1折"
									name="price" value="">
							</div>
						</div>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" id="dateTime" class="btn btn-success">保存</button>
									<button type="button" class="btn btn-default">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>	
			
		</div>

	</my_body>
	
	
	<my_script>
	    
	     <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/jquery-1.7.1.min.js"></script>  		
	    <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/price.js"></script> 
		 <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/date.js"></script> 

	</my_script>
</body>
</html>