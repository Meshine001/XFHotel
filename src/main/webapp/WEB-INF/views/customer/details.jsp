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
<my_header> </my_header>
</head>
<body>
	<e_right>
	<h2 class="e_tit">个人资料</h2>
	<form action="<%=basePath%>/customer/modify" id="detailsForm"
		name="detailsForm" method="post">
		<input type="hidden" name="id" value="${c.details.id}">
		<div class="data">
			<div class="password dataL">
				<dl>
					<dt>昵称：</dt>
					<dd>
						<input name="nick" type="text" value="${c.details.nick}">
					</dd>
				</dl>
				<dl>
					<dt>手机：</dt>
					<dd id="tel">${c.tel}</dd>
					<dd>
						&nbsp;
						<!-- a title="修改手机号" class="red" style="cursor:pointer;" id="change_mobile">修改手机号</a -->
					</dd>
				</dl>

				<dl class="data_xb">
					<dt>
						性别：<INPUT type="hidden" name="sex" value="${c.details.sex}"
							id="xingbie" />
					</dt>
					<dd class="selectedSex" style="cursor: pointer;"
						onclick="select_xingbie(1)" id="xb_1">
						<i></i>小伙
					</dd>
					<dd class="selectedSex" style="cursor: pointer;"
						onclick="select_xingbie(2)" id="xb_2">
						<i></i>姑娘
					</dd>
				</dl>
				<div id="birthday_str" style="display: none;">${c.details.birthday}</div>
				<dl class="my_bor">
					<dt>
						生日：<INPUT type="hidden" name="birthday"
							value="${c.details.birthday}" id="birthday" />
					</dt>
					<dd>
						<div class="e_sele" id="select_year">
							<span class="e_slc" id="b_year">请选择</span><i class="e_sj"></i> <select
								class="e_set" name="shengri_year" id="shengri_year">
								<option value="">请选择</option>
								<option value="1980">1980</option>
								<option value="1981">1981</option>
								<option value="1982">1982</option>
								<option value="1983">1983</option>
								<option value="1984">1984</option>
								<option value="1985">1985</option>
								<option value="1986">1986</option>
								<option value="1987">1987</option>
								<option value="1988">1988</option>
								<option value="1989">1989</option>
								<option value="1990">1990</option>
								<option value="1991">1991</option>
								<option value="1992">1992</option>
								<option value="1993">1993</option>
								<option value="1994">1994</option>
								<option value="1995">1995</option>
								<option value="1996">1996</option>
								<option value="1997">1997</option>
								<option value="1998">1998</option>
								<option value="1999">1999</option>
								<option value="2000">2000</option>
							</select>
						</div>
					</dd>
					<dd>年</dd>
					<dd>
						<div class="e_sele" id="select_month">
							<span class="e_slc" id="b_month">请选择</span><i class="e_sj"></i> <select
								class="e_set" name="shengri_month" id="shengri_month">
								<option value="">请选择</option>
								<option value="1">01</option>
								<option value="2">02</option>
								<option value="3">03</option>
								<option value="4">04</option>
								<option value="5">05</option>
								<option value="6">06</option>
								<option value="7">07</option>
								<option value="8">08</option>
								<option value="9">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</select>
						</div>
					</dd>
					<dd>月</dd>
					<dd>
						<div class="e_sele" id="select_day">
							<span class="e_slc" id="b_day">请选择</span><i class="e_sj"></i> <select
								class="e_set" name="shengri_day" id="shengri_day">
								<option value="">请选择</option>
								<option value="1">01</option>
								<option value="2">02</option>
								<option value="3">03</option>
								<option value="4">04</option>
								<option value="5">05</option>
								<option value="6">06</option>
								<option value="7">07</option>
								<option value="8">08</option>
								<option value="9">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
						</div>
					</dd>
					<dd>日</dd>
				</dl>
				<dl>
					<dt>
						星座：<INPUT type="hidden" name="constellation"
							value="${c.details.constellation}" id="constellation" />
					</dt>
					<dd>
						<div class="e_sele e_sele2" id="select_xingzuo">
							<span class="e_slc" id="c_text"><c:if
									test="${c.details.constellation != ''}">${c.details.constellation}</c:if>
								<c:if test="${c.details.constellation == ''}">请选择</c:if></span><i
								class="e_sj" style="right: -115px;"></i> <select class="e_set"
								name="xingzuo" id="xingzuo">
								<option value="0" selected="selected">请选择</option>
								<option value="1">水瓶座</option>
								<option value="2">双鱼座</option>
								<option value="3">白羊座</option>
								<option value="4">金牛座</option>
								<option value="5">双子座</option>
								<option value="6">巨蟹座</option>
								<option value="7">狮子座</option>
								<option value="8">处女座</option>
								<option value="9">天秤座</option>
								<option value="10">天蝎座</option>
								<option value="11">射手座</option>
								<option value="12">摩羯座</option>
							</select>
						</div>
					</dd>
					<dd></dd>
				</dl>
				<dl>
					<dt>职业：</dt>
					<dd>
						<input name="job" id="zhiye" type="text" value="${c.details.job}"
							style="width: 180px;">
					</dd>
					<dd></dd>
				</dl>
				<dl>
					<dt>
						教育程度：<INPUT type="hidden" name="education"
							value="${c.details.education}" id="education" />
					</dt>
					<dd>
						<div class="e_sele e_sele2" id="select_jiaoyu">
							<span class="e_slc" id="e_text"><c:if
									test="${c.details.education != ''}">${c.details.education}</c:if>
								<c:if test="${c.details.education == ''}">请选择</c:if></span><i class="e_sj"
								style="right: -115px;"></i> <select class="e_set" name="jiaoyu"
								id="jiaoyu">
								<option value="0" selected="selected">请选择</option>
								<option value="1">小学</option>
								<option value="2">初中</option>
								<option value="3">高中</option>
								<option value="4">大专</option>
								<option value="5">本科</option>
								<option value="6">硕士</option>
								<option value="7">博士</option>
								<option value="8">博士后</option>
							</select>
						</div>
					</dd>
					<dd></dd>
				</dl>
				<dl>
					<dt>个性宣言：</dt>
					<dd>
						<input name="declaration" id="xuanyan"
							value="${c.details.declaration}" type="text"
							style="width: 360px;">
					</dd>
					<dd></dd>
				</dl>
				<dl class="aihao">
					<dt>
						兴趣爱好：<input type="text" name="hobby" id="xingqu"
							value="${c.details.hobby}">
					</dt>
					<dd style="display: none;">
						<ul>
							<li class="select_xingqu" id="select_xingqu_1"
								onclick="select_xingqu('1')"><span class="ah_list ">看书/新闻/阅读<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_2"
								onclick="select_xingqu('2')"><span class="ah_list ">上网聊天<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_3"
								onclick="select_xingqu('3')"><span class="ah_list ">登山/郊游<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_4"
								onclick="select_xingqu('4')"><span class="ah_list ">网络游戏<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_5"
								onclick="select_xingqu('5')"><span class="ah_list ">美食/做饭<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_6"
								onclick="select_xingqu('6')"><span class="ah_list ">看电影/电视剧<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_7"
								onclick="select_xingqu('7')"><span class="ah_list ">跳舞/唱歌/音乐<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_8"
								onclick="select_xingqu('8')"><span class="ah_list ">钓鱼/游泳<em></em></span></li>
							<li class="select_xingqu" id="select_xingqu_9"
								onclick="select_xingqu('9')"><span class="ah_list ">跑步/户外运动<em></em></span></li>
						</ul>
					</dd>
				</dl>
				<div class="cen_all_btn">
					<input name="submitButton" id="submitButton" type="button"
						value="保存修改">
				</div>
			</div>
			<div class="dataR">
				<div class="tou">
					<div class="y_166_1" style="margin-left: 32px;"></div>
					<div class="data_pho">
						<img src="../images/${c.details.avatar}" id="face_pic_2"
							onclick="upload_face()" width="160" height="160"
							style="border-radius: 50%; cursor: pointer;">
					</div>
					<a style="cursor: pointer;" title="上传头像" id="upload_button"
						onclick="upload_face()">修改头像</a>
				</div>
				<input type="hidden" name="avatar" id="face_url" value="${c.details.avatar}">
				<input type="hidden" name="customerId" value="${c.id}">
			</div>
		</div>
	</form>
	<div style="display: none">
		<div class="" id="upload-face">
			<form action="" id="uploadForm">
				<input type="file" value="" id="avatar" name="file"><br>
				<button type="button" value="" id="upload">确定</button>
			</form>
		</div>
	</div>
	</e_right>
	<my_script> <script type="text/javascript"
		src="<%=basePath%>/dist/customer/js/details.js"></script></my_script>
	</my_script>
</body>
</html>