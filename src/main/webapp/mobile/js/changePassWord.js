//?charset="UTF-8"
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
            fnBase.myalert("������������");
            return
        }
        if(_confirmPassword==''){
            fnBase.myalert("����ȷ������");
            return
        }
        if(_confirmPassword!=_newPassword){
            fnBase.myalert("������������벻һ�£�����������");
            return
        }
        var frontURL=Constant.URL+'/mobile/changePsd';
        var postData={"id":_uid,"oldPsd":_originalPassword,"psd":_newPassword};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.statusCode=='1'){
               fnBase.myalert(data.content)
            }else{
                fnBase.myalert(data.content)
            }
        })
    })
});
