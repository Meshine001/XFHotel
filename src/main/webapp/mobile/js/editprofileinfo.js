
$(document).ready(function(){
    var sexVal='';
    var sexbtn=$("#Myscroll-body section .sex");
    sexbtn.live('click',function(){
        $("#masking").show(10,function(){
            $(".alert-content").animate({bottom:0},300);
        });
    });
    $(".alert-content ul li").click(function(){
        sexVal=$(this).text();
       $(this).addClass("selected").siblings().removeClass("selected");
        $("#masking").hide(10,function(){
            $(".alert-content").animate({bottom:'-1.8rem'},300);
        });
        sexbtn.find('.fl').html(sexVal);
    });


    $("#inputfile").change(function(){
        //����FormData����
        var data = new FormData();
        //ΪFormData�����������
        //
        $.each($('#inputfile')[0].files, function(i, file) {
            data.append('upload_file', file);
        });
        $.ajax({
            url:'http://hongjiu.intplus.com/api/goods?code=303',
            type:'POST',
            data:data,
            cache: false,
            contentType: false,    //����ȱ
            processData: false,    //����ȱ
            success:function(data){
                console.log(data);
                if($("#feedback").children('img').length == 0) $("#feedback").append(data.replace(/&lt;/g,'<').replace(/&gt;/g,'>'));
                else
                    $("#feedback").children('img').eq(0).before(data.replace(/&lt;/g,'<').replace(/&gt;/g,'>'));
            }
        });
    });

});
