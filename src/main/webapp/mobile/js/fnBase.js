    var baseUrl = getRootPath();//调试环境用这个

    // var baseUrl = 'http://www.yiyunzn.xyz';//生成环境下用这个

   
    
var Constant = {
        URL: baseUrl, 
};

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
    menuButnav();
    function menuButnav(){
        $("#bottomMenu li:eq(0)").click(function(){
        	window.location.href="index.html"
        });
        $("#bottomMenu li:eq(1)").click(function(){
        	window.location.href="Lifelist.html"
        });
        $("#bottomMenu li:eq(2)").click(function(){
        	window.location.href="listings.html"
        });
        $("#bottomMenu li:eq(3)").click(function(){
        	window.location.href="orderlist.html"
        });
        $("#bottomMenu li:eq(4)").click(function(){
        	window.location.href="serve.html"
        });
    }
    
    

    
    
    
});
//$(".link-home").attr("href","javascript:void(0);").click(function(){
//    if (/(iPhone|iPad|iPod)/i.test(navigator.userAgent)) {
//        window.location.href = window.document.referrer;
//    } else { window.history.go("-1"); }
//});

    var fnBase={
        loadShow:function(){
                $("<div class='black_loading' style='display: block'><div class='ldbg'><span class='scroll100'></span><p>正在载入，请稍后...</p></div></div>").appendTo($('body'))
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
            setTimeout(function(){$("#alertMC").remove();},1000)
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
	  	   //乘法计算
        accMul:function(arg1, arg2) {
            var m = 0,
                s1 = arg1.toString(),
                s2 = arg2.toString();
            try {
                m += s1.split(".")[1].length
            } catch (e) {}

            try {
                m += s2.split(".")[1].length
            } catch (e) {}

            return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)

        },
      //加法计算
        accAdd:function (arg1, arg2) {
            var r1, r2, m, c;
            try {
                r1 = arg1.toString().split(".")[1].length
            } catch (e) {
                r1 = 0
            }

            try {
                r2 = arg2.toString().split(".")[1].length
            } catch (e) {
                r2 = 0
            }

            c = Math.abs(r1 - r2);
            m = Math.pow(10, Math.max(r1, r2))
            if (c > 0) {
                var cm = Math.pow(10, c);
                if (r1 > r2) {
                    arg1 = Number(arg1.toString().replace(".", ""));
                    arg2 = Number(arg2.toString().replace(".", "")) * cm;
                } else {
                    arg1 = Number(arg1.toString().replace(".", "")) * cm;
                    arg2 = Number(arg2.toString().replace(".", ""));
                }
            } else {
                arg1 = Number(arg1.toString().replace(".", ""));
                arg2 = Number(arg2.toString().replace(".", ""));
            }
            return (arg1 + arg2) / m;
        },


    };



