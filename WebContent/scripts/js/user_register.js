function register() {
	var username = $('#username').val();
	var password1 = $('#password1').val();
	var password2 = $('#password2').val();
	var telephone = $('#telephone').val();
	var name = $('#name').val();
	var sex = $('#sex option:selected').val();
	if (username.length == 0 || password1.length == 0 || password2.length == 0
			|| telephone.length == 0 || name.length == 0 || sex.length == 0) {
		alert("仍有选项未填写");
		return;
	}

	if (username.indexOf('@') == -1) {
		alert("用户名须为邮箱格式");
		return;
	}

	var result = {};
	result.username = username;
	result.password1 = password1;
	result.password2 = password2;
	result.telephone = telephone;
	result.name = name;
	result.sex = sex;

	$.ajax({
		url : 'http://localhost:8080/Tickets/register/member_reg',
		type : 'post',
		dataType : 'json',
		data : result,
		success : function(data) {
			if(data.result==true){
				top.location = 'http://localhost:8080/Tickets/page/user_login.html?checking=1';
			}else{
				alert("注册失败，原因："+data.reason);
			}

		},
		error : function() {
			alert("服务器连接失败！");

		},

	});
}