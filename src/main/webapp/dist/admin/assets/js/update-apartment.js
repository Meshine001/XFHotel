$(document).ready(function() {

	//获取当前网址，如： http://localhost:8080/Tmall/index.jsp 
	var curWwwPath=window.document.location.href; 

	//获取主机地址之后的目录如：/Tmall/index.jsp 
	var pathName=window.document.location.pathname; 
	var pos=curWwwPath.indexOf(pathName); 

	//获取主机地址，如： http://localhost:8080 
	var localhostPath=curWwwPath.substring(0,pos); 

	//获取带"/"的项目名，如：/Tmall 
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	
	// 上传图片
	// ======================================================>
	var uploadForm = $('#upload-image-form');
	//var uploadUrl = localhostPath+'/'+projectName+'/file/upload';
	var uploadUrl = localhostPath+'/file/upload';
	console.log(uploadUrl);
	function uploadFile(data) {
		var result;
		$.ajax(uploadUrl, {
			headers : {
				'X-XSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
			},
			type : 'post',
			data : data,
			async : false,
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(data) {
				console.log(data);
				if (data.statusCode == 1) {
					result = data.content;
				} else {
					alert(data.content);
					result = false;
				}
			},
			error : function(data) {
				alert('连接异常');
				result = false;
			}
		});
		return result;
	}

	var uploadId;
	var uploadPre;
	$('.upload-image').click(function() {
		var uploadType = $(this).attr('id');
		uploadId = uploadType + '-input';
		uploadPre = uploadType + '-img';
		$('#upload-image-input').click();
	});

	$('#upload-image-input').change(function() {
		var data = new FormData(uploadForm[0]);
		var imgUrl = uploadFile(data);
		if (imgUrl != false) {
			$('#' + uploadId).val(imgUrl);
			$('#' + uploadPre).attr('src', localhostPath+'/images/' + imgUrl);
		}
	});

});

var map = new BMap.Map("map");
var geolocation = new BMap.Geolocation();
var point = new BMap.Point($('#lng').val(), $('#lat').val());
map.centerAndZoom(point, 14);
map.enableScrollWheelZoom(true);
var marker = new BMap.Marker(point);
marker.setPosition(map.getCenter());
map.addOverlay(marker);

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
//复选
$(".informers input").click(function(event){
	event.stopPropagation();
	$(this).parent().css({'height':'300px','border':'1px solid #009688'});
	$(this).parent().find('.checkBox,.confirm').show();
	
})
//$(document).click(function(event){
//	event.stopPropagation();
//	$(".informers .checkBox,.informers p").hide();
//})

$(".informers .checkBox ul li").on('click',function(){
	if($(this).hasClass('actives')==true){
		$(this).removeClass('actives');
		$(this).find('span').hide();
	}else{
		$(this).addClass('actives');
		$(this).find('span').show();
	}
})
var Puttingdata='';
$('.informers .confirm').click(function(){
	var coupon='', classdata=new Array();
	var otherdata='';
	var thisData=$(this).parent().parent().find('.checkBox ul li');
	var strarlist=new Array();
	for(var i=0;i<thisData.length;i++){
		if(thisData.eq(i).hasClass('actives')==true){
			strarlist.push(thisData.eq(i).text());
			classdata.push(thisData.eq(i).attr('data-icon'));
		}
		coupon=classdata.join('，');
		Puttingdata=strarlist.join('，');
	}
	$(this).parent().parent().find('.form-control').attr('value',Puttingdata);//名字
	$(this).parent().parent().find('.form-control-class').attr('value',coupon);//icon class
	$(this).parent().parent().css({'height':'auto','border':'none'});
	$(this).parent().parent().find('.checkBox').hide();
	$(this).hide();
})


