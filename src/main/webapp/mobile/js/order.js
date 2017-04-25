$(document).ready(function(){
    var _uid = fnBase.huoqu(0, "uid");
    var _community=fnBase.huoqu(1,"community");
    var _startTime = fnBase.huoqu(1, "startTime");
    var _endTime=fnBase.huoqu(1,"endTime");
    var _oTotalDay=fnBase.huoqu(1,"oTotalDay");
    var _oTotalPrice=fnBase.huoqu(1,"oTotalPrice");
    var _id = decodeURIComponent(fnBase.request("id"));
    var _price=fnBase.huoqu(1,"dayPrice");
    var _apartmenttype=fnBase.huoqu(1,"roomType");


    $(".order_info .roomName").text(_community);
    $(".order_info .roomTime").html(_startTime+"��ס"+_endTime+"�뿪"+"<i class='date'>����"+_oTotalDay+"����</i>");
    $(".order_info .roompYJ").html("Ѻ��:<span style='color: #666'>��280</span>");
    $(".order_info .roompPic").html("�����ܶ�:<span class='money'>��"+_oTotalPrice+"</span>");


//    �ύ����
    $(".navbar a").click(function(){
        //if (_uid == null || _uid == "undefined" || _uid == "") {
        //    window.location.href = "login.html";
        //    return;
        //}
        var _userName=$("#userName").val();
        var _tel=$("#tel").val();
        var _identity=$("#identity").val();
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
        var frontURL=Constant.URL+'/mobile/checkAvailable';
        var postData={
            "roomId":_id,//����ID
            "cusId":_uid,//�û�ID
            "startTime":_startTime,//��סʱ��
            "endTime":_endTime,//�뿪ʱ��
            "oTotalDay":_oTotalDay,//������
            "oTotalPrice":_oTotalPrice,//�ܼ۸�
            "cusName":_userName,//��ס������
            "cusTel":_tel,//��ס�˵绰
            "cusIdCard":_identity,//��ס�����֤��
            "dayPrice":_price,//���쵥��
            "apartmentType":_apartmenttype//��Դ����
        };
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            //window.location.href=""
        })
    })

});