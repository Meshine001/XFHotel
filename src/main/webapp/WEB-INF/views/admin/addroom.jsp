<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		</script>
		<form action="<%=request.getContextPath()%>/admin/apartment/addroom"
			method="post">
			<c:forEach var="i" begin="1" end="${num_room}" step="1">
				<div>
					房间${i}：
					<div>
						编号：<input type="text" name="id_${i}">
					</div>
					<div>
						类型：<input type="text" name="type_${i}">
					</div>
					<div>
						朝向： <select name="direction_${i}">
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
						面积：<input type="text" name="square_${i}">
					</div>
					<div>
						设施：
						<c:forEach items="${lf}" var="facility" varStatus="p">
							<input type="checkbox" name="${facility.description}_${i}"
								value="1"> ${facility.description }
						</c:forEach>
					</div>
					<div>图片：</div>
				</div>
			</c:forEach>
			<input type="hidden" name="apartment_id" value="${apartment_id }">
			<input type="hidden" name="num_room" value="${num_room }"> <input
				type="submit" value="添加">
		</form>
	</div>
</body>
</html>