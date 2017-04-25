$(document).ready(function(){
    var cood='';
    var span='';
    var _uid = fnBase.huoqu(0, "uid");
    var _community=fnBase.huoqu(1,"community");
    var _startTime = fnBase.huoqu(1, "startTime");
    var _endTime=fnBase.huoqu(1,"endTime");
    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
    var _id = decodeURIComponent(fnBase.request("id"));
    var _price=fnBase.huoqu(1,"dayPrice");
    var _apartmenttype=fnBase.huoqu(1,"roomType");
    var _YJpic=fnBase.huoqu(1,"YJpic");

    $(".order_info .roomName").text(_community);
    $(".order_info .roomTime").html(_startTime+"��ס"+_endTime+"�뿪"+"<i class='date'>����"+_oTotalDay+"����</i>");
    $(".order_info .roompYJ").html("Ѻ��:<span style='color: #666'>"+_YJpic+"</span>");
    $(".order_info .roompPic").html("�����ܶ�:<span class='money'>��"+_oTotalPrice+"</span>");

    $("#needpic i").click(function(){
       if($(this).hasClass('active')==true){
           $(this).removeClass('active');
           cood=$(this).attr('cod')
       } else{
           $(this).addClass('active').siblings().removeClass('active');
           cood=$(this).attr('cod')
       }
    });

//    �ύ����
    $(".navbar a").click(function(){
        var _userName=$("#userName").val();
        var _tel=$("#tel").val();
        var _identity=$("#identity").val();
        var roonWz=fnBase.huoqu(1,'roonWz');
        var roomCX=fnBase.huoqu(1,'roomCX');
        var num_door=fnBase.huoqu(1,'num_door');
        var _description=_community+"-"+roonWz+"-"+roomCX+"-"+num_door+'';
        if(_userName==''){
            fnBase.myalert("��������ס������");
            return;
        }
        if(_tel==''){
            fnBase.myalert("��������ס�˵绰����");
            return;
        }
        if(_identity==''){
            fnBase.myalert("�����������֤��");
            return;
        }
        var frontURL=Constant.URL+'/mobile/modulePost';
        var postData={
            "roomId":_id,//����ID
            "cusId":_uid,//�û�ID
            "startTime":_startTime,//��סʱ��
            "endTime":_endTime,//�뿪ʱ��
            "totalDay":_oTotalDay,//������
            "totalPrice":_oTotalPrice,//�ܼ۸�
            "cusName":_userName,//��ס������
            "cusTel":_tel,//��ס�˵绰
            "cusIdCard":_identity,//��ס�����֤��
            "price":_price,//���쵥��
            "apartmentType":_apartmenttype,//��Դ����
            "needFapiiao":cood,//�費��Ҫ��Ʊ
            "preferential":span,
            "personal":$("#presonal").val(),
            "description":_description
        };
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
        })
    })

});