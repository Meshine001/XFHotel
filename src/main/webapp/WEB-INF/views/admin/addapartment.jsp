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
	<div class="row">
		<div class="col-md-4">
			<div class="card">
				<div class="card-header">添加房源</div>
				<div class="card-body">
					<form action="" class="form form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">地址</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="address">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小区名称</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="community">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼号</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_building">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">楼层</label>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">第</span> <input type="text"
										class="form-control" placeholder="" name="floor"> <span
										class="input-group-addon">层</span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="input-group">
									<span class="input-group-addon">共</span> <input type="text"
										class="form-control" placeholder="" name="totalfloor">
									<span class="input-group-addon">层</span>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">朝向</label>
							<div class="col-md-9">
								<select name="direction" class="">
									<option value="e">东</option>
									<option value="se">东南</option>
									<option value="s">南</option>
									<option value="sw">西南</option>
									<option value="w">西</option>
									<option value="nw">西北</option>
									<option value="n">北</option>
									<option value="ne">东北</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">总面积</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="square">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可住人数</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="capacity">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label">户型</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bedroom"> <span class="input-group-addon">室</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="livingroom"> <span class="input-group-addon">厅</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="bathroom"> <span class="input-group-addon">卫</span>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control" placeholder=""
										name="balcony"> <span class="input-group-addon">阳台</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">特色</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">备注</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="description">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">布局图</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" name="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">设施</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="" name="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">可租房间数</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder=""
									name="num_room">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">出租类型</label>
							<div class="col-md-9">
								<select name="apartmenttype"
									onchange="type1change(this.options[this.options.selectedIndex].value)">
									<option value="-1" selected="selected">请选择</option>

								</select> <select name="type"
									onchange="type2change(this.options[this.options.selectedIndex].value)">
									<option value="0">请选择</option>
									<option value="1">单租型</option>
									<option value="2">合租型</option>
									<option value="3">混合型</option>
								</select>
							</div>
						</div>

						<div class="form-footer">
							<div class="form-group">
								<div class="col-md-9 col-md-offset-3">
									<button type="submit" class="btn btn-primary">Save</button>
									<button type="button" class="btn btn-default">Cancel</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<form action="<%=request.getContextPath()%>/admin/apartment/add"
		method="POST">
		<div>
			<div>
				地址：<input type="text" name="address" />
			</div>
			<div>
				小区名称：<input type="text" name="community" />
			</div>
			<div>
				楼号：<input type="text" name="num_building" />
			</div>
			<div>
				楼层：第<input type="text" name="floor" />层&nbsp;共<input type="text"
					name="totalfloor" />层
			</div>
			<div>
				朝向： <select name="direction">
					<option value="e">东</option>
					<option value="se">东南</option>
					<option value="s">南</option>
					<option value="sw">西南</option>
					<option value="w">西</option>
					<option value="nw">西北</option>
					<option value="n">北</option>
					<option value="ne">东北</option>
				</select>
			</div>
			<div>
				面积：<input type="text" name="square" /> 人数：<input type="text"
					name="capacity" />
			</div>
			<div>
				房屋户型：<input type="text" name="bedroom" />室<input type="text"
					name="livingroom" />厅<input type="text" name="bathroom" />卫<input
					type="text" name="balcony" />阳台
			</div>
			<div>
				特色：
				<c:forEach items="${l_feature}" var="feature" varStatus="p">
					<input type="checkbox" name="feature" value="${feature.id}"> ${feature.description }
				</c:forEach>
			</div>
			<div>
				备注：<input type="text" name="description">
			</div>
			<div>布局图：</div>
		</div>
		<div>
			设施：
			<c:forEach items="${l_facility}" var="facility" varStatus="p">
				<input type="checkbox" name="facility" value="${facility.id}"> ${facility.description }
		</c:forEach>
		</div>
		<div>
			房间设置：
			<div>
				总房间数：<input type="text" name="num_room">
			</div>
		</div>
		<div>
			出租类型：<select name="apartmenttype"
				onchange="type1change(this.options[this.options.selectedIndex].value)">
				<option value="-1" selected="selected">请选择</option>
				<c:forEach items="${l_apartmenttype}" var="apartmenttype"
					varStatus="p">
					<option value="${apartmenttype.id }">${apartmenttype.description }</option>
				</c:forEach>
			</select> <select name="type"
				onchange="type2change(this.options[this.options.selectedIndex].value)">
				<option value="0">请选择</option>
				<option value="1">单租型</option>
				<option value="2">合租型</option>
				<option value="3">混合型</option>
			</select>
		</div>
		<div id="lease" style="display: none;"></div>
		<button type="submit">提交</button>
	</form>
	<script type="text/javascript">
			document.getElementById("lease").style.display = "none";
			function type1change(op) {
			if(op!=-1){
				$.ajax({
					async : false,
					cache : false,
					type : 'POST',
					dataType : 'json',
					data : {'type':0,'id':op},
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
											+ "<input type='hidden' name='leasetypeid' value='"+ids+"'>"
											+ "<input type='text' name='leasetype'>";
									htmltext = htmltext + "</div>";
								}
								$("#lease").html(htmltext);
							}
						});
			} else
				$("#lease").html("");
		}
		function type2change(op) {
			if (op == 1)
				document.getElementById("lease").style.display = "";
			if (op == 2)
				document.getElementById("lease").style.display = "none";
			if (op == 3)
				document.getElementById("lease").style.display = "";
		}
	</script>
	</my_body>
</body>
</html>