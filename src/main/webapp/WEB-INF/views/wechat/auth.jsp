<%@page import="com.xfhotel.hotel.support.wechat.Config"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	JSONObject auth = (JSONObject) session.getAttribute("wechatAuth");
	if (null == auth) {
		 String url=request.getParameter("url");
		 String redirect = Config.AUTH_CODE_URL.replace("STATE", url);
		response.sendRedirect(redirect);
	}
	
%>