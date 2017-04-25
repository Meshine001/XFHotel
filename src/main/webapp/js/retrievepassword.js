$(document).ready(function(){
    resiger.addEvent();
});

var resiger={
    inputList:new Array(),
    btnList:new Array(),
    addEvent:function(){
        resiger.inputList=[];
        resiger.btnList=[];
        resiger.inputList.push($("#login-phone"),$("#login-password"),$("#repwd"),$("#yzcode"));
        resiger.btnList.push($("#fetch-cmd"),$("#login_submit"));

        //������֤��
        resiger.btnList[0].click(function(){
            var phoneNumber=resiger.inputList[0].val();
            if(phoneNumber==""){
                fnBase.myalert("����д�ֻ�����");
                return;
            }
            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            if (!myreg.test(phoneNumber)) {
                fnBase.myalert("�ֻ��������� ������11λ����");
                return;
            }
            var frontURL=Constant.URL+"/mobile/sendVCode";
            var postData={"tel":phoneNumber};
            fnBase.commonAjax(frontURL,postData,function(data){
                console.log(data);
                if(data.statusCode=="1"){
                    fnBase.myalert("���ŷ��ͳɹ�");
                }else{
                    fnBase.myalert("���ŷ���ʧ��")
                }
            });
            resiger.timePrompt();
        });


        resiger.inputList[3].blur(function(){
            var phoneNumber=resiger.inputList[0].val();
            var _yzm=resiger.inputList[3].val();
            if(_yzm==""){
                fnBase.myalert("����д��֤��");
                return;
            }
            var frontURL=Constant.URL+"/mobile/checkVCode";
            var postDatat={"vCode":_yzm,"tel": phoneNumber};
            fnBase.commonAjax(frontURL,postDatat,function(data){
                console.log(data);
                if(data.statusCode=="1"){
                    resiger.inputList[3].val(data.content);
                }else{
                    resiger.inputList[3].val(data.content);
                }
            });
        });
        resiger.btnList[1].click(function(){
            var phoneNumber=resiger.inputList[0].val();
            if(phoneNumber==""){
                fnBase.myalert("����д�ֻ�����");
                return;
            }
            var myreg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            if (!myreg.test(phoneNumber)) {
                fnBase.myalert("�ֻ��������� ������11λ����");
                return;
            }
            var _yzm=resiger.inputList[3].val();
            if(_yzm==""){
                fnBase.myalert("����д��֤��");
                return;
            }
            var password=resiger.inputList[1].val();
            if(password==""){
                fnBase.myalert("����д����");
                return;
            }
            if(password.length<6){
                fnBase.myalert("����������6λ");
                return;
            }
            var repwd=resiger.inputList[2].val();
            if(repwd==""){
                fnBase.myalert("���ٴ���д����");
                return;
            }
            if(repwd!=password){
                fnBase.myalert("������������벻һ��");
                return;
            }



            //
            var frontURL=Constant.URL+"/mobile/reg";
            var postDatat={"tel":phoneNumber,"password":password};
            fnBase.commonAjax(frontURL,postDatat,function(data){
                console.log(data);
                if(data.statusCode=="1"){
                    sessionStorage.clear();
                    localStorage.clear();
                    fnBase.keep(0,"uid",data.info.m_id);
                    window.location.href="login.html";
                }else{
                    fnBase.myalert(data.content)
                }
            })



        })

    },
    timePrompt:function(){
        var total=60;
        var mytimecont;
        resiger.btnList[0].text("60����ط�");
        clearInterval(mytimecont);
        mytimecont=setInterval(function(){
            total=total-1;
            var str=total+"����ط�";
            resiger.btnList[0].text(str);
            if(total<=0){
                clearInterval(mytimecont);
                resiger.sendCoded=false;
                resiger.btnList[0].text("������֤��");
            }
        },1000)
    }
};

