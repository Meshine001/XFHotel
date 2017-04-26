﻿$(document).ready(function(){
    login.Entry();
});
var login={
    Entry:function(){

        $("#login_submit").click(function(){
            var phoneNumber=$("#login-username").val();
            if(phoneNumber==""){
                fnBase.myalert("请填写手机号码");
                return;
            }
            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            if(!myreg.test(phoneNumber)){
                fnBase.myalert("手机号码有误！ 请输入11位数字");
                return;
            }
            var passwordNumber=$("#login-password").val();
            if(passwordNumber=="" || passwordNumber.length<6){
                fnBase.myalert("请填写至少6位数登录密码");
                return;
            }
            var frontURL=Constant.URL+"/mobile/login";
            var postData ={"tel":phoneNumber,"password":passwordNumber};
            fnBase.commonAjax(frontURL,postData ,function(data) {
                console.log(data);
                if (data.statusCode == "1") {
                    fnBase.keep( 0,"uid",data.content);
                    window.location.href = "index.html";
                } else {
                    fnBase.myalert(data.content);
                }
            })
        })
    }
};