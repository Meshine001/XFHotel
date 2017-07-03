

$(document).ready(function(){
	$("#head-nav li").click(function(){
		var i=$(this).index();
		$(this).css("color",'#f5a308').siblings().css("color",'#fff');
		$(".init .init-section").eq(i).show().siblings().hide();
	})
	
	
	
});
