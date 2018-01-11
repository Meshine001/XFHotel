$(document).ready(function(){
	// 优惠卷统计
	$.ajax({
			type : 'POST',
			dataType : 'json',
			data : {},
			url:"/admin/Coupon",
			success :function(data){
				$(".statistics p").eq(0).find('span').text(data.money);
				$(".statistics p").eq(1).find('span').text(data.sumTotal);
				$(".statistics p").eq(2).find('span').text(data.used);
				$(".statistics p").eq(3).find('span').text(data.unused);
				$(".statistics p").eq(4).find('span').text(data.stale);
				$(".statistics p").eq(5).find('span').text(data.usedMoney);
			
			}
	})
	
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
					sp+='<tr id="'+data.results[i].id+'"><td>'+data.results[i].id+'</td><td>'+data.results[i].regTime+'</td><td>'+data.results[i].consumptionCount+'</td><td class="look" uid="'+data.results[i].id+'"><i></i></td>';
				}
				$("#usdlist").append(sp);
				$(".page a:last-child").html('第'+data.currentPage+'/'+data.pageCount+'页')
			
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
				sp+='<tr id="'+data.results[i].id+'"><td>'+data.results[i].id+'</td><td>'+data.results[i].regTime+'</td><td>'+data.results[i].consumptionCount+'</td><td class="look" uid="'+data.results[i].id+'"><i></i></td>';
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
	
	
	$(".houseList table").on('click','tr',function(){
		if($(this).hasClass('_active')==true){
			$(this).removeClass('_active')
		}else{
			$(this).addClass('_active')
		}
	})
	
	//条件搜索
	 $("#longtime").on('change',function(){
		 fnBase.loadShow();
	
	 $.ajax({
			type:'POST',
			dataType:'json',
			data:{'time':$("#longtime option:selected").attr('tid')},
			url: "/admin/dsendlist",
			success:function(data){
				fnBase.loadHide();
			//	console.log(data);
				var str='';
				var newTime=new Array();
				$("#usdlist").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr><td>'+data.content[i].id+'</td><td>'+istime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'"><i></i></td></tr>'
				}
				if(data.content.length<=0){
					str='<tr><td colspan="4">没有符合条件的用户</td></tr>'
				}
				$("#usdlist").append(str);

				$(".page").hide();
			}
		})
		if($("#longtime option:selected").attr('tid')=='3'){
			listpage(1)
			setTimeout(function(){
					$(".page").show();
			},1000)
		}
		
	 });
	
	
	 $("#monetary").on('change',function(){
		 fnBase.loadShow();
		  $.ajax({
				type:'POST',
				dataType:'json',
				data:{'money':$("#monetary option:selected").val()},
				url: "/admin/dsendlist",
				success:function(data){
					fnBase.loadHide();
			//		console.log(data);
					var str='';
					var newTime=new Array();
					$("#usdlist").html('');
					for(var i=0;i<data.content.length;i++){
						var infotime=data.content[i].regTime;
						var j = new Date(infotime);
						var istime=j.toLocaleDateString()
						    newTime.push(istime);
						str+='<tr><td>'+data.content[i].id+'</td><td>'+istime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'"><i></i></td></tr>'
					}
					if(data.content.length<=0){
						str='<tr><td colspan="4">没有符合条件的用户</td></tr>'
					}
					$("#usdlist").append(str);

					$(".page").hide();
				}
			})
			if($("#monetary option:selected").attr('value')=='0'){
				listpage(1)
				setTimeout(function(){
						$(".page").show();
				},1000)
			}
	});
	
	 
	 $("#sex").on('change',function(){
		 fnBase.loadShow();
	       $.ajax({
			type:'POST',
			dataType:'json',
			data:{'sex':$("#sex option:selected").val()},
			url: "/admin/dsendlist",
			success:function(data){
				fnBase.loadHide();
		//		console.log(data);
				var str='';
				var newTime=new Array();
				$("#usdlist").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr><td>'+data.content[i].id+'</td><td>'+istime+'</td><td>'+data.content[i].consumptionCount+'</td><td class="look" uid="'+data.content[i].id+'"><i></i></td></tr>'
				}
				if(data.content.length<=0){
					str='<tr><td colspan="4">没有符合条件的用户</td></tr>'
				}
				$("#usdlist").append(str);

				$(".page").hide();

			}
		})
		if($("#sex option:selected").attr('value')=='全部'){
			listpage(1)
			setTimeout(function(){
					$(".page").show();
			},1000)
		}
		
	});	 
	 
	
	 
	//发送优惠卷 
	
	var strlist2="" 
	var strlist=new Array();	
	$(".layer-select .layer-nav:last-child").click(function(){
		   
			var obj=$("#usdlist tr");
			
			for(var i=1;i<obj.length;i++){
				if(obj.eq(i).hasClass('_active')==true){
					strlist.push(obj.eq(i).attr('id'))
				}	
			}
			strlist2=strlist.join(",")
			console.log(strlist2)
			if(strlist.length=='0'){
				fnBase.myalert('选择要发优惠卷的用户才可以下一步呦！')
				return;
			}else{
				$("#maskShow,#module-box").fadeIn();
			}
		   return strlist2;
		
	}) 
	 
	$(".module-footer a:last-child").click(function(){
		strlist=[];
		$("#usdlist tr").removeClass('_active')
		$("#maskShow,#module-box").fadeOut();
	})
	
	$("#keepbtn").click(function(){
	
		var postdata={
				'Id':strlist2,
				"cValue":$('#pnumber').val(),
				"type":$("#pId").val(),
				"startTime":$("#mydatepicker2").val(),
				"endTime":$("#mydatepicker3").val(),
				"rule":$("#nnumber2").val()
		}
		$("#maskShow,#module-box").fadeOut();
		console.log(postdata)
		$.ajax({		
			type : 'POST',
			dataType : 'json',
			data : postdata,
			url:"/admin/sendlist",
			success :function(data){
				console.log(data)
				if(data.statusCode == 1){
					fnBase.myalert(data.content);
					setTimeout(function(){location=location;},1550)
				}else{
					fnBase.myalert(data.content);
				}
			}
		})
	})
	
	
})