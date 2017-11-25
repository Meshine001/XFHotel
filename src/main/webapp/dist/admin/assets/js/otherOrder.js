


$(document).ready(function(){
    
    var PageUp=$("#otherpagecontroller li:first-child a");
    var PageDown=$("#otherpagecontroller li:first-child+li a");
    var totalPage='';
    var page='1';
    getdata(page);
    
    
    
    
    
    function getdata(page){
    	$.ajax({
    		type:'POST',
    		dataType:'JSON',
    		url:'/admin/getListRests',
    		data:{
    			'page':page
    		},
    		error:function(en){
    			alert('好像出了点错，获取失败。')
    		},
    		success:function(data){
    			console.log(data)
    			
    			if(data.statusCode==1){
    				totalPage=data.content.pageCount
    				$(".navs a").eq(1).find('i').html('('+data.content.totalCount+')条');
    				$("#otherpagecontroller li span").html("共"+data.content.pageCount+"页");
    				$("#otherlist").html("");
    				var str="";
    				for(var i=0;i<data.content.results.length;i++){
    					str+='<tr><td>'+data.content.results[i].source+'</td><td style="color:red">'+data.content.results[i].sum+'/元</td><td>'+data.content.results[i].roomName+'</td><td>'+data.content.results[i].startTime+'</td><td>'+data.content.results[i].endTime+'</td><td>'+data.content.results[i].name+'</td><td>'+data.content.results[i].tel+'</td><td><a oid="'+data.content.results[i].id+'" class="removeOrder btn" style="cursor: pointer">删除</a></td></tr>';

    				}
					if(data.content.results.length<=0){
						str='<tr><td colspan="8" style="color:#ffa500;text-align:center">暂无订单</td></tr>'
					}
    				$("#otherlist").append(str);
    			}
    			
    		}
    	})
    }
    
    
    
    PageUp.click(function(){
    	if(page>1){
        	page--;
        	getdata(page);
    	}

    })
    
    PageDown.click(function(){
    	if(page<totalPage){
        	page++;
        	getdata(page);
    	}
    })
    
    
    $("#otherlist").on('click','tr td .removeOrder',function(){
    	$.ajax({
    		type:'POST',
    		dataType:'json',
    		url:'/admin/deleteRests',
    		data:{
    			'id':$(this).attr('oid')
    		},
    		success:function(data){
    			if(data.statusCode==1){
    				alert(data.content)
    			}else{
    				alert(data.content)
    			}
    			location=location;
    		}
    	})
    })
    
    
});