charset="UTF-8"
$(document).ready(function(){
    var _uid=fnBase.huoqu(0,"userid");
    //if(_uid==null||_uid=="undefined"||_uid==""){
    //    window.location.href="login.html";
    //    return;
    //}
    $(".infobtn").click(function(){
       var _originalPassword=$("#original").val();
       var _newPassword=$("#newpw").val();
       var _confirmPassword=$("#confirmPassword").val();
       if(_originalPassword==''){
           fnBase.myalert("请输入旧密码")
           return
       }
        if(_newPassword==''){
            fnBase.myalert("请输入新密码");
            return
        }
        if(_confirmPassword==''){
            fnBase.myalert("请在此输入新密码");
            return
        }
        if(_confirmPassword!=_newPassword){
            fnBase.myalert("两次输入不一致");
            return
        }
        var frontURL=Constant.URL+'/mobile/changePsd';
        var postData={"id":_uid,"oldPsd":_originalPassword,"psd":_newPassword};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.statusCode=='1'){
               fnBase.myalert(data.content)
               window.location.href="serve.html"
            }else{
                fnBase.myalert(data.content)
            }
        })
    })
});
