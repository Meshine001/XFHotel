
$(document).ready(function(){
    getData();
    var sexVal='';
    var _id=fnBase.huoqu(0,'uid');
    var _status=fnBase.huoqu(0,'status');
    console.log(_status);
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
                $("#avatar").attr('src',data.content);
            },
            error: function (data) {
                console.log(data);
            }
        });
    });




    //~~~~~~~~~~~~~~~~~~~~~~~~~
//==================8.16=====================================

    $("#IPlocad").click(function(){
    	if(_status==0){
    		$("#main_con,#masking").show();
    	}else if(_status==1){
    		fnBase.myalert('您已认证、')
    	}
    	
    })
    	
	$("#black_houme").click(function(){
		$("#masking,#main_con").hide();
	});
	var resiger={
		    inputList:new Array(),
		    btnList:new Array(),
		    addEvent:function(){
		        resiger.inputList=[];
		        resiger.btnList=[];
		        resiger.inputList.push($("#tell"),$("#password"),$("#yzm"));
		        resiger.btnList.push($("#fetch-cmd"),$("#login_submit"));

		        //短信验证码
		        resiger.btnList[0].click(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            if(phoneNumber==""){
		                fnBase.myalert("请填写手机号码");
		                return;
		            }
		            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
		            if (!myreg.test(phoneNumber)) {
		                fnBase.myalert("手机号码有误！ 请输入11位数字");
		                return;
		            }
		            var frontURL=Constant.URL+"/mobile/sendVCode";
		            var postData={"tel":phoneNumber};
		            fnBase.commonAjax(frontURL,postData,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                    fnBase.myalert("短信发送成功");
		                }else{
		                    fnBase.myalert("短信发送失败")
		                }
		            });
		            resiger.timePrompt();
		        });


		        resiger.inputList[2].blur(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            var _yzm=resiger.inputList[2].val();
		            if(_yzm==""){
		                fnBase.myalert("请填写验证码");
		                return;
		            }
		            var frontURL=Constant.URL+"/mobile/checkVCode";
		            var postDatat={"vCode":_yzm,"tel": phoneNumber};
		            fnBase.commonAjax(frontURL,postDatat,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                    resiger.inputList[2].val(data.content);
		                }else{
		                    resiger.inputList[2].val(data.content);
		                }
		            });
		        });
		        //确认保存;
		        resiger.btnList[1].click(function(){
		            var phoneNumber=resiger.inputList[0].val();
		            if(phoneNumber==""){
		                fnBase.myalert("请填写手机号码");
		                return;
		            }
		            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
		            if (!myreg.test(phoneNumber)) {
		                fnBase.myalert("手机号码有误！ 请输入11位数字");
		                return;
		            }
		            var _yzm=resiger.inputList[2].val();
		            if(_yzm==""){
		                fnBase.myalert("请填写验证码");
		                return;
		            }
		            var password=resiger.inputList[1].val();
		            if(password==""){
		                fnBase.myalert("请填写密码");
		                return;
		            }
		            if(password.length<6){
		                fnBase.myalert("密码至少是6位");
		                return;
		            }
		
		            var frontURL=Constant.URL+"/mobile/find1";
		            var postDatat={"tel":phoneNumber,"psd":password,'id':_id};
		            fnBase.commonAjax(frontURL,postDatat,function(data){
		                console.log(data);
		                if(data.statusCode=="1"){
		                	$("#main_con,#masking").hide();
		                	fnBase.myalert(data.content);
		                }else{
		                    fnBase.myalert(data.content)
		                }
		                $("#main_con,#masking").hide();
		                window.location.href = "index.html";
		            })



		        })

		    },
		    timePrompt:function(){
		        var total=60;
		        var mytimecont;
		        resiger.btnList[0].text("60秒后重发");
		        clearInterval(mytimecont);
		        mytimecont=setInterval(function(){
		            total=total-1;
		            var str=total+"秒后重发";
		            resiger.btnList[0].text(str);
		            if(total<=0){
		                clearInterval(mytimecont);
		                resiger.sendCoded=false;
		                resiger.btnList[0].text("短信验证码");
		            }
		        },1000)
		    }
		};
	
	
	
    resiger.addEvent();
    


//========================================================
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
        console.log(postData)
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            fnBase.myalert(data.content)
            setTimeout(function(){
                window.location.href="serve.html";
            },300)
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
            $("#avatar").attr('src',data.details.avatar);
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




