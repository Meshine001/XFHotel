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
	<div>
		<form action="<%=request.getContextPath()%>/admin/apartment/addroom"
			method="post">
			<c:forEach var="i" begin="1" end="${num_room}" step="1">
				<div>
					房间${i}：
					<div>
						编号：<input type="text" id="id" name="id">
					</div>
					<div>
						类型：<input type="text" id="type" name="type">
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
						<c:forEach items="${l_facility}" var="facility" varStatus="p" begin="0">
							<input type="checkbox" name="${facility.id}_${i}" value="1"> ${facility.description }
						</c:forEach>
					</div>
					<div>图片：</div>
				</div>
			</c:forEach>
			<input type="hidden" name="apartment_id" value="${apartment_id }">
			<input type="hidden" name="num_room" value="${num_room }"> <input
				type="submit" id="btnSend" value="添加">
		</form>
	</div>
</body>
</html>