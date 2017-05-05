<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>收银台-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">

<my_header>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/dist/customer/css/order-pre.css">
</my_header>
</head>
<body>
	<my_body>
	<div class="order-wrapper" id="order_wrapper">
		<div class="order_infos">
			<div class="order_avalaibel_time">
				<div>
					<span id="day"></span> <span id="hour"></span> <span id="minute"></span>
					<span id="second"></span>
				</div>
				<h3>${order.status}</h3>
				<input type="hidden" id="order-time" value="${order.time}">
				<script type="text/javascript">
			 var orderTime = $('#order-time').val();
			 var nowTime = Date.parse(new Date());
				var intDiff = parseInt((orderTime-nowTime)/1000); //倒计时总秒数量
				function timer(intDiff) {
					window.setInterval(
							function() {
								var day = 0, hour = 0, minute = 0, second = 0; //时间默认值
								if (intDiff > 0) {
									day = Math.floor(intDiff / (60 * 60 * 24));
									hour = Math.floor(intDiff / (60 * 60))
											- (day * 24);
									minute = Math.floor(intDiff / 60)
											- (day * 24 * 60) - (hour * 60);
									second = Math.floor(intDiff)
											- (day * 24 * 60 * 60)
											- (hour * 60 * 60) - (minute * 60);
								}
								if (minute <= 9)
									minute = '0' + minute;
								if (second <= 9)
									second = '0' + second;
								$('#day').html(day + "天");
								$('#hour').html('<s id="h"></s>' + hour + '时');
								$('#minute').html('<s></s>' + minute + '分');
								$('#second').html('<s></s>' + second + '秒');
								intDiff--;
							}, 1000);
				}
				$(function() {
					timer(intDiff);
				});
			</script>
			</div>
			<div class="order_briefs">
				<span class="briefs_item price">订单金额： <span
					class="price_aera"> <dfn>¥</dfn> <b class="num_b">${order.totalPrice }<i
							class="num_s" style="display: none;">.00</i></b>
				</span>
				</span> <span class="briefs_item title"> ${order.description}<em>${order.type}1间&nbsp;&nbsp;&nbsp;&nbsp;入住：${order.startTime }&nbsp;&nbsp;&nbsp;&nbsp;退房：${order.endTime}&nbsp;&nbsp;&nbsp;&nbsp;入住${order.totalDay}晚</em>
				</span>
			</div>
			<div class="order_detail" style="display: block;">
				<dl class="detail_list">
					<dt>费用明细</dt>
					<dd>
						<span class="ti">房费</span><span class="list_ite"><dfn>¥</dfn>${order.totalPrice }</span>
					</dd>
				</dl>
			</div>
			<a href="javascript:;" class="flex_btn" ng-click="details.toggle()"
				style="display: none;"> <span ng-bind="details.action_get()"
				class="ng-binding">订单详情</span> <i class="icon_arrow_up"></i>
			</a>
		</div>
		<div class="hotel_tip">
			<dl class="tip_info">
				<dd>...............一些说明.......</dd>
			</dl>
		</div>
		<div class="mo ng-scope" ng-controller="CmoneyCtrl">
			<p class="pay_price" ng-show="others.amount">
				需支付： <span class="price_aera"> <dfn>¥</dfn> <b
					class="num_b ng-binding">${order.totalPrice}<i class="num_s"
						style="display: none">.00</i></b> <span style="display: none;">
						+ <b class="num_b ng-binding" ng-bind-html="others.fee|amount">0<i
							class="num_s">.00</i></b>
				</span>
				</span> <em style="display: none;" pay-show="others.fee" class="ng-binding">（外卡需加收%服务费）</em>
			</p>
		</div>
		<div class="tabs">
			<div class="tab_nav_nt1">
				<ul class="clearfix pay-type" data-id="${order.id}">
					<li class="tabnav_special  ng-isolate-scope curr pay_tab"
						payType="weixin"><i class="tab_icon_WechatScanCode"></i> 微信支付</li>
					<li class="ng-isolate-scope pay_tab" payType="other">其他支付</li>
				</ul>
			</div>
			<div class="tab_aera weixin_tab weixin" style="">
				<div id="wechatQR" style="display: none;">
					<img id="wechatQRCode" src="">
					<p class="ng-isolate-scope"
						style="padding-top: 10px; background: none;">提示：请打开手机微信的“扫一扫”，扫描二维码完成支付</p>
				</div>
				<p class="ng-isolate-scope" id="wechatTip">提示：点击“下一步”后，请打开手机微信的“扫一扫”，扫描二维码</p>
			</div>
			<div class="tab_aera other_tab" style="display: none;">
				<div class="bank_list noti">
					<ul class="bank_items">
						<li class="ng-isolate-scope act"><i class="more_alipay"></i><em
							class="hint ng-hide" title=""></em></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="order_step">
			<a href="javascript:void(0);" type="button" class="btn_sub ng-binding btn_sub_only" data-payType="wechat"  style="cursor: pointer;">下一步</a>
			<a href="javascript:void(0);" type="button" class="btn_sub ng-binding btn_sub_over" data-payType="pay-over" style="display: none;cursor: pointer;">已完成支付</a>
			<!-- <a href="<%=basePath%>/order/payOver/${order.id}?status=2"
				class="btn_sub ng-binding">下一步</a> -->
		</div>

		<script type="text/javascript">
			var clientIp;
			$.get('https://ipinfo.io/json',function(data){
				clientIp = data.ip;	
			//	console.log(data);
			});
		
			var getting = {
				url:'../../wx/isPayed',
				method:'POST',
				data:{
					id:$('.pay-type').attr('data-id')
				},
				dataType:'json',
				success:function(data){
					if(data.statusCode == 1){
						window.location.href = '../payOver/'+$('.pay-type').attr('data-id');
					}
				}
			};
		
			//支付
			$('.btn_sub').click(function(sub){
				//支付
				if($(sub.target).hasClass('btn_sub_only')){
					$('.pay-type li').each(function(index,e){
						if($(e).hasClass('curr')){
							//微信支付
							if($(e).attr('payType')=='weixin'){
								$.ajax({
									url:'../../wx/pay/nativeOrder',
									method:'POST',
									async:true,
									data:{
										id:$('.pay-type').attr('data-id'),
										ip:clientIp
									},
									success:function(data){
										if(data.status=='success'){
											$('#wechatQRCode').attr('src','../../wx/QRCode?url='+data.obj.url);
											$('#wechatTip').hide();
											$('#wechatQR').show();
											$('.btn_sub_only').hide();
											$('.btn_sub_over').show();
											//关键在这里，Ajax定时访问服务端，不断获取数据 ，这里是1秒请求一次。
											window.setInterval(function(){$.ajax(getting)},2000);
										}else{
											alert('发起订单失败!');
										}
										
									},
									error:function(){
										alert('系统错误，请重试！！');
									}
								});
							}
							
						}
					});
				}else{
					//支付完成点击
					
				}
			
				
			});
			
			//已完成支付
			$('.btn_sub .btn_sub_over').click(function(){
				getting['success']=function(data){
					if(data.statusCode == 1){
						window.location.href = '../payOver/'+$('.pay-type').attr('data-id');
					}else{
						alert('您的支付还未完成！');
					}
				}
				$.get(getting);
			});
			
			//手动查询是否支付
			$('.pay-over').click(function(){
                var checkCount = 0;
                //TODO 再次查询是否支付成功
                var checkWechatPay = function () {
                    var url =  '../../mobile/checkWechatPay';
                    var data = {
                        id:$('.pay-type').attr('data-id')
                    };
                    
                    $.ajax({
                    	url:url,
                    	data:data,
                    	type:'POST',
                    	async:true,
                    	dataType:'json',
                    	success:function (data) {
                           
                            if(data.status == 1 ){
                            	window.location.href = '../payOver/'+$('.pay-type').attr('data-id');
                            }else{
                                //查询3次
                                if (checkCount < 3){
                                    checkCount++;
                                    checkWechatPay();
                                }
                                
                            }
                        }	
                    });
                };
                checkWechatPay();
                alert('您还未支付');
			});
			
			
			
			$('.pay_tab').click(function(e){
				var target = $(e.target);
				$('.tab_nav_nt1 ul li').removeClass('curr');
				$(target).addClass('curr');
				$('.tab_aera').hide();
				if($(target).attr('payType')=='weixin'){
					$('.weixin_tab').show();
				}else if($(target).attr('payType')=='other'){
					$('.other_tab').show();
				}
			});
		</script>
	</div>
	</my_body>
</body>
</html>