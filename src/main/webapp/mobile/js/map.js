
$(document).ready(function(){
    var frontURL=Constant.URL+'/mobile/homeTherefore';
    var postData={};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        var jd,wd,wz1,wz2,jiage,url;
        for(var i=0;i<data.homeRooms.length;i++){
            jd=data.homeRooms[i].position.jing_du;
            wd=data.homeRooms[i].position.wei_du;
            wz1=data.homeRooms[i].position.xa_wei_zhi;
            wz2=data.homeRooms[i].position.xa_wei_zhi+"-"+data.homeRooms[i].position.xiao_qu;
            wz3=data.homeRooms[i].position.bd_wei_zhi;
            jiage=data.homeRooms[i].basic_info.jia_ge;
            url='house_details.html?id='+encodeURIComponent(data.homeRooms[i].id);
            loadwz(jd,wd,wz2,wz3,jiage,url);
        }
    });
});