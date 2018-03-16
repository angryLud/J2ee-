function logout() {

	jQuery.ajax({
		url : 'http://localhost:8080/Tickets/logout/allout',
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.result == true) {
				top.location = 'http://localhost:8080/Tickets/page/user_login.html';
			} else {
				alert(data.reason);
			}
		},
		error : function(XMLHttpRequest) {
			alert(XMLHttpRequest.status+"b");
			alert("服务器连接失败");
		}
	});

}