
var ID=window.sessionStorage.getItem('roomId');
	function list(page) {
		
		$.ajax({
			type : 'get',
			dataType : 'json',
			url:'/comment/getComments',
			data : {
				'roomId' : ID,
				'page':page
			},
			error : function(e) {
				alert("获取数据失败！");
			},
			success : function(data) {
				console.log(data);
				
				$('#pagecontroller').html('');
				var a_f = $('<a></a>').attr('href','#').append('首页').attr('onclick', 'list(1)').attr('href',
				'javascript:void(0);');
				var li_f = $('<li></li>').append(a_f);
				$('#pagecontroller').append(li_f);
				for (var i=1;i<=data[0].PageCount;i++){
					var a_p = $('<a></a>').attr('href','#').append(i).attr('onclick', 'list(' + i + ')')
					.attr('href', 'javascript:void(0);');
					var li_p = $('<li></li>').append(a_p);
					if(i==data[0].CurrentPage)
						li_p.attr('class','active');
					$('#pagecontroller').append(li_p);
				}
				var a_l = $('<a></a>').attr('href','#').append('尾页').attr('onclick', 'list(' + data[0].PageCount + ')')
				.attr('href', 'javascript:void(0);');
				var li_l = $('<li></li>').append(a_l);
				$('#pagecontroller').append(li_l);

				$("#list").html("");
				
				
				
				for(var i=1;i<data.length;i++){
					var str;
								
				   str+='<tr msgid="'+data[i].comment.id+'"><td class="name">'+data[i].tel+'</td><td>'+data[i].comment.time+'</td><td style="max-width:400px;min-width:280px;text-overflow: ellipsis;overflow: hidden">'+data[i].comment.feel+'</td><td>'+data[i].comment.score[1]+'</td><th><a href="javascript:;" class="btn reply">回复</a><br><a href="javascript:;" class="btn removes" >删除</a></th></tr>'
				
				}
				$("#list").append(str);
				if(data.length<=1){
					$('#pagecontroller').html('<li style="padding-left: 14px;">暂时没有评论</li>');
				}
				
				
			}
		});
	};
	var _this='';
	// 删除评论
	$("#list").on('click','tr th .removes',function(){
		_this=$(this).parent().parent().attr('msgid');
		$(this).parent().parent().remove();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url:'/comment/deleteComment',
			data : {
				'id' : _this
			},
			error:function(e){
				alert('数据请求失败')
			},
			success:function(data){
				console.log(data)
				
			}
		})
	})

//	回复评论 begin
	$("#list").on('click','tr th .reply',function(){
		_this=$(this).parent().parent().attr('msgid');
		$("#masking").show();
		$(".myalert").addClass('ace');
		$("#mymassage").val('');
	})
	
	$(".myalert .close").click(function(){
		$("#mymassage").val('');
		$("#masking").hide();
		$(".myalert").removeClass('ace');
	})
	$(".myalert .verify").click(function(){
			if($("#mymassage").val()==""){
				$("#masking").hide();
				$(".myalert").removeClass('ace');
				return
			}
		
			$.ajax({
			type : 'POST',
			dataType : 'json',
			url:'/comment/reply',
			data : {
				'reply' : $("#mymassage").val(),
				'id':_this
			},
			error:function(e){
				alert('回复失败')
			},
			success:function(data){
				console.log(data)
				$(".myalert").removeClass('ace');
				$("#masking").hide();
				alert('回复成功')
			}
		})
	})
	//	回复评论 end
	
	
	$(document).ready(list(1));