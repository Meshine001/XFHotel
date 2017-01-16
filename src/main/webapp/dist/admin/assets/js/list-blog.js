Date.prototype.format = function(f) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	//millisecond
	}
	if (/(y+)/.test(f))
		f = f.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(f))
			f = f.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return f
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
		url : "./get_blogs",//请求的action路径
		error : function(data) {//请求失败处理函数
			alert("获取数据失败！");
		},
		success : function(data) {
			var sp = data.currentPage;
			var ep = data.pageCount;
			if ((sp + 9) < ep) {
				ep = sp + 9;
			}
			if ((ep - 9) < ep) {
				sp = ep - 9;
				if (sp < 1)
					sp = 1;
			}

			$('#pagecontroller').html('');
			var a_f = $('<a></a>').attr('href', '#').append('&laquo;').attr(
					'onclick', 'list(1)').attr('href', 'javascript:void(0);');
			var li_f = $('<li></li>').append(a_f);
			$('#pagecontroller').append(li_f);
			for (var i = sp; i <= ep; i++) {
				var a_p = $('<a></a>').attr('href', '#').append(i).attr(
						'onclick', 'list(' + i + ')').attr('href',
						'javascript:void(0);');
				var li_p = $('<li></li>').append(a_p);
				if (i == data.currentPage)
					li_p.attr('class', 'active');
				$('#pagecontroller').append(li_p);
			}
			var a_l = $('<a></a>').attr('href', '#').append('&raquo;').attr(
					'onclick', 'list(' + data.pageCount + ')').attr('href',
					'javascript:void(0);');
			var li_l = $('<li></li>').append(a_l);
			$('#pagecontroller').append(li_l);

			$('#t_body').html("");
			$.each(data.results, function(index, value) {
				var tr = $('<tr></tr>');
				var d = new Date();
				d.setTime(value.date);
				var s = d.format('yyyy-MM-dd hh:mm:ss');
				var td_date = $('<td></td>').append(s);
				var a_title = $('<a></a>').append(value.title).attr('href',
						'./edit?id=' + value.id);
				var td_title = $('<td></td>').append(a_title);
				var a_del = $('<a></a>').attr('onclick', 'del('+ value.id+",'"+ value.title +"')").attr('href', 'javascript:void(0);').append('删除');
				var td_op = $('<td></td>').append(a_del);
				tr.append(td_date).append(td_title).append(td_op);
				$('#t_body').append(tr);
			});
		}
	});
};
function del(id , title){
	event.returnValue = confirm("删除是不可恢复的，你确认要删除'"+title+"'吗？");
	if(event.returnValue == true){
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType : 'text',
			data : {
				'id' : id
			},
			url : "./delete_blog",//请求的action路径
			error : function(data) {//请求失败处理函数
				console.log(data);
				alert("获取数据失败！");
			},
			success : function(data) {
				list(1);
			}
		});
	}
};
$(document).ready(list(1));