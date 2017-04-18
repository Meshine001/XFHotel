<%@page import="java.net.URLEncoder"%>
<%@page import="com.xfhotel.hotel.support.wechat.Config"%>
<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath();  
	   
	if(request.getQueryString()!=null) 
	{   
	    url+="?"+request.getQueryString();           
	} 
%>
<%
	JSONObject auth = (JSONObject) session.getAttribute("wechatAuth");
	if (null == auth) {
		 String redirect = Config.AUTH_CODE_URL
				 .replace("STATE", url)
				 .replace("REDIRECT_URI", URLEncoder.encode(Config.REQUEST_CODE_REDIRECT_URI, "utf-8"));
		 System.out.println(redirect);
		 response.sendRedirect(redirect);
	}
	
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>js sdk 调起页面</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script>
        function submit(){
            $.ajax({
                type: 'POST',
                url: '../wechat/pay/jsOrder',
                data: {'detail':'测试','desc':'测试','goodSn':'20000000101','orderSn':'200000001111','amount':'0.01'},
                success: function(data){
                console.log(data.obj);
                var appId=data.obj.appId;
                var timeStamp=data.obj.timeStamp;
                var nonceStr=data.obj.nonceStr;
                var package=data.obj.package;
                var paySign=data.obj.paySign;
                WeixinJSBridge.invoke(
                        'getBrandWCPayRequest', {
                            "appId":appId,     //公众号名称，由商户传入
                            "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
                            "nonceStr":nonceStr, //随机串
                            "package":package,
                            "signType":"MD5",         //微信签名方式：
                            "paySign":paySign //微信签名
                        },
                        function(res){
                            WeixinJSBridge.log(res.err_msg);
                            if(res.err_msg == "get_brand_wcpay_request:ok"){
                                <!--支付成功调用-->
                                <!--history.go(0);   -->
                                //alert("成功");
                            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                                <!--取消支付调用-->
                                //alert("取消");

                            }else{
                                <!--支付失败-->
                                //alert("失败");

                            }
                        }
                );
            } ,
                dataType: "json"});

        }

        $(function(){
                $("#sub").click(function(){
                    submit();
                });
        });

    </script>
</head>
<body>
<input type="text" placeholder="请输入金额"/><br>
<input type="button" id="sub" value="提交">
</body>
</html>
