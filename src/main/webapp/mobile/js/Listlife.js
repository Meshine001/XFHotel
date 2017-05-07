
$(document).ready(function(){
    getdata();
    var PageUp=$("#page a").eq(0);
    var PageDown=$("#page a").eq(1);
    var totalPage='';
    var currentPage='';

    function getdata(){
        currentPage='1';
        var frontURL=Constant.URL+'/mobile/story';
        var postData={"page":currentPage};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            currentPage=data.sp;
            totalPage=data.ep;
            var str='';
            $(".content-ul").html('');
            for(var i=0;i<data.blogs.results.length;i++){
                str+='<li link="'+data.blogs.results[i].id+'"><div class="pic"><img src="'+data.blogs.results[i].pic+'"></div><h3 class="_title">'+data.blogs.results[i].title+'</h3></li>';
            }
            $(".content-ul").append(str);
            var len = $('.content-ul li').length;
            if(len==0){
                $('#zanwu').show();
                $("#Myscroll-body").css('background','#FFF')
                //$('body').addClass('wingBg');
            }
        });
    }

    function queryForPages(){

        var frontURL=Constant.URL+'/mobile/story';
        var postData={"page":currentPage};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            var str='';
            $(".content-ul").html('');
            for(var i=0;i<data.blogs.results.length;i++){
                str+='<li link="'+data.blogs.results[i].id+'"><div class="pic"><img src="'+data.blogs.results[i].pic+'"></div><h3 class="_title">'+data.blogs.results[i].title+'</h3></li>';
            }
            $(".content-ul").append(str);
        });
        fnBase.keep(0,"id",data.blogs.results[i].id)
    }

    PageUp.click(function(){
        if(currentPage>1){
            currentPage--;
            console.log(currentPage);
            queryForPages();
        }
        console.log(currentPage)
    });
    PageDown.click(function(){
        if(currentPage<totalPage){
            currentPage++;
            console.log(currentPage);
            queryForPages();
        }

    });

    $(".content-ul li").live('click',function(){
        var _page=$(this).attr("link");
        window.location.href='Life.html?id='+encodeURIComponent(_page);
    });


});