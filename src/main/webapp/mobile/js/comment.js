
$(document).ready(function(){
    textarea();

    function textarea(){
            var $tex = $("#comment");
            var $but = $(".logout");
            var ie = jQuery.support.htmlSerialize;
            var str = 0;
            var abcnum = 0;
            var maxNum = 472;
            var texts= 0;
            var num = 0;
            var sets = null;
            $tex.val("");
            if(ie){
                $tex[0].oninput = changeNum;
            }else{
                $tex[0].onpropertychange = changeNum;
            }
            function changeNum(){
                str = ($tex.val().replace(/\w/g,"")).length;
                abcnum = $tex.val().length-str;
                total = str*2+abcnum;
                if(str*2+abcnum<maxNum || str*2+abcnum == maxNum){
                    texts =Math.ceil((maxNum - (str*2+abcnum))/2);
                    $("._p").html("<span>"+texts+"</span>").children().css({"color":"blue"});
                }else if(str*2+abcnum>maxNum){
                    texts =Math.ceil(((str*2+abcnum)-maxNum)/2);
                    $("_p").html("<span>"+texts+"</span>").children("span").css({"color":"red"});
                }
            }
    }
});