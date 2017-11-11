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
	
	<!-- 11.11 -->
		<div class="modallg">
		  <div class="modal-dialog">
			<div class="modal-content">
			  <div class="modal-header">
				 <button type="button" class="close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title house-title" id="app-title-msg">修改房态<i></i></h4>
			  </div>
			  <div class="modal-body row" id="activerooom">
					<a class="btn" stag="0">有人住</a>
					<a class="btn" stag="1">没人住</a>
			  </div>
			  <div class="modal-footer" style="text-align:center">
			    <a class="btn btn-success" id="present">确定</a>
			  </div>
			</div>
		  </div>
		</div>
	<!-- 11.11 -->
	
	
	
	
	
	
	
	
	
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
		<div class="btn-group" id="sethouse">
		    <a type="button" class="btn btn-default">修改房态</a>
		    <a type="button" class="btn btn-default">修改价格</a>
		</div>
				</div>
			</div>
			

			
			
			
			
		</div>
		
		<div class="col-md-6">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>第三方平台订单统计</h3>
					</div>
				</div>
				<div class="card-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">订单来源</label>
							<div class="col-sm-10 status-time">
								<input type="text" class="form-control" placeholder="" id="otherAddress"
									name="date" value="">
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">总收入</label>
							<div class="col-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="date" value="" id="otherSave">
									<span class="input-group-addon">元</span>	
								</div>
							</div>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">入住总天数</label>
							<div class="col-sm-10">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="date" value="" id="otherdate">
									<span class="input-group-addon">天</span>	
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">入住人姓名</label>
							<div class="col-sm-10 status-endtime">
								<input type="text" class="form-control" placeholder=""
									name="date" value="" id="otherName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-10 status-endtime">
								<input type="text" class="form-control" placeholder=""
									name="date" value="" id="otherTel">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">入住时间</label>
							<div class="col-sm-10 status-endtime">
								<input type="date" class="form-control" placeholder=""
									name="date" value="" id="otherCheck-in">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">离开时间</label>
							<div class="col-sm-10 status-endtime">
								<input type="date" class="form-control" placeholder=""
									name="date" value="" id="otherCheck-out">
							</div>
						</div>

						<div class="form-group">
								<div class="col-md-9 ">
									<button type="submit" class="btn btn-success" id="otherOrder">确定</button>
								</div>
						</div>
						<div class="form-group">
								<div class="col-md-12 ">
									<p style="margin-top:10px">提示：填写完订单信息后，请及时修改房屋状态并保存。</p>
								</div>
						</div>
						
					</div>	
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