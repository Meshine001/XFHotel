
//弹出
$('#change_mobile').live("click",function(){    
	$.colorbox({
		open:true,
		inline:true,
		width:580,
		href:"#step1",
		overlayClose:true
	});	
	return false;	
});
// upload-face
// $('#upload_button').live("click",function(){    
// 	$.colorbox({
// 		open:true,
// 		inline:true,
// 		width:900,
// 		href:"#upload-face",
// 		overlayClose:true
// 	});	
// 	return false;	
// });
//上传头像
function upload_face()
{
	$.colorbox({
		open:true,
		inline:true,
		width:900,
		href:"#upload-face",
		overlayClose:true
	});	
	return false;	
}

//关闭
$('.close').live("click",function(){    
	$.colorbox.close();	
});
function submit_step1()
{
	var yzm = $("#yzm1").val();
	//tiptext1
	$.getJSON('/ajax/changePassword.ajax.php?action=step1&yzm='+yzm, function (data) {
		if(data.status == 100) {
			//alert(data.msg);
			//进入下一步 step2
			$.colorbox({
				open:true,
				inline:true,
				width:580,
				href:"#step2",
				overlayClose:true
			});	
		}else{
			$("#yzm_error_tip1").show();
			$("#tiptext1").html(data.msg);
		}
	});
}
function submit_step2()
{
	var yzm = $("#yzm2").val();
	var newphone = $("#newphone").val();
	//tiptext1
	$.getJSON('/ajax/changePassword.ajax.php?action=step2&yzm='+yzm+'&newphone='+newphone, function (data) {
		if(data.status == 100) {
			alert(data.msg);
			$.colorbox.close();	
			window.reload();
		}else{
			$("#yzm_error_tip2").show();
			$("#tiptext2").html(data.msg);
			$("#myphone").html(newphone);
		}
	});
}

//验证码
function countdown(g, e, h, c, b, m) {
    var d = new Date().getTime();
    var a;
    //g.attr("disabled", "disabled");
	g.removeAttr("onclick");
    g.attr("class", c);
    g.html( b + "秒<br>后重新发送");
    a = window.setInterval(f, 1000);
    function f() {
        var i = d + b * 1000 - new Date().getTime();
        if (i > 0) {
            g.html( Math.ceil(i / 1000) + "秒<br>后重新发送")
        } else {
            window.clearInterval(a);
            g.html( e);
            g.attr("class", h);
           // g.removeAttr("disabled");
			g.attr( "onclick", "sendcode($(this),'"+m+"')");
        }
    }
}

function sendcode(obj,mobile)
{
	var text = "获取验证码";
	$.getJSON('/ajax/reg.ajax.php?action=sendcode&mobile='+mobile+'&f=change_mobile&uid=28689', function (data) {
			if(data.status == 100) {
				alert(data.msg);
				countdown(obj,text,'pass_btn1','pass_btn1 pass_btn2',120,mobile);
			}else{
				alert(data.msg);
			}
		});
}

function selectOptions(id)
{
	var btnSelect = document.getElementById(id);
	var curSelect = btnSelect.getElementsByTagName("span")[0];
	var oSelect = btnSelect.getElementsByTagName("select")[0];
	var aOption = btnSelect.getElementsByTagName("option");
	oSelect.onchange = function () {
		var text=oSelect.options[oSelect.selectedIndex].text;
		curSelect.innerHTML = text;
	}
}
selectOptions('select_year');
selectOptions('select_month');
selectOptions('select_day');

selectOptions('select_xingzuo');
selectOptions('select_jiaoyu');


//<li value="" class="select_xingqu"><span class="ah_list ah_list_on"><em></em></span></li>id="select_xingqu_1"

$(document).ready(function(){
	// 绑定表单提交事件处理器
	$('#ziliaoForm').submit(function() {
		//锁定按钮
		$("#submitButton").attr("disabled", "disabled");
	    // 提交表单
		$(this).ajaxSubmit({
			success:function(response) {
				if(response.status == 100){
					alert('修改成功');
					location.href = response.url; 
// 					window.reload();
				}else{
					alert(response.msg);
					var _h = response.url; 
					if(_h!=''){
						location.href = _h; 
					}
					$("#submitButton").removeAttr("disabled");
				}  
			},
			dataType: 'json'
		});
		return false;
	});
	$(".input_yzm").focus(function(){
		$(".yzm_error_tip").hide();
	})
})