charset="UTF-8"
$(document).ready(function(){
    var _uid=fnBase.huoqu(0,"uid");
    //if(_uid==null||_uid=="undefined"||_uid==""){
    //    window.location.href="login.html";
    //    return;
    //}
    $(".infobtn").click(function(){
       var _originalPassword=$("#original").val();
       var _newPassword=$("#newpw").val();
       var _confirmPassword=$("#confirmPassword").val();
       if(_originalPassword==''){
           fnBase.myalert("�����������")
           return
       }
        if(_newPassword==''){
            fnBase.myalert("������������")
            return
        }
        if(_confirmPassword==''){
            fnBase.myalert("����ȷ������")
            return
        }
        if(_confirmPassword!=_newPassword){
            fnBase.myalert("������������벻һ�£�����������")
            return
        }
        var frontURL=Constant.URL+'api/user?code=114';
        var postData={"uid":_uid,"pass":_originalPassword,"newpass":_newPassword}
        postData=JSON.stringify(postData);
        fnBase.commonAjax(frontURL,{'param':postData},function(data){
            console.log(data);
            if(data.status=='1'){
                fnBase.keep(0,"uid","");
                window.location.href="login.html";
            }else{
                fnBase.myalert(data.info)
            }
        })
    })
});
