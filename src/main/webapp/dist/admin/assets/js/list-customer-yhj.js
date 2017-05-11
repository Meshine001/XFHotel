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
						tr.attr('uid',value.id)
						var td_level = $('<td></td>').append(value.level);
						var td_tel = $('<td></td>').append(value.tel);
						var td_regTime = $('<td></td>').append(value.regTime);
						var td_consumptionCount = $('<td></td>').append(
								value.consumptionCount);
						var td_consumptionTimes = $('<td></td>').append(
								value.consumptionTimes);
						var td_details = $('<td></td>').append(
								getStatus(value.status));
						var td_input="<i></i>";
						var a_detail = $('<a></a>').append(td_input);
//						a_detail.attr('href', './view_customer?id='+value.id);
//						var a_op = $('<a></a>').append(getOp(value.status));
//						a_op.attr('onclick',
//										'change(' + value.id + ','
//												+ value.status +','
//												+ data.currentPage + ')').attr(
//										'href', 'javascript:void(0);');
						
						var td_status = $('<td></td>').append(a_detail);
						tr.append(td_id).append(td_level).append(td_tel)
								.append(td_regTime).append(td_consumptionCount)
								.append(td_consumptionTimes).append(td_details)
								.append(td_status);
						$("#list").append(tr);
					});
		}
	});
};






$(".masking").css({
	"position":'fixed',
	"top":'0',
	"bottom":'0',
	"left":'0',
	"right":'0',
	"background":'black',
	"opacity":'0.8',
	"z-index":'1000000001'
})


	function getData(_data){
	var _data={
			'money':$("#monetary option:selected").val(),
			'sex':$("#sex option:selected").val(),
			'time':$("#longtime option:selected").attr('tid')
	};
	$.ajax({
		type:'POST',
		async : false,
		cache : false,
		dataType:'json',
		data:'_data',
		url: "./dsendlist",
		error:function(){
			alert('数据传输错误！')
		},
		success:function(data){
			console.log(data);
			var str='';
			$("#list").html('');
			for(var i=0;i<data.status.length;i++){
				str+='<tr>'+data.data[i].id+'</tr><tr>'+data.data[i].level+'</tr><tr>'+data.data[i].tel+'</tr><tr>'+data.data[i].regTime+'</tr><tr>'+data.data[i].consumptionCount+'</tr><tr>'+data.data[i].consumptionTimes+'</tr><tr>'+data.data[i].status+'</tr><tr><a><i></i></a></tr>'
			}
			$("#list").append(str);
		}
	})
}


//条件搜索；
 $("#longtime").on('change',function(){
//        getData($("#longtime option:selected").attr('tid'))
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
			console.log(data);
			var str='';
			$("#list").html('');
			for(var i=0;i<data.status.length;i++){
				str+='<tr>'+data.data[i].id+'</tr><tr>'+data.data[i].level+'</tr><tr>'+data.data[i].tel+'</tr><tr>'+data.data[i].regTime+'</tr><tr>'+data.data[i].consumptionCount+'</tr><tr>'+data.data[i].consumptionTimes+'</tr><tr>'+data.data[i].status+'</tr><tr><a><i></i></a></tr>'
			}
			$("#list").append(str);
		}
	})
 });
 $("#monetary").on('change',function(){
//     getData($("#monetary option:selected").val())
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
			console.log(data);
			var str='';
			$("#list").html('');
			for(var i=0;i<data.status.length;i++){
				str+='<tr>'+data.data[i].id+'</tr><tr>'+data.data[i].level+'</tr><tr>'+data.data[i].tel+'</tr><tr>'+data.data[i].regTime+'</tr><tr>'+data.data[i].consumptionCount+'</tr><tr>'+data.data[i].consumptionTimes+'</tr><tr>'+data.data[i].status+'</tr><tr><a><i></i></a></tr>'
			}
			$("#list").append(str);
		}
	})
});
 $("#sex").on('change',function(){
//     getData($("#sex option:selected").val())
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
			console.log(data);
			var str='';
			$("#list").html('');
			for(var i=0;i<data.status.length;i++){
				str+='<tr>'+data.data[i].id+'</tr><tr>'+data.data[i].level+'</tr><tr>'+data.data[i].tel+'</tr><tr>'+data.data[i].regTime+'</tr><tr>'+data.data[i].consumptionCount+'</tr><tr>'+data.data[i].consumptionTimes+'</tr><tr>'+data.data[i].status+'</tr><tr><a><i></i></a></tr>'
			}
			$("#list").append(str);
		}
	})
});

$("#list").on('click','tr',function(){
	if($(this).hasClass('_active')==false){
		$(this).addClass('_active')
	}else{
		$(this).removeClass('_active')
	}
})
fasongdata();
function fasongdata(){
	$("#variety,#price").html('');
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : '',
		url : "192.168.1.109:8080/Users/HY/Desktop/web/XFHotel/java/com/xfhotel/hotel/controller",
		success :function(data){
			var str,ptr='';
			for(var i=0;i<data.vaie.length;i++){
				str+='<option vid='+data.vid[i]+'>'+data.vid[i]+'</option>'
			}
			$("#variety").append(str);
			for(var e=0;e<data.ptr.length;i++){
				ptr+='<option pid='+data.pid[i]+'>'+data.pid[i]+'</option>'
			}
			$("#price").append(ptr);
		}
	})
}

	
var strlist2='';
$("#delivery").click(function(){
	$(".masking").show();
	$("#sending").show();
	$("#new-coupon").show();
    var	strlist=new Array();
	var obj=$(this).parent().parent().parent().parent().find('#list tr');
	for(var i=0;i<obj.length;i++){
		if(obj.eq(i).hasClass('_active')==true){
			strlist.push(obj.eq(i).attr('uid'))
		}	
	}
	var strlist2=strlist.join(",")
   return strlist2;
})
$("#closebtn").click(function(){
	$(".masking").hide();
	$("#sending").hide();
	$("#new-coupon").hide();
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
	$(".masking,#sending").hide();
	console.log(postdata)
	$.ajax({
		
		type : 'POST',
		dataType : 'json',
		data : postdata,
//		url : "hotel/controller/AdminController/sendlist",
		url:"./sendlist",
		error : function(data) {// 请求失败处理函数
			alert("发送失败！");
		},
		success :function(data){
			console.log(data)
			alert("发送成功")
		}
	})
	
//	222
})




$(document).ready(list(1));