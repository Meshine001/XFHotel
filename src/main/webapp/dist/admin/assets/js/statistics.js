


$(document).ready(function(){
	var value=[];
	var echart=echarts.init(document.getElementById('rzoom'));
    var option={
        	baseOption:{
	            title:{
	                text:'房源入住率统计',
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
	                barWidth :50, 
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
			echart.hideLoading(); 
			var name=[];
		
			for(var i=0;i<data.content.length;i++){
					name.push(data.content[i].position.xa_wei_zhi+data.content[i].position.xiao_qu+data.content[i].position.men_pai);
					getOrderNumber(data.content[i].id)
			} 
			echart.setOption({
	            xAxis:{
	                data:name
	            },
	            series:{
	            	data:value
	            }
			})
			console.log(value)
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
			url : 'http://www.yiyunzn.xyz/mobile/getLandlord',//http://localhost/mobile/getLandlord
			data : { 'id' : id },
			async:false,
			success : function(e) {
				value.push(e.length);
			}
		})
	}			
	
    window.onresize = echart.resize;

    echart.setOption(option);
})
