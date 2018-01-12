$(document).ready(function(){
    var totalPage='';
    var currentPage='';
	listpage()
	function listpage(){
		currentPage=1;
		 fnBase.loadShow();
		$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {
				'page' : currentPage
			},
			url : baseUrl+'/admin/get_customers',
			success:function(data){
				fnBase.loadHide();
				totalPage=data.pageCount
				console.log(data)
				var sp="";
				$("#usdlist").html('');
				for(var i=0;i<data.results.length;i++){
					sp+='<tr id="'+data.results[i].id+'"><td>'+data.results[i].id+'</td><td>'+data.results[i].regTime+'</td><td>'+data.results[i].consumptionCount+'</td><td class="look" uid="'+data.results[i].id+'">查看<img src="images/e4ea43876ce023b37be46f08fea84fcf.png"></td>';
				}
				$("#usdlist").append(sp);
				$(".page a:last-child").html('第'+data.currentPage+'/'+data.pageCount+'页')
				$(".page").show();
			}
		})
	}
    function queryForPages(currentPage){

        var frontURL= baseUrl+'/admin/get_customers';
        var postData={"page":currentPage};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            var sp="";
			$("#usdlist").html('');
			for(var i=0;i<data.results.length;i++){
				sp+='<tr id="'+data.results[i].id+'"><td>'+data.results[i].id+'</td><td>'+data.results[i].regTime+'</td><td>'+data.results[i].consumptionCount+'</td><td class="look" uid="'+data.results[i].id+'">查看<img src="images/e4ea43876ce023b37be46f08fea84fcf.png"></td>';
			}
			$("#usdlist").append(sp);
			$(".page a:last-child").html('第'+data.currentPage+'/'+data.pageCount+'页')
        });
     
    }
	
	
	$(".page a:first-child").click(function(){
        if(currentPage>1){
            currentPage--;
            queryForPages(currentPage);
        }
	})
	$(".page a:first-child+a").click(function(){
        if(currentPage<totalPage){
            currentPage++;
            queryForPages(currentPage);
        }
	})

	
	$(".layer-select .layer-nav").eq(0).on('change',function(){
		    var data=$(".layer-select .layer-nav:first-child option:selected").attr('tid');
		    if(data=='3'){
		    	listpage();
		    }else{
				var frontURL= baseUrl+'/admin/dsendlist';
		        var postData={"time":data};
		        fnBase.commonAjax(frontURL,postData,function(data){
		            console.log(data);
		            var sp="";
					$("#usdlist").html('');
					for(var i=0;i<data.content.length;i++){
						sp+='<tr id="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].regTime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'">查看<img src="images/e4ea43876ce023b37be46f08fea84fcf.png"></td>';
					}
					if(data.content.length<=0){
						sp='<tr><td colspan="4"><p>没有满足条件的用户</p></td></tr>'
					}
					$("#usdlist").append(sp);
					 $(".page").hide();
		        });
		    }

	})
	
	$(".layer-select .layer-nav").eq(1).on('change',function(){
			var data=$(".layer-select .layer-nav:first-child+.layer-nav option:selected").attr('value');
			if(data=='0'){
				listpage();
			}else{
				var frontURL= baseUrl+'/admin/dsendlist';
		        var postData={"money":data};
		        fnBase.commonAjax(frontURL,postData,function(data){
		            console.log(data);
		            var sp="";
					$("#usdlist").html('');
					for(var i=0;i<data.content.length;i++){
						sp+='<tr id="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].regTime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'">查看<img src="images/e4ea43876ce023b37be46f08fea84fcf.png"></td>';
					}
					if(data.content.length<=0){
						sp='<tr><td colspan="4"><p>没有满足条件的用户</p></td></tr>'
					}
					$("#usdlist").append(sp);
					 $(".page").hide();
		        });
		       
			}

	})
	
	
	$(".layer-select .layer-nav").eq(2).on('change',function(){
			var data=$(".layer-select .layer-nav:last-child option:selected").val();
			if(data=='全部'){
				listpage();
			}else{
				var frontURL= baseUrl+'/admin/dsendlist';
		        var postData={"sex":data};
		      
		        fnBase.commonAjax(frontURL,postData,function(data){
		            console.log(data);
		            var sp="";
					$("#usdlist").html('');
					for(var i=0;i<data.content.length;i++){
						sp+='<tr id="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].regTime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'">查看<img src="images/e4ea43876ce023b37be46f08fea84fcf.png"></td>';
					}
					if(data.content.length<=0){
						sp='<tr><td colspan="4"><p>没有满足条件的用户</p></td></tr>'
					}
					$("#usdlist").append(sp);		
					 $(".page").hide();
		        });
		       
			}

	})	
	
	function determine(val){
		if(val=="" || val== null || val=='undefined'){
			return  val='未知';
		}else{
			return	val=val;
		}
	}
	
	$(".houseList table").on('click','tr .look',function(){
		var data=$(this).attr('uid');
		$('#module-box').addClass('hover');
		var frontURL= baseUrl+'/mobile/detailsData?id='+data;
        var postData={};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            $('.module-con .icon img').attr('src',data.details.avatar);
            $(".contentText p:nth-child(1)").html('<span>昵称：</span>'+data.details.nick+'');
            $(".contentText p:nth-child(2)").html('<span>姓名：</span>'+determine(data.details.name)+'&nbsp;&nbsp;&nbsp;&nbsp;<span>性别：</span>'+determine(data.details.sex));
            $(".contentText p:nth-child(3)").html('<span>手机：</span>'+determine(data.tel)+'');
            $(".contentText p:nth-child(4)").html('<span>身份证：</span>'+determine(data.details.idCard)+'');
            $(".contentText p:nth-child(5)").html('<span>关注时间：</span>'+determine(new Date( data.regTime ).toLocaleString())+'');
            $(".contentText p:nth-child(6)").html('<span>工作：</span>'+determine(data.details.job)+'');
            $(".contentText p:nth-child(7)").html('<span>教育：</span>'+determine(data.details.education)+'');
            $(".contentText p:nth-child(8)").html('<span>爱好：</span>'+determine(data.details.hobby)+'');
        })
	})
	
	$(".layer-setwin a").click(function(){
		$("#module-box").removeClass('hover')
	})
	
	
})