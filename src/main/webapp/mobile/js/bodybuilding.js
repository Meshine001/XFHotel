
$(document).ready(function(){
	var stp="0";
	
    
    
      //addFitness购买；
      //getFitness查询；
      
      

    $(".link-btn").bind('click').click(function(){
    	if(stp==0){
    		$(".ac-gn-bagview").show();
    		$("#masking").fadeIn();
    		stp=1;
    	}else if(stp==1){
    		$(".ac-gn-bagview").fadeOut();
    		$("#masking").hide();
    		stp=0;
    	}
    })
    $("#masking").click(function(){
    	$(this).fadeOut();
    	$(".ac-gn-bagview,#my-alert-views").hide();
    	stp=0;
    })
    
      
    
    
  
});
