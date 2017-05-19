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
						<a href="./story/blog_content?id=${blog.getId() }" title="">${blog.getTitle()}</a>
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
	<div id="pagecontroller" class="page">
		<a class="up none" href="<%=basePath%>/story?page=1">&lt;</a>
		<c:forEach varStatus="p" begin="${sp}" end="${ep }" step="1">
			<c:if test="${p.current==blogs.getCurrentPage()}">
				<a class="page_on" href="<%=basePath%>/story?page=${p.current}">${p.current}</a>
			</c:if>
			<c:if test="${p.current!=blogs.getCurrentPage()}">
				<a href="<%=basePath%>/story?page=${p.current}">${p.current}</a>
			</c:if>
		</c:forEach>
		<a class="pgdn" href="<%=basePath%>/story?page=${blogs.getPageCount()}">&gt;</a>
	</div>
	
	</my_body>
</body>
</html>