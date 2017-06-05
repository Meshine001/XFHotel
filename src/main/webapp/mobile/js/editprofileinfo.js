
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
    var personpic='';

    //$('#formid #inputfile').change(function(){
    //   $('#formid').submit();
    //});

    //~~~~~~~~~~~~~~~~~~~~~~~~

    var commersrc='';
    $("#file").change(function(){
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: Constant.URL+'/mobile/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                commersrc=data.content;
                $("#avatar").attr('src',Constant.URL+'/images/'+data.content);
            },
            error: function (data) {
                console.log(data);
            }
        });
    })




    //~~~~~~~~~~~~~~~~~~~~~~~~~




    $(".baocun_btn").click(function(){
    	fnBase.loadShow();
        var _uid = fnBase.huoqu(0, "uid");
        var _nickNameInput=$("#fillNick").val();
        var frontURL=Constant.URL+'/mobile/modify';
        var postData={
            "avatar":commersrc,
            "customerId":_uid,
            "nick":_nickNameInput,
            "sex":sexVal,
            "tel":$("#tel").val(),
            "idCard":$("#idCard").val(),
            "job":$("#job").val(),
            "education":$("#education").val(),
            "declaration":$("#declaration").val(),
            "hobby":$("#hobby").val()
        };

        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            if(data.customerId=="1"){
            	fnBase.loadHide();
                fnBase.myalert(data.content)
            }else{
                fnBase.myalert(data.content)
            }
        })
    });
    //退出
    $(".quit_btn").click(function(){
        sessionStorage.clear();
        localStorage.clear();
        setTimeout(function(){
            window.location.href="login.html";
        },300)
    });

    //
    function getData(){
        var _uid = fnBase.huoqu(0, "uid");
        if (_uid == null || _uid == "undefined" || _uid == "") {
            window.location.href = "login.html";
            return;
        }
        var frontURL=Constant.URL+'/mobile/detailsData?id='+_uid;
        var postData={};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            $("#avatar").attr('src',Constant.URL+'/images/'+data.details.avatar);
            $("#fillNick").val(data.details.nick);
            $("#Sex").text(data.details.sex);
            $("#tel").val(data.details.tel);
            $("#idCard").val(data.details.idCard);
            $("#job").val(data.details.job);
            $("#education").val(data.details.education);
            $("#declaration").val(data.details.declaration);
            $("#hobby").val(data.details.hobby);
        });

    }
    
    
    

});




