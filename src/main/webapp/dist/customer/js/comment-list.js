/**
 * 加载评论
 * 
 * @param data
 * @returns
 */
function appendComments(comment) {
	var dp_box = $('<div/>').addClass('dp_box clearfix mt_10').appendTo(
			$('.comment_list'));
	var img = $('<img/>').addClass('dp_pic72').appendTo(dp_box);
	var dp_con = $('<div/>').addClass('dp_con').appendTo(dp_box);

	var h6 = $('<h6/>').appendTo(dp_con);
	$.ajax({
		url : $('.comment_list').attr('data-url') + "/customer/detailsData?id="
				+ comment.fromWho,
		method : 'GET',
		dataType : 'json',
		success : function(data) {
			$(img).attr(
					'src',
					$('.comment_list').attr('data-url') + '/images/'
							+ data.details.avatar);
			var nick;
			if(data.details.nick == '' || data.details.nick == undefined){
				nick = '匿名用户';
			}else{
				nick = data.details.nick;
			}
			var hHtml = "<a><span class='col_pink'>" + nick
					+ "</span></a>入住时间：  <i>" + comment.entryTime + "</i>";
			$(h6).html(hHtml);
		}
	});

	$('<div/>').addClass('feel').text(comment.feel).appendTo(dp_con);
	var pic_show = $('<ul/>').addClass('pic_show clearfix').appendTo(dp_con);
	$.each(comment.pics, function(i, pic) {
		var li = $('<li/>').appendTo(pic_show);
		var a = $('<a/>').addClass('thumbimg detail_comment_thumbimg').attr(
				'href', 'javascript:void(0);').appendTo(li);
		$('<img/>').attr('src',
				$('.comment_list').attr('data-url') + '/images/' + pic)
				.appendTo(a);
	});

	if (comment.reply != null) {
		var reply_box = $('<div/>').addClass('reply_box').appendTo(dp_con);
		$('<div/>').addClass('arrow_top').appendTo(reply_box);
		$('<h5/>').text('管家回复：').appendTo(reply_box);
		$('<p/>').text(comment.reply).appendTo(reply_box);
	}

}
/**
 * 页码翻页
 * 
 * @param page
 * @returns
 */
function jumpToPage(page) {
	$('.comment_list').attr('data-page', page);
	$('.comment_list').html('');

	var url = $('.comment_list').attr('data-url') + "/comment/get?roomId="
			+ $('.comment_list').attr('data-room') + "&page="
			+ $('.comment_list').attr('data-page');

	getComments(url);
}

/**
 * 设置页码
 * 
 * @param data
 * @returns
 */
function setPage(data) {
	var pagination = $('<div/>').addClass('pagination_v2 pb0_vou').appendTo(
			$('.comment_list'));
	for (var i = 1; i <= data.pageNo; i++) {
		if (i == data.currentPage) {
			$('<span/>').addClass('active_link').text(i).appendTo(pagination);
		} else {
			$('<a/>').attr('target', '_self').attr('href', '#ongo').attr(
					'onclick',
					'javascript: jumpToPage(' + i + ');return false;').text(i)
					.appendTo(pagination);
		}
	}
	if(data.pageNo > 4){
		$('<a/>').attr('target', '_self').attr('href', '#ongo').attr(
				'onclick',
				'javascript: jumpToPage('
						+ ($('.comment_list').attr('data-page') + 1)
						+ ');return false;').text(">").appendTo(pagination);
	}
	
}

/**
 * 获取评论
 * 
 * @param page
 *            页码
 * @returns
 */
function getComments(url) {
	$.ajax({
		url : url,
		method : 'GET',
		dataType : 'json',
		success : function(data) {
			$.each(data.results, function(i, comment) {
				appendComments(comment);
			});
			// 页码
			setPage(data);
		}
	});
}

//加载第一页
jumpToPage(1);



