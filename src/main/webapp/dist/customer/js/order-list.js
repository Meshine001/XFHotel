
        function showOrder(item){

            var mainorder =$('<div></div>').addClass('mainorder order-pay').appendTo($('<div></div>').addClass("order-bought").appendTo($('.order-list')));
            var hd = $('<div></div>').addClass('mainorder-hd').appendTo(mainorder);
            $('<span></span>').addClass('column').text(item.type).appendTo(hd);
            $('<span></span>').addClass('first').text(item.id+'|'+item.timeStr).appendTo(hd);
             $('<span></span>').attr('title',item.preferential).text(item.preferential).appendTo($('<span></span>').attr('style','z-index:0;').addClass('mechanism').appendTo(hd));
            var main = $('<div></div>').addClass('mainorder-main').appendTo(mainorder); 
            var tr = $('<tr></tr>').appendTo($('<tbody></tbody>').appendTo($('<table></table>').addClass('order-bd clearfix').appendTo(main)));
            var product = $('<div></div>').addClass('hotel-order product-fist clearfix').appendTo($('<td></td>').addClass('product').appendTo(tr));
            $('<img/>').attr('src','../images/'+item.apartment.pic2[0]).appendTo($('<p/>').addClass('hotel-pic').appendTo(product));
            var allTitle = $('<dl/>').addClass('all-title').appendTo(product);
            $('<dt/>').text(item.description).appendTo(allTitle);
            $('<dt/>').appendTo(allTitle);
            $('<i/>').addClass('name-icon icon-zhu').appendTo($('dd').text(item.startTime).appendTo(allTitle));
            $('<i/>').addClass('name-icon icon-li').appendTo($('dd').text(item.endTime).appendTo(allTitle));
            var qunatity = $('<td/>').addClass('qunatity').appendTo(tr);
            var qHtml = "<em style='font-family:arial;'>¥</em>" + item.price + 'x'+item.totalDay+'天'
            + '<br><br><br>'+ item.needFapiao;
            qunatity.html(qHtml);
            var pHtml =  "<em style='font-family:arial;'>¥</em>" + item.totalPrice;
            $('<li/>').html(pHtml).appendTo($('<ul/>').addClass('orderbd-list').appendTo($('<td/>').addClass('price').appendTo(tr)));
            $('<p/>').text(item.status).appendTo($('<td/>').addClass('state').appendTo(tr));
            var remarks = $('<td/>').addClass('remarks').appendTo(tr);
            $('<a/>').addClass('order-action').text('查看详情').appendTo($('<p/>').appendTo(remarks));
            $('<a/>').addClass('order-btn').text('我要点评').appendTo($('<p/>').appendTo(remarks));
        }

        function search(){
            var form = $('#search-form').serialize();
            var url = '../order/search';
            $.ajax({
                type : "GET",
                url : url,
                data: form,
                dataType:'json',
                error : function(request) {
                    console.log(request);
                    alert("连接异常！");
                },
                success : function(data) {
                    $('.order-list').empty();
                    if (data.statusCode == 0) {
                        
                    } else {
                      $(data.content).each(function(index,item){
                        showOrder(item);
                      });
                    }

                }
            });
        }

		$(document).ready(function(){
			$('#order-type-sel-wrapper').click(function(){
				$('#order-type-sel-list').show();
			});

			$('#startDate').datepicker();
            $('#endDate').datepicker();

            $('#order-time-list').click(function(e){
                var target = e.target;
                if ($(target).hasClass("range")) {
                     var parent = $(target).parent();
                     parent.parent().find('li').each(function(index,li){
                        $(li).attr('class','');
                     });
                     parent.attr('class','cur'); 
                     $('#range').val($(target).attr('data-range'));
                     search();
                };
               
            });
            $('.searchbar').change(function(e){
               // var target = e.target;
                search();
            });
            
            search();
            
		});