
$(document).ready(function(){
    getData();
    var sexVal='';

    var sexbtn=$("#Myscroll-body section .sex");
    sexbtn.live('click',function(){
        $("#masking").show(10,function(){
            $(".alert-content").animate({bottom:0},300);
        });
    });
    $(".alert-content ul li").click(function(){
        sexVal=$(this).text();
       $(this).addClass("selected").siblings().removeClass("selected");
        $("#masking").hide(10,function(){
            $(".alert-content").animate({bottom:'-1.8rem'},300);
        });
        sexbtn.find('.fl').text(sexVal);
        return sexVal;
    });
    function getData(){
        //var _uid = fnBase.huoqu(0, "uid");
        //if (_uid == null || _uid == "undefined" || _uid == "") {
        //    window.location.href = "login.html";
        //    return;
        //}
        var _nickName = decodeURIComponent(fnBase.request("nickName"));
        $("#fillNick").val(_nickName);

        $(".infobtn").click(function(){
            var _nickNameInput=$("#fillNick").val();
            var frontURL='';
            var postData={"name":_nickNameInput,"sex":sexVal};
            fnBase.commonAjax(frontURL,postData,function(data){
                console.log(data);
                if(data.status=="1"){
                    $("#fillNick").val(data.info.name);
                    $("#Myscroll-body section .sex").find('.fl').text(data.info.sex);
                    fnBase.myalert(data.msg)
                }else{
                    fnBase.myalert(data.msg)
                }
            })
        })
    }

});




