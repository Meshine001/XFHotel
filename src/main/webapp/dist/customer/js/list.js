$(document).ready(function(){

	
	var formatList = function(){
		$('.listbox-item').addClass('col-md-12 column');
		$('.listbox-imgbox').addClass('col-md-4 column');
		$('.listbox-details').addClass('col-md-8 column');
		$('.listbox-details-title').addClass('row clearfix');
		$('.listbox-details-body').addClass('row clearfix');
		
		$('.listbox-details-custom-price').addClass('col-md-12 column');
		$('.listbox-details-custom').addClass('col-md-8 column');
		$('.listbox-details-price').addClass('col-md-4 column');
		$('.listbox-details-features').addClass('col-md-12 column');
		$('.listbox-details-description').addClass('col-md-12 column').css('margin-top','10px');
	};
	
	
	var show = function(data){
		var listbox = $('.listbox');
		console.log(data);
		$.each(data,function(index,room){
			if(room.prices[0] == '-1'){// 还未设置价钱的房间不显示
				return true;﻿
			}else{
				var apartment;
				$.ajax({
					cache : true,
					type : "post",
					dataType : "json",
					data:{'apartmentid':room.apartment},
					url : "./admin/apartment/getapartment",
					async : false,
					error : function(request) {
					},
					success : function(a) {
						apartment = a;
					}
				});
				var imgUrl = './images/'+apartment.pic2[0];
				var title = apartment.community+apartment.bedroom+'室'
				+apartment.livingroom+'厅';
				
				
				var customType = apartment.apartmenttype;
				var floor = '第'+apartment.floor+'层/共'+apartment.totalfloor+'层';
				var square = apartment.square+'平方';
				var derection = apartment.direction;
				
				var address = apartment.location;
				
				var features = apartment.features;// 缺数据
				
				var price = room.prices;
				var leasyType = '日';
				var oldPrice = '';
				
				
				
				
				var description = apartment.description;
				
				
				var item = $('<div></div>').addClass('listbox-item').css('margin-top','20px').appendTo(listbox);
				
				// img
				var ancher = $('<a></a>').attr('href','./info/'+room.id).appendTo(item);
				$('<img></img>').attr('src',imgUrl).addClass('img-responsive').appendTo($('<div></div>').addClass('listbox-imgbox').appendTo(ancher));
				
				var details = $('<div></div>').addClass('listbox-details').appendTo(item);
				// title
				var titleRow = $('<div></div>').addClass('listbox-details-title').appendTo(details);
			
				$('<h3></h3>').text(title).appendTo($('<div></div>').addClass('col-md-12 column').appendTo(titleRow));
				
				// details body
				var detailsBody = $('<div></div>').addClass('listbox-details-body').appendTo(details);
				
				// custom and price
				var customAndPrice = $('<div></div>').addClass('listbox-details-custom-price').appendTo(detailsBody);
				var customRow = $('<div></div>').addClass('listbox-details-custom').appendTo(customAndPrice);
				var customUl = $('<ul></ul>').appendTo(customRow);
				// custom
			
				$('<li>类型：</li>').append($('<span></span>').text(customType)).appendTo(customUl);
				
				$('<li>'+'楼层：'+ '<span>'+floor+'</span> '+'面积：'+ '<span>'+square+'</span> '+'朝向：'+ '<span>'+derection+'</span>'+'</li>').appendTo(customUl);
				
				$('<li>地址：</li>').append($('<span></span>').text(address)).appendTo(customUl);
				
				// price

				var priceRow = $('<div></div>').addClass('listbox-details-price').appendTo(customAndPrice);
				$('<h3></h3>').text(price).append($('<small></small>').text('元/'+leasyType)).appendTo(priceRow);
				$('<h4></h4>').text('原价：￥'+oldPrice+'/'+leasyType).appendTo(priceRow).hide();// TODO
																								// 还没做打折
				
				// features
				var featuresRow = $('<div></div>').addClass('listbox-details-features').appendTo(detailsBody);
				
				
				
				$.each(features,function(index,item){
					$('<span></span>').addClass('label label-default').text(item.description).appendTo(featuresRow);
				});
				
				// description
				var descriptionRow = $('<div></div>').addClass('listbox-details-description').appendTo(detailsBody);
				
				$('<p></p>').text(description).appendTo(descriptionRow);
			}
			
		});
		
		formatList();
		
	};
	
	
	var search = function(){
		$.ajax({
			cache : true,
			type : "GET",
			dataType : "json",
			url : "./search",
			async : false,
			error : function(request) {
				alert("连接异常！");
			},
			success : function(data) {
				if(data.statusCode == 1){
					show(data.content);
				}
				
			}
		});
	};
	
	search();
});