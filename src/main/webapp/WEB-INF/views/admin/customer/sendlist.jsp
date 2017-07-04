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
<link rel="stylesheet" type="text/css" href="<%=basePath%>/dist/admin/assets/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/dist/admin/assets/css/zzsc.css">
<link rel="stylesheet" href="<%=basePath%>/dist/admin/assets/css/dcalendar.picker.css"/>
</head>


<body>
  
	<my_body>
	<div class="masking" style="display:none"></div>
	<div id="sending" >
			<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<table class="table" id="new-coupon" style="transition: all .1s;">
					<tr><th style="color:#5cb85c;font-size:18px">创建优惠卷</th><th><a class="btn" id="closebtn" style="float:right">X</a></th></tr>
					<tr>
						<th width="12%">金额</th>
						<th><input type=number id="pnumber"></th>	
					</tr>
					<tr>
						<th>种类</th>
						<th><select id="pId">
									<option value="-1">不限</option>
									<option value="0">电影票</option>
									<option value="1" selected="selected">优惠券</option>
							</select></th>
					</tr>
					<tr>
						<th>有效期</th>
						<th><input id='mydatepicker2' type='text'/> 至  <input id='mydatepicker3' type='text'/></th>	
					</tr>
				
					<tr>
						<th>描述</th>
						<th>消费满<input type=number id="nnumber2">元可用</th>	
					</tr>
					<tr>
						<th><a href="javascript:;" id="keepbtn" class="btn" style="background:#777;color:#FFF">确定</a></th>
						<th></th>
					</tr>
				</table>
		</div>
		</div>
	</div>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>派发优惠卷</h3>
					</div>
					<ul class="card-action">
						<li><a href="javascript:;" class="btn" id="delivery" style="background:#18aa4a;color:#FFF">发送</a></li>
					</ul>
				</div>
				<div style="overflow: scroll;width:100%;height:auto">
				<div class="col-md-12 statistics">
				    <div class="col-md-2 col-xs-6">
				       <laber>总金额<span>3000</span>元</laber> 
				    </div>
				    <div class="col-md-2 col-xs-6">
				    	<laber>总数量<span>1500</span>个</laber> 
				    </div>
				    <div class="col-md-2 col-xs-6">
				    	<laber>未使用<span>1500</span>个</laber> 
				    </div>
				    <div class="col-md-2 col-xs-6">
				    	<laber>已使用<span>0</span>个</laber> 
				    </div>
				    <div class="col-md-2 col-xs-6">
				    	<laber>已过期<span>0</span>个</laber> 
				    </div>
				</div>
				<table class="table" style="border-top:1px solid #dfe6e8">
					
					<thead>
						<th>
							注册时间
								<select id="longtime">
									<option tid="3">全部</option>
									<option tid="0">新用户注册</option>
									<option tid="1">注册满一年</option>
								</select>
						</th>
						<th>
							消费金额筛选
								<select id="monetary">
									<option value="0">全部</option>
									<option value="200">满200</option>
									<option value="400">满400</option>
									<option value="600">满600</option>
								</select>
						</th>
						<th> 性别
								<select id="sex">
								    <option>全部</option>
									<option>男</option>
									<option>女</option>
								</select>       
						</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th id="about" class="">全选<i></i></th>
						<!--<th>1</th>  -->
					</thead>
					<thead>
						<th>序号</th>
						<th>等级</th>
						<th>电话</th>
						<th>注册时间</th>
						<th>消费总额</th>
						<th>消费次数</th>
						<!-- <th>状态</th> -->
						<th>优惠卷使用情况</th>
						<th>优惠卷选择</th>
					</thead>
					<tbody id="list">
						
					</tbody>
				</table>
				</div>
				<ul id="pagecontroller" class="pagination">
					
				</ul>
			</div>
		</div>
	</div>
	</my_body>
	<my_script> 
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/list-customer-yhj.js"></script> 
		<script type="text/javascript"src="<%=basePath%>/dist/admin/assets/js/dcalendar.picker.js"></script>
	<script>
		$('#mydatepicker').dcalendarpicker(); 
		$('#mydatepicker2,#mydatepicker3').dcalendarpicker({
			format:'yyyy-mm-dd'
		}); 
		$('#mycalendar').dcalendar();
	</script>	
	</my_script>
</body>
</html>