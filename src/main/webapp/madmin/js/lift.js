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
			url : baseUrl+'/admin/blog/get_blogs',
			success:function(data){
				console.log(data)
				fnBase.loadHide();
				totalPage=data.pageCount

				var sp="";
				$("#usdlist").html('');
				for(var i=0;i<data.results.length;i++){
					sp+='<tr id="'+data.results[i].id+'"><td>'+new Date( data.results[i].date ).toLocaleString()+'</td><td>'+data.results[i].title+'</td><td class="look" uid="'+data.results[i].id+'"><a>编辑</a><a>删除</a></td>';
				}
				$("#usdlist").append(sp);
				$(".page a:last-child").html('第'+data.currentPage+'/'+data.pageCount+'页')
			
			}
		})
	}
    function queryForPages(currentPage){

        var frontURL= baseUrl+'/admin/blog/get_blogs';
        var postData={"page":currentPage};
        fnBase.commonAjax(frontURL,postData,function(data){
            console.log(data);
            var sp="";
			$("#usdlist").html('');
			for(var i=0;i<data.results.length;i++){
				sp+='<tr id="'+data.results[i].id+'"><td>'+new Date( data.results[i].date ).toLocaleString()+'</td><td>'+data.results[i].title+'</td><td class="look" uid="'+data.results[i].id+'"><a>编辑</a><a>删除</a></td>';
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



	

	
	function determine(val){
		if(val=="" || val== null || val=='undefined'){
			return  val='未知';
		}else{
			return	val=val;
		}
	}
	
//	$(".houseList table").on('click','tr .look',function(){
//		var data=$(this).attr('uid');
//		$('#module-box').addClass('hover');
//		var frontURL= baseUrl+'/mobile/detailsData?id='+data;
//        var postData={};
//        fnBase.commonAjax(frontURL,postData,function(data){
//            console.log(data);
//            $('.module-con .icon img').attr('src',data.details.avatar);
//            $(".contentText p:nth-child(1)").html('<span>昵称：</span>'+data.details.nick+'');
//            $(".contentText p:nth-child(2)").html('<span>姓名：</span>'+determine(data.details.name)+'&nbsp;&nbsp;&nbsp;&nbsp;<span>性别：</span>'+determine(data.details.sex));
//            $(".contentText p:nth-child(3)").html('<span>手机：</span>'+determine(data.tel)+'');
//            $(".contentText p:nth-child(4)").html('<span>身份证：</span>'+determine(data.details.idCard)+'');
//            $(".contentText p:nth-child(5)").html('<span>关注时间：</span>'+determine(new Date( data.regTime ).toLocaleString())+'');
//            $(".contentText p:nth-child(6)").html('<span>工作：</span>'+determine(data.details.job)+'');
//            $(".contentText p:nth-child(7)").html('<span>教育：</span>'+determine(data.details.education)+'');
//            $(".contentText p:nth-child(8)").html('<span>爱好：</span>'+determine(data.details.hobby)+'');
//        })
//	})
	$(".layer-select .layer-nav").click(function(){
		$("#module-box").addClass('hover')
	})
	
	$(".layer-setwin a").click(function(){
		$("#module-box").removeClass('hover')
	})
	console.log(baseUrl)
	var editor = new wangEditor('div_editor');


	editor.config.uploadImgUrl = baseUrl+"/admin/blog/upload_blog_pic";
	editor.config.uploadParams = {
	};
	editor.config.uploadHeaders = {
	    'Accept' : 'text/x-json'
	};
	editor.config.hideLinkImg = true;
	 editor.config.uploadImgFileName = 'myFileName'; 
		
	editor.create();
	$('#submit').click(function() {
		if($('#title').val()==null || $('#title').val()=="" || $('#title').val()==undefined ){
			fnBase.myalert('陛下，您还没填写内容不能发布！')
			return;
		}
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : 'json',
			data : {
				'title' : $('#title').val(),
				'content' : editor.$txt.html(),
				'txt' : editor.$txt.formatText()
			},
			url : baseUrl+"/admin/blog/publish",
			error : function() {//请求失败处理函数
				alert("发布失败！");
			},
			success : function(data) {
				console.log(data)
				fnBase.myalert('发布成功')
				setTimeout(function(){location=location;},1550)
			}
		});
	});	
	
	$(".houseList table").on('click',' tr .look a:last-child',function(){
		var frontURL=baseUrl+"/admin/blog/delete_blog";
	    var postData={'id': $(this).parent().attr('uid')};
		fnBase.myalert('删除成功')
		setTimeout(function(){
			location=location
    	}, 1800);
		
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url:frontURL,
			data:postData,
			success:function(data){

			}
		})
	})
	
	$(".houseList table").on('click',' tr .look a:first-child',function(){
		var frontURL=baseUrl+"/admin/blog/update";
	    var postData={'id': $(this).parent().attr('uid')};
	    window.location.href="editlift.html?id="+$(this).parent().attr('uid');
//		fnBase.myalert('删除成功')
//		setTimeout(function(){
//			location=location
//    	}, 1800);
//		
//		$.ajax({
//			type : 'POST',
//			dataType : 'json',
//			url:frontURL,
//			data:postData,
//			success:function(data){
//
//			}
//		})
	})
	
	
})