var baseUrl = getRootPath();//调试环境用这个
console.log(baseUrl);
//  var baseUrl = 'http://www.yiyunzn.xyz';//生成环境下用这个

var clientIp = getIp();

var Constant = {
       //URL: "http://192.168.1.108/",
        URL: baseUrl,
        CLIENT_IP:clientIp
};
/**
 * 获取ip
 */
function getIp() {
    var ip;
    var ipInfoUrl = 'http://ipinfo.io/json';
    $.ajax({
        url:ipInfoUrl,
        async:false,
        success:function (data) {
            ip = data.ip;
        }
    });
    return ip;
}

/**
 * 获取根目录
 * @returns {string}
 */
function getRootPath() {
    //获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/ems
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//    return(localhostPath + projectName);
    return(localhostPath);
}

$(document).ready(function(){
    menuStudent()
    function menuStudent(){
        //try {
        //    throw new Date(); // 抛出当前时间对象
        //} catch (e) {
        //    alert(e.toLocaleString()); // 使用本地格式显示当前时间
        //}
        $(".header-mobile .link-home").click(function(){
            $(".menuBar").addClass("menuOpen");
            $(".Masking-out").show();
        });
        $(".menuBar").find("li").click(function(){
            $('.menuBar').removeClass('menuOpen');
            $(".Masking-out").hide();
        });
        $(".Masking-out").click(function(){
            $('.menuBar').removeClass('menuOpen');
            setTimeout(function(){$(".Masking-out").hide()},500)
        });
    }

});

    var fnBase={
        loadShow:function(){
                $("<div class='black_loading' style='display: block'><div class='ldbg'><img src='images/loading.gif' ><p>正在载入，请稍后...</p></div></div>").appendTo($('body'))
        },
        
        loadHide:function(){
                $('.black_loading').remove();
        },
        commonAjax : function(url, data,fn){
            fnBase.loadShow();
            $.ajax({
                //cache : true,
                data : data,
                url : url,
                timeout:30000,
                dataType : "json",
                async : true,
                type : "POST",
                success : function(data) {
                    fnBase.loadHide();
                    fn(data);
                },
                error:function(e){
                    fnBase.loadHide();
                    fnBase.myalert("网络请求错误");
                }
            });
        },

        myalert:function(txt){
            var str='<div id="alertMC"><p>'+txt+'</p></div>';
            $("body").append(str);
            setTimeout(function(){$("#alertMC").remove();},1500)
        },
        keep:function(type,key,value){
            if(type==0){
                window.localStorage.setItem(key,value);
            }else if(type==1){
                window.sessionStorage.setItem(key,value);
            }
        },
        huoqu:function(type,key){
            if(type==0){
                return  window.localStorage.getItem(key);
            }else if(type==1){
                return  window.sessionStorage.getItem(key);
            }
        },
        request: function(name){
            var url = window.location.href;
            if(url){
                var valArray = url.split("?")[1];
                if(valArray && valArray.length >0){
                    var valArr = valArray.split("&");
                    if(valArr && valArr.length > 0){
                        for(var i in valArr){
                            if(valArr[i].split("=")[0] == name){
                                return valArr[i].split("=")[1];
                            }
                        }
                    }
                }
            }
        },
    };



