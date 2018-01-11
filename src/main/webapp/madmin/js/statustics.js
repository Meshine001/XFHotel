


$(document).ready(function(){
	
	$(".layer-select a").click(function(){
		$(this).addClass('show').siblings().removeClass('show');
		var i=$(this).index();
		if(i==0){
			$(".content .houseList").eq(0).show();
			$(".content .houseList").eq(1).hide();
		}else{
			$(".content .houseList").eq(0).hide();
			$(".content .houseList").eq(1).show();
		}
	})

	
	
	
	
	var hoidarray=[];
	var houseName=[];
	var name=[];
	var pricesCon="";
    var months=[];
    var jd='';
	var jj=[];
	var vm=[];
	var item="";
    var mydate=new Date();
    var thisyear=mydate.getFullYear();
    var thismonth=mydate.getMonth()+1;
    function p(s) {
    	return s < 10 ? '0' + s: s;
    }
    
    for(var a=1;a<=12;a++){
    	months.push(a+'月')
    }
	$("#statusyear").html('');
	var eu="";
	for(var i=0;i<2;i++){
		eu+='<option date="'+parseInt(thisyear-i)+'">'+parseInt(thisyear-i)+'年</option>';
	}
	$("#statusyear").append(eu);
    
    
    $("#statusmoth").html('');
    var monthre=months.reverse();
	for(var j=0;j<months.length;j++){
		jd+='<option date="'+parseInt(monthre[j])+'">'+monthre[j]+'</option>';
	}
	$("#statusmoth").append(jd);
    
	for(var i=0;i<$("#statusmoth option").length;i++){
		if($("#statusmoth option").eq(i).attr('date')==thismonth){
			$("#statusmoth option").eq(i).attr('selected','selected')
			$("#statusmoth option").eq(i).attr('date')
		}
	}
	
	
	
    var uid=window.localStorage.getItem('uid');
	$.ajax({
		type:'post',
		dataType:'json',
		async:false,
		data:{'id':uid},
		url:'/admin/user/steward',
		success:function(data){	
			console.log(data)
			for(var i=0;i<data.content.length;i++){
					name.push(data.content[i].position.xa_wei_zhi+data.content[i].position.xiao_qu+data.content[i].position.men_pai);
					hoidarray.push(data.content[i].id)
					houseName.push(data.content[i].position.xa_wei_zhi+'-'+data.content[i].position.xiao_qu+data.content[i].position.men_pai)
			}
			 getHouse.commonGetsr(data.content.length , hoidarray , houseName);

		},
		error:function(data){
			alert('图表请求数据失败！')
		}
	})

	var echart=echarts.init(document.getElementById('rzoom'));
	echart.width=window.innerWidth+'px';
	    var option={
	        	baseOption:{
		            title:{
		                text:'房屋入住统计',
		                subtext:'入住天数'
		            },
		            xAxis:{
		                data:[],
		                axisLabel: {  
		                	   interval:0,  
		                	   rotate:40  
		                	}  
		            },
		            yAxis:{
		
		            },
		            lable:{
		                normal:{
		                    textStyle:{
		                        fontsize:'0.12rem'
		                    }
		                }
		            },
		            tooltip:{
		                show:true,
		                formatter:'名称:{b}<br />入住:{c}天'
		            },
		            series:[{
		            	data:[],
		            	name:'订单量',
		                type:'bar',
		                barWidth :10, 
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
    window.onresize = echart.resize;
	    echart.setOption(option,true); 
	

	Occupancyrate($("#statusyear option:selected").attr('date'),$("#statusmoth option:selected").attr('date'),01)
	
	$("#statusyear").on('change',function(){
		Occupancyrate($("#statusyear option:selected").attr('date'),$("#statusmoth option:selected").attr('date'),01)
	})
	
	$("#statusmoth").on('change',function(){
		Occupancyrate($("#statusyear option:selected").attr('date'),$("#statusmoth option:selected").attr('date'),01)
	})
	
	
	
	
    function Occupancyrate(y,m,d){	
		echart.showLoading();
		var _namess=[];	
		var value=[];
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : '../order/SetOrderFate',
			data : {
				 'date' : y+'-'+m+'-'+d
			},
			success : function(data) {
				console.log(data)
				for(var i=0;i<data.content.length;i++){
					for(var k in data.content[i]){  
						item = data.content[i][k];
						_namess.push(k)
						value.push(item);
					}
				}
				echart.setOption({
		            xAxis:{
		                data:_namess
		            },
					series:{
		            	data:value
		            }
				})
				echart.hideLoading(); 
			}
		})
		
	};	
	
	

    
   
	
	

    
    
    
    
    

  
    

   
  
    
})
 
    
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
	    	$(".houseListcon").append(_str);
	    	$(".houseListcon li:eq(0)").addClass('ace');
	    	
	    	$("#yearDate").html('');
	    	var eu="";
	    	for(var i=0;i<2;i++){
	    		eu+='<option date="'+parseInt(thisyear-i)+'">'+parseInt(thisyear-i)+'年</option>';
	    	}
	    	$("#yearDate").append(eu);

	    	
	    	for(var i=0;i<$(".houseListcon li").length;i++){
	    		if($(".houseListcon li").eq(i).hasClass('ace')==true){
	    			_hid=$(".houseListcon li").eq(i).attr('hid');
	    			_name=$(".houseListcon li").eq(i).text();
	    		}
	    	}
	    	
	    	
	    	date(_hid,$('#yearDate option:selected').attr('date'),$("#everydata .houseListcon li:eq(0)").text())
	    	
	    	$(".houseListcon").on('click','li',function(){
	    		$(this).addClass('ace').siblings().removeClass('ace');
	    		_hid=$(this).attr('hid')
	    		date(_hid,$('#yearDate option:selected').attr('date'),$(this).text());
	    	})
	    		    	
	    	$("#yearDate").on('change',function(){
	    		date(_hid,$('#yearDate option:selected').attr('date'),_name)
	    	})
	    	
	    	

	    function date(id,year,name){
	    		console.log(months)
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
		                    data:months,
		                    "axisLabel":{  
		                        interval: 0,
		                        rotate:40  
		                    } 
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
