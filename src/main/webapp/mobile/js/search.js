$(document).ready(function(){
    $("#closebtn").click(function(){
        window.location.href="javascript:history.back(-1);";
    });

    var frontURL=Constant.URL+'api/util?code=204';
    fnBase.commonAjax(frontURL,{'param':''},function(data){
        console.log(data);
        if(data.status=="1"){
            $(".hotSearch ul").html("");
            var str='';
            for(var i=0;i<data.info.length;i++){
                str+='<li><a href="javascript:void(0)">'+data.info[i]+'</a></li>'
            }
            $(".hotSearch ul").append(str);
            var _key='';
            $(".hotSearch ul li a").click(function(_key){
                _key=$(this).text();
                gotoListPage(_key);
            });
            $("#searchTo").click(function(){
               _key=$(".serchText").val();
                gotoListPage(_key);
            });
            function gotoListPage(_key){
                if(fnBase.huoqu(1,"mode")==0){
                    window.location.href="house_particulars.html?key="+encodeURIComponent(_key);
                }else{
                    window.location.href="scanStore.html?key="+encodeURIComponent(_key)+"&shopid="+fnBase.huoqu(1,"othershoipid");
                }
            }

        }else{
            fnBase.myalert(data.info)
        }

    })


});
