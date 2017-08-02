'use strict';

$(document).ready(function(){
	$(".top-nav-wrap a").click(function(){
		$(this).addClass('atl').siblings().removeClass('atl');
	})
	$(".top-nav-wrap a:eq(0)").click(function(){
		$(".houselist").show();
		$(".msg-house-list").hide();
	})
	$(".top-nav-wrap a:gt(0)").click(function(){
		$(".houselist").hide();
		$(".msg-house-list").show();
	})
	
});
