$(document).ready(
	function(){
				 $("body").hide();
		function zoom() {
				 var html = document.querySelector("html");
		         var rem = html.offsetWidth / 3.75;
		         html.style.fontSize = rem + "px";
			     $("body").show();
			}
			zoom();
			window.onresize = zoom;
			$("body").show();
			
			$("#bottomMenu .innerCon ul li").click(function(){
				$(this).addClass('active').siblings().removeClass('active');
			})
			
	}
);
