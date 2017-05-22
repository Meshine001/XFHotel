<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>确认订单-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<div class="panel">
		<div class="panel-body">
			<div id="ol" class="">
				<h3 class="t_h30">确认订单</h3>
				<div class="ol_info">
					<p>请如实填写以下信息。</p>
					<h4>${apartment.position.xiao_qu}-${apartment.position.xa_wei_zhi}-${apartment.basic_info.cao_xiang}朝向</h4>
					<h4>${oStart}入住-${oEnd}离开&nbsp;&nbsp;共${oTotalDay}晚</h4>
					<h4>押金：¥${oCashPledge}&nbsp;&nbsp;需要支付：¥${oTotalPrice}</h4>
					<form action="<%=basePath%>/order/modulePost" method="post" name=""
						id="">
						<input type="hidden" name="cusId" value="${c.id}"> <input
							type="hidden" name="description"
							value="${apartment.position.xiao_qu}-${apartment.position.lou_hao}号楼-${apartment.position.dan_yuan}单元-${apartment.position.lou_ceng}楼-${apartment.position.men_pai}房间">
						<input type="hidden" name="roomId" value="${apartment.id}">
						<dl>
							<dd>
								<font color="#FF0000">*</font>&nbsp;您的姓名：
							</dd>
							<!--input的点击class样式为ol_on-->
							<dt>
								<input id="xingming" class="ol_input" value="${c.details.name}"
									name="cusName" placeholder="请输入您的姓名">
							</dt>
						</dl>
						<dl>
							<dd>
								<font color="#FF0000">*</font>&nbsp;联系手机：
							</dd>
							<dt>
								<input id="shouji" class="ol_input" value="${c.details.tel}"
									name="cusTel" placeholder="请填写您的手机号">
							</dt>
						</dl>
						<dl>
							<dd>
								<font color="#FF0000">*</font>&nbsp;身份证号：
							</dd>
							<dt>
								<input id="shouji" class="ol_input" value="${c.details.idCard}"
									name="cusIdCard" placeholder="请填写您的身份证号">
							</dt>
						</dl>
						<dl>
							<dd>
								<font color="#FF0000"></font>&nbsp;同住人信息：
							</dd>
							<dt>
								<input id="otherCusName" class="ol_input" value=""
									name="otherCusName" placeholder="请填同住人姓名，若无可不填">
							</dt>


						</dl>
						<dl>
							<dt>
								<input id="otherCusIDCard" class="ol_input" value=""
									name="otherCusIdCard" placeholder="请填同住人身份证">
							</dt>
						</dl>

						<dl style="display: none;">
							<dd>需要发票：</dd>
							<dt class="woman">
								<input name="needFapiao" type="radio" value="false"
									checked="checked"> 不需要
							</dt>
							<dt class="man">
								<input name="needFapiao" type="radio" value="true"> 需要
							</dt>
						</dl>
						<dl>
							<dd>个人需求：</dd>
							<dt>
								<textarea name="personal"></textarea>
							</dt>
						</dl>
						<input type="hidden" value="${oStart}" name="startTime"> <input
							type="hidden" value="${oEnd}" name="endTime"> <input
							type="hidden" name="totalDay" value="${oTotalDay}"> <input
							type="hidden" name="price" value="${oPrice}"> <input
							type="hidden" name="totalPrice" value="${oTotalPrice}"> <input
							type="hidden" name="preferential" value="${oPreferential}">
						<input type="hidden" name="apartmentType"
							value="${apartment.basic_info.lei_xing}"> <input id="submit"
							type="submit" class="tijiao" value="提&nbsp;&nbsp;交" name="">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>