var echart=echarts.init(document.getElementById('rzoom'));
$(document).ready(function(){

	$("#rzoom").css( 'width', $("#rzoom").width());
	

    var option={
        	baseOption:{
	            title:{
	                text:'全部房源入住率统计',
	                subtext:'入住率'
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
	                formatter:'系列名:{a}<br />名称:{b}<br />订单量:{c}'
	            },
	            series:[{
	                name:'订单量',
	                type:'bar',
	                barWidth :60, 
	                data:[]
	            }]
        },
        media:[
            {
                //小与1000像素时候响应
                query:{
                    maxWidth:1000
                },
                option:{
                    title:{
                        show:true,
                    }
                }
            }
        ]
    };
    echart.showLoading();
	var uid=window.localStorage.getItem('uid');
	$.ajax({
		type:'post',
		dataType:'json',
		data:{'id':uid},
		url:'/admin/user/steward',
		success:function(data){
			console.log(data)
			
			echart.hideLoading(); 
			var name=[];
		
			for(var i=0;i<data.content.length;i++){
					name.push(data.content[i].position.xa_wei_zhi+data.content[i].position.xiao_qu+data.content[i].position.men_pai);
			//		value.push(data.content[i].id);
					getOrderNumber(data.content[i].id)
			} 
			echart.setOption({
	            xAxis:{
	                data:name
	            }
			})
			
		},
		error:function(data){
			alert('图表请求数据失败！')
			echart.hideLoading(); 
		}
	})
    var value=[];
    function getOrderNumber(id){
		
		$.ajax({
			type : 'POST',
			dataType : 'json',
			url : 'http://localhost/mobile/getLandlord',// http://www.yiyunzn.xyz/mobile/getLandlord
			data : { 'id' : id },
			async:false,
			success : function(e) {
				value.push(e.length);
				console.log(e)
//				echart.setOption({
//				            series:{
//				            	data:value
//				            }
//				})
			}
		})
	}
//	var value=[];				
	console.log(value)
	
    window.onresize = echart.resize;

    echart.setOption(option);
})
