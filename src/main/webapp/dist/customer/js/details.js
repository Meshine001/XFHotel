$(document).ready(function() {

	//上传头像
	$("#upload").click(function() {
		 var formData = new FormData($( "#uploadForm" )[0]);  
	     $.ajax({  
	          url: '../file/upload' ,  
	          type: 'POST',  
	          data: formData,  
	          async: false,  
	          cache: false,  
	          contentType: false,  
	          processData: false,  
	          success: function (data) {  
	              $("#face_url").attr("value",data);
	              $("#face_pic_2").attr("src",'../images/'+data);
	          },  
	          error: function (data) {  
	              alert(returndata);  
	          }  
	     });  
	     $.colorbox.close();
	});

	
	$("#submitButton").click(function() {
		var birth = $("#b_year").text()+"/"+
		$("#b_month").text()+"/"+
		$("#b_day").text();
		$("#birthday").val(birth);
		var constellation = $("#c_text").text();
		$("#constellation").val(constellation);
		$("#education").val($("#e_text").text());
		
		$("#detailsForm").submit();
	});
	
	
	if($("#birthday_str").text()!=''){
		var arr = $("#birthday_str").text().split("/");
		var year = arr[0];
		var month = arr[1];
		var day = arr[2];
		$("#b_year").text(year);
		$("#b_month").text(month);
		$("#b_day").text(day);
	}
	
	if($("#xingbie").val()!=''){
		var sex = $("#xingbie").val();
		if(sex == '男'){
			select_xingbie(1);
		}else{
			select_xingbie(2);
		}
	}else{
		select_xingbie(1);
	}
});

// 选择性别
function select_xingbie(n) {
	$(".selectedSex").each(function() {
		$(this).children("i").removeClass("data_xb_on");
	})
	$("#xb_" + n).children("i").addClass("data_xb_on");
	if(n ==1){
		$("#xingbie").val("男");
	}else{
		$("#xingbie").val("女");
	}
	
}

function selectOptions(id) {
	var btnSelect = document.getElementById(id);
	var curSelect = btnSelect.getElementsByTagName("span")[0];
	var oSelect = btnSelect.getElementsByTagName("select")[0];
	var aOption = btnSelect.getElementsByTagName("option");
	oSelect.onchange = function() {
		var text = oSelect.options[oSelect.selectedIndex].text;
		curSelect.innerHTML = text;
	}
}
selectOptions('select_year');
selectOptions('select_month');
selectOptions('select_day');

selectOptions('select_xingzuo');
selectOptions('select_jiaoyu');

function select_xingqu(k) {
	var obj = $("#select_xingqu_" + k).children("span");

	if (obj.hasClass("ah_list_on")) {
		obj.removeClass("ah_list_on");

		// 赋值
		var xq = $("#xingqu").val();
		/*
		 * var xq_arr = xq.split(","); //移除末尾空白 xq_arr.pop(); var _in =
		 * $.inArray(k, xq_arr); if(_in>-1){ var _xq = xq_arr.splice(_in,1);
		 * 
		 * $("#xingqu").val(_xq.join(",")+","); }
		 */
		// 查找
		var cz = xq.indexOf('$' + k + '$');
		if (cz > -1) {
			var _xq = xq.replace('$' + k + '$', '$');
			$("#xingqu").val(_xq);
		}
	} else {
		obj.addClass("ah_list_on");
		// 赋值
		var xq = $("#xingqu").val();
		// 查找
		var cz = xq.indexOf('$' + k + '$');
		if (cz == -1) {
			$("#xingqu").val(xq + k + '$');
		}

	}

}

function upload_face() {
	$.colorbox({
		open : true,
		inline : true,
		width : 900,
		href : "#upload-face",
		overlayClose : true
	});
	return false;
}
