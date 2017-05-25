/**
 * Created by Administrator on 2017/5/25 0025.
 */
$(document).ready(function(){
   $(".navbar a").click(function(){
       var _id = decodeURIComponent(fnBase.request("id"));
       $.ajax({
           type:'POST',
           dataType:'json',
           data:{"orderId":_id},
           url:Constant.URL+'/mobile/outLease',
           success:function(data){
               console.log(data)
               if(data.statusCode==1){
                   fnBase.myalert(data.content);
                   window.location.href = 'myorder.html?id=' + decodeURIComponent(fnBase.request("id"));
               }
           }
       })
   })
});