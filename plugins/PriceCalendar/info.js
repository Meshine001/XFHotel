


window.DetailMoreCalendar = {
    //{{{
    isAbroad : $('#isAbroad').val() == 1 ? true : false,
    init : function(){
        //{{{
        this.luid = $('#lodgeUnitId').val(); 
        this.editable = true;
        var that = this;
        $('#detailBookArea').on('click','#showMoreCalendar',function(){
            var calendarCode = LodgeUnitCalendarImageCodeValidator.getCalendarCode();
            if(!calendarCode){
                LodgeUnitCalendarImageCodeValidator.showImageCodeBox();
                return false;
            }
            that.getCalendarHtml(new Date(),$('#leftTbody'),$('#leftMonth')); 
            var day = new Date();
            var y = day.getFullYear(); 
            var m = day.getMonth();
            that.getCalendarHtml(new Date(y,m+1,1),$('#rightTbody'),$('#rightMonth'));
            $('#fullCalendarDialog').show(); 
            $('body').css('overflow','hidden');
            $('#showMoreCalendar').attr('bodyposition',$(document).scrollTop());
            //if($('#showMoreCalendar').attr('hideleftarrow') != '1'){
            $('#fullCalendarDialog').find('.arrow_l1').hide();
            //}
            if(DetailMoreCalendar.isAbroad){
                $('.calendar_r').after('<div class="calendar_time">以'+$('#cityName').val()+'时间为准</div>');
            }
        });
        $('#fullCalendarDialog').on('click','.colsed',function(e){
            $('#fullCalendarDialog').hide();     
            $('body').css('overflow','visible');
            //$('#showMoreCalendar').attr('hideleftarrow',1);
            if($('#showMoreCalendar').attr('bodyposition')){
                 $(document).scrollTop($('#showMoreCalendar').attr('bodyposition'));
            }
        })
        .on('click','.arrow_r1',function(){
            $('#fullCalendarDialog').find('.arrow_l1').show();
            var leftYear = $('#leftMonth').attr('y');
            var leftMonth = $('#leftMonth').attr('m');

            var rightYear = $('#rightMonth').attr('y');
            var rightMonth = $('#rightMonth').attr('m');

            that.showLeftCalendar(leftYear,leftMonth-(-1));
            that.showRightCalendar(rightYear,rightMonth-(-1));
        })
        .on('click','.arrow_l1',function(){
            var newDate = new Date();
            var currentMonth = newDate.getMonth();
            var currentYear = newDate.getFullYear();
            var leftYear = $('#leftMonth').attr('y');
            var leftMonth = $('#leftMonth').attr('m');
            if(leftMonth == currentMonth-(-3) && currentYear == leftYear){
                $(this).hide();
            }
            var rightYear = $('#rightMonth').attr('y');
            var rightMonth = $('#rightMonth').attr('m');

            that.showLeftCalendar(leftYear,leftMonth-3);
            that.showRightCalendar(rightYear,rightMonth-3);
        });
        //}}}
    },
    showLeftCalendar : function(leftYear,leftMonth)
    {
        var leftMonthHtml = $('#leftMonth');
        var nowDate = new Date();
        this.getCalendarHtml(new Date(leftYear,leftMonth,1),$('#leftTbody'),leftMonthHtml);
        if(leftMonthHtml.attr('y') == nowDate.getFullYear() && leftMonthHtml.attr('m') == nowDate.getMonth()-(-1) ){
            $('#fullCalendarDialog').find('.arrow_l1').hide();
        } else {
            $('#fullCalendarDialog').find('.arrow_l1').show();
        }
    },
    showRightCalendar : function(rightYear,rightMonth)
    {
        var rightMonthHtml = $('#rightMonth');
        this.getCalendarHtml(new Date(rightYear,rightMonth,1),$('#rightTbody'),rightMonthHtml);
    },
    getCalendarHtml : function(day,tbodynode,month){
        //{{{
        //tbodynode.children().remove();
        day.setDate(1);
        var firstDay = day.getDay();
        var newDate = new Date();
        var today = 0;
        if(newDate.getMonth() == day.getMonth() && newDate.getFullYear() == day.getFullYear()) {
            today = newDate.getDate();
        }

        var dateArray = [];
        for (var i = 0; i < 1; i++) {
            var toMonth = day.getMonth();
            for (var j = 1; j <= 37; j++) {
                day.setDate(j);
                if(j == 1) {
                    var week = day.getDay();
                    for(var k = 0;k < week;k++) {
                        dateArray.push({y:'',m:'',d:'',w:''});
                    }
                }
                if (day.getMonth(true) != toMonth){
                    break;
                }
                dateArray.push({y:day.getFullYear(),m:day.getMonth(),d:day.getDate(),w:day.getDay()});
            };
        };
        month.html(dateArray[15].y+'-'+(dateArray[15].m-(-1)));
        month.attr('y',dateArray[15].y);
        month.attr('m',dateArray[15].m-(-1));

        if(dateArray[15].m <= 7) {
            var startDate = dateArray[15].y + '-0' + (dateArray[15].m + 1) + '-01';
            var endDate = dateArray[15].y + '-0' + (dateArray[15].m + 2) + '-01';
        }else if(dateArray[15].m == 8){
            var startDate = dateArray[15].y + '-0' + (dateArray[15].m + 1) + '-01';
            var endDate = dateArray[15].y + '-' + (dateArray[15].m + 2) + '-01';
        }else if(dateArray[15].m == 11){
            var startDate = dateArray[15].y + '-' + (dateArray[15].m + 1) + '-01';
            var endDate = (dateArray[15].y+1) + '-01-01';
        } else {
            var startDate = dateArray[15].y + '-' + (dateArray[15].m + 1) + '-01';
            var endDate = dateArray[15].y + '-' + (dateArray[15].m + 2) + '-01';
        }
        var calendarCode = LodgeUnitCalendarImageCodeValidator.getCalendarCode();
        if(!calendarCode){
            LodgeUnitCalendarImageCodeValidator.showImageCodeBox();
            return false;
        }
        var url = XZWebUrlWriter.getAJAX_GetLodgeUnitCalendar(this.luid,startDate,endDate,this.editable,calendarCode);
        XZWebAjax.post(url,{},true,function(data){
            var dateHtml = '';
            for(var j = 0; j < 42; j++) {
                if(dateArray[j] == undefined) continue;
                var dayText =  dateArray[j].d;
                if(today != 0 && j == today+firstDay-1 && !DetailMoreCalendar.isAbroad) {
                    dayText = '今日'; 
                }
                if(j >= firstDay) {
                    if(j%7 == 0) {
                        if(today != 0 && j<today+firstDay-1) {
                            dateHtml += '<tr><td class="col_gray">'+dayText+'</td>'; 
                        } else {
                            if(data[j-firstDay].state == 'available') {
                                if(data[j-firstDay].pricetype == 'normal') {
                                    dateHtml+='<tr><td>'+dayText+'<br/><span class="col_gray">剩'+data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td>';
                                } else {
                                    dateHtml+='<tr><td class="cur">'+dayText+'<br/><span class="col_gray">剩'+data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td>';
                                }
                            } else {
                                dateHtml += '<tr><td class="yz">'+dayText+'<p class="col_gray pt5">已租</p></td>'; 
                            }
                        }
                    } else  if(j%7 == 6) {
                        if(today != 0 && j<today+firstDay-1) {
                            dateHtml += '<td class="col_gray">'+dayText+'</td><tr><td colspan="7" class="calendar_line"></td></tr>'; 
                        } else {
                            if(data[j-firstDay].state == 'available') {
                                if(data[j-firstDay].pricetype == 'normal') {
                                    dateHtml+='<td>'+dayText+'<br/><span class="col_gray">剩'+data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td></tr><tr><td colspan="7" class="calendar_line"></td></tr>';
                                } else {
                                    dateHtml+='<td class="cur">'+dayText+'<br/><span class="col_gray">剩'+data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td></tr><tr><td colspan="7" class="calendar_line"></td></tr>';
                                }
                            } else {
                                dateHtml += '<td class="yz">'+dayText+'<p class="col_gray pt5">已租</p></td></tr><tr><td colspan="7" class="calendar_line"></td></tr>'; 
                            }
                        }
                    } else {
                        if(today != 0 && j<today+firstDay-1) {
                            dateHtml += '<td class="col_gray">'+dayText+'</td>'; 
                        } else {
                            if(data[j-firstDay].state == 'available') {
                                if(data[j-firstDay].pricetype == 'normal') {
                                    dateHtml+='<td>'+dayText+'<br/><span class="col_gray">剩' +data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td>';
                                } else {
                                    dateHtml+='<td class="cur">'+dayText+'<br/><span class="col_gray">剩' +data[j-firstDay].housenum+data[j-firstDay].word+'<br/>&yen;'+data[j-firstDay].houseprice+'</span></td>';
                                }
                            } else {
                                dateHtml += '<td class="yz">'+dayText+'<p class="col_gray pt5">已租</p></td>'; 
                            }
                        }
                    }
                } else {
                    if(j%7 == 0) {
                        dateHtml+='<tr><td>'+dayText+'</td>';
                    } else  if(j%7 == 6) {
                        dateHtml+='<td>'+dayText+'</td></tr><tr><td colspan="7" class="calendar_line"></td></tr>';
                    } else {
                        dateHtml+='<td>'+dayText+'</td>';
                    }
                }
                if(j>27 && j == data.length-1+firstDay && j%7 != 6 && !DetailMoreCalendar.isAbroad){
                    dateHtml += '</tr><tr><td colspan="7" class="calendar_line"></td></tr>';
                }
            }
            tbodynode.html(dateHtml);
        });
        //}}}
    }
    //}}}
}