 

    var config = {
        modules: {
            'price-calendar': {
                fullpath: 'js/price-calendar.js', //根据项目路径调整
                type    : 'js',
                requires: ['price-calendar-css']
            },
            'price-calendar-css': {
                fullpath: 'css/price-calendar.css', //根据项目路径调整
                type    : 'css'
            }
        }
    };

    var data = {
        "2017-03-13": {
            "price"  : "100",
            "roomNum": "1"
        },
        "2017-03-14": {
            "price"  : "120",
            "roomNum": "1"
        },
        "2017-03-15": {
            "price"  : "150",
            "roomNum": "1"
        }  
    }

    YUI(config).use('price-calendar', function(Y) {
    var oCalendar = new Y.PriceCalendar();    
    //初始化日历
    //点击确定按钮
    oCalendar.on('confirm',function(){
        alert('入住时间：'+this.get('depDate')+',离店时间:  '+this.get('endDate'));
    });
    //点击取消按钮
    oCalendar.on('cancel',function(){
        this.set('depDate','').set('endDate','').render();
    });
    
    oCalendar.set('data',null)
            .set('depDate','')
            .set('endDate','')
            .set('minDate','')
            .set('afterDays','');
    //设置从今天算起
    var today = new Date();
    oCalendar.set('date',today)
    .set('minDate',today);
    // .set('data',data);
    oCalendar.render();




    });