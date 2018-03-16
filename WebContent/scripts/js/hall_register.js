function register(){
	var hallName = $('#hallName').val();
	var password1 = $('#password1').val();
	var password2 = $('#password2').val();
	var address = $('#address').val();
	var juniorNum = $('#juniorNum').val();
	var seniorNum = $('#seniorNum').val();
	var percent = $('#percent').val();
	if (hallName.length == 0 || password1.length == 0 || password2.length == 0
			|| address.length == 0 || juniorNum.length == 0 || seniorNum.length == 0||percent == 0) {
		alert("仍有选项未填写");
		return;
	}

	var result = {};
	result.hallName = hallName;
	result.password1 = password1;
	result.password2 = password2;
	result.address = address;
	result.juniorNum = juniorNum;
	result.seniorNum = seniorNum;
	result.percent = percent;

	$.ajax({
		url : 'http://localhost:8080/Tickets/register/hall_reg',
		type : 'post',
		dataType : 'json',
		data : result,
		success : function(data) {
			if(data.result==true){
				top.location = 'http://localhost:8080/Tickets/page/hall_login.html';
			}else{
				alert("注册失败，原因："+data.reason);
			}

		},
		error : function() {
			alert("服务器连接失败！");

		},

	});
	
}