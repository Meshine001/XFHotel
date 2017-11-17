<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
	<header>
		<div class="main">
			<a href="<%=basePath%>" title="青舍首页" class="logo">智能公寓平台</a>
			<div class="nav clearfix">
				<ul class="nav_ul">
					<li><a href="<%=basePath%>/" title="">首页</a></li>
					<li><a href="<%=basePath%>/list?area=0&priceRange=0&layout=0&enterTime=0&leaseType=1&features=0&sortType=0" title="">酒店式公寓</a></li>
					<li><a href="<%=basePath%>/list?area=0&priceRange=0&layout=0&enterTime=0&leaseType=3&features=0&sortType=0" title="">休闲式公寓</a></li>
					<li><a href="<%=basePath%>/story?page=1" title="">青客生活</a></li>
					<li><a href="<%=basePath%>/serviceCenter" target="_blank"
						title="">服务中心</a></li>
					<li><a href="" target="_blank" title="">在线管家</a></li>
				</ul>
				<div class="lgorrg" onmouseover="showHomeMenu()"
					onmouseout="hideHomeMenu()">
					<c:choose>
						<c:when test="${c != null }">
							<a href="<%=basePath%>/customer/details"
								title="${c.details.nick}">
								<div class="name">
									<img src="<%=basePath%>/images/${c.details.avatar}" width="40"
										height="40"><span>${c.details.nick}</span>
								</div>
							</a>
							<ul class="name_ul" id="home-menu" style="display: none;">
								<li><a href="<%=basePath%>/customer/details">个人资料</a></li>
								<li><a href="<%=basePath%>/customer/logout">退出</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<a class="lg" href="<%=basePath%>/customer/login?forword=login"
								title="登录">登录</a>&nbsp;&nbsp;<span><img alt=""
								src="<%=basePath%>/dist/public/v1/images/phone.jpg"></span>&nbsp;&nbsp;<a
								class="rg" href="<%=basePath%>/customer/login?forword=reg"
								title="注册青舍用户">注册</a>
						</c:otherwise>
					</c:choose>
				</div>
				<a target="_blank" title="下载APP" href="/down/"
					style="display: none;">
					<div class="download">
						<i></i>下载APP
					</div>
				</a>
			</div>
		</div>
		<script type="text/javascript">
			function showHomeMenu() {
				$("#home-menu").css('display', '');
			}
			function hideHomeMenu() {
				$("#home-menu").hide();
			}
		</script>
	</header>