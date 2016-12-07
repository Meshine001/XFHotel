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
	<form action="<%=request.getContextPath()%>/admin/apartment/add"
		method="POST">
		<div>
			<div>
				地址：<input type="text" name="address" />
			</div>
			<div>
				小区名称：<input type="text" name="community" />
			</div>
			<div>
				楼号：<input type="text" name="num_building" />
			</div>
			<div>
				楼层：第<input type="text" name="floor" />层&nbsp;共<input type="text"
					name="totalfloor" />层
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
				面积：<input type="text" name="square" /> 人数：<input type="text"
					name="capacity" />
			</div>
			<div>
				房屋户型：<input type="text" name="bedroom" />室<input type="text"
					name="livingroom" />厅<input type="text" name="bathroom" />卫<input
					type="text" name="balcony" />阳台
			</div>
			<div>
				特色：
				<c:forEach items="${l_feature}" var="feature" varStatus="p">
					<input type="checkbox" name="feature" value="${feature.id}"> ${feature.description }
				</c:forEach>
			</div>
			<div>
				备注：<input type="text" name="description">
			</div>
			<div>布局图：</div>
		</div>
		<div>
			设施：
			<c:forEach items="${l_facility}" var="facility" varStatus="p">
				<input type="checkbox" name="facility" value="${facility.id}"> ${facility.description }
		</c:forEach>
		</div>
		<div>
			房间设置：
			<div>
				总房间数：<input type="text" name="num_room">
			</div>
		</div>
		<script type="text/javascript">
			var type1 = 0;
			var type2 = 0;
			document.getElementById("lease").style.display = "none";
			document.getElementById("leasesub").style.display = "none";
			function type1change(op) {
				if (op == 1) {
					document.getElementById("leasesub").style.display = "";
				} else {
					document.getElementById("leasesub").style.display = "none";
				}
			}
			function type2change(op) {
				if (op == 1)
					document.getElementById("lease").style.display = "";
				if (op == 2)
					document.getElementById("lease").style.display = "none";
				if (op == 3)
					document.getElementById("lease").style.display = "";
			}
		</script>

		<div>
			出租类型：<select name="type1"
				onchange="type1change(this.options[this.options.selectedIndex].value)">
				<option value="0">请选择</option>
				<option value="1">酒店型公寓</option>
				<option value="2">短租型公寓</option>
			</select> <select name="type2"
				onchange="type2change(this.options[this.options.selectedIndex].value)">
				<option value="0">请选择</option>
				<option value="1">单租型</option>
				<option value="2">合租型</option>
				<option value="3">混合型</option>
			</select>
		</div>
		<div id="lease" style="display: none">
			<table>
				<tr>
					<td>选择</td>
					<td>出租类型</td>
					<td>出租价格</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="daytype"></td>
					<td>天</td>
					<td><input type="text" name="dayprice"></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="weektype"></td>
					<td>周</td>
					<td><input type="text" name="weekprice"></td>
				</tr>
				<div id="leasesub" style="display: none">
				<tr>
					<td><input type="checkbox" name="monthtype"></td>
					<td>月</td>
					<td><input type="text" name="monthprice"></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="yeartype"></td>
					<td>年</td>
					<td><input type="text" name="yearprice"></td>
				</tr>
				</div>
			</table>
		</div>
		<button type="submit">提交</button>
	</form>
</body>
</html>