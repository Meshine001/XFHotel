var Constant = {
    URL: "http://192.168.1.109:8080/hotel"
};
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

});

    //var shebei_type=0;//0网页 1安卓 2ios
    //var load_flag = false; // 数据记载标识 数据未加载
    var fnBase={
        loadShow:function(){
                $("<div class='black_loading' style='display: block'><div class='ldbg'><img src='images/loading.gif' ><p>正在载入，请稍后...</p></div></div>").appendTo($('body'))
        },
        loadHide:function(){
                $('.black_loading').remove();
        },
        commonAjax : function(url, data,fn){
            //fnBase.loadShow();
            $.ajax({
                cache : true,
                data : data,
                url : url,
                timeout:30000,
                dataType : "json",
                async : true,
                type : "post",
                success : function(data) {
                    fnBase.loadHide();
                    fn(data);
                },
                error:function(e){
                    fnBase.loadHide();
                    //fnBase.myalert("网络错误");
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



