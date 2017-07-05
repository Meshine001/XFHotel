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
			     <div id="new-coupon" style="transition: all .1s;">
			        <div class="card form-horizontal">
			        	<h3 style="float:left;margin: 19px 0 0 12px;">创建优惠卷</h3>
			        	<div style="float:right;margin:12px 12px 0 0"><a class="btn" id="closebtn">X</a></div>
			        </div>
			        <div class="card-body" style="clear:both">
			        	<div class="form-group plo">
				        	<label  class="col-md-3">金额</label>
				        	<div class="col-md-3"><input type="number"  placeholder="金额" class="form-control"  id="pnumber"></div>	
			        	</div>
			        	<div class="form-group plo">
				        	<label  class="col-md-3">种类</label>
				        	<div class="col-md-3">
								<select id="pId"  placeholder="种类" class="form-control" >
										<option value="-1">不限</option>
										<option value="0">电影票</option>
										<option value="1" selected="selected">优惠券</option>
								</select>
							</div>	
			        	</div>
			        	<div class="form-group plo">
				        	<label  class="col-md-3">有效期</label>
				        	<div class="col-md-9">
				        		<input id='mydatepicker2'  placeholder="开始日期" class="form-control"  type='text'/> ~  <input  placeholder="结束日期" class="form-control"  id='mydatepicker3' type='text'/>
				        	</div>	
			        	</div>
			        	<div class="form-group plo">
				        	<label  class="col-md-3">描述</label>
				        	<div class="col-md-9">
				        		<input type=number placeholder="满多少钱可用" class="form-control" id="nnumber2">
				        	</div>	
			        	</div>
			        	
			        	<div class="form-group plo">
				        	<div class="col-md-9">
				        		<a href="javascript:;" id="keepbtn" class="btn" style="background:#777;color:#FFF">确定</a>
				        	</div>	
			        	</div>
			        </div>
			     </div>

			
		</div>
		</div>
	</div>
	</div>
	
	
	<!-- 个人优惠卷列表 -->
	<div class="Volume-list">
	   <span class="close">X</span>
	   <div class=volume><i>优惠卷</i><i>金额</i><i>起止时间</i><i>可用</i></div>
	   <p id="zanwu">暂时没用可用优惠卷</p>
	   <ul>
	        
	   </ul>
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
				<div style="overflow: hidden;width:100%;height:auto;overflow-x:auto;">
				<div class="col-md-12 statistics">
				    <div class="col-md-2 col-xs-12">
				       <laber>总金额<span></span>元</laber> 
				    </div>
				    <div class="col-md-2 col-xs-12">
				    	<laber>总数量<span></span>张</laber> 
				    </div>
				    <div class="col-md-2 col-xs-12">
				    	<laber>未使用<span></span>张</laber> 
				    </div>
				    <div class="col-md-2 col-xs-12">
				    	<laber>已使用<span></span>张</laber> 
				    </div>
				    <div class="col-md-2 col-xs-12">
				    	<laber>已过期<span></span>张</laber> 
				    </div>
				    <div class="col-md-2 col-xs-12">
				    	<laber>抵用金额<span>600</span>元</laber> 
				    </div>
				</div>
				<!-- 筛选 -->
				<div class="col-md-12 statistics">
				    <div class="col-md-3 col-xs-6">
				       		<label class="col-md-3"  style="padding:0;line-height:36px;">注册时间</label>
				       		<div class="col-md-6" style="padding:0">
								<select id="longtime" class="form-control">
									<option tid="3">全部</option>
									<option tid="0">新用户注册</option>
									<option tid="1">注册满一年</option>
								</select>
							</div>	
				    </div>
				    <div class="col-md-3 col-xs-6">
				    	<label class="col-md-3" style="padding:0;line-height:36px;">消费金额</label>
				    	<div class="col-md-6" style="padding:0">
								<select id="monetary" class="form-control">
									<option value="0">全部</option>
									<option value="200">满200</option>
									<option value="400">满400</option>
									<option value="600">满600</option>
								</select>
						</div>			
				    </div>
				    <div class="col-md-3 col-xs-6">
				    	<label class="col-md-3" style="padding:0;line-height:36px;">性别</label>
				    	<div class="col-md-6" style="padding:0">
								<select id="sex" class="form-control">
								    <option>全部</option>
									<option>男</option>
									<option>女</option>
								</select> 
						</div>				
				    </div>
				   
				</div>
				
				
				
				<table class="table" style="border-top:1px solid #dfe6e8">
					
					<thead>
						<th>序号</th>
						<th>等级</th>
						<th>电话</th>
						<th>注册时间</th>
						<th>消费总额</th>
						<th>消费次数</th>
						<th>优惠卷使用情况</th>	
						<th id="about">全选<i></i></th>
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