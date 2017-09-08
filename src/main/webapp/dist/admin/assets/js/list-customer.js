function getStatus(s) {
	var status = "";
	if (s == 1)
		status = "激活";
	if (s == 0)
		status = "冻结";
	return status;
};
function getOp(s) {
	var status = "";
	if (s == 0)
		status = "激活";
	if (s == 1)
		status = "冻结";
	return status;
};
function change(id, status,cp) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {
			'id' : id,
			'status' : status
		},
		url : "./change_status",// 请求的action路径
		error : function(e) {// 请求失败处理函数
			alert("更改失败！");
		},
		success : function(data) {
			list(cp);
		}
	});
};

function list(page) {
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {
			'page' : page
		},
		url : "./get_customers",// 请求的action路径
		error : function(e) {// 请求失败处理函数
			alert("获取数据失败！");
		},
		success : function(data) {
		//	console.log(data)
			$('#pagecontroller').html('');
			var a_f = $('<a></a>').attr('href','#').append('&laquo;').attr('onclick', 'list(1)').attr('href',
			'javascript:void(0);');
			var li_f = $('<li></li>').append(a_f);
			$('#pagecontroller').append(li_f);
			for (var i=data.sp;i<=data.ep;i++){
				var a_p = $('<a></a>').attr('href','#').append(i).attr('onclick', 'list(' + i + ')')
				.attr('href', 'javascript:void(0);');
				var li_p = $('<li></li>').append(a_p);
				if(i==data.currentPage)
					li_p.attr('class','active');
				$('#pagecontroller').append(li_p);
			}
			var a_l = $('<a></a>').attr('href','#').append('&raquo;').attr('onclick', 'list(' + data.pageCount + ')')
			.attr('href', 'javascript:void(0);');
			var li_l = $('<li></li>').append(a_l);
			$('#pagecontroller').append(li_l);

			var trtitle = $('<tr></tr>').append('');
			$("#list").html(trtitle);
			$.each(data.results,
					function(index, value) {
						var tr = $('<tr></tr>');
						var td_id = $('<td></td>').append(value.id);
						var td_level = $('<td></td>').append(value.level);
						var td_tel = $('<td></td>').append(value.tel);
						var td_regTime = $('<td></td>').append(value.regTime);
						var td_consumptionCount = $('<td></td>').append(
								value.consumptionCount);
						var td_consumptionTimes = $('<td></td>').append(
								value.consumptionTimes);
						var td_details = $('<td></td>').append(
								getStatus(value.status));
						var a_detail = $('<a class="btn btn-success"></a>').append('查看详情');
						a_detail.attr('href', './view_customer?id='+value.id);
						var a_op = $('<a class="btn btn-warning"></a>').append(getOp(value.status));
						a_op.attr('onclick',
										'change(' + value.id + ','
												+ value.status +','
												+ data.currentPage + ')').attr(
										'href', 'javascript:void(0);');
						var td_status = $('<td></td>').append(a_detail).append(
								'<br>').append(a_op);
						tr.append(td_id).append(td_level).append(td_tel)
								.append(td_regTime).append(td_consumptionCount)
								.append(td_consumptionTimes).append(td_details)
								.append(td_status);
						$("#list").append(tr);
					});
		}
	});
};


// 5.20分页存在问题；
//条件搜索；
$("#longtime").on('change',function(){
	 $.ajax({
			type:'POST',
			async : false,
			cache : false,
			dataType:'json',
			data:{'time':$("#longtime option:selected").attr('tid')},
			url: "./dsendlist",
			error:function(){
				alert('数据传输错误！')
			},
			success:function(data){
			//	console.log(data);
				var str='';
				var newTime=new Array();
				$("#list").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
					'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a><br><a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
					
				}
				$("#list").append(str);
// OK	查看详细  状态显示			
				$(".lockit").on('click',function(){
					window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
				})

				if($("#longtime option:selected").attr('tid')=='3'){
					list(1)
				}
				
			}
		})
});
$("#monetary").on('change',function(){
//  getData($("#monetary option:selected").val())
	  $.ajax({
			type:'POST',
			async : false,
			cache : false,
			dataType:'json',
			data:{'money':$("#monetary option:selected").val()},
			url: "./dsendlist",
			error:function(){
				alert('数据传输错误！')
			},
			success:function(data){
			//	console.log(data);
				var str='';
				var newTime=new Array();
				$("#list").html('');
				for(var i=0;i<data.content.length;i++){
					var infotime=data.content[i].regTime;
					var j = new Date(infotime);
					var istime=j.toLocaleDateString()
					    newTime.push(istime);
					str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
					'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a><br><a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
					
				}
				$("#list").append(str);
// OK	查看详细  状态显示			
				$(".lockit").on('click',function(){
					window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
				})
				if($("#monetary option:selected").val()=='0'){
					list(1)
				}
				
				
			}
		})
	
});

$("#sex").on('change',function(){
//  getData($("#sex option:selected").val())
    $.ajax({
		type:'POST',
		async : false,
		cache : false,
		dataType:'json',
		data:{'sex':$("#sex option:selected").val()},
		url: "./dsendlist",
		error:function(){
			alert('数据传输错误！')
		},
		success:function(data){
		//	console.log(data);
			var str='';
			var newTime=new Array();
			$("#list").html('');
			for(var i=0;i<data.content.length;i++){
				var infotime=data.content[i].regTime;
				var j = new Date(infotime);
				var istime=j.toLocaleDateString()
				    newTime.push(istime);
				str+='<tr _pid="'+data.content[i].id+'"><td>'+data.content[i].id+'</td><td>'+data.content[i].level+'</td><td>'+data.content[i].tel+'</td><td>'+newTime[i]+'</td><td>'+data.content[i].consumptionCount+'</td><td>'+data.content[i].consumptionTimes+
				'</td><td>'+getStatus(data.content[i].status)+'</td><td><a class="lockit">查看详细</a><br><a onclick="change(' +data.content[i].id+ ','+data.content[i].status+',1)">'+getOp(data.content[i].status)+'</a></td></tr>';
				
			}
			$("#list").append(str);
//OK	查看详细  状态显示			
			$(".lockit").on('click',function(){
				window.location.href="view_customer?id="+encodeURIComponent($(this).parent().parent().attr('_pid'));
			})
			if($("#sex option:selected").val()=='全部'){
				list(1)
			}
		}
	})
})
$(document).ready(list(1));