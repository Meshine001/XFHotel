<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<title>-青舍都市公寓-西安租房_西安合租</title>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
</head>
<body>
	<my_body>
	<table class="table" id="list">
	</table>
	<a id="firstpage"><<</a> <a id="formerpage"><</a> <a id="currentpage">
	</a> <a id="latterpage">></a> <a id="lastpage">>></a>
	共 <a id="pagecount"></a>页 <script type="text/javascript">
		Date.prototype.format = function(f){
		    var o ={
		        "M+" : this.getMonth()+1, //month
		        "d+" : this.getDate(),    //day
		        "h+" : this.getHours(),   //hour
		        "m+" : this.getMinutes(), //minute
		        "s+" : this.getSeconds(), //second
		        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		        "S" : this.getMilliseconds() //millisecond
		    }
		    if(/(y+)/.test(f))f=f.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
		    for(var k in o)
		        if(new RegExp("("+ k +")").test(f))f = f.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));return f
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
					console.log(data);
					alert("获取数据失败！");
				},
				success : function(data) {
					console.log(data);
					$("#currentpage").html(data.currentPage);
					if( data.currentPage != 1 ){
						$("#firstpage").attr('onclick','list(1)').attr('href','javascript:void(0);');
						$("#formerpage").attr('onclick','list('+ (data.currentPage-1) +')').attr('href','javascript:void(0);');
					}
					else{
						$("#firstpage").attr('onclick','javascript:void(0);').attr('href','javascript:void(0);');
						$("#formerpage").attr('onclick','javascript:void(0);').attr('href','javascript:void(0);');
					}
					if( data.currentPage != data.pageCount ){
						$("#lastpage").attr('onclick','list('+ data.pageCount +')').attr('href','javascript:void(0);');
						$("#latterpage").attr('onclick','list('+ (data.currentPage+1) +')').attr('href','javascript:void(0);');
					}
					else{
						$("#lastpage").attr('onclick','javascript:void(0);').attr('href','javascript:void(0);');
						$("#latterpage").attr('onclick','javascript:void(0);').attr('href','javascript:void(0);');
					}
					$("#pagecount").html(data.pageCount);
					$("#totalcount").html(data.totalCount);
					
					$("#list").html("");
					console.log(data);
					$.each(data.results, function(index, value) {
						var tr = $('<tr></tr>');
						var d= new Date();
						d.setTime(value.date);
						var s=d.format('yyyy-MM-dd hh:mm:ss');
						var td_date = $('<td></td>').append(s);
						var td_title = $('<td></td>').append(value.title);
						tr.append(td_date).append(td_title);
						$("#list").append(tr);
					});
				}
			});
		};
		$(document).ready(list(1));
	</script> </my_body>
</body>