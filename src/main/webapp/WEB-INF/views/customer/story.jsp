<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>青客生活-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<my_banner>
	<div class="story_banner"></div>
	</my_banner>
	<my_body>
	<div class="story_tittle"></div>
	<c:forEach items="${blogs.getResults()}" var="blog" varStatus="p">
		<div class="sty_list">
			<img src="${blog.getPic()}" />
			<div class="sty_con">
				<dl class="list_tit">
					<dt>
						<a href="/shenghuo/info-466.html" title="">${blog.getTitle()}</a>
					</dt>
					<dd>${blog.getDateStr()}</dd>
					<div class="list_like">浏览(${blog.getReadtime() })次</div>

				</dl>
				<dl class="list_txt">
					<dt>&nbsp; &nbsp;&nbsp;&nbsp;${blog.getAbs_text() }…</dt>
				</dl>
			</div>
		</div>
	</c:forEach>
	<ul id="pagecontroller" class="pagination">
		<li><a href="<%=basePath%>/story?page=1">&laquo;</a></li>
		<c:forEach varStatus="p" begin="${sp}" end="${ep }" step="1">
			<c:if test="${p.current==blogs.getCurrentPage()}">
				<li class="active"><a
					herf="<%=basePath%>/story?page=${p.current}">${p.current}</a></li>
			</c:if>
			<c:if test="${p.current!=blogs.getCurrentPage()}">
				<li><a herf="<%=basePath%>/story?page=${p.current}">${p.current}</a>
				</li>
			</c:if>
		</c:forEach>
		<li><a href="<%=basePath%>/story?page=${blogs.getPageCount()}">&raquo;</a></li>
	</ul>
	</my_body>
</body>
</html>