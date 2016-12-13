<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/dist/commons/jquery/jquery-3.1.1.js"></script>
	<script type="text/javascript">
		var roomid = ${roomid};
		$(document).ready(function(){
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : 'json',
				data : {'roomid':roomid},
				url : "<%=request.getContextPath()%>/admin/apartment/getroom",//请求的action路径
				error : function() {//请求失败处理函数
					alert("获取数据失败！");
				},
				success : function(data) {
				}
			});
		});
	</script>
				
	<div>
		<form action="<%=request.getContextPath()%>/admin/apartment/updateroom"
			method="post">
			<div>
				房间${i}：
				<div>
					编号：<input type="text" id="id" name="id">
				</div>
				<div>
					类型：<select id="type" name="type">
						<option value="primary">主卧</option>
						<option value="secondary">次卧</option>
					</select>
				</div>
				<div>
					朝向： <select name="direction">
						<option value="e">东</option>
						<option value="se">东南</option>
						<option value="s">南</option>
						<option value="sw">西南</option>
						<option value="w">西</option>
						<option value="nw">西北</option>
						<option value="n">北</option>
						<option value="ne">东北</option>
					</select>
				</div>
				<div>
					面积：<input type="text" name="square">
				</div>
				<div>
					设施：
					<c:forEach items="${l_facility}" var="facility" varStatus="p"
						begin="0">
						<input type="checkbox" name="${facility.id}_${i}" value="1"> ${facility.description }
						</c:forEach>
				</div>
				<div>图片：</div>
			</div>
			<input type="hidden" name="roomid" > <input
				type="submit" id="btnSend" value="添加">
		</form>
	</div>
</body>
</html>