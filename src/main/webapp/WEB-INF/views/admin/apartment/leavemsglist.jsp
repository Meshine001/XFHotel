<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>公寓列表-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">

</head>
<body>
	<my_body>
	<div id="masking" style="display:none"></div>
	<div class="myalert">
		<p style="font-size:14px;color:#337ab7;line-height:43px;padding-left:10px">留言回复 <a class="btn close " style="float:right;color:#337ab7;opacity: 1;background: none;box-shadow: none;">X</a></p>
		<textarea  name="content" id="mymassage"  placeholder="在此处留下你想对评论者的回复内容" ></textarea>
		<p style="text-align:center"><a href="javascript:;" style="padding: 7px 35px;width:30%;margin:0 auto;" class="btn verify">确认</a></p>
	</div>
	
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>评论列表</h3>
					</div>
					
				</div>
				<div style="overflow: auto;width:100%;height:auto">
				<table class="table" id="table">
					<thead class="msglist">
						<tr>
							<th>评论人</th>
							<th>评论时间</th>
							<th>评论内容</th>
							<th>评分</th>
							<th>操作</th>
						</tr>
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
		<script type="text/javascript" src="<%=basePath%>/dist/admin/assets/js/msglist.js"></script> 
	</my_script>
</body>
</html>