
$(document).ready(function(){
    login.Entry();
});
var login={
    Entry:function(){

        $("#login_submit").click(function(){
            var phoneNumber=$("#login-username").val();
            if(phoneNumber==""){
                fnBase.myalert("����д�ֻ�����");
                return;
            }
            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            if(!myreg.test(phoneNumber)){
                fnBase.myalert("�ֻ��������� ������11λ����");
                return;
            }
            var passwordNumber=$("#login-password").val();
            if(passwordNumber=="" || passwordNumber.length<6){
                fnBase.myalert("����д����6λ����¼����");
                return;
            }
            var frontURL=Constant.URL+"/mobile/login";
            var postData ={"tel":phoneNumber,"password":passwordNumber};
            //postData=JSON.stringify(postData);
            fnBase.commonAjax(frontURL,postData ,function(data) {
                console.log(data);
                if (data.statusCode == "1") {
                    window.location.href = "index.html";
                } else {
                    fnBase.myalert(data.content);
                }
            })
        })
    }
};