
$(document).ready(function(){
	 var userid,wechatOpenId="",_status='0',ss="0",uid="";
	 var redirect = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxfa31f9e4951f95df&redirect_uri=http%3A%2F%2Fwww.yiyunzn.xyz%2Fwx%2Fauth%2Fautomatic&response_type=code&scope=snsapi_userinfo&state=Listlife.html#wechat_redirect';
	    
  	 uid=fnBase.request("id");
  	 userid=fnBase.huoqu(0,'userid');
	 
	 if(userid==null || userid==undefined || userid==""){
		 if(uid==null || uid==undefined ||uid==''){
			  window.location.href = redirect; 	 
			  return; 
		 }else{
			 userid=fnBase.request("id")
			 userid=fnBase.keep(0,'userid',userid);
		 }
	 };
	
	
	
    getdata();
    var PageUp=$("#page a").eq(0);
    var PageDown=$("#page a").eq(1);
    var totalPage='';
    var currentPage='';

    function getdata(){
    	   fnBase.loadShow();
        currentPage='1';
        var frontURL=Constant.URL+'/mobile/story';
        var postData={"page":currentPage};
        fnBase.commonAjax(frontURL,postData,function(data){
        	fnBase.loadHide();
            console.log(data);
            currentPage=data.sp;
            totalPage=data.ep;
            var str='';
            $(".content-ul").html('');
            for(var i=0;i<data.blogs.results.length;i++){
                str+='<li link="'+data.blogs.results[i].id+'"><div class="pic"><img src="'+Constant.URL+ data.blogs.results[i].pic+'"><p class="mask">'+data.blogs.results[i].title+'</p></div><div class="content-mob"><p>'+data.blogs.results[i].abs_text+'</p><img src="'+Constant.URL+ data.blogs.results[i].pic+'"></div></li>';
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
            	str+='<li link="'+data.blogs.results[i].id+'"><div class="pic"><img src="'+Constant.URL+ data.blogs.results[i].pic+'"><p class="mask">'+data.blogs.results[i].title+'</p></div><div class="content-mob"><p>'+data.blogs.results[i].abs_text+'</p><img src="'+Constant.URL+ data.blogs.results[i].pic+'"></div></li>';
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