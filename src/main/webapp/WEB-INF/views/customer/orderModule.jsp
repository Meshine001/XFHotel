<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>title-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<div id="ol" class="ol">
    <h3 class="t_h30">确认订单</h3>
    <div class="ol_info">
      <p>请如实填写以下信息。</p>
      <h4>${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间</h4>
      <h4>${oStart}入住-${oEnd}离开&nbsp;&nbsp;共${oTotalDay}晚&nbsp;&nbsp;¥${oTotalPrice}</h4>
      <form action="<%=basePath%>/order/modulePost" method="post" name="" id="">
      <input type="hidden" name="cusId" value="${c.id}">
      <input type="hidden" name="description" value="${apartment.community}-${apartment.num_building}-${apartment.direction}朝向-${apartment.num_door}房间">
       <input type="hidden" name="roomId" value="${room.id}">
      <dl>
        <dd><font color="#FF0000">*</font>&nbsp;您的姓名：</dd>
        <!--input的点击class样式为ol_on-->
        <dt>
          <input id="xingming" class="ol_input" value="${c.details.name}" name="cusName" placeholder="行不改名坐不改姓，坚决不做无名氏">
        </dt>
      </dl>
      <dl>
        <dd><font color="#FF0000">*</font>&nbsp;联系手机：</dd>
        <dt>
          <input id="shouji" class="ol_input" value="${c.details.tel}" name="cusTel" placeholder="请填写您的手机号">
        </dt>
      </dl>
            <dl>
        <dd><font color="#FF0000">*</font>&nbsp;身份证号：</dd>
        <dt>
          <input id="shouji" class="ol_input" value="${c.details.idCard}" name="cusIdCard" placeholder="请填写您的身份证号">
        </dt>
      </dl>
      <dl>
        <dd>需要发票：</dd>
        <dt class="woman">
          <input name="needFapiao" type="radio" value="false" checked="checked">
          不需要</dt>
        <dt class="man">
          <input name="needFapiao" type="radio" value="true">
          需要</dt>
      </dl>
      <dl>
        <dd>个人需求：</dd>
        <dt>
          <textarea name="personal"></textarea>
        </dt>
      </dl>
      <input type="hidden" value="${oStart}" name="startTime">
      <input type="hidden" value="${oEnd}" name="endTime">
      <input type="hidden" name="totalDay" value="${oTotalDay}">
      <input type="hidden" name="price" value="${oPrice}">
      <input type="hidden" name="price" value="${oTotalPrice}">
      <input type="hidden" name="preferential" value="${oPreferential}">
      <input type="hidden" name="apartmentType" value="${apartment.apartmenttype}">
      <input id="submit" type="submit" class="tijiao" value="提&nbsp;&nbsp;交" name="">
      </form>
      <span class="colse"> × </span> </div>
  </div>
</body>
</html>