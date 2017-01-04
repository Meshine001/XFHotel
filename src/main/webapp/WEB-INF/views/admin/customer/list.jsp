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
	</a> <a id="latterpage">></a> <a id="lastpage">>></a> 共 <a id="totalcount"></a>条记录
	共 <a id="pagecount"></a>页 <script type="text/javascript">
		function getStatus(s) {
			var status = "";
			if (s == 1)
				status = "激活";
			if (s == 0)
				status = "冻结";
			return status;
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
				url : "./get_customers",//请求的action路径
				error : function() {//请求失败处理函数
					alert("获取数据失败！");
				},
				success : function(data) {
					console.log(data);
					$("#currentpage").html(data.currentPage);
					if( data.currentPage != 1 ){
						$("#firstpage").attr('onclick','list(1)').attr('href','javascript:void(0);');
						$("#formerpage").attr('onclick','list('+ (data.currentPage-1) +')').attr('href','javascript:void(0);');
					}
					if( data.currentPage != data.pageCount ){
						$("#lastpage").attr('onclick','list('+ data.pageCount +')').attr('href','javascript:void(0);');
						$("#latterpage").attr('onclick','list('+ (data.currentPage+1) +')').attr('href','javascript:void(0);');
					}
					$("#pagecount").html(data.pageCount);
					$("#totalcount").html(data.totalCount);
					
					var trtitle=$('<tr></tr>').append('<td>序号</td><td>等级</td><td>电话</td><td>注册时间</td><td>消费总额</td><td>消费次数</td><td>详情</td><td>状态</td>');
					$("#list").html(trtitle);
					$.each(data.results, function(index, value) {
						var tr = $('<tr></tr>');
						var td_id = $('<td></td>').append(value.id);
						var td_level = $('<td></td>').append(value.level);
						var td_tel = $('<td></td>').append(value.tel);
						var td_regTime = $('<td></td>').append(value.regTime);
						var td_consumptionCount = $('<td></td>').append(
								value.consumptionCount);
						var td_consumptionTimes = $('<td></td>').append(
								value.consumptionTimes);
						var td_details = $('<td></td>').append(value.details);
						var td_status = $('<td></td>').append(value.status);
						tr.append(td_id).append(td_level).append(td_tel)
								.append(td_regTime).append(td_consumptionCount)
								.append(td_consumptionTimes).append(td_details)
								.append(td_status);
						$("#list").append(tr);
					});
				}
			});
		};
		$(document).ready(list(1));
	</script> </my_body>
</body>
</html>