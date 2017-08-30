var _uid="",wechatOpenId="",_status='0',ss="0";
$(document).ready(function(){
    login.Entry();
    login.login();
});
var login={
	timePrompt:function(){
	        var total=60;
	        var mytimecont;
	        $("#fetch-cmd").text("60秒后重发");
	        clearInterval(mytimecont);
	        mytimecont=setInterval(function(){
	            total=total-1;
	            var str=total+"秒后重发";
	            $("#fetch-cmd").text(str);
	            if(total<=0){
	                clearInterval(mytimecont);
	                $("#fetch-cmd").text("短信验证码");
	            }
	        },1000)
	    },
	Entry:function(){
		$("#fetch-cmd").click(function(){
			
			   
	            getid();
	            console.log(ss)
	   
	            
	          
		});
	
		
		function getid(){
			 var phoneNumber=$("#login-username").val();
	            if(phoneNumber==""){
	                fnBase.myalert("请填写手机号码");
	                return;
	            }
	            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
	            if (!myreg.test(phoneNumber)) {
	                fnBase.myalert("手机号码有误！ 请输入11位数字");
	                return;
	            }
			var frontURL=Constant.URL+"/mobile/login";
            var postData ={"tel":$("#login-username").val()};
            fnBase.commonAjax(frontURL,postData ,function(data) {
            	console.log(data);
            	if(data.statusCode == "1"){
            		fnBase.keep( 0,"uid",data.content.id);
                    _uid=data.content.id;
                    wechatOpenId=data.content.wechatOpenId;
                    ss=1;
            	}else if(data.statusCode == "0"){
            		fnBase.myalert(data.content);
            		ss=0;
            	}
                if(ss==0){
	            	return;
	            }else if(ss==1){
	            	  console.log(ss)
	            	
	            	var frontURL=Constant.URL+"/mobile/sendVCode";
		            var postData={"tel":phoneNumber};
		            console.log(postData);
		            fnBase.commonAjax(frontURL,postData,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                    fnBase.myalert("短信发送成功");
		                    _status=1;
		                    login.timePrompt();
		                }else{
		                    fnBase.myalert("短信发送失败")
		                }
		               
		            });
		            
	            }
            	
            	
            })
		}
        $("#login_submit").click(function(){
            if(_status == 0 || $("#login-username").val()==null){
            	fnBase.myalert('请填写已上信息')
            	return;
            }else{
            	if(wechatOpenId == null || wechatOpenId == undefined){
                    var redirect = 'https://open.weixin.qq.com/connect/oauth2/authorize?'+
                        'appid=wxfa31f9e4951f95df'+
                        '&redirect_uri=http%3a%2f%2fwww.yiyunzn.xyz%2fwx%2fauth%2fopenId%3fid%3d'+_uid+
                        '&response_type=code&scope=snsapi_base&'+
                        'state=index.html#wechat_redirect ';
                    window.location.href = redirect;
                }else{
                	
                   window.location.href = "index.html";
                }
            }
            
            
        })
    },
	login:function(){
		$("#weixin").click(function(){
			  var redirect1 = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=index.html#wechat_redirect';
			  window.location.href = redirect1;
		})
	}
};