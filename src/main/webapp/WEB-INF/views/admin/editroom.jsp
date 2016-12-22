<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<my_body>
	
	<div>
		<form action="<%=request.getContextPath()%>/admin/apartment/updateroom"
			method="post" enctype="multipart/form-data">
			<div>
				<input type="hidden" id="id" name="id" >
				<div>
					编号：<input type="text" id="description" name="description">
				</div>
				<div>
					类型：<select id="type" name="type">
						<option value="主卧">主卧</option>
						<option value="次卧">次卧</option>
					</select>
				</div>
				<div>
					朝向： <select id="direction" name="direction">
						<option value="东">东</option>
							<option value="南">南</option>
							<option value="西">西</option>
							<option value="北">北</option>
					</select>
				</div>
				<div>
					面积：<input type="text" id="square" name="square">
				</div>
				<div>
					设施：
					<c:forEach items="${l_facility}" var="facility" varStatus="p"
						begin="0">
						<input type="checkbox" id="facility${facility.id}" name="facility" 
							value="${facility.id}"> ${facility.description }
						</c:forEach>
				</div>
				<div>图片：<input type="file" name="file">
						<input type="file" name="file">
						<input type="file" name="file"></div>
			</div>
			<div id="lease"></div>
			<input type="submit" id="btnSend" value="保存">
		</form>
	</div>
	
	<script type="text/javascript">
		var roomid = ${roomid};
		$(document).ready(function(){
			$.ajax({
				async : false,
				cache : false,
				type : 'POST',
				dataType : 'json',
				data : {'roomid':roomid},
				url : "<%=request.getContextPath()%>/admin/apartment/getroom",//请求的action路径
				error : function() {//请求失败处理函数
					alert("获取数据失败！");
				},
				success : function(data) {
					$('#id').val(data.id);
					$('#description').val(data.description);
					$('#type').val(data.type);
					$('#square').val(data.square);
					$('#direction').val(data.direction);
					$.each(data.facilities,function(index,value){
						$('#facility'+value.id).attr('checked',true);
					});
					console.log(data);
					if(data.ltype=="2"){
						$.ajax({
							async : false,
							cache : false,
							type : 'POST',
							dataType : 'json',
							data : {'type':1,'id':data.id},
							url : "<%=request.getContextPath()%>/admin/apartment/getleasetype",//请求的action路径
									error : function() {//请求失败处理函数
										alert("获取数据失败！");
									},
									success : function(data) {
										var leasetypes = data.leasetype;
										var leasetypeids = data.leasetypeid;
										var htmltext = "";
										for ( var i in leasetypes) {
											var ids = leasetypeids[i];
											var types = leasetypes[i];
											htmltext = htmltext + "<div>";
											htmltext = htmltext
													+ types
													+ "<input type='hidden' id='leasetypeid"+ids+"' name='leasetypeid' value='"+ids+"' />"
													+ "<input type='text' id='leasetype"+ids+"' name='leasetype' />";
											htmltext = htmltext + "</div>";
										}
										$("#lease").html(htmltext);
									}
								});
					}
					$.each(data.prices,function(index,value){
						$('#leasetype'+value.leasetypeid).val(value.price);
					});
				}
			});
		});
	</script>
	
	</my_body>
</body>
</html>