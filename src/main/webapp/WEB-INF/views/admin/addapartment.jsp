<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.xfhotel.hotel.entity.Room"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
$List roomList = new ArrayList();
$
function addroom(){
	$Room r = new Room();
	roomList.add(r);
	$
}
</script>
<body>
	<form action="./add" method="POST">
		<div>
			小区名称：<input type="text" name="community" /> 房屋户型：<input type="text"
				name="bedroom" />室<input type="text" name="livingroom" />厅<input
				type="text" name="bathroom" />卫<input type="text" name="balcony" />阳台
			楼层：第<input type="text" name="floor" />层&nbsp;共<input type="text"
				name="totalfloor" />层 朝向： <select name="direction">
				<option value="e">东</option>
				<option value="se">东南</option>
				<option value="s">南</option>
				<option value="sw">西南</option>
				<option value="w">西</option>
				<option value="nw">西北</option>
				<option value="n">北</option>
				<option value="ne">东北</option>
			</select> 布局图：
		</div>
		<div>设施：</div>
		<div>
			<c:forEach items="${roomList}" var="room" varStatus="p">
				<div>
					名称：<input type="text" name="description" />
					朝向： <select name="r_direction">
						<option value="e">东</option>
						<option value="se">东南</option>
						<option value="s">南</option>
						<option value="sw">西南</option>
						<option value="w">西</option>
						<option value="nw">西北</option>
						<option value="n">北</option>
						<option value="ne">东北</option>
					</select> 面积：<input type="text" name="square" /> 人数：<input type="text"
						name="capacity" /> 价格：<input type="text" name="price" /> 支付方式：
					图片：
				</div>
			</c:forEach>
			<button type="button" onclick="addroom()">新增房间</button>
		</div>
		<button type="submit">提交</button>
	</form>
</body>
</html>