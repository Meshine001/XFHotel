$(document).ready(function(){
	var _id = decodeURIComponent(fnBase.request("id"));
	var point
	getHouseinfo();
	function getHouseinfo(){
		var frontURL=baseUrl+'/admin/apartment/get';
	    var postData={"id":_id};
	    fnBase.commonAjax(frontURL,postData,function(data){
	    	console.log(data)
	    	$("#address").text(data.position.bd_wei_zhi);
	    	$("#wei_zhi").val(data.position.xa_wei_zhi)
	    	$("#jie_dao").val(data.position.jie_dao)
	    	$("#xiao_qu").val(data.position.xiao_qu)
	    	$("#lou_hao").val(data.position.lou_hao)
	    	$("#dan_yuan").val(data.position.dan_yuan)
	    	$("#lou_ceng").val(data.position.lou_ceng)
	    	$("#zong_lou_ceng").val(data.position.zong_lou_ceng)
	    	$("#men_pai").val(data.position.men_pai)
	    	$("#suo_di_zhi").val(data.basic_info.suo_di_zhi)
	    	$("#mian_ji").val(data.basic_info.mian_ji)
	    	$("#shi").val(data.basic_info.shi)
	    	$("#ting").val(data.basic_info.ting)
	    	$("#wei").val(data.basic_info.wei)
	    	$("#yang_tai").val(data.basic_info.yang_tai)
	    	$("#reng_shu").val(data.basic_info.reng_shu)
	    	$("#chuang").val(data.basic_info.chuang)
	    	$("#miao_su").val(data.description)
	    	$("#te_se").val(data.te_se)
	    	$("#jia_ju").val(data.jia_ju)
	    	$("#wei_yu").val(data.wei_yu)
	    	$("#can_chu").val(data.can_chu)
	    	$("#pei_tao").val(data.pei_tao)
	    	$("#zou_bian").val(data.zou_bian)
	    	$("#qi_ta").val(data.qi_ta)
	    	for(var i=0;i<data.pic_show.length;i++){
	    		$("#uploadImg ul li").eq(i).find('img').attr('src',baseUrl+'/images/'+data.pic_show[i])
	    		$("#uploadImg ul li").eq(i).find('img').attr('locad',data.pic_show[i]);
	    	}
	    	$("#jia_ge").val(data.basic_info.jia_ge)
	    	$("#VR_di_zhi").val(data.panorama)
	    })
	}

	
	
	
	
	
    //图片上传begin
	var commersrc=[]; //存房间图图片
	var huxingtusrc=[];//户型图
	

	
//baseUrl
	
    $("#file1").on('change',function(){
        var formData = new FormData($( "#uploadForm1" )[0]);
        $.ajax({
            url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img1").attr('src',baseUrl+'/images/'+data.content);
                	$("#img1").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });

    });
    $("#file2").on('change',function(){
        var formData = new FormData($( "#uploadForm2" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img2").attr('src',baseUrl+'/images/'+data.content);
                	$("#img2").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $("#file3").on('change',function(){
        var formData = new FormData($( "#uploadForm3" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img3").attr('src',baseUrl+'/images/'+data.content);
                	$("#img3").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $("#file4").on('change',function(){
        var formData = new FormData($( "#uploadForm4" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img4").attr('src',baseUrl+'/images/'+data.content);
                	$("#img4").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $("#file5").on('change',function(){
        var formData = new FormData($( "#uploadForm5" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img5").attr('src',baseUrl+'/images/'+data.content);
                	$("#img5").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $("#file6").on('change',function(){
        var formData = new FormData($( "#uploadForm6" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img6").attr('src',baseUrl+'/images/'+data.content);
                	$("#img6").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    $("#file7").on('change',function(){
        var formData = new FormData($( "#uploadForm7" )[0]);
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	$("#img7").attr('src',baseUrl+'/images/'+data.content);
                	$("#img7").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });   
   
    $("#file8").on('change',function(){
        var formData = new FormData($( "#uploadForm8" )[0]); 
        $.ajax({
        	  url: baseUrl+'/file/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                if(data.statusCode==1){
                	 $("#img8").attr('src',baseUrl+'/images/'+data.content);
                	 $("#img8").attr('locad',data.content);
                }else if(data.statusCode==0){
                	fnBase.myalert(data.content)
                }
               
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
    
	//图片上传end
    
    var x="",y="";
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function (r) {
        if (this.getStatus() == BMAP_STATUS_SUCCESS) {
            var map = new BMap.Map("map");
        
            map.centerAndZoom(r.point, 12); //定义地图等级，就是放大倍数
            map.enableScrollWheelZoom(true); //启用地图滚轮放大缩小   
            var marker = new BMap.Marker(r.point);// 创建标注
            map.addOverlay(marker);  // 将标注添加到地图中
            map.panTo(r.point);
        //    getaddress(r.point.lng,r.point.lat) //默认标注自己地址
            
           // alert('您的位置：' + r.point.lng + ',' + r.point.lat);
              
            marker.enableDragging(); //标注可拖拽
            //marker.disableDragging();           // 不可拖拽

            // 开启事件监听
            marker.addEventListener("dragend", function (e) {

                 x = e.point.lng; //经度
                 y = e.point.lat; //纬度
              
                 getaddress(x, y)
            });
        }
        else {
            alert('failed' + this.getStatus());
        }
   	
    }, { enableHighAccuracy: true })
    
    // 根据经纬度获取地址；
    var add="";
    function getaddress(lng, lat){ 
    	var pt = new BMap.Point(lng, lat);
    	var geoc = new BMap.Geocoder();
    	geoc.getLocation(pt,function(rs){
    				var addComp = rs.addressComponents;
    				add=addComp.province + ", " + addComp.city+ ", " + addComp.district + ", "+ addComp.street;
    				$("#address").text(add)
    	});
    }
    
    
    //  1:  创建所有弹窗data

    var info={
    	"tese":{
    		"name":['智能门锁','投影电视','健身器材','麻将桌','茶桌'],
    		"icon":['icon-smartlock','icon-projector','icon-Fitness','icon-mahjong','icon-tea']
    	},
    	"jiaju":{
    		"name":['无线电','电视','冰箱','空调','洗衣机','热水器'],
    		"icon":['icon-wifi','icon-tv','icon-icebox','icon-airconditioner','icon-washingmachine','icon-hatwort']
    	},
    	"weiyu":{
    		"name":['淋雨','毛巾','拖鞋','洗发露','牙具','卫生纸','电吹风'],
    		"icon":['icon-hairandbodylotion','icon-towel','icon-slipper','icon-liquidshampoo','icon-toiletthings','icon-tissues','icon-hairdryer']
    	},
    	"canchu":{
    		"name":['烹饪锅具','餐具','电饭煲','微波炉'],
    		"icon":['icon-cookingpots','icon-atableware','icon-electriccooker','icon-wbl']
    	},
    	"peitao":{
    		"name":['小区门禁','小区保安','电梯','市政供暖'],
    		"icon":['icon-accesscontrol','icon-publicsecurity','icon-elevator','icon-heating']
    	},
    	"zhoubian":{
    		"name":['地铁','公交站','餐馆','商城','银行','便利店','医院','药店','电影院','KTV','娱乐场所','旅游景点'],
    		"icon":['icon-metro','icon-BUS','icon-restaurant','icon-market','icon-bank','icon-svs','icon-infirmary','icon-drugstore','icon-cinema','icon-KTV','icon-recreation','icon-sightspot']
    	},
    	"qita":{
    		"name":['可做饭','可吸烟','可聚会'],
    		"icon":['icon-Cooking','icon-smoke','icon-elevator','icon-dinetogether']
    	}
    }
 //   console.log(info)
    // 存放value
    var nameArray=[],
       classArray=[],
       namelist="",
       classlist="";

    
    // 给选中的data添加状态
    $(".module-con").on('click','a',function(){
    	if($(this).hasClass('active')==true){
    		$(this).removeClass('active')
    	}else{
    		$(this).addClass('active')
    	}
    })
   // 取消 ( 公共 ) 
    function removeActive(){
 	   $("#maskShow,#module-box").hide();
	   $(".module-con").html('');
	   $(".module-con a").removeClass('active');
	   $(".module-footer a:nth-child(1)").removeAttr('class');
	   nameArray=[];
	   classArray=[];
    }
    
   $(".module-footer a:nth-child(2)").click(function(){
	   removeActive();
   })
    // 触发选择弹窗
    $(".houseList .new .newmask").click(function(){
    	var $this=$(this).attr('item');
    	
    	
    	if($this=='tese'){
    		$("#module-box .module-state").text('房屋特色');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.tese.name.length;i++){
    			vv+='<a class="'+info.tese.icon[i]+'" data-class="'+info.tese.icon[i]+'" data-names="'+info.tese.name[i]+'">'+info.tese.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('teseBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	
    	if($this=='jiaju'){
    		$("#module-box .module-state").text('室内家居');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.jiaju.name.length;i++){
    			vv+='<a class="'+info.jiaju.icon[i]+'" data-class="'+info.jiaju.icon[i]+'" data-names="'+info.jiaju.name[i]+'">'+info.jiaju.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('jiajuBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	
    	
    	if($this=='weiyu'){
    		$("#module-box .module-state").text('室内卫浴');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.weiyu.name.length;i++){
    			vv+='<a class="'+info.weiyu.icon[i]+'" data-class="'+info.weiyu.icon[i]+'" data-names="'+info.weiyu.name[i]+'">'+info.weiyu.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('weiyuBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	
    	if($this=='canchu'){
    		$("#module-box .module-state").text('餐厨');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.canchu.name.length;i++){
    			vv+='<a class="'+info.canchu.icon[i]+'" data-class="'+info.canchu.icon[i]+'" data-names="'+info.canchu.name[i]+'">'+info.canchu.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('canchuBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	
    	
    	if($this=='peitao'){
    		$("#module-box .module-state").text('配套');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.peitao.name.length;i++){
    			vv+='<a class="'+info.peitao.icon[i]+'" data-class="'+info.peitao.icon[i]+'" data-names="'+info.peitao.name[i]+'">'+info.peitao.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('peitaoBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	    	
    	
    	if($this=='zhoubian'){
    		$("#module-box .module-state").text('周边');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.zhoubian.name.length;i++){
    			vv+='<a class="'+info.zhoubian.icon[i]+'" data-class="'+info.zhoubian.icon[i]+'" data-names="'+info.zhoubian.name[i]+'">'+info.zhoubian.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('zhoubianBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	
    	
    	if($this=='qita'){
    		$("#module-box .module-state").text('其他');
    		$("#module-box .module-con").html('');
    		var vv="";
    		for(var i=0;i<info.qita.name.length;i++){
    			vv+='<a class="'+info.qita.icon[i]+'" data-class="'+info.qita.icon[i]+'" data-names="'+info.qita.name[i]+'">'+info.qita.name[i]+'<span></span></a>';
    		}
    		$("#module-box .module-con").append(vv);
    		$(".module-footer a:nth-child(1)").addClass('qitaBtn');
    		$("#maskShow,#module-box").fadeIn();
    	}
    	    	
    })
    
    // 选择好需要添加的特色后 ==> 添加到 value中；
    $(".module-footer").on('click','.teseBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='tese'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='tese'] input:nth-child(2)").attr('value',classlist)

    })
    // 选择好需要添加的家居后 ==> 添加到 value中；
    $(".module-footer").on('click','.jiajuBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='jiaju'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='jiaju'] input:nth-child(2)").attr('value',classlist)

    })    
    
       // 选择好需要添加的卫浴后 ==> 添加到 value中；
    $(".module-footer").on('click','.weiyuBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='weiyu'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='weiyu'] input:nth-child(2)").attr('value',classlist)

    })   

       // 选择好需要添加的餐厨后 ==> 添加到 value中；
    $(".module-footer").on('click','.canchuBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='canchu'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='canchu'] input:nth-child(2)").attr('value',classlist)

    })       
    
       // 选择好需要添加的配套后 ==> 添加到 value中；
    $(".module-footer").on('click','.peitaoBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='peitao'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='peitao'] input:nth-child(2)").attr('value',classlist)

    })       
        
       // 选择好需要添加的周边后 ==> 添加到 value中；
    $(".module-footer").on('click','.zhoubianBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='zhoubian'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='zhoubian'] input:nth-child(2)").attr('value',classlist)

    })       
          
       // 选择好需要添加的周边后 ==> 添加到 value中；
    $(".module-footer").on('click','.qitaBtn',function(){ 
    	for(var i=0;i<$(".module-con a").length;i++){
    		if($(".module-con a").eq(i).hasClass('active')==true){
    			nameArray.push($(".module-con a").eq(i).attr('data-names'))
    			classArray.push($(".module-con a").eq(i).attr('data-class'))
    		}
    	}
    	namelist=nameArray.join(',');
    	classlist=classArray.join(',');
    	removeActive();
    	$(".houseList .new .newmask[item='qita'] input:nth-child(1)").attr('value',namelist)
    	$(".houseList .new .newmask[item='qita'] input:nth-child(2)").attr('value',classlist)

    })       
      
     var wu_ss="";
	function commerSrc(){ //图片
    	var mm=[];
    	var wu=["","","","",""];
	    for(var i=0;i<$("#uploadImg ul .fangjiantu").length;i++){
	    	var jj=$("#uploadImg ul .fangjiantu").eq(i).find('img').attr('locad');
	    	mm.push(jj)
	    	if(mm[i] ==null || mm[i] =="" || mm[i] =="undefined"){
	    		
	    	}else{
	    		commersrc.push(mm[i])
	    	}
	    }
	    var  wu_number=commersrc.concat(wu);
	    wu_ss=wu_number.splice(0,5);
   
        return wu_ss;
	}

   var san_ss="";
	function huxingtuSrc(){ //图片
    	var gg=[];
    	var san=["","",""];
	    for(var i=0;i<$("#uploadImg ul .huxingtusrc").length;i++){
	    	var jj=$("#uploadImg ul .huxingtusrc").eq(i).find('img').attr('locad');
	    	gg.push(jj)
	    	if(gg[i] ==null || gg[i] =="" || gg[i] =="undefined"){
	    		
	    	}else{
	    		huxingtusrc.push(gg[i])
	    	}
	    }
	    var  san_number=huxingtusrc.concat(san);
	         san_ss=san_number.splice(0,3);
	    
	   return san_ss;
	}
   


   
	$("#push_btn").click(function(){
		 commerSrc()
		 huxingtuSrc()
		 
		var pic1=[];
		var pic3=['','',''];
        var frontURL= baseUrl+'/admin/apartment/update11/'+_id;

        var postData={
        		'jing_du':x,
        		'wei_du':y,
		  		'xa_wei_zhi':$("#wei_zhi option:selected").val(),
		        'jie_dao':$("#jie_dao").val(),
		  		'xiao_qu':$("#xiao_qu").val(),
		  		'lou_hao':$("#lou_hao").val(),
		  		'dan_yuan':$("#dan_yuan").val(),
		  		'lou_ceng':$("#lou_ceng").val(),
		  		'zong_lou_ceng':$("#zong_lou_ceng").val(),
		  		'men_pai':$("#men_pai").val(),
		  		'suo_di_zhi':$("#suo_di_zhi").val(),
		  		'cao_xiang':$("#cao_xiang option:selected").val(),
		  		'mian_ji':$("#mian_ji").val(),
		  		'shi':$("#shi").val(),
		  		'ting':$("#ting").val(),
		  		'wei':$("#wei").val(),
		  		'yang_tai':$("#yang_tai").val(),		
		  		'reng_shu':$("#reng_shu").val(),	
		  		'chuang':$("#chuang").val(),	
		  		'miao_su':$("#miao_su").val(),		
		  		'te_se':$("#te_se").attr('value'),	
		  		'te_se_class':$("#te_se_class").attr('value'),	
		  		'jia_ju':$("#jia_ju").attr('value'),	
		  		'jia_ju_class':$("#jia_ju_class").attr('value'),	
		  		'wei_yu':$("#wei_yu").attr('value'),	
		  		'wei_yu_class':$("#wei_yu_class").attr('value'),	
		  		'can_chu':$("#can_chu").attr('value'),	
		  		'can_chu_class':$("#can_chu_class").attr('value'),		
		  		'pei_tao':$("#pei_tao").attr('value'),	
		  		'pei_tao_class':$("#pei_tao_class").attr('value'),	
		  		'zhou_bian':$("#zhou_bian").attr('value'),	
		  		'zhou_bian_class':$("#zhou_bian_class").attr('value'),		  
		  		'qi_ta':$("#qi_ta").attr('value'),	
		  		'qi_ta_class':$("#qi_ta_class").attr('value'),	
		  		'pic2':wu_ss,
		 		'pic3':san_ss,
		 		'pic1':pic1,
		  		'jia_ge':$("#jia_ge").val(),	
		  		'VR_di_zhi':$("#VR_di_zhi").val(),
		  		'bd_wei_zhi':$("#address").text()
        };

        $.ajax({
        	url:frontURL,
        	data:postData,
            dataType:"json",
            traditional:true,
            async : true,
            type : "POST",
        	success:function(data){
        		console.log(data)
        		if(data!="" || data !=null){
        			fnBase.myalert('提交成功')
        			setTimeout(function(){
        				window.location.href="apartment.html"
			    	}, 1800);
        			
        		}
        	}
        })
        
     
       
        
        
	})
    
    
})