

$(document).ready(function(){
	
	
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
	
	
	$.ajax({
		async : false,
		cache : false,
		type : 'POST',
		dataType : 'json',
		data : {'apartmentid':$('#apartmentid').val()},
		url : "./getapartment",//请求的action路径
		error : function() {//请求失败处理函数
			alert("获取数据失败！");
		},
		success : function(data) {
			console.log(data);
			var map = new BMap.Map("map");
			var geolocation = new BMap.Geolocation();
			var point = new BMap.Point(data.longitude,data.latitude);
			var marker = new BMap.Marker(point);
			marker.setPosition(point);
			map.addOverlay(marker);
			map.centerAndZoom(point,12);
			map.enableScrollWheelZoom(true);
			map.addEventListener('ondragging', function(){
				marker.setPosition(map.getCenter());
        			});
			map.addEventListener("dragend", function showInfo(){
				var cp = map.getCenter();
				getaddress(cp.lng,cp.lat);
			});
			function getaddress(lng,lat){
				var pt = new BMap.Point(lng,lat);
				var geoc = new BMap.Geocoder();  
				geoc.getLocation(pt, function(rs){
					var addComp = rs.addressComponents;
					$('#location_info').val(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street);
					$('#lng').val(lng);
					$('#lat').val(lat);
					
				});        
			}
			$('#apartmentid').val(data.id);
			$('#address').val(data.address);
			$('#community').val(data.community);
			$('#num_building').val(data.num_building);
			$('#floor').val(data.floor);
			$('#totalfloor').val(data.totalfloor);
			$('#square').val(data.square);
			$('#capacity').val(data.capacity);
			$('#bedroom').val(data.bedroom);
			$('#livingroom').val(data.livingroom);
			$('#bathroom').val(data.bathroom);
			$('#balcony').val(data.balcony);
			$('#description').val(data.description);
			$.each(data.facilities,function(index,value){
				$('#fa-'+value).attr('checked',true);
			});
			$.each(data.features,function(index,value){
				$('#fe-'+value).attr('checked',true);
			});
			$("#apartment-type option").each(function (){  
			    if($(this).text()==data.apartmenttype){   
			    	$(this).attr('selected','selected');
			    	if(data.apartmenttype == '酒店型'){
			    		$('#lease-type').hide();
			    	}
			 }});  
			
			$('#location_info').val(data.location);
			$('#lng').val(data.longitude);
			$('#lat').val(data.latitude);
			$.each(data.prices,function(index,value){
				$('#leasetypeid'+value.leasetypeid).val(value.leasetypeid);
				$('#leasetype'+value.leasetypeid).val(value.price);
			});
			var roomshtml = "";
			$.each(data.rooms,function(index,value){
				var url = "./editroom";
				var form = $('<form></form>').attr('action',url).attr('method','post');
				var ul = $('<ul></ul>');
				var li =  $("<li>"+ value.description
					+ "&nbsp 出租类型：" + value.ltype
					+ "&nbsp 面积：" + value.square
					+ "&nbsp 朝向：" + value.direction
					+ "&nbsp 可住：" + value.capacity
					+ "&nbsp 类型：" + value.type 
					+ "&nbsp</li>");
				ul.append(li);
				form.append(ul);
				var submit = $('<input></input>').attr('type','submit').attr('value','编辑').appendTo(li);
				var inrid = $('<input></input>').attr('type','hidden').attr('value',value.id).attr('name','roomid');
				var inaid = $('<input></input>').attr('type','hidden').attr('value',data.id).attr('name','apartmentid');
				form.append(inrid).append(inaid);
				$("#rooms").append(form);
			});
			
			$('#pic1-1-img').attr('src','../../images/'+data.pic1);
			$('#pic1-1-input').val(data.pic1);
			$.each(data.pic2,function(index,pic){
				var imgUrl = '../../images/'+pic;
				var id = 'pic2-'+(index+1);
				var img = $('<img></img>').attr('id',id+'-img').addClass('img-thumbnail').attr('src',imgUrl).css('height','80px').css('width','120px');
				var li;
				if(index < 2){
					//客厅图
				    li = $('<li></li>').appendTo($('#livingroom-ul'));
				}else{
					//餐厅图
					li = $('<li></li>').appendTo($('#eattingroom-ul'));
				}
				var input = $('<input></input>').attr('type','hidden').attr('name','pic2').attr('id',id+'-input').val(pic);
				var a = $('<a>更换</a>').appendTo(li).addClass('btn upload-label').attr('id',id);
				li.append(img).append(input).append(a);
			});
			$.each(data.pic3,function(index,pic){
				var imgUrl = '../../images/'+pic;
				var id = 'pic3-'+(index+1);
				var img = $('<img></img>').attr('id',id+'-img').addClass('img-thumbnail').attr('src',imgUrl).css('height','80px').css('width','120px');
				var li;
				if(index < 2){
					//浴室图
				    li = $('<li></li>').appendTo($('#bathroom-ul'));
				}else{
					//厨房图
					var li = $('<li></li>').appendTo($('#kitchen-ul'));
				}
				var input = $('<input></input>').attr('type','hidden').attr('name','pic3').attr('id',id+'-input').val(pic);
				var a = $('<a>更换</a>').appendTo(li).addClass('btn upload-label').attr('id',id);
				li.append(img).append(input).append(a);
			});
			$.each(data.pic4,function(index,pic){
				var imgUrl = '../../images/'+pic;
				var id = 'pic4-'+(index+1);
				var img = $('<img></img>').attr('id',id+'-img').addClass('img-thumbnail').attr('src',imgUrl).css('height','80px').css('width','120px');
				//小区实景
				var li = $('<li></li>').appendTo($('#community-ul'));
				var input = $('<input></input>').attr('type','hidden').attr('name','pic4').attr('id',id+'-input').val(pic);
				var a = $('<a>更换</a>').appendTo(li).addClass('btn upload-label').attr('id',id);
				li.append(img).append(input).append(a);
			});
		}
	});
	
	var uploadForm = $('#upload-form');
	var uploadUrl = '../../file/upload';
	
	function uploadFile(data){
		var result;
		$.ajax(uploadUrl,{
			headers: {'X-XSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')}, 
	        type: 'post',
	        data: data,
	        async: false,
	        dataType: 'json',
	        processData: false,
	        contentType: false,
	        success:function(data){
	        	console.log(data);
	        	if(data.statusCode == 1){
	        		result = data.content;
	        	}
	        	else{
	        		alert(data.content);
	        		result = false;
	        	}
	        },
	        error:function(data){
	        	alert('连接异常');
	        	result = false;
	        }
		});
		return result;
	}
	
	var uploadId;
	var uploadPre;
	$('.upload-label').click(function(){
		var uploadType = $(this).attr('id');
		uploadId = uploadType +'-input';
		uploadPre = uploadType + '-img';
		$('#upload-file-input').click();
	});
	
	$('#upload-file-input').change(function(){
		var data = new FormData(uploadForm[0]);
		var imgUrl = uploadFile(data);
		if(imgUrl != false){
			$('#'+uploadId).val(imgUrl);
			$('#'+uploadPre).attr('src','../../images/'+imgUrl);
		}
	});
});