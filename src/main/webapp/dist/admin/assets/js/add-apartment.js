$(document).ready(
		function() {

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

			var hidePrices = function() {
				$('.day').hide();
				$('.week').hide();
				$('.month').hide();
				$('.year').hide();
			}

			hidePrices();
			
//
//			// ==============================
//
//			$('#apartment-type').change(function() {
//				hidePrices();
//				var op = $(this).children('option:selected').val();
//				if (op != -1) {
//					if (op == '酒店型') {
//						$('#lease-type').hide();
//					} else {
//						$('#lease-type').show();
//					}
//				}
//			});
//
//			$('#lease-type').change(function() {
//				hidePrices();
//				var op = $(this).children('option:selected').val();
//				if (op != -1) {
//					if (op != '合租型') {
//						$('.month').show();
//						$('.year').show();
//					}
//				}
//
//			});
//

			//===============================
			//百度地图
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
		});
