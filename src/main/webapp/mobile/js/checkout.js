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
               console.log(data);
               if(data.statusCode==1){
                   setTimeout(function(){
                	   fnBase.myalert('退房成功、押金在24小时内退还到你的账号上。')
                	   window.location.href = 'myorder.html?id=' + decodeURIComponent(fnBase.request("id"));
                   },2000)
                   
               }else{
            	   fnBase.myalert(data.content);
               }
           }
       })
   })
});