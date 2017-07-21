

$(document).ready(function(){
	$("#head-nav li").click(function(){
		var i=$(this).index();
		$(this).css("color",'#f5a308').siblings().css("color",'#fff');
		$(".init .init-section").eq(i).show().siblings().hide();
		
	})
	getint();
	function getint(){
		var id = decodeURIComponent(fnBase.request("id"));
		var int = decodeURIComponent(fnBase.request("int"));
		if(int==0){
			$("#nickName i").text('您还没有可用积分')
		}else{
			$("#nickName i").text(int)
		}
	}
	
	
});
