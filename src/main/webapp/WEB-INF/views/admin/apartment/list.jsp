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
	<div class="row">
		<div class="col-md-12">
			<div class="card card-mini">
				<div class="card-header">
					<div class="card-title">
						<h3>公寓列表</h3>
					</div>
					<ul class="card-action">
						<li><a class="btn btn-success " href="<%=basePath%>/admin/apartment/add">添加</a>
						</li>
					</ul>

				</div>
				<div style="overflow: scroll;width:100%;height:auto">
				<table class="table">
					<thead>
						<tr>
							<th>编号</th>
							<th>类型</th>
							<th>房号</th>
							<th>地址</th>
							<th>位置</th>
							<th>小区</th>
							<th>楼号</th>
							<th>楼层</th>
							<th>朝向</th>
							<th>面积</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="h-table">
						<c:forEach items="${apartments}" var="apartment">
							<tr roomId="${apartment.id}">
								<td>${apartment.id}</td>
								<td>${apartment.basic_info.lei_xing}</td>
								<td>${apartment.position.men_pai}</td>
								<td>${apartment.position.jie_dao}</td>
								<td>${apartment.position.xa_wei_zhi}</td>
								<td>${apartment.position.xiao_qu}</td>
								<td>${apartment.position.lou_hao}</td>
								<td>${apartment.position.lou_ceng}/${apartment.position.zong_lou_ceng}</td>
								<td>${apartment.basic_info.cao_xiang}</td>
								<td>${apartment.basic_info.mian_ji}</td>
								<th><a class="btn"
									href="<%=basePath %>/admin/apartment/update/${apartment.id}">编辑详情</a>&nbsp;&nbsp;<br>
									<a class="btn"
									href="<%=basePath%>/admin/apartment/price/${apartment.id}">编辑价格</a>&nbsp;&nbsp;<br>
									
									<a href="<%=basePath %>/admin/apartment/showHome/${apartment.id}" class="btn" >
									<c:if test="${apartment.show_home == true }">
									首页隐藏
									</c:if>
									
									<c:if test="${apartment.show_home == false }">
									首页显示
									</c:if>
									</a><br>
									<a href="<%=basePath %>/admin/apartment/delete/${apartment.id}" class="btn">删除</a>
								
								<!-- 房客留言操作begin -->	
									<br><a href="<%=basePath%>/admin/leavemsglist" class="btn evalpinglun">查看评论</a>
								<!-- 房客留言操作end -->	
								
								<!-- 房态修改begin -->	
									<br><a href="<%=basePath%>/admin/status" class="btn houseStatus">房态设置</a>
								<!-- 房态修改end -->	
								
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
	</my_body>
	<my_script>
		
	    <script>
	    $("#h-table").on('click','tr .evalpinglun,tr .houseStatus',function(){
			var roomid=$(this).parent().parent().attr('roomid');
			window.sessionStorage.setItem('roomId',roomid);
		})
		
	    </script>
	</my_script>
</body>
</html>