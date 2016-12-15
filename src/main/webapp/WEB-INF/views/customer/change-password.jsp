<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>${title}-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
</head>
<body>
	<e_right>
	<h2 class="e_tit2">
		<a class="e_titon" title="修改密码" href="/home/change_password.html">修改密码</a>
	</h2>
	<form action="/ajax/changePassword.ajax.php?action=changepassword"
		name="changePasswordForm" id="changePasswordForm" method="post">
		<div class="password">
			<dl>
				<dt>手机号：</dt>
				<dd id="changeMobile">187****9465</dd>
				<dd>
					<a title="修改手机号" class="red" style="cursor: pointer;"
						id="change_mobile">修改手机号</a>
				</dd>
			</dl>
			<dl>
				<dt>验证码：</dt>
				<dd>
					<input name="yzm" id="yzm" type="text" class="pass_ipt1 input_yzm"><em
						class="pass_btn1 sendcode" id="sendcode"
						onclick="sendcode($(this),'18710579465')">获取验证码</em>
				</dd>
				<dd style="display: none;" class="yzm_error_tip" id="yzm_error_tip">
					<i>-</i>
					<p class="tip_text" id="tip_text">请输入正确的验证码</p>
				</dd>
			</dl>
			<dl>
				<dt>新密码：</dt>
				<dd>
					<input name="newpwd" type="password" id="newpwd"
						onkeyup="qiangdu()">
				</dd>
				<dd class="pass_aq">
					<span class="" id="pwd_ruo">弱</span> <span class="" id="pwd_zhong">中</span>
					<span class="" id="pwd_qiang">强</span>
				</dd>
			</dl>
			<dl>
				<dt>确认密码：</dt>
				<dd>
					<input name="newpwd2" type="password" id="newpwd2"
						onblur="chkpwd()">
				</dd>
				<dd id="tip-pwd2" style="display: none;">
					<i>-</i>
					<p>两次输入的密码不一致。</p>
				</dd>
			</dl>
			<div class="cen_all_btn">
				<input style="cursor: pointer;" name="submitButton"
					id="submitButton" type="submit" value="保存修改">
			</div>
		</div>
	</form>
	</e_right>
</body>
</html>