
$(document).ready(function(){
	var roomId=fnBase.request('id');
	var page="",count="";

	function getcomment(page){
	 $(".houseList ul").html('');
	 fnBase.loadShow();
     $.ajax({
    	 type:'get',
    	 data:{"roomId":roomId,"page":page},
    	 url:'/comment/getComments',
    	 dataType:'json',
    	 success:function(data){
    		 fnBase.loadHide();
    		 console.log(data)
    		 count=data[0].PageCount;
    		$(".page a:last-child").html('第:'+data[0].CurrentPage+'/'+data[0].PageCount+'页');
    		
    		 var str="";
    		 for(var i=1;i<data.length;i++){
    			 str+='<li><p>用户：'+data[i].nick+'<span style="float:right;color:orange">'+data[i].comment.score[i]+'星</span></p><p>内容：'+data[i].comment.feel+'</p><p>评论时间：'+data[i].comment.entryTime+'</p><p>操作：<a toid="'+data[i].comment.id+'">回复</a><a toid="'+data[i].comment.id+'">删除</a></p></li>'
    		 }
				if(data.length<=1){
					str='<p style="font-size:0.14rem;">暂无评论</p>';
					$(".page").hide();
				}
    		 $(".houseList ul").append(str)
    	 }
     })
     
	}
	
	$(".page a:nth-child(1)").click(function(){
		if(page>1){
			page--;
			 console.log(page)
			getcomment(page)
		}
	})
	$(".page a:nth-child(2)").click(function(){
		if(page<count){
			page++;
			console.log(page)
			getcomment(page)
		}
	})
	
	//回复
	$(".houseList ul").on('click','li a:nth-child(1)',function(){
		var id=$(this).attr('toid');
		$("#module-box,#maskShow").fadeIn();
		$("#contuse").attr('cid',id)
	})
	$("#contuse").click(function(){
		var textcon=$("#priceset").val();
		var id=$(this).attr('cid');
		if(textcon==null || textcon==""){
			fnBase.myalert('填写回复后才能提交！')
			return false
		}
		 var frontURL= baseUrl+'/comment/reply';
	        var postData={"reply":textcon,"id":id};
	        fnBase.commonAjax(frontURL,postData,function(data){
	        	console.log(data);
	        	fnBase.myalert(data.content)
	        	$("#module-box,#maskShow").fadeOut();
	        	$("#contuse").attr('cid','')
	        })
	})
	
	$(".module-footer a:nth-child(2)").click(function(){
		$("#module-box,#maskShow").fadeOut();
		$("#contuse").attr('cid','')
	})
	
	
	//删除评论
	$(".houseList ul").on('click','li a:last-child',function(){
		 var frontURL= baseUrl+'/comment/deleteComment';
	     var postData={"id": $(this).attr('toid')};
	        fnBase.commonAjax(frontURL,postData,function(data){
	        	console.log(data);
	        	fnBase.myalert(data.content)
	        	location=location
	        })

	})
	getcomment(1)
});