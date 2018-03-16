function login() {

	if ($('#username').val().length === 0 || $('#password').val().length === 0) {
		alert("经理编号或密码不能为空");
		return;
	}

	 var result = {};
	 result.managerNo = $('#username').val();
	 result.password = $('#password').val();
	 $.ajax({
		url : 'http://localhost:8080/Tickets/login/manager_login',
		type : 'POST',
		dataType : 'json',
		data : result,
		success : function(data) {
			if (data.result == true) {
				top.location = 'http://localhost:8080/Tickets/page/manager_hall_statistic.html';
			} else {
				alert("登录失败，原因：" + data.reason);
			}
		},
		error : function(XMLHttpRequest,textStatus,errorThrown) {
			alert("服务器连接失败！");
		}
	});

}