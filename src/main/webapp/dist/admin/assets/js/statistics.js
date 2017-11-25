


$(document).ready(function(){
	var hoidarray=[];
	var houseName=[];
	var value=[];
	var name=[];
	var pricesCon="";

	
	var echart=echarts.init(document.getElementById('rzoom'));
    var option={
        	baseOption:{
	            title:{
	                text:'房屋入住统计',
	                subtext:'总订单'
	            },
	
	            xAxis:{
	                data:[],
	                axisLabel: {  
	                	   interval:0,  
	                	   rotate:20  
	                	}  
	            },
	            yAxis:{
	
	            },
	            tooltip:{
	                show:true,
	                formatter:'名称:{b}<br />订单量:{c}个订单'
	            },
	            series:[{
	                name:'订单量',
	                type:'bar',
	                barWidth :50, 
	                data:[],
	                itemStyle: {
	                    normal: {
	                        color: function(params) {
	                            var colorList = [
	                              '#5e7e54','#e44f2f','#81b6b2','#eba422','#5e7e54',
	                               '#e44f2f','#81b6b2','#eba422','#5e7e54','#e44f2f'
	                            ];
	                            return colorList[params.dataIndex]
	                        },
	　　　　　　　　　　　　　
	                    }
	                }
	            }]
        },
        media:[
            {
                query:{
                    maxWidth:1220
                },
                option:{
                    title:{
                        show:true,
                    }
                }
            }
        ]
    };
   // window.onresize = echart.resize;
    echart.setOption(option);
    
    echart.showLoading();
	var uid=window.localStorage.getItem('uid');
	$.ajax({
		type:'post',
		dataType:'json',
		data:{'id':uid},
		url:'/admin/user/steward',
		success:function(data){			
			echart.hideLoading(); 
			
			
			for(var i=0;i<data.content.length;i++){
					name.push(data.content[i].position.xa_wei_zhi+data.content[i].position.xiao_qu+data.content[i].position.men_pai);
					getOrderNumber(data.content[i].id)
					hoidarray.push(data.content[i].id)
					houseName.push(data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+data.content[i].position.men_pai)
			} 
			echart.setOption({
	            xAxis:{
	                data:name
	            },
	            series:{
	            	data:value
	            }
			})
			 getHouse.commonGetsr(data.content.length , hoidarray , houseName);
		},
		error:function(data){
			alert('图表请求数据失败！')
			echart.hideLoading(); 
		}
	})
 
    function getOrderNumber(id){	
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'http://www.yiyunzn.xyz/mobile/getLandlord',
			data : { 'id' : id },
			async:false,
			success : function(e) {
				value.push(e.length);
			}
		})
	};			

    
    
    
    
    

    //房子数量  
    
    var getHouse={
    	commonGetsr:function(nu,id,name){
    		var laiber="";
    		var myChart;
 	        var mydate=new Date();
	        var thisyear=mydate.getFullYear();
	        var lastyear=mydate.getFullYear()-1;
	        var thismonth=mydate.getMonth()+1;
	        var thisday=mydate.getDate();
	        var months=[];
	        var prices=[];
	        var years=[];
	        var _hid="";
	        var _name="";
	        for(var a=1;a<=12;a++){
	        	months.push(a+'月')
	        	prices.push(a)
	        }
	    	var _str=""	
	    	for(var i=0;i<name.length;i++){
	    		_str+='<li hid="'+id[i]+'">'+name[i]+'</li>'
	    	}	
	    	$("#everydata .houseListcon").append(_str);
	    	$("#everydata .houseListcon li:eq(0)").addClass('ace');
	    	
	    	$("#yearDate").html('');
	    	var eu="";
	    	for(var i=0;i<2;i++){
	    		eu+='<option date="'+parseInt(thisyear-i)+'">'+parseInt(thisyear-i)+'年</option>';
	    	}
	    	$("#yearDate").append(eu);
	    	
	    	
	    	for(var i=0;i<$("#everydata .houseListcon li").length;i++){
	    		if($("#everydata .houseListcon li").eq(i).hasClass('ace')==true){
	    			_hid=$("#everydata .houseListcon li").eq(i).attr('hid');
	    			_name=$("#everydata .houseListcon li").eq(i).text();
	    		}
	    	}
	    	
	    	
	    	date(_hid,$('#yearDate option:selected').attr('date'),$("#everydata .houseListcon li:eq(0)").text())
	    	
	    	$("#everydata .houseListcon").on('click','li',function(){
	    		$(this).addClass('ace').siblings().removeClass('ace');
	    		_hid=$(this).attr('hid')
	    		date(_hid,$('#yearDate option:selected').attr('date'),$(this).text());
	    	})
	    		    	
	    	$("#yearDate").on('change',function(){
	    		date(_hid,$('#yearDate option:selected').attr('date'),_name)
	    	})
	    	
	    	

	    function date(id,year,name){
	    		console.log(id+'^^^'+year+'^^^'+name)
				var echart=echarts.init(document.getElementById('post_detail'));
		        var option={
		            baseOption:{
		                title:{
		                    text:name,
		                    subtext:thisyear+'年/'+'月收入情况：'
		                },
		                legend:{
		                    data:[]
		                },
		                xAxis:{
		                    data:months
		                },
		                yAxis:{

		                },
		                tooltip:{
		                    show:true,
		                    formatter:'月份:{b}<br />收入:{c}元'
		                },
		                series:[{
		                    type:'bar',
		                    data:[]
		                }]
		            },
		            media:[
		                {
		                    query:{
		                        maxWidth:1220
		                    },
		                    option:{
		                        title:{
		                            show:true,
		                        }
		                    }
		                }
		            ]
		        };
		        window.onresize = echart.resize;
		        echart.setOption(option);
		        echart.showLoading();
	    		$.ajax({
	            		type:'post',
	            		dataType:'json',
	            		data:{
	            			'year' : year,
	            			'roomId': id
	            		},
	            		url:'/order/getOrderSum',
	            		success:function(data){
	            			console.log(data)
	            			if(data.statusCode==1){
	            				echart.setOption({
	            		            series:{
	            		            	data:data.content
	            		            }
	            				})
	            			}
	            			echart.hideLoading(); 
	            		}
	            	})
		        
		        
	    }

	        
	        
	        
	        
	        
     
	        
	        

	        
	        
	        

                  
            
    	} 
	}
    

   
  
    
})

