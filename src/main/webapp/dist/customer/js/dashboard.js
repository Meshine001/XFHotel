$(document).ready(function() {
	var location = window.location.pathname;
	var li = location.substring(location.lastIndexOf("/")+1,location.lenth);
	activeMenue(li);
});

function activeMenue(e) {
	$("#sidebar li").removeClass("active");
	$("#"+e).addClass("active");
}