


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

			
			var hidePrices = function(){
				$('.day').hide();
				$('.week').hide();
				$('.month').hide();
				$('.year').hide();
			}
			
			hidePrices();
			
			$('#apartment-type').change(
					function() {
						hidePrices();
						var op = $(this).children('option:selected').val();
						if (op != -1) {
							if(op == '酒店型'){
								$('#lease-type').hide();
							}else{
								$('#lease-type').show();
							}
						}
			});

			$('#lease-type').change(function() {
				hidePrices();
				var op = $(this).children('option:selected').val();
				if(op != -1){
					if(op != '合租型'){
						$('.month').show();
						$('.year').show();
					}
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