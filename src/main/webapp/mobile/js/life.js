
$(document).ready(function(){

    var _id = decodeURIComponent(fnBase.request("id"));
    var frontURL=Constant.URL+'/mobile/blog_content';
    var postData={"id":_id};
    fnBase.commonAjax(frontURL,postData,function(data){
        console.log(data);
        $("#content").html();
        var dataCon='<h3 class="my-title">'+data.title+'</h3><p class="f12">'+data.date+'</p><p class="ptxt">'+data.content+'</p>'
        $("#content").append(dataCon)
    })
});