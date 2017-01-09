<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="angtian">
<meta name="description" content="酒店价格日历组件Demo">
<meta name="keywords" content="日历, 日历组件, 价格日历">
<title>酒店价格日历Demo</title>
<style>
body {
	padding: 0;
	margin: 0 10px;
}

.title {
	padding: 0;
	margin: 10px 0;
	font: 700 18px/1.5 \5fae\8f6f\96c5\9ed1;
}

.title em {
	font-style: normal;
	color: #C00;
	font-size: 14px;
}

.title a {
	font: 400 14px/1.5 Tahoma;
}

.example {
	margin-top: 10px;
}

.example button {
	margin: 0 5px 10px 0;
}
</style>
<script src="<%=basePath%>/dist/commons/price-calendar/yui-min.js"></script>
<script>
	var config = {
		modules : {
			'price-calendar' : {
				fullpath : '../../dist/commons/price-calendar/price-calendar.js',
				type : 'js',
				requires : [ 'price-calendar-css' ]
			},
			'price-calendar-css' : {
				fullpath : '../../dist/commons/price-calendar/price-calendar.css',
				type : 'css'
			}
		}
	};
	YUI(config)
			.use(
					'price-calendar',
					'jsonp',
					function(Y) {
						var sub = Y.Lang.sub;
						var url = 'http://fgm.cc/learn/calendar/price-calendar/getData.asp?minDate={mindate}&maxDate={maxdate}&callback={callback}';

						//价格日历实例    
						var oCal = new Y.PriceCalendar();

						//点击确定按钮
						oCal.on('confirm', function() {
							alert('入住时间：' + this.get('depDate') + '\n离店时间：'
									+ this.get('endDate'));
						});

						//点击取消按钮
						oCal.on('cancel',
								function() {
									this.set('depDate', '').set('endDate', '')
											.render();
								});

						Y
								.one('#J_Example')
								.delegate(
										'click',
										function(e) {
											var that = this, oTarget = e.currentTarget;
											switch (true) {
											//设置日历显示个数
											case oTarget.hasClass('J_Count'):
												this
														.set(
																'count',
																oTarget
																		.getAttribute('data-value'))
														.render();
												break;
											//时间范围限定
											case oTarget.hasClass('J_Limit'):
												this
														.set('data', null)
														.set('depDate', '')
														.set('endDate', '')
														.set('minDate', '')
														.set(
																'afterDays',
																oTarget
																		.getAttribute('data-limit'));
												if (!oTarget
														.hasAttribute('data-date')) {
													this
															.set('date',
																	new Date())
												} else {
													var oDate = oTarget
															.getAttribute('data-date');
													this.set('minDate', oDate);
													this.set('date', oDate);
												}
												oTarget.ancestor().one(
														'.J_RoomStatus') ? oTarget
														.ancestor()
														.one('.J_RoomStatus')
														.setContent(
																'\u663e\u793a\u623f\u6001')
														.removeClass('J_Show')
														: oTarget
																.ancestor()
																.append(
																		'<button class="J_RoomStatus">\u663e\u793a\u623f\u6001</button>');
												break;
											//异步拉取酒店数据
											case oTarget
													.hasClass('J_RoomStatus'):
												oTarget.toggleClass('J_Show');
												if (oTarget.hasClass('J_Show'))
													Y
															.jsonp(
																	sub(
																			url,
																			{
																				mindate : this
																						.get('minDate'),
																				maxdate : this
																						.get('maxDate')
																			}),
																	{
																		on : {
																			success : function(
																					data) {
																				that
																						.set(
																								'data',
																								data);
																				oTarget
																						.setContent('\u9690\u85cf\u623f\u6001')
																			}
																		}
																	});
												else {
													this.set('data', null);
													oTarget
															.setContent('\u663e\u793a\u623f\u6001');
												}
												break;
											}
										}, 'button', oCal);
					});
</script>
</head>
<body>
	<h1 class="title">
		酒店价格日历Demo<em>（限定范围后可异步加载房态信息）</em><a href="api.html" target="_blank">API文档</a>
	</h1>
	<div id="J_Example" class="example">
		<button class="J_Count" data-value="1">单日历</button>
		<button class="J_Count" data-value="2">双日历</button>
		<button class="J_Count" data-value="3">三日历</button>
		<br />
		<button class="J_Limit" data-limit="90">限定范围（今天->90天）</button>
		<button class="J_Limit" data-limit="60" data-date="2012-10-01">指定日历时间并限定范围（2012年10月1号->60天）</button>
	</div>
</body>
</html>
