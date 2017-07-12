"use strict";
	var date =function($){
		$.fn.hotelDate = function(options){
			var nowdate = new Date(); // 获取当前时间
			var dateArr = new Array(); // 获取到的时间数据集合
			var btn = $(this);
			btn.on('click', initTemplate); // 初始化(显示)插件
			
			function initTemplate(){
				 var listIndex = 0;
				 $('#statusDate').css({
					overflow: 'hidden'
				  });
				  // 主容器模板
				  var dateTemplate = '\n        <div class =\'date container c-gray\'>\n        </div>      \n      ';

				  $('#statusDate').append(dateTemplate); 

				  // action容器模板
				  dateArr.forEach(function (item, index) {
					var template = '\n          <div class=\'action mt10\'>\n            <div class=\'title tac c-blue\'><div class="y">' + item.getFullYear() + '</div>\u5E74<div class="m">' + (item.getMonth() + 1) + '</div>\u6708</div>\n            <ul class=\'week border-bottom\'><li>\u65E5</li><li>\u4E00</li><li>\u4E8C</li><li>\u4E09</li><li>\u56DB</li><li>\u4E94</li><li>\u516D</li></ul>\n            <ul class=\'day f-small\'></ul>\n          </div>        \n        ';
					$('.date').append(template);
				  });
				  
				   // 天数模板 
				  $('.action').each(function (index, item) {

					var days = getDays(dateArr[index]); // 当月天数
					var nowweek = dateArr[index].getDay(); // 当月1号是星期几
					console.log(days)
					for (var i = 0; i < days + nowweek; i++) {
					  var template = '';
					  // 空白填充
					  if (i < nowweek) {
						template = '<li></li>';
					  } else {
						listIndex++;
						
						template = '<li index="' + listIndex + '" date-date="' + dateArr[index].getFullYear() + '-' + (dateArr[index].getMonth() + 1) + '-' + (i - nowweek + 1) + '">' + (i - nowweek + 1) + '</li>';
					  }
					  $(item).find('.day').append(template);
					}
				  });
				
			}
			
			 // 获取num个月的时间数据
			function getDate(num) {

			  var year = nowdate.getFullYear();
			  var month = nowdate.getMonth() - 1;

			  for (var i = 0; i < num; i++) {
				month <= 12 ? month++ : (month = 1, year++);
				var data = new Date(year, month); // 从当前月开始算 
				dateArr.push(data);
			  }
			}
			
			// 获取当月天数
			function getDays(date) {
			  //构造当前日期对象
			  var date = new Date(date);
			  //获取年份
			  var year = date.getFullYear();
			  //获取当前月份
			  var mouth = date.getMonth() + 1;
			  //定义当月的天数；
			  var days;
			  //当月份为二月时，根据闰年还是非闰年判断天数
			  if (mouth == 2) {
				days = year % 4 == 0 ? 29 : 28;
			  } else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
				//月份为：1,3,5,7,8,10,12 时，为大月.则天数为31；
				days = 31;
			  } else {
				//其他月份，天数为：30.
				days = 30;
			  }
			  return days;
			}

			getDate(2); 
			
		}
	}(jQuery)