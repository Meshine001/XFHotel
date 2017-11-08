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
						<h3>房屋状态</h3>
					</div>
				</div>
				<div class="card-body" id="statusDate" style="position:relative"> 
					
					<!-- 2017-7-14日历 --> 
					
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
					
				
			<!-- 2017-7-14日历 --> 
		
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>房屋状态设置</h3>
					</div>
				</div>
				<div class="card-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">起始日期</label>
							<div class="col-sm-10 status-time">
								<input type="date" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10 status-endtime">
								<input type="date" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">状态选择</label>
							<div class="col-sm-10 status-house">
								<a href="javascript:;" stag="0">有人住</a>
								<a href="javascript:;" stag="1">没人住</a>
							</div>
						</div>
						<div class="form-group">
								<div class="col-md-9 ">
									<button type="submit" class="btn btn-success" id="btnyesDate">确定</button>
								</div>
						</div>
						<div class="form-group">	
						</div>
						
					</div>	
				</div>
			</div>
			
			
			<div class="card card-mini" style="margin-top:20px;">
				<div class="card-header">
					<div class="card-title">
						<h3>特殊价格设置</h3>
					</div>
				</div>
				<div class="card-body">
				
				<form action="<%=basePath%>/admin/apartment/price/set"
						class="form form-horizontal" method="post">
						<input type="hidden" name="apartmentId" value id="apartmentId">
						<div class="form-group">
							<label class="col-sm-2 control-label">选择日期</label>
							<div class="col-sm-10">
								<input type="date" id="teshuDate" class="form-control" placeholder=""
									name="date" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">当日价格</label>
							<div class="col-sm-10">
								<input type="text" id="teshuPrice" class="form-control" placeholder=""
									name="price" value="">
							</div>
						</div>
						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9">
									<button type="submit" id="teshuSubmit" class="btn btn-success">保存</button>
									<button type="button" class="btn btn-danger">取消</button>
								</div>
							</div>
						</div>
					</form>
				
				</div>
				
			</div>
			
			
			
		</div>
		
		
		
	</my_body>
	
	<my_script>
	    
	     <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/jquery-1.7.1.min.js"></script>  		
	     <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/status.js"></script> 
		 <script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/date.js"></script> 
		<script>
		(function(){
			var bace={
				     request: function(name){
				            var url = window.location.href;
				            if(url){
				                var valArray = url.split("?")[1];
				                if(valArray && valArray.length >0){
				                    var valArr = valArray.split("&");
				                    if(valArr && valArr.length > 0){
				                        for(var i in valArr){
				                            if(valArr[i].split("=")[0] == name){
				                                return valArr[i].split("=")[1];
				                            }
				                        }
				                    }
				                }
				            }
				        }
			}
			$("#apartmentId").attr('value',encodeURIComponent(bace.request("id")));

		})()
		</script>
	</my_script>
	
</body>
</html>