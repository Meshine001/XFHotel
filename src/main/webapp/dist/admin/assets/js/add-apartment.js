


$(document).ready(
		function() {
			
			var getFeatures = function(){
				
				$.ajax({
					cache : true,
					type : "GET",
					url : "./features",
					async : false,
					error : function(request) {
						alert("连接异常！");
					},
					success : function(data) {
						if (data.statusCode == 0) {
							var des = $('<li>'+data.content+'</li>');
							des.appendTo('#features-li');
						} else {
							$('#features-li').empty();
							var features = data.content;
							$(features).each(function(index,f){
								var form = $('<form></form>');
								var li = $('<li></li>');
								var inputId = $('<input></input>').attr('type','hidden').attr('name','id').val(f.id);
								var inputDes = $('<input></input>').attr('type', 'text').attr('name','description').val(f.description);
								var del = $('<button>删除</button>').attr('id',f.id);
								del.click(function(){

									$.ajax({
										cache : true,
										type : "POST",
										url : "./features/delete",
										data : {
											id:del.attr('id')
										},
										async : false,
										error : function(request) {
											alert("连接异常！");
										},
										success : function(data) {
											alert(data.content);
											li.remove();
										}
									});
								});
								li.append(form);
								form.append(inputId);
								form.append(inputDes);
								inputDes.after(del);
								li.appendTo('#features-li');
							});
						}
						$('#feature-modal').modal('show');

					}
				});
			};
			
			
			/**
			 * 添加特色
			 */
			$('#add-feature').click(function(){
				$.ajax({
					cache : true,
					type : "POST",
					url : "./features/add",
					data : $('#add-feature-form').serialize(),
					async : false,
					error : function(request) {
						alert("连接异常！");
					},
					success : function(data) {
						if (data.statusCode == 0) {
							alert(data.content)
						} else {
							alert("添加成功");
							getFeatures();
//							var f = data.content;
//							
//							var li = $('<li></li>').append($('<input></input>').attr('type', 'hidden').attr('name','id').val(f.id));
//							li.appendTo('#featrues-li');
//							var des = $('<li></li>').append($('<input></input>').attr('type', 'text').attr('name','description').val(f.description));
//							des.appendTo('#featrues-li');
						}

					}
				});
			});
			
			
			/**
			 * 编辑特色
			 */
			$('#edit-feature').click(function(){
				getFeatures();
			});
			
			
			var $layoutImageInput = $('#layout-image-input');
			$layoutImageInput.change(function() {
				var files = $layoutImageInput.prop('files'), file;
				if (files.length > 0) {
					file = files[0];
					if (/^image\/\w+/.test(file.type)) {
						$('#layout-image').attr('src',
								URL.createObjectURL(file));
					} else {
						alert("请选择图片！");
					}
				}
			});

			$('#apartment-type').change(
					function() {
						var op = $(this).children('option:selected').val();
						$("#lease-price").html("");
						if (op != -1) {
							$('#apartment-type-input').val(op);
							$.ajax({
								async : false,
								cache : false,
								type : 'POST',
								dataType : 'json',
								data : {
									'type' : 0,
									'id' : op
								},
								url : "../apartment/getleasetype",// 请求的action路径
								error : function() {// 请求失败处理函数
									alert("获取数据失败！");
								},
								success : function(data) {
									console.log(data);
									var leasetypes = data.leasetype;
									var leasetypeids = data.leasetypeid;
									for ( var i in leasetypes) {
										var ids = leasetypeids[i];
										var types = leasetypes[i];
										var div = $('<div></dic>').attr('class','input-group');
										var spanType = $('<span></span>').attr('class','input-group-addon').text(types);
										var spanPrice = $('<span></span>').attr('class','input-group-addon').text('元');
										var inputType = $('<input></input>')
										.attr('type', 'text').attr(
												'name', 'price');
										div.append(spanType);
										div.append(inputType);
										div.append(spanPrice);
										$('#lease-price').append(div);
										var inputId = $('<input></input>')
												.attr('type', 'hidden').attr(
														'name', 'leasetypeid')
												.val(ids);
										div.append(inputId);
//										label.appendTo('#lease-price');
//										inputId.appendTo('#lease-price');
//										inputType.appendTo('#lease-price');
									}

								}
							});
						}
			});

			$('#lease-type').change(function() {
				var op = $(this).children('option:selected').val();
				$('#type-input').val(op);
				if (op == 1) {
					$('#lease-price').show();
				} else {
					$('#lease-price').hide();
				}
			});
		});

var map = new BMap.Map("map");
var geolocation = new BMap.Geolocation();
var point = new BMap.Point(116.331398, 39.897445);
map.centerAndZoom(point, 12);
map.enableScrollWheelZoom(true);
var marker = new BMap.Marker(point);
marker.setPosition(map.getCenter());
map.addOverlay(marker);
geolocation.getCurrentPosition(function(r) {
	if (this.getStatus() == BMAP_STATUS_SUCCESS) {
		map.panTo(r.point);
		marker.setPosition(map.getCenter());
		getaddress(r.point.lng, r.point.lat);
	} else {
		alert("定位失败！");
	}
}, {
	enableHighAccuracy : true
});
map.addEventListener('ondragging', function() {
	marker.setPosition(map.getCenter());
});
map.addEventListener("dragend", function showInfo() {
	var cp = map.getCenter();
	getaddress(cp.lng, cp.lat);
});
function getaddress(lng, lat) {
	var pt = new BMap.Point(lng, lat);
	var geoc = new BMap.Geocoder();
	geoc.getLocation(pt,
			function(rs) {
				var addComp = rs.addressComponents;
				$('#location_info').val(
						addComp.province + ", " + addComp.city
								+ ", " + addComp.district + ", "
								+ addComp.street);
				$('#lng').val(lng);
				$('#lat').val(lat);
			});
}