$(document).ready(function(){

	
	var show = function(data){
		for(var i=0;i<3;i++){
			var listBox = $('<div></div>').attr('class','col-md-12 column');
			
			//img
			var imgUrl = 'http://localhost:8080/hotel/dist/customer/img/apartment-01.jpg';
			var img = $('<img></img>').attr('class','img-responsive').attr('src',imgUrl);
				img.appendTo($('<div></div>').attr('class','col-md-4 column').appendTo(listBox));
				
			var right = $('<div></div>').attr('class','col-md-8 column');
			
			var right_row1 = $('<div></div>').attr('class','row clearfix');
			right_row1.appendTo(right);
			var right_row2 = $('<div></div>').attr('class','row clearfix');
			right_row2.appendTo(right);
			
			
			//title
			var rightTitle = "安和小区5居室-南卧-C房间";
			$('<h3></h3>').text(rightTitle).appendTo(
					$('<div></div>').attr('class','col-md-12 column').appendTo(
							right_row1
					)	
			);
			
			var right_row2_12 = $('<div></div>').attr('class','col-md-12 column');
			 right_row2_12.appendTo(right_row2);
			 
			var right_row2_12_8 = $('<div></div>').attr('class','col-md-8 column');
			right_row2_12_8.appendTo(right_row2_12);
			
			var ul = $('<ul></ul>');
			ul.appendTo(right_row2_12_8);
			//features
			var featureLi = $('<li>风格</li>');
			var features = ['芷青村','piao liang'];
			$.each(features,function(index,data){
				$('<span></span>').text(data).appendTo(featureLi);
			});
			featureLi.appendTo(ul);
			
			//floor
			var floor = '第26层/共32层';
			$('<span></span>').text(floor).appendTo(
					$('<li>楼层</li>').appendTo(ul)	
			);
			
			//address
			var address = '郑东新区文苑南路与邢庄北街交叉口';
			$('<span></span>').text(address).appendTo(
					$('<li>地址</li>').appendTo(ul)	
			);
			
			var right_row2_12_4 = $('<div></div>').attr('class','col-md-4 column');
			right_row2_12_4.appendTo(right_row2_12);
			
			//price
			var oriPrice = '原价￥1422/月';
			var price = '1302';
			$('<h3></h3>').text(price).append($('<small>元/月</small>')).appendTo(right_row2_12_4);
			$('<h3></h3>').text(oriPrice).appendTo(right_row2_12_4);
			
			right.appendTo(listBox);
			listBox.appendTo($('.listbox'));
		}
		
		
		
		
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
				show(data);
			}
		});
	};
	
	search();
});